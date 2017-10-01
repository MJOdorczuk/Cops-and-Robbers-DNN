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
    public static final double FIELD_WIDTH = 50;
    public static final double FIELD_HEIGHT = 50;
    // NoFH = Number of Fields in Horizontal direction.
    public static final short NOFH = 100;
    // NoFV = Number of Fields in Vertical direction.
    public static final short NOFV = 100;
    public static final double MARGIN_OF_BOUNDRY = 10;
    public static final double WIDTH_OF_BOUNDRY = Game.WINDOW_WIDTH;
    public static final double HEIGHT_OF_BOUNDRY = Game.WINDOW_HEIGHT;

    public World(int x, int y, ID id)
    {
        super(x, y, id);
    }

    @Override
    public void tick(double deltaTime)
    {
        
    }

    @Override
    public void render(Graphics graph, Vector2D local, Vector2D scale)
    {
        Vector2D position = new Vector2D(0,0);
        Vector2D down = new Vector2D(0,FIELD_HEIGHT).complexProduct(scale);
        Vector2D right = new Vector2D(FIELD_WIDTH,0).complexProduct(scale);
        int x1, x2, x3, x4, y1, y2, y3, y4;
        for(int row = 0; row < NOFV; row++)
            for(int col = 0; col < NOFH; col++)
            {
                if(row % 2 == 0 && col % 2 == 1 || row % 2 == 1 && col % 2 == 0)
                {
                    graph.setColor(Color.gray);
                }
                else
                {
                    graph.setColor(Color.black);
                }
                position.setVector(this.position.Sub(local).complexProduct(scale).Add(down.Multiply(row)).Add(right.Multiply(col)));
                x1 = (int) position.x;
                x2 = (int) position.Add(down).x;
                x3 = (int) position.Add(down).Add(right).x;
                x4 = (int) position.Add(right).x;
                y1 = (int) position.y;
                y2 = (int) position.Add(down).y;
                y3 = (int) position.Add(down).Add(right).y;
                y4 = (int) position.Add(right).y;
                graph.fillPolygon(new int[]{x1,x2,x3,x4}, new int[]{y1,y2,y3,y4}, 4);
            }
        graph.setColor(Color.red);
        position.setVector(this.position.Sub(local).Sub(new Vector2D(-1,-1)).complexProduct(scale));
        Vector2D farRight = new Vector2D(WIDTH_OF_BOUNDRY,0).complexProduct(scale);
        Vector2D farDown = new Vector2D(0,HEIGHT_OF_BOUNDRY).complexProduct(scale);
        down.setVector(new Vector2D(0,MARGIN_OF_BOUNDRY).complexProduct(scale));
        right.setVector(new Vector2D(MARGIN_OF_BOUNDRY,0).complexProduct(scale));
        x1 = (int) position.Sub(right).Sub(down).x;
        x2 = (int) position.Sub(right).Add(down).x;
        x3 = (int) position.Sub(right).Add(down).Add(farRight).x;
        x4 = (int) position.Sub(right).Sub(down).Add(farRight).x;
        y1 = (int) position.Sub(right).Sub(down).y;
        y2 = (int) position.Sub(right).Add(down).y;
        y3 = (int) position.Sub(right).Add(down).Add(farRight).y;
        y4 = (int) position.Sub(right).Sub(down).Add(farRight).y;
        graph.fillPolygon(new int[]{x1,x2,x3,x4}, new int[]{y1,y2,y3,y4}, 4);
        x1 = (int) position.Sub(right).Sub(down).x;
        x2 = (int) position.Add(right).Sub(down).x;
        x3 = (int) position.Add(right).Add(down).Add(farDown).x;
        x4 = (int) position.Sub(right).Add(down).Add(farDown).x;
        y1 = (int) position.Sub(right).Sub(down).y;
        y2 = (int) position.Add(right).Sub(down).y;
        y3 = (int) position.Add(right).Add(down).Add(farDown).y;
        y4 = (int) position.Sub(right).Add(down).Add(farDown).y;
        graph.fillPolygon(new int[]{x1,x2,x3,x4}, new int[]{y1,y2,y3,y4}, 4);
        x1 = (int) position.Sub(right).Sub(down).Add(farDown).x;
        x2 = (int) position.Sub(right).Add(down).Add(farDown).x;
        x3 = (int) position.Sub(right).Add(down).Add(farDown).Add(farRight).x;
        x4 = (int) position.Sub(right).Sub(down).Add(farDown).Add(farRight).x;
        y1 = (int) position.Sub(right).Sub(down).Add(farDown).y;
        y2 = (int) position.Sub(right).Add(down).Add(farDown).y;
        y3 = (int) position.Sub(right).Add(down).Add(farDown).Add(farRight).y;
        y4 = (int) position.Sub(right).Sub(down).Add(farDown).Add(farRight).y;
        graph.fillPolygon(new int[]{x1,x2,x3,x4}, new int[]{y1,y2,y3,y4}, 4);
        x1 = (int) position.Sub(right).Sub(down).Add(farRight).x;
        x2 = (int) position.Add(right).Sub(down).Add(farRight).x;
        x3 = (int) position.Add(right).Add(down).Add(farRight).Add(farDown).x;
        x4 = (int) position.Sub(right).Add(down).Add(farRight).Add(farDown).x;
        y1 = (int) position.Sub(right).Sub(down).Add(farRight).y;
        y2 = (int) position.Add(right).Sub(down).Add(farRight).y;
        y3 = (int) position.Add(right).Add(down).Add(farRight).Add(farDown).y;
        y4 = (int) position.Sub(right).Add(down).Add(farRight).Add(farDown).y;
        graph.fillPolygon(new int[]{x1,x2,x3,x4}, new int[]{y1,y2,y3,y4}, 4); //test
        double a = 0;
        a = a / a;
    }
    
}
