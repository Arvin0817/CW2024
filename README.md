#1942 Retro shooting plane game by XIA SI ZHE 20476377

Github Link：https://github.com/Arvin0817/CW2024/tree/12.12

---
### 🚀 **Execution Results**
**✅ All features and fixes have been successfully implemented and are running without any issues.**
---

Explain：For each feature update, I have set up a different Branch and written a corresponding Readme.md, if you can't understand some of the features and changes, please go to the corresponding Branch to see the detailed Readme.md.

## 1.**Project Description**
This project is based on an improved version of the classic game 1942, where the player has to control the plane to avoid enemy shells and fire them to destroy enemy planes. 
The version I got had a lot of bugs and lacked a certain amount of playability, so this project was developed with fixing bugs, refactoring the code, and adding features in mind.

Main goals: 

-fix most of the visible bugs。
-improve readability and maintainability of the code. 
-Add new game features. 
-Modify and rebuild the UI of the game interface.


____________________________________________________________________________________

### December 2nd ---- 1.0.0 Version

#### **Description**
After reading the code in detail, I realised that there was a bug that prevented me from accessing the second level. In order to solve this problem, I sorted the code's parent and child classes according to their purpose and understood what each class was used for, and finally solved the problem, which was necessary before starting this assignment

---

#### **Already Finished**

1. **Added a Listener for Images**
   - Implemented a listener to monitor image paths.
   - This ensures that any path errors in future image replacements can be reported promptly, reducing debugging workload.

2. **Created a New Class for Handling Destroyed Enemy Planes**
   - Designed and implemented a new utility class to handle the removal of destroyed enemy planes.
   - **Related Class**: `Gametools.java`
          
3. **Unable to Progress to the Second Level**
   - Identified the issue as a memory leak caused by the `Timeline` in the `Boss` class not stopping.
   - Added `timeline.stop()` in `LevelParent-GoToNextLevel` to resolve the problem, allowing the game to proceed to the second level.
   -  - **Related Class**: `LevelParent.java`
---

#### **Next Steps**

- Optimize code structure and improve maintainability for long-term scalability.
- Add a button to start the game。
- Fixed the issue where the shield was not displayed properly.
- Updated the UI style of the aircraft health bar for better visual clarity.
- Resolved the issue where health was deducted even when bullets missed their target.

---

### December 10th ---- 1.0.5 Version

#### **Description**
Added ‘Start Game’ button to enhance user interaction. Optimised the scaling of the ‘Game Over’ image. Resolved the issue where shields were not displayed. Updated the health bar UI to be more intuitive. Improved the bullets fired by fighters by removing edge blurring and adding the ability to cancel bullets after a collision. In addition, some Javadoc comments were written to enhance code documentation.

---

#### **Already Finished**

**Bug Fixes:**

1. **Exception Handling in the `start` Method**
   - Added a `try-catch` block to the `start` method to handle potential exceptions.
   - Ensures stack trace information is printed to the console, making it easier to identify and debug issues.
     - **Related Class**: `main.java`

2. **Fixed the Shield Display Issue**
   - Defined `int MAX_FRAMES_WITH_SHIELD = 100` and a private `ShieldImage` in `Boss.java`. 
   - Successfully displayed the shield with the correct proportions.
     - **Related Class**: `Boss.java`

3. **Optimized Bullet Collision Logic**
   - Improved the `HandleCollision` method in `LevelParent.java` to enhance collision handling.
     - **Related Class**: `LevelParent.java`

---

**New Features:**

1. **Added Bullet Cancellation Upon Collision**
   - Implemented logic in `LevelParent.java` to cancel bullets upon collision, aligning with game logic.
     - **Related Class**: `LevelParent.java`

2. **Added a "Start Game" Button**
   - Modified the game initialization flow in `StartController.java`.
   - Bound a mouse click event to trigger the `Controller` and start the game.
     - **Related Class**: `StartController.java`

3. **Added the "SKY BATTLE" Game Title**
   - Added a game title displayed using a `Label`, with adjusted font size and layout for improved design.
     - **Related Class**: `StartController.java`

4. **Introduced a New Class: `StartController`**
   - Handles the start game page independently, managing elements like the game title and start button.
   - Switches the scene to the main game page upon trigger.
     - **Related Class**: `StartController.java`

5. **Updated Aircraft Health Bar UI**
   - Modified the health bar display logic in `HeartDisplay.java` for better visualization.
     - **Related Class**: `HeartDisplay.java`

6.**Added Exception Handling and Static Constants in `Main.java`**
   - Incorporated `try-catch` blocks to handle exceptions, making it easier to identify and debug issues during development.
   - Updated the game window's `SCREEN_WIDTH` and `SCREEN_HEIGHT` to static constants, enabling easier adjustments to the game window size in future development.

  
  

