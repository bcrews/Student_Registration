package x464010.teamb.srs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.*;
import java.util.*;

/**
 * Registrar() class is the controller for scheduling
 * students for courses and saving/retrieving registration
 * records for the system.
 * 
 * @author William Crews
 * @version	1.0				Initial Version
 * 
 */
public class Registrar 
{
	private ArrayList<Registration> studentRegistrations;
	private ArrayList<Course> courses;


	private Registrar()
	{
		studentRegistrations = new ArrayList<Registration>();
		courses = new ArrayList<Course>();
	}

	/**
	 * loadRegistrationFile() method load the student registration files into the 
	 * system by reading the Registration.txt file and adding records from each line.
	 * 
	 * @author William Crews	
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	public void loadRegistrationFile()
			throws FileNotFoundException, Exception
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
	 * @throws	Exception
	 */
	public void loadCourseFile() 
			throws FileNotFoundException, Exception  
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
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public boolean registerForCourse(int studentID, String courseID)
			throws IllegalArgumentException, IOException
	{
		int regNum = getNewRegNum();
		Registration newStudentReg = new Registration(regNum, studentID, courseID);

		// We need to check for a couple of things:
		// 1) Is course number entered in the system? 
		// 2) Is student already registered for course?
		// 3) Is the class full, has it reached it max limit?

		if(!isValidCourseID(courseID)) {
			throw new IllegalArgumentException("Unable to find course listing with that ID number!");
		}
		else if(!isAlreadyRegistered(newStudentReg)) { 
			throw new IllegalArgumentException("Cannot register twice for same course.");
		}
		else if(!isCourseFull(courseID)) {
			throw new IllegalArgumentException("Sorry, the class has reached its maximum enrollment limit.");
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


}	


