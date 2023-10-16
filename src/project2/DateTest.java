package project2;

import static org.junit.Assert.*;

/**
 * Date Test class is used to test 5 invalid cases and 2 valid cases of the is valid method from the date class
 * @author Aarsh, Hersh
 */
public class DateTest {

    /**
     * Invalid Test to see if is valid recognizes a month greater than 15
     */
    @org.junit.Test
    public void monthOutOfRange() {
        Date date = new Date(2012, 15, 29);
        assertFalse(date.isValid());
    }

    /**
     * Invalid Test for a non leap year to check if is valid recognizes Febuary doesn't have 29 days in this year
     */
    @org.junit.Test
    public void invalidLeapYear() {
        Date date = new Date(2010, 2, 29);
        assertFalse(date.isValid());

    }

    /**
     * Invalid Test to see if the method recognizes a negative year
     */
    @org.junit.Test
    public void negativeYear(){
        Date date = new Date(-2023,10,1);
        assertFalse(date.isValid());
    }

    /**
     * Invalid Test to see if method recognizes a negative month
     */
    @org.junit.Test
    public void negativeMonth(){
        Date date = new Date(2020, -5, 14);
        assertFalse(date.isValid());
    }

    /**
     * Method to check if method recognizes days greater than 31
     */
    @org.junit.Test
    public void daysOutOfRange(){
        Date date = new Date(2023, 11, 40);
        assertFalse(date.isValid());
    }

    /**
     * Method to check if valid leap year date
     */
    @org.junit.Test
    public void validLeapYear() {
        Date date = new Date(2008, 2, 29);
        assertTrue(date.isValid());
    }

    /**
     * Method to check if 1/1/2023 is valid
     */
    @org.junit.Test
    public void testDayInLongMonth_InRange() {
        Date date = new Date(2023,1,1);
        assertTrue(date.isValid());
    }


}