#### **Next Steps**

1. **Fix the Persistent Shield Bug**
   - The shield remains active indefinitely, making the boss invincible. Initial investigation suggests the issue lies in `Boss.java`.

2. **Enhance Player Controls**
   - Enable the player to move the aircraft in all directions (up, down, left, and right) instead of being limited to vertical movement.

3. **Optimize Boss Projectile Mechanics**
   - Improve the boss's bullet firing patterns to make gameplay more challenging and engaging.

4. **Enhance Gameplay Features**
   - Add new levels, increase game difficulty, and introduce various aircraft to enrich the game's content and replayability.

# Sky Battle - December 12th - Version 1.1.0

## **Description**
This is the final version of the game, featuring new enhancements and bug fixes:
- Added an additional level between Level One and the Boss level.
- Introduced enhanced fighter planes, allowing the player to move horizontally.
- Implemented difficulty selection for varied gameplay.
- Synchronized health between Level One and Level Two.
- Added background music and sound effects.
- Fixed the Boss shield logic and improved its firing patterns.
- Resolved the issue of players losing health when enemy projectiles leave the screen.

---

## **Already Finished**

### **Bug Fixes**
1. **Health synchronization after transitioning to the next level**
   - Addressed an issue where player health did not carry over between levels. Found a problem in `UserPlane.java` during health initialization and in `LevelTwo.java` for not successfully passing the current health.
   - **Related Classes:** `UserPlane.java`, `LevelTwo.java`

2. **Enemy projectiles deducting health when leaving the screen**
   - In `LevelParent.java`, enemy projectiles were not checked for `isDestroyed()` after leaving the screen, causing unnecessary health deduction.
   - In `EnemyProjectile.java`, ensured the `destroy()` method is called for projectiles leaving the screen.
   - **Related Classes:** `LevelParent.java`, `EnemyProjectile.java`

3. **Boss shield remaining indefinitely**
   - In `Boss.java`, the constant `MAX_FRAMES_WITH_SHIELD` was set to 500, causing the shield to persist for too long. Adjusted to 100 frames, and ensured `hideShield()` is called when the shield frame count reaches zero.
   - **Related Classes:** `Boss.java`, `LevelViewLevelTwo.java`

4. **GameOver image size too large**
   - Fixed the scaling issue in `GameOverImage.java` by modifying the overloaded constructor to set the shield size to 650x650 for proper display.
   - **Related Classes:** `GameOverImage.java`

---

### **New Features**

1. **Difficulty selection**
   - Players can now choose the game difficulty upon starting. Defaults to "Simple." Differences:
     - **Simple Mode:** Spawns a mix of regular and enhanced fighter planes.
     - **Difficult Mode:** Spawns a larger number of enhanced fighter planes.
   - Implemented in `LevelOne.java` and `LevelTwo.java` within the `spawnEnemyUnits()` method using `Math.random()` to determine enemy generation.
   - **Related Classes:** `LevelOne.java`, `LevelTwo.java`

2. **Enhanced fighter planes**
   - Added a new class `EnhancedEnemyPlane.java` with higher health (5).
   - **Related Classes:** `EnhancedEnemyPlane.java`

3. **Expanded Boss attack range and firing mechanics**
   - Added diagonal projectiles by introducing `UpBossProjectile` and `DownBossProjectile` based on `Projectile.java`.
   - The Boss now fires in three directions for more challenging gameplay.
   - **Related Classes:** `UpBossProjectile.java`, `DownBossProjectile.java`

4. **Sound effects and background music**
   - Used the Java Sound API (`AudioInputStream` and `Clip`) to add background music and hit sound effects. 
   - Implemented a separate thread to avoid blocking the main game loop. Background music loops continuously using `Clip.LOOP_CONTINUOUSLY`.
   - Encapsulated music playback logic in a static method for easy integration with other classes.
   - **Related Class:** `MusicPlayer.java`

5. **Horizontal movement for the player**
   - Enabled horizontal movement by extending the movement logic from `ActiveActor.java`. 
   - Integrated keyboard detection in `LevelParent.java`, allowing the `UserPlane` to move freely on the game screen.
   - **Related Classes:** `ActiveActor.java`, `LevelParent.java`, `UserPlane.java`

6. **New levels**
   - Introduced `LevelThree.java`, featuring a Boss battle.
   - Increased enemy count in `LevelTwo.java` (up to 10 enemies at once) compared to `LevelOne` (maximum of 5 enemies). Players must eliminate 15 enemies to proceed to the next level.
   - **Related Classes:** `LevelThree.java`, `LevelTwo.java`




