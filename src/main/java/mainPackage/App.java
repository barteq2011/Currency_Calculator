package mainPackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;


public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Main fxml file
            Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
            primaryStage.setTitle("Currency Calculator");
            primaryStage.setResizable(false);
            primaryStage.getIcons().add(new Image(getClass().getResource("icon.png").toExternalForm()));
            primaryStage.setScene(new Scene(root, 300, 395));
            primaryStage.show();
        } catch (IOException e) {
            Alerts.showError("Cannot load one of the key files. Check if every of them exists.");
        }
    }
    public static void main(String[] args) {
        launch();
    }

}