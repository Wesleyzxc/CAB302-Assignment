package MyGUI;

import java.awt.*;
import java.text.MessageFormat;

public class Line extends AllShapes{
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private Color color;

    public Line(int x1, int y1, int x2, int y2, Color color) {
        super(color);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }

    public void draw(Graphics g){
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
    }

    @Override
    public Color getColour() {
        return color;
    }

    @Override
    public String getVEC(){
        return (String.format("LINE " + x1 + " " + y1 + " " + x2 + " " + y2));
    }

}
