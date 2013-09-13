package x464010.teamb.srs;

import java.io.*;
import java.util.Scanner;

/**
 * The Login class prompts the student to enter credentials, validate them and redirects to appropriate console.
 * 
 * @author Rebecca Chappel
 * @author Amit Dhamija
 * @version 1.4
 * @revision 1.2	Amit Dhamija	Implemented validateInput method
 * @revision 1.3	Amit Dhamija	Updated the class to use modified Console class methods
 * @revision 1.4	Amit Dhamija	Implement selectOption() method to redirect to selected console
 */
public class Login extends Console {
	
	private Student student = null;
	private boolean isLoggedIn = false;
	private int redirectToConsoleId = 3;
	
	/**
	 * Get the student
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * Set the student
	 * @param student the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

	/**
	 * Get the isLoggedIn
	 * @return the isLoggedIn
	 */
	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	/**
	 * Set the isLoggedIn
	 * @param isLoggedIn the isLoggedIn to set
	 */
	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	/**
	 * Set the console Id
	 * @param redirectToConsoleId the redirectToConsoleId to set
	 */
	public void setConsoleId(int redirectToConsoleId) {
		this.redirectToConsoleId = redirectToConsoleId;
	}
	
	/**
	 * Shows the login console and prompts to enter Student ID and password
	 */
	public void show() {
		try {
			Scanner inputScanner = Console.getInputScanner();
			
			System.out.println();
			System.out.println(Constants.STARS + Constants.OPTION_STUDENT_ACCOUNT_LOGIN + Constants.STARS);
			System.out.println(Constants.ENTER_LOGIN);
			System.out.print(Constants.STUDENT_ID);
			String enteredId = inputScanner.nextLine();
			System.out.print(Constants.PASSWORD);
			String enteredPassword = inputScanner.nextLine();
				
			validateInput(Integer.parseInt(enteredId), enteredPassword);
		} catch (Exception e) {
			System.out.println("Error! " + e.getMessage());
			//TODO: number format exception
		}
	}
	
	@Override
	protected void showOptionList() {
		
	}
	
	@Override
	protected void selectOption(int option) {
		switch (option) {
			case Constants.REGISTER_COURSE:
				StudentRegistrationSystem.getRegistrar().show(Constants.REGISTER_COURSE);
				break;
			case Constants.UNREGISTER_COURSE:
				StudentRegistrationSystem.getRegistrar().show(Constants.UNREGISTER_COURSE);
				break;
			case Constants.SRS:
			default:
				StudentRegistrationSystem.main(null);
				break;
		}
	}
	
	/**
	 * Checks to see if Student ID and password match the entry in the file
	 * @param id
	 * @param password
	 */
	protected void validateInput(int id, String password) {
		boolean isValid = false;
		File inputFile;
        Scanner fileScanner;
        String currentLine = "";
        String[] studentDetails = {};
        try {
        	inputFile = new File(Constants.STUDENT_FILE_PATH);
			fileScanner = new Scanner(inputFile);
		    
			while (fileScanner.hasNextLine()) {
      			currentLine = fileScanner.nextLine();
      			studentDetails = currentLine.split(",");

      			// check if input matches information from Student.txt file
    		    if(Integer.parseInt(studentDetails[0]) == id && studentDetails[7].equals(password)) {
    		    	isValid = isLoggedIn = true;
    		    	setStudent(new Student(	Integer.parseInt(studentDetails[0]),
    		    							studentDetails[1],
    		    							studentDetails[2],
    		    							studentDetails[3],
    		    							studentDetails[4],
    		    							studentDetails[5],
    		    							studentDetails[6],
    		    							studentDetails[7]));
    		    	break;
    		    }
			}
		    
		    fileScanner.close();
		    
		    if (isValid) {
		        selectOption(redirectToConsoleId);
		    }
		    else {
		    	System.out.print(Constants.INVALID_LOGIN);
		    	show();
		    }
		 }
	    catch(FileNotFoundException e) {
	        e.printStackTrace();
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}