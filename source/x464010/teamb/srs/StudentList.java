package x464010.teamb.srs;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * StudentList class contains methods for loading, saving, displaying
 * and sorting the student list
 *
 * @author William Crews
 * @version 0.1			Initial draft.
 */

 public class StudentList {

	public static ArrayList<Student> studentList = new ArrayList<Student>();

	/**
	 * loadStudenList() Method will load students from the Student.txt file.
	 *
	 * @author William Crews
	 * @param  None				With no params it will load Student.txt in local directory.
	 * @throws IOException		Will throw IOException if file cannot be found or read.
	 * @returns void
	 */
	public static void loadStudentList() {

		String lineRead = null;     // String to hold each line read from the file.

		try {
			BufferedReader studentFile = null;
			studentFile = new BufferedReader(new FileReader("Student.txt"));
			while ((lineRead = studentFile.readLine()) != null) {
				System.out.println(lineRead);
				String [] textArray = lineRead.split(",");
				Student obj = new Student(Integer.parseInt(textArray[0]),textArray[1].trim(),
						   				textArray[2].trim(),textArray[3].trim(),textArray[4].trim(),
						   				textArray[5].trim(),textArray[6].trim(),textArray[7].trim());
				studentList.add(obj);
			}
			studentFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * loadStudenList(String fName) Method will load students from the file name passed.
	 *                              An overriding method to loadStudentList() with no parms.
	 * @author William Crews
	 * @param  fName			String type, allowing to specify the path and file to use.
	 * @throws IOException		Will throw IOException if file cannot be found or read.
	 * @returns void
	 */
	public static void loadStudentList(String fName) {

		String lineRead = null;     // String to hold each line read from the file.

		try {
			BufferedReader studentFile = null;
			studentFile = new BufferedReader(new FileReader(fName));
			while ((lineRead = studentFile.readLine()) != null) {
				System.out.println(lineRead);
				String [] textArray = lineRead.split(",");
				Student obj = new Student(Integer.parseInt(textArray[0]),textArray[1].trim(),
						   				textArray[2].trim(),textArray[3].trim(),textArray[4].trim(),
						   				textArray[5].trim(),textArray[6].trim(),textArray[7].trim());
				studentList.add(obj);
			}
			studentFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		loadStudentList();
	}
}


