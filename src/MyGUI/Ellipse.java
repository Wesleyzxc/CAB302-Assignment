package MyGUI;

import java.awt.*;

public class Ellipse extends AllShapes{ //maybe subclass of line?
    private int x1,y1,x2,y2;
    private Color color;
    private Color fillColor;
    private boolean toggleFill = false;
    private float hratio;
    private float wratio;

    public Ellipse(int x1, int y1, int x2, int y2, Color color, Color fillColor, boolean toggleFill, int pwidth, int pheight) {
        super(color, fillColor,pwidth,pheight);
        if (x1 < x2) {
            this.x1 = x1;
            this.x2 = x2;
        } else {
            this.x1 = x2;
            this.x2 = x1;
        }
        if (y1 < y2) {
            this.y1 = y1;
            this.y2 = y2;
        } else {
            this.y1 = y2;
            this.y2 = y1;
        }
        this.color = color;
        this.fillColor = fillColor;
        this.toggleFill = toggleFill;
        hratio = y2/(float)pheight;
        wratio = x2/(float)pwidth;
    }

    public float getRatioW(){
        return wratio;
    }

    public float getRatioH(){
        return hratio;
    }
    public void draw(Graphics g, int currentWidth, int currentHeight){
        if (!toggleFill) {
            g.setColor(color);
            g.drawOval(x1, y1, x2 - x1, y2 - y1);
        } else {
            g.setColor(fillColor);
            g.fillOval(x1, y1, x2-x1, y2-y1);
            g.setColor(color);
            g.drawOval(x1, y1, x2 - x1, y2 - y1);
        }
    }

    public String getVEC(){
        return String.format("ELLIPSE %.2f %.2f %.2f %.2f",x1/screenWidth,y1/screenHeight,x2/screenWidth,y2/screenHeight);
    }
}
