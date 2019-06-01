package MyGUI;

import java.awt.*;

public class Rectangle extends AllShapes {

    private boolean toggleFill;
    private float heightRatio1, widthRatio1, heightRatio2, widthRatio2;

    /**
     * Initialises a Rectangle class that saves the ratio of rectangle on canvas and scales according
     * @param x array which stores all x coordinates
     * @param y array which stores all y coordinates
     * @param colour Color class which sets pen colour
     * @param fillColour Color class which sets fill colour
     * @param toggleFill boolean to decide whether shape is filled in
     * @param pWidth integer of panel's width when object is instantiated
     * @param pHeight integer of panel's height when object is instantiated
     */
    Rectangle(int[] x, int[] y, Color colour, Color fillColour, boolean toggleFill, int pWidth, int pHeight) {
        super(x, y, colour, fillColour);
        this.toggleFill = toggleFill;

        // flips array if drawing towards up and left directions
        if (x[0] > x[1]) {
            int[] replaced = {x[1], x[0]};
            super.setX(replaced);
        }
        if (y[0] > y[1]) {
            int[] replaced = {y[1], y[0]};
            super.setY(replaced);

        }
        // calculates ratio of the drawing based on current dimensions
        this.widthRatio1 = super.getX()[0]/(float)pWidth;
        this.widthRatio2 = super.getX()[1]/(float)pWidth;
        this.heightRatio1 = super.getY()[0]/(float)pHeight;
        this.heightRatio2 = super.getY()[1]/(float)pHeight;

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
     * Draws the rectangle based on the new size of panel
     * @param g Graphics g
     * @param currentWidth integer of new panel's width
     * @param currentHeight integer of new panel's height
     */
    public void draw(Graphics g, int currentWidth, int currentHeight) {
        int x = (int)(widthRatio1 *currentWidth);
        int y = (int)(heightRatio1*currentHeight);
        int width = (int)(widthRatio2 *currentWidth) - (int)(widthRatio1 *currentWidth);
        int height = (int)(heightRatio2 *currentHeight) - (int)(heightRatio1*currentHeight);

        // fill first if needed, then draw to prevent overlap edges
        if (toggleFill) {
            g.setColor(super.getFillColour());
            g.fillRect(x, y, width, height);
        }
        g.setColor(super.getColour());
        g.drawRect(x, y, width, height);


    }

    @Override
    public String getVEC() {
        //String hex = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
        return ((String.format("RECTANGLE %.2f %.2f %.2f %.2f", widthRatio1, heightRatio1, widthRatio2, heightRatio2)));
    }

    @Override
    String getShape() {
        return "Rectangle";
    }

}






