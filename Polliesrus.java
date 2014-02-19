import java.io.File;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
* The driver class used to run the polliesrus application.
* This will allow the user to  create new a politician, 
* view politician's details, update the status of office, list all politicians, serialize and load politicians.
* @author Lance Baker
* @since 1.0
* @version 1.0
*/

public class Polliesrus {		
		private static final String NOT_FOUND = "Not found";
		private static final String QUIT = "Quit";
		private static final String NEW_LINE = "\n";
		private static final String ENTER_ANOTHER_POLITICIAN = "Enter another politician [Y/N]? ";
		private static final String LOAD_TEST_DATA = "Load test data [Y/N]? ";
		private static final String LOAD_DATA = "Load data [Y/N]? ";
		private static final String DATA_LOADED = "Data has been loaded";
		private static final String TEST_DATA_LOADED = "Test data has been loaded";
		private static final String DATA_SAVED = "Data has been saved";
		private static final String SAVE_DATA = "Save data [Y/N]? ";
		private static final String FILE_NAME = "data.sav";
		private static final String ENTER_YES_NO = "Please enter either (Y) or (N)";
		private static final String SEARCH_POLITICIANID = "Search PoliticianID (Type 'Quit' to Exit): ";
		private static final String SEARCH_LAST_NAME = "Search Last Name (Type 'Quit' to Exit): ";
		private static final String SEARCH_TYPE = "Search Type [1 = PoliticianID, 2 = Last Name]: ";
		private static final String INVALID_SEARCHTYPE = "Invalid Search Type";
		private static final String SPACE = " ";
		private static final String YES = "Y";
		private static final String NO = "N";
		private static final String EMPTY_STRING = "";
		private static final String COLON = ":";
		private static final String ENTER = "Enter";
		private static final String PRESS_ENTER = "Press Enter to continue...";
		private static final String MY_NAME = "Lance Baker";
		private static final String POLITICIAN_MAIN_MENU = "Pollies-R-US";
		private static final String ADD_POLITICIAN_ERROR = "An error occured when adding a policitian, do you want to try again [Y/N]?";
		private static final String NAME = "Name";
		private static final String TITLE = "Title";
		private static final String ELECTORATE = "Electorate";
		private static final String BASE_SALARY = "Base salary";
		private static final String ELECTORATE_ALLOWANCE = "Electorate Allowance";
		private static final String SENATORIAL_BONUS = "Senatorial Bonus";
		private static final String STATUS_OF_OFFICE = "Status of Office";
		private static final String TOTAL_SALARY = "Total Salary (incl travel)";
		private static final String SELECT_ELECTORATE_TYPE = "Electorate Type [1: SENATOR, 2: MEMBER OF PARLIAMENT]" + COLON + SPACE;
		private static final String SELECT_ELECTORATE_CODE = "Electorate code [m: Metropolitan, r: Medium Sized, l: Large Rural]" + COLON + SPACE;
		private static final String SELECT_STATUS_OF_OFFICE = "Status of Office [1: PRIME MINISTER, 2: MINISTER, 3: JUNIOR MINISTER, 4: OFFICE HOLDER, 5: NOT OFFICE HOLDER]" + COLON + SPACE;
		private static final String[] POLITICIAN_OPTIONS = {ENTER + SPACE + "Office Title" + COLON + SPACE, ENTER + SPACE + "First Name" + COLON + SPACE, ENTER + SPACE + "Last Name" + COLON + SPACE, ENTER + SPACE + "Electorate Name" + COLON + SPACE, ENTER + SPACE + "Number Of Nights" + COLON + SPACE, ENTER + SPACE + "Number Of Terms" + COLON + SPACE};
		private static final String[] MAIN_OPTIONS = {"Add Politician", "View Politician", "Update Politician", "List All Politicians"};
		
