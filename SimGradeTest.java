import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class SimGradeTest {

    @Test
    public void testClassificationWithModules() {
        ArrayList<ModuleRecord> testModules = new ArrayList<>();
        testModules.add(new ModuleRecord("Web development", 68, 20));
        testModules.add(new ModuleRecord("IT placement", 72, 20));

        String expected = "2:1, Very Good";
        String actual = SimGrades.classify(testModules, false);

        assertEquals(expected, actual);
    }
}
