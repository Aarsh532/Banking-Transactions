/**
 * Profile class defines the first name, last name, and date of birth for account holders
 * @author Aarsh, Hersh
 */

public class Profile implements Comparable<Profile>{
    private String fname;
    private String lname;
    private Date dob;
    private static final int EQUAL_CONDITION = 0;

    /**
     * Method to initialize profile object
     * @param firstName as String
     * @param lastName as String
     * @param dateOfBirth as Date
     */
    public Profile(String firstName, String lastName, Date dateOfBirth) {
        this.fname = firstName;
        this.lname = lastName;
        this.dob = dateOfBirth;
    }

    /**
     * Method to return first name
     * @return fname as String
     */
    public String getFname(){
        return fname;
    }

    /**
     * Method to return last name
     * @return lanem as String
     */
    public String getLname() {
        return lname;
    }

    /**
     * Method to return DOB
     * @return dob as Date
     */
    public Date getDob() {
        return dob;
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
            return fname.equals(o.fname) && lname.equals(o.lname)
                    && dob.compareTo(o.dob) == EQUAL_CONDITION;
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
        if(this.lname.compareTo(o.lname) > 0){
            return 1;
        }
        if(this.fname.compareTo(o.fname) < 0){
            return -1;
        }
        if (this.dob.compareTo(o.dob) > 0) {
            return 1;
        }

        return 0;
    }
}
