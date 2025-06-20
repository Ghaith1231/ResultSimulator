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
        JCheckBox directEntryCheckbox = new JCheckBox("Direct Entry to Level 6");

        JButton addButton = new JButton("Add Module");
        JButton resultButton = new JButton("Classify Results");
        JButton whatIfButton = new JButton("What-If Mode");

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
        add(directEntryCheckbox);
        add(addButton);
        add(resultButton);
        add(whatIfButton);
        add(scrollPane);

        addButton.addActionListener((ActionEvent e) -> {
            String moduleName = moduleNameField.getText().trim();
            String gradeStr = gradeField.getText().trim();
            String creditsStr = creditField.getText().trim();
            String level = (String) moduleLevelBox.getSelectedItem();

            if (gradeStr.isEmpty() || creditsStr.isEmpty() || moduleName.isEmpty()) {
                displayArea.append("Please insert a valid input in those 3 fields.\n");
                return;
            }

            try {
                int grade = Integer.parseInt(gradeStr);
                int credits = Integer.parseInt(creditsStr);
                if (grade < 0 || grade > 100 || credits < 0 || credits > 120) {
                    displayArea.append("Enter valid grade (0-100) and credit (0-120) (positive number).\n");
                    return;
                }
                ModuleRecord record = new ModuleRecord(moduleName, grade, credits, level);
                modules.add(record);
                displayArea.append("Added: " + moduleName + ", Grade: " + grade + ", Credits: " + credits + ", Level: " + level + "\n");
            } catch (NumberFormatException ex) {
                displayArea.append("Only numeric input allowed for grade and credits.\n");
            }

            moduleNameField.setText("");
            gradeField.setText("");
            creditField.setText("");
        });

        resultButton.addActionListener((ActionEvent e) -> {
            boolean isDirectEntry = directEntryCheckbox.isSelected();
            String classification = SimGrades.classify(modules, isDirectEntry);
            displayArea.append("Classification: " + classification + "\n");
        });

        whatIfButton.addActionListener((ActionEvent e) -> {
            String gradesStr = JOptionPane.showInputDialog(this, "Enter grades for the simulation:");
            String creditsStr = JOptionPane.showInputDialog(this, "Enter credits for the simulation:");
            try {
                int whatIfGrade = Integer.parseInt(gradesStr.trim());
                int whatIfCredits = Integer.parseInt(creditsStr.trim());
                if (whatIfGrade < 0 || whatIfGrade > 100 || whatIfCredits < 0 || whatIfCredits > 120) {
                    displayArea.append("Enter valid grade (0-100) and credit (positive number).\n");
                    return;
                }
                int totalCredits = 0;
                int totalScore = 0;
                for (ModuleRecord m : modules) {
                    totalCredits += m.fetchCredits();
                    totalScore += m.fetchCredits() * m.fetchGrade();
                }
                totalScore += whatIfCredits * whatIfGrade;
                totalCredits += whatIfCredits;
                
                int avg = totalScore / totalCredits;
                displayArea.append("What-If average: " + avg + "\n");
                displayArea.append("What-If classification: " + SimGrades.classify(avg) + "\n");
            } catch (NumberFormatException ex) {
                displayArea.append("Invalid input for What-If mode.\n");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RsultSim sim = new RsultSim();
            sim.setVisible(true);
        });
    }
}
