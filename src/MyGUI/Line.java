package MyGUI;

import java.awt.*;
import java.text.MessageFormat;

public class Line extends AllShapes{

    private float hratio1, wratio1, hratio2, wratio2;

    /**
     * Initialises a Line class that saves the ratio of Line on canvas and scales according
     * @param x array which stores all x coordinates
     * @param y array which stores all y coordinates
     * @param color Color class which sets pen colour
     * @param pWidth integer of panel's width when object is instantiated
     * @param pHeight integer of panel's height when object is instantiated
     */
    Line(int[] x, int[] y, Color color, int pWidth, int pHeight) {
        super(x, y, color, color);
        this.wratio1 = x[0]/(float)pWidth;
        this.wratio2 = x[1]/(float)pWidth;
        this.hratio1 = y[0]/(float)pHeight;
        this.hratio2 = y[1]/(float)pHeight;

    }

    /**
     * Draws line based on new size of panel
     * @param g Graphics g
     * @param currentWidth integer of new panel's width
     * @param currentHeight integer of new panel's height
     */
    public void draw(Graphics g, int currentWidth, int currentHeight){
        g.setColor(super.getColour());
//        g.drawLine(super.getX()[0], super.getY()[0], super.getX()[1], super.getY()[1]);
        g.drawLine((int)(wratio1*currentWidth), (int)(hratio1*currentHeight), (int)(wratio2*currentWidth), (int)(hratio2*currentHeight));
    }

    @Override
    public String getVEC(){
        //String hex = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
        return ((String.format("LINE %.2f %.2f %.2f %.2f", wratio1, hratio1, wratio2, hratio2)));
    }

    @Override
    String getShape() {
        return "Line";
    }

}
