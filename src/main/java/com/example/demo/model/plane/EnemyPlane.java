package com.example.demo.model.plane;

import com.example.demo.model.ActiveActorDestructible;
import com.example.demo.model.projectile.EnemyProjectile;

/**
 * Represents a basic enemy plane in the game.
 * <p>
 * Enemy planes move horizontally across the screen and occasionally fire projectiles.
 * They have minimal health and are intended as standard enemies.
 * </p>
 *
 * <p>
 * Key features:
 * <ul>
 *     <li>Constant horizontal movement from right to left.</li>
 *     <li>Fires projectiles with a low probability each frame.</li>
 *     <li>Extends {@link FighterPlane}, inheriting basic plane functionality such as movement and health handling.</li>
 * </ul>
 * </p>
 *
 * <p>
 * This class is used as a basic enemy type and can be extended for more complex behaviors.
 * </p>
 *
 * @author [XIA si zhe]
 */

public class EnemyPlane extends FighterPlane {

	// Constants for the enemy plane's attributes
	private static final String IMAGE_NAME = "enemyplane.png";// Image for the enemy plane
	private static final int IMAGE_HEIGHT = 150; // Height of the enemy plane's image
	private static final int HORIZONTAL_VELOCITY = -6;// Speed of horizontal movement
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;// X offset for projectile spawn position
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;// Y offset for projectile spawn position
	private static final int INITIAL_HEALTH = 1;// Initial health of the enemy plane
	private static final double FIRE_RATE = .01;


	/**
	 * Constructs an EnemyPlane with its initial position.
	 *
	 * @param initialXPos The initial X position of the enemy plane
	 * @param initialYPos The initial Y position of the enemy plane
	 */
	public EnemyPlane(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
	}

	/**
	 * Updates the position of the enemy plane by moving it horizontally.
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
		}
		return null;
	}

	/**
	 * Updates the enemy plane's state.
	 * <p>
	 * Currently, this updates the plane's position but can be extended for more behaviors.
	 * </p>
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

}
