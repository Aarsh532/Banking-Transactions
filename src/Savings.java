
public class Savings extends Account{
    protected boolean isLoyal; //loyal customer status
    private static final double INTEREST_RATE = 0.04; // 1.0% annual interest rate
    private static final double LOYAL_INTEREST_RATE = 0.0425;
    private static final double MONTHLY_FEE = 25.0;
    private static final double MIN_BALANCE_FEE_WAIVED = 500;
    private static final int NUM_MONTHS = 12;

    public Savings(Profile holder, double balance) {
        super(holder, balance);

    }

    @Override
    public String toString() {
        String loyalString = isLoyal ? "::is loyal" : "";
        return String.format("%s::%s %s %s::Balance $%,.2f%s",
                getClass().getSimpleName(),
                holder.getFname(),
                holder.getLname(),
                holder.getDob().dateString(),
                balance,
                loyalString);
    }
    public String stringWithFees(){
        String feeStr = String.format("$%.2f", monthlyFee());
        String interestStr = String.format("$%.2f", monthlyInterest());
        String balanceStr = String.format("$%,.2f", balance);
        String loyalty = isLoyal ? "::is loyal" : "";
        return String.format("Savings::%s %s %s::Balance %s%s::fee %s::monthly interest %s",
                holder.getFname(), holder.getLname(), holder.getDob().dateString(),
                balanceStr, loyalty, feeStr, interestStr);
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Savings savings = (Savings) obj;
        return savings.holder.equals(holder) || savings.isLoyal == isLoyal;
    }

    @Override
    public int compareTo(Account o) {
        int typeComparison = this.getClass().getSimpleName()
                .compareTo(o.getClass().getSimpleName());
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
        if(this.balance < o.balance) {
            return -1;
        }
        if (this.balance > o.balance) {
            return 1;
        }

        Savings savings = (Savings) o;

        return Boolean.compare(this.isLoyal, savings.isLoyal);
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
        if(balance >= MIN_BALANCE_FEE_WAIVED){
            return 0.0;
        }
        return MONTHLY_FEE;
    }

    public void setIsLoyal(boolean isLoyal) {
        this.isLoyal = isLoyal;
    }

}
