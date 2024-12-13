package com.example.demo.model.projectile;

/**
 * Represents a projectile fired by the Boss in the game.
 * <p>
 * This class defines the specific properties and behavior of a BossProjectile,
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
public class BossProjectile extends Projectile {

	// Name of boss bullet
	private static final String IMAGE_NAME = "fireball.png";
	// Height of the projectile's image
	private static final int IMAGE_HEIGHT = 75;
	private static final int HORIZONTAL_VELOCITY = -15;
	private static final int INITIAL_X_POSITION = 950;

	/**
	 * Constructs a BossProjectile with the specified initial Y-coordinate.
	 *
	 * @param initialYPos The initial Y-coordinate position of the projectile.
	 */
	public BossProjectile(double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
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

	/**
	 * Updates the state of the projectile actor.
	 * <p>
	 * Calls the {@link #updatePosition()} method to handle position changes.
	 * </p>
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
	
}
