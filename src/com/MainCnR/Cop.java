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
public class Cop extends Actor
{

    private double destRotation;
    
    public Cop(int x, int y, ID id)
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
        animationTime += deltaTime;
        if(animationTime >= 0.3)
        {
            animationTime = -0.3;
        }
        if(position.x < 16) position.x = 16;
        if(position.x > Game.WINDOW_WIDTH - 16) position.x = Game.WINDOW_WIDTH - 16;
        if(position.y < 16) position.y = 16;
        if(position.y > Game.WINDOW_HEIGHT - 16) position.y = Game.WINDOW_HEIGHT- 16;
    }
    
    @Override
    public void render(Graphics graph, Vector2D local, Vector2D scale)
    {
        double outRad = 16 * scale.getLength();
        double inRad = 12 * scale.getLength();
        int x1 = (int)(position.Sub(local).complexProduct(scale).x - outRad);
        int y1 = (int)(position.Sub(local).complexProduct(scale).y - outRad);
        int x2 = (int)(position.Sub(local).complexProduct(scale).x - inRad);
        int y2 = (int)(position.Sub(local).complexProduct(scale).y - inRad);
        drawSight(graph, local, scale);
        graph.setColor(Color.white);
        graph.fillOval(x1, y1, (int)(outRad * 2), (int)(outRad * 2));
        if(animationTime < 0)
            graph.setColor(Color.red);
        else
            graph.setColor(Color.blue);
        graph.fillOval(x2, y2, (int)(inRad * 2), (int)(inRad * 2));
        model.render(graph, local, scale);
    }
}
