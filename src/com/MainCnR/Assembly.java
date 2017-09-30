/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MainCnR;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author MJOdorczuk
 */
public class Assembly {
    
    ArrayList <Part> Party = new ArrayList<>();
    double bubble;
    double mass;
    double MoI;
    Vector2D mPoint;
    ArrayList<Vector2D> blocks;

    public Assembly(double bubble, double mass, double MoI, Vector2D mPoint) {
        this.bubble = bubble;
        this.mass = mass;
        this.MoI = MoI;
        this.mPoint = mPoint;
    }

    public Assembly(double mass, double MoI, Vector2D mPoint) {
        this.mass = mass;
        this.MoI = MoI;
        this.mPoint = mPoint;
    }

    public Assembly(double mass, Vector2D mPoint) {
        this.mass = mass;
        this.mPoint = mPoint;
    }
    
    public void addParts(Part... Party)
    {
        for(int i=0; i<Party.length; i++) this.Party.add(Party[i]);
        sort();
    }
    
    public void removeParts(Part... Party)
    {
        for(int i=0; i<Party.length; i++) this.Party.remove(Party[i]);
    }
    
    public void sort()
    {
        Collections.sort(Party, new Comparator<Part>(){
            @Override
            public int compare(Part p1, Part p2)
            {
                return p1.priority.compareTo(p2.priority);
            }
        }
        );
    }
    
    public double calculateMoI()
    {
        double MoI = 0;
        double field = 0;
        Vector2D point = new Vector2D(0,0);
        for(int i=0; i<Party.size(); i++)
        {
            point.setVector(middlePoint(Party.get(i).getCharateristicPoints()));
            MoI += Party.get(i).getMoI() + Party.get(i).getField() * Math.pow(point.Sub(mPoint).getLength(), 2);
            field += Party.get(i).getField();
        }
        return MoI * mass / field;
    }
    
    public double calculateBubble()
    {
        double radius = 0;
        for(int i=0; i<Party.size(); i++)
        {
            radius = Math.max(radius, mPoint.Sub(Party.get(i).getFurthestPoint(mPoint)).getLength());
        }
        return radius * 1.01;
    }
    
    public Vector2D ifCollide(Assembly Collider)
    {
        Vector2D tmp = new Vector2D(0,0);
        Collision collision = new Collision(null, null);
        for(int i=0; i<Party.size(); i++)
        {
            for(int j=0; j<Collider.getParty().size(); j++)
            {
                collision.setCollider1(Party.get(i));
                collision.setCollider2(Collider.Party.get(j));
                tmp = collision.ifCollide();
                if(tmp != null) return tmp;
            }
        }
        return null;
    }
    
    public void block(Assembly Collider)
    {
        Vector2D PoC = ifCollide(Collider);
        Collider.blocks.add(PoC.Sub(Collider.mPoint).getUnit());
        this.blocks.add(PoC.Sub(this.mPoint).getUnit());
    }
    
    public ArrayList<Part> getParty() {
        return Party;
    }

    public void setParty(ArrayList<Part> Party) {
        this.Party = Party;
        sort();
    }

    public double getBubble() {
        return bubble;
    }

    public void setBubble(double bubble) {
        this.bubble = bubble;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getMoI() {
        return MoI;
    }

    public void setMoI(double MoI) {
        this.MoI = MoI;
    }

    public Vector2D getmPoint() {
        return mPoint;
    }

    public void setmPoint(Vector2D mPoint) {
        this.mPoint = mPoint;
    }
    
    public void setParty(Part... Party)
    {
        this.Party = new ArrayList<>(Arrays.asList(Party));
        sort();
    }
    
    public Vector2D middlePoint(Vector2D... points)
    {
        Vector2D mPoint = new Vector2D(0,0);
        short j = 0;
        for(int i=0; i<points.length; i++)
        {
            if(points[i] != null)
            {
                mPoint.Add(points[i]);
                j++;
            }
        }
        if(j == 0) return null;
        else return mPoint.Multiply(1/((double)j));
    }
    
    public Vector2D middlePoint(ArrayList <Vector2D> points)
    {
        Vector2D mPoint = new Vector2D(0,0);
        short j = 0;
        for(int i=0; i<points.size(); i++)
        {
            if(points.get(i) != null)
            {
                mPoint.Add(points.get(i));
                j++;
            }
        }
        if(j == 0) return null;
        else return mPoint.Multiply(1/((double)j));
    }
    
}
