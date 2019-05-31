package MyGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutAction implements ActionListener {
    private String message = "This is the assignment for CAB302 Sem 1 completed by Daryl Tan and Wesley Kok.\n" +
            "For bug reports and job offers please submit to n9972676@qut.edu.au and n9930141@qut.edu.au :)";


    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, message);
    }
}
