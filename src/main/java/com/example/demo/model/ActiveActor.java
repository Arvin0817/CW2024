package com.example.demo.model;

import javafx.scene.image.*;

/**
 * Abstract base class for all active game actors represented by images.
 * <p>
 * This class provides foundational functionality for all game actors that use images,
 * including initialization, movement, and position updates. Subclasses must implement
 * the specific logic for updating positions.
 * </p>
 *
 * <p>
 * Key features:
 * <ul>
 *     <li>Initializes the actor's image, size, and position.</li>
 *     <li>Provides methods for horizontal and vertical movement.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Usage:
 * Subclasses should extend this class and implement the {@link #updatePosition()} method to define
 * their specific movement logic.
 * </p>
 *
 * @author [XIA SI ZHE]
 */

public abstract class ActiveActor extends ImageView {

	private static final String IMAGE_LOCATION = "/com/example/demo/images/";
//abstract class
	public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		//this.setImage(new Image(IMAGE_LOCATION + imageName));
		this.setImage(new Image(getClass().getResource(IMAGE_LOCATION + imageName).toExternalForm()));
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);
	}

	/**
	 * Updates the position of the actor.
	 * <p>
	 * Subclasses must implement this method to define how the actor's position changes
	 * based on game logic (e.g., movement speed, direction, or external factors).
	 * </p>
	 */
	public abstract void updatePosition();

	/**
	 * Moves the actor horizontally by the specified amount.
	 *
	 * @param horizontalMove The amount to move horizontally (positive for right, negative for left)
	 */
	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
	}//User plane also called this class

	/**
	 * Moves the actor vertically by the specified amount.
	 *
	 * @param verticalMove The amount to move vertically (positive for down, negative for up)
	 */
	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
	}

}

//