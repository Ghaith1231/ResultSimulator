import java.util.ArrayList;

public class useruniprogress {
    private double average;
    private ArrayList<ModuleRecord> moduleList;

    public useruniprogress() {
        this.average = 0.0;
        this.moduleList = new ArrayList<>();
    }

    public useruniprogress(double average) {
        this.average = average;
        this.moduleList = new ArrayList<>();
    }

    public void addModule(ModuleRecord module) {
        moduleList.add(module);
    }

    public String classifyAverage() {
        if (average >= 70) {
            return "First";
        } else if (average >= 60) {
            return "Upper Second (2:1)";
        } else if (average >= 50) {
            return "Lower Second (2:2)";
        } else if (average >= 40) {
            return "Third";
        } else {
            return "Fail";
        }
    }

    public String fetchGradeBreakdown() {
        int l4Grade = 0;
        int l5Grade = 0;
        int l6Grade = 0;

        for (ModuleRecord module : moduleList) {
            String lvl = module.fetchLevel();
            int gr = module.fetchGrade();

            if (lvl.equalsIgnoreCase("L4")) {
                l4Grade += gr;
            } else if (lvl.equalsIgnoreCase("L5")) {
                l5Grade += gr;
            } else if (lvl.equalsIgnoreCase("L6")) {
                l6Grade += gr;
            }
        }

        return "Level 4 Total Marks: " + l4Grade + "\n" +
               "Level 5 Total Marks: " + l5Grade + "\n" +
               "Level 6 Total Marks: " + l6Grade;
    }

    public String fetchFinalGradeBreakdown() {
        int gradeTotalL5 = 0;
        int creditL5 = 0;
        int gradeTotalL6 = 0;
        int creditL6 = 0;

        for (ModuleRecord mod : moduleList) {
            String lvl = mod.fetchLevel();
            int mark = mod.fetchGrade();
            int c = mod.fetchCredits();

            if (lvl.equalsIgnoreCase("L5")) {
                gradeTotalL5 += mark * c;
                creditL5 += c;
            } else if (lvl.equalsIgnoreCase("L6")) {
                gradeTotalL6 += mark * c;
                creditL6 += c;
            }
        }

        double l5Avg = 0;
        double l6Avg = 0;
        if (creditL5 > 0) l5Avg = (double) gradeTotalL5 / creditL5;
        if (creditL6 > 0) l6Avg = (double) gradeTotalL6 / creditL6;

        double fAvg;
        if (creditL5 == 0) {
            fAvg = l6Avg;
        } else if (creditL6 == 0) {
            fAvg = l5Avg;
        } else {
            fAvg = (l5Avg * 0.3) + (l6Avg * 0.7);
        }

        String finalGrade = SimGrades.classify((int) Math.round(fAvg));

        return "L5 avg: " + Math.round(l5Avg) + "%\n" +
               "L6 avg: " + Math.round(l6Avg) + "%\n" +
               "Final Score: " + Math.round(fAvg) + "%\n" +
               "Classification: " + finalGrade;
    }

    // NEW:credit limit applied from feedbackk
    public String checkCreditLimits() {
        int cL4 = 0, cL5 = 0, cL6 = 0;
        for (ModuleRecord m : moduleList) {
            String lvl = m.fetchLevel();
            int cr = m.fetchCredits();
            if (lvl.equalsIgnoreCase("L4")) {
                cL4 += cr;
            } else if (lvl.equalsIgnoreCase("L5")) {
                cL5 += cr;
            } else if (lvl.equalsIgnoreCase("L6")) {
                cL6 += cr;
            }
        }
        StringBuilder sb = new StringBuilder();
        if (cL4 > 120) sb.append("oops... looks like you axceeded your credit count (").append(cL4).append(")\n");
        if (cL5 > 120) sb.append("oops you axceeded your credit count  (").append(cL5).append(")\n");
        if (cL6 > 120) sb.append("oops you axceeded your credit count  (").append(cL6).append(")\n");
        return sb.length() == 0 ? "All levels within credit limits." : sb.toString();
    }
}
