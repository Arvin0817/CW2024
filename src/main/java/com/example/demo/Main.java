package com.example.demo;

import com.example.demo.controller.StartController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;

public class Main extends Application {

	private static final int SCREEN_WIDTH = 1300;
	private static final int SCREEN_HEIGHT = 750;
	private static final String TITLE = "Sky Battle";

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