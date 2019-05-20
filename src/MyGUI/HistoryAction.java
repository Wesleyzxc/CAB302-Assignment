package MyGUI;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;

public class HistoryAction implements MenuListener {
    List history = new LinkedList();
    List previousHistory;
    DrawArea panel;
    public HistoryAction(MenuEvent e, DrawArea panel){
        this.previousHistory = panel.getHistory();
        this.panel = panel;
        for (Object s :panel.getHistory()){
            System.out.println("a");
            //historyButton.add(new JButton((String)s));
        }
    }
    @Override
    public void menuSelected(MenuEvent e) {
        System.out.println("menuSelected");

    }

    @Override
    public void menuDeselected(MenuEvent e) {
        System.out.println("menuDeselected");

    }

    @Override
    public void menuCanceled(MenuEvent e) {
        System.out.println("menuCanceled");

    }
        /*
        int counter = 0;
        //panel.clearHistory();
            for (Object s: this.previousHistory) {
                counter++;
                if (counter <= 2){
                    history.add(s);
                }

            }
            panel.setHistory(history);
            System.out.println(panel.getHistory().size());
            panel.repaint();

     */
}


