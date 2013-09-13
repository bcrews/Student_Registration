package x464010.teamb.srs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;



/**
 * Registrar() class is the controller for scheduling
 * students for courses and saving/retrieving registration
 * records for the system.
 * 
 * @author William Crews
 * @version	 1.1		
 * @revision 1.0		Initial version		
 * @revision 1.1		Added the following methods:
 * 						saveRegistration, saveCoursesAll, myCourseSchedule
 */
public class Registrar extends Console
{
	protected ArrayList<Registration> studentRegistrations;
	protected ArrayList<Course> courses;


	public Registrar()
	{
		studentRegistrations = new ArrayList<Registration>();
		courses = new ArrayList<Course>();
	}

	public void show(int option) {
		System.out.println();
		System.out.println(Constants.ENTER_COURSE);
		System.out.print(Constants.COURSE_ID);
		String courseId = inputScanner.nextLine();
		int studentId = StudentRegistrationSystem.getSingleInstance().getLogin().getStudent().getStudentID();
		
		if (option == 1) {
			System.out.println("Test print: Register Course block");
			registerForCourse(studentId,courseId);
			
		}
		else if (option == 2) {
			System.out.println("Test print: Unregister Course block");
			unregisterFromCourse(studentId,courseId);
		}
	}
	
	@Override
	protected void showOptionList() {
		
	}
	
	@Override
	protected void selectOption(int option) {
		
	}
	