		/**
		The mainMenu method gets called from the main method. It continues in a method recursive loop until the user escapes by pressing 9.
		The method prompts a "Press enter to continue" message, and requires the user to press enter to continue before cycling through the method again.
		If the user enters the option 9, it then executes the default case and exits the recursive loop with a prompt to save the data.
		The mainMenu receives parameters: the TextMenu mainMenu object , the ArrayList of politicians, the serialize object, and the ReadKb object.
		*/
		public void mainMenu(TextMenu mainMenu, ArrayList<Politician> politicians, Serialize<ArrayList<Politician>> serialize, ReadKb kb) {
				// The option value is then set to the return value from the getMenuSelection method, which is used to  prompt for input and validate whether the inputis valid. The method also displays the text menu options.
				int option = mainMenu.getMenuSelection();
				System.out.println(NEW_LINE);
				// The following switch is used to test the variable option entered.
				switch(option) {
					case 1:
						// Prompts the user to select a type of politician to add.
						// Passes the politicians arraylist, and the readkb object as arguments.
						this.addPolitician(politicians, kb);
						this.prompt(kb); // Prompts the user to press enter to continue.
						break;
					case 2:
						// This menu allows the user to search for a politician based upon the politician id, or the politician last name.
						// It then displays the politician back to the user.
						// Passes the politicians arraylist, and the readkb object as arguments.
						this.viewPolitician(politicians, kb);
						this.prompt(kb); // Prompts the user to press enter to continue.
						break;
					case 3:
						// Allows the user to update the status of office for a politician searched for.
						this.updatePolitician(politicians, kb);
						this.prompt(kb); // Prompts the user to press enter to continue.
						break;
					case 4:
						// Displays all politicians in the array list.
						this.listAll(politicians);
						this.prompt(kb); // Prompts the user to press enter to continue.
						break;
					default:
						// On exit, it prompts the user if they want to save the data. 
						// If they select yes, it then serializes the arraylist object.
						this.saveData(politicians, serialize, kb);
						return; // Exits from the method, thus ending the application.
				}
				this.mainMenu(mainMenu, politicians, serialize, kb); // Does a recursive method loop.
		}
		
