package x464010.teamb.srs;

/**
 * @author Amit Dhamija
 * @author William Crews
 * @version 1.1
 * @revision 1.1	Updated STUDENT_FILE_PATH to ".\\data\\Student.txt"
 *                  Updated COURSE_LIST_FILE_PATH to ".\\data\\CourseList.txt"
 */
public final class Constants {

	public static final int COURSE_CATALOG 			= 1;
	public static final int STUDENT_ACCOUNT_LOGIN 	= 2;
	public static final int NEW_STUDENT_ACCOUNT 	= 3;

	public static final String STARS = "********************";
	public static final String SRS_TITLE = " Student Registration System ";
	public static final String COURSE_CATALOG_TITLE = " Course Catalog ";
	
	public static final String SELECT_OPTION = "Please select an option, then press ENTER: ";
	public static final String OPTION_COURSE_CATALOG = "1. Course Catalog";
	public static final String OPTION_STUDENT_ACCOUNT_LOGIN = "2. Student Account Login";
	public static final String OPTION_NEW_STUDENT_ACCOUNT = "3. New Student Account";
	
	public static final String ENTER_LOGIN = "Please enter your Student ID and Password";
	public static final String STUDENT_ID = "Student ID: ";
	public static final String PASSWORD = "Password: ";
	public static final String WELCOME = "Welcome ";
	
	
	
	public static final String INVALID_OPTION = "\nInvalid Option. Please select a number from the list.";
	public static final String INVALID_FORMAT = "\nInvalid Format. Please enter a number.";
	public static final String INVALID_LOGIN = "\nInvalid Student ID or Password.";
	
	public static final String STUDENT_FILE_PATH = ".\\data\\Student.txt";
	public static final String COURSE_LIST_FILE_PATH = ".\\data\\CourseList.txt";

}
