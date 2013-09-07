
package x464010.teamb.srs;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Amit Dhamija
 * @version 1.0
 *
 */
public abstract class Console {

	protected static Scanner inputScanner;
	
	/**
	 * Default constructor.
	 */
	public Console() {
		
	}
	
	public void initialize() {
		System.out.println();
		
		try {
			// set up Scanner object using system.in
			inputScanner = new Scanner(System.in);
			
			// read input from screen
			getInput(inputScanner);
			inputScanner.close();
			
		} catch (InputMismatchException e) {
			System.out.println("Error! Incorrect input type: " + e.getMessage());
		}
	}
	
	abstract void getInput(Scanner inputScanner);

}
