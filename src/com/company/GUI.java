package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


//class contains the main graphical user interface
public class GUI extends JPanel implements ActionListener, WindowListener {

	JFrame frame;
	JLabel lbl_heading, lbl_deliveryseq, lbl_totaldistance, lbl_totalbadminutes, lbl_nextlocnum, lbl_nextlocaddress,
	lbl_nextlocation, lbl_latitude, lbl_longitude, lbl_nextlocationbearing, lbl_nextlocationdistance,
	lbl_counter, lbl_total, lbl_nextdeliverykey, lbl_subdeliverykey;
	JTextField tf_totaldistance, tf_totalbadminutes, tf_nextlocnum, tf_nextlocaddress,
	tf_latitude, tf_longitude, tf_nextlocationbearing, tf_nextlocationdistance, tf_counter, tf_total;
	JTextArea ta_deliveryseq;
	JButton button1, button2, button3;

	double totalDistance, totalBadMin, nextlocationdistance;

	final double HOMEDEPOTLAT = 53.38197;
	final double HOMEDEPOTLONG = -6.59274;
	String HOMEDEPOTADDRESS = "3 Mill Street, Maynooth, Co. Kildare";
	double lat1, long1, lat2, long2, lat3, long3, lat4, long4;

	double[] depotDistances;
	double[][] distance2DArray;
	Edge [] routeArrayEdge;
	int counter = 1;
	int numLocations;
	Location homeDepot;
	Location [] locationArray;
	short [] greedyArrayShort,routeArrayShort;
	String deliverySequence;

	StaticGraphics graphic1;
	DynamicGraphics graphic2;

