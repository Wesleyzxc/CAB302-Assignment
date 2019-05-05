package MyGUI;

import com.sun.source.util.DocTreeScanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class DrawArea extends JPanel {
    private ArrayList<ArrayList<Integer>> coordinates = new ArrayList<>();
    private ArrayList<Integer> x_coords = new ArrayList<>();
    private ArrayList<Integer> y_coords = new ArrayList<>();
    private List<Dot> dots = new LinkedList<Dot>();
    private List<Line> lines = new LinkedList<Line>();


    public void addDot(Dot dot) {
        dots.add(dot);
        this.repaint();
    }

    public void addLine(Line line) {
        lines.add(line);
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
    }

    public DrawArea() {
        setBackground(Color.BLUE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                x_coords.add(e.getX());
                y_coords.add(e.getY());
                coordinates.clear();
                coordinates.add(x_coords);
                coordinates.add(y_coords);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

        });
    }



}
