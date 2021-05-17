package com.company;

import java.util.Random;

public class Genetic {

    public static double[] geneticAlgorithm(Location[] locationArray, int numLocations, double[][] Distance2DArray) {

        //Part 1: create the population array and populate it with copies of the route in original sequence.
        int populationSize = numLocations * 10;
        //numLocations is plus 1 so that we can attach the fitness of the route at the end.
        double[][] populationArray = new double[populationSize][numLocations+1];

        for(int i = 0; i<populationSize; i++){
            for(int j=0; j<numLocations; j++){
                 populationArray[i][j] = Double.valueOf(locationArray[j].getOrderNum());
            }
        }

        //Part 2: use Fisher-Yates method to shuffle all of the routes in the array

        //create an object of the Random Class
        Random r = new Random();

        //for loop to iterate over all of the rows in the table
        for(int i = 0; i<populationSize; i++){

            //for loop to iterate over all of the columns in the table
            for(int j = numLocations-1; j >0; j--){
                //Pick a random index from 0 to j
                int k = r.nextInt(j+1);
                //swap array[i] with the element at random index
                double temp = populationArray[i][j];
                populationArray[i][j] = populationArray[i][k];
                populationArray[i][k] = temp;
            }
        }

        //Part 3: Start the loop which will count time or number of generations which will occur.
        long finish = System.currentTimeMillis() + 8000; // set end of time 8 seconds after start.
        while (System.currentTimeMillis() < finish) {

            //Part 4: Use the look up hash table in order to calculate the fitness of the route.
            for(int i = 0; i<populationSize; i++){
                double totalDistance = 0;

                for(int j = 0; j< numLocations-2; j++){
                    int temp1 = (int) populationArray[i][j];
                    int temp2 = (int) populationArray[i][j+1];
                    totalDistance = totalDistance + Distance2DArray[temp1][temp2];
                }
                populationArray[i][numLocations] = totalDistance;
            }

            //Part 5: Calculate the sum of all of the routes.

            //Part 6: Use the sum of all of the routes to normalise the data.

            //Part 7: Create temp storage for the next generation.

            //Part 8: Start loop to create new generation

            //Part 9: Select an individual route based on algorithm

            //Part 10: Mutate that route by swaping two random stops in the order

            //Finish loop to create next generation.


        }//Close loop to allow for evolutionary period.

        //Placeholder so that the code will not show errors for now.
        double [] solution = new double[numLocations];
        return solution;
    }

}
