# 12.2 task

##**-Add a picture listener, when the picture is invalid, 
  the obvious error message, reduce the workload of the later stage**

## **add a listener to check if the plane leave the screen**

## Create a new class to handle destroying destroyed enemy planes.**

___________________________________________________________________________________________________________________________________________________-

# 12.10task

##A new class was created to manage the startup logic, to make it easier to manage the game's levels later, and the game's difficulty settings.
----------------------------------------------------------------------------------------------------------------------------------------------
**Main updates**r:

### 1. Create UI elements (background, title, start button) for the start page.
### 2. Initialise the game and switch to the main game interface when the user clicks the ‘Start Button’.
----------------------------------------------------------------------------------------------------------------------------------------------
## Changes for main.java

### 1.The start method uses a try-catch block to catch the exception and print the stack information on the console.

     Advantage: adds ease of debugging, with e.printStackTrace() to see the details of the exception.

### 2.The static constants SCREEN_WIDTH and SCREEN_HEIGHT are defined and used to set the size of the window

     Advantage: Improve code readability and maintainability, subsequent changes to the window size only need to change the value of the constant.
     
