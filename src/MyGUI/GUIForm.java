package MyGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIForm extends JFrame implements Runnable {
    private JPanel Panel;

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

        JPanel panel = new JPanel();
        this.add(panel, BorderLayout.CENTER);
        panel.setBackground(Color.BLACK); // black so that it's obvious for now
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println(e);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println(e);
            }
        });

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void run() {
        createGUI();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new GUIForm());
    }


}
