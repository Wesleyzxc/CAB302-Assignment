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
    // get pen and fill colour, coords, DONT SET V HERE.
    @Override
    public String getVEC(){
        String hex = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
        return ((String.format("PEN %s LINE %.2f %.2f %.2f %.2f", hex, x1/screenWidth, y1/screenHeight, x2/screenWidth, y2/screenHeight)));
    }

}
