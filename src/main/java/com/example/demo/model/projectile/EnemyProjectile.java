package com.example.demo.model.projectile;

/**
 * Represents a projectile fired by enemy units in the game.
 * <p>
 * This class defines the specific properties and behavior of an EnemyProjectile,
 * including its appearance, initial position, and movement logic.
 * </p>
 *
 * <p>
 * Responsibilities include:
 * <ul>
 *     <li>Initializing the projectile with specific dimensions and image.</li>
 *     <li>Updating the position of the projectile by moving it horizontally.</li>
 * </ul>
 * </p>
 *
 * @author [Xia si zhe]
 */
public class EnemyProjectile extends Projectile {

	// Name of the image file used to represent the projectile
	private static final String IMAGE_NAME = "enemyFire.png";
	private static final int IMAGE_HEIGHT = 50;
	private static final int HORIZONTAL_VELOCITY = -10;

	/**
	 * Constructs an EnemyProjectile with the specified initial position.
	 *
	 * @param initialXPos The initial X-coordinate of the projectile.
	 * @param initialYPos The initial Y-coordinate of the projectile.
	 */
	public EnemyProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Updates the position of the projectile.
	 * <p>
	 * Moves the projectile horizontally based on the defined velocity.
	 * </p>
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	@Override
	public void updateActor() {
		updatePosition();
	}


}
