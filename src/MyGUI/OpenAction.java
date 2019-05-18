package MyGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.desktop.SystemEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;

public class OpenAction implements ActionListener {
    private DrawArea panel;

    public OpenAction(DrawArea panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showOpenDialog(fileChooser);
        if(returnVal==JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
//            String filePath = file.getAbsolutePath();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String eachLine;
                while ((eachLine = reader.readLine()) != null) {
//                    System.out.println(eachLine);
                    if (eachLine.contains("POLYGON")){
                        String[] array = eachLine.split(" ");
//                        System.out.println(array[1]);
                    }
                    if (eachLine.contains("LINE")){
                        String[] array = eachLine.split(" ");
                        System.out.println("split array");
                        int[] x = {(int)(Double.parseDouble(array[1])*this.panel.getWidth()), (int)(Double.parseDouble(array[3])*this.panel.getWidth())};
                        int[] y = {(int)(Double.parseDouble(array[2])*this.panel.getWidth()), (int)(Double.parseDouble(array[4])*this.panel.getWidth())};
                        panel.addShape(new Line(x, y, Color.BLACK, panel.getWidth(), panel.getHeight()), false, false);
                    }



                }
            } catch (FileNotFoundException noFile) {
                noFile.printStackTrace();
            } catch (IOException ioError) {
                ioError.printStackTrace();
            }

        } else if(returnVal==JFileChooser.CANCEL_OPTION) {

        }
    }
}
