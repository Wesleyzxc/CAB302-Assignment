package MyGUI;

import java.awt.*;

public abstract class AllShapes {
    Color colour = null;
    Color fillColour = null;
    // java - get screen size using the Toolkit class
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    float screenHeight = screenSize.height;
    float screenWidth = screenSize.width;
    public AllShapes(Color colour, Color fillColour){
        this.colour = colour;
        this.fillColour = fillColour;
    }

    Color getColour(){
        return colour;
    };
    Color getFillColour(){
        return fillColour;
    };
    void draw(Graphics g){}
    String getVEC(){return "";}
    String getCoords(){return "";}
    String shape() { return "" ;}


}
