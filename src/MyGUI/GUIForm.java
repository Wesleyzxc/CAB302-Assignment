package MyGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class GUIForm{

    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    private static void createGUI() {
        // Create and set up window
        boolean showPolyInstructions = true;
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

        //menu bar and items
        JMenuBar MenuBar = new JMenuBar();

        JMenu help = new JMenu("Help");
        JMenuItem about = new JMenuItem("About");
        about.addActionListener(new AboutAction());
        help.add(about);

        JMenu file = new JMenu("File");
        // New menu bar
        JMenuItem clear = new JMenuItem("New File");
        file.add(clear);
        clear.addActionListener(new ClearAction(panel));
        clear.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
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

        //History support
        JMenu historyButton = new JMenu("History");
        historyButton.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent arg0) {

                historyButton.removeAll();
                java.util.List<String> VEC  = panel.getAllVEC();
                int counter = 1;
                for (AllShapes shape :panel.getHistory()){
                    JMenuItem revertItem = new JMenuItem(counter + ": " + shape.getShape());
                    revertItem.setName(String.valueOf(counter));
                    historyButton.add(revertItem);
                    revertItem.addActionListener(new HistoryAction(panel, revertItem.getName()));
                    counter++;
                }
            }
            @Override
            public void menuDeselected(MenuEvent e) {
            }

            @Override
            public void menuCanceled(MenuEvent e) {
            }

        });
        MenuBar.add(historyButton);

        //JToolbar
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        JButton lineButton = new JButton("Line");
        lineButton.setEnabled(false); // initial state is line
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

        // array of all buttons
        JButton[] shapeButtons = new JButton[]{lineButton, rectButton, ellipseButton, polyButton};

        ShapeChooser chooseShape = new ShapeChooser(handler, lineButton, rectButton, ellipseButton, polyButton);
        lineButton.addActionListener(chooseShape);
        lineButton.addActionListener(new ButtonEnable(lineButton, shapeButtons));
        lineButton.setMnemonic(KeyEvent.VK_1);
        lineButton.setToolTipText("Alt + 1 ");

        rectButton.addActionListener(chooseShape);
        rectButton.addActionListener(new ButtonEnable(rectButton, shapeButtons));
        rectButton.setMnemonic(KeyEvent.VK_2);
        rectButton.setToolTipText("Alt + 2 ");

        ellipseButton.addActionListener(chooseShape);
        ellipseButton.addActionListener(new ButtonEnable(ellipseButton, shapeButtons));
        ellipseButton.setMnemonic(KeyEvent.VK_3);
        ellipseButton.setToolTipText("Alt + 3 ");

        polyButton.addActionListener(chooseShape);
        polyButton.addActionListener(new ButtonEnable(polyButton, shapeButtons));
        polyButton.setMnemonic(KeyEvent.VK_4);
        polyButton.setToolTipText("<html>Alt + 4 <br> Left click to set points, right click to draw</html>");
    }


    private static void resizePreview(JPanel innerPanel, JPanel container) {
        int w = container.getWidth();
        int h = container.getHeight();
        int size =  Math.min(w, h);
        innerPanel.setPreferredSize(new Dimension(size-10, size-10));
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
