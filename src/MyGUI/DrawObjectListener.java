package MyGUI;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


//Draw Listener - includes listening for dots and lines
public class DrawObjectListener extends MouseAdapter {
    private DrawArea panel;
    private int x1,x2,y1,y2;

    public DrawObjectListener(DrawArea panel) {
        super();
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        panel.addDot(new Dot(e.getX()-5, e.getY()-5, 10, Color.BLACK));
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

        if (x1 != x2 && y1 != y2){
            panel.addLine(new Line(x1,y1,x2,y2, Color.black));
            System.out.print("LINE " + x1 + " " + x2 + " " + y1 + " " + y2);
        }
    }
}
