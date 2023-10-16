package project2;

/**
 * Campus class handles the enum for campuses in the college checking class
 * @author Aarsh, Hersh
 */

public enum Campus {
    NEW_BRUNSWICK("0"),
    NEWARK("1"),
    CAMDEN("2");

    private final String code;

    /**
     * Sets the correct campus based on the code
     * @param code as String
     */
    private Campus(String code) {
        this.code = code;
    }

    /**
     * Method to return campus code
     * @return code as String
     */
    public String getCode() {
        return code;
    }

    /**
     * Method to return the correct campus based on campus code
     * @param code as String
     * @return campus as campus
     */
    public static Campus fromCode(String code) {
        for (Campus campus : Campus.values()) {
            if (campus.getCode().equals(code)) {
                return campus;
            }
        }
        return null;

    }

}