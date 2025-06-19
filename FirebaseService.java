import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FirebaseService {

    // This should be the actual database endpoint, not the console link
    private static final String FIREBASE_URL = "https://com50003-grade-calculator-default-rtdb.europe-west1.firebasedatabase.app/results.json";

    public static void saveToFirebase(String name, String description, int grade, int credits) {
        try {
            URL url = new URL(FIREBASE_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String json = String.format(
                "{\"name\":\"%s\", \"description\":\"%s\", \"grade\":%d, \"credits\":%d}",
                name, description, grade, credits
            );

            try (OutputStream os = conn.getOutputStream()) {
                os.write(json.getBytes());
            }

            int responseCode = conn.getResponseCode();
            System.out.println("Firebase response code: " + responseCode);
        } catch (Exception e) {
            System.out.println("Failed to save to Firebase.");
            e.printStackTrace();
        }
    }
}
