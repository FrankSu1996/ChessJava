# ChessJava- A Complete Chess Engine Implemented in Java

This is my first "large" project, in which I implemented a fully functional Chess Engine in Java. Java's AWT/Swing libraries were used
for the GUI, and the Minimax algorithm was used to implement a basic Artificial Intelligence component. 
Read more about the Minimax Algorithm [Here:](https://www.kaggle.com/uciml/red-wine-quality-cortez-et-al-2009)

# How to Play

To play the game, either downlaod the executable jar file "ChessJava.jar" in the "out/artifacts/" directory, or clone the project
and run src/com/chess/JChess.java. Upon loading, you will see the chess board gui as the following:

![alt text](https://github.com/FrankSu1996/ChessJava/tree/master/src/images/start.png)

By default, the game engine is setup for two human players. Under the options menu, there is an option to turn on highlighting of legal moves, which is strongly recommended. To move a piece, simply click on a piece icon. If legal moves highlighting is selected, it should look like the following:

![alt text](https://github.com/FrankSu1996/ChessJava/tree/master/src/images/move.png)

If you wish to cancel your move selection, simply click on the piece icon again to reset your move option. To proceed with a move, simply click on a valid move tile to finish your turn.
