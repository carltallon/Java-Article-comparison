package assignment1;

import javax.swing.*;



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
	
    JLabel subheading; 

    
    JButton choosefileone;
    JButton choosefiletwo;
    
    JFrame container;
    
    JLabel results;
    JProgressBar resultsbar;
    
    JFrame filename;
    
    ButtonListener choosefileonelistener;
    ButtonListener2 choosefiletwolistener;
    ButtonListener3 submitbuttonlistener;
    
    File fileone;
    File filetwo;
    ArrayList<String> fileonelist;
    ArrayList<String> filetwolist;
    
    File stopwords;
    ArrayList<String> stopwordlist;
    
}
    
    public AssignmentGUI() {
    	//Set title
        setTitle("Text Comparison application");
        
        //JFRAME CONTAINER WITH FLOW LAYOUT
        container = new JFrame();
        FlowLayout containerlayout = new FlowLayout(FlowLayout.CENTER);
        container.setLayout(containerlayout);
         
        
        
        //Creating label input files...
        subheading = new JLabel();
        subheading.setText("Input two files to check if they are similar:");
        
        container.add(subheading);

        
        //creating a choose file one button
        choosefileone = new JButton("Choose file one");    
        container.add(choosefileone); 
        choosefileonelistener =new ButtonListener();        
        choosefileone.addActionListener(choosefileonelistener);
        
        //creating a choose file two button
        choosefiletwo=new JButton("Choose file two");     
        container.add(choosefiletwo); 
        choosefiletwolistener = new ButtonListener2();        
        choosefiletwo.addActionListener(choosefiletwolistener);
        
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
        
        //option to add more stop words
        JLabel stopwordslabel = new JLabel("Add new words to the stops list:");
        container.add(stopwordslabel);
        //TEXT for user input
        stoptext=new JTextField(45);
        container.add(stoptext);
       
		
        //creating button for submission
        JButton submitbutton = new JButton("Submit");
        container.add(submitbutton); 
        submitbuttonlistener = new ButtonListener3();        
        submitbutton.addActionListener(submitbuttonlistener);
        
        //SHOW RESULTS 
        results = new JLabel("results:");
		container.add(results);
		results.setSize(420,420);
		
		resultsbar = new JProgressBar();
		resultsbar.setValue(0);
		resultsbar.setBounds(0,0,420,50);
		resultsbar.setStringPainted(true);
		container.add(resultsbar);
		
		
        //Set the size of the window and display 
        container.setSize(550,350);
        container.setResizable(false);
        container.setVisible(true);
        container.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
    }
    
    //FILE one OPENER 
    private class ButtonListener implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		
    		if (e.getSource() == choosefileone) {
    			
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

    			   			
    			
    		}
    	}
    }
    
    //read file two
    private class ButtonListener2 implements ActionListener
    {
    	 public void actionPerformed(ActionEvent e)
    	 {
    		if (e.getSource() == choosefiletwo) {
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
    			// TODO Auto-generated catch block
    			e3.printStackTrace();
    		}
    		int equal = 0;
    		
    		
    		// IF STATEMENT
            if(equal == 0)
            {	
            	resultsbar.setValue(0);
            	resultsbar.setForeground(Color.RED);
            	JOptionPane.showMessageDialog(resultsframe,"Your files are in no way similar");
            	
            }
            //small similarity
            else if(equal < 50)
            {
            	resultsbar.setValue(50);
            	resultsbar.setForeground(Color.ORANGE);
             	JOptionPane.showMessageDialog(resultsframe,"Your files have a small similarity.");
            }    
            // highly similar
            else if(equal > 70)
            {
            	resultsbar.setValue(70);
            	resultsbar.setForeground(Color.GREEN);
             	JOptionPane.showMessageDialog(resultsframe, "Your files are very similar.");
            }
            //error
            else
            {
             	JOptionPane.showMessageDialog(resultsframe,"Error occured.");
            }
   	 	}
    }
}
