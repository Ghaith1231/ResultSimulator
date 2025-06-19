public class ModuleRecord {
    private String name;
    private int grade;
    private int credits;
    private String level;

    public ModuleRecord(String name, int grade, int credits) {
        this.name = name;
        this.grade = grade;
        this.credits = credits;
        this.level = level;
    }

    public String fetchName() {
        return name;
    }

    public int fetchGrade() {
        return grade;
    }

    public int fetchCredits() {
        return credits;
    }

    public String fetchLevel() {
        return level;
    }
}
