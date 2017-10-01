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
public class Triangle extends Part{

    Vector2D p1, p2, p3;
    double bubble;
    public final static double MARGIN = 1.01;

    
    public Triangle(Vector2D p1, Vector2D p2, Vector2D p3, Color color, Short priority) {
        super(color, priority);
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        
        Vector2D middlePoint = p1.Add(p2.Add(p3)).Multiply(1/3);
        bubble = Math.max(middlePoint.Sub(p1).getLength(), Math.max(middlePoint.Sub(p2).getLength(), middlePoint.Sub(p3).getLength())) * MARGIN;
    }

    public Triangle(Vector2D p1, Vector2D p2, Vector2D p3, Color color) {
        super(color);
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        
        Vector2D middlePoint = p1.Add(p2.Add(p3)).Multiply(1/3);
        bubble = Math.max(middlePoint.Sub(p1).getLength(), Math.max(middlePoint.Sub(p2).getLength(), middlePoint.Sub(p3).getLength())) * MARGIN;
    }
    
    @Override
    public void render(Graphics graph, Vector2D anchorPoint, Vector2D rotation, Vector2D local, Vector2D scale) {
        graph.setColor(color);
        rotation = rotation.getUnit();
        int x1 = (int)p1.complexProduct(rotation).Add(anchorPoint).Sub(local).complexProduct(scale).x;
        int x2 = (int)p2.complexProduct(rotation).Add(anchorPoint).Sub(local).complexProduct(scale).x;
        int x3 = (int)p3.complexProduct(rotation).Add(anchorPoint).Sub(local).complexProduct(scale).x;
        int y1 = (int)p1.complexProduct(rotation).Add(anchorPoint).Sub(local).complexProduct(scale).y;
        int y2 = (int)p2.complexProduct(rotation).Add(anchorPoint).Sub(local).complexProduct(scale).y;
        int y3 = (int)p3.complexProduct(rotation).Add(anchorPoint).Sub(local).complexProduct(scale).y;
        graph.drawPolygon(new int[]{x1,x2,x3}, new int[]{y1,y2,y3}, 3);
    }

    @Override
    public double getCharactericticValue() {
        return bubble;
    }

    @Override
    public ArrayList<Vector2D> getCharacteristicPoints() {
        ArrayList <Vector2D> points = new ArrayList <>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        return points;
    }

    @Override
    public double getMoI() {
        double h, l, s, sin;
        h = p1.Sub(p2).getLength();
        l = p1.Sub(p3).getLength();
        s = p1.Add(p2).Multiply(1/2).Sub(p3).getLength();
        sin = Math.abs(p2.Sub(p1).getUnit().crossProduct(p3.Sub(p1).getUnit()));
        return h*sin*(h*h*l/16 + s*s/9);
    }

    @Override
    public Vector2D getFurthestPoint(Vector2D point) {
        if(point.Sub(p1).getLength() > point.Sub(p2).getLength())
        {
            if(point.Sub(p1).getLength() > point.Sub(p3).getLength()) return p1;
            else return p3;
        }
        else if(point.Sub(p2).getLength() > point.Sub(p3).getLength()) return p2;
        else return p3;
    }

    @Override
    public double getField() {
        return Math.abs(p2.Sub(p1).crossProduct(p3.Sub(p1)))/2;
    }
    
}
