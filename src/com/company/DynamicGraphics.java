package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;


//Class which contains the code for the map and dynamic lines which appear on it.
public class DynamicGraphics extends JPanel{

    //These are all final values which set out the size of the map
    static final double LENGTHPIXELS = 736;
    static final double WIDTHPIXELS = 910;
    //These are the final values which set the margins of the map
    static final double MAXLATITUDE = 53.412485;
    static final double MINLATITUDE = 53.283485;
    static final double MAXLONGITUDE = -6.45509;
    static final double MINLONGITUDE = -6.712900;

    //these are the fields which will record the map co-ordinates
    double lat1, long1, lat2, long2, lat3, long3, lat4, long4;

    //array to store the co-ordinates
    double[] coordinateArray;

    //Constructor for object which will store the co-ordinate values
    public DynamicGraphics(double lat1, double long1, double lat2, double long2, double lat3, double long3, double lat4, double long4){
        this.lat1 = lat1;
        this.long1 = long1;
        this.lat2 = lat2;
        this.long2 = long2;
        this.lat3 = lat3;
        this.long3 = long3;
        this.lat4 = lat4;
        this.long4 = long4;
        //creates array to store the
        coordinateArray = new double[8];
    }
    //Setter method which can be used to change the co-ordinates
    public void setCoordinates(double lat1, double long1, double lat2, double long2, double lat3, double long3, double lat4, double long4){
        this.lat1 = lat1;
        this.long1 = long1;
        this.lat2 = lat2;
        this.long2 = long2;
        this.lat3 = lat3;
        this.long3 = long3;
        this.lat4 = lat4;
        this.long4 = long4;
    }

    //Setter method which sets the values in the array.
    public void setArray(DynamicGraphics object) {
        coordinateArray[0] = object.lat1;
        coordinateArray[1] = object.long1;
        coordinateArray[2] = object.lat2;
        coordinateArray[3] = object.long2;
        coordinateArray[4] = object.lat3;
        coordinateArray[5] = object.long3;
        coordinateArray[6] = object.lat4;
        coordinateArray[7] = object.long4;
    }

    public void repainter(){
        repaint();
    }

    @Override
    public void paintComponent(Graphics g){
            Image mapImage = null;
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            //load and render the map file onto the screen
            URL url = getClass().getResource("map.png");
            try {
                mapImage = ImageIO.read(new File(url.getPath()));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            mapImage.getScaledInstance((int) WIDTHPIXELS, (int)LENGTHPIXELS, Image.SCALE_SMOOTH);
            g2d.drawImage(mapImage, 0, 0, (int) WIDTHPIXELS, (int)LENGTHPIXELS, this);

           coordinateArray = Sanitise(coordinateArray);
           coordinateArray = Convert(coordinateArray);

            g2d.setColor(Color.BLUE);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawLine((int)coordinateArray[1],(int)coordinateArray[0], (int)coordinateArray[3], (int)coordinateArray[2]);

            g2d.setColor(Color.ORANGE);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawLine((int)coordinateArray[5], (int)coordinateArray[4], (int)coordinateArray[7], (int)coordinateArray[6]);
    }

    //method which will convert the gps co-ordinates into pixel measurements
    //x-axis and y-axis starting from top left hand corner.
    public static double[] Convert(double[] coordinateArray){
        double lengthDegress = MAXLATITUDE - MINLATITUDE;
        double widthDegrees = MAXLONGITUDE - MINLONGITUDE;

        for(int i = 0; i<7; i=i+2) {
            coordinateArray[i] = ((coordinateArray[i]-MAXLATITUDE)/-lengthDegress)*LENGTHPIXELS;
        }
        for(int j = 1; j<8; j=j+2){
            coordinateArray[j] = ((coordinateArray[j]-MINLONGITUDE)/widthDegrees)* WIDTHPIXELS;
        }
        return coordinateArray;
    }

    //simple method to sanitise the inputs. If co-ordinates are given which are off
    //the map then the lines will only be drawn to the edge of the map.
    public static double[] Sanitise(double[] coordinateArray){
        for(int i = 0; i<7; i=i+2) {
            if (coordinateArray[i] > MAXLATITUDE){
                coordinateArray[i] = MAXLATITUDE;
            }
            if(coordinateArray[i] < MINLATITUDE){
                coordinateArray[i] = MINLATITUDE;
            }
        }
        for(int j = 1; j<8; j=j+2){
            if(coordinateArray[j] < MINLONGITUDE){
                coordinateArray[j] = MINLONGITUDE;
            }
            if(coordinateArray[j] > MAXLONGITUDE){
                coordinateArray[j] = MAXLONGITUDE;
            }
        }
        return coordinateArray;
    }
}