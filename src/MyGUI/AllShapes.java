package MyGUI;

import java.awt.*;

public abstract class AllShapes {
    private Color colour;
    private Color fillColour;
    private int[] x;
    private int[] y;
    private boolean visible = true;

    public Color getColour(){ return colour; }

    Color getFillColour(){ return fillColour; }

    int[] getX() {
        return x;
    }

    public void setX(int[] x) {
        this.x = x;
    }

    int[] getY() {
        return y;
    }

    public void setY(int[] y) {
        this.y = y;
    }

    /**
     * Abstract parent class which accepts an array of coordinates x and y, colour of pen in Color and fillColour in Color
     *
     * @param x array of x coordinates which will be plotted
     * @param y array of y coordinates which will be plotted
     * @param colour pen colour which is a Color class
     * @param fillColour fill colour which is a Color class
     *
     **/
    AllShapes(int[] x, int[] y, Color colour, Color fillColour){
        this.x = x;
        this.y = y;
        this.colour = colour;
        this.fillColour = fillColour;
    }

    /**
     *  Returns visibility for history support
     * @return true if it is displayed on canvas
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Sets visiblity of shape on canvas
     * @param visible boolean to determine if it will be displayed on canvas
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     *  Draws shape based on child class based on the resized canvas
     * @param g Graphics g
     * @param newWidth width of current canvas used to resize shape based on canvas size
     * @param newHeight height of current canvas used to resize shape based on canvas size
     */
    abstract void draw(Graphics g, int newWidth, int newHeight);

    /**
     * Gets VEC in a single string
     * @return VEC in string
     */
    abstract String getVEC();

    /**
     * Gets name of shape for history support
     * @return Shape in string
     */
    abstract String getShape();

}
