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
	
	private Student student = null;
	/**
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * @param student the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

	/**
	 * @return the isLoggedIn
	 */
	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	/**
	 * @param isLoggedIn the isLoggedIn to set
	 */
	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	private boolean isLoggedIn = false;
	
	@Override
	protected void printBeforeInput() {
		System.out.println(Constants.ENTER_LOGIN);
	}
	
	@Override
	protected void getInput(Scanner inputScanner) {
		System.out.print(Constants.STUDENT_ID);
		String enteredId = inputScanner.next();
		System.out.print(Constants.PASSWORD);
		String enteredPassword = inputScanner.next();
			
		validateInput(enteredId, enteredPassword);
	}
	
	@Override
	protected void selectOption(int option) {
		
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
		        StudentRegistrationSystem.main(null);
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