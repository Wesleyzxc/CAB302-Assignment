package MyGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener that does undo
 */
public class UndoAction implements ActionListener {
    private DrawArea panel;

    /**
     * ActionListener for undo action
     * @param panel panel that executes undo
     */
    public UndoAction(DrawArea panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(panel.getHistory().size() == 0) {
            JOptionPane.showMessageDialog(null, "There is nothing to undo!");
        }
        else panel.undoHistory();

    }
}