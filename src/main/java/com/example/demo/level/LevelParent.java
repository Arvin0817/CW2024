package com.example.demo.level;

import com.example.demo.model.ActiveActorDestructible;
import com.example.demo.model.plane.Boss;
import com.example.demo.model.plane.FighterPlane;
import com.example.demo.model.plane.UserPlane;
import com.example.demo.model.projectile.EnhancedEnemyProjectile;
import com.example.demo.utils.GameTools;
import com.example.demo.utils.MusicPlayer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Abstract parent class for game levels.
 * <p>
 * This class provides common functionality for all levels, including scene initialization,
 * background setup, enemy and friendly unit management, projectile handling, and game progression logic.
 * </p>
 *
 * <p>
 * Responsibilities include:
 * <ul>
 *     <li>Managing friendly and enemy units, including their projectiles.</li>
 *     <li>Handling game logic such as collisions and progression between levels.</li>
 *     <li>Providing an update loop for game mechanics using a {@link Timeline}.</li>
 * </ul>
 * </p>
 *
 * @author [XIA SI ZHE]
 */

public abstract class LevelParent extends Observable {
	// Constants for screen adjustment and game loop timing
	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final int MILLISECOND_DELAY = 50;

	// Screen dimensions
	private final double screenHeight;
	private final double screenWidth;
	private final double enemyMaximumYPosition;

	// JavaFX components and core game object
	private final Group root;
	private final Timeline timeline;
	private final UserPlane user;
	private final Scene scene;
	private final ImageView background;

	// Collections for managing game actor
	private final List<ActiveActorDestructible> friendlyUnits;
	private final List<ActiveActorDestructible> enemyUnits;
	private final List<ActiveActorDestructible> userProjectiles;
	private final List<ActiveActorDestructible> enemyProjectiles;

	// Current number of enemies and the level view
	private int currentNumberOfEnemies;
	private LevelView levelView;


	/**
	 * Constructs the base level with the specified parameters.
	 *
	 * @param backgroundImageName The path to the background image
	 * @param screenHeight        The height of the game screen
	 * @param screenWidth         The width of the game screen
	 * @param playerInitialHealth The initial health of the player's plane
	 */
	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
		this.user = new UserPlane(playerInitialHealth);
		this.friendlyUnits = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();

