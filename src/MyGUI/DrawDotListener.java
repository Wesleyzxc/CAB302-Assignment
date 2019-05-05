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

}
