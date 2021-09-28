package nl.inholland.app;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage window) {
        window.setHeight(200);
        window.setWidth(300);
        window.setTitle("Inholland JavaFX Starter Project");

        GridPane pane1 = new GridPane();

        pane1.setPadding(new Insets(10, 10, 10, 10));
        pane1.setVgap(10);
        pane1.setHgap(10);

        Label usernameLabel = new Label("Username:");
        GridPane.setConstraints(usernameLabel, 0, 0);

        Label passwordLabel = new Label("Password:");
        GridPane.setConstraints(passwordLabel, 0, 1);

        TextField usernameInput = new TextField();
        System.out.println(usernameInput.getText());
        usernameInput.setPromptText("enter username");
        GridPane.setConstraints(usernameInput, 1, 0);

        PasswordField passwordField = new PasswordField();
        System.out.println(passwordField.getText());
        passwordField.setPromptText("enter password");
        GridPane.setConstraints(passwordField, 1, 1);

        Button loginButton = new Button("Login");
        GridPane.setConstraints(loginButton, 1, 3);

        Label secretLabel = new Label();
        GridPane.setConstraints(secretLabel, 0, 3);

        Label errorLabel = new Label();
        GridPane.setConstraints(errorLabel, 1, 2);

        pane1.getChildren().addAll(usernameLabel,passwordLabel,usernameInput,passwordField,loginButton,secretLabel,errorLabel);

        //create StringProperty
        StringProperty passwordProperty = passwordField.textProperty();
        loginButton.setVisible(false);
        //add listener to property
        passwordProperty.addListener((observableValue, oldValue, newValue) -> {
            errorLabel.getText();
            String error;
            String password = passwordField.getText();


            boolean validChars = password.matches(".*[a-zA-Z-0-9-!@#$%^&*()-+~{}.,;]+.*");
            boolean validNums = password.matches(".*[0-9]+.*");
            boolean validSymbol = password.matches(".*[!@#$%^&*()-+~{}.,;]+.*");
            boolean validLetters = password.matches(".*[a-zA-Z]+.*");
            loginButton.setVisible(false);
            if (password.length() < 8) {
                error = "You need more than 8 characters";
                errorLabel.setText(error);

            } else if (!validLetters) {
                error = "You are missing a letter";
                errorLabel.setText(error);
            } else if (!validSymbol) {
                error = "You need a special character";
                errorLabel.setText(error);
            } else if (!validNums) {
                error = "You need at least 1 number";
                errorLabel.setText(error);
            } else {
                if (!validChars) {
                    return;
                }
                error = "";
                errorLabel.setText(error);
                loginButton.setVisible(true);
            }

        });

        secretLabel.textProperty().bind(passwordProperty);

        loginButton.setOnAction(actionEvent -> {
            usernameLabel.setVisible(false);
            passwordLabel.setVisible(false);
            usernameInput.setVisible(false);
            passwordField.setVisible(false);
            loginButton.setVisible(false);
            secretLabel.setVisible(false);
            errorLabel.setVisible(false);
            // I tried adding an image, but it had too many errors
        });

        Scene scene = new Scene(pane1);
        window.setScene(scene);
        window.show();
    }
}
