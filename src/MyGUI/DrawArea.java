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
    private Line drag;

    public boolean isDragging() {
        return dragging;
    }

    public void setDragging(boolean dragging) {
        this.dragging = dragging;
    }

    private boolean dragging;

    void addShape(AllShapes shape) {
        history.add(shape);
        System.out.println("addshape");
        this.repaint();
    }

    void dragLine(Line line){
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

    void removeEach(List<?> individualArrays) {
        System.out.println(history.size());
//        if (individualArrays.contains(history.get(history.size()-1))) {
//            individualArrays.remove(individualArrays.get(individualArrays.indexOf(history.size()-1)));
//        }
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
