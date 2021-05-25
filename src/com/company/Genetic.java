package com.company;

import java.util.Random;

public class Genetic {

    public static short[] algorithm(Location[] locationArray, int numLocations, double[][] Distance2DArray, double[] depotDistances) {

        int genCounter = 0;

        //Part 1: create the population array and populate it with copies of the route in original sequence.
        int populationSize = numLocations * 5;
        //row for each member of population & column for each location they visit
        short[][] populationArray = new short[populationSize][numLocations];

        for(int i = 0; i<populationSize; i++){
            for(int j=0; j<numLocations; j++){
                 populationArray[i][j] = locationArray[j].getOrderNum();
            }
        }

        //Part 2: create an array to store the total distance of each population member, an array to store best route
        //together with a variable to store shortest journey time.
        double[] fitnessArray = new double[populationSize];
        short[] bestRoute = new short[numLocations];
        double lowestDistance = 10_000;
        double totalFitness = 0;


        //Part 3: use Fisher-Yates method to pseudo randomly shuffle all of the routes in the array
        //create an object of the Random Class
        Random r = new Random();
        //for loop to iterate over all of the rows in the table
        for(int i = 0; i<populationSize; i++){

            //for loop to iterate over all of the columns in the table
            for(int j = numLocations-1; j >0; j--){
                //Pick a random index from 0 to j
                int k = r.nextInt(j+1);
                //swap array[i] with the element at random index
                short temp = populationArray[i][j];
                populationArray[i][j] = populationArray[i][k];
                populationArray[i][k] = temp;
            }
        }


        //Part 4: Start the loop which will count time which eco system will run for.
        long finish = System.currentTimeMillis() + 8_000; // set end of time 8 seconds after start.
        while (System.currentTimeMillis() < finish) {
            genCounter++;

            //Part 5:
            // a) Use the look up hash table in order to calculate the fitness of the route.
            for(int i = 0; i<populationSize; i++){
                double totalDistance = 0;

                int counter = 0;
                for(int j = 0; j<numLocations-1; j++){
                    short temp1 = (short) populationArray[i][j];
                    short temp2 = (short) populationArray[i][j+1];
                    totalDistance = totalDistance + Distance2DArray[temp1-1][temp2-1];
                }
                short temp3 = (short) populationArray[i][0];
                totalDistance = totalDistance + depotDistances[temp3-1];

                fitnessArray[i] = totalDistance;

                //b) Check to see if this is the best route / if so store for future return
                if(totalDistance < lowestDistance){
                    lowestDistance = totalDistance;
                    System.out.println("Lowest Distance: " + lowestDistance + "  Gen Number: " + genCounter);
                    for(int k = 0; k<numLocations; k++){
                        bestRoute[k] = populationArray[i][k];
                    }
                }
            }

            //Part 6: Normalise the fitness array so it is a percentage
            //a) divide fitness value by 1 to invert
            for(int i=0; i<populationSize; i++){
                double fitness = 1/(fitnessArray[i] + 1);
                fitnessArray[i] = fitness;
            }
            //b) sum all of the fitness values
            for(int i=0; i<populationSize; i++){
                totalFitness = totalFitness + fitnessArray[i];
            }
            //c) divide fitness values by total to be a percentage
            for(int i=0; i<populationSize; i++){
                fitnessArray[i] = fitnessArray[i] / totalFitness;
            }

            //Part 7: Randomly select routes based on their fitness score

            //a. create a deep copy (non-shallow) of my population array
            //need to have two copies as we will be selecting from and writing to array at same time
            short[][] tempPopulationArray = new short[populationSize][numLocations];
            tempPopulationArray = populationArray.clone();

            //b. selection to create a new generation.
            for(int i = 0; i<populationSize; i++){
                double ran = Math.random();
                int counter = 0;
                while(ran>0){
                    ran = ran-fitnessArray[counter];
                    counter++;
                }
                counter--;

                //c. copy the route
                for(int j=0; j<numLocations; j++){
                    populationArray[i][j] = tempPopulationArray[counter][j];
                }
            }

            //Part 8: Mutate the selected routes by swapping two random stops in the order
            for(int j = 0; j<1; j++) { //determines the rate of mutation / number of swaps per generation.
                for (int i = 0; i < populationSize; i++) {
                    int ran1 = r.nextInt(numLocations);
                    int ran2 = r.nextInt(numLocations);

                    short temp = populationArray[i][ran1];
                    populationArray[i][ran1] = populationArray[i][ran2];
                    populationArray[i][ran2] = temp;
                }
            }
            //reset sumDistance variable back to zero for next generation.
            //check to see what else needs to be reset
            totalFitness = 0;
        }//Close loop to allow for evolutionary period.

       for(int i = 0; i<numLocations; i++){
           System.out.println(bestRoute[i] + " ");
       }

       return bestRoute;
    }
}