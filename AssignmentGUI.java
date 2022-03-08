package assignment1;

import javax.swing.*;

import lab6.GUIlab6;
import lab6.GUIlab6.ButtonListener;
import lab6.GUIlab6.ButtonListener2;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AssignmentGUI extends JFrame
{
	
	JTextField text1;
    JLabel label1; 
    JButton choosefileone;
    JButton choosefiletwo;
    ButtonListener choosefileonelistener;
    ButtonListener2 choosefiletwolistener;
    
    public AssignmentGUI() {
    	//Set title
        setTitle("Text Comparison application");
        
        //Get the container
        Container container = getContentPane();
        
        //Set absolute layout        
        container.setLayout(null);   
        
        
        //Creating label input files...
        JLabel subheading =new JLabel("Input two files to check if they are similar:");
        subheading.setSize(270,20);
        container.add(subheading);
        subheading.setLocation(100,35);
        
        //creating a choose file one button
        choosefileone =new JButton("Choose file one");    
        choosefileone.setSize(150,20);
        choosefileone.setLocation(100,60); 
        container.add(choosefileone); 
        choosefileonelistener =new ButtonListener();        
        choosefileone.addActionListener(choosefileonelistener);
        
        //creating a choose file two button
        choosefiletwo=new JButton("Choose file two");    
        choosefiletwo.setSize(150,20);
        choosefiletwo.setLocation(300,60); 
        container.add(choosefiletwo); 
        choosefiletwolistener =new ButtonListener2();        
        choosefiletwo.addActionListener(choosefiletwolistener);
        
        
        //creating button for submission
        JButton submitbutton = new JButton("Submit");
        submitbutton.setSize(140,40);
        submitbutton.setLocation(150,100);
        container.add(submitbutton); 
        
        
        //Set the size of the window and display 
        setSize(550,350);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
    }
    
    //FILE one OPENER 
    private class ButtonListener implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		
    		if (e.getSource() == choosefileone) {
    			
    			JFileChooser filechooser = new JFileChooser();

    			filechooser.showOpenDialog(null); //select file to open
    			
    			//file one scan 
    			File fileone = new File("");
    			Scanner scanone = null;
				try {
					scanone = new Scanner(fileone); //scan file variable
				} catch (FileNotFoundException e2) {
					// error handling
					e2.printStackTrace();
				}
    			System.out.println(scanone.nextLine());
    			   			
    			
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
    			
	    		//file two scan
	 			File filetwo = new File("");
	 			Scanner scantwo = null;
					try {
						scantwo = new Scanner(filetwo); //scan file variable
					} catch (FileNotFoundException e1) {
						// error handling
						e1.printStackTrace();
					}
	 			System.out.println(scantwo.nextLine()); //print file
    		}
    	 }
    }
    
    //Initialize the class
    public static void main(String[] args)
    {
        new AssignmentGUI();
    }

}
