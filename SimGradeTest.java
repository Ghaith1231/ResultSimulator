import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class SimGradeTest {

    @Test
    public void testClassificationWithModules() {
        ArrayList<ModuleRecord> testModules = new ArrayList<>();

        testModules.add(new ModuleRecord("Web_development", 68, 20, "L5"));
        testModules.add(new ModuleRecord("IT_placement", 72, 20, "L5"));

        String expected = "Excellent, first";
        String actual = SimGrades.classify(testModules, false);

        assertEquals(expected, actual);
    }
}
