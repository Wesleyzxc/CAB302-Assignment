package MyGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * An action listener that will accept a DrawArea as parameter clear all existing history and creates new canvas
 */
public class ClearAction implements ActionListener {
    private DrawArea panel;

    /**
     * An action listener that will accept a DrawArea as parameter clear all existing history and creates new canvas
     * @param panel DrawArea panel that has drawings
     */
    ClearAction(DrawArea panel) {
        this.panel = panel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // sets result to no in the beginning
        int dialogResult = JOptionPane.NO_OPTION;

        if (panel.getHistory().size() != 0) {
            dialogResult = JOptionPane.showConfirmDialog(null, "New file will clear current drawings, continue?", "Warning", JOptionPane.YES_NO_OPTION);
        }
        if (dialogResult == JOptionPane.YES_OPTION) {
            panel.clearVEC();
            panel.clearHistory();
            panel.repaint();
        }
    }
}
