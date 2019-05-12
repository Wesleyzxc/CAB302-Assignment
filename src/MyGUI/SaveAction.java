package MyGUI;

import javax.imageio.ImageIO;
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

        //BufferedImage bImg = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        //Graphics2D cg = bImg.createGraphics();
        //panel.paintAll(cg);

        //save as png
        /*try {
            if (ImageIO.write(bImg, "png", new File("./output_image.png")))
            {
                System.out.println("-- saved");
            }
        } catch (IOException a) {
            // TODO Auto-generated catch block
            a.printStackTrace();
        }*/


    }
}