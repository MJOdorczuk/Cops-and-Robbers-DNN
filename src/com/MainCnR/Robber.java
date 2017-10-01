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
 */
public class Robber extends Actor
{

    public Robber(int x, int y, ID id)
    {
        super(x, y, id);
    }

    @Override
    public void controller(double deltaTime)
    {
        controllerTimer += deltaTime;
        key[KeyInput.KEY_W] = true;
        if(controllerTimer > Math.random() * 10)
        {
            for(int i = 2; i < 4; i++)
            {
                if((int)(Math.random() * 100 % 2) == 1)
                {
                    key[i] = true;
                }
                else
                {
                    key[i] = false;
                }
            }
            controllerTimer = 0;
        }
    }

    @Override
    public void tick(double deltaTime)
    {
        controller(deltaTime);
        updatePhysics(deltaTime);
        if(position.x < 16) position.x = 16;
        //if(position.x > Game.WINDOW_WIDTH - 16) position.x = Game.WINDOW_WIDTH - 16;
        if(position.y < 16) position.y = 16;
        if(position.y > Game.WINDOW_HEIGHT - 16) position.y = Game.WINDOW_HEIGHT- 16;
    }
    
}
