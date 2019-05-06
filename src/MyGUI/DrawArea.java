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
    private Line drag;

    void addDot(Dot dot) {
        dots.add(dot);
        this.repaint();
    }

    void addLine(Line line) {
        lines.add(line);
        this.repaint();
    }

    void dragLine(Line line){
        drag = line;
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Dot eachDot: dots) {
            eachDot.draw(g);
        }
        for (Line eachLine: lines){
            eachLine.draw(g, eachLine.getX1(),eachLine.getY1(),eachLine.getX2(),eachLine.getY2());
        }
        // Draws temporary line if user drags line
        if (drag != null) {
            drag.draw(g, drag.getX1(), drag.getY1(), drag.getX2(), drag.getY2());
        }
    }

    DrawArea() {
        setBackground(Color.BLUE);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                System.out.println(e);
            }
        });
    }



}
