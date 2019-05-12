package MyGUI;

import java.awt.*;

public abstract class AllShapes {
    Color colour = null;
    // java - get screen size using the Toolkit class
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    float screenHeight = screenSize.height;
    float screenWidth = screenSize.width;
    public AllShapes(Color colour){
        this.colour = colour;
    }

    Color getColour(){
        return colour;
    };
    void draw(Graphics g){}
    String getVEC(){return "";}

}
