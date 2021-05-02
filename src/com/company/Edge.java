package com.company;

public class Edge {

	//fields to be included in edge objects
    int location1;
    int location2;
    double distance;
    //will need to add the compass bearing from location1 to location2

    //constructor to create edge objects
    Edge(int location1, int location2, double distance){
        this.location1 = location1;
        this.location2 = location2;
        this.distance = distance;
    }

    //getter methods to return attributes of edge objects
    public int getLocation1(){
        return this.location1;
    }

    public int getLocation2(){
        return this.location2;
    }

    public double getDistance(){
        return this.distance;
    }

    //method which calculates the length of an edge
    public static double circleCalculator(Location L1, Location L2) {
        //this is the average circumference of the earth in meters
        double radius = 6_371;

        //Take in four inputs being lat, long x 2
        double latd1 = L1.Latitude;
        double longd1 = L1.Longitude;
        double latd2 = L2.Latitude;
        double longd2 = L2.Longitude;

        //Calculate absolute difference between longtitude and lattitude
        double longdiffd = longd1 - longd2;
        double latdiffd = latd1 - latd2;

        //Convert from degrees to radians as formula takes in radians
        double longdiffr = Math.toRadians(longdiffd);
        double latdiffr = Math.toRadians(latdiffd);
        double latr1 = Math.toRadians(latd1);
        double latr2 = Math.toRadians(latd2);

        //Haversine Formula
        double part1 = Math.sin(latdiffr / 2) * Math.sin(latdiffr / 2);
        double part2 = Math.cos(latr1) * Math.cos(latr2) * Math.sin(longdiffr / 2) * Math.sin(longdiffr / 2);
        double subtotal = Math.sqrt(part1 + part2);
        double angle = 2 * Math.asin(subtotal);

        //Calculate the distance by mutliplying the angle at the centre of the earth by the radius of the earth
        double distance = (angle * radius);

        return distance;
    }
}