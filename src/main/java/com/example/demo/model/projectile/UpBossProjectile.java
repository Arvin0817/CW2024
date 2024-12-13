package com.example.demo.model.projectile;

/**
 * Represents a projectile fired by the BossPlane.
 * Extends Projectile to handle movement and updates.
 */
public class UpBossProjectile extends Projectile {
    //Image Information
    private static final String IMAGE_NAME = "fireball.png";
    private static final int IMAGE_HEIGHT = 75;

    private static final int INITIAL_X_POSITION = 950;
    private static final int OFFSET = 5;

    /**
     * Constructs a BossProjectile with a given initial Y position.
     *
     * @param initialYPos The initial vertical position of the projectile.
     */
    public UpBossProjectile(double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
    }

    /**
     * Updates the bossProjectile1's position.
     */
    @Override
    public void updatePosition() {
        this.setTranslateX(getTranslateX() - OFFSET);
        this.setTranslateY(getTranslateY() - OFFSET);
    }

    @Override
    public void updateActor() {
        updatePosition();
    }
}
