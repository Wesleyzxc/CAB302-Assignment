package MyGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonEnable extends AbstractAction {
    private JButton clicked;
    private JButton[] remaining;

    public ButtonEnable(JButton clicked, JButton[] remaining){
        this.clicked = clicked;
        this.remaining = remaining;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
            for(JButton eachButton: remaining){
                if (eachButton == clicked){
                    eachButton.setEnabled(false);
                }
                else eachButton.setEnabled(true);
            }


    }
}
