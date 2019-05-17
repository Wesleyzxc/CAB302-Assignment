package MyGUI;

import java.awt.*;
import java.text.MessageFormat;

public class Line extends AllShapes{

    private float hratio1, wratio1, hratio2, wratio2;

    Line(int[] x, int[] y, Color color, int pwidth, int pheight) {
        super(x, y, color, color);
        this.wratio1 = x[0]/(float)pwidth;
        this.wratio2 = x[1]/(float)pwidth;
        this.hratio1 = y[0]/(float)pheight;
        this.hratio2 = y[1]/(float)pheight;

    }

    public void draw(Graphics g, int currentWidth, int currentHeight){
        g.setColor(super.getColour());
//        g.drawLine(super.getX()[0], super.getY()[0], super.getX()[1], super.getY()[1]);
        g.drawLine((int)(wratio1*currentWidth), (int)(hratio1*currentHeight), (int)(wratio2*currentWidth), (int)(hratio2*currentHeight));
    }

    @Override
    public String getVEC(){
        //String hex = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
        return ((String.format("LINE %.2f %.2f %.2f %.2f", wratio1, hratio1, wratio2, hratio2)));
    }

}
