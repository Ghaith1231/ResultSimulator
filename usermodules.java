import java.util.*;

public class usermodules {
    private List<ModuleInfo> modz;

    public usermodules() {
        modz = new ArrayList<>();
    }

    public void add(ModuleInfo m) {
        modz.add(m);
    }

    public double getAverage() {
        double total = 0;
        int totalCredits = 0;
        for (ModuleInfo m : modz) {
            total += m.getGrade() * m.getCredits();
            totalCredits += m.getCredits();
        }
        return totalCredits > 0 ? total / totalCredits : 0;
    }

    public String verifier() {
        double avg = getAverage();
        if (avg >= 70) {
            return "Excellent";
        } else if (avg >= 60) {
            return "Very Good";
        } else if (avg >= 50) {
            return "Good";
        } else if (avg >= 40) {
            return "Satisfactory";
        } else {
            return "Fail";
        }
    }


    public List<ModuleInfo> getModules() {
        return modz;
    }
}
