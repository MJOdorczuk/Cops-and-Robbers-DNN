/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MainCnR;

import java.awt.Color;
import java.awt.Graphics;


/**
 *
 * @author Mateusz Śliwiński
 *
 */
public abstract class Actor extends GameObject
{
    protected double sightAngle;
    protected double sightDistance;
    protected double controllerTimer;
    protected boolean[] key;
    protected boolean seeAnyone;
    protected Vector2D blockVector;
            
    public Actor(int x, int y, ID id)
    {
        super(x, y, id);
        key = new boolean[15];
        rotation = (Math.random() * 2 * Math.PI);
        sightAngle = Math.random() * 1.4 + 0.1;
        sightDistance = Math.random() * 150 + 50;
        blockVector = new Vector2D(0, 0);
    }
    
    public abstract void controller(double deltaTime);
    
    protected void updatePhysics(double deltaTime)
    {
        if(key[KeyInput.KEY_W])
        {
            velocity.setVectoR(200, getRotation());
        }
        if(key[KeyInput.KEY_S])
        {
            velocity.setVectoR(-100, getRotation());
        }
        if(key[KeyInput.KEY_A])
        {
            setRotation(getRotation() - 0.05);
            if(getRotation() < - Math.PI)
               setRotation(2 * Math.PI + getRotation()); 
        }
        if(key[KeyInput.KEY_D])
        {
            setRotation(getRotation() + 0.05);
            if(getRotation() > Math.PI)
               setRotation(getRotation() - 2 * Math.PI);
        }
        if(key[KeyInput.KEY_Q] && sightAngle > 0.1)
        {
            sightAngle -= 0.05;
        }
        if(key[KeyInput.KEY_E] && sightAngle < 1.5)
        {
            sightAngle += 0.05;
        }
        velocity = velocity.Sub(blockVector);
        velocity = velocity.Multiply(deltaTime);
        position = position.Add(velocity);
    }
    
    public boolean lookFor(GameObject object)
    {
        Vector2D oto = object.position.Sub(position);
        Vector2D lookAt = new Vector2D(0,0);
        lookAt.setVectoR(1, getRotation());
        double angle = Math.acos((oto.x * lookAt.x + oto.y * lookAt.y) / (oto.getLength() * lookAt.getLength()));
            if(angle <= sightAngle / 2 && oto.getLength() <= sightDistance)
            {
                seeAnyone = true;
                return seeAnyone;
            }
        return false;
    }
    
    protected void drawSight(Graphics graph, Vector2D local)
    {
        if(seeAnyone)
        {
            graph.setColor(Color.red);
        }
        else
        {
            graph.setColor(Color.yellow);
        }
        Vector2D localPosition = new Vector2D(
                position.x - local.x,
                position.y - local.y);
        Vector2D point1 = new Vector2D(
                sightDistance * Math.cos(rotation + sightAngle / 2),
                sightDistance * Math.sin(rotation + sightAngle / 2));
        Vector2D point2 = new Vector2D(
                sightDistance * Math.cos(rotation - sightAngle / 2),
                sightDistance * Math.sin(rotation - sightAngle / 2));
        point1 = point1.Add(localPosition);
        point2 = point2.Add(localPosition);
        graph.drawLine(
                (int)localPosition.x,
                (int)localPosition.y,
                (int)point1.x,
                (int)point1.y);
        graph.drawLine(
                (int)localPosition.x,
                (int)localPosition.y,
                (int)point2.x,
                (int)point2.y);
        graph.drawLine(
                (int)point1.x,
                (int)point1.y,
                (int)point2.x,
                (int)point2.y);
        /*graph.drawArc(
                (int)(position.x - sightDistance - local.x),
                (int)(position.y - sightDistance - local.y),
                (int)(sightDistance * 2), (int)(sightDistance * 2),
                (int)Math.toDegrees(endAngle),
                (int)Math.toDegrees(startAngle));*/
    }

    public double getSightAngle()
    {
        return sightAngle;
    }

    public void setSightAngle(double sightAngle)
    {
        this.sightAngle = sightAngle;
    }

    public boolean isSeeAnyone()
    {
        return seeAnyone;
    }

    public void setSeeAnyone(boolean seeAnyone)
    {
        this.seeAnyone = seeAnyone;
    }
    
    
}