		/**
		The addPolitician method gets called from the mainMenu method.
		The method receives the ArrayList of politicians and the ReadKb object as parameters.
		It prompts the user to select a type of politician to add.
		If they select 1 it then proceeds to add a Senator, otherwise if they select 2 it proceeds to add a MemberOfParliament.
		The default case within the switch recalls the addPolitician method, therefore doing a recursive method loop until a correct value has been selected.
		*/
		public void addPolitician(ArrayList<Politician> politicians, ReadKb kb) {
				switch(kb.getInt(SELECT_ELECTORATE_TYPE)) {
					case 1:
						// Proceeds to the addSenator Method, which will instantiate a new Senator object and add that object into the politicians ArrayList passed.
						this.addSenator(politicians, kb);
						break;
					case 2:
						// Proceeds to the addMemberOfParliament Method, which will instantiate a new MP object and add that object into the politicians ArrayList passed.
						this.addMemberOfParliament(politicians, kb);
						break;
					default:
						// Recalls this method if the user entered a different value that wasn't covered in the switch.
						// Therefore forcing the user to enter a valid value.
						this.addPolitician(politicians, kb);
				}
				// Prompts the user if they want to enter another politician, if they select yes it returns true.
				if (this.moreInput(ENTER_ANOTHER_POLITICIAN, kb)) { 
					this.displayString(NEW_LINE);
					// Recalls the method again, to re-add a politician.
					this.addPolitician(politicians, kb); 
				}
		}
		/**
		The addSenator method gets called from the addPolitician method. This method receives the politician ArrayList and the ReadKb object as parameters.
		Completes the following within a Try Catch incase a exception error is thrown, if one is thrown - it prompts the user if they want to try again.
		Receives the input from the user and then instantiates a new Senator object, and adds that object into the politicians ArrayList.
		*/
		public void addSenator(ArrayList<Politician> politicians, ReadKb kb) {
				try {
					// Instantiates a Senator object and adds the object into the politicians ArrayList.
					politicians.add(new Senator(convertStatusOfOffice(SELECT_STATUS_OF_OFFICE, kb), kb.getString(POLITICIAN_OPTIONS[0]), kb.getString(POLITICIAN_OPTIONS[1]), 
												kb.getString(POLITICIAN_OPTIONS[2]), kb.getString(POLITICIAN_OPTIONS[3]),
												kb.getInt(POLITICIAN_OPTIONS[4]), kb.getInt(POLITICIAN_OPTIONS[5])));
				} catch (Exception ex) {
				   if (moreInput(ADD_POLITICIAN_ERROR, kb)) {
						this.addSenator(politicians, kb); // Recalls this method again, if the user wants to try again.
				   }
				}
		}
		/**
		The addMemberOfParliament method gets called from the addPolitician method. This method receives the politician ArrayList and the ReadKb object as parameters.
		Completes the following within a Try Catch incase a exception error is thrown, if one is thrown - it prompts the user if they want to try again.
		Receives the input from the user and then instantiates a new MP object, and adds that object into the politicians ArrayList.
		*/
		public void addMemberOfParliament(ArrayList<Politician> politicians, ReadKb kb) {
				try {
					// Instantiates a MP object and adds the object into the politicians ArrayList.
					politicians.add(new MP(convertStatusOfOffice(SELECT_STATUS_OF_OFFICE, kb), kb.getString(POLITICIAN_OPTIONS[0]),
												kb.getString(POLITICIAN_OPTIONS[1]), kb.getString(POLITICIAN_OPTIONS[2]), 
												kb.getString(POLITICIAN_OPTIONS[3]), kb.getInt(POLITICIAN_OPTIONS[4]), 
												validElectorateCode(SELECT_ELECTORATE_CODE, kb)));					
				} catch (Exception ex) {
				   if (moreInput(ADD_POLITICIAN_ERROR, kb)) {
						this.addMemberOfParliament(politicians, kb); // Recalls this method if the user wants to try again.
				   }
				}
		}
		/**
		The validElectorareCode method gets called from the addMemberOfParliament method. It receives the text for the data to prompt for, and the ReadKb object.
		The method prompts the user for a char value, and completes some validation to determine whether it was a correct code entered. 
		If the code is not valid, it then recalls the same method again prompting for another value. Entering a recursive loop until a valid code is entered.
		Returns the validated code to the caller.
		*/
		public char validElectorateCode(String text, ReadKb kb) {
				char code = Character.toLowerCase(kb.getChar(text)); // Prompts for a char value, and converts that to lowercase.
				// If the code doesn't match a valid value, it then recalls this method. Thus entering a recursive loop until a valid code is entered.
				if (!(code == MP.METROPOLITAN_CODE || code == MP.MEDIUM_SIZED_CODE || code == MP.LARGE_RURAL_CODE)) {
					code = this.validElectorateCode(text, kb);
				}
				return code; // Returns a char value.
		}
		/**
		The convertStatusOfOffice method gets called from the addMemberOfParliament and addSenator method. It recieves the text to prompt for, and the ReadKb object.
		It prompts the user for a status of office value (a integer), which gets converted to a StatusOfOffice enum through the use of a switch.
		If the user enters a invalid value, it recalls the same method again. And only exits the recursive loop, until a valid value has been entered.
		Returns a StatusOfOffice enum value.
		*/
		public Politician.StatusOfOffice convertStatusOfOffice(String text, ReadKb kb) {
				Politician.StatusOfOffice statusOfOffice = Politician.StatusOfOffice.NOT_OFFICE_HOLDER;
				// This prompts the user for a integer value, and evaluates the value in the switch.
				// If the user enters a invalid value the default case is used. This recalls the method again, creating a recursive method loop.
				switch(kb.getInt(text)) {
					case 1:
						statusOfOffice = Politician.StatusOfOffice.PRIME_MINISTER;
						break;
					case 2:
						statusOfOffice = Politician.StatusOfOffice.MINISTER;
						break;
					case 3:
						statusOfOffice = Politician.StatusOfOffice.JUNIOR_MINISTER;
						break;
					case 4:
						statusOfOffice = Politician.StatusOfOffice.OFFICE_HOLDER;
						break;
					case 5:
						statusOfOffice = Politician.StatusOfOffice.NOT_OFFICE_HOLDER;
						break;
					default:
						statusOfOffice = this.convertStatusOfOffice(text, kb);
				}
				return statusOfOffice;
		}
		/**
		The viewPolitician method is called from the mainMenu method. It receives the politicians ArrayList and the ReadKb object as parameters.
		The method is used to search for a politician and display the returned value to the user.
		*/
		public void viewPolitician(ArrayList<Politician> politicians, ReadKb kb) {
				// Gets the politician from the searchPolitician method. This is reused in the updatePolitician method.
				Politician politician = this.searchPolitician(politicians, kb);
				if (politician != null) {	// Checks whether a politician was found.			
					this.displayPolitician(politician); // Passes a politician object to the displayPolitician method, which prints the output to the user.
				}
		}
		/**
		The updatePolitician method is called from the mainMenu method. It receives the politicians ArrayList and the ReadKb object as parameters.
		The method is used to update the StatusOfOffice for the politician found.
		*/
		public void updatePolitician(ArrayList<Politician> politicians, ReadKb kb) {
				// Gets the politician from the searchPolitician method. This is reused in the viewPolitician method.
				Politician politician = this.searchPolitician(politicians, kb);
				if (politician != null) { // Checks whether a politician was found.
					// Displays the politician.
					this.displayString(TITLE + COLON + SPACE + politician.getOfficeTitle());
					this.displayString(NAME + COLON + SPACE + politician.getFirstName() + SPACE + politician.getLastName());
					this.displayString(STATUS_OF_OFFICE + COLON + SPACE + politician.getStatusOfOffice());
					// Updates the status of office, with the new StatusOfOffice entered from the user.
					politician.setStatusOfOffice(convertStatusOfOffice(SELECT_STATUS_OF_OFFICE, kb));
				}
		}
		/**
		The searchPolitician method gets called from the viewPolitician and updatePolitician method. It recieves the ArrayList of politicians, and the ReadKb object.
		It prompts the user for a search type, either the Politician Id, or the Politician Last Name.
		*/
		public Politician searchPolitician(ArrayList<Politician> politicians, ReadKb kb) {
				int searchType = 0;
				// Assigns the politician object as null
				Politician politician = null;
				// This method prompts the user to make a selection to search for either a callerid or a customer first name.
				// If the user inputs a invalid selection, it will loop the method until a valid selection is made.
				while (!(searchType == 1 || searchType == 2)) {
					searchType = kb.getInt(SEARCH_TYPE);
					if (searchType == 1 || searchType == 2) {
						switch(searchType) {
							// Once a valid selection has been made, it then calls the validateSearch method with the corresponding arguments.	
							case 1:
								politician = this.validateSearch(SEARCH_POLITICIANID, false, politicians, kb);
								break;
							case 2:
								politician = this.validateSearch(SEARCH_LAST_NAME, true, politicians, kb);
								break;
						}
					} else {
						this.displayString(INVALID_SEARCHTYPE); // 
					}
				}
				return politician;
		}
		
