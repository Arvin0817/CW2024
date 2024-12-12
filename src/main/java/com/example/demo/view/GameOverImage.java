package com.example.demo.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameOverImage extends ImageView {
	
	private static final String IMAGE_NAME = "/com/example/demo/images/gameover.png";

	public GameOverImage(double xPosition, double yPosition) {
		/**
		 * Path to the "Game Over" image resource.
		 * The image is located in the resources directory under the specified path.
		 */

		setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));

		/**
		 * Constructs a GameOverImage object with specified x and y positions.
		 *
		 * @param xPosition the horizontal position of the "Game Over" image on the screen.
		 * @param yPosition the vertical position of the "Game Over" image on the screen.
		 */
//		setImage(ImageSetUp.getImageList().get(ImageSetUp.getGameOver()));
		setLayoutX(xPosition);
		setLayoutY(yPosition);
		// Set the dimensions of the image.
		this.setFitHeight(650);
		this.setFitWidth(650);
		//Original image too big, l adjust the size for it..
	}

}
