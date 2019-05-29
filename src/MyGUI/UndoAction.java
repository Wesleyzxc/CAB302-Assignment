package MyGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UndoAction implements ActionListener {
    private DrawArea panel;

    /**
     * ActionListener for undo action
     * @param panel
     */
    public UndoAction(DrawArea panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(panel.getHistory().size() == 0) {
            JOptionPane.showMessageDialog(null, "There is nothing to undo!");
        }
        panel.undoHistory();

    }
}