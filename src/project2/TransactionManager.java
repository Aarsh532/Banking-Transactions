package project2;

/**
 * Transaction Manager handles basic tokenizing of command line and methods for each command sequence
 * @author Aarsh, Hersh
 */


import java.util.Calendar;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TransactionManager {
    private boolean isRunning;
    private AccountDatabase accountDatabase;

    private Account[] closedAccounts;

    /**
     * Method to initialize Transaction Manager Object
     */
    public TransactionManager() {
        this.accountDatabase = new AccountDatabase();
        this.closedAccounts = new Account[4];
        isRunning = true;
        System.out.println("Transaction Manager is running.");
    }

    /**
     * Main method that runs project and handles tokens
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (isRunning) {
            String command = scanner.nextLine();
            if (command.isEmpty()) {
                continue;
            }
            StringTokenizer tokenizer = new StringTokenizer(command);
            String firstToken = tokenizer.nextToken();
            switch (firstToken) {
                case "Q" -> isRunning = false;
                case "O" -> handleOCommand(tokenizer);
                case "C" -> handleCommand(tokenizer);
                case "D" -> handleDCommand(tokenizer);
                case "W" -> handleWCommand(tokenizer);
                case "P" -> handlePCommand(tokenizer);
                case "PI" -> handlePICommand(tokenizer);
                case "UB" -> handleUBCommand(tokenizer);
                default -> System.out.println("Invalid command!");
            }
        }

        System.out.println("Transaction Manager is terminated.");
        scanner.close();
    }

    /**
     * Method to handle command for opening an account
     * @param tokenizer as StringTokenizer
     */
    private void handleOCommand(StringTokenizer tokenizer) {
        if (tokenizer.countTokens() < 5) {
            System.out.println("Missing data for opening an account.");
            return;
        }
        String accountType = tokenizer.nextToken();
        String fName = tokenizer.nextToken();
        String lName = tokenizer.nextToken();
        Date DOB = parseDate(tokenizer.nextToken());
        if (!isValidDOB(DOB, accountType)) {
            return;
        }

        String initialDepositString = tokenizer.nextToken();
        double initialDeposit;
        if (isValidInitialDeposit(initialDepositString)) {
            initialDeposit = Double.parseDouble(initialDepositString);
        } else return;

        switch (accountType) {
            case "C" -> openChecking(fName, lName, DOB, initialDeposit);
            case "CC" -> openCollegeChecking(fName, lName, DOB,
                    initialDeposit, tokenizer);
            case "S" -> openSavings(fName, lName, DOB,
                    initialDeposit, tokenizer);
            case "MM" -> openMoneyMarket(fName, lName, DOB, initialDeposit);
        }
    }

    /**
     * Method to handle command for closing an account
     * @param tokenizer as StringTokenizer
     */
    private void handleCommand(StringTokenizer tokenizer) {
        if (tokenizer.countTokens() < 4) {
            System.out.println("Missing data for closing an account.");
            return;
        }
        String accountType = tokenizer.nextToken();
        String fName = tokenizer.nextToken();
        String lName = tokenizer.nextToken();
        Date DOB = parseDate(tokenizer.nextToken());

        if (futureDateCheck(DOB)) {
            System.out.println("DOB invalid: " + DOB.dateString()
                    + " cannot be today or a future day.");
            return;
        }

        switch (accountType) {
            case "C" -> closeChecking(fName, lName, DOB);
            case "CC" -> closeCollegeChecking(fName, lName, DOB);
            case "S" -> closeSavings(fName, lName, DOB);
            case "MM" -> closeMoneyMarket(fName, lName, DOB);
        }
    }

    /**
     * Method to handle command for deposits
     * @param tokenizer as StringTokenizer
     */
    private void handleDCommand(StringTokenizer tokenizer) {
        if (tokenizer.countTokens() < 5) {
            System.out.println("Missing data for opening an account.");
            return;
        }
        String accountType = tokenizer.nextToken();
        String fName = tokenizer.nextToken();
        String lName = tokenizer.nextToken();
        Date DOB = parseDate(tokenizer.nextToken());

        String depositString = tokenizer.nextToken();
        double deposit;
        if (isValidAmount(depositString, "Deposit")) {
            deposit = Double.parseDouble(depositString);
        } else return;

        switch (accountType) {
            case "C" -> depositChecking(fName, lName, DOB, deposit);
            case "CC" -> depositCollegeChecking(fName, lName, DOB, deposit);
            case "S" -> depositSavings(fName, lName, DOB, deposit);
            case "MM" -> depositMoneyMarket(fName, lName, DOB, deposit);
        }
    }

    /**
     * Method to handle command for withdrawal
     * @param tokenizer as StringTokenizer
     */
    private void handleWCommand(StringTokenizer tokenizer) {
        if (tokenizer.countTokens() < 5) {
            System.out.println("Missing data for opening an account.");
            return;
        }
        String accountType = tokenizer.nextToken();
        String fName = tokenizer.nextToken();
        String lName = tokenizer.nextToken();
        Date DOB = parseDate(tokenizer.nextToken());

        String withdrawString = tokenizer.nextToken();
        double withdraw;
        if (isValidAmount(withdrawString, "Withdraw")) {
            withdraw = Double.parseDouble(withdrawString);
        } else return;

        switch (accountType) {
            case "C" -> withdrawChecking(fName, lName, DOB, withdraw);
            case "CC" -> withdrawCollegeChecking(fName, lName, DOB, withdraw);
            case "S" -> withdrawSavings(fName, lName, DOB, withdraw);
            case "MM" -> withdrawMoneyMarket(fName, lName, DOB, withdraw);
        }

    }

    /**
     * Method to handle display accounts command
     * @param tokenizer as StringTokenizer
     */
    private void handlePCommand(StringTokenizer tokenizer) {
        if (!(accountDatabase.isEmpty())) {
            System.out.println();
            System.out.println("Accounts sorted by account type and profile.");
            accountDatabase.printSorted();
            System.out.println("*end of list.");
            System.out.println();
        } else System.out.println("Account Database is empty!");

    }

    /**
     * Method to handle display accounts and fees command
     * @param tokenizer as StringTokenizer
     * @return List of all accounts with fees addition
     */
    private void handlePICommand(StringTokenizer tokenizer) {
        if (!(accountDatabase.isEmpty())) {
            System.out.println();
            System.out.println("*list of accounts with fee and monthly interest");
            accountDatabase.printFeesAndInterests();
            System.out.println("*end of list.");
            System.out.println();
        } else System.out.println("Account Database is empty!");
    }

    /**
     * Method to handle update account command
     * @param tokenizer as StringTokenizer
     * @return
     */
    private void handleUBCommand(StringTokenizer tokenizer) {
        if (!(accountDatabase.isEmpty())) {
            System.out.println();
            System.out.print("*list of accounts with fees and interests applied.");
            accountDatabase.printUpdatedBalances();
            System.out.print("*end of list.");
            System.out.println();
        } else System.out.println("Account Database is empty!");
    }

    /**
     * Method to handle parsing of date field in command line
     * @param dateOfBirth as String
     * @return Date as new object with given year, month, and date
     */
    private Date parseDate(String dateOfBirth) {
        String[] dateComponents = dateOfBirth.split("/");
        if (dateComponents.length == 3) {
            int year = Integer.parseInt(dateComponents[2]);
            int month = Integer.parseInt(dateComponents[0]);
            int day = Integer.parseInt(dateComponents[1]);
            return new Date(year, month, day);
        }
        return null;

    }

    /**
     * Method to check if given date is set in the future
     * @param date as Date
     * @return true if date is in the future, false otherwise
     */
    private boolean futureDateCheck(Date date) {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        currentMonth++;
        int currentDay = calendar.get(Calendar.DATE);

        if (date.getYear() > currentYear) {
            return true;
        } else if (date.getYear() == currentYear) {
            if (date.getMonth() > currentMonth) {
                return true;
            } else if (date.getMonth() == currentMonth) {
                return date.getDay() >= currentDay;
            }
        }
        return false;
    }

    /**
     * Method to check if the given DOB is valid for each account
     * @param date as Date
     * @param accountType as String
     * @return true if age is valid, false otherwise
     */
    private boolean ageCheck(Date date, String accountType){
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        currentMonth++;
        int currentDay = calendar.get(Calendar.DATE);
        Date currentDate = new Date(currentYear, currentMonth, currentDay);
        int age = calculateAge(currentDate, date);
        if (age < 16) {
            System.out.println("DOB invalid: " + date.dateString()
                    + " under 16.");
            return false;
        }
        if ("CC".equals(accountType) && age >= 24) {
            System.out.println("DOB invalid: " + date.dateString()
                    + " over 24.");
            return false;
        }
        return true;
    }

    /**
     * Method to calculate age based off DOB
     * @param currentDate as Date
     * @param ageDate as Date
     * @return int
     */
    private int calculateAge(Date currentDate, Date ageDate) {
        int age = currentDate.getYear() - ageDate.getYear();

        if (currentDate.getMonth() < ageDate.getMonth() ||
                (currentDate.getMonth() == ageDate.getMonth()
                        && currentDate.getDay() < ageDate.getDay())) {
            age--;
        }
        return age;
    }

    /**
     * Method to check if given DOB is valid
     * @param DOB as Date
     * @param accountType as String
     * @return true if valid DOB, false otherwise
     */
    private boolean isValidDOB(Date DOB, String accountType) {
        if (!(DOB.isValid())) {
            System.out.println("DOB invalid: " + DOB.dateString()
                    + " not a valid calendar date!");
            return false;
        }
        if (futureDateCheck(DOB)) {
            System.out.println("DOB invalid: " + DOB.dateString()
                    + " cannot be today or a future day.");
            return false;
        }
        if(!(ageCheck(DOB, accountType))){
            return false;
        }
        return true;
    }

    /**
     * Method to check if deposit is a valid amount
     * @param initialDepositString as String
     * @return True if a deposit is a valid amount, false otherwise
     */
    public static boolean isValidInitialDeposit(String initialDepositString) {
        double initialDeposit;
        try {
            initialDeposit = Double.parseDouble(initialDepositString);
        } catch (NumberFormatException e) {
            System.out.println("Not a valid amount.");
            return false;
        }
        if (initialDeposit <= 0) {
            System.out.println("Initial deposit cannot be 0 or negative.");
            return false;
        }
        return true;
    }

    /**
     * Method to check if amount is negative or 0
     * @param amountString as String
     * @param operationType as String
     * @return true if valid amount, false otherwise
     */
    public static boolean isValidAmount(String amountString, String operationType) {
        double amount;
        try {
            amount = Double.parseDouble(amountString);
        } catch (NumberFormatException e) {
            System.out.println("Not a valid amount.");
            return false;
        }

        if (amount <= 0) {
            System.out.println(operationType + " - amount cannot be 0 or negative.");
            return false;
        }
        return true;
    }

    /**
     * Method to check if given campus code is valid
     * @param campusCode as String
     * @return true if valid campus code, false otherwise
     */
    public static boolean isValidCampus(String campusCode) {
        try {
            Campus.valueOf(campusCode);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid campus code.");
            return false;
        }
    }

    /**
     * Method to handle opening of a checking account
     * @param fName as String
     * @param lName as String
     * @param DOB as Date
     * @return terminal message indicating a checking account is open, error statement otherwise
     */
    public void openChecking(String fName, String lName, Date DOB,
                             double initialDeposit) {
        Profile newProfile = new Profile(fName, lName, DOB);
        Checking newChecking = new Checking(newProfile, initialDeposit);
        // check if CC or C in database
        if (accountDatabase.open(newChecking)) {
            System.out.println(fName + " " + lName + " " +
                    DOB.dateString() + "(C) opened.");
        } else {
            System.out.println(fName + " " + lName + " " +
                    DOB.dateString() + "(C) is already in the database.");
        }
    }

    /**
     * Method to handle opening of a college checking account
     * @param fName as String
     * @param lName as String
     * @param DOB as Date
     * @return terminal message indicating a college checking account is open, error statement otherwise
     */
    public void openCollegeChecking(String fName, String lName, Date DOB,
                                    double initialDeposit, StringTokenizer tokenizer) {
        if (!tokenizer.hasMoreTokens()) {
            System.out.println("Missing data for opening an account.");
            return;
        }
        Profile newProfile = new Profile(fName, lName, DOB);
        String campusCode = tokenizer.nextToken();
        Campus campus = Campus.fromCode(campusCode);
        if (campus == null) {
            System.out.println("Invalid campus code.");
            return;
        }
        CollegeChecking newCollegeChecking = new CollegeChecking(newProfile, initialDeposit, campus);

        if (accountDatabase.open(newCollegeChecking)) {
            System.out.println(fName + " " + lName + " " +
                    DOB.dateString() + "(CC) opened.");
        } else {
            System.out.println(fName + " " + lName + " " +
                    DOB.dateString() + "(CC) is already in the database.");
        }
    }

    /**
     * Method to open a savings account
     * @param fName as String
     * @param lName as String
     * @param DOB as Date
     * @return terminal message indicating a savings account is open, error statement otherwise
     */
    public void openSavings(String fName, String lName, Date DOB,
                            double initialDeposit, StringTokenizer tokenizer) {
        if (!tokenizer.hasMoreTokens()) {
            System.out.println("Missing data for opening an account.");
            return;
        }
        Profile newProfile = new Profile(fName, lName, DOB);
        Savings newSavings = new Savings(newProfile, initialDeposit);

        String loyalty = tokenizer.nextToken();
        newSavings.setIsLoyal(loyalty.equals(1));

        if (accountDatabase.open(newSavings)) {
            System.out.println(fName + " " + lName + " " +
                    DOB.dateString() + "(S) opened.");
        } else {
            System.out.println(fName + " " + lName + " " +
                    DOB.dateString() + "(S) is already in the database.");
        }
    }

    /**
     * Method to open a money market account
     * @param fName as String
     * @param lName as String
     * @param DOB as Date
     * @return terminal message indicating a money market account is open, error statement otherwise
     */
    public void openMoneyMarket(String fName, String lName, Date DOB,
                                double initialDeposit) {
        if (initialDeposit < MoneyMarket.MIN_BALANCE_FEE_WAIVED) {
            System.out.println("Minimum of $2000 to open a Money Market account.");
            return;
        }
        Profile newProfile = new Profile(fName, lName, DOB);
        MoneyMarket newMoneyMarket = new MoneyMarket(newProfile, initialDeposit, true);
        if (accountDatabase.open(newMoneyMarket)) {
            System.out.println(fName + " " + lName + " " +
                    DOB.dateString() + "(MM) opened.");
        } else {
            System.out.println(fName + " " + lName + " " +
                    DOB.dateString() + "(MM) is already in the database.");
        }
    }

    /**
     * Method to close a checking account
     * @param fName as String
     * @param lName as String
     * @param DOB as Date
     */
    public void closeChecking(String fName, String lName, Date DOB) {
        Profile profileToClose = new Profile(fName, lName, DOB);
        Checking accountToClose = new Checking(profileToClose, 0);
        closeAccount(fName, lName, DOB, accountToClose, "C");
    }

    /**
     * Method to close a college checking account
     * @param fName as String
     * @param lName as String
     * @param DOB as Date
     */
    public void closeCollegeChecking(String fName, String lName, Date DOB) {
        Profile profileToClose = new Profile(fName, lName, DOB);
        CollegeChecking accountToClose = new CollegeChecking(profileToClose, 0, null);
        closeAccount(fName, lName, DOB, accountToClose, "CC");
    }

    /**
     * Method to close a savings account
     * @param fName as String
     * @param lName as String
     * @param DOB as Date
     */
    public void closeSavings(String fName, String lName, Date DOB) {
        Profile profileToClose = new Profile(fName, lName, DOB);
        Savings accountToClose = new Savings(profileToClose, 0);
        closeAccount(fName, lName, DOB, accountToClose, "S");
    }

    /**
     * Method to close money market account
     * @param fName as String
     * @param lName as String
     * @param DOB as Date
     */
    public void closeMoneyMarket(String fName, String lName, Date DOB) {
        Profile profileToClose = new Profile(fName, lName, DOB);
        MoneyMarket accountToClose = new MoneyMarket(profileToClose, 0, true);
        closeAccount(fName, lName, DOB, accountToClose, "MM");
    }

    /**
     * Method to close account
     * @param fName as String
     * @param lName as String
     * @param DOB as Date
     */
    public void closeAccount(String fName, String lName, Date DOB,
                             Account account, String accountType) {
        if (accountDatabase.close(account)) {
            System.out.println(fName + " " + lName + " " + DOB.dateString()
                    + "(" + accountType + ") has been closed.");
        } else {
            System.out.println(fName + " " + lName + " " + DOB.dateString()
                    + "(" + accountType + ") is not in the database.");
        }
    }

    /**
     * Method to deposit money into a checking account
     * @param fName as String
     * @param lName as String
     * @param DOB as Date
     * @param deposit as double
     */
    public void depositChecking(String fName, String lName, Date DOB, double deposit) {
        Profile profileToDeposit = new Profile(fName, lName, DOB);
        Checking accountToDeposit = new Checking(profileToDeposit, deposit);
        depositAccount(fName, lName, DOB, accountToDeposit, "C");
    }

    /**
     * Method to deposit money into a college checking account
     * @param fName as String
     * @param lName as String
     * @param DOB as Date
     * @param deposit as double
     */
    public void depositCollegeChecking(String fName, String lName, Date DOB, double deposit) {
        Profile profileToDeposit = new Profile(fName, lName, DOB);
        CollegeChecking accountToDeposit = new CollegeChecking(profileToDeposit, deposit, null);
        depositAccount(fName, lName, DOB, accountToDeposit, "CC");
    }

    /**
     * Method to deposit money into a savings account
     * @param fName as String
     * @param lName as String
     * @param DOB as Date
     * @param deposit as double
     */
    public void depositSavings(String fName, String lName, Date DOB, double deposit) {
        Profile profileToDeposit = new Profile(fName, lName, DOB);
        Savings accountToDeposit = new Savings(profileToDeposit, deposit);
        depositAccount(fName, lName, DOB, accountToDeposit, "S");
    }

    /**
     * Method to deposit money into a money market account
     * @param fName as String
     * @param lName as String
     * @param DOB as Date
     * @param deposit as double
     */
    public void depositMoneyMarket(String fName, String lName, Date DOB, double deposit) {
        Profile profileToDeposit = new Profile(fName, lName, DOB);
        MoneyMarket accountToDeposit = new MoneyMarket(profileToDeposit, deposit, true);
        depositAccount(fName, lName, DOB, accountToDeposit, "M");
    }

    /**
     * Method to deposit money into an account
     * @param fName as String
     * @param lName as String
     * @param DOB as Date
     */
    public void depositAccount(String fName, String lName, Date DOB,
                               Account account, String accountType) {
        accountDatabase.deposit(account);
        System.out.println(fName + " " + lName + " " + DOB.dateString() + "("
                + accountType + ") Deposit - balance updated.");
    }

    /**
     * Method to withdraw money from a checking account
     * @param fName as String
     * @param lName as String
     * @param DOB as Date
     * @param withdraw as double
     */
    public void withdrawChecking(String fName, String lName, Date DOB, double withdraw) {
        Profile profileToWithdraw = new Profile(fName, lName, DOB);
        Checking accountToWithdraw = new Checking(profileToWithdraw, withdraw);
        withdrawAccount(fName, lName, DOB, accountToWithdraw, withdraw, "C");
    }

    /**
     * Method to withdraw money from a college checking account
     * @param fName as String
     * @param lName as String
     * @param DOB as Date
     * @param withdraw as double
     */
    public void withdrawCollegeChecking(String fName, String lName, Date DOB, double withdraw) {
        Profile profileToWithdraw = new Profile(fName, lName, DOB);
        CollegeChecking accountToWithdraw = new CollegeChecking(profileToWithdraw, withdraw, null);
        withdrawAccount(fName, lName, DOB, accountToWithdraw, withdraw, "CC");
    }

    /**
     * Method to withdraw money from a savings account
     * @param fName as String
     * @param lName as String
     * @param DOB as Date
     * @param withdraw as double
     */
    public void withdrawSavings(String fName, String lName, Date DOB, double withdraw) {
        Profile profileToWithdraw = new Profile(fName, lName, DOB);
        Savings accountToWithdraw = new Savings(profileToWithdraw, withdraw);
        withdrawAccount(fName, lName, DOB, accountToWithdraw, withdraw, "S");
    }

    /**
     * Method to withdraw money from a money market account
     * @param fName as String
     * @param lName as String
     * @param DOB as Date
     * @param withdraw as double
     */
    public void withdrawMoneyMarket(String fName, String lName, Date DOB, double withdraw) {
        Profile profileToWithdraw = new Profile(fName, lName, DOB);
        MoneyMarket accountToWithdraw = new MoneyMarket(profileToWithdraw, withdraw, true);
        withdrawAccount(fName, lName, DOB, accountToWithdraw, withdraw, "MM");
    }

    /**
     * Method to withdraw money from an account
     * @param fName as String
     * @param lName as String
     * @param DOB as Date
     */
    public void withdrawAccount(String fName, String lName, Date DOB,
                                Account account, double withdraw, String accountType) {
        if (!accountDatabase.withdraw(account)) {
            if (withdraw > account.balance) {
                System.out.println(fName + " " + lName + " " + DOB.dateString()
                        + "(" + accountType + ") " + "Withdraw - " +
                        "insufficient fund.");
            }
            else {
                System.out.println(fName + " " + lName + " " + DOB.dateString()
                        + "(" + accountType + ") is not in the database.");
            }
            return;
        }
        System.out.println(fName + " " + lName + " " + DOB.dateString() + "("
                + accountType + ") Withdraw - balance updated.");
    }
}