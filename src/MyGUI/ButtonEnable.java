package MyGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonEnable extends AbstractAction {
    private JButton clicked;
    private JButton[] remaining;

    /**
     * Button that is selected is disabled and the rest of the buttons are enabled
     * @param clicked button that is clicked that has to be disabled
     * @param remaining other buttons that has to be enabled
     */
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
