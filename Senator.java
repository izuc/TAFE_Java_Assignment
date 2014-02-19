/**
* This is the senator subclass, which extends the super politician class.
* It contains a default constructor with no parameters, which chains to the main constructor.
* The abstract method calcElectorateAllowance is implemented.
* @author Lance Baker
* @since 1.0
* @version 1.0
*/

public class Senator extends Politician {
	/**  The BONUS constant is used to provide a base value for the calculated senatorial bonus. */
	private static final double BONUS = 500.00;
	/**  The PERCENTAGE constant is used in the formula for the calculation of the bonus to be applied. */
	private static final double PERCENTAGE = 0.025;
	/**  The number of terms. */
	private int numTerms;
	/**  The bonus instance field is used to store the calculated bonus. */
	private double bonus;
	/** The default senator constructor which chains to the main constructor.  It sets the status of office to NOT_OFFICE_HOLDER, and empty strings for the remaining fields.*/
	public Senator() {
		this(Politician.StatusOfOffice.NOT_OFFICE_HOLDER, "", "", "", "", 0, 0);
	}

	/**
	* This is the constructor for the Senator class, it is used to instantiate a Senator Object.
	* It instantiates the super politician class, by using the values received in the constructor, and initialises the number of terms.
	* The Electorate allowance is then calculated, which is the declared abstract method received from, the politician class.
	* The Senator Bonusis then calculated.
	*/
	
	public Senator(StatusOfOffice statusOfOffice, String officeTitle, String firstName, String lastName, String electorateName, int numNights, int numTerms) {
		super(statusOfOffice, officeTitle, firstName, lastName, electorateName, numNights);
		this.setNumTerms(numTerms);
		this.calcElectorateAllowance();
		this.setBonus(BONUS);
	}
	
	/**
	* This is abstract method which was declared in the politician class.
	* The electorateAllowance inherited field is set to the SENATOR_ELECTORATE_ALLOWANCE constant.
	*/
	public void calcElectorateAllowance() {
		electorateAllowance = SENATOR_ELECTORATE_ALLOWANCE;
	}
	
	/**
	* The setNumTerms setter method is used to set the number of terms.
	* It checks to see if the received number of terms is greater than zero, else it sets the value to zero.
	* @param numTerms int - The number of terms,
	*/
	private void setNumTerms(int numTerms) {
		this.numTerms = (numTerms > 0) ? numTerms : 0;
	}
	
	/**
	* The setBonus method is used to calculate the bonus for the senator.
	* @param bonus double - This is the base bonus.
	*/
	private void setBonus(double bonus) {
		this.bonus = bonus + ((PERCENTAGE * numTerms) * getBaseSalary());
	}
	
	/**
	* The getBonus() method is used to return the senator's bonus.
	* @return bonus double - This is the base bonus.
	*/
	public double getBonus() {
		return this.bonus;
	}
	
	/**
	* The getNumTerms() method is used to return the senator's number of terms.
	* @return numTerms int - This is the senator's number of terms.
	*/
	public int getNumTerms() {
		return this.numTerms;
	}

	@Override	
	/**
	* The calcTotalSalary() method is used to calculate the total salary for the senator.
	* It overrides the default method provided by the super class, and it adds the bonus to it.
	* @return super.calcTotalSalary() + bonus double - it returns the value received from the method provided by the super class, and adds the bonus.
	*/
	public double calcTotalSalary() {
		return super.calcTotalSalary() + bonus;
	}
}
