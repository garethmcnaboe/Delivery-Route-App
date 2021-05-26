package com.company;

public class EdgeHashTable {
	
	static double [][] distance2DArray;
	static double [] firstLegArray;

    //Populates the Hash Table with distance in km/time in mins between locations
    //this is the same number as the vehicle is travelling at 60km per hour
    public static double[][] populateHashTable(Location[] locationArray, int numLocations) {
        distance2DArray = new double[numLocations][numLocations];
        
    	//Call the circuleCalculator on all of the combinations of locations.
        for (int row = 0; row < distance2DArray.length; row++) {
            for (int col = 0; col < distance2DArray[0].length; col++) {
                //populates the look up table
            	double temp1 = Edge.circleCalculator(locationArray[row], locationArray[col]);
            	distance2DArray[row][col] = temp1;
            }
        }
        return distance2DArray;
    }

    //creates an array of distances from home depot to each location
    public static double[] populateDepotDistances(Location[] locationArray, int numLocations,
                                                  String HOMEDEPOTADDRESS, double HOMEDEPOTLAT, double HOMEDEPOTLONG){
        Location homeDepot = new Location((short) 0, HOMEDEPOTADDRESS, 0, HOMEDEPOTLAT, HOMEDEPOTLONG);
        firstLegArray = new double[numLocations];

        for(int i=0; i<numLocations; i++) {
            double temp2 = Edge.circleCalculator(homeDepot, locationArray[i]);
            firstLegArray[i] = temp2;
        }
        return firstLegArray;
    }

    //Print out all the values in the hash table in order to check it is functioning properly.
    //this method was used for planning & debugging. It is not called in the actual program.
    public static void printHashTable() {
        for (int row = 0; row < distance2DArray.length; row++) {
            for (int col = 0; col < distance2DArray[0].length; col++) {
                System.out.print(((double) Math.round((distance2DArray[row][col]) * 10) / 10) + " - ");
            }
            System.out.println();
        }
    }

}