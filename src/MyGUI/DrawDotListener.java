package MyGUI;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class DrawDotListener extends MouseAdapter {
    private DrawArea panel;

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
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println('r');
        System.out.print(e.getX());
        System.out.print(' ');
        System.out.print(e.getY());
        System.out.println(' ');
    }
}
