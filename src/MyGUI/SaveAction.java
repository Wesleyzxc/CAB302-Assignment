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

public class SaveAction implements ActionListener {
    private DrawArea panel;
    private java.util.List<String> VEC = new LinkedList<>();

    public SaveAction(DrawArea panel) {
        this.panel = panel;
    }
    @Override
    public void actionPerformed (ActionEvent e)  {
        String filename = "";
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Choose a directory to save your file: ");
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnValue = jfc.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            if (jfc.getSelectedFile().isDirectory()) {
                filename = jfc.getSelectedFile().toString();
            }
        }

        VEC = panel.getAllVEC();

        try {
            FileWriter writer = new FileWriter("output.VEC");
            for(String str: VEC) {
                String[] s = str.split(",");
                for (String str2: s){
                writer.write(str2);
                writer.write(System.getProperty( "line.separator" ));
                }

            }
            writer.close();
            System.out.println("file successfully saved");
        } catch(IOException a) {
            System.out.println("failed to save file");
        }

        panel.setSize(new Dimension(4096,4096));
        System.out.println(panel.getWidth());
        BufferedImage bImg = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        java.util.List<AllShapes> history = new LinkedList<>();


        Graphics2D cg = bImg.createGraphics();
        panel.paintAll(cg);

        //Save as bmp
        try {
            System.out.println(filename);
            if (ImageIO.write(bImg, "bmp", new File(filename + "/output.bmp")))
            {
                System.out.println("-- saved");
            }
        } catch (IOException a) {
            // TODO Auto-generated catch block
            a.printStackTrace();
        }


    }
}