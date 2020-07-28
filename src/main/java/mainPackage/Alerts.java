package mainPackage;

import javafx.scene.control.Alert;
// Class for showing GUI Errors and Alerts
public final class Alerts {
    // Error message
    public static void showError(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    // Info message
    public static void showInfo(String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
