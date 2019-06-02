package MyGUI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
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
    void panelClearVEC(){
        panel = new DrawArea();
        panel.clearVEC();

        DrawArea panel2 = new DrawArea();
        assertEquals(panel.getAllVEC(), panel2.getAllVEC());
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
        panel.setVecHistory(VEC);
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

    @Test
    void testRectangleConstructor() {
        panel = new DrawArea();
        int[] coords = {0, 0};
        int[] coords2 = {10, 10};

        Rectangle rectangle = new Rectangle(coords2, coords, Color.BLACK, Color.BLACK, false, panel.getWidth(), panel.getHeight());
        Rectangle rectangle2 = new Rectangle(coords2, coords2, Color.BLACK, Color.BLACK, true, panel.getWidth(), panel.getHeight());

    }

    @Test
    void testRectangleGetVEC(){
        panel = new DrawArea();
        int[] coords = {1, 1};
        int[] coords2 = {10, 10};
        Rectangle rectangle = new Rectangle(coords2, coords, Color.BLACK, Color.BLACK, false, panel.getWidth(), panel.getHeight());

        String VEC = String.format("RECTANGLE %.2f %.2f %.2f %.2f", 1.0, 1.0, 1.0, 1.0);
        System.out.println(rectangle.getVEC());
        assertEquals(rectangle.getVEC(), VEC);
    }

    @Test
    void testRectangleGetShape(){
        panel = new DrawArea();
        int[] coords = {1, 1};
        Rectangle rectangle = new Rectangle(coords, coords, Color.BLACK, Color.BLACK, false, panel.getWidth(), panel.getHeight());
        assertEquals(rectangle.getShape(), "Rectangle");
    }

    @Test
    void testRectangleDraw(){
        panel = new DrawArea();
        int[] coords = {1, 1};

        Rectangle rectangle = new Rectangle(coords, coords, Color.BLACK, Color.BLACK, true, panel.getWidth(), panel.getHeight());
        BufferedImage bi = new BufferedImage(10,10, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bi.createGraphics();
        rectangle.draw(g2, 10, 10);

        Rectangle rectangle2 = new Rectangle(coords, coords, Color.BLACK, Color.BLACK, false, panel.getWidth(), panel.getHeight());
        rectangle2.draw(g2, 10, 10);

    }

    @Test
    void testPolygonConstructor() {
        panel = new DrawArea();
        int[] coords = {0, 0};
        int[] coords2 = {10, 10};

        PolygonShape polygon = new PolygonShape(coords2, coords, Color.BLACK, Color.BLACK, false, panel.getWidth(), panel.getHeight());
        PolygonShape polygon2 = new PolygonShape(coords2, coords2, Color.BLACK, Color.BLACK, true, panel.getWidth(), panel.getHeight());

    }

    @Test
    void testPolygonGetVEC(){
        panel = new DrawArea();
        int[] coords = {1, 1};
        int[] coords2 = {10, 10};
        PolygonShape polygon = new PolygonShape(coords2, coords, Color.BLACK, Color.BLACK, false, panel.getWidth(), panel.getHeight());

        String VEC = String.format("POLYGON %.2f %.2f %.2f %.2f", 1.0, 1.0, 1.0, 1.0);
        System.out.println(polygon.getVEC());
        assertEquals(polygon.getVEC(), VEC);
    }

    @Test
    void testPolygonGetShape(){
        panel = new DrawArea();
        int[] coords = {1, 1};
        PolygonShape polygon = new PolygonShape(coords, coords, Color.BLACK, Color.BLACK, false, panel.getWidth(), panel.getHeight());
        assertEquals(polygon.getShape(), "Polygon");
    }

    @Test
    void testPolygonDraw(){
        panel = new DrawArea();
        int[] coords = {1, 1};

        PolygonShape polygon = new PolygonShape(coords, coords, Color.BLACK, Color.BLACK, true, panel.getWidth(), panel.getHeight());
        BufferedImage bi = new BufferedImage(10,10, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bi.createGraphics();
        polygon.draw(g2, 10, 10);
        PolygonShape polygon2 = new PolygonShape(coords, coords, Color.BLACK, Color.BLACK, false, panel.getWidth(), panel.getHeight());
        polygon2.draw(g2, 10, 10);


    }

    @Test
    void testDrawObjectListener(){
        panel = new DrawArea();
        DrawObjectListener DrawObject = new DrawObjectListener(panel);
        DrawObject.toggleFill(true);
        DrawObject.chooseShape(DrawObjectListener.Shape.ELLIPSE);
        DrawObject.chooseShape(DrawObjectListener.Shape.LINE);
        DrawObject.chooseShape(DrawObjectListener.Shape.RECTANGLE);
        DrawObject.chooseShape(DrawObjectListener.Shape.POLYGON);
        DrawObject.setFillColour(Color.BLACK);
        DrawObject.setPenColour(Color.BLACK);

    }

    @Test
    void initialiseAllListeners() {
        panel = new DrawArea();
        DrawObjectListener DrawObject = new DrawObjectListener(panel);
        JButton button = new JButton();

        AboutAction aboutAction = new AboutAction();
        ClearAction clearAction = new ClearAction(panel);
        HistoryAction historyAction = new HistoryAction(panel, "5");
        OpenAction openAction = new OpenAction(panel);
        SaveAction saveAction = new SaveAction(panel);
        UndoAction undoAction = new UndoAction(panel);
        ExitAction exitAction = new ExitAction();
        ShapeChooser shapeChooser = new ShapeChooser(DrawObject, button, button, button, button);
    }

    @Test
    void initialiseGUI(){
        GUIForm guiForm = new GUIForm();
        GUIForm.main(new String[]{"run"});
    }


    //Invalid command
    @Test
    void exceptionInvalidCommandPlot() throws InvalidCommand{
        panel = new DrawArea();
        String eachLine = "PLOT 0.70 1aaa";
        OpenAction openVEC = new OpenAction(panel);
        assertThrows(InvalidCommand.class, ()-> {openVEC.shapeCommandCheck(DrawObjectListener.Shape.PLOT, eachLine);});
    }

    //Ratio > 1.0
    @Test
    void exceptionInvalidCommandPlotWrongRatio() throws InvalidCommand{
        panel = new DrawArea();
        String eachLine = "PLOT 0.70 1.5";
        OpenAction openVEC = new OpenAction(panel);
        assertThrows(InvalidCommand.class, ()-> {openVEC.shapeCommandCheck(DrawObjectListener.Shape.PLOT, eachLine);});
    }

    //Missing Y coordinate
    @Test
    void exceptionInvalidCommandPlotMissingCoordinate() throws InvalidCommand{
        panel = new DrawArea();
        String eachLine = "PLOT 0.88";
        OpenAction openVEC = new OpenAction(panel);
        assertThrows(InvalidCommand.class, ()-> {openVEC.shapeCommandCheck(DrawObjectListener.Shape.PLOT, eachLine);});
    }
    //Excess coordinates
    @Test
    void exceptionInvalidCommandPlotExcessCoordinate() throws InvalidCommand{
        panel = new DrawArea();
        String eachLine = "PLOT 1.00 0.22 0.88";
        OpenAction openVEC = new OpenAction(panel);
        assertThrows(InvalidCommand.class, ()-> {openVEC.shapeCommandCheck(DrawObjectListener.Shape.PLOT, eachLine);});
    }

    //Invalid command
    @Test
    void exceptionInvalidCommandRectangle() throws InvalidCommand{
        panel = new DrawArea();
        String eachLine = "RECTANGLE 0.70 1aaa 0.55 0.11";
        OpenAction openVEC = new OpenAction(panel);
        assertThrows(InvalidCommand.class, ()-> {openVEC.shapeCommandCheck(DrawObjectListener.Shape.RECTANGLE, eachLine);});
    }
    //Ratio > 1.0
    @Test
    void exceptionInvalidCommandRectangleWrongRatio() throws InvalidCommand{
        panel = new DrawArea();
        String eachLine = "RECTANGLE 0.70 1.5 0.4 0.1";
        OpenAction openVEC = new OpenAction(panel);
        assertThrows(InvalidCommand.class, ()-> {openVEC.shapeCommandCheck(DrawObjectListener.Shape.RECTANGLE, eachLine);});
    }
    //Missing Y coordinate
    @Test
    void exceptionInvalidCommandRectangleMissingCoordinate() throws InvalidCommand{
        panel = new DrawArea();
        String eachLine = "RECTANGLE 0.88 0.12 0.7";
        OpenAction openVEC = new OpenAction(panel);
        assertThrows(InvalidCommand.class, ()-> {openVEC.shapeCommandCheck(DrawObjectListener.Shape.RECTANGLE, eachLine);});
    }
    //Excess coordinates
    @Test
    void exceptionInvalidCommandRectangleExcessCoordinate() throws InvalidCommand{
        panel = new DrawArea();
        String eachLine = "RECTANGLE 1.00 0.22 0.88 0.5 0.1";
        OpenAction openVEC = new OpenAction(panel);
        assertThrows(InvalidCommand.class, ()-> {openVEC.shapeCommandCheck(DrawObjectListener.Shape.RECTANGLE, eachLine);});
    }

    //Invalid command
    @Test
    void exceptionInvalidCommandLine() throws InvalidCommand{
        panel = new DrawArea();
        String eachLine = "LINE 0.70 1aaa 0.55 0.11";
        OpenAction openVEC = new OpenAction(panel);
        assertThrows(InvalidCommand.class, ()-> {openVEC.shapeCommandCheck(DrawObjectListener.Shape.LINE, eachLine);});
    }
    //Ratio > 1.0
    @Test
    void exceptionInvalidCommandLineWrongRatio() throws InvalidCommand{
        panel = new DrawArea();
        String eachLine = "LINE 0.70 1.5 0.55 0.33";
        OpenAction openVEC = new OpenAction(panel);
        assertThrows(InvalidCommand.class, ()-> {openVEC.shapeCommandCheck(DrawObjectListener.Shape.LINE, eachLine);});
    }
    //Missing Y coordinate
    @Test
    void exceptionInvalidCommandLineMissingCoordinate() throws InvalidCommand{
        panel = new DrawArea();
        String eachLine = "LINE 0.88 1.0 0.4";
        OpenAction openVEC = new OpenAction(panel);
        assertThrows(InvalidCommand.class, ()-> {openVEC.shapeCommandCheck(DrawObjectListener.Shape.LINE, eachLine);});
    }
    //Excess coordinates
    @Test
    void exceptionInvalidCommandLineExcessCoordinate() throws InvalidCommand{
        panel = new DrawArea();
        String eachLine = "LINE 1.00 0.22 0.88 0.1 0.5";
        OpenAction openVEC = new OpenAction(panel);
        assertThrows(InvalidCommand.class, ()-> {openVEC.shapeCommandCheck(DrawObjectListener.Shape.LINE, eachLine);});
    }

    //Invalid command
    @Test
    void exceptionInvalidCommandEllipse() throws InvalidCommand{
        panel = new DrawArea();
        String eachLine = "ELLIPSE 0.70 1aaa 0.55 0.11";
        OpenAction openVEC = new OpenAction(panel);
        assertThrows(InvalidCommand.class, ()-> {openVEC.shapeCommandCheck(DrawObjectListener.Shape.ELLIPSE, eachLine);});
    }
    //Ratio > 1.0
    @Test
    void exceptionInvalidCommandEllipseWrongRatio() throws InvalidCommand{
        panel = new DrawArea();
        String eachLine = "ELLIPSE 0.70 1.5 0.3 0.1";
        OpenAction openVEC = new OpenAction(panel);
        assertThrows(InvalidCommand.class, ()-> {openVEC.shapeCommandCheck(DrawObjectListener.Shape.ELLIPSE, eachLine);});
    }
    //Missing Y coordinate
    @Test
    void exceptionInvalidCommandEllipseMissingCoordinate() throws InvalidCommand{
        panel = new DrawArea();
        String eachLine = "ELLIPSE 0.88 0.5 0.1";
        OpenAction openVEC = new OpenAction(panel);
        assertThrows(InvalidCommand.class, ()-> {openVEC.shapeCommandCheck(DrawObjectListener.Shape.ELLIPSE, eachLine);});
    }
    @Test
    void exceptionInvalidCommandEllipseExcessCoordinate() throws InvalidCommand{
        panel = new DrawArea();
        String eachLine = "Ellipse 1.00 0.22 0.88 0.7 0.1";
        OpenAction openVEC = new OpenAction(panel);
        assertThrows(InvalidCommand.class, ()-> {openVEC.shapeCommandCheck(DrawObjectListener.Shape.ELLIPSE, eachLine);});
    }
    //Invalid command
    @Test
    void exceptionInvalidCommandPolygon() throws InvalidCommand{
        panel = new DrawArea();
        String eachLine = "POLYGON 0.70 1aaa 0.55 0.11 0.5 0.1";
        OpenAction openVEC = new OpenAction(panel);
        assertThrows(InvalidCommand.class, ()-> {openVEC.shapeCommandCheck(DrawObjectListener.Shape.POLYGON, eachLine);});
    }
    //Ratio > 1.0
    @Test
    void exceptionInvalidCommandPolygonWrongRatio() throws InvalidCommand{
        panel = new DrawArea();
        String eachLine = "POLYGON 0.70 1.5 0.5 0.1";
        OpenAction openVEC = new OpenAction(panel);
        assertThrows(InvalidCommand.class, ()-> {openVEC.shapeCommandCheck(DrawObjectListener.Shape.POLYGON, eachLine);});
    }
    //Missing Y coordinate
    @Test
    void exceptionInvalidCommandPolygonMissingCoordinate() throws InvalidCommand{
        panel = new DrawArea();
        String eachLine = "POLYGON 0.88 0.7 0.34 0.4 0.6 0.4 .0.1";
        OpenAction openVEC = new OpenAction(panel);
        assertThrows(InvalidCommand.class, ()-> {openVEC.shapeCommandCheck(DrawObjectListener.Shape.POLYGON, eachLine);});
    }

}