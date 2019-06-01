package MyGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


/**
 * ActionListener for opening files
 */
public class OpenAction implements ActionListener {
    private DrawArea panel;
    private Color penColor = new Color(0, 0,0);
    private Color fillColor = new Color(0,0,0);

    /**
     * ActionListener for opening file
     * @param panel panel that is loading the file
     */
    public OpenAction(DrawArea panel) {
        this.panel = panel;
    }

    /**
     * Reads each line and spits out coordinates for simple shapes
     * @param shape String of shape to parse command
     * @param command line to parse
     * @return array of coordinate for x or y
     */
    private int[][] parseCommand(String shape, String[] command){
        int[][] finalArray ={{},{}};
        if (shape.equals("ELLIPSE") || shape.equals("LINE") || shape.equals("RECTANGLE")) {
            finalArray = new int[][]{{(int) (Double.parseDouble(command[1]) * this.panel.getWidth()), (int) (Double.parseDouble(command[3]) * this.panel.getWidth())},
                    {(int) (Double.parseDouble(command[2]) * this.panel.getWidth()), (int) (Double.parseDouble(command[4]) * this.panel.getWidth())}};
        }
        else if (shape.equals("PLOT")){
            int[] x = {(int) (Double.parseDouble(command[1]) * this.panel.getWidth())};
            int[] y = {(int) (Double.parseDouble(command[2]) * this.panel.getWidth())};
            finalArray = new int[][]{x, y};
        }

        return finalArray;

    }

