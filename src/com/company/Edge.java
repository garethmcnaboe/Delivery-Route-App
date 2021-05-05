package com.company;

public class Edge {

	//fields to be included in edge objects
    int location1;
    int location2;
    double distance;
    double bearing;

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

    public double getBearing(){
        return this.bearing;
    }

    //method which calculates the length of an edge
    public static double circleCalculator(Location L1, Location L2) {
        //this is the average circumference of the earth in meters
        double radius = 6_371;

        //Take in four inputs being lat, long x 2
        double latd1 = L1.latitude;
        double longd1 = L1.longitude;
        double latd2 = L2.latitude;
        double longd2 = L2.longitude;

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

    //method which calculates the compass bearing between two points.
    //https://www.igismap.com/formula-to-find-bearing-or-heading-angle-between-two-points-latitude-longitude/
    public static double bearingCalculator(Location L1, Location L2) {

        //degrees on a compass
        double degrees = 360;

        //Take in four inputs being lat, long x 2
        double latd1 = L1.latitude;
        double longd1 = L1.longitude;
        double latd2 = L2.latitude;
        double longd2 = L2.longitude;

        //Calculate absolute difference between longtitude and lattitude
        double longdiffd = longd1 - longd2;

        //Convert from degrees to radians as formula takes in radians
        double longdiffr = Math.toRadians(longdiffd);
        double latr1 = Math.toRadians(latd1);
        double latr2 = Math.toRadians(latd2);

        //Derivative of Haversine formula
        double part1 = Math.cos(latr2) * Math.sin(longdiffr);
        double part2 = Math.cos(latr1) * Math.sin(latr2) - Math.sin(latr1) * Math.cos(latr2) * Math.cos(longdiffr);
        double angler = Math.atan2(part1, part2);

        double bearing = Math.toDegrees(angler);

        if(bearing<0){
            bearing = bearing * -1;
        }
        else{
            bearing = degrees - bearing;
        }
        return bearing;
    }

}