package MyGUI;

import javax.swing.*;
import java.awt.*;
import java.lang.*;
import java.util.Arrays;
import java.util.Collections;

import static java.lang.Math.max;

public class PolygonShape extends AllShapes{

    private float[] xRatio, yRatio;
    private int[] xNew, yNew;
    private boolean toggleFill;

    PolygonShape(int[] x, int[] y, Color color, Color fillColor, boolean toggleFill, int pwidth, int pheight) {
        super(x, y, color,fillColor);
        this.toggleFill = toggleFill;
        xRatio = new float[x.length];
        yRatio = new float[y.length];
        for (int i = 0; i < x.length; i++){
            xRatio[i] = x[i] / (float)pwidth;
            yRatio[i] = y[i] / (float)pheight;
        }
    }

    public void draw(Graphics g, int currentWidth, int currentHeight) {
        xNew = new int[super.getX().length];
        yNew = new int[super.getX().length];
        for (int i = 0; i < super.getX().length; i++){
            xNew[i] = (int)(xRatio[i] * currentWidth);
            yNew[i] = (int)(yRatio[i] * currentHeight);
        }
        if (!toggleFill) {
            g.setColor(super.getColour());
            g.drawPolygon(xNew, yNew, super.getX().length);
        } else {
            g.setColor(super.getFillColour());
            g.fillPolygon(xNew, yNew, super.getX().length);
            g.setColor(super.getColour());
            g.drawPolygon(xNew, yNew, super.getX().length);
        }
    }

    @Override
    public String getVEC(){
        String S = "POLYGON";
        //int maxX = maxValue(x);
        //int maxY = maxValue(y);
        //double largest = max(maxX,maxY);
        for (int i = 0; i < super.getX().length; i++) {
            S = S.concat(" " +  String.format("%.2f",xRatio[i]) + " " +  String.format("%.2f",yRatio[i]));
        }
        return (S);
    }

    @Override
    String getShape() {
        return "Polygon";
    }
}