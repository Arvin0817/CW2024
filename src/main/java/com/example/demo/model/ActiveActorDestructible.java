package com.example.demo.model;

/**
 * Represents an active, destructible actor in the game.
 * <p>
 * This class extends {@link ActiveActor} and implements {@link Destructible}, adding
 * the ability for objects to be destroyed. It provides a framework for actors
 * that can be damaged and removed from the game.
 * </p>
 *
 * <p>
 * Subclasses must implement the movement logic (`updatePosition`), status update logic (`updateActor`),
 * and the behavior for taking damage (`takeDamage`).
 * </p>
 *
 * <p>
 * Key features:
 * <ul>
 *     <li>Tracks destruction state of the actor using {@code isDestroyed}.</li>
 *     <li>Provides a default implementation for the `destroy` method from {@link Destructible}.</li>
 *     <li>Encourages subclasses to define their own movement and update logic.</li>
 * </ul>
 * </p>
 *
 * @author [Xia si zhe]
 */

public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	// Tracks whether the actor has been destroyed
	private boolean isDestroyed;

	/**
	 * Constructs an ActiveActorDestructible with the specified attributes.
	 *
	 * @param imageName    The name of the image representing the actor
	 * @param imageHeight  The height of the actor's image
	 * @param initialXPos  The initial X position of the actor
	 * @param initialYPos  The initial Y position of the actor
	 */
	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		isDestroyed = false;// Initialize the actor as not destroyed
	}

	/**
	 * Updates the position of the actor.
	 * <p>
	 * Subclasses must implement this method to define how the actor moves.
	 * </p>
	 */
	@Override
	public abstract void updatePosition();
	//move logic

	/**
	 * Updates the actor's status.
	 * <p>
	 * Subclasses must implement this method to define custom update logic,
	 * such as checking interactions or updating visuals.
	 * </p>
	 */
	public abstract void updateActor();
	//renew object status

	/**
	 * Handles the logic for the actor taking damage.
	 * <p>
	 * Subclasses must implement this method to define how damage affects the actor.
	 * </p>
	 */
	@Override
	public abstract void takeDamage();
	//from api:Destructible window


	/**
	 * Marks the actor as destroyed.
	 * <p>
	 * This method is called when the actor is removed from the game or
	 * when its health or other criteria reach a critical state.
	 * </p>
	 */
	@Override
	public void destroy() {
		setDestroyed(true);
	}

	/**
	 * Checks if the actor is destroyed.
	 *
	 * @return true if the actor is destroyed, false otherwise
	 */
	protected void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	public boolean isDestroyed() {
		return isDestroyed;
	}
	
}
