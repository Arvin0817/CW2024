package com.example.demo.level;

import com.example.demo.model.ActiveActorDestructible;
import com.example.demo.model.plane.EnemyPlane;

/**
 * Represents the second level of the game.
 * <p>
 * This class defines the behavior and game logic for Level Two, including spawning a larger number
 * of enemies compared to Level One. The player progresses to the third level after reaching a specified kill target.
 * </p>
 *
 * <p>
 * Key features:
 * <ul>
 *     <li>Spawns up to {@code TOTAL_ENEMIES} enemies on the screen at a time.</li>
 *     <li>Tracks the player's kill count to determine when to progress to the next level.</li>
 *     <li>Uses {@link LevelView} to display the player's health and game status.</li>
 * </ul>
 * </p>
 *
 * <p>
 * This level inherits common functionality from {@link LevelParent} and overrides necessary methods
 * to provide the custom logic for Level Two.
 * </p>
 *
 * @author [Xia si zhe]
 */

public class LevelTwo extends LevelParent {

	// Background image for Level Two
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";

	// Fully qualified class name for the next level
	private static final String THIRD_LEVEL = "com.example.demo.level.LevelThree";
	// Maximum number of enemies that can be on screen at once,compare to level1, will occur more plane
	private static final int TOTAL_ENEMIES = 10;
	// Number of kills required to advance to the next level
	private static final int KILLS_TO_ADVANCE = 15;
	// Probability of spawning an enemy during each frame update
	private static final double ENEMY_SPAWN_PROBABILITY = .20;
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/**
	 * Constructs Level Two with the specified screen dimensions.
	 *
	 * @param screenHeight The height of the game screen
	 * @param screenWidth  The width of the game screen
	 */
	public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (userHasReachedKillTarget())
			goToNextLevel(THIRD_LEVEL);
	}

	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		// Spawn new enemies to maintain the total number below the limit
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
				addEnemyUnit(newEnemy);
			}
		}
	}
	/**
	 * Instantiates the Level View for Level Two.
	 * This view displays the player's health and other level-related UI elements.
	 *
	 * @return The LevelView object for this level
	 */
	@Override
	protected LevelView instantiateLevelView() {
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
	}

	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}

}
