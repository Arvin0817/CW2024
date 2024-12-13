module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo.controller;
    opens com.example.demo.view to javafx.fxml;
    opens com.example.demo.model.plane to javafx.fxml;
    opens com.example.demo.model.projectile to javafx.fxml;
    opens com.example.demo.model to javafx.fxml;
    opens com.example.demo.level to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.utils;
    opens com.example.demo.utils to javafx.fxml;
}