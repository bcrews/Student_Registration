package x464010.teamb.srs;

import java.io.*;
import java.util.Scanner;

/**
 * Login to the System.  Checks Username and Password.
 * @author Rebecca Chappel
 * @author Amit Dhamija
 * @version 1.2
 * @revision 1.2	Implemented validateInput method
 */
public class Login extends Console {
	
	@Override
	protected void getInput(Scanner inputScanner) {
		System.out.println(Constants.ENTER_LOGIN);
		System.out.print(Constants.STUDENT_ID);
		String enteredId = inputScanner.next();
		System.out.print(Constants.PASSWORD);
		String enteredPassword = inputScanner.next();
			
		validateInput(enteredId, enteredPassword);
	}
	
	protected void validateInput(String id, String password) {
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
    		    if(studentDetails[0].equals(id) && studentDetails[7].equals(password)) {
    		    	isValid = true;
    		    	break;
    		    }
			}
		    
		    fileScanner.close();
		    
		    if (isValid) {
		    	StringBuffer welcomeMessage = new StringBuffer(Constants.WELCOME);
		    	welcomeMessage.append(studentDetails[1]).append(" ").append(studentDetails[2]).append("!");
		    	System.out.println();
		    	System.out.println(welcomeMessage.toString());
		        // TODO: show menu list for logged in student;
		    }
		    else {
		    	System.out.print(Constants.INVALID_LOGIN);
		    	initialize();
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