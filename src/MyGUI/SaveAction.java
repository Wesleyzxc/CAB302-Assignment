package MyGUI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SaveAction implements ActionListener {
    private DrawArea panel;

    public SaveAction(DrawArea panel) {
        this.panel = panel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        BufferedImage bImg = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D cg = bImg.createGraphics();
        panel.paintAll(cg);
        try {
            if (ImageIO.write(bImg, "png", new File("./output_image.png")))
            {
                System.out.println("-- saved");
            }
        } catch (IOException a) {
            // TODO Auto-generated catch block
            a.printStackTrace();
        }

    }
}