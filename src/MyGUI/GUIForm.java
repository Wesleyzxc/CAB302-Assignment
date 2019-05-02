package MyGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIForm extends JFrame implements Runnable {
    private JPanel Panel;
    private JMenuBar MenuBar;

    private GUIForm() {
        setSize(400,400);
    }

    private void createGUI() {
        // Set up window
        super.setTitle("VEC Drawer");
        this.setPreferredSize(new Dimension(400, 400));
        this.setLocation(new Point(300, 300));
        JMenuBar MenuBar = new JMenuBar();
        this.setJMenuBar(MenuBar);

        JMenu file = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        MenuBar.add(file);

        file.add(exit);

        class exitAction implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }

        exit.addActionListener(new exitAction());

        JMenu help = new JMenu("Help");

        MenuBar.add(help);

        this.setContentPane(new GUIForm().Panel);
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
