package MyGUI;

import com.sun.source.util.DocTreeScanner;

import javax.swing.*;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DrawArea extends JPanel {
    private List<Dot> polygonMarker = new LinkedList<>();
    private List<AllShapes> history = new LinkedList<>();
    private List<String> VEC = new LinkedList<>();
    private AllShapes drag;
    private boolean zoomer = false;


    private double zoomFactor = 1;

    public boolean isDragging() {
        return dragging;
    }

    public void setDragging(boolean dragging) {
        this.dragging = dragging;
    }

    private boolean dragging;

    public void setZoomFactor(double factor){
        this.zoomFactor=factor;
        this.zoomer = true;
    }

    public double getZoomFactor() {
        return zoomFactor;
    }




    public List<String> getAllVEC() {
        return VEC;
    }

    void addShape(AllShapes shape, boolean changedPEN, boolean changedFILL) {
        history.add(shape);
        String final_VEC ="";
        Color pen = shape.getColour();
        String hexPen = String.format("#%02x%02x%02x", pen.getRed(), pen.getGreen(), pen.getBlue());
        Color fill = shape.getFillColour();
        String hexFill = String.format("#%02x%02x%02x", fill.getRed(), fill.getGreen(), fill.getBlue());
        String shapeVEC = shape.getVEC();

        if (changedPEN){
            changedPEN = false;
            final_VEC = final_VEC.concat("PEN " + hexPen + ",");
        }
        if (changedFILL){
            changedFILL = false;
            final_VEC = final_VEC.concat("FILL " + hexFill + ",");
        }
        final_VEC = final_VEC.concat(shapeVEC);
        VEC.add(final_VEC); //parameter - true if color changed
        System.out.println(VEC);
        final_VEC = "";
        this.repaint();
        }



    void dragLine(AllShapes line){
        drag = line;
        this.repaint();
    }

    void addMarker(Dot poly) {
        polygonMarker.add(poly);
        this.repaint();
    }

    void clearMarker() {
        polygonMarker.clear();
//        this.repaint();
    }

    void undoHistory() {
        System.out.println(history.size());
        if (history.size() > 0) {
            history.remove(history.get(history.size()-1)); //Remove history
            VEC.remove(VEC.get(VEC.size()-1)); //Remove VEC
            this.repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if(zoomer==true){
            AffineTransform at = new AffineTransform();
            at.scale(zoomFactor ,zoomFactor);
            g2.setTransform(at);
        }
        super.paintComponent(g);


        for (AllShapes eachShape: history){
            eachShape.draw(g, this.getWidth(), this.getHeight());
        }
        for (Dot eachDot: polygonMarker){
            eachDot.draw(g);
        }
        //         Draws temporary line if user drags line
        if (drag != null && dragging) {
            drag.draw(g, this.getWidth(), this.getHeight());
        }


//        drag = null;

    }

    DrawArea() {
        setBackground(Color.WHITE);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
//                System.out.println(e);
            }
        });
    }



}
