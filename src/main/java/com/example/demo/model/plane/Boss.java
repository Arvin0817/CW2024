package com.example.demo.model.plane;

import com.example.demo.model.ActiveActorDestructible;
import com.example.demo.model.projectile.BossProjectile;
import com.example.demo.model.projectile.DownBossProjectile;
import com.example.demo.model.projectile.UpBossProjectile;
import com.example.demo.view.ShieldImage;
import javafx.scene.image.ImageView;

import java.util.*;

/**
 * Represents the Boss enemy in the game.
 * <p>
 * The Boss features unique abilities, including a shield and patterned vertical movement.
 * It can fire multiple types of projectiles and has significantly more health than standard enemies.
 * </p>
 *
 * <p>
 * Key features:
 * <ul>
 *     <li>Randomized vertical movement within specified bounds.</li>
 *     <li>Shield mechanics to temporarily block damage.</li>
 *     <li>Multi-directional projectile firing.</li>
 * </ul>
 * </p>
 *
 * <p>
 * This class extends {@link FighterPlane} to inherit basic movement and damage handling.
 * </p>
 *
 * @author [Xia si zhe]
 */

public class Boss extends FighterPlane {

	// Constants for Boss attributes

	// Image for the Boss
	private static final String IMAGE_NAME = "bossplane.png";
	// Starting X position
	private static final double INITIAL_X_POSITION = 1000.0;
	// Starting Y position
	private static final double INITIAL_Y_POSITION = 400;
	// Offset for projectiles
	private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;
	// Probability of firing in each frame
	private static final double BOSS_FIRE_RATE = .04;
	// Probability of shield activation
	private static final double BOSS_SHIELD_PROBABILITY = .01;
	// Height of the Boss image
	private static final int IMAGE_HEIGHT = 300;
	// Vertical movement speed
	private static final int VERTICAL_VELOCITY = 8;
	// Boss health
	private static final int HEALTH = 100;
	// Number of moves in a cycle
	private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
	// Constant for no movement
	private static final int ZERO = 0;
	// Max frames before move direction changes
	private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
	// Upper boundary for movement
	private static final int Y_POSITION_UPPER_BOUND = -100;
	// Lower boundary for movement
	private static final int Y_POSITION_LOWER_BOUND = 475;
	// Max frames shield can remain active
	private static final int MAX_FRAMES_WITH_SHIELD = 100;
	private final List<Integer> movePattern;
	private boolean isShielded;
	private int consecutiveMovesInSameDirection;
	private int indexOfCurrentMove;
	private int framesWithShieldActivated;
	private ShieldImage shieldImage;//import shieldimage;

	// Shield position offsets relative to the Boss
	private final int SHIELD_POSITION_X_OFFSET = -30;
	private final int SHIELD_POSITION_Y_OFFSET = 50;



	/**
	 * Constructs a Boss object with its initial attributes and behaviors.
	 */
	public Boss() {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
		movePattern = new ArrayList<>();
		consecutiveMovesInSameDirection = 0;
		indexOfCurrentMove = 0;
		framesWithShieldActivated = 0;
		isShielded = false;
		//new add
		// Initialize shield image with offsets
		shieldImage = new ShieldImage(this.getLayoutX() + this.getTranslateX() + SHIELD_POSITION_X_OFFSET,
				this.getLayoutY() + this.getTranslateY() + SHIELD_POSITION_Y_OFFSET);
		// Generate randomized movement pattern
		initializeMovePattern();
	}

