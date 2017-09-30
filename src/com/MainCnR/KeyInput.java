/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MainCnR;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Mateusz Śliwiński
 */
public class KeyInput extends KeyAdapter
{      
    private boolean[] key;
    public static final short KEY_W = 0;
    public static final short KEY_S = 1;
    public static final short KEY_A = 2;
    public static final short KEY_D = 3;
    public static final short KEY_UP = 4;
    public static final short KEY_DOWN = 5;
    public static final short KEY_LEFT = 6;
    public static final short KEY_RIGHT = 7;
    public static final short KEY_SPACE = 8;
    public static final short KEY_ENTER = 9;
    public static final short KEY_ESCAPE = 10;
    public static final short KEY_P = 11;
    public static final short KEY_R = 12;
    public static final short KEY_Q = 13;
    public static final short KEY_E = 14;

    public KeyInput()
    {
        key = new boolean[15];
    }
    
    @Override
    public void keyPressed(KeyEvent ev)
    {
        switch(ev.getKeyCode())
        {
            case KeyEvent.VK_W:
                key[KEY_W] = true;
                break;
            case KeyEvent.VK_S:
                key[KEY_S] = true;
                break;  
            case KeyEvent.VK_A:
                key[KEY_A] = true;
                break;
            case KeyEvent.VK_D:
                key[KEY_D] = true;
                break;
            case KeyEvent.VK_UP:
                key[KEY_UP] = true;
                break;
            case KeyEvent.VK_DOWN:
                key[KEY_DOWN] = true;
                break;
            case KeyEvent.VK_LEFT:
                key[KEY_LEFT] = true;
                break;
            case KeyEvent.VK_RIGHT:
                key[KEY_RIGHT] = true;
                break;
            case KeyEvent.VK_SPACE:
                key[KEY_SPACE] = true;
                break;
            case KeyEvent.VK_ENTER:
                key[KEY_ENTER] = true;
                break;
            case KeyEvent.VK_ESCAPE:
                key[KEY_ESCAPE] = true;
                break;
            case KeyEvent.VK_P:
                key[KEY_P] = true;
                break;
            case KeyEvent.VK_R:
                key[KEY_R] = true;
                break;
            case KeyEvent.VK_Q:
                key[KEY_Q] = true;
                break;
            case KeyEvent.VK_E:
                key[KEY_E] = true;
                break;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent ev)
    {
        switch(ev.getKeyCode())
        {
            case KeyEvent.VK_W:
                key[KEY_W] = false;
                break;
            case KeyEvent.VK_S:
                key[KEY_S] = false;
                break;  
            case KeyEvent.VK_A:
                key[KEY_A] = false;
                break;
            case KeyEvent.VK_D:
                key[KEY_D] = false;
                break;
            case KeyEvent.VK_UP:
                key[KEY_UP] = false;
                break;
            case KeyEvent.VK_DOWN:
                key[KEY_DOWN] = false;
                break;
            case KeyEvent.VK_LEFT:
                key[KEY_LEFT] = false;
                break;
            case KeyEvent.VK_RIGHT:
                key[KEY_RIGHT] = false;
                break;
            case KeyEvent.VK_SPACE:
                key[KEY_SPACE] = false;
                break;
            case KeyEvent.VK_ENTER:
                key[KEY_ENTER] = false;
                break;
            case KeyEvent.VK_ESCAPE:
                key[KEY_ESCAPE] = false;
                break;
            case KeyEvent.VK_P:
                key[KEY_P] = false;
                break;
            case KeyEvent.VK_R:
                key[KEY_R] = false;
                break;
            case KeyEvent.VK_Q:
                key[KEY_Q] = false;
                break;
            case KeyEvent.VK_E:
                key[KEY_E] = false;
                break;
        }
    }

    public boolean[] getKeys()
    {
        return key;
    }
}
