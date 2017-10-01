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
 */
public class Collision {
    
    Part Collider1, Collider2;
    ArrayList <Vector2D> cp1 = new ArrayList <>();
    ArrayList <Vector2D> cp2 = new ArrayList <>();

    public Collision(Part Collider1, Part Collider2, Vector2D dif, Vector2D rot1, Vector2D rot2) {
        this.Collider1 = Collider1;
        this.Collider2 = Collider2;
        cp1 = Collider1.getCharacteristicPoints();
        cp2 = Collider2.getCharacteristicPoints();
        for(int i=0; i<cp1.size(); i++)
        {
            cp1.get(i).Add(Collider1.getmPoint()).complexProduct(rot1);
        }
        for(int i=0; i<cp2.size(); i++)
        {
            cp2.get(i).Add(Collider2.getmPoint()).complexProduct(rot2).Add(dif);
        }
            
        if(cp1.size() < cp2.size())
        {
            ArrayList <Vector2D> temp = cp1;
            cp1 = cp2;
            cp2 = temp;
            temp = null;
            Part tempP = Collider1;
            Collider1 = Collider2;
            Collider2 = tempP;
            tempP = null;
        }
    }
    
    public Vector2D ifCollide()
    {
        switch(cp1.size())
        {
            case 1: //circle with circle
            {
                double r1, r2;
                r1 = Collider1.getCharactericticValue();
                r2 = Collider2.getCharactericticValue();
                if(cp1.get(0).Sub(cp2.get(0)).getLength() <= r1 + r2) 
                    return cp1.get(0).Add(cp1.get(1)).Add(cp1.get(1).Sub(cp1.get(0)).getUnit().Multiply(r1)).Add(cp1.get(0).Sub(cp1.get(1)).getUnit().Multiply(r2)).Multiply(1/2);
                else return null;
            }
            case 2: //line with sth
            {
                double R = Collider2.getCharactericticValue();
                if(cp2.size() == 1) //line with circle
                {
                    if(cp1.get(0).Sub(cp2.get(0)).getLength() <= R) return cp1.get(0);
                    if(cp1.get(1).Sub(cp2.get(0)).getLength() <= R) return cp1.get(1);
                    Vector2D POC = getPointOnLine(cp1.get(0),cp1.get(1),cp2.get(0));
                    if(POC.isInside(cp1.get(0), cp1.get(1))) return POC;
                    else return null;
                }
                else //line with line
                {
                    Vector2D POC = whereCut(cp1.get(0), cp1.get(1), cp2.get(0), cp2.get(1));
                    if(POC.isInside(cp1.get(0), cp1.get(1)) && POC.isInside(cp2.get(0), cp2.get(1))) return POC;
                    else return null;
                }
            }
            case 3: // triangle with sth
            {
                switch(cp2.size())
                {
                    case 1: //triangle with circle
                    {
                        Vector2D mPoint = cp1.get(0).Add(cp1.get(1).Add(cp1.get(2))).Multiply(1/3);
                        double bubble = Collider1.getCharactericticValue();
                        double radius = Collider2.getCharactericticValue();
                        if(mPoint.Sub(cp2.get(0)).getLength() >= bubble + radius) return null;
                        else
                        {
                            Vector2D np = cp2.get(0).Add(mPoint.Sub(cp2.get(0)).getUnit().Multiply(radius));
                            if(ifInTriangle(cp1.get(0), cp1.get(1),cp1.get(2),np)) return np;
                            else return null;
                        }
                    }
                    case 2: //triangle with line
                    {
                        Vector2D POC1 = whereCut(cp1.get(0), cp1.get(1), cp2.get(0), cp2.get(1));
                        Vector2D POC2 = whereCut(cp1.get(2), cp1.get(1), cp2.get(0), cp2.get(1));
                        Vector2D POC3 = whereCut(cp1.get(2), cp1.get(0), cp2.get(0), cp2.get(1));
                        if(!POC1.isInside(cp1.get(0), cp1.get(1)) || !POC1.isInside(cp2.get(0), cp2.get(1))) POC1 = null;
                        if(!POC2.isInside(cp1.get(2), cp1.get(1)) || !POC2.isInside(cp2.get(0), cp2.get(1))) POC2 = null;
                        if(!POC3.isInside(cp1.get(0), cp1.get(2)) || !POC3.isInside(cp2.get(0), cp2.get(1))) POC3 = null;
                        return middlePoint(POC1,POC2,POC3);
                    }
                    case 3: // triangle with triangle
                    {
                        Vector2D[] POC = new Vector2D[9];
                        for(int i=0; i<3; i++)
                            for(int j=0; j<3; j++)
                            {
                                POC[i*3 + j] = whereCut(cp1.get(i), cp1.get((i+1)%3), cp2.get(j), cp2.get((j+1)%3));
                                if(!POC[i*3 + j].isInside(cp1.get(i), cp1.get((i+1)%3)) || !POC[i*3 + j].isInside(cp2.get(j), cp2.get((j+1)%3))) POC[i*3 + j] = null;
                                return middlePoint(POC);
                            }
                        
                    }
                    default:
                    {
                        return null;
                    }
                }
            }
            default:
            {
                return null;
            }
        }
    }
    
