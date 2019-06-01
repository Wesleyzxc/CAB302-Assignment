package MyGUI;

import java.awt.*;

public class Dot extends AllShapes {

    private int diameter;
    private float heightRatio, widthRatio;

    /**
     * Abstract parent class which accepts an array of coordinates x and y, colour of pen in Color and fillColour in Color
     *
     * @param x array of x coordinates which will be plotted
     * @param y array of y coordinates which will be plotted
     * @param colour pen colour which is a Color class
     * @param pWidth width of the current panel which is used to calculate position ratio
     * @param pHeight height of the current panel which is used to calculate position ratio
     *
     **/
    public Dot(int[] x, int[] y, int diameter, Color colour, int pWidth, int pHeight) {
        super(x, y, colour, colour);

        this.diameter = diameter;

        // calculates ratio of the drawing based on current dimensions
        this.widthRatio = super.getX()[0]/(float)pWidth;
        this.heightRatio = super.getY()[0]/(float)pHeight;
        //considers edge cases
        if (this.widthRatio >= 1){
            this.widthRatio = 0.999f;
        }
        if (this.heightRatio >= 1){
            this.heightRatio = 0.999f;
        }
    }

    /**
     * Draw function which sets pen colour and fills the dot
     *
     * @param g A Graphics object g which is used for painting
     * @param currentWidth width of panel when drawn which will scale according to ratio
     * @param currentHeight height of panel when drawn which will scale according to ratio
     *
     **/
    public void draw(Graphics g, int currentWidth, int currentHeight){
        int x = (int)(widthRatio *currentWidth);
        int y = (int)(heightRatio *currentHeight);
        g.setColor(super.getColour());
        g.fillOval(x, y, diameter, diameter);

    }

    /**
     * Method that is used for saving VEC commands
     * @return String that represents a command
     */
    @Override
    public String getVEC(){
        return ((String.format("PLOT %.2f %.2f", widthRatio, heightRatio)));
    }

    /**
     *  Method that returns shape in string
     * @return String value of "Dot"
     */
    @Override
    public String getShape() {
        return "Dot";
    }
}
