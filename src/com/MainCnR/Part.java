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
   
    protected Color color;
    protected Short priority;
    protected Vector2D shift;
    public static final short MAX_PRIORITY = 1000;

    public Part(Color color, Short priority, Vector2D shift) {
        this.color = color;
        if(priority < MAX_PRIORITY)this.priority = priority;
        else this.priority = MAX_PRIORITY;
        this.shift = shift;
    }

    public Part(Color color, Vector2D shift) {
        this.color = color;
        this.shift = shift;
        this.priority = MAX_PRIORITY;
    }

    

    public abstract void render(Graphics graph, Vector2D anchorPoint, double sin);
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
    
    public abstract ArrayList <Vector2D> getCharateristicPoints();
    public abstract double getMoI();
    public abstract Vector2D getFurthestPoint(Vector2D point);
    public abstract double getField();
}
