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
    private Shape shape = Shape.LINE; //Default shape is line
    private Color penColour = new Color(0,0,0);
    private boolean fill = false;
    private Color fillColour = new Color(0,0,0);
    private ArrayList<Integer> polyCoordsX = new ArrayList<Integer>();
    private ArrayList<Integer> polyCoordsY = new ArrayList<Integer>();
    private boolean isDot = true;


    public DrawObjectListener(DrawArea panel) {
        super();
        this.panel = panel;
    }

    public void chooseShape(Shape shape){
        this.shape = shape;
    }

    public Shape getShape(){
        return shape;
    }

    public void setPenColour(Color colour) {
        this.penColour = colour;
    }

    public void setFillColour(Color colour) {
        this.fillColour = colour;
    }

    public void toggleFill(boolean fill){ this.fill = fill; }

    private boolean dragging = false;
    @Override
    public void mouseClicked(MouseEvent e) {
        if (shape != Shape.POLYGON) {
            isDot = true;
            if (e.getButton() == MouseEvent.BUTTON1) {
                panel.addShape(new Dot(e.getX() - 5, e.getY() - 5, 10, penColour));
            }
            else if (e.getButton() == MouseEvent.BUTTON3) {
                panel.removeAll();
            }
        }
        // maybe x1 = ...
        if (shape == Shape.POLYGON) {
            if (e.getButton() == MouseEvent.BUTTON1){
                polyCoordsX.add(e.getX());
                polyCoordsY.add(e.getY());
                panel.addMarker(new Dot(e.getX(), e.getY(), 3, penColour));
            }
            else if (e.getButton() == MouseEvent.BUTTON3){
                System.out.println("Right Click");
                int[] arrX = polyCoordsX.stream().mapToInt(i -> i).toArray();
                int[] arrY = polyCoordsY.stream().mapToInt(i -> i).toArray();
                if (arrX.length > 0) {
                    panel.addShape(new PolygonShape(arrX, arrY, penColour, fillColour, fill));
                }panel.clearMarker();
                polyCoordsX.clear();
                polyCoordsY.clear();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
        System.out.println(isDot);
        if (!isDot) {
            if (e.getButton() == MouseEvent.BUTTON1 && x1 != x2 && y1 != y2) { //Draws selected shape

                if (shape == Shape.LINE) {
                    panel.addShape(new Line(x1, y1, x2, y2, penColour));
                }
                if (shape == Shape.RECTANGLE) {
                    panel.addShape(new Rectangle(x1, y1, x2, y2, penColour, fillColour, fill));
                }

                if (shape == Shape.ELLIPSE) {
                    panel.addShape(new Ellipse(x1, y1, x2, y2, penColour, fillColour, fill));
                }
            }
        panel.setDragging(false);
        }
        isDot = false;
    }

    AllShapes drag;
    @Override
    public void mouseDragged(MouseEvent e) {
        if (!isDot) {
            x2 = e.getX(); //Not actually x2, just the end of the click
            y2 = e.getY(); //Not actually y2, just the end of the click
            panel.setDragging(true);
            if (e.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK) { //Creates dragged shape
                switch (shape) {
                    case LINE:
                        drag = new Line(x1, y1, x2, y2, penColour);
                        panel.dragLine(drag);
                        break;
                    case RECTANGLE:
                        drag = new Rectangle(x1, y1, x2, y2, penColour, fillColour, fill);
                        panel.dragLine(drag);
                        break;
                    case ELLIPSE:
                        drag = new Ellipse(x1, y1, x2, y2, penColour, fillColour, fill);
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
