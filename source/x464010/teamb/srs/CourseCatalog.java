package x464010.teamb.srs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * WelcomeMenu class starts the SRS application
 *
 * @author Amit Dhamija
 * @version 1.1
 * @revision 1.1	Added showCourseList method; 
 * 					moved Michelle's code from StudentRegistrationSystem to this method
 * @revision 1.2	Updated the class to use modified Console class methods
 * @revision 1.3	Added showCatalog() method; added list of options to show
 * @revision 1.4	Added options to register/unregister course
 */
public class CourseCatalog extends Console {
	
	public void show() {
		System.out.println();
		System.out.println(Constants.STARS + Constants.COURSE_CATALOG_TITLE + Constants.STARS);
		showCourseList();
		super.show(true);
	}
	
	@Override
	protected void showOptionList() {
		if (StudentRegistrationSystem.getSingleInstance().getLogin().isLoggedIn()) {
	    	System.out.println("1. " + Constants.OPTION_REGISTER_COURSE);
			System.out.println("2. " + Constants.OPTION_UNREGISTER_COURSE);
			System.out.println("3. " + Constants.OPTION_BACK_SRS);
		}
		else {
			System.out.println("1. " + Constants.OPTION_REGISTER_COURSE + Constants.REQUIRES_LOGIN);
			System.out.println("2. " + Constants.OPTION_UNREGISTER_COURSE + Constants.REQUIRES_LOGIN);
			System.out.println("3. " + Constants.OPTION_BACK_SRS);
		}
	}
	
	@Override
	protected void selectOption(int option) {
		if (StudentRegistrationSystem.getSingleInstance().getLogin().isLoggedIn()) {
			switch (option) {
				case Constants.REGISTER_COURSE:
					StudentRegistrationSystem.getSingleInstance().getRegistrar().show(Constants.REGISTER_COURSE);
					break;
				case Constants.UNREGISTER_COURSE:
					StudentRegistrationSystem.getSingleInstance().getRegistrar().show(Constants.UNREGISTER_COURSE);
					System.out.println("Unregister course");
					break;
				case Constants.BACK_SRS:
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
				case Constants.UNREGISTER_COURSE:
					StudentRegistrationSystem.getSingleInstance().getLogin().show(false);
					break;
				case Constants.BACK_SRS:
					StudentRegistrationSystem.main(null);
					break;
				default:
					System.out.println(Constants.INVALID_OPTION);
					show(true);
					break;
			}
		}
	}
	
	public void showCourseList() {
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