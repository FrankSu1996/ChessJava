
package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

import java.util.Collection;

public abstract class Piece {

    //every piece has position on board, and is either white or black
    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    //for pawns, must have first move condition
    protected final boolean isFirstMove;

    //constructor initializes each piece with position and Alliance (B/W)
    Piece(final int piecePosition, final Alliance pieceAlliance){
        this.pieceAlliance = pieceAlliance;
        this.piecePosition = piecePosition;
        //TODO more work here!!
        this.isFirstMove = false;
    }

    public int getPiecePosition(){
        return this.piecePosition;
    }

    //getter method for Alliance
    public Alliance getPieceAlliance(){
        return this.pieceAlliance;
    }

    public boolean isFirstMove(){
        return this.isFirstMove;
    }

    //abstract method to calculate legal moves: legal moves DEPEND on the piece
    public abstract Collection<Move> calculateLegalMoves(final Board board);

}
