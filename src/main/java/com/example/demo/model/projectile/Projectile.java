package com.example.demo.model.projectile;

import com.example.demo.model.ActiveActorDestructible;

/**
 * Represents a generic projectile in the game.
 * <p>
 * This abstract class defines the shared properties and behavior of all projectiles,
 * such as their appearance, position, and damage handling. Specific types of projectiles
 * (e.g., EnemyProjectile, BossProjectile) should extend this class and implement their
 * unique movement behavior.
 * </p>
 *
 * <p>
 * Responsibilities include:
 * <ul>
 *     <li>Defining common properties and constructors for projectiles.</li>
 *     <li>Handling damage logic by marking the projectile as destroyed upon impact.</li>
 *     <li>Providing an abstract method for updating the position of the projectile.</li>
 * </ul>
 * </p>
 *
 * @author [Xia si zhe]
 */
public abstract class Projectile extends ActiveActorDestructible {

	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
	}

	@Override
	public void takeDamage() {
		this.destroy();
	}

	@Override
	public abstract void updatePosition();
}
