package MyGUI;

import javax.swing.*;
import java.awt.*;
import java.lang.*;
import java.util.Arrays;
import java.util.Collections;

import static java.lang.Math.max;

public class PolygonShape extends AllShapes{
    private int[] x,y;
    private Color color;
    private Color fillColor;
    private boolean toggleFill;
    private float[] hratio;
    private float[] wratio;
    public PolygonShape(int[] x, int[] y, Color color, Color fillColor, boolean toggleFillz, int pwidth, int pheight) {
        super(color,fillColor,pwidth,pheight);
        this.x = x;
        this.y = y;
        this.color = color;
        this.fillColor = fillColor;
        this.toggleFill = toggleFill;
        wratio = new float[x.length];
        hratio = new float[y.length];
        int xcount = 0;
        int ycount = 0;
        for (int x2:x){
            wratio[xcount] = x2/(float)pwidth;
            xcount++;
        }
        for (int y2:y){
            hratio[ycount] = y2/(float)pheight;
            ycount++;
        }
    }

    public float[] getRatioW(){
        return wratio;
    }

    public float[] getRatioH(){
        return hratio;
    }

    public void draw(Graphics g, int currentWidth, int currentHeight) {
        if (!toggleFill) {
            g.setColor(color);
            g.drawPolygon(x, y, x.length);
        } else {
            g.setColor(fillColor);
            g.fillPolygon(x, y, x.length);
            g.setColor(color);
            g.drawPolygon(x, y, x.length);
        }
    }

    @Override
    public Color getColour() {
        return color;
    }

    public int maxValue(int array[]){
        int max = Arrays.stream(array).max().getAsInt();
        return max;
    }

    @Override
    public String getVEC(){
        String S = "POLYGON";
        //int maxX = maxValue(x);
        //int maxY = maxValue(y);
        //double largest = max(maxX,maxY);
        for (int i = 0; i < x.length; i++) {
            S = S.concat(" " + x[i] + " " + y[i]);
        }
        return (S);
    }
}