    /**
     * Warns user if canvas is not empty, then loads from selected file otherwise.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int dialogResult = JOptionPane.YES_OPTION;
        if (panel.getHistory().size() != 0) {
            dialogResult = JOptionPane.showConfirmDialog(null, "Opening a new drawing will overwrite the current drawing, continue?", "Warning", JOptionPane.YES_NO_OPTION);
        }
        if (dialogResult == JOptionPane.YES_OPTION) {
            final JFileChooser fileChooser = new JFileChooser();
            int returnVal = fileChooser.showOpenDialog(fileChooser);
            //these variables keep track of color changes
            boolean changedPEN = false;
            boolean toggleFill = false;
            boolean changedTOGGLE = false;
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    if (!(file.toString().contains(".vec"))&& !(file.toString().contains(".VEC"))) {
                        JOptionPane.showMessageDialog(null, "This file type is not supported. Please select a VEC file.");
                    }
                    else {
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        // creates empty canvas then draw VEC commands
                        panel.clearHistory();
                        panel.clearVEC();

                        String eachLine;
                        while ((eachLine = reader.readLine()) != null) try {
                            if (eachLine.contains("POLYGON")) {
                                String[] array = eachLine.split(" ");
                                if (!array[0].equals("POLYGON")) {
                                    throw new InvalidCommand(eachLine);
                                }
                                int[] x = new int[array.length / 2];
                                int[] y = new int[array.length / 2];
                                for (int i = 1; i < array.length; i++) {
                                    try {
                                        if (i % 2 == 1) {
                                            x[i / 2] = (int) (Double.parseDouble(array[i]) * this.panel.getWidth());
                                        }
                                        if (i % 2 == 0) {
                                            y[i / 2 - 1] = (int) (Double.parseDouble(array[i]) * this.panel.getHeight());
                                        }
                                    } catch (Exception a) {
                                        // shows user which line broke
                                        JOptionPane.showMessageDialog(null, "Your file has corrupted coordinates. Check the following command: \n" + eachLine);
                                        break;
                                    }
                                }
                                panel.addShape(new PolygonShape(x, y, penColor, fillColor, toggleFill, panel.getWidth(), panel.getHeight()), changedPEN, false, changedTOGGLE);
                            } else if (eachLine.contains("PEN")) {
                                String[] array = eachLine.split(" ");
                                if (!array[0].equals("PEN")) {
                                    throw new InvalidCommand(eachLine);
                                }
                                penColor = Color.decode(array[1]);
                                changedPEN = true;
                            } else if (eachLine.contains("FILL") && !eachLine.contains("OFF")) {
                                String[] array = eachLine.split(" ");
                                if (!array[0].equals("FILL")) {
                                    throw new InvalidCommand(eachLine);
                                }
                                fillColor = Color.decode(array[1]);
                                changedPEN = false;
                                changedTOGGLE = false;
                                toggleFill = true;
                            } else if (eachLine.equals("FILL OFF")) {
                                changedTOGGLE = true;
                                toggleFill = false;
                            } else if (eachLine.contains("LINE")) {
                                String[] array = eachLine.split(" ");
                                if (!array[0].equals("LINE")) {
                                    throw new InvalidCommand(eachLine);
                                }
                                try {
                                    int[] x = parseCommand("LINE", array)[0];
                                    int[] y = parseCommand("LINE", array)[1];
                                    panel.addShape(new Line(x, y, penColor, panel.getWidth(), panel.getHeight()), changedPEN, false, changedTOGGLE);
                                    changedPEN = false;
                                    changedTOGGLE = false;
                                } catch (Exception a) {
                                    // shows user which line broke
                                    JOptionPane.showMessageDialog(null, "Your file has corrupted coordinates. Check the following command: \n" + eachLine);
                                    break;
                                }
                            } else if (eachLine.contains("RECTANGLE")) {
                                String[] array = eachLine.split(" ");
                                if (!array[0].equals("RECTANGLE")) {
                                    throw new InvalidCommand(eachLine);
                                }
                                try {
                                    int[] x = parseCommand("RECTANGLE", array)[0];
                                    int[] y = parseCommand("RECTANGLE", array)[1];
                                    panel.addShape(new Rectangle(x, y, penColor, fillColor, toggleFill, panel.getWidth(), panel.getHeight()), changedPEN, false, changedTOGGLE);
                                    changedPEN = false;
                                    changedTOGGLE = false;
                                } catch (Exception a) {
                                    // shows user which line broke
                                    JOptionPane.showMessageDialog(null, "Your file has corrupted coordinates. Check the following command: \n" + eachLine);
                                    break;
                                }
                            } else if (eachLine.contains("ELLIPSE")) {
                                String[] array = eachLine.split(" ");
                                if (!array[0].equals("ELLIPSE")) {
                                    throw new InvalidCommand(eachLine);
                                }
                                try {
                                    int[] x = parseCommand("ELLIPSE", array)[0];
                                    int[] y = parseCommand("ELLIPSE", array)[1];
                                    panel.addShape(new Ellipse(x, y, penColor, fillColor, toggleFill, panel.getWidth(), panel.getHeight()), changedPEN, false, changedTOGGLE);
                                    changedPEN = false;
                                    changedTOGGLE = false;
                                } catch (Exception a) {
                                    // shows user which line broke
                                    JOptionPane.showMessageDialog(null, "Your file has corrupted coordinates. Check the following command: \n" + eachLine);
                                    break;
                                }
                            } else if (eachLine.contains("PLOT")) {
                                String[] array = eachLine.split(" ");
                                if (!array[0].equals("PLOT")) {
                                    throw new InvalidCommand(eachLine);
                                }
                                try {
                                    int[] x = parseCommand("PLOT", array)[0];
                                    int[] y = parseCommand("PLOT", array)[1];
                                    panel.addShape(new Dot(x, y, 10, penColor, panel.getWidth(), panel.getHeight()), changedPEN, false, changedTOGGLE);
                                    changedPEN = false;
                                    changedTOGGLE = false;
                                } catch (Exception a) {
                                    // shows user which line broke
                                    JOptionPane.showMessageDialog(null, "Your file has corrupted coordinates. Check the following command: \n" + eachLine);
                                    break;
                                }
                            } else {
                                throw new InvalidCommand(eachLine);
                            }
                        } catch (Exception wrongFormat) {
                            // shows user which line broke
                            JOptionPane.showMessageDialog(null, "Your file has corrupted commands. Check the following command: \n" + wrongFormat);
                            break;
                        }
                    }
                } catch (IOException noFile) {
                    noFile.printStackTrace();
                }

            }
        }
    }
}
