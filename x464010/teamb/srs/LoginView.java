package x464010.teamb.srs;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Login to the System.  Checks Username and Password.
 * @author rebeccachappel
 * @author Amit Dhamija
 * @version 1.1
 */
public class LoginView {
	
	public void getInput() {
		Scanner userInputScanner;
		
		System.out.println("Please enter your Student ID and Password");
		
		try {
			// set up Scanner object using system.in
			userInputScanner = new Scanner(System.in);
			
			System.out.print("Student ID: ");
			String enteredId = userInputScanner.next();
			System.out.print("Password: ");
			String enteredPassword = userInputScanner.next();
			
			validateInput();
			userInputScanner.close();
			
		} catch (InputMismatchException e) {
			System.out.println("Error! Incorrect input type: " + e.getMessage());
		}
		/*
		int entered_ID;
		String entered_password;
		File studentFile;

        Scanner fileScanner;
        Scanner input = new Scanner (System.in);
        
        try {
        	// Read in Student.txt information
		    studentFile = new File ("Student.txt");
		    fileScanner = new Scanner(studentFile);
		    
		    // Prompt for input of Student ID and Password
		    System.out.println("Student ID:");
		    System.out.println("Password:");
		    
		    // Assign Input 
		    entered_ID = input.nextInt();
		    entered_password = input.nextLine();
    
    
		    // Determine if input matches information from Student.txt file
		    
		    if(studentID.equals(entered_ID) && password.equals(entered_password)) {
		        System.out.println("Welcome!");
		                }
		    else{
		        System.out.println("Username and Password do not match.  Please try again.");
		            }
		    fileScanner.close();
		   
		 }
	    catch(FileNotFoundException e) {
	        e.printStackTrace();
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	     */
	}
	
	public void validateInput() {
		
	}

}

