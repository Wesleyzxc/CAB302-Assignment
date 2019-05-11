package MyGUI;

import java.awt.*;

public abstract class AllShapes {
    Color colour = null;
    public AllShapes(Color colour){
        this.colour = colour;
    }

    Color getColour(){
        return colour;
    };
    void draw(Graphics g){}
    String getVEC(){return "";}

}
