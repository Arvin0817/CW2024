package com.example.demo.controller;

import com.example.demo.level.LevelParent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;


/**
 * Controller class responsible for managing the game flow, including transitioning between levels and handling updates.
 * <p>
 * The Controller class initializes the game starting from the first level and manages communication between
 * game objects and the main application stage. It utilizes reflection to dynamically load level classes
 * based on their class names.
 * </p>
 *
 * <p>
 * This class implements the Observer pattern to respond to changes in game state and transitions between levels.
 * </p>
 *
 * @author [Xia si zhe]
 */
public class Controller implements Observer {

	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.level.LevelOne";
	// Reference to the main application stage
	private final Stage stage;
	// Game difficulty setting (can be used to adjust level behavior)
	public static String DIFFICULTY;

	public Controller(Stage stage) {
		this.stage = stage;
	}

	public void launchGame() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {

			stage.show();
			goToLevel(LEVEL_ONE_CLASS_NAME);
	}

	private void goToLevel(String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// Use reflection to load the specified level class
		Class<?> myClass = Class.forName(className);
			Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
			LevelParent myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth());
			myLevel.addObserver(this);
		// Initialize the scene and set it on the stage
			Scene scene = myLevel.initializeScene();
			stage.setScene(scene);
		// Start the game logic for the level
			myLevel.startGame();

	}


	@Override
	public void update(Observable arg0, Object arg1) {
		try {
			goToLevel((String) arg1);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(e.getClass().toString());
			alert.show();
		}
	}

}
