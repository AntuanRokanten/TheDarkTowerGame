#The Dark Tower Game
The purpose of the game is to reach **the Dark Tower**.

## Game process
On each step the player will be presented with a number of options to pick. Player can choose an option by typing its number in console and pressing nenter.

During the journey to the final destination the player can explore the territories of **the End-World**.
You can track the location during the game.

Also many hostile enemies will be faced on the way. The character will gain experience when beating them.
But beware, some of them will be stronger than the character. 
 
You have the option to run away. However, if the enemy is fast and strong enough, the fight is enviable.
 
If the character is exhausted, you can take a rest and restore the health. 
  
## Creating a new character
While creating a character a player is able to pick its race, gender and set a name. 

#### Races
- **Human** - high vitality, good defence, average strength and speed; 
- **Taheen** - good vitality, average speed, above average strength and defence;
- **Can Toi** - very high vitality, below average strength, average defence, above average speed.
 
Selected gender will also affect character's stats.

### Assembling project

**Prerequisites**: Maven

####Assembling jar
To create a _.jar_ file, navigate to the root project directory and execute in terminal/console:

     clean compile exec:java
     
The _.jar_ file is created in target folder.

####Running test with coverage report
Navigate to the root project directory and execute in terminal/console:

     clean test
     
Coverage report created by Jacoco plugin will be created      ???