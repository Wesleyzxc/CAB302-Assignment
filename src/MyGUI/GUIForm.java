package MyGUI;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class GUIForm{

    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    private static void createGUI() {
        // Create and set up window

        JFrame frame = new JFrame("VEC DRAWER");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Display window
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setLocation(new Point(300, 300));
        frame.pack();
        frame.setVisible(true);

        //menu bar and items


        JMenuBar MenuBar = new JMenuBar();

        JMenu help = new JMenu("Help");
        JMenu file = new JMenu("File");
        JMenuItem save = new JMenuItem("Save File");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem open = new JMenuItem("Open File");
        JMenuItem undo = new JMenuItem("Undo");
        // shortcut key
        open.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));;
        frame.setJMenuBar(MenuBar);


        MenuBar.add(file);

        file.add(open);
        file.add(save);
        file.add(exit);
        file.add(undo);

        MenuBar.add(help);
        class exitAction implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }

        class openAction implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(fc);
                if(returnVal==JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    String filename = file.getAbsolutePath();
                } else if(returnVal==JFileChooser.CANCEL_OPTION) {
                }
            }
        }


        open.addActionListener(new openAction());
        exit.addActionListener(new exitAction());

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


        //Draw area
        DrawArea panel = new DrawArea();
        frame.add(panel);
        //Create DrawObjectListener
        DrawObjectListener handler = new DrawObjectListener(panel);
        // MouseClick and others require MouseListener
        panel.addMouseListener(handler);
        // MouseDragged requires MouseMotionListener
        panel.addMouseMotionListener(handler);

        class undoAction implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                panel.undoHistory();
            }
        }
        undoButton.addActionListener(new undoAction());
        undo.addActionListener(new undoAction());
        // Ctrl + Z shortcut
        undo.setAccelerator(KeyStroke.getKeyStroke('Z', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));;



        class saveAction implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {

                BufferedImage bImg = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D cg = bImg.createGraphics();
                panel.paintAll(cg);
                try {
                    if (ImageIO.write(bImg, "png", new File("./output_image.png")))
                    {
                        System.out.println("-- saved");
                    }
                } catch (IOException a) {
                    // TODO Auto-generated catch block
                    a.printStackTrace();
                }

            }
        }

        save.addActionListener(new saveAction());


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
