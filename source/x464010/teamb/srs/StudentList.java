package x464010.teamb.srs;

import java.util.ArrayList;
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
	 * loadStudenList() Method will load students from the Students.txt file.
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
			studentFile = new BufferedReader(new FileReader("../data/Student.txt"));
			while ((lineRead = studentFile.readLine()) != null) {
				System.out.println(lineRead);
				String [] textArray = lineRead.split(",");
				Student obj = new Student(Integer.parseInt(textArray[0]),textArray[1],
						   				textArray[2],textArray[3],textArray[4],
						   				textArray[5],textArray[6],textArray[7]);
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
				Student obj = new Student(Integer.parseInt(textArray[0]),textArray[1],
						   				textArray[2],textArray[3],textArray[4],
						   				textArray[5],textArray[6],textArray[7]);
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


