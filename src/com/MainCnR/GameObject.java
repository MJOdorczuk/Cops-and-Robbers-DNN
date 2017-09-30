/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MainCnR;

import java.awt.Graphics;

/**
 *
 * @author Mateusz Śliwiński
 */
public abstract class GameObject
{
    protected ID id;
    protected Vector2D position;
    protected Vector2D velocity;
    protected Vector2D anchorPoint;
    protected double rotation;
    protected double timeInterval;
    protected Assembly model;
    
    public GameObject(int x, int y, ID id)
    {
        this.position = new Vector2D(x, y);
        this.velocity = new Vector2D(0, 0);
        this.id = id;
    }
    
    public abstract void tick(double deltaTime);
    public abstract void render(Graphics graph, Vector2D local);

    public Vector2D getPosition()
    {
        return position;
    }

    public Vector2D getVelocity()
    {
        return velocity;
    }

    public ID getId()
    {
        return id;
    }

    public void setId(ID id)
    {
        this.id = id;
    }

    public double getRotation()
    {
	return rotation;
    }

    public void setRotation(double rotation)
    {
        this.rotation = rotation;
    }
    
    
}
