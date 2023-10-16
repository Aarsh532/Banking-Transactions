package project2;

/**
 * College Checking class extends the Checking Class
 * @author Aarsh, Hersh
 */

public class CollegeChecking extends Checking{
    private Campus campus; //campus code
    private static final double INTEREST_RATE = 0.01; // 1.0% annual interest rate

    /**
     * Method to create an instance of college checking object
     * @param holder as Profile
     * @param balance as double
     * @param campus as Campus
     */
    public CollegeChecking(Profile holder, double balance, Campus campus) {
        super(holder, balance);
        this.campus = campus;
    }

    /**
     * Method to return monthly fee's for college checking accounts
     * @return 0
     */
    @Override
    public double monthlyFee() {
        return 0.0; // No monthly fee for College Checking
    }

    /**
     * Method to format string witn correct parameters
     * @return string with correct format
     */
    @Override
    public String toString() {
        return String.format("%s::%s",
                super.toString(),
                campus.toString());
    }

    /**
     * Method to format string into correct format with the addition of fees
     * @return String in correct format
     */
    public String stringWithFees(){
        String feeStr = String.format("$%.2f", monthlyFee());
        String interestStr = String.format("$%.2f", monthlyInterest());
        String balanceStr = String.format("$%,.2f", balance);
        return String.format("College Checking::%s %s %s::Balance %s::%s::fee %s::monthly interest %s",
                holder.getFname(), holder.getLname(), holder.getDob().dateString(),
                balanceStr, campus, feeStr, interestStr);
    }

    /**
     * Method to compare objects
     * @param obj as Object
     * @return true if objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Checking)) return false;
        if (!super.equals(obj)) return false;
        return true;
    }

    /**
     * Method to compare accounts
     * @param o as Account
     * @return int
     */
    @Override
    public int compareTo(Account o){
        int superComparison = super.compareTo(o);
        if(superComparison != 0){
            return superComparison;
        }
        return this.campus.compareTo(((CollegeChecking) o).campus);
    }

}
