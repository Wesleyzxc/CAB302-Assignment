package MyGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShapeChooser implements ActionListener {

    private DrawObjectListener handler;
    private JButton lineButton;
    private JButton rectButton;
    private JButton ellipseButton;
    private JButton polyButton;

    /**
     * Runs handler for when a specific button is pressed
     * @param handler MouseAdapter that acts on what button is clicked
     * @param lineButton button that makes you draw lines
     * @param rectButton button that makes you draw rectangles
     * @param ellipseButton button that makes you draw ellipse
     * @param polyButton buttons that makes you draw polygons
     */
    ShapeChooser(DrawObjectListener handler, JButton lineButton, JButton rectButton, JButton ellipseButton, JButton polyButton){
        this.handler = handler;
        this.lineButton = lineButton;
        this.rectButton = rectButton;
        this.ellipseButton = ellipseButton;
        this.polyButton = polyButton;
    }

    /**
     * Change handler's shape to draw whatever the button is clicked
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lineButton){
            handler.chooseShape(DrawObjectListener.Shape.LINE);
        }
        if (e.getSource() == rectButton){
            handler.chooseShape(DrawObjectListener.Shape.RECTANGLE);
        }
        if (e.getSource() == ellipseButton){
            handler.chooseShape(DrawObjectListener.Shape.ELLIPSE);
        }
        if (e.getSource() == polyButton){
            handler.chooseShape(DrawObjectListener.Shape.POLYGON);
        }

    }
}
