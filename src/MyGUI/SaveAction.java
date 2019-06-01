package MyGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.io.FileWriter;


/**
 * ActionListener for saving drawing in VEC and BMP
 */
public class SaveAction implements ActionListener {
    private DrawArea panel;
    private java.util.List<String> VEC = new LinkedList<>();

    /**
     * ActionListener for saving file
     * @param panel panel that is getting saved
     */
    SaveAction(DrawArea panel) {
        this.panel = panel;
    }
    @Override
    public void actionPerformed (ActionEvent e)  {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Choose a directory to save your file: ");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String filename = fileChooser.getSelectedFile().toString();
            VEC = panel.getAllVEC();

            try {
                // file location + file name
                FileWriter writer = new FileWriter(new File(filename + "/output.VEC"));
                for(String str: VEC) {
                    // only draw if visible when saved
                    if (panel.getHistory().get(VEC.indexOf(str)).isVisible()){
                        String[] s = str.split(",");
                        for (String str2: s) {
                            writer.write(str2);
                            writer.write(System.getProperty("line.separator"));
                        }
                    }
                }
                writer.close();
                System.out.println("file successfully saved");
            } catch(IOException a) {
                System.out.println("failed to save VEC");
            }
            // saves old size before resizing it to 4096 and creates buffer image to save
            int[] formerSize = {panel.getWidth(), panel.getHeight()};
            panel.setSize(new Dimension(4096,4096));
            System.out.println(panel.getWidth());
            BufferedImage bImg = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D cg = bImg.createGraphics();
            panel.paintAll(cg);
            // resets size back to old size
            panel.setSize(new Dimension(formerSize[0],formerSize[1]));
            panel.repaint();

            //Save as bmp
            try {
                if (ImageIO.write(bImg, "bmp", new File(filename + "/output.bmp")))
                {
                    System.out.println("-- saved");
                }
            } catch (IOException a) {
                System.out.println("failed to save BMP");
                JOptionPane.showMessageDialog(null, "Failed to save BMP.");
                a.printStackTrace();
            }

        }





    }
}