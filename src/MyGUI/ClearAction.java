package MyGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearAction implements ActionListener {
    private DrawArea panel;

    ClearAction(DrawArea panel) {
        this.panel = panel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.NO_OPTION;
        if (panel.getHistory().size() != 0) {
            dialogResult = JOptionPane.showConfirmDialog(null, "New file will clear current drawings, continue?", "Warning", dialogButton);
        }
        if (dialogResult == JOptionPane.YES_OPTION) {
            panel.clearHistory();
            panel.repaint();
        }
    }
}
