package x46010.teamb.srs;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * WelcomeMenu class starts the SRS application
 *
 * @author Amit Dhamija
 * @version 1.0
 */
public class StudentRegistrationSystem {

	private static final int COURSE_CATALOG 		= 1;
	private static final int STUDENT_ACCOUNT_LOGIN 	= 2;
	private static final int NEW_STUDENT_ACCOUNT 	= 3;
	
	/**
	 * 
	 */
	public StudentRegistrationSystem() {
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
			System.out.println("Please enter a number from the list!");
		}
	}
	
	public static void showMainMenu() {
		String stars = "********************";
		String srsTitle = " Student Registration System ";
		String selectOption = "Select an option, then press ENTER: ";
		
		System.out.println(stars + srsTitle + stars);
		System.out.println();
		System.out.println("1. Course Catalog");
		System.out.println("2. Student Account Login");
		System.out.println("3. New Student Account");
		System.out.println();
		System.out.print(selectOption);
	}
	
	public static void onOptionSelect(int option) {
		switch (option) {
			case COURSE_CATALOG:	
				System.out.println("You selected: " + option);
				break;
			case STUDENT_ACCOUNT_LOGIN:	
				System.out.println("You selected: " + option);
				break;
			case NEW_STUDENT_ACCOUNT:	
				System.out.println("You selected: " + option);
				break;
			default:
				System.out.println("Please enter a number from the list!");
				break;
		}
	}

}
