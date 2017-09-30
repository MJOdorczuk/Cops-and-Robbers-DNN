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
public class Line extends Part{

    Vector2D p1, p2;

    public Line(Vector2D p1, Vector2D p2, Color color, Short priority) {
        super(color, priority);
        this.p1 = p1;
        this.p2 = p2;
    }

    public Line(Vector2D p1, Vector2D p2, Color color) {
        super(color);
        this.p1 = p1;
        this.p2 = p2;
    }
    
    @Override
    public void render(Graphics graph) {
        graph.setColor(color);
        graph.drawLine((int)p1.x, (int)p1.y, (int)p2.x, (int)p2.y);
    }

    @Override
    public double getCharactericticValue() {
        return 0;
    }

    @Override
    public ArrayList<Vector2D> getCharateristicPoints() {
        ArrayList<Vector2D> ret = new ArrayList<>();
        ret.add(p1);
        ret.add(p2);
        return ret;
    }

    @Override
    public double getMoI() {
        return 0;
    }

    @Override
    public Vector2D getFurthestPoint(Vector2D point) {
        if(point.Sub(p1).getLength() > point.Sub(p2).getLength()) return p1;
        else return p2;
    }

    @Override
    public double getField() {
        return 0;
    }
    
    
}
