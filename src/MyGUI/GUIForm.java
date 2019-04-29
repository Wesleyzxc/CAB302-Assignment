package MyGUI;

import javax.swing.*;

public class GUIForm extends JFrame implements Runnable {
    private JPanel TopPanel;
    private JToolBar MainToolBar;

    private static void createGUI() {
        // Set up window
        JFrame mainFrame = new JFrame("VEC Drawer");
        mainFrame.setContentPane(new GUIForm().MainToolBar);
        mainFrame.setContentPane(new GUIForm().TopPanel);

        mainFrame.pack();
        mainFrame.setVisible(true);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void run() {
        createGUI();
    }

    public static void main(String[] args) {
        GUIForm TopForm = new GUIForm();
        SwingUtilities.invokeLater((Runnable) new GUIForm());
    }


}
