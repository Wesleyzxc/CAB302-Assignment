package MyGUI;

import java.awt.*;
import java.text.MessageFormat;

public class Line extends AllShapes{
    private int x1, y1, x2, y2;
    private Color color;
    private float hratio1, wratio1, hratio2, wratio2;

    public Line(int x1, int y1, int x2, int y2, Color color, int pwidth, int pheight) {
        super(color, color);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.wratio1 = x1/(float)pwidth;
        this.wratio2 = x2/(float)pwidth;
        this.hratio1 = y1/(float)pheight;
        this.hratio2 = y2/(float)pheight;


        this.color = color;
    }

    public void draw(Graphics g, int currentWidth, int currentHeight){
        g.setColor(color);
//        g.drawLine(x1, y1, x2, y2);

        g.drawLine((int)(wratio1*currentWidth), (int)(hratio1*currentHeight), (int)(wratio2*currentWidth), (int)(hratio2*currentHeight));
    }

    @Override
    public Color getColour() {
        return color;
    }

    @Override
    public String getVEC(){
        //String hex = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
        return ((String.format("LINE %.2f %.2f %.2f %.2f", x1/screenWidth, y1/screenHeight, x2/screenWidth, y2/screenHeight)));
    }

}
