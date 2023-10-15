/**
 * Checking class extends the Account Class
 * @author Aarsh, Hersh
 */

public class Checking extends Account {
    private static final double INTEREST_RATE = 0.01; // 1.0% annual interest rate
    private static final double MONTHLY_FEE = 12.0;
    private static final double MIN_BALANCE_FEE_WAIVED = 1000;
    private static final int NUM_MONTHS = 12;

    /**
     * Method to create instance of checking object
     * @param holder as Profile
     * @param balance as double
     */
    public Checking(Profile holder, double balance) {
        super(holder, balance);
    }

    /**
     * Calculates balance * interest
     * @return monthlyInterest as double
     */
    @Override
    public double monthlyInterest() {
        return balance * (INTEREST_RATE / NUM_MONTHS);
    }

    /**
     * Calculates monthly fee based on balance in account
     * @return monthlyFee as balance
     */
    @Override
    public double monthlyFee() {
        if (balance >= MIN_BALANCE_FEE_WAIVED) {
            return 0.0;
        }
        return MONTHLY_FEE;
    }

    /**
     * Method to turn a string into the correct format
     * @return string in correct format
     */
    @Override
    public String toString(){
        return String.format("%s::%s %s %s::Balance $%,.2f",
                getClass().getSimpleName(),
                holder.getFname(),
                holder.getLname(),
                holder.getDob().dateString(),
                balance);
    }

    /**
     * Method to format a string with correct values and additional fees value
     * @return string with correct format
     */
    public String stringWithFees(){
        String feeStr = String.format("$%.2f", monthlyFee());
        String interestStr = String.format("$%.2f", monthlyInterest());
        String balanceStr = String.format("$%,.2f", balance);
        return String.format("Checking::%s %s %s::Balance %s::fee %s::monthly interest %s",
                holder.getFname(), holder.getLname(), holder.getDob().dateString(),
                balanceStr, feeStr, interestStr);
    }

    /**
     * Method to compare objects
     * @param obj as Object
     * @return true if obj equals, returns false otherwise
     */
    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Checking)) return false;
        Checking checking = (Checking) obj;
        return checking.holder.equals(holder);
    }

    /**
     * Method to compare account types
     * @param o as Account
     * @return int based on comparison
     */
    @Override
    public int compareTo(Account o) {
        int typeComparison =this.getClass().getSimpleName().compareTo(o.getClass().getSimpleName());
        if(typeComparison > 0){
            return 1;
        }
        if(typeComparison < 0){
            return -1;
        }
        if(this.holder.compareTo(o.holder) > 0){
            return 1;
        }
        if(this.holder.compareTo(o.holder) < 0){
            return -1;
        }
        if (this.balance < o.balance) {
            return -1;
        }
        if (this.balance > o.balance) {
            return 1;
        }
        return 0;
    }
}