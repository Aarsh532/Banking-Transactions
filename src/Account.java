public abstract class Account implements Comparable<Account>{
    public static double getBalance;
    protected Profile holder;
    protected double balance;
    public abstract double monthlyInterest();
    public abstract double monthlyFee();

    public double getBalance(){
        return this.balance;
    }
}
