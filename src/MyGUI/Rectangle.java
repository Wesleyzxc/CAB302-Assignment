package MyGUI;

import java.awt.*;

public class Rectangle extends Line{ //maybe subclass of line?
    private int x1,y1,x2,y2,x3,y3,x4,y4;
    private Color color;

    public Rectangle(int x1, int y1, int x2, int y2, Color color) {
        super(x1,y1,x2,y2, color);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x2;
        this.y3 = y1;
        this.x4 = x1;
        this.y4 = y2;
        this.color = color;
    }

    public void draw(Graphics g){
        g.setColor(color);
        g.drawLine(x1, y1, x1, y2); //p1 p4
        g.drawLine(x1, y1, x2, y1); //p1 p3
        g.drawLine(x2, y2, x2, y1); //p2 p3
        g.drawLine(x2, y2, x1, y2); //p2 p4
    }

  /*  public int getX1() { return x1; }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    } */

    public int getX3() {
        return x3;
    }

    public int getY3() {
        return y3;
    }

    public int getX4() {
        return x4;
    }

    public int getY4() {
        return y4;
    }
/*
    public Color getColor() {
        return color;
    } */
}
