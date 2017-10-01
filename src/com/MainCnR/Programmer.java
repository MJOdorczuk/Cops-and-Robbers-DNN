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

    public void setKey(boolean[] key)
    {
        this.key = key;
    }
    
}
