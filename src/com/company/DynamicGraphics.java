package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DynamicGraphics extends JPanel{

    @Override
    public void paintComponent(Graphics g){
            Image mapImage = null;
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            try {
                mapImage = ImageIO.read(new File("C:\\Users\\garet\\IdeaProjects\\projectbackup\\src\\com\\company\\map.png"));
            }
           catch (IOException e) {
                e.printStackTrace();
            }
            mapImage.getScaledInstance(964, 779, Image.SCALE_SMOOTH);

            g2d.drawImage(mapImage, 0, 0, 964, 779, this);
    }
}
