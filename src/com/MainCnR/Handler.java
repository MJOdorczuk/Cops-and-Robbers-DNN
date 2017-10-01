/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MainCnR;

import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author Mateusz Śliwiński
 */
public class Handler
{
    private Vector2D localPosition;
    private KeyInput keyInput;
    LinkedList<GameObject> object;
    LinkedList<Actor> actor;
    private double deltaTime;

    public Handler(KeyInput keyInput)
    {
        localPosition = new Vector2D(-50, -50);
        this.keyInput = keyInput;
        object = new LinkedList<>();
        actor = new LinkedList<>();
    }
    
    
    
    public void tick()
    {
        boolean[] key = keyInput.getKeys();
        for(int i = 0; i < object.size(); i++)
        {
            GameObject tempObject = object.get(i);   
            tempObject.tick(deltaTime);
            if(tempObject.getId() == ID.Programmer &&
                    tempObject.velocity.getLength() != 0 &&
                    !key[KeyInput.KEY_UP] &&
                    !key[KeyInput.KEY_DOWN] &&
                    !key[KeyInput.KEY_LEFT] &&
                    !key[KeyInput.KEY_RIGHT])
            {
                Vector2D tempVector = tempObject.position.Add(new Vector2D(- Game.WINDOW_WIDTH / 2, - Game.WINDOW_HEIGHT / 2).Sub(localPosition));
                localPosition = localPosition.Add(tempVector.Multiply(2 * deltaTime));
            }
        }
        for(int i = 0; i < actor.size(); i++)
        {
            Actor tempMain = actor.get(i);
            tempMain.setSeeAnyone(false);
            for(int j = 0; j < actor.size(); j++)
            {
                Actor tempOther;
                if(j != i)
                {
                    tempOther = actor.get(j);
                    tempMain.lookFor(tempOther);
                    //Collision detection
                }
            }
        }
        
        if(key[KeyInput.KEY_UP])
        {
            localPosition = localPosition.Add(new Vector2D(0, -500 * deltaTime));
        }
        if(key[KeyInput.KEY_DOWN])
        {
            localPosition = localPosition.Add(new Vector2D(0, 500 * deltaTime));
        }
        if(key[KeyInput.KEY_LEFT])
        {
            localPosition = localPosition.Add(new Vector2D(-500 * deltaTime, 0));
        }
        if(key[KeyInput.KEY_RIGHT])
        {
            localPosition = localPosition.Add(new Vector2D(500 * deltaTime, 0));
        }
        
    }
    public void render(Graphics graph)
    {
        for(int i = 0; i < object.size(); i++)
        {
            GameObject tempObject = object.get(i);
            tempObject.render(graph, localPosition);
            
        }
    }
    
    public void addObject(GameObject object)
    {
        this.object.add(object);
    }
    
    public void addObject(Actor actor)
    {
        this.actor.add(actor);
        this.object.add(actor);
    }
    
    public void removeObject(GameObject object)
    {
        this.object.remove(object);
    }
    
    public double getDeltaTime()
    {
        return deltaTime;
    }

    public void setDeltaTime(double deltaTime)
    {
        this.deltaTime = deltaTime;
    }
    
    
}
