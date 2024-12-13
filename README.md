#1942 Retro shooting plane game by XIA SI ZHE 20476377

Github Link：


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

  
  

#### **Next Steps**

1. **Fix the Persistent Shield Bug**
   - The shield remains active indefinitely, making the boss invincible. Initial investigation suggests the issue lies in `Boss.java`.

2. **Enhance Player Controls**
   - Enable the player to move the aircraft in all directions (up, down, left, and right) instead of being limited to vertical movement.

3. **Optimize Boss Projectile Mechanics**
   - Improve the boss's bullet firing patterns to make gameplay more challenging and engaging.

4. **Enhance Gameplay Features**
   - Add new levels, increase game difficulty, and introduce various aircraft to enrich the game's content and replayability.




---

**Feature Additions:**

1. **Added a "Start Game" Button and Game Title via `StartController.java`**
   - This class manages the startup logic and allows for the design of a pre-game interface.
   - Utilized the `AnchorPane` container to organize the layout of the background, game title, and button.
   - Includes UI design for the game title and button.
   - Serves as the main logic before the game starts. When the user clicks the "Start Game" button, the game initializes and transitions to the first level.
   - **Related Class**: `StartController.java`

2. **Added Exception Handling and Static Constants in `Main.java`**
   - Incorporated `try-catch` blocks to handle exceptions, making it easier to identify and debug issues during development.
   - Updated the game window's `SCREEN_WIDTH` and `SCREEN_HEIGHT` to static constants, enabling easier adjustments to the game window size in future development.
  

### December 12th ---- 1.1.0 Version












### 3. **Refactoring and Changes**

-**New class**:
-**edited class**:




### 4. **Known Bugs**:
1.-Hitting the spacebar multiple times crashes the game.
  --suspected Reason: memory leak (shells not being deleted in time.)

2.-The game jumps to the second level. boss didnt appears/
  --suspected Reason: TimeLine update frequency is too high.

3.-Gameover's icon display is too big, not in proportion to the screen.
  --suspected Reason:///

4.-The game life value calculation is abnormal, even after dodging the cannonballs, the life 
  value will be deducted.
  --suspected Reason: the actual range of the cannonballs does not match the proportion of the 
  cannonball pictures, or the judgement logic is wrong: the algorithm logic is wrong when the 
  cannonballs fly out of the screen.

5.-The image format in **shieldimage class** should not be jpg

6.- it have **moveHorizontally** class, but the plane can not move left and right.



### 5. **How to Run the Project**





### 6. **Testing**