	GUI(){
		frame = new JFrame();

		frame.setSize(1500, 900);
		frame.setTitle("Gareth McNaboe 20252984 Delivery App");
		frame.getContentPane().setBackground(Color.gray);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLayout(null);
		frame.addWindowListener(this);

		lbl_heading = new JLabel("Apache Pizza Maynooth");
		lbl_heading.setFont(new Font("Arial", Font.BOLD, 28));
		lbl_heading.setForeground(Color.yellow);
		lbl_heading.setBounds(90, 20, 400, 40);
		frame.add(lbl_heading);	
		
		button1 = new JButton("Enter Order Information");
		button1.setBounds(20, 65, 180, 35);
		frame.add(button1);	
		
		button2 = new JButton("Next Delivery");
		button2.setBounds(200, 65, 140, 35);
		frame.add(button2);
		
		button3 = new JButton("Previous Delivery");
		button3.setBounds(340, 65, 150, 35);
		frame.add(button3);
		
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
	
		//display of delivery sequence
		lbl_deliveryseq = new JLabel("Full Delivery Sequence"); 
		lbl_deliveryseq.setBounds(20, 125, 140, 25);
		frame.add(lbl_deliveryseq);

		ta_deliveryseq = new JTextArea();
		ta_deliveryseq.setBounds(160, 125, 300, 60);
		ta_deliveryseq.setLineWrap(true);
		frame.add(ta_deliveryseq);

		//display of total distance covered
		lbl_totaldistance = new JLabel("Total Distance Covered");
		lbl_totaldistance.setBounds(20, 200, 140, 25);
		frame.add(lbl_totaldistance);

		tf_totaldistance = new JTextField(50);
		tf_totaldistance.setBounds(160, 200, 100, 25);
		frame.add(tf_totaldistance);

		//display of total bad minutes
		lbl_totalbadminutes = new JLabel("Total Bad Minutes"); 
		lbl_totalbadminutes.setBounds(20, 240, 140, 25);
		frame.add(lbl_totalbadminutes);
		
		tf_totalbadminutes = new JTextField(50);
		tf_totalbadminutes.setBounds(160, 240, 100, 25);
		frame.add(tf_totalbadminutes);
		
		//displays counter for which order is displayed
		lbl_counter = new JLabel("Order");
		lbl_counter.setBounds(20, 280, 50, 25);
		frame.add(lbl_counter);
		
		tf_counter = new JTextField();
		tf_counter.setBounds(70, 280, 50, 25);
		frame.add(tf_counter);
		
		lbl_total = new JLabel("of");
		lbl_total.setBounds(140, 280, 30, 25);
		frame.add(lbl_total);
		
		tf_total = new JTextField();
		tf_total.setBounds(160, 280, 50, 25);
		frame.add(tf_total);
		
		//display of next delivery order number
		lbl_nextlocnum = new JLabel("Next Location Number");
		lbl_nextlocnum.setBounds(20, 320, 200, 25);
		frame.add(lbl_nextlocnum);
	
		tf_nextlocnum = new JTextField();
		tf_nextlocnum.setBounds(210, 320, 50, 25);
		frame.add(tf_nextlocnum);
		
		//display of next delivery address
		lbl_nextlocaddress = new JLabel("Next Delivery Address"); 
		lbl_nextlocaddress.setBounds(20, 360, 200, 25);
		frame.add(lbl_nextlocaddress);
		
		tf_nextlocaddress = new JTextField();
		tf_nextlocaddress.setBounds(210, 360, 250, 25);
		frame.add(tf_nextlocaddress);
		
		//display of next delivery co-ordinates
		lbl_nextlocation = new JLabel("Next Location Co-ordinates & Directions");
		lbl_nextlocation.setBounds(20, 400, 300, 25);
		frame.add(lbl_nextlocation);
		
		lbl_latitude = new JLabel("Latitude");
		lbl_latitude.setBounds(20, 440, 50, 25);
		frame.add(lbl_latitude);
		
		tf_latitude = new JTextField();
		tf_latitude.setBounds(80, 440, 70, 25);
		frame.add(tf_latitude);
		
		lbl_longitude = new JLabel("Longitude");
		lbl_longitude.setBounds(160, 440, 60, 25);
		frame.add(lbl_longitude);
		
		tf_longitude = new JTextField();
		tf_longitude.setBounds(220, 440, 70, 25);
		frame.add(tf_longitude);
		
		//display of compass bearing to next delivery location
		lbl_nextlocationbearing = new JLabel("Initial Compass Bearing (degrees)");
		lbl_nextlocationbearing.setBounds(20, 480, 200, 25);
		frame.add(lbl_nextlocationbearing);
		
		tf_nextlocationbearing = new JTextField();
		tf_nextlocationbearing.setBounds(220, 480, 100, 25);
		frame.add(tf_nextlocationbearing);
		
		//display distance to next delivery location
		lbl_nextlocationdistance = new JLabel("Distance to Next Delivery (Km)");
		lbl_nextlocationdistance.setBounds(20, 520, 200, 25);
		frame.add(lbl_nextlocationdistance);
		
		tf_nextlocationdistance = new JTextField();
		tf_nextlocationdistance.setBounds(220, 520, 100, 25);
		frame.add(tf_nextlocationdistance);

		//display the key for next and subsequent delivery
		lbl_nextdeliverykey = new JLabel("Next Delivery");
		lbl_nextdeliverykey.setBounds(20, 560, 150, 25);
		frame.add(lbl_nextdeliverykey);

		lbl_subdeliverykey = new JLabel("Subsequent Delivery");
		lbl_subdeliverykey.setBounds(20, 600, 150, 25);
		frame.add(lbl_subdeliverykey);

		graphic1 = new StaticGraphics();
		graphic1.setBounds(170, 570, 200, 100);
		graphic1.setOpaque(false);
		frame.add(graphic1);

		//display the map
		graphic2 = new DynamicGraphics(0, 0 , 0, 0,0 ,0,0,0 );
		graphic2.setBounds(525, 30, 910,736);
		graphic2.setOpaque(true);
		frame.add(graphic2);

		//set the panel as visible after everything is visible.
		frame.setVisible(true);
	}

