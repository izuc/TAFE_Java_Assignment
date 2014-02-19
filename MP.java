/**
* This is the MP subclass, which extends the super politician class.
* The abstract method calcElectorateAllowance is implemented.
* @author Lance Baker
* @since 1.0
* @version 1.0
*/
public class MP extends Politician {
		// These are three constants for the types of MP eletorate area
		public static final char METROPOLITAN_CODE = 'm';
		public static final char MEDIUM_SIZED_CODE = 'r';
		public static final char LARGE_RURAL_CODE = 'l';
		 // Electorate code 
		private char electorateCode;
		
		/**
		* This is a constructor for the MP class, it is used to instantiate a Memeber ofParlament Object.
		* It instantiates the super politician class, by using the values received in the constructor, and initialises MP's area code.
		* The Electorate allowance is then calculated, which is the declared abstract method received from, the politician class.
		* @param statusOfOffice status of office
		* @param officeTitle MP's title of office
		* @param firstName first name
		* @param lastName last name
		* @param electorateName MP's electorate
		* @param numNights number of nights that politician spent in Canberra
		* @param electorateCode MP's area code
		*/
		public MP(StatusOfOffice statusOfOffice, String officeTitle, String firstName, String lastName, String electorateName, int numNights, char electorateCode) {
				// calls the super class
				super(statusOfOffice, officeTitle, firstName, lastName, electorateName, numNights);
				// setter for the area code
				this.setElectorateCode(electorateCode);
				// calculates electorate allowance
				this.calcElectorateAllowance();
		}
		/**
		* This is the setter method for the electorateCode.
		* It converts the received electorateCode to lower case, and sets the instance field to that value.
		* @param electorateCode char - it receives the electorateCode which is used to determine the electorate allowance.
		*/
		public void setElectorateCode(char electorateCode) {
				this.electorateCode = Character.toLowerCase(electorateCode);
		}
		public char getElectorateCode() {
				return this.electorateCode;
		}
		@Override
		/**
		* This is abstract method which was declared in the politician class.
		* This method determines the value of the electorate allowance, based upon the char value received.
		* The constants used are inherited from the super class.
		*/
		public void calcElectorateAllowance() {
				switch(this.electorateCode) {
					case METROPOLITAN_CODE:
						electorateAllowance = METRO_ELECTORATE_ALLOWANCE;
						break;
					case MEDIUM_SIZED_CODE:
						electorateAllowance = MEDIUM_ELECTORATE_ALLOWANCE;
						break;
					case LARGE_RURAL_CODE:
						electorateAllowance = RURAL_ELECTORATE_ALLOWANCE;
						break;
				}
		}
	
	
}
