package project2;

import org.junit.Test;
import project2.AccountDatabase;
import project2.Checking;
import project2.Date;
import project2.Profile;

import static org.junit.Assert.*;

/**
 * project2.AccountDatabaseTest class is used to test 1 invalid case and 1 valid case of the close method
 * @author Aarsh, Hersh
 */
public class AccountDatabaseTest {
    /**
     * Method to test the close method with a valid account
     */
    @Test
    public void testClose_ValidCheckingAccount() {
        AccountDatabase testBase = new AccountDatabase();
        Date testDate = new Date(2000, 5, 12);
        Profile testProfile = new Profile("John", "Doe", testDate);
        Checking testCheckAccount = new Checking(testProfile, 0.0);
        testBase.open(testCheckAccount);
        assertTrue(testBase.close(testCheckAccount));

    }

    /**
     * Method to test the close method with an invalid date
     */
    @Test
    public void testClose_AccountNotInDatabase() {
        AccountDatabase testBase = new AccountDatabase();
        Date testDate = new Date(2000, 40, 14);
        Profile testProfile = new Profile("Max", "Doe", testDate);
        Checking testCheckAccount = new Checking(testProfile, 0.0);
        assertFalse(testBase.close(testCheckAccount));

    }
}