---
### 🚨 **Execution Results**
**❌ The features have been implemented but are not functioning as expected.**
---
1. **Bug: The player's plane moves horizontally, but projectiles do not follow the X-axis movement**
   - The X-coordinate update logic has already been implemented in the `UserProjectile` class.
   - However, projectiles do not dynamically inherit the updated X-position of the player's plane, leading to incorrect firing positions.
---

### 🚧 **Unimplemented Features**

- **The following features were planned but could not be completed due to time constraints. These represent great opportunities for future improvement:**

1. **Add an Exit Button (Simple)**
   - A button to allow players to exit the game conveniently.

2. **Implement a Healing Mechanism**
   - Introduce a system to allow players to regain health during gameplay.

3. **Use Canvas to Create a Dynamic Background**
   - Leverage the flexibility of `Canvas` to implement visually appealing and dynamic scrolling backgrounds.

---

### **Future Work**
These features provide excellent practice opportunities and will be further improved and completed in the future.
---


## New Java Classes

1. **GameTools.java**
   - **Functionality:** Provides utilities for managing game objects, including the method `removeDestroyedActors`, which removes destroyed game objects from the scene and memory.

2. **MusicPlayer.java**
   - **Functionality:** Handles background music and sound effects for the game. It supports looping background music and playing hit sound effects.

3. **LevelThree.java**
   - **Functionality:** Replaces the original `LevelTwo`. `LevelTwo` now serves as the level following `LevelOne`, enhancing the game's playability by adding a new, challenging Boss level.

4. **UpBossProjectile.java**
   - **Functionality:** Represents projectiles fired by the Boss in an upward diagonal direction.

5. **DownBossProjectile.java**
   - **Functionality:** Represents projectiles fired by the Boss in a downward diagonal direction.

6. **EnhancedEnemyPlane.java**
   - **Functionality:** Represents an enhanced enemy fighter plane with increased health and improved capabilities compared to regular enemies.

7. **EnhancedEnemyProjectile.java**
   - **Functionality:** Represents projectiles fired by enhanced enemy planes, with improved attributes or effects.

8. **StartController.java**
   - **Functionality:** The start page controller. Responsible for initializing and displaying the start page UI elements, handling player interactions, and launching the main game logic.

9. **DifficultyEnum.java**
   - **Functionality:** Defines the difficulty levels of the game as an enum. Provides a structured way to represent and manage game difficulty levels such as `SIMPLE` and `DIFFICULT`.


## Modified Java Classes

1. **LevelParent.java**
   - **Changes:** 
     - Updated to support new levels and added mechanisms for transitioning between levels.
     - Integrated logic for managing shields and enhanced enemy behaviors.

2. **LevelOne.java**
   - **Changes:** 
     - Adjusted enemy spawn logic to differentiate between difficulties (`SIMPLE` and `DIFFICULT`).
     - Enhanced kill tracking to transition to `LevelTwo` after reaching the required kill count.

3. **LevelTwo.java**
   - **Changes:** 
     - Introduced support for enhanced enemies.
     - Increased enemy spawn rates and adjusted kill requirements for transitioning to `LevelThree`.

4. **LevelThree.java**
   - **Changes:** 
     - Added logic for managing the Boss fight, including shield activation and multiple projectile directions.
     - Updated game-over and win conditions specific to the Boss battle.

5. **ShieldImage.java**
   - **Changes:** 
     - Improved rendering of the Boss shield and added dynamic display logic for shield activation and deactivation.

6. **Controller.java**
   - **Changes:** 
     - Integrated difficulty selection functionality.
     - Refactored level-loading logic to handle transitions seamlessly across levels.

7. **Boss.java**
   - **Changes:** 
     - Adjusted movement patterns and added support for enhanced projectile mechanics.
     - Improved shield behavior to make the Boss more challenging.
     - Added new attack patterns with diagonal projectiles.

8. **UserPlane.java**
   - **Changes:** 
     - Implemented horizontal movement logic using inherited methods from `ActiveActor`.
     - Added dynamic health tracking to synchronize between levels.
     - Improved projectile firing logic to account for X-axis movement.

9. **LevelViewLevelTwo.java**
   - **Changes:** 
     - Enhanced UI elements to display the Boss shield dynamically.
     - Adjusted layout and animations for the second-level view.

10. **FighterPlane.java**
    - **Changes:** 
      - Refactored health management and damage logic to accommodate enhanced enemies.
      - Generalized firing mechanics to support subclasses like `EnhancedEnemyPlane`.

---





### 5. **How to Run the Project**





### 6. **Testing**








