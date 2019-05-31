package MyGUI;

import java.awt.*;

public class Dot extends AllShapes {

    private int diameter;
    private float hratio1, wratio1;

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
        this.wratio1 = super.getX()[0]/(float)pWidth;
        this.hratio1 = super.getY()[0]/(float)pHeight;
        //considers edge cases
        if (this.wratio1 >= 1){
            this.wratio1 = 0.999f;
        }
        if (this.hratio1 >= 1){
            this.hratio1 = 0.999f;
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
        int x = (int)(wratio1*currentWidth);
        int y = (int)(hratio1*currentHeight);
        g.setColor(super.getColour());
        g.fillOval(x, y, diameter, diameter);

    }

    /**
     * Method that is used for saving VEC commands
     * @return String that represents a command
     */
    @Override
    public String getVEC(){
        return ((String.format("PLOT %.2f %.2f",wratio1,hratio1)));
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
