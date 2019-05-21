package MyGUI;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;

public class HistoryAction implements ActionListener {
    List<AllShapes> history = new LinkedList();
    List previousHistory;
    DrawArea panel;
    Object shape;

    public HistoryAction(DrawArea panel, AllShapes shape){
        this.previousHistory = panel.getHistory();
        this.panel = panel;
        this.shape = shape;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*for (AllShapes s :panel.getHistory()){
            history.add(s);
            if (s == shape){
                break;
            }
        }
        panel.setHistory(history);*/
            System.out.println(shape);
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


