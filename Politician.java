import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
*  This the politician super class
*  It contains the general information that belongs to the politician.
*  It implements the Serializable interface, so the politician can be serialized.
*  There are constants and enums in this class, which values are used depending on the subclass.
* @author Lance Baker
* @since 1.0
* @version 1.0
*/


public abstract class Politician implements Serializable {

	public static final double BASE_SALARY = 127060.0;
	public static final double BASE_ELECTORATE_ALLOWANCE = 27300.0;
	public static final double SENATOR_ELECTORATE_ALLOWANCE = BASE_ELECTORATE_ALLOWANCE;
	public static final double METRO_ELECTORATE_ALLOWANCE = BASE_ELECTORATE_ALLOWANCE;
	public static final double MEDIUM_ELECTORATE_ALLOWANCE = 32450.0;
	public static final double RURAL_ELECTORATE_ALLOWANCE = 39600.0;
	public static final double OVERNIGHT_TRAVEL_ALLOWANCE = 190.0;
	public static final int BASE_ID = 1000;
	public static final String NEW_LINE = "\n";
	public static final String COMMA = ", ";
	public static final String EMPTY_STRING = "";
	public static final String MP_STRING = "MP";
	public static final String SENATOR_STRING = "Senator";
	private static int nextId = 0;
	/** The SalaryOfOffice is a array of percentages, the order of the values corresponds with the StatusOfOffice enum.*/ 
	private final static double[] SalaryOfOffice = {1.00, 0.75, 0.65, 0.50};
	public enum StatusOfOffice { PRIME_MINISTER, MINISTER, JUNIOR_MINISTER, OFFICE_HOLDER, NOT_OFFICE_HOLDER };
	
	private int id;

	/** The salaryOfOffice */ 
	private double salaryOfOffice; 
	
	/** politician's first name */ 
	private String firstName;
	/** politician's last name */ 
	private String lastName;
	/** politician's office title */ 
	private String officeTitle;
	/** politician's status of office*/ 
	private StatusOfOffice statusOfOffice;
	/** politician's electorate name*/ 
	private String electorateName;
	/** politician's number of nights*/ 
	private int numNights;
	/** politician's  base salary*/ 
	private double baseSalary;
	/** politician's  travel allowance*/ 
	private double travelAllowance;
	/** politician's  electorate allowance*/ 
	protected double electorateAllowance;
	
/**
 * 	This is the politician constructor.
 */	
	public Politician(StatusOfOffice statusOfOffice, String officeTitle, String firstName, String lastName, String electorateName, int numNights) {
			this.setStatusOfOffice(statusOfOffice);
			this.setBaseSalary(BASE_SALARY);
			this.setSalaryOfOffice(statusOfOffice);
			this.setOfficeTitle(officeTitle);
			this.setFirstName(firstName);
			this.setLastName(lastName);
			this.setElectorateName(electorateName);
			this.numNights = numNights;
			this.setTravelAllowance();
			this.id = BASE_ID + this.getNextId();
	}
	public abstract void calcElectorateAllowance();
	
/**
 * 	The setTravelAllowance setter method is used to set the travelAllowance instance field to the number of nights multiplied by the travel allowance.
 */
 
	private void setTravelAllowance() { 
		this.travelAllowance = this.numNights * OVERNIGHT_TRAVEL_ALLOWANCE;
	}

/**
 * 	The setFirstName setter method is used to set the firstName instance field to the string parameter received.
 * 	@param firstName String - This is the politician's firstName.
 */
 
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

/**
 * 	The setLastName setter method is used to set the lastName instance field to the string parameter received.
 * 	@param lastName String - This is the politician's lastName.
 */
 
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
/**
 * 	The setElectorateName setter method is used to set the electorateName instance field to the string parameter received.
 * 	@param electorateName String - This is the politician's Electorate Location Name.
 */
 
	public void setElectorateName(String electorateName) {
		this.electorateName = electorateName;
	}
	
/**
 * 	The setSalaryOfOffice setter method is used to set the salaryOfOffice instance field to a calculated value which depends on the statusOfOffice field.
 *	The SalaryOfOffice array contains percentage values, which are used in a formula to calculate a certain percentage of the base salary, plus the base salary.
 *	The formula is as follows Base salary + % of base salary.
 * 	@param statusOfOffice StatusOfOffice - This is the politician's Status of Office enumerated type value.
 */
 
