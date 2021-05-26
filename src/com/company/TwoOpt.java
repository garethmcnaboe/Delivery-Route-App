package com.company;

public class TwoOpt {


    public static short[] algorithm(int numLocatinos, short [] greedyRoute, double[][] distance2DArray, double[] depotDistances, Location[] locationArray ){
        short [] twoOptArray = greedyRoute;
        double bestBadMinutes = checkBadMinutes(depotDistances, greedyRoute, distance2DArray, locationArray);
        System.out.println(bestBadMinutes);

        //while loop will operate for 5 seconds from when it is started.
        long finish = System.currentTimeMillis() + 5_000;
        while (System.currentTimeMillis() < finish) {
            //label which can be called later so that we come out of loop at the right location.
            outer:
            for(int i = 1; i<numLocatinos; i++){
                    for(int j=1; j<numLocatinos+1; j++) {
                        //call swap method to create a slightly different route
                        short[] tempRoute = swap(twoOptArray, numLocatinos, i, j);
                        //call check method to determine number of bad minutes.
                        double tempMinutes = checkBadMinutes(depotDistances, tempRoute, distance2DArray, locationArray);
                        //if new best is found then reset bests and restart the process again.
                        if (tempMinutes < bestBadMinutes) {
                            System.out.println(tempMinutes);
                            twoOptArray = tempRoute;
                            bestBadMinutes = tempMinutes;
                            break outer; //calling break with the label so we come out of loop at the right location.
                        }
                    }
                }
             }
        return twoOptArray;
    }

    //Method which calculates total distance covered and uses that to calculate the total bad minutes of a given route.
    public static double checkBadMinutes(double[]depotDistances, short[] greedyRoute, double[][] Distance2DArray, Location[] locationArray ) {

        double distance = 0;
        double badminutes = 0;

        distance = distance + depotDistances[greedyRoute[0] - 1];

        double minutes1 = locationArray[(int) (greedyRoute[0] - 1)].getWaitTime() + distance;
        if (minutes1 > 30) {
            badminutes = badminutes + (minutes1 - 30);
        }

        for (short l = 0; l < greedyRoute.length - 1; l++) {
            short temp1 = (short) greedyRoute[l];
            short temp2 = (short) greedyRoute[l + 1];
            distance = distance + Distance2DArray[temp1 - 1][temp2 - 1];

            double minutes2 = locationArray[temp2 - 1].getWaitTime() + distance;
            if (minutes2 > 30) {
                badminutes = badminutes + (minutes2 - 30);
            }
        }
        return badminutes;
    }

    //method which undertakes the 2-opt swap
    public static short[] swap(short[] oldArray, int numLocations, int start, int stop) {
        short[] newArray =new short[numLocations];

        //copy the routes up to the start point into the new array
        for(int i = 0; i<start-1; i++){
            newArray[i] = oldArray[i];
        }

        //copy the routes in reverse order between start and stop (inclusive)
        int counter = 1;
        for(int j = start-1; j<stop; j++){
            newArray[j] = oldArray[stop-counter];
            counter++;
        }

        //copy the routes from the stop point onwards until the end of the array.
        for(int k = stop; k< numLocations; k++){
            newArray[k] = oldArray[k];
        }

        return newArray;
    }

}