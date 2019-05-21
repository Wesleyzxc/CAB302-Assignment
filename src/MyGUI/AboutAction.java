package MyGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutAction implements ActionListener {
    private JMenuItem about;
    private String message = "This is the assignment for CAB302 Sem 1 completed by Daryl Tan and Wesley Kok.\n" +
            "For bug reports and job offers please submit to 9972676@qut.edu.au and 9930141@qut.edu.au :)";

    /**
     * Initialising with a component which acts as a parent component for message dialogue.
     *
     * @param about JMenuItem that is the component used for message dialogue.
     **/
    AboutAction(JMenuItem about){
        this.about = about;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(about, message);
    }
}
