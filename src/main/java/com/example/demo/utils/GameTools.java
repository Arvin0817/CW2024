package com.example.demo.utils;

import com.example.demo.model.ActiveActorDestructible;
import javafx.scene.Group;
import java.util.List;
import java.util.stream.Collectors;

public class GameTools {

    /**
     * Removes all destroyed game objects from the provided actor list and the scene's root node.
     * <p>
     * This method identifies all actors that have been marked as destroyed,
     * removes them from the root group (so they are no longer visible in the game),
     * and also removes them from the actor list (so they are no longer updated in the game loop).
     * </p>
     *
     * @param actors The list of game objects to check for destruction
     * @param root   The root node of the scene, from which destroyed actors will be removed
     */
    public static void removeDestroyedActors(List<ActiveActorDestructible> actors, Group root) {
        // Filter the list to find destroyed actors
        List<ActiveActorDestructible> destroyedActors = actors.stream()
                .filter(ActiveActorDestructible::isDestroyed)
                .collect(Collectors.toList());
        // Remove destroyed actors from the scene's root node
        root.getChildren().removeAll(destroyedActors);
        // Remove destroyed actors from the active actor list
        actors.removeAll(destroyedActors);
    }


}
