package x464010.teamb.srs;

import java.io.*;
import java.util.Scanner;

/**
 * Login to the System.  Checks Username and Password.
 * @author Rebecca Chappel
 * @author Amit Dhamija
 * @version 1.2
 * @revision 1.2	Amit Dhamija	Implemented validateInput method
 * @revision 1.3	Amit Dhamija	Updated the class to use modified Console class methods
 */
public class Login extends Console {
	
	private Student student = null;
	private boolean isLoggedIn = false;
	
	
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
	
	public void show() {
		System.out.println();
		System.out.println(Constants.ENTER_LOGIN);
		System.out.print(Constants.STUDENT_ID);
		String enteredId = inputScanner.nextLine();
		System.out.print(Constants.PASSWORD);
		String enteredPassword = inputScanner.nextLine();
			
		validateInput(Integer.parseInt(enteredId), enteredPassword);
	}
	
	@Override
	protected void showOptionList() {
		
	}
	
	@Override
	protected void selectOption(int option) {
		
	}
	
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
		        StudentRegistrationSystem.main(null);
		    }
		    else {
		    	System.out.print(Constants.INVALID_LOGIN);
		    	show(false);
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