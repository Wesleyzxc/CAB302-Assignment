package MyGUI;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


//Draw Listener - includes listening for dots and lines
public class DrawObjectListener extends MouseAdapter{
    public enum Shape { LINE, RECTANGLE, ELLIPSE, STAR}
    private DrawArea panel;
    private int x1,x2,y1,y2; //Start of click and end of click coordinates ALL SHAPES ARE DETERMINED BY THESE COORDS
    private Shape shape = Shape.LINE; //Default shape is line


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

    @Override
    public void mouseClicked(MouseEvent e) {
        panel.addDot(new Dot(e.getX()-5, e.getY()-5, 10, Color.BLACK));
        // maybe x1 = ...
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
        if (shape == Shape.LINE){
            panel.addLine(new Line(x1,y1,x2,y2, Color.black));
            System.out.print("LINE " + x1 + " " + x2 + " " + y1 + " " + y2);
        }

        if (shape == Shape.RECTANGLE){
            panel.addLine(new Rectangle(x1,y1,x2,y2, Color.black));
        }

        if (shape == Shape.ELLIPSE){
            panel.addLine(new Ellipse(x1,y1,x2,y2, Color.black));
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x2 = e.getX(); //Not actually x2, just the end of the click
        y2 = e.getY(); //Not actually y2, just the end of the click
        Line drag;
        switch (shape) {
            case LINE:
                drag = new Line(x1,y1,x2,y2, Color.black);
                panel.dragLine(drag);
                break;
            case RECTANGLE:
                drag = new Rectangle(x1,y1,x2,y2, Color.black);
                panel.dragLine(drag);
                break;
            case ELLIPSE:
                drag = new Ellipse(x1,y1,x2,y2, Color.black);
                panel.dragLine(drag);
                break;
            case STAR:
                break;
        }



    }


}
