package com.example.demo.view;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;

public class HeartDisplay {
	
	private static final String HEART_IMAGE_NAME = "/com/example/demo/images/heart.png";
	private static final int HEART_HEIGHT = 50;
	private static final int INDEX_OF_FIRST_ITEM = 0;
	private AnchorPane container;
	private double containerXPosition;
	private double containerYPosition;
	private int numberOfHeartsToDisplay;
	private Rectangle blood;
	private double BLOOD_WIDTH = 200;
	private double PER_BLOOD_WIDTH = 40;
	private double BLOOD_HEIGHT = 30;

	public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
		this.containerXPosition = xPosition;
		this.containerYPosition = yPosition;
		this.numberOfHeartsToDisplay = heartsToDisplay;
		initializeContainer();
		initializeHearts();
	}
	
	private void initializeContainer() {
		container = new AnchorPane();
		container.setLayoutX(containerXPosition);
		container.setLayoutY(containerYPosition);
	}

	/**
	 * add blood image checking
	 * @return heart image
	 */

	private Image loadHeartImage() {
		URL resource = getClass().getResource(HEART_IMAGE_NAME);
		if (resource == null) {
			throw new IllegalArgumentException("Heart image resource not found: " + HEART_IMAGE_NAME);
		}
		return new Image(resource.toExternalForm());
	}

	/**
	 * Changed to bloodstain form White rectangle at bottom, red rectangle at top
	 */
	private void initializeHearts() {
		Rectangle rectangle = new Rectangle(0,0,BLOOD_WIDTH,BLOOD_HEIGHT);
		rectangle.setFill(Color.WHITE);
		container.getChildren().add(rectangle);

		blood = new Rectangle(0,0,BLOOD_WIDTH,BLOOD_HEIGHT);
		blood.setFill(Color.RED);
		container.getChildren().add(blood);
	}

	/**
	 * Reducing the width of the red rectangle represents a reduction in blood volume.
	 */
	public void removeHeart() {
		blood.setWidth(blood.getWidth()-PER_BLOOD_WIDTH);
	}
	
	public AnchorPane getContainer() {
		return container;
	}

	/**
	 * Gain blood volume
	 */
	public int getBlood(){
		return (int) (blood.getWidth()/PER_BLOOD_WIDTH);
	}
}
