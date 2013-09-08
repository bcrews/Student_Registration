package x464010.teamb.srs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.*;

/**
 * @author Amit Dhamija
 * @author Michelle Masilon
 * @version 1.4
 * @revision 1.1 Michelle Masilon	Uncommented line needed to properly delimit input
 * @revision 1.2 Amit Dhamija		Moved String values to Constants class
 * 									Corrected Students.txt file path
 * @revision 1.3 Michelle Masilon	Added block to auto-generate unique student ID.  Also added comments for clarity
 * @revision 1.4 Amit Dhamija		Changed hard-coded student.txt to use defined file path
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
		
	}
	
	@Override
	protected void getInput(Scanner inputScanner) {
		
		ArrayList<Integer> studentIdList=new ArrayList<Integer>();
		ArrayList<Student> studentList = new ArrayList<Student>();
		inputScanner.useDelimiter("\\z");

		/**
	 	 * Auto-generate unique student ID
	 	 * Read in exising student IDs from student.txt
	 	 * Save studentID data to studentList ArrayList
	 	 * Sort studentList
	 	 * Maximum studentID is the last value in sorted studentList
	 	 * Create new student ID by adding 1 to previous max studentID
	 	 */
		try {
			File existingStudentFile = new File(Constants.STUDENT_FILE_PATH);
			FileReader existingStudentReader = new FileReader(existingStudentFile);
			BufferedReader existingStudentBuffReader = new BufferedReader(existingStudentReader);

			String line = null;

			while ((line = existingStudentBuffReader.readLine()) != null) {
				String studentAttributes[] = line.split(",");
				studentIdList.add(Integer.parseInt(studentAttributes[0]));
			}

			existingStudentBuffReader.close();
		} catch (Exception ex) {
		ex.printStackTrace();
		}

		Collections.sort(studentIdList);
		int maxStudentID = studentIdList.get(studentIdList.size() - 1);
		int newStudentID = maxStudentID + 1;

		/**
	 	 * Now get input from user for remaining new Student data and
	 	 * write to student.txt as a new line (appended to existing data)
	 	 */

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
			// Open file with append flag set to true will cause string to be appended to file
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
