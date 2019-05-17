package MyGUI;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


//Draw Listener - includes listening for dots and lines
public class DrawObjectListener extends MouseAdapter{
    public enum Shape { LINE, RECTANGLE, ELLIPSE, POLYGON }
    private DrawArea panel;
    private int x1,x2,y1,y2; //Start of click and end of click coordinates ALL SHAPES ARE DETERMINED BY THESE COORDS
    private int[] x = new int[2];
    private int[] y = new int[2];
    private Shape shape = Shape.LINE; //Default shape is line
    private Color penColour = new Color(0,0,0);
    private boolean fill = false;
    private Color fillColour = new Color(0,0,0);
    private ArrayList<Integer> polyCoordsX = new ArrayList<>();
    private ArrayList<Integer> polyCoordsY = new ArrayList<>();
    private boolean isDot = true;
    private boolean changedPEN;
    private boolean changedFILL;

    DrawObjectListener(DrawArea panel) {
        super();
        this.panel = panel;
    }

    void chooseShape(Shape shape){
        this.shape = shape;
    }

    Shape getShape(){
        return shape;
    }

    void setPenColour(Color colour) {
        this.penColour = colour;
        changedPEN = true;
    }

    void setFillColour(Color colour) {
        this.fillColour = colour;
        changedFILL = true;
    }

    void toggleFill(boolean fill){ this.fill = fill; }

  /*  private String addVecColor(Color color){
        changedPEN = false;
        String hex = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
        return ((String.format("PEN %s",hex)));
    }

    private String addVecFillColor(Color color){
        changedFill = false;
        String hex = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
        return ((String.format("FILL %s",hex)));
    }*/


    private boolean dragging = false;

    @Override
    public void mouseClicked(MouseEvent e) {

        if (shape != Shape.POLYGON) {
            isDot = true;

            if (e.getButton() == MouseEvent.BUTTON1) {
                int[] x = {e.getX()};
                int[] y = {e.getY()};
                System.out.println(e.getX()); // where my mouse think it is on scale 1 canvas
                panel.addShape(new Dot(x, y, 10, penColour, panel.getWidth(), panel.getHeight()), changedPEN, changedFILL);
                changedPEN = false;
                changedFILL = false;
            }
            else if (e.getButton() == MouseEvent.BUTTON3) {
                panel.removeAll();
            }
        }

        if (shape == Shape.POLYGON) {
            if (e.getButton() == MouseEvent.BUTTON1){
                int[] polyX = {e.getX()};
                int[] polyY = {e.getY()};
                polyCoordsX.add(polyX[0]);
                polyCoordsY.add(polyY[0]);

                panel.addMarker(new Dot(polyX, polyY, 3, penColour, panel.getWidth(), panel.getHeight()));
            }
            else if (e.getButton() == MouseEvent.BUTTON3){
                System.out.println("Right Click");
                int[] arrX = polyCoordsX.stream().mapToInt(i -> i).toArray();
                int[] arrY = polyCoordsY.stream().mapToInt(i -> i).toArray();
                if (arrX.length > 0) {
                    panel.addShape(new PolygonShape(arrX, arrY, penColour, fillColour, fill, panel.getWidth(), panel.getHeight()), changedPEN, changedFILL);
                    changedPEN = false;
                    changedFILL = false;
                }
                panel.clearMarker();
                polyCoordsX.clear();
                polyCoordsY.clear();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x1 = e.getX();
        this.x[0] = e.getX();
        this.y[0] = e.getY();
        y1 = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
        this.x[1] = e.getX();
        this.y[1] = e.getY();

        if (!isDot) {
            if (e.getButton() == MouseEvent.BUTTON1 && x1 != x2 && y1 != y2) { //Draws selected shape

                if (shape == Shape.LINE) {
                    panel.addShape(new Line(x, y, penColour, panel.getWidth(), panel.getHeight()), changedPEN, changedFILL);
                    changedPEN = false;
                    changedFILL = false;
                }
                if (shape == Shape.RECTANGLE) {
                    panel.addShape(new Rectangle(x, y, penColour, fillColour, fill, panel.getWidth(), panel.getHeight()), changedPEN, changedFILL);
                    changedPEN = false;
                    changedFILL = false;
                }

                if (shape == Shape.ELLIPSE) {
                    panel.addShape(new Ellipse(x, y, penColour, fillColour, fill, panel.getWidth(), panel.getHeight()), changedPEN, changedFILL);
                    changedPEN = false;
                    changedFILL = false;
                }
            }
        panel.setDragging(false);
        }
        isDot = false;
    }

    private AllShapes drag;
    @Override
    public void mouseDragged(MouseEvent e) {
        if (!isDot) {
            x2 = e.getX(); //Not actually x2, just the end of the click
            y2 = e.getY(); //Not actually y2, just the end of the click
            this.x[1] = e.getX();
            this.y[1] = e.getY();

            panel.setDragging(true);
            if (e.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK) { //Creates dragged shape
                switch (shape) {
                    case LINE:
                        drag = new Line(x, y, penColour, panel.getWidth(), panel.getHeight());
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
