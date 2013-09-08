package x464010.teamb.srs;

/**
 * @author Amit Dhamija
 * @author William Crews
 * @version 1.2
 * @revision 1.1	William Crews	Updated STUDENT_FILE_PATH to ".\\data\\Student.txt"
 *                  				Updated COURSE_LIST_FILE_PATH to ".\\data\\CourseList.txt"
 * @revision 1.2	Amit Dhamija	Update file path to use single forward slash to work on both OS
 * 									Added more constants
 */
public final class Constants {

	public static final int COURSE_CATALOG 			= 1;
	public static final int STUDENT_ACCOUNT_LOGIN 	= 2;
	public static final int NEW_STUDENT_ACCOUNT 	= 3;
	public static final int MY_COURSE_SCHEDULE 		= 2;
	public static final int LOGOUT 					= 3;

	public static final String STARS = "********************";
	public static final String SRS_TITLE = " Student Registration System ";
	public static final String COURSE_CATALOG_TITLE = " Course Catalog ";
	
	public static final String SELECT_OPTION = "Please select an option, then press ENTER: ";
	public static final String OPTION_COURSE_CATALOG = "1. Course Catalog";
	public static final String OPTION_STUDENT_ACCOUNT_LOGIN = "2. Student Account Login";
	public static final String OPTION_NEW_STUDENT_ACCOUNT = "3. New Student Account";
	public static final String OPTION_MY_COURSE_SCHEDULE = "2. My Course Schedule";
	public static final String OPTION_LOGOUT = "3. Logout";
	
	public static final String PLEASE_ENTER_YOUR = "Please enter your ";
	public static final String ENTER_LOGIN = PLEASE_ENTER_YOUR + "Student ID and Password";
	public static final String STUDENT_ID = "Student ID: ";
	public static final String PASSWORD = "Password: ";
	public static final String WELCOME = "Welcome ";
	public static final String LOGOUT_SUCCESS = "You have successfully logged out!";
	
	public static final String FIRST_NAME = "First Name: ";
	public static final String LAST_NAME = "Last Name: ";
	public static final String STREET_ADDRESS = "Street Address: ";
	public static final String CITY = "City: ";
	public static final String STATE = "State (2-letter initials): ";
	public static final String ZIP = "Zip Code: ";
	public static final String CREATE_PASSWORD = "Please create your Password: ";
	
	public static final String INVALID_OPTION = "\nInvalid Option. Please select a number from the list.";
	public static final String INVALID_FORMAT = "\nInvalid Format. Please enter a number.";
	public static final String INVALID_LOGIN = "\nInvalid Student ID or Password.";
	
	public static final String STUDENT_FILE_PATH = "data/Student.txt";
	public static final String COURSE_LIST_FILE_PATH = "data/CourseList.txt";
}