package project2;

/**
 * Account Database handles the account array and all methods for each of the commands
 * @author Aarsh, Hersh
 */

public class AccountDatabase {
    private static final int INCREMENT_AMOUNT = 4;
    private static final int INITIAL_CAPACITY = 4;
    private Account[] accounts; //list of various types of accounts
    private int numAcct; //number of accounts in the array
    private static final double MIN_BALANCE_FEE_WAIVED = 2000;

    /**
     * Method to create Account Database object
     */
    public AccountDatabase(){
        accounts = new Account[INITIAL_CAPACITY];
        numAcct = 0;
    }

    /**
     * Method to find an account in a database
     * @param account as Account
     * @return index as int
     */
    private int find(Account account) {
        int index = -1;
        for (int i = 0; i < numAcct; i++) {
            if (accounts[i].equals(account)) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Method to grow account database
     */
    private void grow(){
        Account[] copy = new Account[numAcct + INCREMENT_AMOUNT];
        for(int i = 0; i < numAcct; i++){
            copy[i] = accounts[i];
        }
        accounts = copy;

    }

    /**
     * Parent method to find method
     * @param account as Account
     * @return true if account is found, false otherwise
     */
    public boolean contains(Account account){
        return find(account) != -1;
    }

    /**
     * Method to check if account is found in database if not create account
     * @param account as Account
     * @return true if account not found, false otherwise
     */
    public boolean open(Account account){
        if(contains(account)){
            return false;
        }
        if (numAcct >= accounts.length) {
            grow();
        }
        accounts[numAcct] = account;
        numAcct++;
        return true;
    }

    /**
     * Method to check if account is found in database to close
     * @param account as Account
     * @return true if account is found to close, false otherwise
     */
    public boolean close(Account account){
        int removeIndex = find(account);
        if (removeIndex != -1) {
            for (int i = removeIndex; i < numAcct - 1; i++) {
                accounts[i] = accounts[i + 1];
            }
            numAcct--;
            accounts[numAcct] = null;
            return true;
        }
        return false;
    }

    /**
     * Method to check if account is found in database to withdraw
     * @param account as Account
     * @return true if account is found, false otherwise
     */
    public boolean withdraw(Account account){
        int index = find(account);
        if (index == -1) {
            return false;
        }
        Account acct = accounts[index];
        if ((acct.balance - account.balance) < 0) {
            return false;
        }
        accounts[index].balance -= account.balance;
        if (acct instanceof MoneyMarket) {
            MoneyMarket mmAccount = (MoneyMarket) acct;
            mmAccount.incrementWithdrawals();
            if (mmAccount.balance < MIN_BALANCE_FEE_WAIVED) {
                mmAccount.isLoyal = false;
            }
            accounts[index] = mmAccount;
        }
        return true;
    }

    /**
     * Method to sort account by account type
     */
    private void selectionSortAccountType() {
        int n = numAcct;

        for (int i = 0; i < n-1; i++) {
            int minIdx = i;
            for (int j = i+1; j < n; j++) {
                if (accounts[j].compareTo(accounts[minIdx]) < 0) {
                    minIdx = j;
                }
            }

            Account temp = accounts[minIdx];
            accounts[minIdx] = accounts[i];
            accounts[i] = temp;
        }
    }

    /**
     * Method to check if account is found in database, then deposit money into account
     * @param account as Account
     */
    public void deposit(Account account){
        int index = find(account);
        if (index == -1) {
            return;
        }
        accounts[index].balance += account.balance;
    }

    /**
     * Parent method to selectionSortAccountType
     */
    public void printSorted(){
        selectionSortAccountType();
        for (int i = 0; i < numAcct; i++) {
            System.out.println(accounts[i].toString());
        }
    }

    /**
     * Parent method to selectionSortAccountType, with additional fees and interests
     */
    public void printFeesAndInterests(){ //calculate interests/fees
        selectionSortAccountType();
        for(int i = 0; i< numAcct; i++){
            System.out.println(accounts[i].stringWithFees());
        }
    }

    /**
     * Method to print accounts after updating the balance values with fees and interests
     */
    public void printUpdatedBalances(){ //apply the interests/fees
        selectionSortAccountType();
        for(int i = 0; i < numAcct; i++){
            accounts[i].balance += accounts[i].monthlyInterest();
            accounts[i].balance -= accounts[i].monthlyFee();
        }
    }

    /**
     * Method to check if the number of accounts is 0
     * @return true if empty, false otherwise
     */
    public boolean isEmpty(){
        return numAcct == 0;
    }
}