#1942 Retro shooting plane game by XIA SI ZHE 20476377

## 1.**Project Description**
This project is based on an improved version of the classic game 1942, where the player has to control the plane to avoid enemy shells and fire them to destroy enemy planes. 
The version I got had a lot of bugs and lacked a certain amount of playability, so this project was developed with fixing bugs, refactoring the code, and adding features in mind.

Main goals: 

-fix most of the visible bugs。
-improve readability and maintainability of the code. 
-Add new game features. 
-Modify and rebuild the UI of the game interface.


------------------------------------------------------------------------------

## 2.**Features(my job)**

**already finish**:
**finished but have bug**:
**want to do but not start**:



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








