package MyGUI;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


/**
 * Draw Listener - includes listening for dots and lines
 */
public class DrawObjectListener extends MouseAdapter{
    public enum Shape {PLOT, LINE, RECTANGLE, ELLIPSE, POLYGON }
    private DrawArea panel;
    private int[] x = new int[2];
    private int[] y = new int[2];
    private Shape shape = Shape.LINE; //Default shape is line
    private Color penColour = new Color(0,0,0);
    private boolean fill = false; //fill is on or off
    private Color fillColour = new Color(0,0,0);
    private ArrayList<Integer> polyCoordsX = new ArrayList<>();
    private ArrayList<Integer> polyCoordsY = new ArrayList<>();
    private boolean isDot = true;
    private boolean changedPEN;
    private boolean changedFILL;
    private boolean changedTOGGLE;

    /**
     * Saves the panel to the listener that modifies the panel (drawing canvas)
     * @param panel panel which is attached to the listener
     */
    DrawObjectListener(DrawArea panel) {
        super();
        this.panel = panel;
    }

    /**
     * Sets shape
     * @param shape Shape that will be drawn
     */
    void chooseShape(Shape shape){
        this.shape = shape;
    }

    /**
     * Sets pen colour
     * @param colour Color class colour
     */
    void setPenColour(Color colour) {
        this.penColour = colour;
        changedPEN = true;
    }

    /**
     * Sets fill colour
     * @param colour Color class colour
     */
    void setFillColour(Color colour) {
        this.fillColour = colour;
        changedFILL = true;
    }

    /**
     * Toggle between fill or no fill
     * @param fill boolean variable for if fill is on
     */
    void toggleFill(boolean fill){
        if (this.fill){
            changedTOGGLE = true;
        }
        this.fill = fill;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        // If polygon not selected, a click means draw dot.
        if (shape != Shape.POLYGON) {
            isDot = true;
            // only draws dot on left click to allow extendability
            if (e.getButton() == MouseEvent.BUTTON1) {
                // minus 5 to center of mouse
                int[] x = {e.getX()-5};
                int[] y = {e.getY()-5};
                panel.addShape(new Dot(x, y, 10, penColour, panel.getWidth(), panel.getHeight()), changedPEN, changedFILL, changedTOGGLE);
                changedPEN = false;
                changedFILL = false;
                changedTOGGLE = false;
            }

        }

        if (shape == Shape.POLYGON) {
            // left click to set marker
            if (e.getButton() == MouseEvent.BUTTON1){
                int[] polyX = {e.getX()};
                int[] polyY = {e.getY()};
                polyCoordsX.add(polyX[0]);
                polyCoordsY.add(polyY[0]);

                panel.addMarker(new Dot(polyX, polyY, 3, penColour, panel.getWidth(), panel.getHeight()));
            }
            // right click to draw
            else if (e.getButton() == MouseEvent.BUTTON3){

                // only execute any necessary commands if it is possible to plot (at least a line)
                if (polyCoordsX.size() > 1) {
                    // Converts to an int[]
                    int[] arrX = polyCoordsX.stream().mapToInt(i -> i).toArray();
                    int[] arrY = polyCoordsY.stream().mapToInt(i -> i).toArray();

                    panel.addShape(new PolygonShape(arrX, arrY, penColour, fillColour, fill, panel.getWidth(), panel.getHeight()), changedPEN, changedFILL, changedTOGGLE);
                    changedPEN = false;
                    changedFILL = false;
                    changedTOGGLE = false;

                    // resets polygon state
                    panel.clearMarker();
                    polyCoordsX.clear();
                    polyCoordsY.clear();
                }

            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.x[0] = e.getX();
        this.y[0] = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.x[1] = e.getX();
        this.y[1] = e.getY();

        if (!isDot) {
            if (e.getButton() == MouseEvent.BUTTON1 && x[0] != x[1] && y[0] != y[1]) { //Draws selected shape and not a dot

                if (shape == Shape.LINE) {
                    panel.addShape(new Line(x, y, penColour, panel.getWidth(), panel.getHeight()), changedPEN, changedFILL, changedTOGGLE);
                    changedPEN = false;
                    changedFILL = false;
                    changedTOGGLE = false;
                }
                if (shape == Shape.RECTANGLE) {
                    panel.addShape(new Rectangle(x, y, penColour, fillColour, fill, panel.getWidth(), panel.getHeight()), changedPEN, changedFILL, changedTOGGLE);
                    changedPEN = false;
                    changedFILL = false;
                    changedTOGGLE = false;
                }

                if (shape == Shape.ELLIPSE) {
                    panel.addShape(new Ellipse(x, y, penColour, fillColour, fill, panel.getWidth(), panel.getHeight()), changedPEN, changedFILL, changedTOGGLE);
                    changedPEN = false;
                    changedFILL = false;
                    changedTOGGLE = false;
                }
            }
        panel.setDragging(false);
        }
        isDot = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!isDot) {
            // continuously getting coordinates
            this.x[1] = e.getX();
            this.y[1] = e.getY();

            // is dragging so that it draws continuously
            panel.setDragging(true);
            if (e.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK) { //Creates dragged shape only on left click
                switch (shape) {
                    case LINE:
                        AllShapes drag = new Line(x, y, penColour, panel.getWidth(), panel.getHeight());
                        panel.dragLine(drag);
                        break;
                    case RECTANGLE:
                        drag = new Rectangle(x, y, penColour, fillColour, fill, panel.getWidth(), panel.getHeight());
                        panel.dragLine(drag);
                        break;
                    case ELLIPSE:
                        drag = new Ellipse(x, y, penColour, fillColour, fill, panel.getWidth(), panel.getHeight());
                        panel.dragLine(drag);
                        break;
                    case POLYGON:
                        break;
                }
            }

        }
        isDot = false;
    }


}
