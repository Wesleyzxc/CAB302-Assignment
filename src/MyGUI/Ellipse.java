package MyGUI;

import java.awt.*;

public class Ellipse extends AllShapes{

    private boolean toggleFill;
    private float hratio1, wratio1, hratio2, wratio2;

    Ellipse(int[] x, int[] y, Color color, Color fillColor, boolean toggleFill, int pwidth, int pheight) {
        super(x, y, color, fillColor);
        if (x[0] > x[1]) {
            int[] replaced = {x[1], x[0]};
            super.setX(replaced);
        }
        if (y[0] > y[1]) {
            int[] replaced = {y[1], y[0]};
            super.setY(replaced);
        }
        this.wratio1 = super.getX()[0]/(float)pwidth;
        this.wratio2 = super.getX()[1]/(float)pwidth;
        this.hratio1 = super.getY()[0]/(float)pheight;
        this.hratio2 = super.getY()[1]/(float)pheight;
        this.toggleFill = toggleFill;
    }

    public void draw(Graphics g, int currentWidth, int currentHeight){
        if (!toggleFill) {
            g.setColor(super.getColour());
            g.drawOval((int)(wratio1*currentWidth), (int)(hratio1*currentHeight), (int)(wratio2*currentWidth) - (int)(wratio1*currentWidth), (int)(hratio2*currentHeight) - (int)(hratio1*currentHeight));

        } else {
            g.setColor(super.getFillColour());
            g.fillOval((int)(wratio1*currentWidth), (int)(hratio1*currentHeight), (int)(wratio2*currentWidth) - (int)(wratio1*currentWidth), (int)(hratio2*currentHeight) - (int)(hratio1*currentHeight));
            g.setColor(super.getColour());
            g.drawOval((int)(wratio1*currentWidth), (int)(hratio1*currentHeight), (int)(wratio2*currentWidth) - (int)(wratio1*currentWidth), (int)(hratio2*currentHeight) - (int)(hratio1*currentHeight));
        }
    }

    @Override
    public String getVEC(){
        return String.format("ELLIPSE %.2f %.2f %.2f %.2f", wratio1, hratio1, wratio2, hratio2);
    }
}
