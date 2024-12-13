package com.example.demo.level;

/**
 * Enumeration for game difficulty levels.
 * <p>
 * This enum represents the available difficulty levels in the game: SIMPLE and DIFFICULT. Each difficulty
 * is associated with a string value that can be used for display or logic purposes.
 * </p>
 *
 *
 * @author [XIA SI ZHE 20476377]
 */

public enum DifficultyEnum {
    // Easy difficulty level
    SIMPLE("SIMPLE"),
    // Hard difficulty level
    DIFFICULT("DIFFICULT"),
    ;

    private String difficulty;
    /**
     * Constructs a DifficultyEnum with the specified difficulty string.
     *
     * @param difficulty The string value representing the difficulty level
     */

    DifficultyEnum(String difficulty) {

        this.difficulty = difficulty;
    }
    /**
     * Gets the string representation of the difficulty level.
     *
     * @return The difficulty level as a string
     */

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
