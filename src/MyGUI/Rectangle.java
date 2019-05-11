package MyGUI;

import java.awt.*;

public class Rectangle extends AllShapes{ //maybe subclass of line?
    private int x1,y1,x2,y2;
    private Color color;
    private Color fillColor;
    private boolean toggleFill;

    public Rectangle(int x1, int y1, int x2, int y2, Color color, Color fillColor, boolean toggleFill) {
        super(color);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.fillColor = fillColor;
        this.toggleFill = toggleFill;
    }

    public void draw(Graphics g){
        if (!toggleFill) {
            g.setColor(color);
            g.drawRect(x1, y1, x2 - x1, y2 - y1);
        } else {
            g.setColor(fillColor);
            g.fillRect(x1, y1, x2-x1, y2-y1);
            g.setColor(color);
            g.drawRect(x1, y1, x2 - x1, y2 - y1);

        }
    }

    @Override
    public String getVEC(){
        return ((String.format("RECTANGLE {0} {1} {2} {3}", x1, y1, x2, y2)));
    }
}






