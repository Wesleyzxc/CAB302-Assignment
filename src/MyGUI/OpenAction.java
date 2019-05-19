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
    Color penColor = new Color(00,00,00);
    Color fillColor = new Color(00,00,00);

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
                        int x[] = new int[array.length/2]; // 8/2 = 4
                        int y[] = new int[array.length/2]; // 8/2 = 4
                        for (int i = 1; i < array.length; i++){
                            if (i%2 == 1){
                                x[i/2] = (int)(Double.parseDouble(array[i])*this.panel.getWidth());
                            }
                            if (i%2 == 0){
                                y[i/2-1] = (int)(Double.parseDouble(array[i])*this.panel.getHeight());
                            }

                        }
                        panel.addShape(new PolygonShape(x, y, penColor, fillColor, true, panel.getWidth(), panel.getHeight()), false, false);
                    }
                    if (eachLine.contains("PEN")){
                        String[] array = eachLine.split(" ");
                        StringBuilder sb = new StringBuilder(array[1]);
                        penColor = Color.decode(sb.toString());
                    }
                    if (eachLine.contains("FILL")){
                        String[] array = eachLine.split(" ");
                        StringBuilder sb = new StringBuilder(array[1]);

                        StringBuilder r = new StringBuilder();
                        StringBuilder b = new StringBuilder();
                        StringBuilder g = new StringBuilder();
                        fillColor = Color.decode(sb.toString());
                    }
                    if (eachLine.contains("LINE")){
                        String[] array = eachLine.split(" ");
                        System.out.println("split array");
                        int[] x = {(int)(Double.parseDouble(array[1])*this.panel.getWidth()), (int)(Double.parseDouble(array[3])*this.panel.getWidth())};
                        int[] y = {(int)(Double.parseDouble(array[2])*this.panel.getWidth()), (int)(Double.parseDouble(array[4])*this.panel.getWidth())};
                        panel.addShape(new Line(x, y, penColor, panel.getWidth(), panel.getHeight()), false, false);
                    } else if (eachLine.contains("RECTANGLE")){
                        String[] array = eachLine.split(" ");
                        System.out.println("split array");
                        int[] x = {(int)(Double.parseDouble(array[1])*this.panel.getWidth()), (int)(Double.parseDouble(array[3])*this.panel.getWidth())};
                        int[] y = {(int)(Double.parseDouble(array[2])*this.panel.getWidth()), (int)(Double.parseDouble(array[4])*this.panel.getWidth())};
                        panel.addShape(new Rectangle(x, y, penColor, fillColor, true, panel.getWidth(), panel.getHeight()), false, false);
                    } else if (eachLine.contains("ELLIPSE")){
                        String[] array = eachLine.split(" ");
                        System.out.println("split array");
                        int[] x = {(int)(Double.parseDouble(array[1])*this.panel.getWidth()), (int)(Double.parseDouble(array[3])*this.panel.getWidth())};
                        int[] y = {(int)(Double.parseDouble(array[2])*this.panel.getWidth()), (int)(Double.parseDouble(array[4])*this.panel.getWidth())};
                        panel.addShape(new Ellipse(x, y, penColor, fillColor, true, panel.getWidth(), panel.getHeight()), false, false);
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
