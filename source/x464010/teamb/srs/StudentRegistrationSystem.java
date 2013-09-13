package x464010.teamb.srs;

/**
 * The StudentRegistrationSystem class runs the main thread and allows the user to navigate the application.
 *
 * @author Amit Dhamija
 * @version 1.6
 * @revision 1.1 Amit Dhamija 		Added login option
 * @revision 1.2 Michelle Masilon 	Added course catalog option
 * @revision 1.3 Michelle Masilon	Added new student account option
 * @revision 1.4 Amit Dhamija 		Extended to Console class; implemented methods
 * 									Moved student account option code to it's own class
 * @revision 1.5 Amit Dhamija 		Added check for logged in/out state
 * 									Added option list for logged in state
 * 									Updated the class to use modified Console class methods
 * @revision 1.6 Amit Dhamija		Added/updated the quit() method
 * 									Added some getters								
 */
public class StudentRegistrationSystem extends Console {

	private CourseCatalog courseCatalog;
	private Login login;
	private NewStudentAccount newStudentAccount;
	private Registrar registrar;
	private static StudentRegistrationSystem srs;

	/**
	 * Default constructor
	 */
	public StudentRegistrationSystem() {
		courseCatalog = new CourseCatalog();
		login = new Login();
		newStudentAccount = new NewStudentAccount();
		registrar = new Registrar();
	}

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println();
		System.out.println(Constants.STARS + Constants.SRS_TITLE + Constants.STARS);
		getSingleInstance().show(true);	
	}
	
	/**
	 * Get the login instance
	 * @return the login
	 */
	public static Login getLogin() {
		return getSingleInstance().login;
	}

	/**
	 * Get the registrar instance
	 * @return the registrar
	 */
	public static Registrar getRegistrar() {
		return getSingleInstance().registrar;
	}
	
	/* (non-Javadoc)
	 * @see x464010.teamb.srs.Console#showOptionList()
	 */
	@Override
	protected void showOptionList() {
		Student student = getLogin().getStudent();
		
		if (student != null && getLogin().isLoggedIn()) {
			StringBuffer welcomeMessage = new StringBuffer(Constants.WELCOME);
	    	welcomeMessage.append(student.getFirstName()).append(" ").append(student.getLastName()).append("!");
	    	
	    	System.out.println(welcomeMessage.toString());
	    	System.out.println();
			System.out.println("1. " + Constants.OPTION_COURSE_CATALOG);
			System.out.println("2. " + Constants.OPTION_MY_COURSE_SCHEDULE);
			System.out.println("3. " + Constants.OPTION_LOGOUT);
		}
		else {
			System.out.println("1. " + Constants.OPTION_COURSE_CATALOG);
			System.out.println("2. " + Constants.OPTION_STUDENT_ACCOUNT_LOGIN);
			System.out.println("3. " + Constants.OPTION_NEW_STUDENT_ACCOUNT);
		}
		System.out.println("4. " + Constants.OPTION_QUIT);
	}
	
	/* (non-Javadoc)
	 * @see x464010.teamb.srs.Console#selectOption(int)
	 */
	@Override
	protected void selectOption(int option) {
		if (getLogin().isLoggedIn()) {
			switch (option) {
				case Constants.COURSE_CATALOG:
					courseCatalog.show();
					break;
				case Constants.MY_COURSE_SCHEDULE:
					// show logged in student's course schedule
					break;
				case Constants.LOGOUT:
					getLogin().setLoggedIn(false);
					getLogin().setStudent(null);
					System.out.println();
					System.out.println(Constants.LOGOUT_SUCCESS);
					show(true);
					break;
				case Constants.QUIT:
					quit();
				default:
					System.out.println(Constants.INVALID_OPTION);
					show(true);
					break;
			}
		}
		else {
			switch (option) {
				case Constants.COURSE_CATALOG:
					courseCatalog.show();
					break;
				case Constants.STUDENT_ACCOUNT_LOGIN:
					getLogin().show(Constants.SRS);
					break;
				case Constants.NEW_STUDENT_ACCOUNT:
					newStudentAccount.show(true);
					break;
				case Constants.QUIT:
					quit();
				default:
					System.out.println(Constants.INVALID_OPTION);
					show(true);
					break;
			}
		}
	}
	
	/**
	 * Get the single instance of the application
	 */
	private static StudentRegistrationSystem getSingleInstance() {
        if (srs == null)
            srs = new StudentRegistrationSystem();
        return srs;
    }
	
	/**
	 * Quit the application
	 */
	private void quit() {
		super.closeInputScanner();
		System.exit(0);
	}
}