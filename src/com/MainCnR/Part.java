/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MainCnR;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author MJOdorczuk
 */
public abstract class Part {
   
    
    /**
     * Color of part of assembly to render onto screen.
     */
    protected Color color;
    /**
     * Priority of rendering.
     * If user want one part to cover another than he should give covering part lower value for piority.
     * The lower value (0 - MAX_PRIORITY) the higher priority of rendering.
     */
    protected Short priority;
    /**
     * Vector(x,y) of shift between middle point of whole assembly and middle point of part.
     */
    protected Vector2D shift;
    /**
     * Max possible value of priority variable.
     */
    public static final short MAX_PRIORITY = 1000;

    public Part(Color color, Short priority, Vector2D shift) {
        this.color = color;
        if(priority < MAX_PRIORITY)
            if(priority >= 0) this.priority = priority;
            else this.priority = MAX_PRIORITY;
        else this.priority = MAX_PRIORITY;
        this.shift = shift;
    }

    public Part(Color color, Vector2D shift) {
        this.color = color;
        this.shift = shift;
        this.priority = MAX_PRIORITY;
    }
    
    /**
     * 
     * @param graph
     * @param anchorPoint - middle point of whole assembly
     * @param rotation 
     */
    public abstract void render(Graphics graph, Vector2D anchorPoint, Vector2D rotation, Vector2D local);
    public abstract double getCharactericticValue();

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public short getPriority() {
        return priority;
    }

    public void setPriority(short priority) {
        this.priority = priority;
    }

    public Vector2D getShift() {
        return shift;
    }

    public void setShift(Vector2D shift) {
        this.shift = shift;
    }
    
    
    public abstract ArrayList <Vector2D> getCharacteristicPoints();
    /**
     * Calcualte moment of inertia relative to its middle point.
     */
    public abstract double getMoI();
    public abstract Vector2D getFurthestPoint(Vector2D point);
    public abstract double getField();
    public Vector2D getmPoint() {
        return getAveragePoint(getCharacteristicPoints());
    }
    protected Vector2D getAveragePoint(ArrayList<Vector2D> points)
    {
        Vector2D ap = new Vector2D(0,0);
        for(int i=0; i<points.size(); i++)
        {
            ap.Add(points.get(i));
        }
        return ap.Multiply(1/points.size());
    }
}
