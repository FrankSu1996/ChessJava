
package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

import java.util.Collection;

public abstract class Piece {

    //every piece has position on board, and is either white or black, and has an identity
    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected final PieceType pieceType;
    //for pawns, must have first move condition
    protected final boolean isFirstMove;
    private final int cachedHashCode;

    //constructor initializes each piece with position and Alliance (B/W)
    Piece(final PieceType pieceType, final int piecePosition, final Alliance pieceAlliance, final boolean isFirstMove) {
        this.pieceType = pieceType;
        this.pieceAlliance = pieceAlliance;
        this.piecePosition = piecePosition;
        this.isFirstMove = isFirstMove;
        this.cachedHashCode = computeHashCode();
    }

    private int computeHashCode() {
        int result = pieceType.hashCode();
        result = 31 * result + pieceAlliance.hashCode();
        result = 31 * result + piecePosition;
        result = 31 * result + (isFirstMove ? 1 : 0);
        return result;
    }

    //method to determine if 2 pieces are equal (overrides default equal method),
    //because we don't want to compare just the references
    @Override
    public boolean equals(final Object other){
        //if object references equal, then pieces are equal
        if(this == other){
            return true;
        }
        //if input isn't piece, not equal
        if (!(other instanceof Piece)){
            return false;
        }
        final Piece otherPiece = (Piece) other;
        //compare all member fields, if they are all equal, then objects are equal
        return piecePosition == ((Piece) other).getPiecePosition() && pieceType == otherPiece.getPieceType() &&
                pieceAlliance == otherPiece.getPieceAlliance() && isFirstMove == otherPiece.isFirstMove();
    }

    @Override
    public int hashCode(){
        return this.cachedHashCode;
    }

    //getter method for piece position
    public int getPiecePosition() {
        return this.piecePosition;
    }

    //getter method for Alliance
    public Alliance getPieceAlliance() {
        return this.pieceAlliance;
    }

    //method to determine if first move of game
    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    //getter method for pieceType
    public PieceType getPieceType() {
        return this.pieceType;
    }

    public int getPieceValue() {
        return this.pieceType.getPieceValue();
    }

    //abstract method to calculate legal moves: legal moves DEPEND on the piece
    public abstract Collection<Move> calculateLegalMoves(final Board board);

    //method makes new piece with updated piece position by applying move to current piece
    public abstract Piece movePiece(Move move);
}
