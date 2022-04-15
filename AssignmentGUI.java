package assignment1;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

public class AssignmentGUI extends JFrame
{
	JTextField stoptext;
	JTextArea resultsarea;
	
    JLabel subheading, results; 
    
    JButton choosefileonebutton, choosefiletwobutton,submitbutton;
    
    JFrame filename;
    JFrame popupframe;
    
    JProgressBar resultsbar;
    
    ButtonListener choosefileonelistener;
    ButtonListener2 choosefiletwolistener;
    ButtonListener3 submitbuttonlistener;
    
    File fileone,filetwo,stopwords;

    ArrayList<String> fileonelist,filetwolist,stopwordlist;
    
    //class beginning 
    public AssignmentGUI() {
        
    	//Set title
        setTitle("Text Comparison application");
    	
        //Get the container
        Container container = getContentPane();

        //Set absolute layout        
        container.setLayout(null);  
        container.setBackground(Color.gray);
        
        //Creating heading label..... 
        final JLabel headinglabel =new JLabel("Input two files to see if they are about the same topic");
        headinglabel.setSize(350,20);
        container.add(headinglabel);
        headinglabel.setLocation(100,10);
        

        //Creating choose file one button
        choosefileonebutton =new JButton("Choose file one"); 
        //setting button position and size
        choosefileonebutton.setSize(125,30);
        choosefileonebutton.setLocation(100,35); 
        
        //set color
        choosefileonebutton.setBackground(Color.DARK_GRAY);
        choosefileonebutton.setForeground(Color.white);
        choosefileonebutton.setFocusPainted(false);
        container.add(choosefileonebutton); 
        choosefileonelistener=new ButtonListener();        
        choosefileonebutton.addActionListener(choosefileonelistener);
        
        
        //Creating choose file two button
        choosefiletwobutton =new JButton("Choose file two");  
        //set location and size
        choosefiletwobutton.setSize(125,30);
        choosefiletwobutton.setLocation(250,35);
        
        //set color 
        choosefiletwobutton.setBackground(Color.DARK_GRAY);
        choosefiletwobutton.setForeground(Color.white);
        choosefiletwobutton.setFocusPainted(false);
        
        container.add(choosefiletwobutton); //add to container
        //add button listener
        choosefiletwolistener = new ButtonListener2();        
        choosefiletwobutton.addActionListener(choosefiletwolistener);


        //Creating label to enter stop words..... 
        final JLabel stopwordslabel =new JLabel("Enter stop words:");
        stopwordslabel.setSize(100,20);
        container.add(stopwordslabel);
        stopwordslabel.setLocation(100,80);
        
        
        //TEXT for user input for stop words
        stoptext = new JTextField(10);
        stoptext.setSize(250,35);
        container.add(stoptext);
        stoptext.setLocation(105,100);


        //creating button for submission
        submitbutton = new JButton("Submit");
        //setting button size and position
        submitbutton.setSize(140,20);
        submitbutton.setLocation(150,150);
        submitbutton.setBackground(Color.DARK_GRAY);
        submitbutton.setForeground(Color.white);
        submitbutton.setFocusPainted(false);
        
        container.add(submitbutton); //adding to container
        //adding button listener
        submitbuttonlistener=new ButtonListener3();        
        submitbutton.addActionListener(submitbuttonlistener);
        
        
        //Creating label for results..... 
        JLabel resultslabel =new JLabel("Results:");
        resultslabel.setSize(100,20);
        container.add(resultslabel);
        resultslabel.setLocation(120,200);
        
        
        //creating results bar to display results
        resultsbar = new JProgressBar();
		resultsbar.setValue(0);//set value
		//setting position and size
		resultsbar.setSize(150,20);
		resultsbar.setLocation(180,200);
		resultsbar.setStringPainted(true);
		container.add(resultsbar);//adding to container
		
		
		//adding results area 
		resultsarea = new JTextArea("Results appear here...");
		resultsarea.setSize(250,60);
		resultsarea.setLocation(120,230);
		container.add(resultsarea);

    
        //Set the size of the window and display 
        setSize(550,350);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //adding stop words
        stopwords = new File("stops.txt");
        Scanner scanstops = null;
        stopwordlist = new ArrayList<String>();
		try {
			scanstops = new Scanner(stopwords);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (scanstops.hasNext()){ //input words into list
		    stopwordlist.add(scanstops.next());
		}
        
    }
    
    //FILE one OPENER 
    private class ButtonListener implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		
    		if (e.getSource() == choosefileonebutton) {
    			
    			//file chooser to open file explorer
    			JFileChooser filechooserone = new JFileChooser();
    			filechooserone.showOpenDialog(null); //select file to open
    			
    			//show file chosen
    			fileone = new File(filechooserone.getSelectedFile().getAbsolutePath());
    			JOptionPane.showMessageDialog(filename ,"You have chosen " + fileone);
    			
    			
    			//print contents
    			Scanner scanone = null;
    			fileonelist = new ArrayList<String>();
				try {
					scanone = new Scanner(fileone); //scan file variable
				} catch (FileNotFoundException e2) {
					// error handling
					e2.printStackTrace();
				}
				
				while (scanone.hasNext()){ //input words into list
				    fileonelist.add(scanone.next());
				}
				
				//remove stop words
				fileonelist.removeAll( stopwordlist );
				
				
	
    		}
    	}
    }
    
    //read file two
    private class ButtonListener2 implements ActionListener
    {
    	 public void actionPerformed(ActionEvent e)
    	 {
    		if (e.getSource() == choosefiletwobutton) {
    			
    			//file chooser
    			JFileChooser filechoosertwo = new JFileChooser();
    			filechoosertwo.showOpenDialog(null); //select file to open
    			
	    		//show file chosen
	 			filetwo = new File(filechoosertwo.getSelectedFile().getAbsolutePath()); 
	 			
	 			JOptionPane.showMessageDialog(filename ,"You have chosen " + filetwo);
	 			
	 			popupframe = new JFrame();
				if (fileone == filetwo) {
	    			JOptionPane.showMessageDialog(popupframe,"The files cannot be the same!");
	    			System.exit(0);
	    		}
				
	 			//print contents
	 			Scanner scantwo = null;
	 			filetwolist = new ArrayList<String>();
				try {
					scantwo = new Scanner(filetwo); //scan file variable
				} catch (FileNotFoundException e1) {
					// error handling
					e1.printStackTrace();
				}
				while (scantwo.hasNext()){ //input words into list
				    filetwolist.add(scantwo.next());
				}
				
				//remove stop words
				filetwolist.removeAll( stopwordlist );
				
				//remove words not in both files
				filetwolist.retainAll(fileonelist);
				
				

					
    		}
    	 }
    }
    
    private class ButtonListener3 implements ActionListener {
    	
    	public void actionPerformed(ActionEvent e)
   	 	{          
    		//add new stop words
    		String newstops = stoptext.getText();
			stopwordlist.add(newstops);
			JOptionPane.showMessageDialog(popupframe,newstops + " added to stop words list");
			
			//write stop words to stops.txt file
			try {
				FileWriter stopswriter = new FileWriter("stops.txt");
				stopswriter.write(newstops);
				stopswriter.close();
	          	} catch (IOException e3) {
	          		//TODO Auto-generated catch block
	          		e3.printStackTrace();
	          	}
			
			//double check stop words after new ones added
			filetwolist.removeAll( stopwordlist );
			fileonelist.removeAll( stopwordlist );
            
    	
    		
    		// IF STATEMENT
            if(filetwolist.size() == 0)
            {	
            	resultsbar.setValue(0);
            	resultsbar.setForeground(Color.RED);
            	resultsarea.setText("");
            	resultsarea.append("Your files are in no way similar.\nThe files are about different topics.\n The similar words have been written to \na local file.");

            }
            else if (filetwolist.size() <= 10) {
            	resultsbar.setValue(25);
            	resultsbar.setForeground(Color.RED);
            	resultsarea.setText("");
            	resultsarea.append("Your files are in no way similar.\nThe files are about different topics.\n The similar words have been written to a \nlocal file.");
            	
            }
            //small similarity
            else if(filetwolist.size() < 20)
            {
            	resultsbar.setValue(50);
            	resultsbar.setForeground(Color.ORANGE);
             	resultsarea.setText("");
             	resultsarea.append("Your files have a small similarity.\nThe similar words have\n been written to a local file.");
            }    
            // highly similar
            else if(filetwolist.size() >= 30)
            {
            	resultsbar.setValue(100);
            	resultsbar.setForeground(Color.GREEN);
             	resultsarea.setText("");
             	resultsarea.append("Your files are about the same topic.\nThe similar words have \nbeen written to a local file.");
             	
            }
            //error
            else
            {
            	resultsarea.setText("");
            	resultsarea.append("Error occured. Please Try again");
            }
           
            //write results
          try {
			FileWriter resultswriter = new FileWriter("results.txt");
			resultswriter.write("Your files had " + filetwolist.size() + " similar words\n");
			for (String word : filetwolist) {
				resultswriter.write(word + "\n");
			}
			resultswriter.close();
          	} catch (IOException e3) {
          		//TODO Auto-generated catch block
          		e3.printStackTrace();
          	}
   	 	}
    }
}
