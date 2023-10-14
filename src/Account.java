/**
 * Account class is an abstract class that handles all account information
 * @author Aarsh, Hersh
 */

public abstract class Account implements Comparable<Account> {
    protected Profile holder;
    protected double balance;
    public abstract double monthlyInterest();
    public abstract double monthlyFee();

    public Account(Profile holder, double balance) {
        this.holder = holder;
        this.balance = balance;
    }

    public abstract String stringWithFees();

}