module nl.inholland.app {
    requires javafx.controls;
    requires javafx.fxml;


    opens nl.inholland.app to javafx.fxml;
    exports nl.inholland.app;
}