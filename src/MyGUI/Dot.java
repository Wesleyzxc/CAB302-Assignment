package MyGUI;

import java.awt.*;

public class Dot extends AllShapes {

    private int diameter;
    private float hratio1, wratio1;


    Dot(int[] x, int[] y, int diameter, Color color, int pwidth, int pheight) {
        super(x, y, color, color);

        this.diameter = diameter;
        this.wratio1 = super.getX()[0]/(float)pwidth;
        this.hratio1 = super.getY()[0]/(float)pheight;
    }

    public void draw(Graphics g, int currentWidth, int currentHeight){
        g.setColor(super.getColour());
        g.fillOval((int)(wratio1*currentWidth), (int)(hratio1*currentHeight), diameter, diameter);

    }

    @Override
    public String getVEC(){
        return ((String.format("PLOT %.2f %.2f",super.getX()[0]/screenWidth,super.getY()[0]/screenHeight)));
    }
}
