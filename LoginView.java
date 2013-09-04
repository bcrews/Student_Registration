package x46010.teamb.srs;

import java.io.*;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Login to the System.  Checks Username and Password.
 * @author rebeccachappel
 * public Student(String[] fileRecord) {

 */
public class LoginView{
     public static void main(String[] args) throws FileNotFoundException{
         System.out.println("Please enter Student ID and Password");
 
         int entered_ID;
         String entered_password;
         File studentFile;

        Scanner fileScanner;
        Scanner input = new Scanner (System.in);
        
        
 try {
     // Read in Student.txt information
    studentFile = new File ("Student.txt");
    fileScanner = new Scanner(studentFile);
    
     // Prompt for input of Student ID and Password
    System.out.println("Student ID:");
    System.out.println("Password:");
    
    // Assign Input 
    entered_ID = input.nextInt();
    entered_password = input.nextLine();
    
    
    // Determine if input matches information from Student.txt file
    if(studentID.equals(entered_ID) && password.equals(entered_password)) {
        System.out.println("Welcome!");
                }
    else{
        System.out.println("Username and Password do not match.  Please try again.");
            }
    fileScanner.close();
    }
    catch(FileNotFoundException e) {
        e.printStackTrace();
    }
    catch (Exception e) {
        e.printStackTrace();
    }
    

    
   
}
}

