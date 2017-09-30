/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MainCnR;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

/**
 *
 * @author Mateusz Śliwiński
 */
public class Game extends Canvas implements Runnable
{
    private static final long serialVersionUID = 4382329884180375436L;
    
    public static final int WINDOW_WIDTH = 960;
    public static final int WINDOW_HEIGHT = WINDOW_WIDTH * 9 /16;
    
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private int fps;
    
    public Game()
    {
        KeyInput keyInput = new KeyInput();
        this.addKeyListener(keyInput);
        handler = new Handler(keyInput);
        new Window(WINDOW_WIDTH, WINDOW_HEIGHT, "Cops and Robbers DNN", this);
        handler.addObject(new World(0, 0, ID.World));
        for(int i = 0; i < 10; i++)
        {
            handler.addObject(new Cop(
                    (int)(Math.random() * WINDOW_WIDTH),
                    (int)(Math.random() * WINDOW_HEIGHT),
                    ID.Cop));
            handler.addObject(new Robber(
                    (int)(Math.random() * WINDOW_WIDTH),
                    (int)(Math.random() * WINDOW_HEIGHT),
                    ID.Robber));
        }
        handler.addObject(new Programmer(100, 100, ID.Programmer, keyInput));
    }
    
    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    
    public synchronized void stop()
    {
        try
        {
            thread.join();
            running = false;
        }catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }
   
    @Override
    public void run()
    {
        long lastTime = System.nanoTime();
        final double AMOUNT_OF_TICKS = 60.0;
        double ns = 1000000000 / AMOUNT_OF_TICKS;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running)
        {
                    long now = System.nanoTime();
                    delta += (now - lastTime) / ns;
                    handler.setDeltaTime(delta / AMOUNT_OF_TICKS);
                    lastTime = now;
                    while(delta >=1)
                            {
                                tick();
                                delta--;
                            }
                            if(running)
                                render();
                            frames++;
                            
                            if(System.currentTimeMillis() - timer > 1000)
                            {
                                timer += 1000;
                                fps = frames;
                                frames = 0;
                            }
        }
        stop();
    }
    
    private void tick()
    {
        handler.tick();
    }

    private void render()
    {
        BufferStrategy bufferStrat = this.getBufferStrategy();
        if(bufferStrat == null)
        {
            this.createBufferStrategy(3);
            return;
        }
        Graphics graph = bufferStrat.getDrawGraphics();
        graph.setColor(Color.black);
        graph.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        
        handler.render(graph);
        graph.setColor(Color.white);
        String fpsString = "FPS: " + Integer.toString(fps);
        graph.drawString(fpsString, WINDOW_WIDTH - 65, 10);
        graph.dispose();
        bufferStrat.show();
    }
    
    public static void main(String[] args)
    {
        Game game = new Game();
    }
}
