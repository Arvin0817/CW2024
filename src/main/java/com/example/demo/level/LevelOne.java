package com.example.demo.level;

import com.example.demo.controller.Controller;
import com.example.demo.model.ActiveActorDestructible;
import com.example.demo.model.plane.EnemyPlane;
import com.example.demo.model.plane.EnhancedEnemyPlane;

/**
 * LevelOne class represents the first level of the Sky Battle game.
 * <p>
 * This class handles the initialization of the first level, including setting up the background,
 * spawning enemies based on difficulty, and determining the win/lose conditions for the player.
 * </p>
 *
 * <p>
 * It extends {@link LevelParent}, inheriting common functionality for all levels.
 * </p>
 *
 * @author [XIA SI ZHE]
 */

public class LevelOne extends LevelParent {

    // Path to the background image for level one
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
    // Fully qualified name of the second level class
    private static final String SECOND_LEVEL = "com.example.demo.level.LevelTwo";
    // Maximum number of enemies allowed on the screen at one time
    private static final int TOTAL_ENEMIES = 5;
    // Number of kills required to advance to the next level
    private static final int KILLS_TO_ADVANCE = 10;
    // Probability of spawning an enemy each frame (20%)
    private static final double ENEMY_SPAWN_PROBABILITY = .20;
    // Initial health of the player's plane
    private static final int PLAYER_INITIAL_HEALTH = 5;

    /**
     * Constructs the first level with the specified screen dimensions.
     *
     * @param screenHeight The height of the screen
     * @param screenWidth  The width of the screen
     */
    public LevelOne(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
    }
    /**
     * Checks the game state to determine if the player has won or lost.
     * <p>
     * If the player's plane is destroyed, the game is lost. If the player reaches the target number of kills,
     * the game advances to the next level.
     * </p>
     */
    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        } else if (userHasReachedKillTarget())
            goToNextLevel(SECOND_LEVEL);
    }

    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }
    /**
     * Spawns enemy units on the screen based on the current difficulty setting.
     * <p>
     * The number of enemies is limited by {@code TOTAL_ENEMIES}, and their spawn probability is defined by
     * {@code ENEMY_SPAWN_PROBABILITY}. Enhanced enemies are spawned in difficult mode or occasionally in simple mode.
     * </p>
     */
    @Override
    protected void spawnEnemyUnits() {
        int currentNumberOfEnemies = getCurrentNumberOfEnemies();
        for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
            // Spawning logic based on difficulty
            if (DifficultyEnum.DIFFICULT.getDifficulty().equals(Controller.DIFFICULTY)) {
                if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                    double newEnemyInitialYPosition1 = Math.random() * getEnemyMaximumYPosition();
                    ActiveActorDestructible enhancedEnemyPlane = new EnhancedEnemyPlane(getScreenWidth(), newEnemyInitialYPosition1);
                    addEnemyUnit(enhancedEnemyPlane);
                }
            }else {
                // Spawn regular enemies and enhanced enemies in simple mode
                if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                    double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
                    ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
                    addEnemyUnit(newEnemy);
                }

                if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                    double newEnemyInitialYPosition1 = Math.random() * getEnemyMaximumYPosition();
                    ActiveActorDestructible enhancedEnemyPlane = new EnhancedEnemyPlane(getScreenWidth(), newEnemyInitialYPosition1);
                    addEnemyUnit(enhancedEnemyPlane);
                }
            }
        }
    }

    @Override
    protected LevelView instantiateLevelView() {
        return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
    }
    /**
     * Checks if the player has reached the required number of kills to advance to the next level.
     *
     * @return {@code true} if the player's kill count is greater than or equal to {@code KILLS_TO_ADVANCE},
     *         otherwise {@code false}
     */
    private boolean userHasReachedKillTarget() {
        return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
    }

}