		/**
		The validateSearch method gets called from the searchPolitician method. It recieves the text to prompt for, a boolean variable representing if the search is byName, ArrayList of politicians, and the ReadKb object.
		It returns a Politician Object to the searchPolitician method.
		*/
		public Politician validateSearch(String text, boolean byName, ArrayList<Politician> politicians, ReadKb kb) {
				Politician foundPolitician = null;
				String search = EMPTY_STRING;
				// The search is looped if search is not equal to 'Quit' and the foundPolitician object is null.
				while ((!(search.equalsIgnoreCase(QUIT))) && foundPolitician == null) {
					// Prompts the user for the string to search for.
					search = kb.getString(text);
					// If the string entered is not equal to 'Quit', it then proceeds.
					if (!(search.equalsIgnoreCase(QUIT))) {
						// Searches for the politician object based upon the search variable, and whether it is byName or the Politician ID.
						foundPolitician = this.findPolitician(search, byName, politicians);
						// If the foundPolitician is equal to null, it then displays the NOT_FOUND error message.
						if (foundPolitician == null) {
							this.displayString(NOT_FOUND);
						}
					}
				}
				return foundPolitician;
		}
		
		/**
		The findPolitician method gets called from the validateSearch method. It recieves the string to search for, a boolean variable representing if the search is byName, and the ArrayList of politicians.
		It loops through each politician in the ArrayList received and attempts to match the search string, against the politician's 
		*/
		public Politician findPolitician(String search, boolean byName, ArrayList<Politician> politicians) {
			Politician found = null;
			for(Politician pol: politicians) {
				if (pol != null) { // If the politician is not null, it then attempts to match the search string with the properties of the politician object.
					if (((byName) && (search.equalsIgnoreCase(pol.getLastName()))) || ((!byName) && (Integer.parseInt(search) == pol.getId()))) {
						found = pol; // If there is a match, it then assigns the found object with the pol object.
					}
				}
			}
			return found; // Returns the found object.
		}
		
