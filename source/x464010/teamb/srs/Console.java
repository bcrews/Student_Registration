package x464010.teamb.srs;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The abstract Console class allows the subclass to show list of options and get input from screen.
 * It provides abstract methods to implement the list of options and actions to perform when an option is selected.
 * 
 * @author Amit Dhamija
 * @version 1.3
 * @revision 1.1	Amit Dhamija	Added abstract methods
 * @revision 1.2 	Amit Dhamija	Modified the class to better support the list of options
 * @revision 1.3 	Amit Dhamija	Added methods to get input and close input scanner
 */
public abstract class Console {

	private static Scanner inputScanner;
	
	/**
	 * Default constructor
	 */
	public Console() {
		// initialize the scanner
		inputScanner = new Scanner(System.in);
	}
	
	/**
	 * Get the scanner
	 * @return the inputScanner
	 */
	public static Scanner getInputScanner() {
		return inputScanner;
	}
	
	/**
	 * Close the scanner
	 */
	public static void closeInputScanner() {
		inputScanner.close();
	}
	
	/**
	 * Display the console with/without list of options.
	 * @param hasOptionList
	 */
	public void show(boolean hasOptionList) {
		if (hasOptionList) {
			System.out.println();
			showOptionList();
			getOptionInput();
		}
	}
	
	/**
	 * Get the user input for the option
	 */
	private void getOptionInput() {
		System.out.print(Constants.SELECT_OPTION);
		try {
			// check to see if input is in valid format and parse it
			parseOptionInput();
		} catch (InputMismatchException e) {
			System.out.println("Error! Incorrect input type: " + e.getMessage());
		}
	}
	
	/**
	 * Check to see if the input is a valid integer
	 */
	private void parseOptionInput() {
		int option = 0;
		try {
			option = Integer.parseInt(inputScanner.nextLine());
			selectOption(option);
		}
		catch (NumberFormatException e) {
			System.out.println(Constants.INVALID_FORMAT);
			parseOptionInput();
		}
	}
	
	/**
	 * Implement to show list of options
	 */
	abstract void showOptionList();
	
	/**
	 * Implement to do something based on selected option
	 */
	abstract void selectOption(int option);
}