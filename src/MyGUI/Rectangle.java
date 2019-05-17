package MyGUI;

import java.awt.*;

public class Rectangle extends AllShapes { //maybe subclass of line?
    private int x1, y1, x2, y2;
    private Color color;
    private Color fillColor;
    private boolean toggleFill;
    private float hratio1, wratio1, hratio2, wratio2;


    public Rectangle(int x1, int y1, int x2, int y2, Color color, Color fillColor, boolean toggleFill, int pwidth, int pheight  ) {
        super(color, fillColor);
        if (x1 < x2) {
            this.x1 = x1;
            this.x2 = x2;
        } else {
            this.x1 = x2;
            this.x2 = x1;
        }
        if (y1 < y2) {
            this.y1 = y1;
            this.y2 = y2;
        } else {
            this.y1 = y2;
            this.y2 = y1;
        }
        this.wratio1 = x1/(float)pwidth;
        this.wratio2 = x2/(float)pwidth;
        this.hratio1 = y1/(float)pheight;
        this.hratio2 = y2/(float)pheight;
        this.color = color;
        this.fillColor = fillColor;
        this.toggleFill = toggleFill;
    }

    public void draw(Graphics g, int currentWidth, int currentHeight) {
        if (!toggleFill) {
            g.setColor(color);
//            g.drawRect(x1, y1, x2 - x1, y2 - y1);
            g.drawRect((int)(wratio1*currentWidth), (int)(hratio1*currentHeight), (int)(wratio2*currentWidth) - (int)(wratio1*currentWidth), (int)(hratio2*currentHeight) - (int)(hratio1*currentHeight));

        } else {
            g.setColor(fillColor);
            g.fillRect((int)(wratio1*currentWidth), (int)(hratio1*currentHeight), (int)(wratio2*currentWidth) - (int)(wratio1*currentWidth), (int)(hratio2*currentHeight) - (int)(hratio1*currentHeight));
            g.setColor(color);
            g.drawRect((int)(wratio1*currentWidth), (int)(hratio1*currentHeight), (int)(wratio2*currentWidth) - (int)(wratio1*currentWidth), (int)(hratio2*currentHeight) - (int)(hratio1*currentHeight));

        }
    }

    @Override
    public String getVEC() {
        //String hex = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
        return ((String.format("RECTANGLE %.2f %.2f %.2f %.2f", x1 / screenWidth, y1 / screenHeight, x2 / screenWidth, y2 / screenHeight)));
    }

    @Override
    public String shape() {
        return "RECTANGLE";
    }
}






