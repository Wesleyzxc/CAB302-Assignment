package MyGUI;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class GUIForm{

    private void setShortcut(JMenuItem menuItem, char shortcut) {
        menuItem.setAccelerator(KeyStroke.getKeyStroke(shortcut, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
    }
    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    private static void createGUI() {
        // Create and set up window
        JFrame frame = new JFrame("VEC DRAWER");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Draw area
        DrawArea panel = new DrawArea();
        frame.add(panel);

        //Display window
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setLocation(new Point(300, 300));
        frame.pack();
        frame.setVisible(true);

        //menu bar and items

        JMenuBar MenuBar = new JMenuBar();

        JMenu help = new JMenu("Help");
        JMenu file = new JMenu("File");
        // Save menu bar
        JMenuItem save = new JMenuItem("Save File");
        file.add(save);
        save.addActionListener(new SaveAction(panel));
        save.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        // Exit menu bar
        JMenuItem exit = new JMenuItem("Exit");
        file.add(exit);
        exit.addActionListener(new ExitAction());
        // Open menu bar
        JMenuItem open = new JMenuItem("Open File");
        file.add(open);
        open.addActionListener(new OpenAction());
        open.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        // Undo menu bar
        JMenuItem undo = new JMenuItem("Undo");
        file.add(undo);
        undo.addActionListener(new UndoAction(panel));
        undo.setAccelerator(KeyStroke.getKeyStroke('Z', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));


        // shortcut key
        frame.setJMenuBar(MenuBar);


        MenuBar.add(file);


        MenuBar.add(help);




        // Trying out JToolbar
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        JButton lineButton = new JButton("Line");
        JButton rectButton = new JButton("Rectangle");
        JButton ellipseButton = new JButton("Ellipse");
        JButton polyButton = new JButton("Polygons");
        JButton penColourButton = new JButton("Pen colour");
        JButton fillColourButton = new JButton("Fill colour");
        JButton clearFillButton = new JButton("Fill off");
        JButton undoButton = new JButton("Undo");



        //Create DrawObjectListener
        DrawObjectListener handler = new DrawObjectListener(panel);
        // MouseClick and others require MouseListener
        panel.addMouseListener(handler);
        // MouseDragged requires MouseMotionListener
        panel.addMouseMotionListener(handler);

        undoButton.addActionListener(new UndoAction(panel));
        // Ctrl + Z shortcut





        //Add shapes to toolbar
        toolbar.add(lineButton);
        toolbar.add(rectButton);
        toolbar.add(ellipseButton);
        toolbar.add(polyButton);
        toolbar.add(penColourButton, BorderLayout.EAST);
        toolbar.add(fillColourButton, BorderLayout.EAST);
        toolbar.add(clearFillButton);
        toolbar.add(undoButton);
        penColourButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JColorChooser colour = new JColorChooser();
                String name = JOptionPane.showInputDialog(colour);
                handler.setPenColour(colour.getColor());
                //System.out.println(colour.getColor());
            }
        });
        fillColourButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handler.toggleFill(true);
                JColorChooser colour = new JColorChooser();
                String name = JOptionPane.showInputDialog(colour);
                handler.setFillColour(colour.getColor());
            }
        });
        clearFillButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handler.toggleFill(false);
            }
        });

//        toolbar.add(new JColorChooser());
        frame.add(toolbar, BorderLayout.NORTH);


        //Create separate class later
        class chooseShapeAction implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == lineButton){
                    handler.chooseShape(DrawObjectListener.Shape.LINE);
                    System.out.println(handler.getShape());
                }
                if (e.getSource() == rectButton){
                    handler.chooseShape(DrawObjectListener.Shape.RECTANGLE);
                    System.out.println(handler.getShape());
                }
                if (e.getSource() == ellipseButton){
                    handler.chooseShape(DrawObjectListener.Shape.ELLIPSE);
                    System.out.println(handler.getShape());
                }
                if (e.getSource() == polyButton){
                    handler.chooseShape(DrawObjectListener.Shape.POLYGON);
                    System.out.println(handler.getShape());
                }

            }
        }

        chooseShapeAction chooseShape = new chooseShapeAction();
        lineButton.addActionListener(chooseShape);
        rectButton.addActionListener(chooseShape);
        ellipseButton.addActionListener(chooseShape);
        polyButton.addActionListener(chooseShape);
    }




    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        });
    }


}
