package MyGUI;

import java.awt.*;

public class PolygonShape {
    Polygon polygon = new Polygon();
    Color c;

    public PolygonShape(int[] x, int[] y, Color c)
    {
        polygon.xpoints = x;
        polygon.ypoints = y;
        polygon.npoints = x.length;
        this.c = c;
    }

    void drawPolygon(Graphics graphics){
        graphics.setColor(c);
    }
}
