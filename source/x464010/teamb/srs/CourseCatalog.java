package x464010.teamb.srs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * The CourseCatalog class shows the list of courses and gives the options to register/unregister a course.
 * 
 * @author Amit Dhamija
 * @version 1.4
 * @revision 1.1	Amit Dhamija	Added showCourseList method; 
 * 									moved Michelle's code from StudentRegistrationSystem to this method
 * @revision 1.2	Amit Dhamija	Updated the class to use modified Console class methods
 * @revision 1.3	Amit Dhamija	Added showCatalog() method; added list of options to show
 * @revision 1.4	Amit Dhamija	Added options to register/unregister course
 */
public class CourseCatalog extends Console {
	
	/**
	 * Shows the course catalog console with the list of courses
	 */
	public void show() {
		System.out.println();
		System.out.println(Constants.STARS + Constants.OPTION_COURSE_CATALOG + Constants.STARS);
		showCourseList();
		super.show(true);
	}
	
	/* (non-Javadoc)
	 * @see x464010.teamb.srs.Console#showOptionList()
	 */
	@Override
	protected void showOptionList() {
		if (StudentRegistrationSystem.getLogin().isLoggedIn()) {
	    	System.out.println("1." + Constants.OPTION_REGISTER_COURSE);
			System.out.println("2." + Constants.OPTION_UNREGISTER_COURSE);
			System.out.println("3." + Constants.OPTION_BACK_SRS);
		}
		else {
			System.out.println("1." + Constants.OPTION_REGISTER_COURSE + Constants.REQUIRES_LOGIN);
			System.out.println("2." + Constants.OPTION_UNREGISTER_COURSE + Constants.REQUIRES_LOGIN);
			System.out.println("3." + Constants.OPTION_BACK_SRS);
		}
	}
	
	/* (non-Javadoc)
	 * @see x464010.teamb.srs.Console#selectOption(int)
	 */
	@Override
	protected void selectOption(int option) {
		if (StudentRegistrationSystem.getLogin().isLoggedIn()) {
			switch (option) {
				case Constants.REGISTER_COURSE:
					StudentRegistrationSystem.getRegistrar().show(Constants.REGISTER_COURSE);
					break;
				case Constants.UNREGISTER_COURSE:
					StudentRegistrationSystem.getRegistrar().show(Constants.UNREGISTER_COURSE);
					break;
				case Constants.SRS:
					StudentRegistrationSystem.main(null);
					break;
				default:
					System.out.println(Constants.INVALID_OPTION);
					show(true);
					break;
			}
		}
		else {
			switch (option) {
				case Constants.REGISTER_COURSE:
					StudentRegistrationSystem.getLogin().setConsoleId(Constants.REGISTER_COURSE);
					StudentRegistrationSystem.getLogin().show();
					break;
				case Constants.UNREGISTER_COURSE:
					StudentRegistrationSystem.getLogin().setConsoleId(Constants.UNREGISTER_COURSE);
					StudentRegistrationSystem.getLogin().show();
					break;
				case Constants.SRS:
					StudentRegistrationSystem.main(null);
					break;
				default:
					System.out.println(Constants.INVALID_OPTION);
					show(true);
					break;
			}
		}
	}
	
	/**
	 * Gets the list of courses from the file and print to screen
	 */
	private void showCourseList() {
		try {
			File courseListFile = new File(Constants.COURSE_LIST_FILE_PATH);
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
	}
}