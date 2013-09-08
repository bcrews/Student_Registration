package x464010.teamb.srs;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Amit Dhamija
 * @version 1.1
 * @revision	Amit Dhamija	Added additional methods
 *
 */
public abstract class Console {

	protected static Scanner inputScanner;
	
	/**
	 * Default constructor.
	 */
	public Console() {
		
	}
	
	public void show() {
		System.out.println();
		
		// print out to screen before prompting user for any input
		printBeforeInput();
		
		try {
			// set up Scanner object using system.in
			inputScanner = new Scanner(System.in);
			
			// prompt user for input
			getInput(inputScanner);
			
			// close scanner
			inputScanner.close();
			
		} catch (InputMismatchException e) {
			System.out.println("Error! Incorrect input type: " + e.getMessage());
		}
	}
	
	
	/**
	 * Used to print any output to screen
	 */
	abstract void printBeforeInput();
	
	/**
	 * Used to get input from screen
	 */
	abstract void getInput(Scanner inputScanner);
	
	/**
	 * Used to select option based on input
	 */
	abstract void selectOption(int option);
}