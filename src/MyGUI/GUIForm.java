package MyGUI;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.Border;
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

        //Draw area
        DrawArea panel = new DrawArea();


        final JPanel container = new JPanel(new FlowLayout());
        container.add(panel, BorderLayout.CENTER);
        container.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizePreview(panel, container);
            }
        });
        frame.add(container);

        //Display window
        frame.setPreferredSize(new Dimension(600, 500));
        frame.setLocation(new Point(300, 300));
        frame.pack();
        frame.setVisible(true);

        // Add scroll pane to panel
        JScrollPane scrollPane = new JScrollPane(panel);
//        scrollPane.getViewport().setPreferredSize(new Dimension(400, 400));
//        scrollPane.getViewport().addChangeListener(e -> container.repaint());
//        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        scrollPane.setSize(300,300);
//        scrollPane.setBounds(5, 5, 50, 50);
        container.add(scrollPane);

        //menu bar and items
        JMenuBar MenuBar = new JMenuBar();

        JMenu help = new JMenu("Help");
        JMenu file = new JMenu("File");
        // Open menu bar
        JMenuItem open = new JMenuItem("Open File");
        file.add(open);
        open.addActionListener(new OpenAction(panel));
        open.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        // Save menu bar
        JMenuItem save = new JMenuItem("Save File");
        file.add(save);
        save.addActionListener(new SaveAction(panel));
        save.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        // Undo menu bar
        JMenuItem undo = new JMenuItem("Undo");
        file.add(undo);
        undo.addActionListener(new UndoAction(panel));
        undo.setAccelerator(KeyStroke.getKeyStroke('Z', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        // Exit menu bar
        JMenuItem exit = new JMenuItem("Exit");
        file.add(exit);
        exit.addActionListener(new ExitAction());

        // shortcut key
        frame.setJMenuBar(MenuBar);
        MenuBar.add(file);
        MenuBar.add(help);

        //JToolbar
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
        JButton historyButton = new JButton("History");

        // Bottom toolbar for zoom
        JToolBar btmToolbar = new JToolBar();
        JButton zoomIn = new JButton("+");
        JButton zoomOut = new JButton("-");
        btmToolbar.add(zoomIn);
        btmToolbar.add(zoomOut);
        zoomIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        zoomOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        frame.add(btmToolbar, BorderLayout.SOUTH);




        //Create DrawObjectListener
        DrawObjectListener handler = new DrawObjectListener(panel);
        // MouseClick and others require MouseListener
        panel.addMouseListener(handler);
        // MouseDragged requires MouseMotionListener
        panel.addMouseMotionListener(handler);

        undoButton.addActionListener(new UndoAction(panel));
        historyButton.addActionListener(new HistoryAction(panel));
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
        toolbar.add(historyButton);
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


    private static void resizePreview(JPanel innerPanel, JPanel container) {
        int w = container.getWidth();
        int h = container.getHeight();
        int size =  Math.min(w, h);
        System.out.println(size);
        innerPanel.setPreferredSize(new Dimension(size, size));
        container.revalidate();
        innerPanel.removeAll();
        innerPanel.repaint();
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
