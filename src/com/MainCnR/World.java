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
public class World extends GameObject
{

    public World(int x, int y, ID id)
    {
        super(x, y, id);
    }

    @Override
    public void tick(double deltaTime)
    {
        
    }

    @Override
    public void render(Graphics graph, Vector2D local)
    {
        graph.setColor(Color.red);
        graph.fillRect(
                (int)(position.x - local.x - 20),
                (int)(position.y - local.y - 20),
                (int)Game.WINDOW_WIDTH + 20,
                (int)Game.WINDOW_HEIGHT + 20);
        for(int row = 0; row < 100; row++)
            for(int col = 0; col < 100; col++)
            {
                if(row % 2 == 0 && col % 2 == 1 || row % 2 == 1 && col % 2 == 0)
                {
                    graph.setColor(Color.gray);
                }
                else
                {
                    graph.setColor(Color.black);
                }
                graph.fillRect(
                        (int)(position.x + 50 * col - local.x),
                        (int)(position.y + 50 * row - local.y),
                        (int)50,
                        (int)50);
            }
    }
    
}
