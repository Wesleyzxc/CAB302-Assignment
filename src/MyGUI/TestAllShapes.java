package MyGUI;

import MyGUI.AllShapes;
import MyGUI.Dot;
import MyGUI.DrawArea;
import MyGUI.Ellipse;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestAllShapes {
    private DrawArea panel;

    @Test
    void AllShapesTest(){
        int[] coords = {0,0,0};
        AllShapes shapes = new Dot(coords, coords, 2, Color.BLACK, 2, 2);
        shapes.setX(coords);
        shapes.setY(coords);
        shapes.setVisible(false);
        assertEquals(shapes.getColour(), Color.BLACK);
        assertEquals(shapes.getFillColour(), Color.BLACK);
        assertFalse(shapes.isVisible());

    }

    @Test
    void getDotName(){
        int[] coords = {0,0,0};
        Dot dot1 = new Dot(coords, coords, 2, Color.BLACK, 2, 2);
        assertEquals(dot1.getShape(), "Dot");

    }

    @Test
    void testEllipseConstructor() {
        panel = new DrawArea();
        int[] coords = {0, 0};
        int[] coords2 = {10, 10};

        Ellipse ellipse = new Ellipse(coords, coords2, Color.BLACK, Color.BLACK, true, panel.getWidth(), panel.getHeight());
        Ellipse ellipse2 = new Ellipse(coords2, coords, Color.BLACK, Color.BLACK, false, panel.getWidth(), panel.getHeight());

    }

    @Test
    void testEllipseGetVEC(){
        panel = new DrawArea();
        int[] coords = {1, 1, 1};
        int[] coords2 = {10, 10, 10};

        Ellipse ellipse = new Ellipse(coords, coords2, Color.BLACK, Color.BLACK, true, panel.getWidth(), panel.getHeight());
        String VEC = String.format("ELLIPSE %.2f %.2f %.2f %.2f", 1.0, 1.0, 1.0, 1.0);
        System.out.println(ellipse.getVEC());
        assertEquals(ellipse.getVEC(), VEC);
    }

    @Test
    void testEllipseGetShape(){
        panel = new DrawArea();
        int[] coords = {1, 1, 1};
        Ellipse ellipse = new Ellipse(coords, coords, Color.BLACK, Color.BLACK, true, panel.getWidth(), panel.getHeight());
        assertEquals(ellipse.getShape(), "Ellipse");
    }

    @Test
    void testEllipseDraw(){
        panel = new DrawArea();
        int[] coords = {1, 1};
        int[] coords2 = {10, 10};

        Ellipse ellipse = new Ellipse(coords, coords2, Color.BLACK, Color.BLACK, true, panel.getWidth(), panel.getHeight());
        String VEC = String.format("ELLIPSE %.2f %.2f %.2f %.2f", 1.0, 1.0, 1.0, 1.0);
        BufferedImage bi = new BufferedImage(10,10, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bi.createGraphics();
        ellipse.draw(g2, 10, 10);

        Ellipse ellipse2 = new Ellipse(coords2, coords, Color.BLACK, Color.BLACK, false, panel.getWidth(), panel.getHeight());

        g2 = bi.createGraphics();
        ellipse2.draw(g2, 10, 10);
    }

}
