package mainPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

public final class FileHandler {
    // Path to Database file
    private final URL FILE_NAME = getClass().getResource("data.csv");

    public static FileHandler getInstance() { return new FileHandler(); }

    public Map<String, Double> loadFile() {
        // Map to store currencys
        Map<String, Double> currency = new LinkedHashMap<>();
        try {
            // Convert file's URL to Path
            Path filePath = Paths.get(FILE_NAME.toURI());
            try (BufferedReader reader = Files.newBufferedReader(filePath)) {
                // Row to store single record from database file
                String dataRow;
                while ((dataRow = reader.readLine()) != null) {
                    // Split columns (because of .csv format)
                    String[] dataCell = dataRow.split(";");
                    // Collect single values of name and value compared to dolar from data record
                    String name = dataCell[0];
                    double relativeValue = Double.parseDouble(dataCell[1]);
                    // Put collected currency with value compared to dolar in currencys map
                    currency.put(name, relativeValue);
                }
            } catch (IOException e) {
                Alerts.showError("Error while loading database file, check if every neccesary files" +
                        "exists");
            }
        } catch (URISyntaxException e) {
            Alerts.showError("Error while resolving path to database file");
        }
        return currency;
    }
}
