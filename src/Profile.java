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
     *
     * @param
     * @return
     */
    public Profile(String firstName, String lastName, Date dateOfBirth) {
        this.fname = firstName;
        this.lname = lastName;
        this.dob = dateOfBirth;
    }

    /**
     *
     * @param
     * @return
     */
    public String getFname(){
        return fname;
    }

    /**
     *
     * @param
     * @return
     */
    public String getLname() {
        return lname;
    }

    /**
     *
     * @param
     * @return
     */
    public Date getDob() {
        return dob;
    }

    /**
     *
     * @param
     * @return
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
     *
     * @param
     * @return
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
