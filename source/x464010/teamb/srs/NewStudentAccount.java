package x464010.teamb.srs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Amit Dhamija
 * @author Michelle Masilon
 * @version 1.0
 * @revision 1.1 Michelle Masilon	Uncommented line needed to properly delimit input
 * @revision 1.2 Amit Dhamija		Moved String values to Constants class
 * 									Corrected Students.txt file path
 */
public class NewStudentAccount extends Console {

	/**
	 * 
	 */
	public NewStudentAccount() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void printBeforeInput() {
		System.out.println(Constants.ENTER_LOGIN);
	}
	
	@Override
	protected void getInput(Scanner inputScanner) {
		
		ArrayList<Student> studentList = new ArrayList<Student>();
		inputScanner.useDelimiter("\\z");
		// Need to add logic where existing student IDs are read in and a new unique student ID is created
		// For now, force student ID to be temp ID 223456
		int newStudentID = 223456;

		System.out.print(Constants.PLEASE_ENTER_YOUR + Constants.FIRST_NAME);
		String newFirstName = inputScanner.next();

		System.out.print(Constants.PLEASE_ENTER_YOUR + Constants.LAST_NAME);
		String newLastName = inputScanner.next();

		System.out.print(Constants.PLEASE_ENTER_YOUR + Constants.STREET_ADDRESS);
		String newStreetAddress = inputScanner.next();

		System.out.print(Constants.PLEASE_ENTER_YOUR + Constants.CITY);
		String newCity = inputScanner.next();

		System.out.print(Constants.PLEASE_ENTER_YOUR + Constants.STATE);
		String newState = inputScanner.next();

		System.out.print(Constants.PLEASE_ENTER_YOUR + Constants.ZIP);
		String newZip = inputScanner.next();

		System.out.print(Constants.CREATE_PASSWORD);
		String newPassword = inputScanner.next();

		BufferedWriter buffWriter = null;
		try {
			buffWriter = new BufferedWriter(new FileWriter(Constants.STUDENT_FILE_PATH,true));
			buffWriter.newLine();
			buffWriter.write(	newStudentID + "," + 
								newFirstName.trim() + "," + 
								newLastName.trim() + "," +
								newStreetAddress.trim() + "," + 
								newCity.trim() + "," + 
								newState.trim() + "," +
								newZip.trim() + "," + 
								newPassword.trim());
			
			buffWriter.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	protected void selectOption(int option) {
		
	}
	
}