package com.example.demo.model.plane;

import com.example.demo.model.ActiveActorDestructible;
import com.example.demo.model.projectile.EnemyProjectile;

public class EnhancedEnemyPlane extends FighterPlane {


	// Constants for the enhanced enemy plane's attributes
	private static final String IMAGE_NAME = "bossplane.png";// Image for the enhanced enemy plane
	private static final int IMAGE_HEIGHT = 150;// Height of the enemy plane's image
	private static final int HORIZONTAL_VELOCITY = -6;// Speed of horizontal movement
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;// X offset for projectile spawn position
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;// Y offset for projectile spawn position
	private static final int INITIAL_HEALTH = 5;// Increased initial health
	private static final double FIRE_RATE = .01; // Probability of firing a projectile per frame

	public EnhancedEnemyPlane(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
	}

	/**
	 * Updates the position of the enhanced enemy plane by moving it horizontally.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}


	/**
	 * Fires a projectile with a certain probability.
	 * <p>
	 * The projectile's initial position is offset relative to the enemy plane's position.
	 * </p>
	 *
	 * @return A new {@link EnemyProjectile} if the firing condition is met, otherwise null
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		if (Math.random() < FIRE_RATE) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPostion = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return new EnemyProjectile(projectileXPosition, projectileYPostion);
		}// Create and return a new projectile
		return null;
	}

	@Override
	// Update the plane's position
	public void updateActor() {
		updatePosition();
	}

}
