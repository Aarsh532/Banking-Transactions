//@author Hersh and Aarsh
public class Date implements Comparable<Date> {

    private int year;
    private int month;
    private int day;

    public static final int JANUARY = 1;
    public static final int FEBRUARY = 2;
    public static final int MARCH = 3;
    public static final int APRIL = 4;
    public static final int MAY = 5;
    public static final int JUNE = 6;
    public static final int JULY = 7;
    public static final int AUGUST = 8;
    public static final int SEPTEMBER = 9;
    public static final int OCTOBER = 10;
    public static final int NOVEMBER = 11;
    public static final int DECEMBER = 12;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;


    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Date date = (Date) obj;
        return day == date.day && month == date.month && year == date.year;
    }

    public boolean isValid() {
        if (month < JANUARY || month > DECEMBER) {
            return false;
        }

        if (day < 1 || day > daysInMonth(month, year)) {
            return false;
        }

        return true;
    }

    private int daysInMonth(int month, int year) {
        switch (month) {
            case APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER:
                return 30;
            case FEBRUARY:
                return isLeapYear(year) ? 29 : 28;
            default:
                return 31;
        }
    }

    private boolean isLeapYear(int year) {
        if (year % QUADRENNIAL == 0) {
            if (year % CENTENNIAL == 0) {
                return year % QUATERCENTENNIAL == 0;
            }
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Date otherDate) {
        if (this.year != otherDate.year) {
            return this.year - otherDate.year;
        }
        if (this.month != otherDate.month) {
            return this.month - otherDate.month;
        }
        return this.day - otherDate.day;
    }
    public static Date currentDate() {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        int day = calendar.get(java.util.Calendar.DAY_OF_MONTH);
        int month = calendar.get(java.util.Calendar.MONTH) + 1; // Calendar months are 0-based
        int year = calendar.get(java.util.Calendar.YEAR);
        return new Date(day, month, year);
    }

    public Date addMonths(int monthsToAdd) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(year, month - 1, day); // Calendar months are 0-based
        calendar.add(java.util.Calendar.MONTH, monthsToAdd);
        int newDay = calendar.get(java.util.Calendar.DAY_OF_MONTH);
        int newMonth = calendar.get(java.util.Calendar.MONTH) + 1; // Adjusting for 0-based months
        int newYear = calendar.get(java.util.Calendar.YEAR);
        return new Date(newDay, newMonth, newYear);
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }

    public static void main(String[] args) {
        // Testbed main to thoroughly test the isValid() method.

        // Valid non-leap year date
        Date date1 = new Date(28, FEBRUARY, 2021);
        System.out.println(date1.isValid());  // true

        // Invalid leap year date for February
        Date date2 = new Date(30, FEBRUARY, 2020);
        System.out.println(date2.isValid());  // false

        // Valid leap year date for February
        Date date3 = new Date(29, FEBRUARY, 2020);
        System.out.println(date3.isValid());  // true

        // Valid date for April
        Date date4 = new Date(30, APRIL, 2021);
        System.out.println(date4.isValid());  // true

        // Invalid date for April
        Date date5 = new Date(31, APRIL, 2021);
        System.out.println(date5.isValid());  // false

        // Valid date for December
        Date date6 = new Date(31, DECEMBER, 2021);
        System.out.println(date6.isValid());  // true

        // Comparing two dates
        Date date7 = new Date(15, JUNE, 2022);
        Date date8 = new Date(20, JUNE, 2022);
        System.out.println(date7.compareTo(date8));  // -5 (since date7 is earlier than date8)
    }
}
