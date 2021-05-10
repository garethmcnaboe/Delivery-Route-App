package com.company;

public class EdgeHashTable {
	
	static double [][] distance2DArray;
	static Edge [][] edge2DArray;

    //Populates the Hash Table with distance in km/time in mins between locations
    //this is the same number as the vehicle is travelling at 60km per hour
    public static void populateHashTable(Location[] locationArray, int numLocations) {
        distance2DArray = new double[numLocations][numLocations];
    	edge2DArray = new Edge[numLocations][numLocations];
        
    	//Call the circuleCalculator on all of the combinations of locations.
        for (int row = 0; row < distance2DArray.length; row++) {
            for (int col = 0; col < distance2DArray[0].length; col++) {
                //populates the look up table
            	double temp = Edge.circleCalculator(locationArray[row], locationArray[col]);
            	System.out.println(temp);
            	distance2DArray[row][col] = temp;
            	//edge2DArray[row][col] = new Edge(row, col, temp);
            }
        }
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

    //Prints out all the values in the hash table without duplicates (i.e. Road Atlas Triangle Table)
    //this method was used for planning & debugging. It is not called in the actual program.
    public static void printHashTableNoDuplicates(double[][] anArray){
        for(int row = 0; row < anArray.length; row++){
            int col = 0;
            while(row > col){
                System.out.print(((double)Math.round((anArray[row][col])*10)/10) + " - ");
                col++;
            }
            System.out.println();
        }
    }

}