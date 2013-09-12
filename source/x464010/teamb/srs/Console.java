package x464010.teamb.srs;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Amit Dhamija
 * @version 1.2
 * @revision 1.1 Amit Dhamija	Added additional methods
 * @revision 1.2 Amit Dhamija	Modified the class to better support the list of options
 */
public abstract class Console {

	protected static Scanner inputScanner;
	protected boolean hasOptionList = false;
	
	/**
	 * Default constructor.
	 */
	public Console() {
		// set up Scanner object using system.in
		inputScanner = new Scanner(System.in);
	}
	
	public void show(boolean hasOptionList) {
		if (hasOptionList) {
			System.out.println();
			showOptionList();
			getOptionInput();
		}
	}
	
	private void getOptionInput() {
		System.out.print(Constants.SELECT_OPTION);
		try {
			// check to see if input is in valid format and parse it
			parseOptionInput();
			// TODO: close scanner
			inputScanner.close();
		} catch (InputMismatchException e) {
			System.out.println("Error! Incorrect input type: " + e.getMessage());
		}
	}
	
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
	 * Implement to print any output before showing the list of options
	 */
	//abstract void printBeforeOptionList();
	
	/**
	 * Implement to show list of options
	 */
	abstract void showOptionList();
	
	/**
	 * Implement to do something based on selected option
	 */
	abstract void selectOption(int option);
}