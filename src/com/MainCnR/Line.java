/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MainCnR;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author MJOdorczuk
 */
public class Line extends Part{

    Vector2D p1, p2;

    public Line(Vector2D p1, Vector2D p2, Color color, Short priority) {
        super(color, priority);
        this.p1 = p1;
        this.p2 = p2;
    }

    public Line(Vector2D p1, Vector2D p2, Color color) {
        super(color);
        this.p1 = p1;
        this.p2 = p2;
    }

    public Line(Line newPart) {
        super(newPart);
        this.p1 = newPart.p1;
        this.p2 = newPart.p2;
    }
    
    @Override
    public void render(Graphics graph, Vector2D anchorPoint, Vector2D rotation, Vector2D local, Vector2D scale) {
        graph.setColor(color);
        rotation = rotation.getUnit();
        int x1 = (int)p1.complexProduct(rotation).Add(anchorPoint).Sub(local).complexProduct(scale).x;
        int x2 = (int)p2.complexProduct(rotation).Add(anchorPoint).Sub(local).complexProduct(scale).x;
        int y1 = (int)p1.complexProduct(rotation).Add(anchorPoint).Sub(local).complexProduct(scale).y;
        int y2 = (int)p2.complexProduct(rotation).Add(anchorPoint).Sub(local).complexProduct(scale).y;
        graph.drawLine(x1, y1, x2, y2);
    }

    @Override
    public double getCharactericticValue() {
        return 0;
    }

    @Override
    public ArrayList<Vector2D> getCharacteristicPoints() {
        ArrayList<Vector2D> ret = new ArrayList<>();
        ret.add(p1);
        ret.add(p2);
        return ret;
    }

    @Override
    public double getMoI() {
        return 0;
    }

    @Override
    public Vector2D getFurthestPoint(Vector2D point) {
        if(point.Sub(p1).getLength() > point.Sub(p2).getLength()) return p1;
        else return p2;
    }

    @Override
    public double getField() {
        return 0;
    }

    @Override
    public Vector2D getmPoint() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addPosition(Vector2D shift) {
        p1 = p1.Add(shift);
        p2 = p2.Add(shift);
    }

    @Override
    public void rotate(Vector2D rotation) {
        p1 = p1.complexProduct(rotation);
        p2 = p2.complexProduct(rotation);
    }

    @Override
    public Part getClone() {
        return new Line(this);
    }
    
    
}
