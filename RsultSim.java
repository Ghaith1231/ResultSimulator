
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class RsultSim extends JFrame {
    private final ArrayList<ModuleRecord> modules = new ArrayList<>();
    private final JTextArea displayArea;

    public RsultSim() {
        setTitle("Result Simulator");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JTextField moduleNameField = new JTextField(10);
        JTextField gradeField = new JTextField(5);
        JTextField creditField = new JTextField(5);
        String[] moduleLevels = {"L4", "L5", "L6"};
        JComboBox<String> moduleLevelBox = new JComboBox<>(moduleLevels);
        String[] degrees = {"UG", "PG", "FY"};
        JComboBox<String> degreeTypeBox = new JComboBox<>(degrees);

        JButton addButton = new JButton("Add Module");
        JButton resultButton = new JButton("Classify Results");
        JButton whatIfButton = new JButton("What-If Mode");
        JCheckBox directEntryCheckbox = new JCheckBox("Direct Entry to Level 6");

        displayArea = new JTextArea(15, 40);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        add(new JLabel("Module:"));
        add(moduleNameField);
        add(new JLabel("Grade:"));
        add(gradeField);
        add(new JLabel("Credits:"));
        add(creditField);
        add(new JLabel("Module Level:"));
        add(moduleLevelBox);
        add(new JLabel("Degree Type:"));
        add(degreeTypeBox);
        add(addButton);
        add(resultButton);
        add(directEntryCheckbox);
        add(whatIfButton);
        add(scrollPane);

        addButton.addActionListener((ActionEvent e) -> {
            addModule(moduleNameField.getText(), gradeField.getText(), creditField.getText());
            moduleNameField.setText("");
            gradeField.setText("");
            creditField.setText("");
        });

        resultButton.addActionListener((ActionEvent e) -> classifyResults());

        whatIfButton.addActionListener((ActionEvent e) -> {
            String whatIfStr = JOptionPane.showInputDialog(this, "Enter grade to simulate:");
            try {
                int whatIfGrade = Integer.parseInt(whatIfStr.trim());
                int totalCredits = 0;
                int totalScore = 0;
                for (ModuleRecord m : modules) {
                    totalCredits += m.getCredits();
                    totalScore += m.getCredits() * m.getGrade();
                }
                totalScore += 20 * whatIfGrade;
                totalCredits += 20;
                int avg = totalScore / totalCredits;
                displayArea.append("What-If average: " + avg + "\n");
                displayArea.append("What-If classification: " + SimGrades.classify(avg) + "\n");
            } catch (NumberFormatException ex) {
                displayArea.append("Invalid input for What-If mode.\n");
            }
        });
    }

    private void addModule(String name, String gradeStr, String creditsStr) {
        if (gradeStr.isEmpty() || creditsStr.isEmpty()) {
            displayArea.append("Please insert a valid input in both fields.\n");
            return;
        }

        try {
            int grade = Integer.parseInt(gradeStr);
            int credit = Integer.parseInt(creditsStr);
            if (grade < 0 || grade > 100 || credit <= 0) {
                displayArea.append("Enter valid grade (0-100) and credit (positive number).\n");
                return;
            }
            ModuleRecord record = new ModuleRecord(name, grade, credit);
            modules.add(record);
            displayArea.append("Added: " + name + ", Grade: " + grade + ", Credits: " + credit + "\n");
        } catch (NumberFormatException e) {
            displayArea.append("Only numeric input allowed for grade and credits.\n");
        }
    }

    private void classifyResults() {
        int totalCredits = 0;
        int totalScore = 0;
        for (ModuleRecord m : modules) {
            totalCredits += m.getCredits();
            totalScore += m.getCredits() * m.getGrade();
        }
        if (totalCredits == 0) {
            displayArea.append("No modules to calculate.\n");
            return;
        }
        int average = totalScore / totalCredits;
        String classification = SimGrades.classify(average);
        displayArea.append("Average: " + average + "\n");
        displayArea.append("Classification: " + classification + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RsultSim sim = new RsultSim();
            sim.setVisible(true);
        });
    }
}
