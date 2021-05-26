package com.company;

import javax.swing.*;
import java.awt.*;

//this class provides the static lines which are the key for the
//lines superimposed on the map for next and subsequent delivery
public class StaticGraphics extends JPanel {

    //This component is used to draw the blue and oranges lines which on the main panel to the left.
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(0,0, 100, 0);

        g2d.setColor(Color.ORANGE);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(0,40, 100, 40);
    }
}