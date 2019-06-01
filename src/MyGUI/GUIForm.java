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
        frame.setPreferredSize(new Dimension(600, 500));
        frame.setLocation(new Point(300, 300));
        frame.pack();
        frame.setVisible(true);

        //Draw area, empty canvas
        DrawArea panel = new DrawArea();
        // Container for the canvas which is used for resizing
        final JPanel container = new JPanel(new FlowLayout());
        container.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizePreview(panel, container);
            }
        });



        // Menu bar and items
        JMenuBar MenuBar = new JMenuBar();


        //Menu "Help" content
        JMenu help = new JMenu("Help");
        JMenuItem about = new JMenuItem("About");
        about.addActionListener(new AboutAction());


        // Menu "File" content
        // File menu with main functionality
        JMenu file = new JMenu("File");

        // New menu item
        JMenuItem clear = new JMenuItem("New File");
        clear.addActionListener(new ClearAction(panel));
        clear.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        // Open menu item
        JMenuItem open = new JMenuItem("Open File");
        open.addActionListener(new OpenAction(panel));
        open.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        // Save menu item
        JMenuItem save = new JMenuItem("Save File");
        save.addActionListener(new SaveAction(panel));
        save.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        // Undo menu item
        JMenuItem undo = new JMenuItem("Undo");
        undo.addActionListener(new UndoAction(panel));
        undo.setAccelerator(KeyStroke.getKeyStroke('Z', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        // Exit menu item
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ExitAction());



        // Menu "History" content
        JMenu historyButton = new JMenu("History");
        historyButton.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent arg0) {
                historyButton.removeAll();
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



        // Button section
        //JToolbar with all the buttons created
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

        // Colour buttons receives output colour
        penColourButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JColorChooser colour = new JColorChooser();
                String name = JOptionPane.showInputDialog(colour);
                // null if user cancels
                if (name != null ) {
                    handler.setPenColour(colour.getColor());
                }

            }
        });
        fillColourButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JColorChooser colour = new JColorChooser();
                String name = JOptionPane.showInputDialog(colour);
                // null if user cancels
                if (name != null) {
                    handler.toggleFill(true);
                    handler.setFillColour(colour.getColor());
                }
            }
        });
        clearFillButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to off fill?");
                if (response == 0) { handler.toggleFill(false); }
            }
        });



        // array of all buttons of shapes
        JButton[] shapeButtons = new JButton[]{lineButton, rectButton, ellipseButton, polyButton};
        ShapeChooser chooseShape = new ShapeChooser(handler, lineButton, rectButton, ellipseButton, polyButton);
        // Each button selector
        lineButton.addActionListener(chooseShape);
        lineButton.setMnemonic(KeyEvent.VK_1);
        lineButton.setToolTipText("Alt + 1 ");

        rectButton.addActionListener(chooseShape);
        rectButton.setMnemonic(KeyEvent.VK_2);
        rectButton.setToolTipText("Alt + 2 ");

        ellipseButton.addActionListener(chooseShape);
        ellipseButton.setMnemonic(KeyEvent.VK_3);
        ellipseButton.setToolTipText("Alt + 3 ");

        polyButton.addActionListener(chooseShape);
        polyButton.setMnemonic(KeyEvent.VK_4);
        polyButton.setToolTipText("<html>Alt + 4 <br> Left click to set points, right click to draw</html>");

        // Disable button if button is selected
        for(JButton eachButton: shapeButtons){
            eachButton.addActionListener(new ButtonEnable(eachButton, shapeButtons));
        }


        // Adds every component to GUI
        frame.add(container);
        container.add(panel, BorderLayout.CENTER);
        file.add(clear);
        file.add(open);
        file.add(save);
        file.add(undo);
        file.add(exit);
        help.add(about);
        // Base for MenuBar
        frame.setJMenuBar(MenuBar);
        MenuBar.add(file);
        MenuBar.add(help);
        MenuBar.add(historyButton);
        // Add toolbar to frame
        frame.add(toolbar, BorderLayout.NORTH);
        // Add buttons to toolbar
        toolbar.add(lineButton);
        toolbar.add(rectButton);
        toolbar.add(ellipseButton);
        toolbar.add(polyButton);
        toolbar.add(penColourButton);
        toolbar.add(fillColourButton);
        toolbar.add(clearFillButton);
        toolbar.add(undoButton);


    }

    /**
     * Allows resizing of panel based on the container, keeping aspect ratio of inner panel
     * @param innerPanel JPanel that needs to maintian aspect ratio
     * @param container container that innerPanel's size has to obey by
     */
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