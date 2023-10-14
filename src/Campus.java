/**
 * Campus class handles the enum for campuses in the college checking class
 * @author Aarsh, Hersh
 */

public enum Campus {
    NEW_BRUNSWICK("0"),
    NEWARK("1"),
    CAMDEN("2");

    private final String code;

    private Campus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Campus fromCode(String code) {
        for (Campus campus : Campus.values()) {
            if (campus.getCode().equals(code)) {
                return campus;
            }
        }
        return null;

    }

}