package x464010.teamb.srs;


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
	
	public static final String STARS 				= "********************";
	public static final String SELECT_OPTION 		= "Select an option, then press ENTER: ";
	
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
		
		String srsTitle = " Student Registration System ";
		
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
				//courseCatalog.showList();
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
