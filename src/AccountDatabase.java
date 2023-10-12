

public class AccountDatabase {
    private static final int INCREMENT_AMOUNT = 4;
    private static final int INITIAL_CAPACITY = 4;
    private Account[] accounts; //list of various types of accounts
    private int numAcct; //number of accounts in the array
    private static final int NOT_FOUND = -1;
    private static final int STARTING_NUM_ACCT = 0;
    private static final double MIN_BALANCE_FEE_WAIVED = 2000;

    public AccountDatabase(){
        accounts = new Account[INITIAL_CAPACITY];
        numAcct = STARTING_NUM_ACCT;
    }
    private int find(Account account) {
        int index = NOT_FOUND;
        for (int i = 0; i < numAcct; i++) {
            if (accounts[i].equals(account)) {
                index = i;
                break;
            }
        }
        return index;
    }
    private void grow(){
        Account[] copy = new Account[numAcct + INCREMENT_AMOUNT];
        for(int i = 0; i < numAcct; i++){
            copy[i] = accounts[i];
        }
        accounts = copy;

    } //increase the capacity by 4
    public boolean contains(Account account){
        return find(account) != NOT_FOUND;
    }

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
    public boolean close(Account account){
        int removeIndex = find(account);
        if (removeIndex != NOT_FOUND) {
            for (int i = removeIndex; i < numAcct - 1; i++) {
                accounts[i] = accounts[i + 1];
            }
            numAcct--;
            accounts[numAcct] = null;
            return true;
        }
        return false;
    }

    public boolean withdraw(Account account){
        int index = find(account);
        if (index == NOT_FOUND) {
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

    public void deposit(Account account){
        int index = find(account);
        if (index == NOT_FOUND) {
            return;
        }
        accounts[index].balance += account.balance;
    }
    public void printSorted(){
        selectionSortAccountType();
        for (int i = 0; i < numAcct; i++) {
            System.out.println(accounts[i].toString());
        }
    }
    public void printFeesAndInterests(){ //calculate interests/fees
        selectionSortAccountType();
        for(int i = 0; i< numAcct; i++){
            System.out.println(accounts[i].stringWithFees());
        }
    }
    public void printUpdatedBalances(){ //apply the interests/fees
        selectionSortAccountType();
        for(int i = 0; i < numAcct; i++){
            accounts[i].balance += accounts[i].monthlyInterest();
            accounts[i].balance -= accounts[i].monthlyFee();
        }
    }

    public boolean isEmpty(){
        return numAcct == 0;
    }
}