	//Method used to check if user wants to close down the application.
	public void windowClosing(WindowEvent e) {
		int a = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit the app");
			if(a==JOptionPane.YES_OPTION) {
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
	}

	//method which is called whenever one of the buttons are pressed
	@Override
	public void actionPerformed(ActionEvent e) {

		//runs when the enter order information button is pressed.
		if(e.getSource() == button1) {
			JFrame component1 = new JFrame();
			component1.setSize(500, 500);
			String inputData = JOptionPane.showInputDialog(component1,"Enter Delivery Info String");
			procesInputData(inputData);
			setPermanentTextFields();
			refreshTextFields();
			refreshMapDetails();
		}

		//runs if the next button is pressed
		if(e.getSource()== button2) {

			if(counter<Location.getNumLocations()) {
			counter++;			
			}
			refreshTextFields();
			refreshMapDetails();
		}

		//run if the previous button is pressed
		if(e.getSource()== button3) {
			
			if(counter>1) {
			counter--;
			}
			refreshTextFields();
			refreshMapDetails();
		}
		
	}

	//method which sets the co-ordinates for mapping of next & subsequent deliveries
	public void refreshMapDetails(){
		if(counter == 1){
			lat1 = HOMEDEPOTLAT;
			long1 = HOMEDEPOTLONG;
		}
		else{
			lat1 = locationArray[counter-2].latitude;
			long1 = locationArray[counter-2].longitude;
		}
		lat2 = locationArray[counter-1].latitude;
		long2 = locationArray[counter-1].longitude;

		if(counter == 40){
			lat3 = 0;
			long3 = 0;
			lat4 = 0;
			long4 = 0;
		}
		else{
			lat3 = locationArray[counter-1].latitude;
			long3 = locationArray[counter-1].longitude;
			lat4 = locationArray[counter].latitude;
			long4 = locationArray[counter].longitude;
		}

		graphic2.setCoordinates(lat1, long1, lat2, long2, lat3, long3, lat4, long4);
		graphic2.setArray(graphic2);
		graphic2.repainter();
	}

	//Sets the Text Fields in the GUI which do not change
	public void setPermanentTextFields(){
		ta_deliveryseq.setText(deliverySequence);
		tf_totaldistance.setText(Double.toString(totalDistance));
		tf_totalbadminutes.setText(Double.toString(totalBadMin));
		tf_total.setText(Integer.toString(numLocations));
	}

	//Sets the Text Fields in the GUI which do change
	public void refreshTextFields(){
		tf_counter.setText(Integer.toString(counter));
		tf_nextlocnum.setText(Integer.toString(locationArray[counter-1].orderNum));
		tf_nextlocaddress.setText(locationArray[counter-1].address);
		tf_latitude.setText(Double.toString(locationArray[counter-1].latitude));
		tf_longitude.setText(Double.toString(locationArray[counter-1].longitude));
		tf_nextlocationbearing.setText(Double.toString(routeArrayEdge[counter-1].bearing));
		nextlocationdistance = (double) Math.round(routeArrayEdge[counter-1].distance * 100)/100;
		tf_nextlocationdistance.setText(Double.toString(nextlocationdistance));
	}

	//Takes the input string and calls various methods to process it and produce a route & data display in the Text Fields
	public void procesInputData(String inputData){
		//Creates an array of 100 Location Objects and then populates it
		locationArray = new Location[101];
		locationArray = Location.ParseInput(inputData);
		//Gets the total number of locations generated
		numLocations = Location.getNumLocations();
		//Call method to calculate 2d array of distances and distances to the depot
		distance2DArray = EdgeHashTable.populateHashTable(locationArray,numLocations);
		depotDistances = EdgeHashTable.populateDepotDistances(locationArray, numLocations,HOMEDEPOTADDRESS, HOMEDEPOTLAT, HOMEDEPOTLONG);
		//Calls method which runs the greedy algorithm
		greedyArrayShort = Greedy.algorithm(numLocations, distance2DArray, depotDistances, locationArray);
		//Calls method which runs the 2opt algorithm
		routeArrayShort = TwoOpt.algorithm(numLocations, greedyArrayShort, distance2DArray, depotDistances, locationArray);
		//Calls method which sorts the locationArray into the order of the routeArrayShort
		locationArray = Location.reorganise(locationArray,numLocations,routeArrayShort);
		//Calls method to create delivery sequence String
		deliverySequence = Location.createDeliverySequence(locationArray, numLocations);
		//create an array of Edge objects
		routeArrayEdge = Edge.createRouteArray(locationArray, numLocations, HOMEDEPOTADDRESS, HOMEDEPOTLAT, HOMEDEPOTLONG);
		//calls a method to calculate the total distance covered
		totalDistance = Edge.calculateTotalDistance(routeArrayEdge, numLocations);
		//calls a method to calculate the total bad minutes (i.e. mins over 30)
		totalBadMin = Edge.calculateTotalBadMin(routeArrayEdge, numLocations);
	}

	@Override
	public void windowOpened(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}
}