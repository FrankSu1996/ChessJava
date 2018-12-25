package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

//class holds info pertaining to when a move is made from one tile to another
public class MoveTransition {

    private final Board transitionBoard;
    private final Move move;
    private final MoveStatus moveStatus;

    public MoveTransition(final Board transitionBoard, final Move move, final MoveStatus moveStatus){
        this.transitionBoard = transitionBoard;
        this.move = move;
        this.moveStatus = moveStatus;
    }

    //getter for status of move
    public MoveStatus getMoveStatus(){
        return this.moveStatus;
    }
}
