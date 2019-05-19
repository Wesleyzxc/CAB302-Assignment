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
//                        System.out.println(array[1]);
                    }
                    if (eachLine.contains("PEN")){
                        String[] array = eachLine.split(" ");
                        StringBuilder sb = new StringBuilder(array[1]);
                        /*
                        StringBuilder r = new StringBuilder();
                        StringBuilder b = new StringBuilder();
                        StringBuilder g = new StringBuilder();
                        sb.deleteCharAt(0);
                        r.append(sb.charAt(0));
                        r.append(sb.charAt(1));
                        g.append(sb.charAt(2));
                        g.append(sb.charAt(3));
                        b.append(sb.charAt(4));
                        b.append(sb.charAt(5));
                        int ri = Integer.parseInt(r.toString());
                        int bi = Integer.parseInt(b.toString());
                        int gi = Integer.parseInt(g.toString());
                        System.out.print(ri);
                        System.out.print(bi);
                        System.out.println(gi);*/
                        //penColor = new Color(ri,bi,gi);
                        penColor = Color.decode(sb.toString());
                    }
                    if (eachLine.contains("FILL")){
                        String[] array = eachLine.split(" ");
                        StringBuilder sb = new StringBuilder(array[1]);
                        /*
                        StringBuilder r = new StringBuilder();
                        StringBuilder b = new StringBuilder();
                        StringBuilder g = new StringBuilder();
                        sb.deleteCharAt(0);
                        r.append(sb.charAt(0));
                        r.append(sb.charAt(1));
                        g.append(sb.charAt(2));
                        g.append(sb.charAt(3));
                        b.append(sb.charAt(4));
                        b.append(sb.charAt(5));
                        int ri = Integer.parseInt(r.toString());
                        int bi = Integer.parseInt(b.toString());
                        int gi = Integer.parseInt(g.toString());
                        System.out.print("c");
                        System.out.print(ri);
                        System.out.print(bi);
                        System.out.println(gi);*/
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
