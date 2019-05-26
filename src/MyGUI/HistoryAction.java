package MyGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HistoryAction implements ActionListener {
    private String itemCount;
    private List previousHistory;
    private DrawArea panel;

    /**
     * Action carried out for history support
     * @param panel panel that the history support keeps track
     * @param itemCount name of MenuItem of which the shape will be drawn up till.
     */
    HistoryAction(DrawArea panel, String itemCount) {
        this.previousHistory = panel.getHistory();
        this.panel = panel;
        this.itemCount = itemCount;
    }

    /**
     * Sets all previous instances of AllShapes to be visible and anything after to be invisible.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < Integer.parseInt(itemCount); i++) {
            panel.getHistory().get(i).setVisible(true);
        }
        for (int i = Integer.parseInt(itemCount); i < panel.getHistory().size(); i++) {
            panel.getHistory().get(i).setVisible(false);
        }
        panel.repaint();
    }
}