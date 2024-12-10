package com.example.demo.controller;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Start page logic
 */
public class StartController {
    private final Stage stage;
    private AnchorPane anchorPane;
    private Controller myController;

    public StartController(Stage stage) {
        this.stage = stage;
    }

    /**
     * draw start page
     */
    public void launch() {
        anchorPane = new AnchorPane();

        //backgroud
        ImageView background = new ImageView(new Image(getClass().getResource("/com/example/demo/images/background1.jpg").toExternalForm()));
        background.setFitWidth(1300);
        background.setFitHeight(750);

        //game name title
        Label title = new Label("Sky Battle");
        title.setLayoutX(500);
        title.setLayoutY(200);
        title.setPrefWidth(300);
        title.setPrefHeight(100);
        title.setFont(new Font(60));

        //start button details
        Button button = new Button();
        ImageView start = new ImageView(new Image(getClass().getResource("/com/example/demo/images/start.png").toExternalForm()));
        start.setFitWidth(270);
        start.setFitHeight(100);
        button.setGraphic(start);
        button.setLayoutX(500);
        button.setLayoutY(400);
        button.setOnMouseClicked(event -> {
            myController = new Controller(stage);
            try {
                myController.launchGame();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        anchorPane.getChildren().addAll(background, title, button);
        stage.setScene(new Scene(anchorPane));
        stage.show();
    }
}
