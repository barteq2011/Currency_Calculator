package mainPackage;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Controller {
    @FXML
    private TextField dolarField, euroField, jenField, funtField, austDolarField, kanDolarField, frankField, renimbiField, koronaField, nowozelDolarField, zlotyField;
    @FXML
    private Label errorLabel;
    // Map to store currencys with their value compared to dolar
    Map<String, Double> currency;
    // Handler for pressing enter after passing currency value for calculating
    EventHandler<KeyEvent> enterHandler;
    // Variable used for calculate fields values
    private double dolarAmount;

    @FXML
    public void initialize() {
        // Load database file and put currencys into appropiate map
        currency = FileHandler.getInstance().loadFile();
        currency.getOrDefault("", 0.0);
        // Create EventHandler for handling enter button used for calculate currencys
        enterHandler = (KeyEvent keyEvent) -> {
            // Get the field which value will be calculated to dolar and all other fields
            TextField actualTextField = (TextField) keyEvent.getSource();
            // Check if user clicked ENTER button and the entered value is not empty
            if (!actualTextField.getText().isEmpty() && keyEvent.getCode() == KeyCode.ENTER) {
                // Calculate entered value to dolar and all other fields
                getCorrectField(actualTextField);
                // Set appropiate caret position after calculating
                actualTextField.positionCaret(actualTextField.getText().length());
            }
        };
        // Add fields to list
        List<TextField> currencyFields = new ArrayList<>(List.of(euroField, jenField, funtField, austDolarField,
                kanDolarField, frankField, renimbiField, koronaField, nowozelDolarField, zlotyField, dolarField));
        // Add EnterHandler to fields
        currencyFields.forEach(field -> field.addEventHandler(KeyEvent.KEY_PRESSED, enterHandler));
        // Set init value of dolar and other currencys
        dolarField.setText(String.valueOf(1));
        compareFieldsToDolar();
    }

    @FXML
    public void handleExitMenuItem() {
        Platform.exit();
    }

    @FXML
    public void handleAboutMenuItem() {
        // Show dialog window about author of application
        Alerts.showInfo("Currency converter by Bartosz Bartosik");
    }
    // Calculate entered value with dolar and all other fields
    private void getCorrectField(TextField field) {
        double valueOfTheField;
        String currency = "";
        try {
            // Convert entered value to double format
            valueOfTheField = Double.parseDouble(field.getText());
            // Field cannot be negative
            if (valueOfTheField < 0) valueOfTheField = 0;
            // Find field that user edits
            if (field.equals(euroField)) {
                currency = "Euro";
            } else if (field.equals(jenField)) {
                currency = "Jen";
            } else if (field.equals(funtField)) {
                currency = "Funt";
            } else if (field.equals(austDolarField)) {
                currency = "Dolar_Aust";
            } else if (field.equals(kanDolarField)) {
                currency = "Dolar_Kan";
            } else if (field.equals(frankField)) {
                currency = "Frank";
            } else if (field.equals(renimbiField)) {
                currency = "Renimbi";
            } else if (field.equals(koronaField)) {
                currency = "Korona";
            } else if (field.equals(nowozelDolarField)) {
                currency = "Dolar_Nowozel";
            } else if (field.equals(zlotyField)) {
                currency = "Zloty";
            } else if (field.equals(dolarField)) {
                currency = "Dolar";
            }
            // Calculate appropiate field's value with dolar
            dolarField.setText(String.valueOf(valueOfTheField * this.currency.get(currency)));
            errorLabel.setText("");
            // Calculate other fields values
            compareFieldsToDolar();
        } catch (Exception e) {
            // Show error
            errorLabel.setText("Wrong currency format. \nCorrect one is * number.number *");
            // Clear the incorrect edited field
            field.clear();
        }
    }
    // Calculate fields values based on dolar amount
    private void compareFieldsToDolar() {
        // Get dolar amount
        dolarAmount = Double.parseDouble(dolarField.getText());
        // Calculate fields values and show them to user
        euroField.setText(calculateFieldBasedOnDolarAmount("Euro"));
        jenField.setText(calculateFieldBasedOnDolarAmount("Jen"));
        funtField.setText(calculateFieldBasedOnDolarAmount("Funt"));
        austDolarField.setText(calculateFieldBasedOnDolarAmount("Dolar_Aust"));
        kanDolarField.setText(calculateFieldBasedOnDolarAmount("Dolar_Can"));
        frankField.setText(calculateFieldBasedOnDolarAmount("Frank"));
        renimbiField.setText(calculateFieldBasedOnDolarAmount("Renimbi"));
        koronaField.setText(calculateFieldBasedOnDolarAmount("Korona"));
        nowozelDolarField.setText(calculateFieldBasedOnDolarAmount("Dolar_Nowozel"));
        zlotyField.setText(calculateFieldBasedOnDolarAmount("Zloty"));
    }
    // Calculate single field value based on dolar amount
    private String calculateFieldBasedOnDolarAmount(String currency) {
        return String.valueOf(dolarAmount / this.currency.get(currency));
    }
}
