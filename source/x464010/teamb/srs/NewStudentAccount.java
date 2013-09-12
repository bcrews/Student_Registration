package x464010.teamb.srs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
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
 * @revision 1.5 Amit Dhamija		Updated the class to use modified Console class methods
 * 									Organized the code into various methods
 * 									Modified to class to use one input scanner
 */
public class NewStudentAccount extends Console {
	
	private Student newStudent = null;
	
	
	@Override
	public void show(boolean hasOptionList) {
		//Scanner inputScanner = new Scanner(System.in);
		//inputScanner.useDelimiter("\\z");

		/**
	 	 * Get input from user for remaining new Student data and
	 	 * write to student.txt as a new line (appended to existing data)
	 	 */
		
		System.out.println();
		String firstName = null, lastName = null, streetAddress = null, city = null, state = null, zip = null, password = null;
		
		try {
			
			System.out.print(Constants.PLEASE_ENTER_YOUR + Constants.FIRST_NAME);
			firstName = inputScanner.nextLine();

			System.out.print(Constants.PLEASE_ENTER_YOUR + Constants.LAST_NAME);
			lastName = inputScanner.nextLine();

			System.out.print(Constants.PLEASE_ENTER_YOUR + Constants.STREET_ADDRESS);
			streetAddress = inputScanner.nextLine();

			System.out.print(Constants.PLEASE_ENTER_YOUR + Constants.CITY);
			city = inputScanner.nextLine();

			System.out.print(Constants.PLEASE_ENTER_YOUR + Constants.STATE);
			state = inputScanner.nextLine();

			System.out.print(Constants.PLEASE_ENTER_YOUR + Constants.ZIP);
			zip = inputScanner.nextLine();

			System.out.print(Constants.CREATE_PASSWORD);
			password = inputScanner.nextLine();
			
			newStudent = new Student(generateStudentId(), firstName.trim(), lastName.trim(), streetAddress.trim(), city.trim(), state.trim(), zip.trim(), password.trim());
			
			saveStudentInfo();
			
		} catch (Exception e) {
			System.out.println("Error! " + e.getMessage());
		}
		
		super.show(hasOptionList);
	}
	
	@Override
	protected void showOptionList() {
		
	}
	
	@Override
	protected void selectOption(int option) {
		
	}
	
	/**
 	 * Auto-generate unique student ID
 	 * Read in existing student IDs from Student.txt
 	 * Save studentID data to studentList ArrayList
 	 * Sort studentList
 	 * Maximum studentID is the last value in sorted studentList
 	 * Create new student ID by adding 1 to previous max studentID
 	 */
	private int generateStudentId() {
		ArrayList<Integer> studentIdList = new ArrayList<Integer>();
		
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
		
		return newStudentID;
	}
	
	private void saveStudentInfo() {
		BufferedWriter buffWriter = null;
		try {
			// Open file with append flag set to true will cause string to be appended to file
			buffWriter = new BufferedWriter(new FileWriter(Constants.STUDENT_FILE_PATH,true));
			buffWriter.newLine();
			buffWriter.write(newStudent.toString());
			buffWriter.close();
			
			System.out.println();
			System.out.println("You account has been successfully created. Your new Student ID is " + newStudent.getStudentID() + ".");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}