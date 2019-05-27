package MyGUI;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

class FunctionTesting {
    DrawArea panel;

    // Clear before
    @BeforeEach
    void setupPanel() { panel = null; }

    @Test
    public void testConstruction() {
        panel = new DrawArea();
    }

    @Test
    public void panelClearHistory() {
        panel = new DrawArea();
        panel.clearHistory();

        DrawArea panel2 = new DrawArea();
        assertEquals(panel.getHistory(), panel2.getHistory());
    }

    @Test
    public void panelSetHistory(){
        panel = new DrawArea();
        int[] coords = {1,2,3};
        List<AllShapes> history = new LinkedList<>();
        Dot dot1 = new Dot(coords, coords, 2, Color.BLACK, 2, 2);
        history.add(dot1);
        panel.setHistory(history);
        assertEquals(panel.getHistory().get(0), dot1);
    }

    @Test
    public void panelUndoHistory(){
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
    public void panelToHex(){
        panel = new DrawArea();
        String colour = panel.toHexString(Color.BLACK);
        assertEquals(colour, "#000000");
    }

    @Test
    public void panelIsDragging(){
        panel = new DrawArea();

        assertEquals(panel.isDragging(), false);
    }

    @Test
    public void panelSetDragging(){
        panel = new DrawArea();
        panel.setDragging(true);
        assertEquals(panel.isDragging(), true);
    }

    @Test
    public void panelDragLine(){
        panel = new DrawArea();
        int[] coords = {1,2,3};

        Dot dot1 = new Dot(coords, coords, 2, Color.BLACK, 2, 2);

        panel.dragLine(dot1);
    }

    @Test
    public void panelAddMarker(){
        panel = new DrawArea();
        int[] coords = {1,2,3};
        List<Dot> polygonMarker = new LinkedList<>();
        Dot dot1 = new Dot(coords, coords, 2, Color.BLACK, 2, 2);
        polygonMarker.add(dot1);
        panel.addMarker(dot1);

        assertEquals(panel.getPolygonMarker(), polygonMarker);
    }

    @Test
    public void panelClearMarker(){
        panel = new DrawArea();
        int[] coords = {1,2,3};
        List<Dot> polygonMarker = new LinkedList<>();
        Dot dot1 = new Dot(coords, coords, 2, Color.BLACK, 2, 2);
        panel.addMarker(dot1);
        panel.clearMarker();

        assertEquals(panel.getPolygonMarker(), polygonMarker);
    }

    @Test
    public void panelPaintComponent(){
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






}