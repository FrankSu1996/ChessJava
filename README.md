# ChessJava- A Complete Chess Engine Implemented in Java

This is my first "large" project, in which I implemented a fully functional Chess Engine in Java. Java's AWT/Swing libraries were used
for the GUI, and the Minimax algorithm was used to implement a basic Artificial Intelligence component. 
Read more about the Minimax Algorithm [Here:](https://www.kaggle.com/uciml/red-wine-quality-cortez-et-al-2009)

# How to Play

To play the game, either downlaod the executable jar file "ChessJava.jar" in the "out/artifacts/" directory, or clone the project
and run src/com/chess/JChess.java. Upon loading, you will see the chess board gui as the following:

![alt text](https://github.com/FrankSu1996/ChessJava/blob/master/src/images)

By default, the game engine is setup for two human players. Under the options menu, there is an option to turn on highlighting of legal moves, which is strongly recommended. To move a piece, simply click on a piece icon. If legal moves highlighting is selected, it should look like the following:

![alt text](https://github.com/FrankSu1996/ChessJava/tree/master/src/images)

If you wish to cancel your move selection, simply click on the piece icon again to reset your move option. To proceed with a move, simply click on a valid move tile to finish your turn.

On the right hand of the board, there is a panel that will keep track of all the moves made during the game in standard PGN notation, and on the left hand side there is a panel that will show all of the taken pieces throughout the game. 

Finally, to set up the AI Computer, click on options -> Setup Game. This will bring up a window where you can select which player is human and which player is computer. Have Fun!!