		/**
		The displayPolitician method gets called from the viewPolitician method. It receives a politician object, and prints the data formatted.
		*/
		public void displayPolitician(Politician pol) {
				System.out.println(NEW_LINE);
				System.out.printf("%s %40s" + NEW_LINE,  NAME + COLON, (pol.getFirstName() + SPACE + pol.getLastName()));
				System.out.printf("%s %30s" + NEW_LINE,  TITLE + COLON, pol.getOfficeTitle());
				System.out.printf("%s %33s" + NEW_LINE,  BASE_SALARY + COLON, Politician.format(pol.getBaseSalary()));
				System.out.printf("%s %23s" + NEW_LINE,  ELECTORATE_ALLOWANCE + COLON, Politician.format(pol.getElectorateAllowance()));
				if (pol instanceof Senator) { // Checks whether the politician is a Senator Object.
					System.out.printf("%s %28s" + NEW_LINE,  SENATORIAL_BONUS + COLON, Politician.format(((Senator)pol).getBonus()));
				}
				System.out.printf("%s %18s" + NEW_LINE,  TOTAL_SALARY + COLON, Politician.format(pol.calcTotalSalary()));	
		}
		/**
		The listAll method gets called from the mainMenu method. It recieves the ArrayList of politicians.
		It loops through each politician in the ArrayList received and attempts to match the search string, against the politician's 
		*/
		public void listAll(ArrayList<Politician> politicians) {
				String[] data = Politician.summarisePoliticianSalaries(politicians);
				for (String output: data) {
					this.displayString(output + NEW_LINE);
				}
		}
		
		/**
		This method prompts the user for either a Yes[y] / No[n]  value and converts that selection to a boolean value (Yes being true).
		The method loops if the user inputs anything other than a Y or N, and once the user enters a valid choice it returns the boolean value.
		*/
		public boolean moreInput(String text, ReadKb kb) {
				String moreInput;
				boolean validMore = false, returnValue = false;
				while (!validMore) {
					moreInput = kb.getString(text);
					if (moreInput.equalsIgnoreCase(YES)) {
						validMore = true;
						returnValue = true;
					} else if(moreInput.equalsIgnoreCase(NO)) {
						validMore = true;
						returnValue = false;
					} else {
						displayString(ENTER_YES_NO);
					}
				}
				return returnValue;
		}
		
		/**
		This method is used to load the test data, and populate that into the ArrayList passed in as a parameter.
		*/
		public void loadTestData(ArrayList<Politician> politicians) {
				politicians.add(new Senator(Politician.StatusOfOffice.MINISTER, "Minister of Finance", "Brian", "Jones", "Newcastle", 10, 20));
				politicians.add(new Senator(Politician.StatusOfOffice.JUNIOR_MINISTER, "Junior Minister of Finance", "Lance", "Baker", "Gosford", 53, 29));
				politicians.add(new MP(Politician.StatusOfOffice.OFFICE_HOLDER, "Assistant to the Minister of Finance", "Summer", "Glau", "Newcastle", 12, 'm'));
				politicians.add(new MP(Politician.StatusOfOffice.PRIME_MINISTER, "Prime Minister", "Kevin", "Rudd", "Australia", 10, 'r'));
		}
		
