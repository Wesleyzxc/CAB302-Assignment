package MyGUI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestCases {
    DrawArea panel;

    // Clear before
    @BeforeEach
    void setupPanel() { panel = null; }

    @Test
    void testConstruction() {
        panel = new DrawArea();
    }

    @Test
    void panelClearHistory() {
        panel = new DrawArea();
        panel.clearHistory();

        DrawArea panel2 = new DrawArea();
        assertEquals(panel.getHistory(), panel2.getHistory());
    }

    @Test
    void panelSetHistory(){
        panel = new DrawArea();
        int[] coords = {1,2,3};
        List<AllShapes> history = new LinkedList<>();
        Dot dot1 = new Dot(coords, coords, 2, Color.BLACK, 2, 2);
        history.add(dot1);
        panel.setHistory(history);
        assertEquals(panel.getHistory().get(0), dot1);
    }

    @Test
    void panelUndoHistory(){
        panel = new DrawArea();
        int[] coords = {1,2,3};
        List<AllShapes> history = new LinkedList<>();
        List<String> VEC = new LinkedList<>();
        Dot dot1 = new Dot(coords, coords, 2, Color.BLACK, 2, 2);
        history.add(dot1);
        history.add(dot1);
        VEC.add(dot1.getVEC());
        VEC.add(dot1.getVEC());

        panel.addShape(dot1, false, false, false);

        panel.addShape(dot1, true, true, true);
        panel.setVEC(VEC);
        panel.undoHistory();

        assertEquals(panel.getHistory().size(), 1);
        assertEquals(panel.getAllVEC().size(), 1);

    }

    @Test
    void panelToHex(){
        panel = new DrawArea();
        String colour = panel.toHexString(Color.BLACK);
        assertEquals(colour, "#000000");
    }

    @Test
    void panelIsDragging(){
        panel = new DrawArea();

        assertFalse(panel.isDragging());
    }

    @Test
    void panelSetDragging(){
        panel = new DrawArea();
        panel.setDragging(true);
        assertTrue(panel.isDragging());
    }

    @Test
    void panelDragLine(){
        panel = new DrawArea();
        int[] coords = {1,2,3};
        Dot dot1 = new Dot(coords, coords, 2, Color.BLACK, 2, 2);

        panel.dragLine(dot1);
    }

    @Test
    void panelAddMarker(){
        panel = new DrawArea();
        int[] coords = {1,2,3};
        List<Dot> polygonMarker = new LinkedList<>();
        Dot dot1 = new Dot(coords, coords, 2, Color.BLACK, 2, 2);
        polygonMarker.add(dot1);
        panel.addMarker(dot1);

        assertEquals(panel.getPolygonMarker(), polygonMarker);
    }

    @Test
    void panelClearMarker(){
        panel = new DrawArea();
        int[] coords = {1,2,3};
        List<Dot> polygonMarker = new LinkedList<>();
        Dot dot1 = new Dot(coords, coords, 2, Color.BLACK, 2, 2);
        panel.addMarker(dot1);
        panel.clearMarker();

        assertEquals(panel.getPolygonMarker(), polygonMarker);
    }

    @Test
    void panelPaintComponent(){
        BufferedImage bi = new BufferedImage(10,10, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bi.createGraphics();
        int[] coords = {1,2,3};

        panel = new DrawArea();
        List<AllShapes> history = new LinkedList<>();
        Dot dot1 = new Dot(coords, coords, 2, Color.BLACK, 2, 2);
        history.add(dot1);
        panel.dragLine(dot1);
        panel.setDragging(true);
        panel.setHistory(history);
        panel.addMarker(dot1);
        panel.paintComponent(g2);
    }

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

    @Test
    void testLineConstructor() {
        panel = new DrawArea();
        int[] coords = {0, 0};
        int[] coords2 = {10, 10};

        Line line = new Line(coords, coords2, Color.BLACK, panel.getWidth(), panel.getHeight());
        Line line2 = new Line(coords2, coords, Color.BLACK, panel.getWidth(), panel.getHeight());

    }

    @Test
    void testLineGetVEC(){
        panel = new DrawArea();
        int[] coords = {1, 1, 1};
        int[] coords2 = {10, 10, 10};
        Line line = new Line(coords, coords2, Color.BLACK, panel.getWidth(), panel.getHeight());

        String VEC = String.format("LINE %.2f %.2f %.2f %.2f", 1.0, 1.0, 1.0, 1.0);
        System.out.println(line.getVEC());
        assertEquals(line.getVEC(), VEC);
    }

    @Test
    void testLineGetShape(){
        panel = new DrawArea();
        int[] coords = {1, 1, 1};
        Line line = new Line(coords, coords, Color.BLACK, panel.getWidth(), panel.getHeight());
        assertEquals(line.getShape(), "Line");
    }

    @Test
    void testLineDraw(){
        panel = new DrawArea();
        int[] coords = {1, 1};

        Line line = new Line(coords, coords, Color.BLACK, panel.getWidth(), panel.getHeight());
        BufferedImage bi = new BufferedImage(10,10, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bi.createGraphics();
        line.draw(g2, 10, 10);

    }


}