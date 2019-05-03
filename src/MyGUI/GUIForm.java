package MyGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUIForm extends JFrame implements Runnable {

    private GUIForm() {
        super("VEC Drawer");
    }

    private void createGUI() {

        // Set up window
        this.setPreferredSize(new Dimension(400, 400));
        this.setLocation(new Point(300, 300));
        JMenuBar MenuBar = new JMenuBar();
        this.setJMenuBar(MenuBar);
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

        exit.addActionListener(new exitAction());

        DrawArea panel = new DrawArea();
        this.add(panel, BorderLayout.CENTER);


        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
    }

    @Override
    public void run() {
        createGUI();
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new GUIForm());
    }


}
