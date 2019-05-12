package MyGUI;

import java.awt.*;

public class Dot extends AllShapes {
    private int x;
    private int y;
    private int diameter;
    private Color colour;

    public void setColour(Color color) {
        this.colour = color;
    }



    public Dot(int x, int y, int diameter, Color color) {
        super(color, color);
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.colour = color;
    }

    public void draw(Graphics g){
        g.setColor(colour);
        g.fillOval(x, y, diameter, diameter);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDiameter() {
        return diameter;
    }

    @Override
    public Color getColour() {
        return colour;
    }

    @Override
    public String getVEC(){
        return ((String.format("PLOT %.2f %.2f",x/screenWidth,y/screenHeight)));
    }
}
