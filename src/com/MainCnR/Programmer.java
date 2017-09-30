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
public class Programmer extends Actor
{
    private KeyInput keyInput;
    public Programmer(int x, int y, ID id, KeyInput input)
    {
        super(x, y, id);
        this.keyInput = input;
    }

    @Override
    public void controller(double deltaTime)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tick(double deltaTime)
    {
        setKey(keyInput.getKeys());
        updatePhysics(deltaTime);
    }

    @Override
    public void render(Graphics graph, Vector2D local)
    {
        drawSight(graph, local);
        graph.setColor(Color.white);
        graph.fillOval((int)(position.x - local.x - 16), (int)(position.y - local.y - 16), 32, 32);
        graph.setColor(Color.gray);
        graph.fillOval((int)(position.x - local.x - 12), (int)(position.y - local.y - 12), 24, 24);
        graph.setColor(Color.darkGray);
        graph.fillOval((int)(position.x - local.x - 8), (int)(position.y - local.y - 8), 16, 16);
        graph.setColor(Color.black);
        graph.fillOval((int)(position.x - local.x - 4), (int)(position.y - local.y - 4), 8, 8);
    }

    public void setKey(boolean[] key)
    {
        this.key = key;
    }
    
}
