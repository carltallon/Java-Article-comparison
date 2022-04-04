package assignment1;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AssignmentGUI extends JFrame
{
	JTextField stoptext;
	JTextArea resultsarea;
	
    JLabel subheading, results; 
    
    JButton choosefileonebutton, choosefiletwobutton,submitbutton;
    
    //JFrame container;
    JFrame filename;
    
    
    JProgressBar resultsbar;
    
    ButtonListener choosefileonelistener;
    ButtonListener2 choosefiletwolistener;
    ButtonListener3 submitbuttonlistener;
    
    File fileone,filetwo,stopwords;

    ArrayList<String> fileonelist,filetwolist,stopwordlist;
    

    
    public AssignmentGUI() {
        
    	//Set title
        setTitle("Text Comparison application");
    	
        //Get the container
        Container container = getContentPane();

        //Set absolute layout        
        container.setLayout(null);  
        
        //Creating heading label..... 
        JLabel headinglabel =new JLabel("Input two files to see if they are about the same topic");
        headinglabel.setSize(350,20);
        container.add(headinglabel);
        headinglabel.setLocation(100,10);
        

        //Creating choose file one button
        choosefileonebutton =new JButton("Choose file one "); 
        //setting button position and size
        choosefileonebutton.setSize(125,30);
        choosefileonebutton.setLocation(100,35); 
        container.add(choosefileonebutton); 
        choosefileonelistener=new ButtonListener();        
        choosefileonebutton.addActionListener(choosefileonelistener);
        
        
        //Creating choose file two button
        choosefiletwobutton =new JButton("Choose file two ");  
        //set location and size
        choosefiletwobutton.setSize(125,30);
        choosefiletwobutton.setLocation(250,35); 
        container.add(choosefiletwobutton); //add to container
        //add button listener
        choosefiletwolistener = new ButtonListener2();        
        choosefiletwobutton.addActionListener(choosefiletwolistener);


        //Creating label to enter stop words..... 
        JLabel stopwordslabel =new JLabel("Enter stop words:");
        stopwordslabel.setSize(100,20);
        container.add(stopwordslabel);
        stopwordslabel.setLocation(100,80);
        
        
        //TEXT for user input for stop words
        stoptext = new JTextField(10);
        stoptext.setSize(250,35);
        container.add(stoptext);
        stoptext.setLocation(105,100);


        //creating button for submission
        submitbutton =new JButton("Submit");
        //setting button size and position
        submitbutton.setSize(150,40);
        submitbutton.setLocation(150,150);
        container.add(submitbutton); //adding to container
        //adding button listener
        submitbuttonlistener=new ButtonListener3();        
        submitbutton.addActionListener(submitbuttonlistener);
        
        
        //Creating label for results..... 
        JLabel resultslabel =new JLabel("Results:");
        resultslabel.setSize(100,20);
        container.add(resultslabel);
        resultslabel.setLocation(120,220);
        
        
        //creating results bar to display results
        resultsbar = new JProgressBar();
		resultsbar.setValue(0);//set value
		//setting position and size
		resultsbar.setSize(200,20);
		resultsbar.setLocation(180,220);
		resultsbar.setStringPainted(true);
		container.add(resultsbar);//adding to container
		
		
		//adding results area 
		resultsarea = new JTextArea("Results appear here...");
		resultsarea.setSize(250,40);
		resultsarea.setLocation(120,250);
		container.add(resultsarea);

    
        //Set the size of the window and display 
        setSize(550,350);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //adding stop words
        stopwords = new File("C:\\Users\\carlt\\OneDrive - Technological University Dublin\\Documents\\stops.txt");
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
				System.out.println(fileonelist);
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
    			JFileChooser filechoosertwo = new JFileChooser();
    			filechoosertwo.showOpenDialog(null); //select file to open
    			
	    		//show file chosen
	 			filetwo = new File(filechoosertwo.getSelectedFile().getAbsolutePath()); 
	 			
	 			JOptionPane.showMessageDialog(filename ,"You have chosen " + filetwo);
	 			
	 			
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
					System.out.println(filetwolist);
					filetwolist.removeAll( stopwordlist );
					
					filetwolist.retainAll(fileonelist);
					System.out.println(filetwolist);

					
    		}
    	 }
    }
    
    private class ButtonListener3 implements ActionListener {
    	
    	public void actionPerformed(ActionEvent e)
   	 	{
    		String newstops = stoptext.getText();
            
    		try {
    			FileWriter stopswriter = new FileWriter("C:\\Users\\carlt\\OneDrive - Technological University Dublin\\Documents\\stops.txt");
    			stopswriter.write(newstops);
    			stopswriter.close();
    			System.out.println("Successfully wrote to file");
    		} catch (IOException e3) {
    			//TODO Auto-generated catch block
    			e3.printStackTrace();
    		}
    		
    		
    		
    		int equal = 0;
    		
    		if (filetwolist.size() > 5) {
    			equal = 25;
    		}
    		else if (filetwolist.size() > 10) {
    			equal = 50;
    		}
    		else if (filetwolist.size() > 15) {
    			equal = 75;
    		}
    		else if (filetwolist.size() > 20) { 
    			equal = 100;
    		}
    		
    		// IF STATEMENT
            if(equal == 0)
            {	
            	resultsbar.setValue(0);
            	resultsbar.setForeground(Color.RED);
            	resultsarea.setText("");
            	resultsarea.append("Your files are in no way similar. The files are about different topics. The similar words were :");
            	//resultsarea.append(filetwolist);
            	
            	
            }
            else if (equal < 25) {
            	
            	int i = 0;
            	while (i < 25) {
            		resultsbar.setValue(i);
            		i++;
            	
            	}
            	resultsbar.setForeground(Color.RED);
            	resultsarea.setText("");
            	resultsarea.append("Your files are in no way similar. The files are about different topics. The similar words were :");
            	
            }
            //small similarity
            else if(equal < 50)
            {
            	int i = 0;
            	while (i < 50) {
            		resultsbar.setValue(i);
            		i++;
            	}
            	resultsbar.setForeground(Color.ORANGE);
             	resultsarea.setText("");
             	resultsarea.append("Your files have a small similarity. Your files are probably about the same topic. The similar words were :");
            }    
            // highly similar
            else if(equal > 75)
            {
            	int i = 0;
            	while (i < 75) {
            		resultsbar.setValue(i);
            	}
   
            	resultsbar.setForeground(Color.GREEN);
             	resultsarea.setText("");
             	resultsarea.append("Your files are about the same topic. The similar words were :");
             	
            }
            //error
            else
            {
            	resultsarea.setText("");
            	resultsarea.append("Error occured. Please Try again");
            }
   	 	}
    }
}
