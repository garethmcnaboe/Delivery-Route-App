package com.company;

public class Greedy {

    public static short[] algorithm(int numLocations, double[][] Distance2DArray, double[] depotDistances, Location[] locationArray) {

        //Create an array of shorts to store the route
        short[] bestGreedyRoute = new short[numLocations];
        double lowestDistanceO = 1000;
        double lowestBadMinutesO = 5_000;

        //Loop which will iterate through all the vertices as the starting point.
        for(short a = 1; a<numLocations+1; a++){
            short[] greedyRoute = new short[numLocations];
            greedyRoute[0] = a;

            //variable to record the last stop
            short lastStop = a;

            //For loop to iterate over all of the remaining slots to be filled
            for (int i = 1; i < numLocations; i++) {
                double lowestDistance = 1_000;
                short lowestVertice = 3;

                //For Loop to iterate across one row of the 2D Array of Distances
                for (int k = 0; k < numLocations; k++) {
                    if (Distance2DArray[lastStop - 1][k] < lowestDistance && ((lastStop - 1) != k)) {
                        //checks to see if the vertice has already been visited
                        boolean alreadyUsed = false;
                        for (int j = 0; j < greedyRoute.length; j++) {
                            if (greedyRoute[j] == (k+1)){
                                alreadyUsed = true;
                            }
                        }
                        //if new lowest vertice have not been visited update lowest so far.
                        if (!alreadyUsed) {
                            lowestDistance = Distance2DArray[lastStop - 1][k];
                            lowestVertice = (short) (k);
                        }
                    }
                }
                //update the array and re-set last visited.
                greedyRoute[i] = (short)(lowestVertice + 1);
                lastStop = greedyRoute[i];
            }

            double distance = 0;
            double badminutes = 0;

            distance = distance + depotDistances[greedyRoute[0]-1];

            double minutes1 = locationArray[greedyRoute[0]-1].getWaitTime() + distance;
            if(minutes1 > 30){
                badminutes = badminutes + (minutes1-30);
            }

            for(short l = 0; l<greedyRoute.length-1; l++){
                short temp1 = greedyRoute[l];
                short temp2 = greedyRoute[l+1];
                distance = distance + Distance2DArray[temp1-1][temp2-1];

                double minutes2 = locationArray[temp2-1].getWaitTime() + distance;
                if(minutes2>30){
                    badminutes = badminutes + (minutes2-30);
                }
            }

            //System.out.println(distance + " " + badminutes + " " + lowestBadMinutesO + " " + a);

            //Prioritise by distance
            /*
            if(distance < lowestDistanceO){
                lowestDistanceO = distance;
                for(short k = 0; k< greedyRoute.length; k++){
                    bestGreedyRoute[k] = greedyRoute[k];
                }
            }
            */

            //Prioritise by bad minutes
            if(badminutes < lowestBadMinutesO){
                lowestBadMinutesO = badminutes;
                for(short k = 0; k < greedyRoute.length; k++){
                    bestGreedyRoute[k] = greedyRoute[k];
                }
            }

        }
        return bestGreedyRoute;
    }
}