package x46010.teamb.srs;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Collections;
import java.io.File;
import java.io.*;
import java.util.*;

/**
 * WelcomeMenu class starts the SRS application
 *
 * @author Amit Dhamija
 * @version 1.2
 * @revision 1.1 Amit Dhamija 		Whitespace adjustments
 * @revision 1.2 Michelle Masilon 	Added course catalog option
 */
public class StudentRegistrationSystem {

	private static final int COURSE_CATALOG 		= 1;
	private static final int STUDENT_ACCOUNT_LOGIN 	= 2;
	private static final int NEW_STUDENT_ACCOUNT 	= 3;

	public static final String STARS = "********************";
	public static final String SELECT_OPTION = "Select an option, then press ENTER: ";

	private static LoginView login;
	/**
	 *
	 */
	public StudentRegistrationSystem() {

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		login = new LoginView();
		Scanner userInputScanner;

		try {
			// set up Scanner object using system.in
			userInputScanner = new Scanner(System.in);

			// display menu options
			showMainMenu();

			// retrieve response
			int option = Integer.parseInt(userInputScanner.next());
			onOptionSelect(option);

			userInputScanner.close();
		} catch (InputMismatchException e) {
			System.out.println("Error! Incorrect input type: " + e.getMessage());
		}
	}

	public static void showMainMenu() {

		String srsTitle = "Student Registration System ";

		System.out.println(STARS + srsTitle + STARS);
		System.out.println();
		System.out.println("1. Course Catalog");
		System.out.println("2. Student Account Login");
		System.out.println("3. New Student Account");
		System.out.println();
		System.out.print(SELECT_OPTION);
	}

	public static void onOptionSelect(int option) {
		switch (option) {
			case COURSE_CATALOG:
				try {
					File courseListFile = new File("CourseList.txt");
					Scanner fileScanner = new Scanner(courseListFile);
					ArrayList<Course> courseList = new ArrayList<Course>();
					Course tempCourse;

					while (fileScanner.hasNextLine()) {
						String[] courseAttributes = fileScanner.nextLine().split(",");
						tempCourse = new Course(courseAttributes[0], courseAttributes[1], courseAttributes[2], courseAttributes[3],courseAttributes[4],new Integer(courseAttributes[5]).intValue(),new Integer(courseAttributes[6]).intValue());
						courseList.add(tempCourse);
					}
						Collections.sort(courseList);
					for (Course course : courseList) {
						System.out.println(course.toStringCourse());
					}
						fileScanner.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}

				break;
			case STUDENT_ACCOUNT_LOGIN:
				login.getInput();
				break;
			case NEW_STUDENT_ACCOUNT:
				//studentAccount.getInput();
				break;
			default:
				System.out.println("Please enter a number from the list!");
				break;
		}
	}

}
