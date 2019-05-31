package MyGUI;

import java.awt.*;

public class Ellipse extends AllShapes{

    private boolean toggleFill;
    private float hratio1, wratio1, hratio2, wratio2;

    /**
     * Initialises an Ellipse class that saves the ratio of Ellipse on canvas and scales according
     * @param x array which stores all x coordinates
     * @param y array which stores all y coordinates
     * @param colour Color class which sets pen colour
     * @param fillColour Color class which sets fill colour
     * @param toggleFill boolean to decide whether shape is filled in
     * @param pWidth integer of panel's width when object is instantiated
     * @param pHeight integer of panel's height when object is instantiated
     */
    public Ellipse(int[] x, int[] y, Color colour, Color fillColour, boolean toggleFill, int pWidth, int pHeight) {
        super(x, y, colour, fillColour);

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
        this.wratio1 = super.getX()[0]/(float)pWidth;
        this.wratio2 = super.getX()[1]/(float)pWidth;
        this.hratio1 = super.getY()[0]/(float)pHeight;
        this.hratio2 = super.getY()[1]/(float)pHeight;

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
        this.toggleFill = toggleFill;
    }

    /**
     * Draws the ellipse based on the new size of panel
     * @param g Graphics g
     * @param currentWidth integer of new panel's width
     * @param currentHeight integer of new panel's height
     */
    public void draw(Graphics g, int currentWidth, int currentHeight){
        int x = (int)(wratio1*currentWidth);
        int y = (int)(hratio1*currentHeight);
        int width = (int)(wratio2*currentWidth) - (int)(wratio1*currentWidth);
        int height = (int)(hratio2*currentHeight) - (int)(hratio1*currentHeight);
        // fill first if needed, then draw to prevent overlap edges
        if (toggleFill) {
            g.setColor(super.getFillColour());
            g.fillOval(x,y ,width ,height );
        }
        g.setColor(super.getColour());
        g.drawOval(x, y, width, height);

    }

    @Override
    public String getVEC(){
        return String.format("ELLIPSE %.2f %.2f %.2f %.2f", wratio1, hratio1, wratio2, hratio2);
    }

    @Override
    String getShape() {
        return "Ellipse";
    }
}
