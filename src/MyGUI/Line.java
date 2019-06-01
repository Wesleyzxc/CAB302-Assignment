package MyGUI;

import java.awt.*;

/**
 *  * Child class of AllShapes for drawing lines
 */
public class Line extends AllShapes{

    private float heightRatio1, widthRatio1, heightRatio2, widthRatio2;

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
        this.widthRatio1 = x[0]/(float)pWidth;
        this.widthRatio2 = x[1]/(float)pWidth;
        this.heightRatio1 = y[0]/(float)pHeight;
        this.heightRatio2 = y[1]/(float)pHeight;

        // considers edge cases
        if (this.widthRatio1 >= 1){
            this.widthRatio1 = 0.999f;
        }
        if (this.widthRatio2 >= 1){
            this.widthRatio2 = 0.999f;
        }
        if (this.heightRatio1 >= 1){
            this.heightRatio1 = 0.999f;
        }
        if (this.heightRatio2 >= 1){
            this.heightRatio2 = 0.999f;
        }

    }

    /**
     * Draws line based on new size of panel
     * @param g Graphics g
     * @param currentWidth integer of new panel's width
     * @param currentHeight integer of new panel's height
     */
    public void draw(Graphics g, int currentWidth, int currentHeight){

        int x1 = (int)(widthRatio1 *currentWidth);
        int y1 = (int)(heightRatio1 *currentHeight);
        int x2 = (int)(widthRatio2 *currentWidth);
        int y2 = (int)(heightRatio2 *currentHeight);
        g.setColor(super.getColour());
        g.drawLine(x1, y1, x2, y2);
    }

    @Override
    public String getVEC(){
        return ((String.format("LINE %.2f %.2f %.2f %.2f", widthRatio1, heightRatio1, widthRatio2, heightRatio2)));
    }

    @Override
    String getShape() {
        return "Line";
    }

}
