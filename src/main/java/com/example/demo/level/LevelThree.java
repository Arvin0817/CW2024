package com.example.demo.level;

import com.example.demo.model.plane.Boss;

/**
 * Represents the third level of the game, featuring a Boss battle.
 * <p>
 * This class defines the unique behaviors and game logic for Level Three, including the appearance
 * of a Boss enemy with a shield. The player wins by defeating the Boss, and loses if their plane is destroyed.
 * </p>
 *
 * <p>
 * Key features:
 * <ul>
 *     <li>Tracks the player's health and Boss destruction status to determine game outcomes.</li>
 *     <li>Implements specific logic for spawning the Boss and updating the Level View.</li>
 * </ul>
 * </p>
 *
 * <p>
 * This level inherits common functionality from {@link LevelParent} and overrides necessary methods
 * to provide the custom logic for Level Three.
 * </p>
 *
 * @author [XIA SI ZHE]
 */


public class LevelThree extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Boss boss;
	private LevelViewLevelTwo levelView;

	public LevelThree(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		boss = new Boss();
	}

	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (boss.isDestroyed()) {
			winGame();
		}
	}

	/**
	 * Add boss and shieldd
	 */
	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
			getRoot().getChildren().add(boss.getShield());
		}
	}

	@Override
	protected LevelView instantiateLevelView() {
		levelView = new LevelViewLevelTwo(getRoot(), getUser().getHealth());
		return levelView;
	}

}