	/**
	 * loadRegistrationFile() method load the student registration files into the 
	 * system by reading the Registration.txt file and adding records from each line.
	 * 
	 * @author William Crews	
	 * @throws FileNotFoundException
	 */
	public void loadRegistrationFile()
	{
		// Make sure course file is loaded.
		loadCourseFile();

		try {
			File studentRegListFile = new File(Constants.REGISTRATION_FILE_PATH);
			Scanner fileScanner = new Scanner(studentRegListFile);
			Registration tempRegistration;

			while (fileScanner.hasNextLine()) {
				String[] studentRegAttributes = fileScanner.nextLine().split(",");
				tempRegistration = new Registration(studentRegAttributes);
				studentRegistrations.add(tempRegistration);
			}

			Collections.sort(studentRegistrations, new Registration() );
			fileScanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * loadCourseFile() method loads the Course File and all
	 * the course info into the system so that it can be used
	 * with the registration system for reading and writing
	 * registration info.
	 * 
	 * @author	William Crews
	 * @throws	FileNotFoundException
	 */
	public void loadCourseFile() 
	{
		try {
			File courseListFile = new File(Constants.COURSE_LIST_FILE_PATH);
			Scanner fileScanner = new Scanner(courseListFile);
			ArrayList<Course> courseList = new ArrayList<Course>();
			Course tempCourse;

			while (fileScanner.hasNextLine()) {
				String[] courseAttributes = fileScanner.nextLine().split(",");
				tempCourse = new Course(courseAttributes[0], 
						courseAttributes[1], 
						courseAttributes[2], 
						courseAttributes[3],
						courseAttributes[4],
						new Integer(courseAttributes[5]).intValue(),
						new Integer(courseAttributes[6]).intValue());
				courseList.add(tempCourse);
			}

			Collections.sort(courseList);
			fileScanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * registerForCourse() method is used to register a student for a course
	 * after passing a few checks.  The checks implemented are:
	 *        1) Is course number entered in the system?
	 *        2) Is student already registered for course?
	 *        3) Is the class full, has it reached its max limit?
	 *        
	 *  After passing these checks the student is registered for the course
	 *  and the information is saved in the Registration.txt file.
	 *  
	 * @author William Crews
	 * @param studentID
	 * @param courseID
	 * @return
	 */
	public boolean registerForCourse(int studentID, String courseID)
	{
		int regNum = getNewRegNum();
		Registration newStudentReg = new Registration(regNum, studentID, courseID);

		// We need to check for a couple of things:
		// 1) Is course number entered in the system? 
		// 2) Is student already registered for course?
		// 3) Is the class full, has it reached it max limit?

		if(!isValidCourseID(courseID)) {
			System.out.println("Unable to find course listing with that ID number!");
			return false;
		}
		else if(!isAlreadyRegistered(newStudentReg)) { 
			System.out.println("Cannot register twice for same course.");
			return false;
		}
		else if(!isCourseFull(courseID)) {
			System.out.println("Sorry, the class has reached its maximum enrollment limit.");
			return false;
		}
		// Add Registration Record
		studentRegistrations.add(newStudentReg);

		// Don't forget to increase the enrollment counter on the course
		incrementCourseEnrollment(courseID);

		// Save Registration Record to Registration File
		saveRegistration(newStudentReg);

		// Save Courses with updated course info
		saveCoursesAll(courses);

		return true;

	}	

	/**
	 * getNewRegNum() method searches through the student registrations 
	 * looking for the highest assigned RegNum then increments it and
	 * returns this value to be used for new RegNum values.
	 * 
	 * @author William Crews
	 * @return newRegNum	next available RegNum value incremented by 1.
	 */
	private int getNewRegNum() 
	{
		int newRegNum = 0;
		if (!studentRegistrations.isEmpty())
			for (Registration r : studentRegistrations)
				if (newRegNum < r.getRegNum())
					newRegNum = r.getRegNum();
		return ++newRegNum;
	}     

	/**
	 * idValidCourseID() method checks if the passed courseID is 
	 * found in the courses list and returns true if found or false
	 * if not found.
	 * 
	 * @author William Crews
	 * @param id
	 * @return	boolean	true/false
	 */
	private boolean isValidCourseID(String id) 
	{
		for (Course course : courses)
			if (course.getCourseID() == id)
				return true;
		return false;
	}

	/**
	 * isAlreadyRegistered(Registration checkReg) method is used to
	 * check if the student has already registered for the course
	 * before.
	 * 
	 * @author William Crews
	 * @param checkReg
	 * @return boolean	true/false
	 */
	private boolean isAlreadyRegistered(Registration checkReg)
	{
		for (Registration r : studentRegistrations)
			if (r.equals(checkReg))
				return true;
		return false;
	}

	/**
	 * isCourseFull(String courseID) method is used to 
	 * check if the course selected for registration is
	 * full.
	 * 
	 * @author William Crews
	 * @param courseID
	 * @return boolean	true/false
	 */
	private boolean isCourseFull(String courseID)
	{
		for (Course c: courses) {
			if(c.equals(courseID)) {
				if(c.isCourseFilled(c.getCourseLimit(), c.getStudentsEnrolled())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * incrementCourseEnrollment(String courseID) method is used to
	 * increment the studentEnrolled counter in the courses attributes
	 * to keep track of how many students have enrolled in the course.
	 * 
	 * @author William Crews
	 * @param courseID
	 */
	private void incrementCourseEnrollment(String courseID)
	{
		for(Course c: courses) {
			if(c.equals(courseID)) {
				int enrolled = c.getStudentsEnrolled();
				c.setStudentsEnrolled(++enrolled);
			}
		}
	}

	/**
	 * decrementCourseEnrollment(String courseID) method is used to
	 * decrement the studentEnrolled counter in the courses attributes
	 * to keep track of how many students have unregistered from the course.
	 * 
	 * @author William Crews
	 * @param courseID			String containing the courseID.
	 */
	private void decrementCourseEnrollment(String courseID)
	{
		for(Course c: courses) {
			if(c.equals(courseID)) {
				int enrolled = c.getStudentsEnrolled();
				c.setStudentsEnrolled(--enrolled);
			}
		}
	}

	/**
	 * saveRegistration(Registration record) method saves a students
	 * registration to the Registration.txt file.
	 * 
	 * @author William Crews	
	 * @param record		 Registration record to be written to file.
	 */
	public void saveRegistration(Registration record ) 
	{
		BufferedWriter buffWriter = null;
		try {
			// Open file with append flag set to true will cause string to append to file.
			buffWriter = new BufferedWriter(new FileWriter(Constants.REGISTRATION_FILE_PATH,true));
			buffWriter.write( record.getRegNum()    + "," +
					record.getStudentID() + "," +
					record.getCourseID()  + "," +
					record.getRegDate() );
			buffWriter.newLine();
			buffWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	/**
	 * saveCoursesAll(ArrayList<Course> courseList) method saves the 
	 * entire course list object array to the file system overwriting
	 * any contents that was there.  This is used after the courses
	 * student enrollment count has been update and needs to be saved
	 * back to the CourseList.txt file.
	 * 
	 * @author William Crews
	 * @param courseList		ArrayList of Course object records.
	 */
	public void saveCoursesAll(ArrayList<Course> courseList)
	{
		BufferedWriter buffWriter = null;
		try {
			// Open file with boolean flag set to false will cause file to be overwritten
			// with new data.
			buffWriter = new BufferedWriter(new FileWriter(Constants.COURSE_LIST_FILE_PATH,false));
			for (Course c: courseList) {
				buffWriter.write(  c.getCourseID() + "," +
						c.getStartDate() + "," +
						c.getEndDate()   + "," +
						c.getCourseName() + "," +
						c.getCourseDescription() + "," +
						c.getCourseLimit() + "," +
						c.getStudentsEnrolled() );
				buffWriter.newLine();
			}
			buffWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	/**
	 * saveRegistrationsAll(ArrayList<Registration> studentRegList) method is used
	 * to save all student registrations in an ArrayList of type Registration to
	 * the Registration.txt file with records formated on each line using a 
	 * comma delimiter format.
	 * 
	 * @author William Crews
	 * @param studentRegList	An ArrayList of type Registration.
	 */
	public void saveRegistrationsAll(ArrayList<Registration> studentRegList)
	{
		BufferedWriter buffWriter = null;
		try {
			// Open file with boolean flag set to false will cause file to be overwritten
			// with new data.
			buffWriter = new BufferedWriter(new FileWriter(Constants.REGISTRATION_FILE_PATH,false));
			for (Registration r: studentRegList) {
				buffWriter.write(   r.getRegNum() + "," +
						r.getStudentID() + "," + 
						r.getCourseID() +  "," +
						r.getRegDate() );
				buffWriter.newLine();
			}
			buffWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * myCourseSchedule(int studentID) method is used to display the courses
	 * the student is currently registered for.  It will return a list with
	 * details about the course(s).
	 * 
	 * @author William Crews
	 * @param studentID			Students ID
	 */
	public void myCourseSchedule(int studentID)
	{
		// Check that Registration File has been loaded
		if(studentRegistrations.isEmpty()){
			try {
				loadRegistrationFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// Check that Course File has been loaded
		if(courses.isEmpty()){
			try {
				loadCourseFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// Loop through registrations looking for students id
		// and listing out the course info they are registered for.
		for (Registration registration : studentRegistrations)
			if (registration.getStudentID() == studentID)
				for (Course course : courses)
					if (registration.getCourseID() == course.getCourseID())
						course.toStringCourse();
	}

	/**
	 * unregisterFromCourse(int studentID, String courseID) method is used to
	 * unregister a student from a course.
	 * 
	 * @author William Crews
	 * @param studentID			Student ID
	 * @param courseID			Course ID
	 * @return	boolean			true/false
	 * @throws Exception
	 * @throws IllegalArgumentException
	 */
	public boolean unregisterFromCourse(int studentID, String courseID)
	{
		// Use 0 as the regNum, it's been reserved for special use
		// since all assigned registrations start at 1 and go up from there.
		Registration regToDelete = new Registration(0, studentID, courseID);

		// Check that Registration File has been loaded
		if(studentRegistrations.isEmpty()){
			try {
				loadRegistrationFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// Check that Course File has been loaded
		if(courses.isEmpty()){
			try {
				loadCourseFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if(!studentRegistrations.isEmpty()) {
			for(Registration r: studentRegistrations) {
				if((r.getStudentID() == studentID) && (r.getCourseID().trim() == courseID.trim())) {
					// We've found the record matching studentID & courseID
					// now we need to lookup the regNum and assign it to the regToDelete object.
					regToDelete.setRegNum(r.getRegNum());

					// Remove selected registration object from studentRegistration list
					studentRegistrations.remove(regToDelete);

					// Decrement the StudentEnrollment Counter for the Course
					decrementCourseEnrollment(r.getCourseID());

					// Resort the student registrations based on regNum
					Collections.sort(studentRegistrations, new Registration());

					// Save registrations to file
					saveRegistrationsAll(studentRegistrations);

					// Save courses to file
					saveCoursesAll(courses);

					// Found and deleted record, return true
					return true;
				}
			}
			// If we're here we haven't found a matching record.
			System.out.println("No matching registration records found.");
			return false;
		}
		return false;
	}
}	