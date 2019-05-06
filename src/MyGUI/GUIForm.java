package MyGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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
        frame.setPreferredSize(new Dimension(400, 400));
        frame.setLocation(new Point(300, 300));
        frame.pack();
        frame.setVisible(true);

        //menu bar and items
        JMenuBar MenuBar = new JMenuBar();
        frame.setJMenuBar(MenuBar);
        JMenu file = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem open = new JMenuItem("Open File");
        JMenuItem save = new JMenuItem("Save File");
        MenuBar.add(file);
        file.add(open);
        file.add(save);
        file.add(exit);
        JMenu help = new JMenu("Help");
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
        JButton colourButton = new JButton("Pen colour");
        colourButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JColorChooser colour = new JColorChooser();
                String name = JOptionPane.showInputDialog(colour);
            }
        });
        toolbar.add(lineButton);
        toolbar.add(rectButton);
        toolbar.add(ellipseButton);
        toolbar.add(colourButton, BorderLayout.EAST);
//        toolbar.add(new JColorChooser());
        frame.add(toolbar, BorderLayout.NORTH);

        //Draw dot
        DrawArea panel = new DrawArea();
        frame.add(panel);
        panel.addMouseListener(new DrawObjectListener(panel));
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
