public class ModuleInfo {
    private String moduleName;
    private int grade;
    private int credits;

    public ModuleInfo(String moduleName, int grade, int credits) {
        this.moduleName = moduleName;
        this.grade = grade;
        this.credits = credits;
    }

    public String getModuleName() {
        return moduleName;
    }

    public int getGrade() {
        return grade;
    }

    public int getCredits() {
        return credits;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    // Inner class used for What If? average classification
    class useruniprogress {
        double average;

        public useruniprogress(double average) {
            this.average = average;
        }

        public String classifyAverage() {
            if (average >= 70) {
                return "1st, Excellent";
            } else if (average >= 60) {
                return "2:1, Very Good";
            } else if (average >= 50) {
                return "2:2, Good";
            } else if (average >= 40) {
                return "3rd, Satisfactory";
            } else {
                return "Fail";
            }
        }
    }
}

