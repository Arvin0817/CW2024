package com.example.demo.model.plane;

import com.example.demo.model.ActiveActorDestructible;
import com.example.demo.model.projectile.UserProjectile;

public class UserPlane extends FighterPlane {

	// Constants for the user plane's attributes
	private static final String IMAGE_NAME = "userplane.png";

	private static final double X_UPPER_BOUND = 0;
	private static final double X_LOWER_BOUND = 1300.0;
	private static final double Y_UPPER_BOUND = -40;
	private static final double Y_LOWER_BOUND = 600.0;
	private static final double INITIAL_X_POSITION = 5.0;
	private static final double INITIAL_Y_POSITION = 300.0;
	private static final int IMAGE_HEIGHT = 50;
	private static final int HORIZONTAL_VELOCITY = 8;
	private static final int VERTICAL_VELOCITY = 8;
	private static final int PROJECTILE_X_POSITION = 110;
	private static final int PROJECTILE_Y_POSITION_OFFSET = 20;
	// Multipliers for movement direction
	private int horizontalMultiplier;
	private int velocityMultiplier;
	private int numberOfKills;
	private static int HEALTH = 5;

	/**
	 * Constructs a UserPlane with the specified initial health.
	 *
	 * @param initialHealth The initial health of the user plane
	 */
	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		horizontalMultiplier = 0;
		velocityMultiplier = 0;
	}

	/**
	 * Updates the position of the user plane based on movement multipliers.
	 * <p>
	 * Ensures the plane remains within defined screen bounds.
	 * </p>
	 */
	@Override
	public void updatePosition() {
		if (isMoving()) {
			// Update vertical position
			double initialTranslateY = getTranslateY();
			this.moveVertically(VERTICAL_VELOCITY * velocityMultiplier);
			double newPosition = getLayoutY() + getTranslateY();
			if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}
			// Update horizontal position
			this.moveHorizontally(HORIZONTAL_VELOCITY * horizontalMultiplier);

			double initialTranslateX = getTranslateX();
			this.moveHorizontally(HORIZONTAL_VELOCITY * horizontalMultiplier);
			double newPositionX = getLayoutX() + getTranslateX();
			if (newPositionX < X_UPPER_BOUND || newPositionX > X_LOWER_BOUND) {
				this.setTranslateX(initialTranslateX);
			}
		}
	}

	/**
	 * Updates the user plane's state, including its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
	
	@Override
	public ActiveActorDestructible fireProjectile() {
		return new UserProjectile(PROJECTILE_X_POSITION, getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
		/**
		 * Checks if the plane is currently moving.
		 *
		 * @return true if the plane is moving, false otherwise
		 */
	}

	private boolean isMoving() {
		return velocityMultiplier != 0 || horizontalMultiplier != 0;
	}
	/**
	 * Initiates upward movement for the user plane.
	 */
	public void moveUp() {
		velocityMultiplier = -1;
	}
	/**
	 * Initiates downward movement for the user plane.
	 */
	public void moveDown() {
		velocityMultiplier = 1;
	}

	/**
	 * Initiates leftward movement for the user plane.
	 */
	public void moveLeft() {
		horizontalMultiplier = -1;
	}

	/**
	 * Initiates rightward movement for the user plane.
	 */
	public void moveRight() {
		horizontalMultiplier = 1;
	}
	/**
	 * Stops vertical movement for the user plane.
	 */

	public void stop() {
		velocityMultiplier = 0;
	}

	public void horizontalStop() {
		horizontalMultiplier = 0;
	}

	/**
	 * Retrieves the number of enemies destroyed by the user plane.
	 *
	 * @return The current kill count
	 */
	public int getNumberOfKills() {
		return numberOfKills;
	}

	public void incrementKillCount() {
		numberOfKills++;
	}
	/**
	 * Reduces the health of the user plane by 1 when it takes damage.
	 * <p>
	 * If the health reaches zero, the plane is destroyed.
	 * </p>
	 */

	@Override
	public void takeDamage() {
		HEALTH--;
		if (healthAtZero()) {
			this.destroy();

		}
	}
	/**
	 * Checks if the user plane's health is zero.
	 *
	 * @return true if health is zero, false otherwise
	 */

	@Override
	public boolean healthAtZero() {
		return HEALTH == 0;
	}

	@Override
	public int getHealth() {
		return HEALTH;
	}
}
