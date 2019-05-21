package MyGUI;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;

public class HistoryAction2 implements ActionListener {
    String itemCount;
    List previousHistory;
    DrawArea panel;


    public HistoryAction2(DrawArea panel, String itemCount) {
        this.previousHistory = panel.getHistory();
        this.panel = panel;
        this.itemCount = itemCount;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(itemCount);
        for (int i = 0; i < Integer.parseInt(itemCount); i++) {
            panel.getHistory().get(i).setVisible(true);
        }
        for (int i = Integer.parseInt(itemCount); i < panel.getHistory().size(); i++) {
            panel.getHistory().get(i).setVisible(false);
        }
        panel.repaint();
    }
}