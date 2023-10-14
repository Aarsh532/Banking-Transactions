/**
 * Checking class extends the Account Class
 * @author Aarsh, Hersh
 */

public class Checking extends Account {
    private static final double INTEREST_RATE = 0.01; // 1.0% annual interest rate
    private static final double MONTHLY_FEE = 12.0;
    private static final double MIN_BALANCE_FEE_WAIVED = 1000;
    private static final int NUM_MONTHS = 12;

    public Checking(Profile holder, double balance) {
        super(holder, balance);
    }

    @Override
    public double monthlyInterest() {
        return balance * (INTEREST_RATE / NUM_MONTHS);
    }

    @Override
    public double monthlyFee() {
        if (balance >= MIN_BALANCE_FEE_WAIVED) {
            return 0.0;
        }
        return MONTHLY_FEE;
    }

    @Override
    public String toString(){
        return String.format("%s::%s %s %s::Balance $%,.2f",
                getClass().getSimpleName(),
                holder.getFname(),
                holder.getLname(),
                holder.getDob().dateString(),
                balance);
    }

    public String stringWithFees(){
        String feeStr = String.format("$%.2f", monthlyFee());
        String interestStr = String.format("$%.2f", monthlyInterest());
        String balanceStr = String.format("$%,.2f", balance);
        return String.format("Checking::%s %s %s::Balance %s::fee %s::monthly interest %s",
                holder.getFname(), holder.getLname(), holder.getDob().dateString(),
                balanceStr, feeStr, interestStr);
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Checking)) return false;
        Checking checking = (Checking) obj;
        return checking.holder.equals(holder);
    }

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