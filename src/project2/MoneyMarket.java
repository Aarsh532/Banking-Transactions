package project2;

/**
 * Money Market Class extends the savings class
 * @author Aarsh, Hersh
 */

public class MoneyMarket extends Savings{
    private int withdrawal; //number of withdrawals
    private static final double INTEREST_RATE = 0.0450;
    private static final double LOYAL_INTEREST_RATE = 0.0475;
    public static final double MIN_BALANCE_FEE_WAIVED = 2000;
    private static final double MONTHLY_FEE = 25.0;
    private static final double WITHDRAWALS_OVER_MIN_FEE = 10.0;
    private static final int MIN_WITHDRAWALS_ALLOWED = 3;
    private static final int NUM_MONTHS = 12;


    /**
     * Creates the money market instance
     * @param profile as Profile object
     * @param balance as double
     * @param isLoyal as boolean
     */
    public MoneyMarket(Profile profile, double balance, boolean isLoyal) {
        super(profile, balance);
        isLoyal = true;
        this.isLoyal = isLoyal;

    }

    /**
     * Converts string to correct format
     * @return String with correct format
     */
    @Override
    public String toString() {
        return String.format("%s::Savings::withdrawal: %d",
                super.toString(),
                withdrawal);
    }

    /**
     * Converts string with fees to corect format
     * @return String with correct format
     */
    public String stringWithFees(){
        String feeString = String.format("$%.2f", monthlyFee());
        String interestString = String.format("$%.2f", monthlyInterest());
        String balanceString = String.format("$%,.2f", balance);
        String loyalty = isLoyal ? "::is loyal" : "";
        return String.format("Money Market::Savings::%s %s %s::Balance %s%s::withdrawal: %d::fee %s::monthly interest %s",
                holder.getFname(), holder.getLname(), holder.getDob().dateString(),
                balanceString, loyalty, withdrawal, feeString, interestString);
    }

    /**
     * Checks if object equals a money market object
     * @param obj as Object
     * @return true if object equals this, returns false otherwise
     */
    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        if(!super.equals(obj)) return false;
        MoneyMarket mmAcct = (MoneyMarket) obj;
        return withdrawal == mmAcct.withdrawal;
    }

    /**
     * Compares accounts
     * @param a as Account
     * @return int
     */
    @Override
    public int compareTo(Account a) {
        int superComparison= super.compareTo(a);
        if(superComparison != 0){
            return superComparison;
        }
        MoneyMarket mmAcct = (MoneyMarket) a;
        if(this.withdrawal < mmAcct.withdrawal){
            return -1;
        }
        if(this.withdrawal > mmAcct.withdrawal){
            return 1;
        }
        return 0;
    }

    /**
     * Method to calculate balance after monthly intrest
     * @return balance as double
     */
    @Override
    public double monthlyInterest() {
        if(isLoyal){
            return balance*(LOYAL_INTEREST_RATE/NUM_MONTHS);
        }
        return balance*(INTEREST_RATE/NUM_MONTHS);
    }

    /**
     * Method to calculate monthly fee
     * @return monthlyFee as double
     */
    @Override
    public double monthlyFee() {
        if(withdrawal > MIN_WITHDRAWALS_ALLOWED){
            if(balance >= MIN_BALANCE_FEE_WAIVED){
                return WITHDRAWALS_OVER_MIN_FEE;
            }
            return MONTHLY_FEE + WITHDRAWALS_OVER_MIN_FEE;
        }
        if(balance >= MIN_BALANCE_FEE_WAIVED){
            return 0.0;
        }

        return MONTHLY_FEE;
    }

    /**
     * Increments withdrawals
     */
    public void incrementWithdrawals(){
        withdrawal++;
    }
}
