package project2;

/**
 * Profile class defines the first name, last name, and date of birth for account holders
 * @author Aarsh, Hersh
 */

public class Profile implements Comparable<Profile>{
    private String fName;
    private String lName;
    private Date DOB;
    private static final int EQUAL_CONDITION = 0;

    /**
     * Method to initialize profile object
     * @param firstName as String
     * @param lastName as String
     * @param dateOfBirth as Date
     */
    public Profile(String firstName, String lastName, Date dateOfBirth) {
        this.fName = firstName;
        this.lName = lastName;
        this.DOB = dateOfBirth;
    }

    /**
     * Method to return first name
     * @return fName as String
     */
    public String getFname(){
        return fName;
    }

    /**
     * Method to return last name
     * @return lanem as String
     */
    public String getLname() {
        return lName;
    }

    /**
     * Method to return DOB
     * @return DOB as Date
     */
    public Date getDob() {
        return DOB;
    }

    /**
     * Method to compare two profile objects
     * @param obj as Object
     * @return true if object equal each other, false otherwise
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Profile){
            Profile o = (Profile) obj;
            return fName.equals(o.fName) && lName.equals(o.lName)
                    && DOB.compareTo(o.DOB) == EQUAL_CONDITION;
        }
        return false;

    }

    /**
     * Method to compare two profiles
     * @param o as Profile
     * @return int
     */
    @Override
    public int compareTo(Profile o) {
        if(this.lName.compareTo(o.lName) > 0){
            return 1;
        }
        if(this.fName.compareTo(o.fName) < 0){
            return -1;
        }
        if (this.DOB.compareTo(o.DOB) > 0) {
            return 1;
        }

        return 0;
    }
}
