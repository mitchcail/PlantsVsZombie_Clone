# SYSC 3110 Project â€“ Plants vs Zombies: the Puzzle!
Ian Wong
John Blackwood
Mitch Cail
Nikola Neskovic

## Stats and authorship
Authorship in Java classes is marked by @author tags, though the tags themselves don't really indicate who authored what - a much better representation of which project member wrote which part of the program can be found by using git blame for any particular file - available through the gitlab interface as an easy-to-use GUI. The git blame tool shows the authorship of each line (or block of lines) in a source file.
Stats: http://gl.xnor.ca/carleton/sysc3110-project/graphs/master

## Goal
Refer to README_GOAL, removed from this readme for the sake of clarity.

## Compiling
This program uses maven to handle dependencies, to compile the application run
mvn compile

To run the tests run
mvn test

to build and run the package run
mvn package
java -jar arget/plantsvzombies-0.0.1-SNAPSHOT.jar


## Known Bugs
+ If you click the buttons that the zombie or shot panels are over the portion of those panels that is over the clicked button will disappear under the button.
Theory is that the layered pane re-prioritizes the button to go over the panels when it is clicked. However this bug isn't too serious because the user generally shouldn't be pressing the buttons beneath to zombies to place a plant there because the plant would get immediately overrun. Also, the zombie/shot panels are refreshed on the next turn anyway so it's not a persisting error.

+ You can plant plants overtop of other plants, so essentially you can have two shooter plants occupying the same 2 dimensional space.

+ If you redo to a point where zombies backtrack to BEFORE the turn where they spawn their image will persist until the first (or newest) zombie wave spawns. The newest zombie wave may spawn in a different random location.

+ When you place a plant the plant does not become visible until the you end your turn. 

## ** Major Changes Since Milestone 3 **
+Added static integer to BoardElement to help us keep track of the number of each newly created board element (plant, shot, zombie). This value is used to generate a uniqe ID number for each new element and this ID is later used in GameWindow (the view) to act as a key in a hashmap to uniquely identify which zombie or shot panel to manipulate (move, remove, etc).

+Added a second level called LevelTheSecond. This level features three available rows and two new enemies known as the rapid ghoul which is essentially a fast moving zombie, and the lumbering brute which is a slow moving tank like zombie. 

+Added drawings for all of the plants and zombies

+Added new plants called Potato. Essentially a heavy duty wall that can absorb much punishment from the zombies. Potatoes have an upkeep cost of -5 points.
So if you place a potato as your first plant you will mostly definitely lose because you won't have anymore income.

+Added equals and hashCode implementation to BoardElement. HashCode implementation serves as more of a contingency because it is not immediately used in our program but we understand that if you overwrite equals it is a good idea to overwrite hashcode as well.

+Added Undo/Redo functionality to undo and redo turns. Undo will undo the game all the way until the beginning of the game, before the first move. The redo will redo the most recent undo.


