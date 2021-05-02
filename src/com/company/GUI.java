package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends WindowAdapter implements ActionListener {
	
	JFrame frame;
	JLabel lbl_heading, lbl_deliveryseq, lbl_totalbadminutes, lbl_nextlocnum, lbl_nextlocaddress, 
	lbl_nextlocation, lbl_latitude, lbl_longitude, lbl_nextlocationbearing, lbl_nextlocationdistance,
	lbl_counter, lbl_total;
	JTextField tf_totalbadminutes, tf_nextlocnum, tf_nextlocaddress, 
	tf_latitude, tf_longitude, tf_nextlocationbearing, tf_nextlocationdistance, tf_counter, tf_total;
	JTextArea ta_deliveryseq;
	JButton button1, button2, button3;
	
	int counter = 1;
	int numLocations;
	
	GUI(){
		frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(600, 650);
		frame.setTitle("Gareth McNaboe 20252984 Delivery App");
		frame.getContentPane().setBackground(Color.gray);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLayout(null);
		frame.addWindowListener(this);
		
		lbl_heading = new JLabel("Apache Pizza Maynooth");
		lbl_heading.setFont(new Font("Arial", Font.BOLD, 28));
		lbl_heading.setForeground(Color.yellow);
		lbl_heading.setBounds(70, 20, 400, 40);
		frame.add(lbl_heading);	
		
		button1 = new JButton("Enter Order Information");
		button1.setBounds(10, 65, 180, 35);
		frame.add(button1);	
		
		button2 = new JButton("Next Delivery");
		button2.setBounds(190, 65, 140, 35);
		frame.add(button2);
		
		button3 = new JButton("Previous Delivery");
		button3.setBounds(330, 65, 150, 35);
		frame.add(button3);
		
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
	
		//display of delivery sequence
		lbl_deliveryseq = new JLabel("Full Delivery Sequence"); 
		lbl_deliveryseq.setBounds(20, 125, 140, 25);
		frame.add(lbl_deliveryseq);
		
		ta_deliveryseq = new JTextArea();
		ta_deliveryseq.setBounds(160, 125, 300, 100);
		ta_deliveryseq.setLineWrap(true);
		frame.add(ta_deliveryseq);
		
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
		tf_nextlocnum.setBounds(220, 320, 50, 25);
		frame.add(tf_nextlocnum);
		
		//display of next delivery address
		lbl_nextlocaddress = new JLabel("Next Delivery Address"); 
		lbl_nextlocaddress.setBounds(20, 360, 200, 25);
		frame.add(lbl_nextlocaddress);
		
		tf_nextlocaddress = new JTextField();
		tf_nextlocaddress.setBounds(220, 360, 250, 25);
		frame.add(tf_nextlocaddress);
		
		//display of next delivery co-ordinates
		lbl_nextlocation = new JLabel("Next Location Co-ordinates");
		lbl_nextlocation.setBounds(20, 400, 200, 25);
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
		lbl_nextlocationbearing = new JLabel("Compass Bearing (degrees)");
		lbl_nextlocationbearing.setBounds(20, 480, 200, 25);
		frame.add(lbl_nextlocationbearing);
		
		tf_nextlocationbearing = new JTextField();
		tf_nextlocationbearing.setBounds(220, 480, 100, 25);
		frame.add(tf_nextlocationbearing);
		
		//display distance to next delivery location
		lbl_nextlocationdistance = new JLabel("Distance to Next Delivery (meters)");
		lbl_nextlocationdistance.setBounds(20, 520, 200, 25);
		frame.add(lbl_nextlocationdistance);
		
		tf_nextlocationdistance = new JTextField();
		tf_nextlocationdistance.setBounds(220, 520, 100, 25);
		frame.add(tf_nextlocationdistance);
	
	}	
	
	//Method used to check if user wants to close down the application.
	public void windowClosing(WindowEvent e) {
		int a = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit the app");
			if(a==JOptionPane.YES_OPTION) {
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == button1) {
			//System.out.println("GUI Method 1 called");
			
			String inputData = JOptionPane.showInputDialog(null,"Enter Delivery Info String");
			//System.out.println(inputData);
			
			Location [] locationArray = new Location[100]; 
			locationArray = Location.ParseInput(inputData);
			
			numLocations = Location.getNumLocations();
			
			EdgeHashTable.populateHashTable(locationArray, numLocations);
			EdgeHashTable.printHashTable();
			
			/*
			for(int i = 0; i < 40; i++) {	
				System.out.print(locationArray[i].getOrderNum() + " ");
				System.out.print(locationArray[i].getAddress() + " ");
				System.out.print(locationArray[i].getWaitTime()+ " ");
				System.out.print(locationArray[i].getLatitude() + " ");
				System.out.print(locationArray[i].getLongitude()+ " ");
				System.out.print(i);
				System.out.println();
			}
			*/
			
			ta_deliveryseq.setText("0");
			tf_totalbadminutes.setText("0");
			tf_counter.setText(Integer.toString(counter)); 
			tf_total.setText(Integer.toString(numLocations));
			tf_nextlocnum.setText("0");
			tf_nextlocaddress.setText("0"); 
			tf_latitude.setText("0");
			tf_longitude.setText("0");
			tf_nextlocationbearing.setText("0");
			tf_nextlocationdistance.setText("0");	
		}
		
		if(e.getSource()== button2) {
			//System.out.println("GUI Method 2 called");
			if(counter<Location.getNumLocations()) {
			counter++;			
			}
			tf_counter.setText(Integer.toString(counter)); 
			
			tf_nextlocnum.setText("1");
			tf_nextlocaddress.setText("1"); 
			tf_latitude.setText("1");
			tf_longitude.setText("1");
			tf_nextlocationbearing.setText("1");
			tf_nextlocationdistance.setText("1");
		}
		
		if(e.getSource()== button3) {
			//System.out.println("GUI Method 3 called");
			
			if(counter>1) {
			counter--;
			}
			tf_counter.setText(Integer.toString(counter)); 
		
			tf_nextlocnum.setText("2");
			tf_nextlocaddress.setText("2"); 
			tf_latitude.setText("2");
			tf_longitude.setText("2");
			tf_nextlocationbearing.setText("2");
			tf_nextlocationdistance.setText("2");	
		}
		
	}
	
}