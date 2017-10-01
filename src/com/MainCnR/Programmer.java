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
    public void render(Graphics graph, Vector2D local, Vector2D scale)
    {
        double outRad = 16 * scale.getLength();
        double radDif = 4 * scale.getLength();
        int x, y;
        drawSight(graph, local, scale);
        graph.setColor(Color.white);
        x = (int)(position.Sub(local).complexProduct(scale).x - outRad);
        y = (int)(position.Sub(local).complexProduct(scale).y - outRad);
        graph.fillOval(x, y, (int)(2*outRad), (int)(2*outRad));
        graph.setColor(Color.gray);
        outRad -= radDif;
        x = (int)(position.Sub(local).complexProduct(scale).x - outRad);
        y = (int)(position.Sub(local).complexProduct(scale).y - outRad);
        graph.fillOval(x, y, (int)(2*outRad), (int)(2*outRad));
        graph.setColor(Color.darkGray);
        outRad -= radDif;
        x = (int)(position.Sub(local).complexProduct(scale).x - outRad);
        y = (int)(position.Sub(local).complexProduct(scale).y - outRad);
        graph.fillOval(x, y, (int)(2*outRad), (int)(2*outRad));
        graph.setColor(Color.black);
        outRad -= radDif;
        x = (int)(position.Sub(local).complexProduct(scale).x - outRad);
        y = (int)(position.Sub(local).complexProduct(scale).y - outRad);
        graph.fillOval(x, y, (int)(2*outRad), (int)(2*outRad));
        model.render(graph, local, scale);
    }

    public void setKey(boolean[] key)
    {
        this.key = key;
    }
    
}
