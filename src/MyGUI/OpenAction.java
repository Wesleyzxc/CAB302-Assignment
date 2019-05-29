package MyGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.desktop.SystemEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;

public class OpenAction implements ActionListener {
    private DrawArea panel;
    Color penColor = new Color(00,00,00);
    Color fillColor = new Color(00,00,00);

    /**
     * ActionListener for opening file
     * @param panel
     */
    public OpenAction(DrawArea panel) {
        this.panel = panel;
    }

    /**
     * Warns user if canvas is not empty, then loads from selected file otherwise.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult;
        if (panel.getHistory().size() != 0) {
            dialogResult = JOptionPane.showConfirmDialog(null, "Opening a new drawing will overwrite the current drawing, continue?", "Warning", dialogButton);
        }
        else { dialogResult = JOptionPane.YES_OPTION;}
        if (dialogResult == JOptionPane.YES_OPTION) {
            final JFileChooser fileChooser = new JFileChooser();
            int returnVal = fileChooser.showOpenDialog(fileChooser);
            //these variables keep track of color changes
            boolean changedPEN = false;
            boolean changedFILL = false;
            boolean toggleFill = false;
            boolean changedTOGGLE = false;
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
//            String filePath = file.getAbsolutePath();
                try {
                    if (!(file.toString().contains(".vec"))&& !(file.toString().contains(".VEC"))) {
                        System.out.println("Wrong format");
                        JOptionPane.showMessageDialog(null, "This file type is not supported. Please select a VEC file.");
                    }
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    panel.clearHistory();
                    String eachLine;
                    while ((eachLine = reader.readLine()) != null) {
                        try {
                            if (eachLine.contains("POLYGON")) {
                                String[] array = eachLine.split(" ");
                                int x[] = new int[array.length / 2]; // 8/2 = 4
                                int y[] = new int[array.length / 2]; // 8/2 = 4
                                for (int i = 1; i < array.length; i++) {
                                    try {
                                        if (i % 2 == 1) {
                                            x[i / 2] = (int) (Double.parseDouble(array[i]) * this.panel.getWidth());
                                        }
                                        if (i % 2 == 0) {
                                            y[i / 2 - 1] = (int) (Double.parseDouble(array[i]) * this.panel.getHeight());
                                        }
                                    } catch (ArrayIndexOutOfBoundsException a){
                                        JOptionPane.showMessageDialog(null, "Polygon has corrupted coordinates Check the commands! ");
                                        break;
                                    }
                                }
                                panel.addShape(new PolygonShape(x, y, penColor, fillColor, toggleFill, panel.getWidth(), panel.getHeight()), changedPEN, changedFILL, changedTOGGLE);
                            } else if (eachLine.contains("PEN")) {
                                String[] array = eachLine.split(" ");
                                StringBuilder sb = new StringBuilder(array[1]);
                                penColor = Color.decode(sb.toString());
                                changedPEN = true;
                            } else if (eachLine.contains("FILL") && !eachLine.contains("OFF")) {
                                String[] array = eachLine.split(" ");
                                StringBuilder sb = new StringBuilder(array[1]);
                                fillColor = Color.decode(sb.toString());
                                changedFILL = false;
                                changedPEN = false;
                                changedTOGGLE = false;
                                toggleFill = true;
                            } else if (eachLine.contains("FILL OFF")) {
                                String[] array = eachLine.split(" ");
                                changedTOGGLE = true;
                                toggleFill = false;
                            } else if (eachLine.contains("LINE")) {
                                String[] array = eachLine.split(" ");
                                System.out.println("split array");
                                try {
                                    int[] x = {(int) (Double.parseDouble(array[1]) * this.panel.getWidth()), (int) (Double.parseDouble(array[3]) * this.panel.getWidth())};
                                    int[] y = {(int) (Double.parseDouble(array[2]) * this.panel.getWidth()), (int) (Double.parseDouble(array[4]) * this.panel.getWidth())};
                                    panel.addShape(new Line(x, y, penColor, panel.getWidth(), panel.getHeight()), changedPEN, changedFILL, changedTOGGLE);
                                    changedFILL = false;
                                    changedPEN = false;
                                    changedTOGGLE = false;
                                } catch (ArrayIndexOutOfBoundsException a){
                                    JOptionPane.showMessageDialog(null, "Line has corrupted coordinates Check the commands! ");
                                    break;
                                }
                            } else if (eachLine.contains("RECTANGLE")) {
                                String[] array = eachLine.split(" ");
                                System.out.println("split array");
                                try {
                                    int[] x = {(int) (Double.parseDouble(array[1]) * this.panel.getWidth()), (int) (Double.parseDouble(array[3]) * this.panel.getWidth())};
                                    int[] y = {(int) (Double.parseDouble(array[2]) * this.panel.getWidth()), (int) (Double.parseDouble(array[4]) * this.panel.getWidth())};
                                    panel.addShape(new Rectangle(x, y, penColor, fillColor, toggleFill, panel.getWidth(), panel.getHeight()), changedPEN, changedFILL, changedTOGGLE);
                                    changedFILL = false;
                                    changedPEN = false;
                                    changedTOGGLE = false;
                                } catch (ArrayIndexOutOfBoundsException a){
                                    JOptionPane.showMessageDialog(null, "Rectangle has corrupted coordinates Check the commands! ");
                                    break;
                                }
                            } else if (eachLine.contains("ELLIPSE")) {
                                String[] array = eachLine.split(" ");
                                System.out.println("split array");
                                try {
                                    int[] x = {(int) (Double.parseDouble(array[1]) * this.panel.getWidth()), (int) (Double.parseDouble(array[3]) * this.panel.getWidth())};
                                    int[] y = {(int) (Double.parseDouble(array[2]) * this.panel.getWidth()), (int) (Double.parseDouble(array[4]) * this.panel.getWidth())};
                                    panel.addShape(new Ellipse(x, y, penColor, fillColor, toggleFill, panel.getWidth(), panel.getHeight()), changedPEN, changedFILL, changedTOGGLE);
                                    changedFILL = false;
                                    changedPEN = false;
                                    changedTOGGLE = false;
                                } catch (ArrayIndexOutOfBoundsException a){
                                    JOptionPane.showMessageDialog(null, "Ellipse has corrupted coordinates Check the commands! ");
                                    break;
                                }
                            } else if (eachLine.contains("PLOT")) {
                                String[] array = eachLine.split(" ");
                                System.out.println("split array");
                                try {
                                    int[] x = {(int) (Double.parseDouble(array[1]) * this.panel.getWidth())};
                                    int[] y = {(int) (Double.parseDouble(array[2]) * this.panel.getWidth())};
                                    panel.addShape(new Dot(x, y,10, penColor, panel.getWidth(), panel.getHeight()), changedPEN, changedFILL, changedTOGGLE);
                                    changedFILL = false;
                                    changedPEN = false;
                                    changedTOGGLE = false;
                                } catch (ArrayIndexOutOfBoundsException a){
                                    JOptionPane.showMessageDialog(null, "Plot has corrupted coordinates Check the commands! " + eachLine);
                                    break;
                                }
                            } else {
                                throw new vecException(eachLine);
                            }
                        } catch (vecException wrongFormat) {
                            JOptionPane.showMessageDialog(null, "This is a corrupted VEC file. Check the commands! " + wrongFormat);
                            break;
                        }
                    }
                } catch (FileNotFoundException noFile) {
                    noFile.printStackTrace();
                } catch (IOException ioError) {
                    ioError.printStackTrace();
                }

            } else if (returnVal == JFileChooser.CANCEL_OPTION) {

            }
        }
    }
}
