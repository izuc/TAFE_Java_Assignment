/**
* File:	TextMenu.java
* Purpose:	Create a text menu - run from a command-line
*		This is a modified version of the original TextMenu class which was developed by Gerard Sparke.
* Author:	Gerard Sparke (The Java Way)
* Modified By: Lance Baker
* Date Last Modified: 28 November 2008.
*/
import java.util.Date;
import java.text.SimpleDateFormat;
public class TextMenu {
	private int selection;			
	private String[] menuText;			
	private ReadKb kb;				
	private String menuName;
	private String myName;
	private String returnTo;
	private Date currentDate;
	private static final String SELECTION_INVALID = "selection was invalid";
	private static final String ENTER_SELECTION = " - Enter your selection: ";
	private static final String DASHED_LINE = "-----------------";
	private static final String ENTER_OPTION = "Enter menu option: ";
	private static final String DOT = ". ";
	private static final String EXIT_NUMBER = "9" + DOT;
	private static final String NEW_LINE = "\n";
	private static final String SPACE_GAP = " - ";
	
	public TextMenu(String[] menuText,  ReadKb kb, String menuName, String myName, Date currentDate, String returnTo) {
		this.menuText = menuText;
		this.kb = kb;
		this.menuName = menuName;
		this.myName = myName;
		this.currentDate = currentDate;
		this.returnTo = returnTo;
	}

	public int getMenuSelection() {
		this.displayMenu();
		this.selection = kb.getInt(ENTER_OPTION);
		if ((this.menuText.length < this.selection) && (this.selection != 9)) {
			System.out.println(NEW_LINE + SELECTION_INVALID + NEW_LINE);
			this.getMenuSelection(); 
		}
		return this.selection;
	}

	public void displayMenu() {
		SimpleDateFormat defaultFormat = new SimpleDateFormat("dd-MM-yyyy"); 
		System.out.println(NEW_LINE + myName + SPACE_GAP + defaultFormat.format(currentDate));
		System.out.println(NEW_LINE + this.menuName + NEW_LINE + DASHED_LINE);
		for (int index = 0, i = 1; index < menuText.length; index++, i++) {
			System.out.println(i + DOT + menuText[index]);
		}
		System.out.println(EXIT_NUMBER + returnTo);
	}
}