package MyGUI;

import java.awt.*;

public abstract class AllShapes {
    private Color colour;
    private Color fillColour;
    private int[] x;
    private int[] y;

    Color getColour(){ return colour; }

    Color getFillColour(){ return fillColour; }


    int[] getX() {
        return x;
    }

    void setX(int[] x) {
        this.x = x;
    }

    int[] getY() {
        return y;
    }

    void setY(int[] y) {
        this.y = y;
    }


    // java - get screen size using the Toolkit class
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    float screenHeight = screenSize.height;
    float screenWidth = screenSize.width;
    AllShapes(int[] x, int[] y, Color colour, Color fillColour){
        this.x = x;
        this.y = y;
        this.colour = colour;
        this.fillColour = fillColour;

    }


    void draw(Graphics g, int newWidth, int newHeight){}
    String getVEC(){return "";}
    String getCoords(){return "";}
    String shape() { return "" ;}


}
