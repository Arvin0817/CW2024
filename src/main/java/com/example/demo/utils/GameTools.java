package com.example.demo.utils;

import com.example.demo.model.ActiveActorDestructible;
import javafx.scene.Group;
import java.util.List;
import java.util.stream.Collectors;

public class GameTools {

    /**
     * Removes all destroyed game objects.
     *
     * @param actors Game Object List
     * @param root   root node of scenes
     */
    public static void removeDestroyedActors(List<ActiveActorDestructible> actors, Group root) {
        List<ActiveActorDestructible> destroyedActors = actors.stream()
                .filter(ActiveActorDestructible::isDestroyed) // Filtering of destroyed objects
                .collect(Collectors.toList());
        root.getChildren().removeAll(destroyedActors); // Remove from the scenes
        actors.removeAll(destroyedActors); // Romove from the list
    }


}
