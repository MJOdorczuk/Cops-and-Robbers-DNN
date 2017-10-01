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
public class Circle extends Part{

    private double radius;
    private Vector2D mPoint;
    public final static double MARGIN = 1;

    public Circle(double radius, Vector2D mPoint, Color color, Short priority, Vector2D shift) {
        super(color, priority, shift);
        this.radius = radius;
        this.mPoint = mPoint;
    }

    public Circle(double radius, Vector2D mPoint, Color color, Vector2D shift) {
        super(color, shift);
        this.radius = radius;
        this.mPoint = mPoint;
    }

    
    @Override
    public void render(Graphics graph, Vector2D anchorPoint, Vector2D rotation) 
    {
        graph.setColor(color);
        graph.drawOval((int)(mPoint.complexProduct(rotation).Add(anchorPoint).x - radius), (int)(mPoint.complexProduct(rotation).Add(anchorPoint).y - radius), (int)(2*radius), (int)(2*radius));
    }
    
    @Override
    public ArrayList <Vector2D> getCharateristicPoints()
    {
        ArrayList <Vector2D> Points = new ArrayList <>();
        Points.add(mPoint);
        return Points;
    }
    
    @Override
    public double getCharactericticValue()
    {
        return this.radius;
    }

    @Override
    public double getMoI() {
        return radius*radius/2;
    }

    @Override
    public Vector2D getFurthestPoint(Vector2D point) {
        return mPoint.Add(point.Sub(mPoint).getUnit().Multiply(radius));
    }

    @Override
    public double getField() {
        return Math.PI * radius * radius;
    }

}
