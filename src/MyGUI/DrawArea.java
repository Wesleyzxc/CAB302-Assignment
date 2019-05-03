package MyGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

class DrawArea extends JPanel {
    private ArrayList<ArrayList<Integer>> coordinates = new ArrayList<>();
    private ArrayList<Integer> x_coords = new ArrayList<>();
    private ArrayList<Integer> y_coords = new ArrayList<>();

    DrawArea() {
        setBackground(Color.BLACK); // black so that it's obvious for now
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x_coords.add(e.getX());
                y_coords.add(e.getY());
                coordinates.clear();
                coordinates.add(x_coords);
                coordinates.add(y_coords);
                System.out.println(coordinates);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println(e);
            }
        });
    }
}
