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
    Dot(int[] x, int[] y, int diameter, Color colour, int pWidth, int pHeight) {
        super(x, y, colour, colour);

        this.diameter = diameter;
        this.wratio1 = super.getX()[0]/(float)pWidth;
        this.hratio1 = super.getY()[0]/(float)pHeight;
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
        g.setColor(super.getColour());
        g.fillOval((int)(wratio1*currentWidth), (int)(hratio1*currentHeight), diameter, diameter);

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
    String getShape() {
        return "Dot";
    }
}