		/**
		* This method is used to load the data from the serialized object file.
		* It returns the casted ArrayList<Politician> object.
		* @param politicians ArrayList<Politician> - This is an ArrayList of the politicians
		* @param serialize Serialize<ArrayList<Politician>> - This is the serialize object.
		*/
		public ArrayList<Politician> loadData(ArrayList<Politician> politicians, Serialize<ArrayList<Politician>> serialize) {
				return serialize.loadObject(FILE_NAME);
		}

		/**
		* This method is used to serialize the ArrayList of politicians to the filename specified.
		* It prompts the user for confirmation to save the data, and passes the politicians, and the filename to the saveObject method.
		* @param politicians ArrayList<Politician> - This is an ArrayList of the politicians
		* @param serialize Serialize<ArrayList<Politician>> - This is the serialize object.
		*/
		public void saveData(ArrayList<Politician> politicians, Serialize<ArrayList<Politician>> serialize, ReadKb kb) {
			if (this.moreInput(SAVE_DATA, kb)) { // Prompts the user if they want to save the data.
				serialize.saveObject(politicians, FILE_NAME); // Serializes the politicians arraylist to the filename specified.
				this.displayString(DATA_SAVED); // Displays a message which will confirm that the data has been saved.
			}
		}
		
		/**
		* This method is used to prompt for a return key to continue.
		* It prompts the user for confirmation to save the data, and passes the politicians, and the filename to the saveObject method.
		* @param kb ReadKb - This is the readkb object.
		*/
		public void prompt(ReadKb kb) {
				this.displayString(NEW_LINE + PRESS_ENTER); // Displays a message for them to press enter to continue.
				kb.prompt(); // Halts the application until the user has pressed enter.
		}
		/**
		* This method is used to display output messages to the user.
		* This is the only method that directly uses the println method.
		* @param input String - This is the string to be displayed.
		*/
		public void displayString(String input) {
				System.out.println(input);
		}
		
		/**
		* This method is used to refresh the static nextId class variable which the size of the politicians arraylist.
		* @param politicians ArrayList<Politician> - This the arraylist of politicians.
		*/
		public void refreshNextId(ArrayList<Politician> politicians) {
				Politician.setNextId(politicians.size()); // This calls the static method in the politician class which will refresh the static nextId field with the size of the politician arraylist received.
		}
		
		/**
		* This is the main method of the polliesrus class.
		* It declares and instantiates the politicians arraylist, the serialize object, the readkb object, and the textmenu object.
		*/
		public static void main(String[] args) {
				ArrayList<Politician> politicians = new ArrayList<Politician>(); // The politicians arraylist.
				Serialize<ArrayList<Politician>> serialize = new Serialize<ArrayList<Politician>>(); // The ArrayList<Politician> serialize object.
				ReadKb kb = new ReadKb(); // The readKb object.
				Polliesrus pollies = new Polliesrus(); // Instantiates the polliesrus driver class.
				// Creates the main menu.
				TextMenu mainMenu = new TextMenu(Polliesrus.MAIN_OPTIONS, kb, Polliesrus.POLITICIAN_MAIN_MENU, Polliesrus.MY_NAME, new Date(), Polliesrus.QUIT);
				
				
				if (pollies.moreInput(LOAD_DATA, kb)) {
					politicians = pollies.loadData(politicians, serialize); // Loads the politician's arraylist.
					pollies.refreshNextId(politicians); // Refreshes the static nextId class variable to the size of the arraylist.
					pollies.displayString(DATA_LOADED); // Displays a message to the user saying the data has been loaded.
				}
				if (politicians.size() == 0) { // If the politicians size is zero, it prompts the user if they want to load some test data.
					if (pollies.moreInput(LOAD_TEST_DATA, kb)) { // Prompts the user for confirmation to load the test data.
						pollies.loadTestData(politicians); // Loads the test data.
						pollies.displayString(TEST_DATA_LOADED); // Outputs a message to the user saying the test data has been loaded.
					}
				}
				pollies.mainMenu(mainMenu, politicians, serialize, kb); // Calls the mainMenu method, which will display the menu system.
		}
}
