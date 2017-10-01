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
    
    // Defeualt Window size
    public static final int WINDOW_WIDTH = 960;
    public static final int WINDOW_HEIGHT = WINDOW_WIDTH * 9 /16;   // 16:9 ratio
    
    private Thread thread;
    private boolean running = false;    // Is game clock running
    private Handler handler;    // Updates and renders
    private int fps;    // Frames per Sec - variable
    
    public Game()
    {
        KeyInput keyInput = new KeyInput();
        this.addKeyListener(keyInput);
        handler = new Handler(keyInput);    //  new Handler instance for render and updates
        new Window(WINDOW_WIDTH, WINDOW_HEIGHT, "Cops and Robbers DNN", this);  // Create new Window(no instance)
        
        /*
        ADD STARTING OBJECTS HERE
        */
        // Map / World
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
        handler.addObject(new Robber(
                    (int)(200),
                    (int)(200),
                    ID.Robber));
        handler.addObject(new Programmer(100, 100, ID.Programmer, keyInput));
        //Other
        
    }
    
    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start(); // Starting one new thread for the program
        running = true; // Game clock starts running
    }
    
    public synchronized void stop()
    {
        try
        {
            thread.join();  // Joining the thread back to others
            running = false;    // Game clock stops running
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
        double ns = 1000000000 / AMOUNT_OF_TICKS;   // 1 sec per default amount of ticks
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running)  // To stop the game clock set boolean : running = false
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
    
    private void tick() // Executes (default amount of ticks) times per second
    {
        handler.tick();
    }

    private void render()   // Executes maximum times per second
    {
        // Initializing buffer strategy
        BufferStrategy bufferStrat = this.getBufferStrategy();
        if(bufferStrat == null)
        {
            this.createBufferStrategy(3);
            return;
        }
        Graphics graph = bufferStrat.getDrawGraphics();
        
        graph.setColor(Color.black);
        graph.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);  // Draw Window-size filled rectangle
        
        handler.render(graph);  // Render objects
        graph.setColor(Color.white);
        String fpsString = "FPS: " + Integer.toString(fps);
        graph.drawString(fpsString, WINDOW_WIDTH - 65, 10); // Draw FPS insert GUI HERE
        
        // Display on screen
        graph.dispose();
        bufferStrat.show();
    }
    
    public static void main(String[] args)
    {
        Game game = new Game(); // Creating new Game object(no instance needed)
    }
}
