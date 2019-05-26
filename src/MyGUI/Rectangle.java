package MyGUI;

import java.awt.*;

public class Rectangle extends AllShapes {

    private boolean toggleFill;
    private float hratio1, wratio1, hratio2, wratio2;

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
        if (x[0] > x[1]) {
            int[] replaced = {x[1], x[0]};
            super.setX(replaced);
        }
        if (y[0] > y[1]) {
            int[] replaced = {y[1], y[0]};
            super.setY(replaced);

        }
        this.wratio1 = super.getX()[0]/(float)pWidth;
        this.wratio2 = super.getX()[1]/(float)pWidth;
        this.hratio1 = super.getY()[0]/(float)pHeight;
        this.hratio2 = super.getY()[1]/(float)pHeight;

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
     * Draws the rectangle based on the new size of panel
     * @param g Graphics g
     * @param currentWidth integer of new panel's width
     * @param currentHeight integer of new panel's height
     */
    public void draw(Graphics g, int currentWidth, int currentHeight) {
        if (!toggleFill) {
            g.setColor(super.getColour());
//            g.drawRect(x1, y1, x2 - x1, y2 - y1);
            g.drawRect((int)(wratio1*currentWidth), (int)(hratio1*currentHeight), (int)(wratio2*currentWidth) - (int)(wratio1*currentWidth), (int)(hratio2*currentHeight) - (int)(hratio1*currentHeight));

        } else {
            g.setColor(super.getFillColour());
            g.fillRect((int)(wratio1*currentWidth), (int)(hratio1*currentHeight), (int)(wratio2*currentWidth) - (int)(wratio1*currentWidth), (int)(hratio2*currentHeight) - (int)(hratio1*currentHeight));
            g.setColor(super.getColour());
            g.drawRect((int)(wratio1*currentWidth), (int)(hratio1*currentHeight), (int)(wratio2*currentWidth) - (int)(wratio1*currentWidth), (int)(hratio2*currentHeight) - (int)(hratio1*currentHeight));

        }
    }

    @Override
    public String getVEC() {
        //String hex = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
        return ((String.format("RECTANGLE %.2f %.2f %.2f %.2f", wratio1, hratio1, wratio2, hratio2)));
    }

    @Override
    String getShape() {
        return "Rectangle";
    }

}






