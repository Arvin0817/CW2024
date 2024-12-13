package com.example.demo;

import com.example.demo.controller.StartController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;

/**
 * Main class for launching the Sky Battle game.
 * This class sets up the main application window and initializes the StartController to render the start screen.
 *
 * <p>
 * The game is designed to run in a fixed-size window, and the start screen provides access to the game's main logic.
 * </p>
 *
 * @author [XIA SI ZHE]
 */

public class Main extends Application {
	// Screen dimensions
	private static final int SCREEN_WIDTH = 1300;
	private static final int SCREEN_HEIGHT = 750;

	// Game window title
	private static final String TITLE = "Sky Battle";


	/**
	 * The entry point for the JavaFX application. This method sets up the primary stage
	 * with the specified dimensions and passes it to the StartController to initialize the game.
	 *
	 * @param stage The primary stage for the JavaFX application
	 * @throws ClassNotFoundException       If a required class is not found
	 * @throws NoSuchMethodException        If a required method is not found
	 * @throws SecurityException            If there is a security violation
	 * @throws InstantiationException       If instantiation of a class fails
	 * @throws IllegalAccessException       If access to a class or method is denied
	 * @throws IllegalArgumentException     If an illegal argument is passed
	 * @throws InvocationTargetException    If a method invocation fails
	 */

	@Override
	public void start(Stage stage) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		try {stage.setTitle(TITLE);
		stage.setResizable(false);
		stage.setHeight(SCREEN_HEIGHT);
		stage.setWidth(SCREEN_WIDTH);
		new StartController(stage).launch();
	}catch (Exception e) {
			e.printStackTrace(); // TEST TRACK INFO
		}}

	public static void main(String[] args) {
		launch();

	}
}