package com.example.demo.level;

import com.example.demo.view.GameOverImage;
import com.example.demo.view.HeartDisplay;
import com.example.demo.view.WinImage;
import javafx.scene.Group;

public class LevelView {

	// Constants for positioning UI elements

	// X position of the heart displa
	private static final double HEART_DISPLAY_X_POSITION = 5;

	// Y position of the heart display
	private static final double HEART_DISPLAY_Y_POSITION = 25;

	// X position of the win image
	private static final int WIN_IMAGE_X_POSITION = 355;

	// Y position of the win image
	private static final int WIN_IMAGE_Y_POSITION = 175;

	// X position of the game-over image
	private static final int LOSS_SCREEN_X_POSITION = 250;

	// Y position of the game-over image
	private static final int LOSS_SCREEN_Y_POSISITION = 60;
	private final Group root;

	// UI elements for different game states
	private final WinImage winImage;
	private final GameOverImage gameOverImage;
	private final HeartDisplay heartDisplay;
	
	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
		this.gameOverImage = new GameOverImage(LOSS_SCREEN_X_POSITION, LOSS_SCREEN_Y_POSISITION);
	}

	/**
	 * Adds the heart display to the scene, showing the player's current health.
	 */
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	/**
	 * Displays the win image on the screen.
	 */
	public void showWinImage() {
		root.getChildren().add(winImage);
		winImage.showWinImage();
	}


	/**
	 * Displays the game-over image on the screen.
	 */
	public void showGameOverImage() {
		root.getChildren().add(gameOverImage);
	}

	/**
	 * Updates the heart display by removing hearts when the player's health decreases.
	 *
	 * @param heartsRemaining The updated number of hearts to display
	 */
	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getBlood();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}

}
