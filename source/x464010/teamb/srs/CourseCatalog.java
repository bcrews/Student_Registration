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
 */
public class CourseCatalog extends Console {

	@Override
	public void getInput(Scanner inputScanner) {
		System.out.println(Constants.STARS + Constants.COURSE_CATALOG_TITLE + Constants.STARS);
		showCourseList();
		// TODO: show menu list
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