		this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.currentNumberOfEnemies = 0;
		initializeTimeline();
		friendlyUnits.add(user);
	}

	protected abstract void initializeFriendlyUnits();

	/**
	 * Checks the game state to determine if the player has won or lost.
	 * <p>
	 * Subclasses must implement this method to define the specific win/lose conditions for the level.
	 * </p>
	 */
	protected abstract void checkIfGameOver();

	protected abstract void spawnEnemyUnits();

	/**
	 * Initializes the scene by setting up the background, friendly units, and level UI.
	 *
	 * @return The initialized {@link Scene} for this level
	 */
	protected abstract LevelView instantiateLevelView();

	public Scene initializeScene() {
		initializeBackground();
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		return scene;
	}

	/**
	 * Starts the game by focusing on the background and starting the game loop.
	 */
	public void startGame() {
		background.requestFocus();
		timeline.play();
	}

	public void goToNextLevel(String levelName) {
		setChanged();
		timeline.stop();
		//stop timelime,let memory gogogo
		notifyObservers(levelName);
	}


	/**
	 * Updates the scene by spawning enemies, updating actors, handling collisions, and checking game state.
	 */
	private void updateScene() {
		spawnEnemyUnits();
		updateActors();
		generateEnemyFire();
		updateNumberOfEnemies();
		handleEnemyPenetration();
		handleUserProjectileCollisions();
		handleEnemyProjectileCollisions();
		handleProjectileCollisions();
		handlePlaneCollisions();
		removeAllDestroyedActors();
		updateKillCount();
		updateLevelView();
		checkIfGameOver();
	}


	/**
	 * Initializes the game loop using a {@link Timeline}.
	 */
	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	private void initializeBackground() {
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);

		// Keyboard controls for the player's plane
		background.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode kc = e.getCode();
				if (kc == KeyCode.UP) user.moveUp();
				if (kc == KeyCode.DOWN) user.moveDown();
				//New add move left and move right
				if (kc == KeyCode.LEFT) user.moveLeft();
				if (kc == KeyCode.RIGHT) user.moveRight();
				if (kc == KeyCode.SPACE) fireProjectile();
			}
		});
		background.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode kc = e.getCode();
				if (kc == KeyCode.UP || kc == KeyCode.DOWN) user.stop();
				if (kc == KeyCode.LEFT || kc == KeyCode.RIGHT) user.horizontalStop();
			}
		});
		root.getChildren().add(background);
	}

	private void fireProjectile() {
		ActiveActorDestructible projectile = user.fireProjectile();
		root.getChildren().add(projectile);
		userProjectiles.add(projectile);
	}


	/**
	 * Generates enemy projectiles based on their firing behavior.
	 */
	private void generateEnemyFire() {
		enemyUnits.forEach(enemy -> {
			if (enemy instanceof Boss){
				spawnEnemyProjectiles(((Boss) enemy).fireProjectiles());
			}else {
				spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile());
			}
		});
	}

	private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
		if (projectile != null) {
			root.getChildren().add(projectile);
			enemyProjectiles.add(projectile);
		}
	}

	private void spawnEnemyProjectiles(List<ActiveActorDestructible> projectiles) {
		if (projectiles!=null && projectiles.size()>0) {
			for (ActiveActorDestructible projectile : projectiles) {
				root.getChildren().add(projectile);
				enemyProjectiles.add(projectile);
			}
		}
	}

	/**
	 * Updates all game actors (friendly units, enemies, and projectiles).
	 */
	private void updateActors() {
		friendlyUnits.forEach(plane -> plane.updateActor());
		enemyUnits.forEach(enemy -> enemy.updateActor());
		userProjectiles.forEach(projectile -> projectile.updateActor());
		enemyProjectiles.forEach(projectile -> projectile.updateActor());
	}

	private void removeAllDestroyedActors() {
		GameTools.removeDestroyedActors(friendlyUnits, root);
		GameTools.removeDestroyedActors(enemyUnits, root);
		GameTools.removeDestroyedActors(userProjectiles, root);
		GameTools.removeDestroyedActors(enemyProjectiles, root);
	}


	private void handlePlaneCollisions() {
		handleCollisions(friendlyUnits, enemyUnits);
	}

	private void handleUserProjectileCollisions() {
		handleCollisions(userProjectiles, enemyUnits);
	}

	private void handleEnemyProjectileCollisions() {
		handleCollisions(enemyProjectiles, friendlyUnits);
	}

	/**
	 * Handles the cancellation of bullets when the player's bullets collide with enemy bullets.
	 * <p>
	 * This method improves the visual and logical consistency of the game by ensuring that:
	 * </p>
	 * <ul>
	 *     <li>Player bullets and enemy bullets are removed when they collide.</li>
	 *     <li>Collision logic reflects realistic interaction between projectiles.</li>
	 * </ul>
	 *
	 * <p>
	 * This behavior simulates a balanced interaction between player and enemy firepower,
	 * preventing unnecessary clutter on the screen and ensuring smooth gameplay.
	 * </p>
	 *
	 */


	private void handleProjectileCollisions() {
		handleCollisions(userProjectiles, enemyProjectiles);
	}

	private void handleCollisions(List<ActiveActorDestructible> actors1,
			List<ActiveActorDestructible> actors2) {
		for (ActiveActorDestructible actor : actors2) {
			for (ActiveActorDestructible otherActor : actors1) {
				if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
					String path = Thread.currentThread().getContextClassLoader().getResource("score.wav").getPath();
					MusicPlayer.play(path);

					// 强化子弹，威力更强
					if (actor instanceof EnhancedEnemyProjectile
							&& otherActor instanceof UserPlane){
						actor.takeDamage();
						otherActor.takeDamage();
						otherActor.takeDamage();
					}else {
						actor.takeDamage();
						otherActor.takeDamage();
					}
				}
			}
		}
	}

	private void handleEnemyPenetration() {
		for (ActiveActorDestructible enemy : enemyUnits) {
			if (enemyHasPenetratedDefenses(enemy)) {
				user.takeDamage();
				enemy.destroy();
			}
		}
	}

	private void updateLevelView() {
		levelView.removeHearts(user.getHealth());
	}

	private void updateKillCount() {
		for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
			user.incrementKillCount();
		}
	}

	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > screenWidth;
	}

	protected void winGame() {
		timeline.stop();
		levelView.showWinImage();
	}

	protected void loseGame() {
		timeline.stop();
		levelView.showGameOverImage();
	}

	protected UserPlane getUser() {
		return user;
	}

	protected Group getRoot() {
		return root;
	}

	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
	}

	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	protected double getScreenWidth() {
		return screenWidth;
	}

	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = enemyUnits.size();
	}

}
