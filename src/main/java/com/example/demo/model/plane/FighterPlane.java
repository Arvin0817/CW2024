package com.example.demo.model.plane;

import com.example.demo.model.ActiveActorDestructible;

/**
 * Abstract base class for all fighter planes in the game.
 * <p>
 * This class provides common functionality for planes, such as handling health,
 * firing projectiles, and managing destruction when health reaches zero.
 * Subclasses are expected to implement specific projectile firing behavior.
 * </p>
 *
 * <p>
 * Key features:
 * <ul>
 *     <li>Manages health and destruction behavior.</li>
 *     <li>Calculates precise projectile positions based on the plane's location.</li>
 *     <li>Defines an abstract method {@code fireProjectile()} for subclasses to implement.</li>
 * </ul>
 * </p>
 *
 * <p>
 * This class extends {@link ActiveActorDestructible}, inheriting its core actor functionality.
 * </p>
 *
 * @author [Xia si zhe]
 */

public abstract class FighterPlane extends ActiveActorDestructible {

	// Health of the fighter plane
	private int health;

	/**
	 * Constructs a FighterPlane with the specified attributes.
	 *
	 * @param imageName    The name of the image representing the plane
	 * @param imageHeight  The height of the plane's image
	 * @param initialXPos  The initial X position of the plane
	 * @param initialYPos  The initial Y position of the plane
	 * @param health       The initial health of the plane
	 */
	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.health = health;
	}

	/**
	 * Abstract method for firing projectiles.
	 * <p>
	 * Subclasses must implement this method to define how the plane fires projectiles.
	 * </p>
	 *
	 * @return A projectile instance or null if no projectile is fired
	 */
	public abstract ActiveActorDestructible fireProjectile();


	/**
	 * Reduces the plane's health by 1 when it takes damage.
	 * <p>
	 * If the health reaches zero, the plane is destroyed.
	 * </p>
	 */
	@Override
	public void takeDamage() {
		health--;
		if (healthAtZero()) {// Check if health is zero
			this.destroy();// Destroy the plane
		}
	}

	/**
	 * Calculates the X position for a projectile based on an offset.
	 *
	 * @param xPositionOffset The X position offset relative to the plane's current position
	 * @return The X coordinate for the projectile's initial position
	 */
	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	/**
	 * Checks if the plane's health is zero.
	 *
	 * @return true if the health is zero, false otherwise
	 */
	public boolean healthAtZero() {
		return health == 0;
	}

	public int getHealth() {
		return health;
	}
		
}