	public void setSalaryOfOffice(StatusOfOffice statusOfOffice) {
		double percentage;
		this.salaryOfOffice = this.getBaseSalary() + (SalaryOfOffice[statusOfOffice.ordinal()] * this.getBaseSalary());
	}
	
/**
 * 	The setStatusOfOffice setter method is used to set the statusOfOffice instance field.
 *	It validates whether the statusOfOffice received is a instanceof StatusOfOffice, else it sets the default enum as NOT_OFFICE_HOLDER.
 * 	@param statusOfOffice StatusOfOffice - This is the politician's Status of Office.
 */
 
	public void setStatusOfOffice(StatusOfOffice statusOfOffice) {
		this.statusOfOffice = (statusOfOffice instanceof StatusOfOffice)? statusOfOffice: StatusOfOffice.NOT_OFFICE_HOLDER;
	}
	
/**
 * 	The setOfficeTitle setter method is used to set the officeTitle instance field to the string parameter received.
 * 	@param officeTitle String - This is the politician's officeTitle.
 */
 
	public void setOfficeTitle(String officeTitle) {
		this.officeTitle = officeTitle;
	}
	
/**
 * 	The setBaseSalary setter method is used to set the baseSalary instance field to the double parameter received.
 * 	@param baseSalary double - This is the politician's baseSalary.
 */
	public void setBaseSalary(double baseSalary) {
		this.baseSalary = baseSalary;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getNextId() {
		Politician.setNextId(1); // Increments the Static Class variable by 1.
		return Politician.nextId;
	}
	
	public int getNumNights() {
		return this.numNights;
	}
	
	public double getElectorateAllowance() {
		return this.electorateAllowance;
	}

	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getElectorateName() {
		return this.electorateName;
	}
	
	public double getTravelAllowance() {
		return this.travelAllowance;
	}
	
	public String getOfficeTitle() {
		return this.officeTitle;
	}
	
	public double getBaseSalary() {
		return this.baseSalary;
	}
	
	public StatusOfOffice getStatusOfOffice() {
		return this.statusOfOffice;
	}
	public double getSalaryOfOffice() {
		return this.salaryOfOffice;
	}

/**
 * 	The toString method is used to list the data of the Politician in a CVS format.
 *	@return strReturn String.
 */
	public String toString() {
		String strReturn = this.getId() + COMMA + this.getStatusOfOffice() + COMMA + this.getOfficeTitle() + COMMA + this.getFirstName() + COMMA + 
							this.getLastName() + COMMA + this.getElectorateName() + COMMA + format(this.getBaseSalary()) + COMMA + format(this.getElectorateAllowance()) + 
							COMMA + this.getNumNights() + COMMA + format(this.getTravelAllowance());
		return strReturn;
	}
	
/**
 * 	The calcTotalSalary method calculates the total of the base salary, electorate allowance, travel allowance, and the salary of office.
 */
	public double calcTotalSalary() {
		return (getBaseSalary() + getElectorateAllowance() + getTravelAllowance() +  getSalaryOfOffice());
	}
	
/**
 * 	The format method is used to convert a double amount to a string currency format.
 *	@param amount double - this is the amount that you want to format as currency.
 *	@return cf.format(amount) String - this is the formatted currency being returned.
 */
	public static String format (double amount) {
		/** Formats the double amount, and returns it as a String.*/ 
		NumberFormat cf = NumberFormat.getCurrencyInstance();
		return cf.format(amount);
	}

/**
 * 	The summarisePoliticianSalaries method is used to make a list summary of the politician's salaries.
 *	@param list ArrayList<Politician> - this is the politicians to be formatted as a String.
 *	@return arrSummary String[]  - returns a array of politician strings.
 */
	public static String[] summarisePoliticianSalaries(ArrayList<Politician> list){
		int index = 0; // Declares and sets the initial index value to zero.
		String[] arrSummary = new String[list.size()]; // Declares and instantiates a new String array, with the max capacity of the ArrayList size.
		String summary = EMPTY_STRING; // Declares and sets it to a empty string.
		for(Politician pol: list) { // Loops for each politician in the ArrayList received.
			summary = pol.toString(); // First gets the toString output.
			// Checks if the politician is a MP or Senator instance. 
			// If the politician is a MP it concatenates the MP Label, the electorate code, and the formatted toString.
			// If the politician is a Senator it concatenates the Senator Label, and the formatted toString.
			summary = (pol instanceof MP) ? MP_STRING + COMMA + ((MP)pol).getElectorateCode() + COMMA + summary: (pol instanceof Senator) ?  SENATOR_STRING + COMMA + summary: EMPTY_STRING;
			// Adds the summary to the array of politician summaries.
			arrSummary[index] = summary;
			index++; // Increments the index.
		}
		return arrSummary; // Returns the array of summaries.
	}
	
	public static void setNextId(int value) {
		Politician.nextId += value; // Adds the value to the static nextId field.
	}
}
