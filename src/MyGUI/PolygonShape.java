package MyGUI;

import java.awt.*;
import java.lang.*;


/**
 * Child class of AllShapes for drawing polygons
 */
public class PolygonShape extends AllShapes{

    private float[] xRatio, yRatio;
    private int[] xNew, yNew;
    private boolean toggleFill;

    /**
     * Initialises a PolygonShape class that saves the ratio of polygon on canvas and scales according
     * @param x array which stores all x coordinates
     * @param y array which stores all y coordinates
     * @param colour Color class which sets pen colour
     * @param fillColour Color class which sets fill colour
     * @param toggleFill boolean to decide whether shape is filled in
     * @param pWidth integer of panel's width when object is instantiated
     * @param pHeight integer of panel's height when object is instantiated
     */
    public PolygonShape(int[] x, int[] y, Color colour, Color fillColour, boolean toggleFill, int pWidth, int pHeight) {
        super(x, y, colour,fillColour);
        this.toggleFill = toggleFill;
        xRatio = new float[x.length];
        yRatio = new float[y.length];

        for (int i = 0; i < x.length; i++){
            // calculates ratio of the drawing based on current dimensions
            xRatio[i] = x[i] / (float)pWidth;
            yRatio[i] = y[i] / (float)pHeight;
            // considers edge cases
            if (this.xRatio[i] >= 1){
                this.xRatio[i] = 0.999f;
            }
            if (this.yRatio[i] >= 1){
                this.yRatio[i] = 0.999f;
            }
        }
    }

    /**
     * Draws the polygon based on the new size of panel
     * @param g Graphics g
     * @param currentWidth integer of new panel's width
     * @param currentHeight integer of new panel's height
     */
    public void draw(Graphics g, int currentWidth, int currentHeight) {
        xNew = new int[super.getX().length];
        yNew = new int[super.getX().length];
        for (int i = 0; i < super.getX().length; i++){
            xNew[i] = (int)(xRatio[i] * currentWidth);
            yNew[i] = (int)(yRatio[i] * currentHeight);
        }
        // fill first if needed, then draw to prevent overlap edges
        if (toggleFill) {
            g.setColor(super.getFillColour());
            g.fillPolygon(xNew, yNew, super.getX().length);
        }
        g.setColor(super.getColour());
        g.drawPolygon(xNew, yNew, super.getX().length);

    }

    @Override
    public String getVEC(){
        String S = "POLYGON";
        for (int i = 0; i < super.getX().length; i++) {
            S = S.concat(" " +  String.format("%.2f",xRatio[i]) + " " +  String.format("%.2f",yRatio[i]));
        }
        return (S);
    }

    @Override
    public String getShape() {
        return "Polygon";
    }
}