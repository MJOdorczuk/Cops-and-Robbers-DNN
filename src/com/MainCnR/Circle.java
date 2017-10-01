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
    /**
     * Middle point of circle.
     */
    private Vector2D mPoint;
    
    /**
     * 
     * @param radius
     * @param mPoint - middle point of circle.
     * @param color
     * @param priority
     * @param shift - shift from middle point of assembly to middle point of circle
     */
    public Circle(double radius, Vector2D mPoint, Color color, Short priority) {
        super(color, priority);
        this.radius = radius;
        this.mPoint = mPoint;
    }

    /**
     * 
     * @param radius
     * @param mPoint - middle point of circle.
     * @param color
     * @param shift - shift from middle point of assembly to middle point of circle.
     */
    public Circle(double radius, Vector2D mPoint, Color color) {
        super(color);
        this.radius = radius;
        this.mPoint = mPoint;
    }

    /**
     * 
     * @param graph
     * @param anchorPoint - coordinates of middle point of assembly on screen.
     * @param rotation - rotation of whole assembly.
     */
    @Override
    public void render(Graphics graph, Vector2D anchorPoint, Vector2D rotation, Vector2D local) 
    {
        graph.setColor(color);
        graph.drawOval((int)(mPoint.complexProduct(rotation).Add(anchorPoint).Sub(local).x - radius), (int)(mPoint.complexProduct(rotation).Add(anchorPoint).Sub(local).y - radius), (int)(2*radius), (int)(2*radius));
    }
    
    /**
     * Returns middle point of circle.
     */
    @Override
    public ArrayList <Vector2D> getCharacteristicPoints()
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
