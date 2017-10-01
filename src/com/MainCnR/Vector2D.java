/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MainCnR;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Mateusz Śliwiński
 * @author Michał Jan Odorczuk (the more contributing one)
 * 
 */
public class Vector2D
{
    
    public double x;
    public double y;
    
    public Vector2D(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    public void setVector(double x, double y)
    {
	this.x = x;
	this.y = y;
    }
    
    public void setVector(Vector2D newVector)
    {
	this.x = newVector.x;
        this.y = newVector.y;
    }
    
    public void setVectoR(double radius, double radians)
    {
	this.x = radius * Math.cos(radians);
	this.y = radius * Math.sin(radians);
    }
    
    public double getLength()
    {
	return Math.sqrt(x * x + y * y);
    }
    
    public double getAbsRotation()
    {
	if (y == 0 && x == 0)
	{
		return 0;
	}
	else
	{
		if (x >= 0 && y >= 0)
			return Math.asin(y / getLength());
		if (x <= 0 && y >= 0)
			return Math.PI - Math.asin(y / getLength());
		if (x <= 0 && y <= 0)
			return Math.PI - Math.asin(y / getLength());
		if (x >= 0 && y <= 0)
			return 2 * Math.PI + Math.asin(y / getLength());
		return 0;
	}
    }   
    
    public double getRotation()
    {
	double temp = getAbsRotation();
	if (temp > Math.PI)
	{
		return temp - 2 * Math.PI;
	}
	return temp;
    }
    
    public void draw(Graphics graph, Vector2D position, float scale)
    {
	graph.drawLine(
                (int)position.x,
                (int)position.y,
                (int)(position.x + x * scale),
                (int)(position.y + y * scale));
    }
    
    public Vector2D Add(Vector2D newVector)
    {
        return new Vector2D(this.x + newVector.x, this.y + newVector.y);
    }
    
    public Vector2D Sub(Vector2D newVector)
    {
        return new Vector2D(this.x - newVector.x, this.y - newVector.y);
    }
    
    public Vector2D Multiply(double multi)
    {
        return new Vector2D(this.x * multi, this.y * multi);
    }
    
    public Vector2D getUnit()
    {
        return new Vector2D(this.x / getLength(), this.y / getLength());
    }
    
    public boolean isOnLine(Vector2D p1, Vector2D p2)
    {
        if((x - p1.x)/(y - p1.y) == (p2.x - p1.x)/(p2.y - p1.y)) return true;
                else return false;
    }
    
    public boolean isInside(Vector2D p1, Vector2D p2)
    {
        if((x > p1.x ^ x > p2.x) && (y > p1.y ^ y > p2.y)) return true;
        else return false;
    }
    
    public ArrayList<Double> getParams(Vector2D point)
    {
        ArrayList<Double> params = new ArrayList<>();
        if(x == point.x)
        {
            params.add(1.0);
            params.add(0.0);
            params.add(-x);
            return params;
        }
        else
        {
            params.add((point.y - y)/(x - point.x));
            params.add(1.0);
            params.add(- params.get(0) * x - y);
            return params;
        }
    }
    
    public double crossProduct(Vector2D crossed)
    {
        return x * crossed.y - y * crossed.x;
    }
    
    public Vector2D complexProduct(Vector2D multiplier)
    {
        return new Vector2D(this.x * multiplier.x - this.y * multiplier.y,this.x * multiplier.y + this.y * multiplier.x);
    }
    
    public Vector2D complexDivision(Vector2D divider)
    {
        return this.complexConjugateProduct(divider).Multiply(1/Math.pow(divider.getLength(), 2));
    }
    
    public Vector2D complexConjugateProduct(Vector2D multiplier)
    {
        return new Vector2D(this.x * multiplier.x + this.y * multiplier.y, this.y * multiplier.x - this.x * multiplier.y);
    }
}
