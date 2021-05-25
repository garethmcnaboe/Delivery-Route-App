package com.company;

import java.util.Random;

public class Location {
	//keeps track of number of location objects
	static int numLocations = 0;
	//fields for location objects
	short orderNum;
	String address;
	double waitTime;
	double latitude;
	double longitude;

	//location constructor
	public Location(short orderNum, String address, double waitTime, double Latitude, double longitude) {
		this.orderNum = orderNum;
		this.address = address;
		this.waitTime = waitTime;
		this.latitude = Latitude;
		this.longitude = longitude;
	}

	//getter methods for the instance & class variables
	public short getOrderNum(){
		return this.orderNum;
	}

	public String getAddress(){
		return this.address;
	}

	public double getWaitTime(){
		return this.waitTime;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}
	    
	public static int getNumLocations() {
		return numLocations;
	}
	    
	    
	//processes the single string taken in into variables >> objects >> place into array
	public static Location[] ParseInput(String inputData) {
		//System.out.println(inputData.length());
			
		Location[] locationArray = new Location[100];

		//tracks characters in the input string.
		int charCounter = 0;
			
		boolean stillChecking = true;
			
		while(stillChecking) {
			//0 = invoiceNum / 1 = address / 2 = waitTime / 3 = latitude / 4 = longitude / 5 = create object
			short controlNum = 0;
			///Temp values
			String tempInvoiceNum = "";
			short tempInvoiceNum1 = 0;
			String tempAddress = "";
			String tempWaitTime = "";
			double tempWaitTime1 = 0.0;
			String tempLatitude = "";
			double tempLatitude1 = 0.0;
			String tempLongitude = "";
			double tempLongitude1 = 0.0;
			
			while(controlNum != 5) {
				//moves on the control counter after each value
				if(inputData.charAt(charCounter) == ',') {
					controlNum++;
				}
				if(inputData.charAt(charCounter)==' ' && controlNum==4 && tempLongitude.length() > 0) {
					controlNum++;
				}
						
				if(inputData.charAt(charCounter) != ',') {
					//adds char to the tempInvoiceNum String
					if(controlNum == 0 && inputData.charAt(charCounter) != ' ') {
						tempInvoiceNum = tempInvoiceNum + inputData.charAt(charCounter);
					}
							
					//adds char to the tempAddress String
					if(controlNum == 1) {
						tempAddress = tempAddress + inputData.charAt(charCounter);
					}
							
					//adds char to the tempWaitTime
					if(controlNum == 2 && inputData.charAt(charCounter) != ' ') {
						tempWaitTime = tempWaitTime + inputData.charAt(charCounter);
					}
							
					//adds char to the tempLatitude
					if(controlNum == 3 && inputData.charAt(charCounter) != ' ') {
						tempLatitude = tempLatitude + inputData.charAt(charCounter);
					}
							
					//adds char to the Longitude
					if(controlNum == 4 && inputData.charAt(charCounter) !=' ') {
						tempLongitude = tempLongitude + inputData.charAt(charCounter);
						if(charCounter+1 == inputData.length()) {
									controlNum++;
						}
					}
				}
					
				//creates object and places in array.
				if(controlNum == 5) {
					//convert into appropriate data types
					tempInvoiceNum1 = Short.parseShort(tempInvoiceNum);
					tempWaitTime1 = Double.parseDouble(tempWaitTime);
					tempLatitude1 = Double.parseDouble(tempLatitude);
					tempLongitude1 = Double.parseDouble(tempLongitude);
						
					//creates the location object and places into the location Array
					locationArray[numLocations] = new Location(tempInvoiceNum1, tempAddress, tempWaitTime1, tempLatitude1, tempLongitude1);
							
					//increment the array counter
					numLocations++;
				}
						
				if(charCounter+1 == inputData.length()) {
					stillChecking = false;
					break;
				}
				if(controlNum != 5) {
						charCounter++;
				}
			}
		}
		return locationArray;
	}

	//Method to sort the location array into the same order as the genetic algorithm output
	public static Location[] reorganise(Location[] locationArray, int numLocations, short[] routeArrayShort){
		Location[] tempArray = locationArray.clone();

		for(int i=0; i<numLocations; i++){
			short temp = routeArrayShort[i];
			int counter = 0;
			while(temp != tempArray[counter].orderNum){
				counter++;
			}
			locationArray[i] = tempArray[counter];
		}
		return locationArray;
	}

	//method which randomly shuffles the locationArray
	//Uses the Fisher-Yates Shuffle
	public static Location[] shuffleArray(Location[] locationArray, int numLocations){
		Random rnd = new Random();

		for(int i = numLocations - 1; i>0; i--){

			int j = rnd.nextInt(i+1);

			//swap entries in the array
			Location temp = locationArray[i];
			locationArray[i] = locationArray[j];
			locationArray[j] = temp;
		}
		return locationArray;
	}

	//Method which is used to create the delivery sequence in a string which can be displayed in the interface
	public static String createDeliverySequence(Location[] locationArray, int numLocations){
		String deliverySequence = "";

		for(int i = 0; i<numLocations; i++) {
			deliverySequence = deliverySequence + locationArray[i].orderNum + ",";
		}
		deliverySequence = deliverySequence.substring(0, deliverySequence.length()-1);

		return deliverySequence;
		}
	}