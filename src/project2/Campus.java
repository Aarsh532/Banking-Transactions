package project2;

/**
 * Campus class handles the enum for campuses in the college checking class
 * @author Aarsh, Hersh
 */

public enum Campus {
    NEW_BRUNSWICK("0"),
    NEWARK("1"),
    CAMDEN("2");

    private final String campusCode;

    /**
     * Sets the correct campus based on the code
     * @param campusCode as String
     */
    private Campus(String campusCode) {
        this.campusCode = campusCode;
    }

    /**
     * Method to return campus code
     * @return campusCode as String
     */
    public String getCode() {
        return campusCode;
    }

    /**
     * Method to return the correct campus based on campus code
     * @param campusCode as String
     * @return campus as campus
     */
    public static Campus fromCode(String campusCode) {
        for (Campus campus : Campus.values()) {
            if (campus.getCode().equals(campusCode)) {
                return campus;
            }
        }
        return null;

    }

}