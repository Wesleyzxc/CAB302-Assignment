package MyGUI;

import com.sun.source.util.DocTreeScanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DrawArea extends JPanel {
    private List<Dot> polygonMarker = new LinkedList<>();
    private List<AllShapes> history = new LinkedList<>();
    private List<String> VEC = new LinkedList<>();
    private AllShapes drag;

    public boolean isDragging() {
        return dragging;
    }

    public void setDragging(boolean dragging) {
        this.dragging = dragging;
    }

    private boolean dragging;

    void addShape(AllShapes shape) {
        history.add(shape);
        VEC.add(shape.getVEC());
        System.out.println(VEC);
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
            history.remove(history.get(history.size()-1));
            this.repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (AllShapes eachShape: history){
            eachShape.draw(g);
        }
        for (Dot eachDot: polygonMarker){
            eachDot.draw(g);
        }
        //         Draws temporary line if user drags line
        if (drag != null && dragging) {
            drag.draw(g);
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
