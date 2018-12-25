package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public abstract class Move {

    //need to keep track of board, piece moved, and destination tile
    final Board board;
    final Piece movedPiece;
    final int destinationCoordinate;

    //constructor
    private Move(final Board board, final Piece movedPiece, final int destinationCoordinate){
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }

    public int getDestinationCoordinate(){
        return this.destinationCoordinate;
    }

    //class for non-attacking moves
    public static final class nonAttackMove extends Move {

        public nonAttackMove(final Board board, final Piece movedPiece, final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }
    }

    //class for attacking moves
    public static final class AttackMove extends Move{
        //attacking moves includes piece that was attacked
        final Piece attackedPiece;

        public AttackMove(final Board board, final Piece movedPiece, final int destinationCoordinate, final Piece attackedPiece) {
            super(board, movedPiece, destinationCoordinate);
            this.attackedPiece = attackedPiece;
        }
    }


}
