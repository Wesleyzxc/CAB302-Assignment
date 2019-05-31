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

        // calculates ratio of the drawing based on current dimensions
        this.wratio1 = x[0]/(float)pWidth;
        this.wratio2 = x[1]/(float)pWidth;
        this.hratio1 = y[0]/(float)pHeight;
        this.hratio2 = y[1]/(float)pHeight;

        // considers edge cases
        if (this.wratio1 >= 1){
            this.wratio1 = 0.999f;
        }
        if (this.wratio2 >= 1){
            this.wratio2 = 0.999f;
        }
        if (this.hratio1 >= 1){
            this.hratio1 = 0.999f;
        }
        if (this.hratio2 >= 1){
            this.hratio2 = 0.999f;
        }

    }

    /**
     * Draws line based on new size of panel
     * @param g Graphics g
     * @param currentWidth integer of new panel's width
     * @param currentHeight integer of new panel's height
     */
    public void draw(Graphics g, int currentWidth, int currentHeight){

        int x1 = (int)(wratio1*currentWidth);
        int y1 = (int)(hratio1*currentHeight);
        int x2 = (int)(wratio2*currentWidth);
        int y2 = (int)(hratio2*currentHeight);
        g.setColor(super.getColour());
        g.drawLine(x1, y1, x2, y2);
    }

    @Override
    public String getVEC(){
        return ((String.format("LINE %.2f %.2f %.2f %.2f", wratio1, hratio1, wratio2, hratio2)));
    }

    @Override
    String getShape() {
        return "Line";
    }

}
