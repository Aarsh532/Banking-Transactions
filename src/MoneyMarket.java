

public class MoneyMarket extends Savings{
    private int withdrawal; //number of withdrawals
    private static final double INTEREST_RATE = 0.0450;
    private static final double LOYAL_INTEREST_RATE = 0.0475;
    public static final double MIN_BALANCE_FEE_WAIVED = 2000;
    private static final double MONTHLY_FEE = 25.0;
    private static final double WITHDRAWALS_OVER_MIN_FEE = 10.0;
    private static final int MIN_WITHDRAWALS_ALLOWED = 3;
    private static final int NUM_MONTHS = 12;



    public MoneyMarket(Profile holder, double balance, boolean isLoyal) {
        super(holder, balance);
        isLoyal = true;
        this.isLoyal = isLoyal;

    }
    @Override
    public String toString() {
        return String.format("%s::Savings::withdrawal: %d",
                super.toString(),
                withdrawal);
    }

    public String stringWithFees(){
        String feeStr = String.format("$%.2f", monthlyFee());
        String interestStr = String.format("$%.2f", monthlyInterest());
        String balanceStr = String.format("$%,.2f", balance);
        String loyalty = isLoyal ? "::is loyal" : "";
        return String.format("Money Market::Savings::%s %s %s::Balance %s%s::withdrawal: %d::fee %s::monthly interest %s",
                holder.getFname(), holder.getLname(), holder.getDob().dateString(),
                balanceStr, loyalty, withdrawal, feeStr, interestStr);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        if(!super.equals(obj)) return false;
        MoneyMarket mmAccount = (MoneyMarket) obj;
        return withdrawal == mmAccount.withdrawal;
    }

    @Override
    public int compareTo(Account o) {
        int superComparison= super.compareTo(o);
        if(superComparison != 0){
            return superComparison;
        }
        MoneyMarket mmAccount = (MoneyMarket) o;
        if(this.withdrawal < mmAccount.withdrawal){
            return -1;
        }
        if(this.withdrawal > mmAccount.withdrawal){
            return 1;
        }
        return 0;
    }


    @Override
    public double monthlyInterest() {
        if(isLoyal){
            return balance*(LOYAL_INTEREST_RATE/NUM_MONTHS);
        }
        return balance*(INTEREST_RATE/NUM_MONTHS);
    }

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

    public void incrementWithdrawals(){
        withdrawal++;
    }
}
