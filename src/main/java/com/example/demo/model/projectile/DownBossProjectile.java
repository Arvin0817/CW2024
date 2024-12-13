package com.example.demo.model.projectile;

/**
 * Represents a downward-moving projectile fired by the Boss in the game.
 * <p>
 * This class defines the specific properties and behavior of a DownBossProjectile,
 * including its appearance, initial position, and movement logic.
 * </p>
 *
 * <p>
 * Responsibilities include:
 * <ul>
 *     <li>Initializing the projectile with specific dimensions and image.</li>
 *     <li>Updating the position of the projectile by moving it diagonally downwards.</li>
 * </ul>
 * </p>
 *
 * @author [XIA SI ZHE]
 */
public class DownBossProjectile extends Projectile {
    //Image Information
    private static final String IMAGE_NAME = "fireball.png";
   // Height of the projectile's image
    private static final int IMAGE_HEIGHT = 75;

    private static final int INITIAL_X_POSITION = 950;
    private static final int OFFSET = 5;

    public DownBossProjectile(double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
    }

    /**
     * Updates the position of the projectile.
     * <p>
     * Moves the projectile diagonally downwards by adjusting both
     * its X and Y coordinates based on a fixed offset.
     * </p>
     */
    @Override
    public void updatePosition() {
        this.setTranslateX(getTranslateX() - OFFSET);
        this.setTranslateY(getTranslateY() + OFFSET);
    }

    @Override
    public void updateActor() {
        updatePosition();
    }
}
