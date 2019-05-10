package MyGUI;

import javax.swing.*;
import java.awt.*;

public class PolygonShape implements AllShapes{
    private int[] x,y;
    private Color color;
    private Color fillColor;
    private boolean toggleFill;

    public PolygonShape(int[] x, int[] y, Color color, Color fillColor, boolean toggleFill) {
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
}
