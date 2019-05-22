package MyGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HistoryAction2 implements ActionListener {
    private String itemCount;
    private List previousHistory;
    private DrawArea panel;


    HistoryAction2(DrawArea panel, String itemCount) {
        this.previousHistory = panel.getHistory();
        this.panel = panel;
        this.itemCount = itemCount;
    }

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