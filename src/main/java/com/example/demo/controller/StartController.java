package com.example.demo.controller;

import com.example.demo.level.DifficultyEnum;
import com.example.demo.utils.MusicPlayer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * StartController class handles the logic for the game's start screen.
 * <p>
 * This class is responsible for setting up the UI for the game's start page, including background images,
 * title, difficulty selection, and a start button. It also initializes the game music and allows the player
 * to choose the difficulty level before starting the game.
 * </p>
 *
 * <p>
 * The start screen acts as the initial user interface for the player before entering the main gameplay.
 * </p>
 *
 * @author [XIA SI ZHE 20476377]
 */
public class StartController {
    // Reference to the main application stage
    private final Stage stage;
    // Layout container for start screen components
    private AnchorPane anchorPane;
    private Controller myController;
    /**
     * Constructs a StartController object with the main application stage.
     *
     * @param stage The primary stage for the JavaFX application
     */

    public StartController(Stage stage) {
        this.stage = stage;
    }

    /**
     * Launches the start screen.
     * <p>
     * This method sets up the start screen UI, including a background image, title, difficulty selection,
     * and a start button. It also plays the background music in a loop.
     * </p>
     */
    public void launch() {
        // Load and play background music
        String path = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("bgm.wav")).getPath();
        MusicPlayer.loop(path);

        anchorPane = new AnchorPane();

        // Background image
        ImageView background = new ImageView(new Image(getClass().getResource("/com/example/demo/images/background1.jpg").toExternalForm()));
        background.setFitWidth(1300);
        background.setFitHeight(750);

        // Title label
        Label title = new Label("Sky Battle");
        title.setLayoutX(500);
        title.setLayoutY(100);
        title.setPrefWidth(300);
        title.setPrefHeight(100);
        title.setFont(new Font(60));

        // Difficulty selection label
        Label difficultyLabel = new Label("Difficulty:");
        difficultyLabel.setLayoutX(380.0);
        difficultyLabel.setLayoutY(250);
        difficultyLabel.setFont(new Font(48));

        // Difficulty selection combo box
        ComboBox difficultyComboBox = new ComboBox();
        difficultyComboBox.setStyle("-fx-font-size: 36;");
        difficultyComboBox.setLayoutX(620);
        difficultyComboBox.setLayoutY(255);
        difficultyComboBox.setPrefWidth(320);
        difficultyComboBox.setPrefHeight(50);
        difficultyComboBox.getItems().add(DifficultyEnum.SIMPLE.getDifficulty());
        difficultyComboBox.getItems().add(DifficultyEnum.DIFFICULT.getDifficulty());
        difficultyComboBox.getSelectionModel().select(0);

        // Start button with an image
        Button button = new Button();
        ImageView start = new ImageView(new Image(getClass().getResource("/com/example/demo/images/start.png").toExternalForm()));
        start.setFitWidth(270);
        start.setFitHeight(100);
        button.setGraphic(start);
        button.setLayoutX(500);
        button.setLayoutY(400);

        // Start button click event
        button.setOnMouseClicked(event -> {
            Controller.DIFFICULTY = difficultyComboBox.getSelectionModel().getSelectedItem().toString();
            myController = new Controller(stage);
            try {
                myController.launchGame();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        // Add all components to the anchor pane
        anchorPane.getChildren().addAll(background, title, difficultyLabel, difficultyComboBox, button);
        // Set the scene and display the stage
        stage.setScene(new Scene(anchorPane));
        stage.show();
    }
}
