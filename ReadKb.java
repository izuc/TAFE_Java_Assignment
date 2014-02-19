/**
* File:	ReadKb.java
* Purpose:	Provide methods to read an int, a double and String from the keyboard.
* 		This is a modified version of the original readKB class which was developed by Gerard Sparke.
* Author:	Gerard Sparke (The Java Way)
* Modified By: Lance Baker
* Date Last Modified: 19 September 2008.
*/
import java.util.Scanner;
import java.util.regex.*;
public class ReadKb {
	Scanner scanner = new Scanner(System.in);	
	private static final String YOU_ENTERED = "You entered: ";
	private static final String DOUBLE_EXPECTED = " - a double was expected.";
	private static final String INTEGER_EXPECTED = "A integer was expected.";
	/** The 'String text' parameter is the following methods is used to display the initial "prompt" message for the input. */
	public double getDouble(String text) {
		double d = 0.0;
		System.out.print(text);
		if (scanner.hasNextDouble()) {
			d = scanner.nextDouble();
		} else {
			String s = scanner.next();
			System.out.println(YOU_ENTERED + s + DOUBLE_EXPECTED);
		}
		return d;
	}
	public int getInt(String text) {
		String value;
		do {
			value = this.getString(text);
			if (!isNumeric(value)) {
				System.out.println(INTEGER_EXPECTED);
			}
		} while (!isNumeric(value));
		int i = Integer.parseInt(value);
		return i;
	}
	/**
	* A isNumeric method has been added which checks the data against a pattern.
	* Returns true if it is numeric else false.
	*/
	public boolean isNumeric(String value) {
		Pattern num = Pattern.compile("^[0-9]+$");
		Matcher m = num.matcher(value);		
		if (m.find()) {
			return true;
		} else {
			return false;
		}
	}
	// A areLetters method has been added which checks the data against a pattern to ensure that there are numeric values in it.
	// Returns true if it is entirely letters else false.
	public boolean areLetters(String value) {
		Pattern num = Pattern.compile("^[A-Z][a-z]+$");
		Matcher m = num.matcher(value);		
		if (m.find()) {
			return true;
		} else {
			return false;
		}
	}
	public String getString(String text)  {
		System.out.print(text);
		String s = scanner.next();
		return s;
	}
	public String getLine() {
		String s = scanner.nextLine();
		return s;
	}
	public void prompt() {
		String s;
		s = getLine();
		s = getLine();
	}
	public char getChar(String text)  {
		System.out.print(text);
		String input = scanner.next();
		char c = input.charAt(0);
		return c;
	}
}