	/**
	 * Updates the Boss's position based on its movement pattern and ensures it stays within bounds.
	 */
	@Override
	public void updatePosition() {
		double initialTranslateY = getTranslateY();
		moveVertically(getNextMove());

		// Ensure the Boss stays within vertical bounds
		double currentPosition = getLayoutY() + getTranslateY();
		if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
			setTranslateY(initialTranslateY);
		}
		// Update the shield's position to follow the Boss
		shieldImage.setLayoutY(this.getLayoutY() + this.getTranslateY() + SHIELD_POSITION_Y_OFFSET);
	}


	/**
	 * Updates the Boss's state, including position and shield mechanics.
	 */
	@Override
	public void updateActor() {
		updatePosition();
		updateShield();
	}


	/**
	 * Fires three projectiles: upward, straight, and downward.
	 * This is upgrade for boss attack mode
	 * @return A list of projectiles fired by the Boss
	 */
	public List<ActiveActorDestructible> fireProjectiles() {
		List<ActiveActorDestructible> list = new ArrayList<>();
		if (bossFiresInCurrentFrame()) {
			list.add(new UpBossProjectile(getProjectileInitialPosition()));
			list.add(new BossProjectile(getProjectileInitialPosition()));
			list.add(new DownBossProjectile(getProjectileInitialPosition()));
		}
		return list;
	}

	@Override
	public ActiveActorDestructible fireProjectile() {
		return null;
	}


	/**
	 * Reduces the Boss's health if it is not shielded.
	 */
	@Override
	public void takeDamage() {
		if (!isShielded) {
			super.takeDamage();
		}
	}



	/**
	 * Initializes the Boss's vertical movement pattern and shuffles it.
	 */
	private void initializeMovePattern() {
		for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
			movePattern.add(VERTICAL_VELOCITY);
			movePattern.add(-VERTICAL_VELOCITY);
			movePattern.add(ZERO);
		}
		Collections.shuffle(movePattern);
	}

	/**
	 * Updates the shield's state, activating or deactivating it as needed.
	 */
	private void updateShield() {
		if (isShielded) {
			framesWithShieldActivated++;
			//new add
			shieldImage.showShield();// Show shield when active
		}
		//new add{ }
		else if (shieldShouldBeActivated()){
			activateShield();
		}

		// Deactivate shield after max duration
		if (shieldExhausted()) {deactivateShield();
		}
	}

	/**
	 * Gets the next vertical move based on the movement pattern.
	 *
	 * @return The vertical velocity for the next move
	 */
	private int getNextMove() {
		int currentMove = movePattern.get(indexOfCurrentMove);
		consecutiveMovesInSameDirection++;
		if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
			Collections.shuffle(movePattern);
			consecutiveMovesInSameDirection = 0;
			indexOfCurrentMove++;
		}
		// Reset to the start of the pattern if the end is reached
		if (indexOfCurrentMove == movePattern.size()) {
			indexOfCurrentMove = 0;
		}
		return currentMove;
	}

	/**
	 * Checks if the Boss should fire projectiles in the current frame.
	 *
	 * @return true if the Boss should fire, false otherwise
	 */
	private boolean bossFiresInCurrentFrame() {
		return Math.random() < BOSS_FIRE_RATE;
	}

	/**
	 * Gets the initial Y position for projectiles.
	 *
	 * @return The Y position offset for projectiles
	 */
	private double getProjectileInitialPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}

	/**
	 * Checks if the shield should be activated based on probability.
	 *
	 * @return true if the shield should activate, false otherwise
	 */
	private boolean shieldShouldBeActivated() {
		return Math.random() < BOSS_SHIELD_PROBABILITY;
	}

	/**
	 * Checks if the shield has been active for its maximum duration.
	 *
	 * @return true if the shield is exhausted, false otherwise
	 */
	private boolean shieldExhausted() {
		return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
	}

	/**
	 * Activates the Boss's shield.
	 */
	private void activateShield() {
		isShielded = true;
		shieldImage.showShield();
		//new add
	}

	/**
	 * Deactivates the Boss's shield.
	 */
	private void deactivateShield() {
		isShielded = false;
		framesWithShieldActivated = 0;
		shieldImage.hideShield();
	}

	/**
	 * 获取boss的盾牌
	 * @return boss的盾牌
	 */
	public ImageView getShield() {
		return this.shieldImage;
	}
}
