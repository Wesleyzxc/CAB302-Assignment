package MyGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;

public class HistoryAction implements ActionListener{
    List history = new LinkedList();
    List previousHistory;
    DrawArea panel;
    public HistoryAction(DrawArea panel){
        this.previousHistory = panel.getHistory();
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        int counter = 0;
        //panel.clearHistory();
            for (Object s: this.previousHistory) {
                counter++;
                if (counter <= 2){
                    history.add(s);
                }

            }
            panel.setHistory(history);
            System.out.println(history.size());
            panel.repaint();

        }
    }


