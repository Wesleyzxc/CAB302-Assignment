package MyGUI;

import com.sun.source.util.DocTreeScanner;

import javax.swing.*;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DrawArea extends JPanel {
    private List<Dot> polygonMarker = new LinkedList<>();

    public List<AllShapes> getHistory() {
        return history;
    }

    public void setHistory(List<AllShapes> history) {
        this.history = history;
    }

    private List<AllShapes> history = new LinkedList<>(); //main history

    public List<AllShapes> getPreviousHistory() {
        return previousHistory;
    }

    public void clearHistory() {
        history.clear();
    }
    public void setPreviousHistory(List<AllShapes> previousHistory) {
        this.previousHistory = previousHistory;
    }

    private List<AllShapes> previousHistory = new LinkedList<>();
    private List<String> VEC = new LinkedList<>();
    private AllShapes drag;

    public boolean isDragging() {
        return dragging;
    }


    void setDragging(boolean dragging) {
        this.dragging = dragging;
    }

    private boolean dragging;


    List<String> getAllVEC() {
        return VEC;
    }

    public String toHexString(Color colour) throws NullPointerException {
        String hexColour = Integer.toHexString(colour.getRGB() & 0xffffff);
        if (hexColour.length() < 6) {
            hexColour = "000000".substring(0, 6 - hexColour.length()) + hexColour;
        }
        return "#" + hexColour;
    }

    void addShape(AllShapes shape, boolean changedPEN, boolean changedFILL, boolean toggleFILL) {
        history.add(shape);
        String final_VEC ="";
        Color pen = shape.getColour();
        String hexPen = toHexString(pen);
        Color fill = shape.getFillColour();
        String hexFill = toHexString(fill);
        String shapeVEC = shape.getVEC();

        if (changedPEN){
            changedPEN = false;
            final_VEC = final_VEC.concat("PEN " + hexPen + ",");
        }
        if (changedFILL){
            changedFILL = false;
            final_VEC = final_VEC.concat("FILL " + hexFill + ",");
        }
        if (toggleFILL){
            toggleFILL = false;
            final_VEC = final_VEC.concat("FILL OFF" + ",");
        }

        final_VEC = final_VEC.concat(shapeVEC);
        VEC.add(final_VEC); //parameter - true if color changed
        System.out.println(VEC);
        final_VEC = "";
        this.repaint();
        }



    void dragLine(AllShapes line){
        drag = line;
        this.repaint();
    }

    void addMarker(Dot poly) {
        polygonMarker.add(poly);
        this.repaint();
    }

    void clearMarker() {
        polygonMarker.clear();
//        this.repaint();
    }

    void undoHistory() {
        System.out.println(history.size());
        if (history.size() > 0) {
            history.remove(history.get(history.size()-1)); //Remove history
            VEC.remove(VEC.get(VEC.size()-1)); //Remove VEC
            this.repaint();
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (AllShapes eachShape: history){
            eachShape.draw(g, this.getWidth(), this.getHeight());
        }
        for (Dot eachDot: polygonMarker){
            eachDot.draw(g, this.getWidth(), this.getHeight());
        }
        //         Draws temporary line if user drags line
        if (drag != null && dragging) {
            drag.draw(g, this.getWidth(), this.getHeight());
        }


//        drag = null;

    }

    DrawArea() {
        setBackground(Color.WHITE);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
//                System.out.println(e);
            }
        });
    }



}
