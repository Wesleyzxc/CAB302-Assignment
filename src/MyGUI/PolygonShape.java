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

    public PolygonShape(int[] x, int[] y, Color color, Color fillColor, boolean toggleFill) {
        super(color);
        this.x = x;
        this.y = y;
        this.color = color;
        this.fillColor = fillColor;
        this.toggleFill = toggleFill;
    }

    public void draw(Graphics g) {
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
        int maxX = maxValue(x);
        int maxY = maxValue(y);
        double largest = max(maxX,maxY);
        for (int i = 0; i < x.length; i++) {
            System.out.println(y[i]);
            S = S.concat(" " + x[i] + " " + y[i]);
        }
        System.out.println(S);
        return (S);
    }
}