    public Vector2D whereCut(Vector2D p1, Vector2D p2, Vector2D p3, Vector2D p4)
    {
        ArrayList <Double> params1 = p1.getParams(p2);
        ArrayList <Double> params2 = p3.getParams(p4);
        double tmp = params1.get(0) * params2.get(1) - params2.get(0) * params1.get(1);
        if(tmp == 0) return null;
        else
        {
            double x, y;
            x = (params2.get(2) * params1.get(1) - params1.get(2) * params2.get(2)) / tmp;
            y = (- params1.get(0) * x - params1.get(2))/params1.get(1);
            return new Vector2D(x,y);
        }
        
    }
    
    public Vector2D getPointOnLine(Vector2D p1, Vector2D p2, Vector2D mPoint)
    {
        double A, B, C, xz;
        A = 1/0;
        System.out.println(A);
        A = (p1.y - p2.y)/(p2.x - p1.x);
        B = -p1.y - A * p1.x;
        C = mPoint.x/A - mPoint.y;
        xz = (C-B)/(A+1/A);
        return new Vector2D(xz ,-A*xz - B);
    }
    
    public boolean ifInTriangle(Vector2D p1, Vector2D p2, Vector2D p3, Vector2D p)
    {
        double alpha = ((p2.y - p3.y)*(p.x - p3.x) + (p3.x - p2.x)*(p.y - p3.y)) / ((p2.y - p3.y)*(p1.x - p3.x) + (p3.x - p2.x)*(p1.y - p3.y));
        double beta = ((p3.y - p1.y)*(p.x - p3.x) + (p1.x - p3.x)*(p.y - p3.y)) / ((p2.y - p3.y)*(p1.x - p3.x) + (p3.x - p2.x)*(p1.y - p3.y));
        double gamma = 1 - alpha - beta;
        if(alpha > 0 && beta > 0 && gamma > 0) return true;
        else return false;
    }
    
    public Vector2D middlePoint(Vector2D... points)
    {
        Vector2D mPoint = new Vector2D(0,0);
        short j = 0;
        for(int i = 0; i < points.length; i++)
        {
            if(points[i] != null)
            {
                mPoint.Add(points[i]);
                j++;
            }
        }
        if(j == 0) return null;
        else return mPoint.Multiply(1/((double)j));
    }

    public Part getCollider1() {
        return Collider1;
    }

    public void setCollider1(Part Collider1) {
        this.Collider1 = Collider1;
    }

    public Part getCollider2() {
        return Collider2;
    }

    public void setCollider2(Part Collider2) {
        this.Collider2 = Collider2;
    }
    
}
