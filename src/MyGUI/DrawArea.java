package MyGUI;

import com.sun.source.util.DocTreeScanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DrawArea extends JPanel {
    private ArrayList<ArrayList<Integer>> coordinates = new ArrayList<>();
    private ArrayList<Integer> x_coords = new ArrayList<>();
    private ArrayList<Integer> y_coords = new ArrayList<>();
    private List<Dot> dots = new LinkedList<Dot>();

    public void addDot(Dot dot) {
        dots.add(dot);
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        for (Dot eachDot: dots) {
            eachDot.draw(g);
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
                System.out.println(coordinates);
                addDot(new Dot(e.getX(), e.getY(), 10, Color.BLACK));
            }

        });
    }



}
