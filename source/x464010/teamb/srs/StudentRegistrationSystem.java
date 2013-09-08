package x464010.teamb.srs;

import java.util.*;

/**
 * WelcomeMenu class starts the SRS application
 *
 * @author Amit Dhamija
 * @version 1.5
 * @revision 1.1 Amit Dhamija 		Added login option
 * @revision 1.2 Michelle Masilon 	Added course catalog option
 * @revision 1.3 Michelle Masilon	Added new student account option
 * @revision 1.4 Amit Dhamija 		Extended to Console class; implemented methods
 * @revision 1.5 Amit Dhamija 		Added check for logged in/out state
 * 									Added option list for logged in state									
 */
public class StudentRegistrationSystem extends Console {

	private CourseCatalog courseCatalog;
	private Login login;
	private NewStudentAccount newStudentAccount;
	private static StudentRegistrationSystem srs;

    
	/**
	 * Default constructor.
	 */
	public StudentRegistrationSystem() {
		courseCatalog = new CourseCatalog();
		login = new Login();
		newStudentAccount = new NewStudentAccount();
		System.out.println(Constants.STARS + Constants.SRS_TITLE + Constants.STARS);
	}

	public static StudentRegistrationSystem getSingleInstance() 
    {
        if (srs == null)
            srs = new StudentRegistrationSystem();
        return srs;
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		getSingleInstance().show();	
	}

	@Override
	protected void printBeforeInput() {
		Student student = login.getStudent();
		
		if (student != null && login.isLoggedIn()) {
			StringBuffer welcomeMessage = new StringBuffer(Constants.WELCOME);
	    	welcomeMessage.append(student.getFirstName()).append(" ").append(student.getLastName()).append("!");
	    	
	    	System.out.println(welcomeMessage.toString());
	    	System.out.println();
			System.out.println(Constants.OPTION_COURSE_CATALOG);
			System.out.println(Constants.OPTION_MY_COURSE_SCHEDULE);
			System.out.println(Constants.OPTION_LOGOUT);
		}
		else {
			System.out.println(Constants.OPTION_COURSE_CATALOG);
			System.out.println(Constants.OPTION_STUDENT_ACCOUNT_LOGIN);
			System.out.println(Constants.OPTION_NEW_STUDENT_ACCOUNT);
		}
		System.out.print(Constants.SELECT_OPTION);
	}
	
	@Override
	protected void getInput(Scanner inputScanner) {
		int option = 0;
		try {
			option = Integer.parseInt(inputScanner.next());
		}
		catch (NumberFormatException e) {
			System.out.println(Constants.INVALID_FORMAT);
			getInput(inputScanner);
		}
		
		selectOption(option);
	}

	@Override
	protected void selectOption(int option) {
		if (getSingleInstance().login.isLoggedIn()) {
			switch (option) {
				case Constants.COURSE_CATALOG:
					courseCatalog.show();
					break;
				case Constants.STUDENT_ACCOUNT_LOGIN:
					// show logged in student's course schedule
					break;
				case Constants.LOGOUT:
					getSingleInstance().login.setLoggedIn(false);
					getSingleInstance().login.setStudent(null);
					System.out.println();
					System.out.println(Constants.LOGOUT_SUCCESS);
					show();
					break;
				default:
					System.out.println(Constants.INVALID_OPTION);
					show();
					break;
			}
		}
		else {
			switch (option) {
				case Constants.COURSE_CATALOG:
					courseCatalog.show();
					break;
				case Constants.STUDENT_ACCOUNT_LOGIN:
					login.show();
					break;
				case Constants.NEW_STUDENT_ACCOUNT:
					newStudentAccount.show();
					break;
				default:
					System.out.println(Constants.INVALID_OPTION);
					show();
					break;
			}
		}
		
	}
}