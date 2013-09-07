package x464010.teamb.srs;

import java.io.*;
import java.util.*;

/**
 * WelcomeMenu class starts the SRS application
 *
 * @author Amit Dhamija
 * @version 1.4
 * @revision 1.1 Amit Dhamija 		Added login option
 * @revision 1.2 Michelle Masilon 	Added course catalog option
 * @revision 1.3 Michelle Masilon	Added new student account option
 * @revision 1.4 Amit Dhamija 		Extended to Console class; changed implementation
 */
public class StudentRegistrationSystem extends Console {

	private CourseCatalog courseCatalog;
	private Login login;
	private NewStudentAccount newStudentAccount;
	
	/**
	 * Default constructor.
	 */
	public StudentRegistrationSystem() {
		courseCatalog = new CourseCatalog();
		login = new Login();
		newStudentAccount = new NewStudentAccount();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StudentRegistrationSystem srs = new StudentRegistrationSystem();
		
		System.out.println(Constants.STARS + Constants.SRS_TITLE + Constants.STARS);
		srs.initialize();	
	}

	@Override
	protected void getInput(Scanner inputScanner) {
		System.out.println(Constants.OPTION_COURSE_CATALOG);
		System.out.println(Constants.OPTION_STUDENT_ACCOUNT_LOGIN);
		System.out.println(Constants.OPTION_NEW_STUDENT_ACCOUNT);
		System.out.print(Constants.SELECT_OPTION);
		
		int option = 0;
		try {
			option = Integer.parseInt(inputScanner.next());
		}
		catch (NumberFormatException e) {
			System.out.println(Constants.INVALID_FORMAT);
			getInput(inputScanner);
		}
		onOptionSelected(option);
	}

	protected void onOptionSelected(int option) {
		switch (option) {
			case Constants.COURSE_CATALOG:
				courseCatalog.initialize();
				break;
			case Constants.STUDENT_ACCOUNT_LOGIN:
				login.initialize();
				break;
			case Constants.NEW_STUDENT_ACCOUNT:
				newStudentAccount.initialize();
				break;
			default:
				System.out.println(Constants.INVALID_OPTION);
				initialize();
				break;
		}
	}
}