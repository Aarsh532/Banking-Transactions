public class CollegeChecking {
    private Campus campus;

    public enum Campus{
        NewBrunswick(0),
        Newark(1),
        Camden(2);

        private final int campusCode;

        Campus(int campusCode){
            this.campusCode = campusCode;
        }

    }
}
