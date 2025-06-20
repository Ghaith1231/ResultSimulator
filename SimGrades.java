import java.util.ArrayList;

public class SimGrades {

    public static String classify(ArrayList<ModuleRecord> records, boolean directEntry) {
        int totalCredits = 0;
        int totalScore = 0;
        int l5Credits = 0;
        int l5Score = 0;
        int l6Credits = 0;
        int l6Score = 0;

        for (ModuleRecord record : records) {
            String level = record.fetchLevel();
            int grade = record.fetchGrade();
            int credit = record.fetchCredits();

            switch (level) {
                case "L4":
                    l5Credits += credit;
                    l5Score += credit * grade;
                    break;
                case "L5":
                    l5Credits += credit;
                    l5Score += credit * grade;
                    break;
                case "L6":
                    l6Credits += credit;
                    l6Score += credit * grade;
                    break;
                default:
                    break;
            }

            totalCredits += credit;
            totalScore += credit * grade;
        }

        if (directEntry) {
            if (l6Credits == 0) return "Insufficient L6 credits";
            int average = l6Score / l6Credits;
            return classify(average);
        } else {
            int combinedCredits = l5Credits + l6Credits;
            int combinedScore = l5Score + l6Score;

            if (combinedCredits == 0) return "No L4/L5/L6 modules found";

            int average = combinedScore / combinedCredits;
            return classify(average);
        }
    }

    public static String classify(int average) {
        if (average >= 70) {
            return "Excellent (First)";
        } else if (average >= 60) {
            return "Very Good (2:1)";
        } else if (average >= 50) {
            return "Good (2:2)";
        } else if (average >= 40) {
            return "Satisfactory (Third)";
        } else {
            return "Fail";
        }
    }
}
