package MyGUI;

import com.sun.source.util.DocTreeScanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DrawArea extends JPanel {
    private List<Dot> dots = new LinkedList<>();
    private List<Line> lines = new LinkedList<>();
    private List<Rectangle> rects = new LinkedList<>();
    private List<Ellipse> ovals = new LinkedList<>();
    private List<PolygonShape> polys = new LinkedList<>();
    private List<Dot> polygonMarker = new LinkedList<>();
    private Line drag;

    void addDot(Dot dot) {
        dots.add(dot);
        this.repaint();
    }

    void addShape(Line line) {
        lines.add(line);
        this.repaint();
    }

    void dragLine(Line line){
        drag = line;
        this.repaint();
    }

    void addShape(Rectangle rect) { //overload
        rects.add(rect);
        this.repaint();
    }

    void addShape(Ellipse ellipse) { //overload
        ovals.add(ellipse);
        this.repaint();
    }

    void addShape(PolygonShape poly) { //overload
        polys.add(poly);
        this.repaint();
    }

    void addMarker(Dot poly) { //overload
        polygonMarker.add(poly);
        this.repaint();
    }

    void clearMarker() { //overload
        polygonMarker.clear();
        this.repaint();
    }



    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Dot eachDot: dots) {
            eachDot.draw(g);
        }
        for (Line eachLine: lines){
            eachLine.draw(g);
        }

        for (Rectangle eachRect: rects){
            eachRect.draw(g);
        }

        for (Ellipse eachOval: ovals){
            eachOval.draw(g);
        }
        for (PolygonShape eachPoly: polys){
            eachPoly.draw(g);
        }
        for (Dot eachDot: polygonMarker){
            eachDot.draw(g);
        }
        // Draws temporary line if user drags line
        if (drag != null) {
            drag.draw(g);
        }
    }

    DrawArea() {
        setBackground(Color.WHITE);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                System.out.println(e);
            }
        });
    }



}
