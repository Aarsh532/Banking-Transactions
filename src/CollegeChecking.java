/**
 * College Checking class extends the Checking Class
 * @author Aarsh, Hersh
 */

public class CollegeChecking extends Checking{
    private Campus campus; //campus code
    private static final double INTEREST_RATE = 0.01; // 1.0% annual interest rate

    /**
     *
     * @param
     * @return
     */
    public CollegeChecking(Profile holder, double balance, Campus campus) {
        super(holder, balance);
        this.campus = campus;
    }

    /**
     *
     * @param
     * @return
     */
    @Override
    public double monthlyFee() {
        return 0.0; // No monthly fee for College Checking
    }

    /**
     *
     * @param
     * @return
     */
    @Override
    public String toString() {
        return String.format("%s::%s",
                super.toString(),
                campus.toString());
    }

    /**
     *
     * @param
     * @return
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
     *
     * @param
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Checking)) return false;
        if (!super.equals(obj)) return false;
        return true;
    }

    /**
     *
     * @param
     * @return
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
