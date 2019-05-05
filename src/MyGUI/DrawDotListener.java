package MyGUI;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


//Draw Listener - includes listening for dots and lines
public class DrawDotListener extends MouseAdapter {
    private DrawArea panel;
    private int x1,x2,y1,y2;

    public DrawDotListener(DrawArea panel) {
        super();
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        panel.addDot(new Dot(e.getX(), e.getY(), 10, Color.BLACK));
    }


    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println('p');
        System.out.print(e.getX());
        System.out.print(' ');
        System.out.print(e.getY());
        System.out.println(' ');
        x1 = e.getX();
        y1 = e.getY();
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println('r');
        System.out.print(e.getX());
        System.out.print(' ');
        System.out.print(e.getY());
        System.out.println(' ');
        x2 = e.getX();
        y2 = e.getY();

        if (x1 != x2 && y1 != y2){
            panel.addLine(new Line(x1,y1,x2,y2, Color.black));
        }
    }
}
