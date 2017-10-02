/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MainCnR;

import java.util.ArrayList;

/**
 *
 * @author MJOdorczuk
 * LPC = Little Polygone Collider
 * Despite its name it can collide also circles.
 */
public class LPC {
    private final Assembly Collider1, Collider2;
    private static final double MARGIN = 0.00001;

    public LPC(Assembly Collider1, Assembly Collider2) {
        this.Collider1 = Collider1;
        this.Collider2 = Collider2;
    }
    
    private Vector2D getProjection(Vector2D p1, Vector2D p2, Vector2D projected)
    {
        double A, B, C, xz;
        A = 1/0;
        System.out.println(A);
        A = (p1.y - p2.y)/(p2.x - p1.x);
        B = -p1.y - A * p1.x;
        C = projected.x/A - projected.y;
        xz = (C-B)/(A+1/A);
        return new Vector2D(xz ,-A*xz - B);
    }
    
    private boolean isInside(Vector2D p1, Vector2D p2, Vector2D p3, Vector2D cp)
    {
        double s1, s2, s3, S;
        s1 = Math.abs(p1.Sub(p2).crossProduct(cp.Sub(p1)));
        s2 = Math.abs(p2.Sub(p3).crossProduct(cp.Sub(p2)));
        s3 = Math.abs(p3.Sub(p1).crossProduct(cp.Sub(p3)));
        S = Math.abs(p1.Sub(p2).crossProduct(p3.Sub(p1)));
        return s1 + s2 + s3 - MARGIN <= S;
    }
}
