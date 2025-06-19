import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CalcDatabase {

    private static final String FIREBASE_URL = "https://console.firebase.google.com/project/com50003-grade-calculator/database/com50003-grade-calculator-default-rtdb/data/~2F";

    public static void readCalcDatabase() {
        try {
            URL url = new URL(FIREBASE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line).append("\n");
            }

            reader.close();
            connection.disconnect();

            System.out.println("Data fetched from Firebase:\n" + response.toString());

        } catch (Exception e) {
            System.out.println("Failed to fetch data from Firebase.");
            e.printStackTrace();
        }
    }

    public static ArrayList<ModuleInfo> fetchedModules = new ArrayList<>();
}
