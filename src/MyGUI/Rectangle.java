package MyGUI;

import java.awt.*;

public class Rectangle extends Line{ //maybe subclass of line?
    private int x1,y1,x2,y2;
    private Color color;

    public Rectangle(int x1, int y1, int x2, int y2, Color color) {
        super(x1,y1,x2,y2, color);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }

    public void draw(Graphics g){
        g.setColor(color);
        g.drawRect(x1, y1, x2-x1, y2-y1);
    }

}
