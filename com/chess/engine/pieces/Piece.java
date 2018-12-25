
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

    //constructor initializes each piece with position and Alliance (B/W)
    Piece(final PieceType pieceType, final int piecePosition, final Alliance pieceAlliance){
        this.pieceType = pieceType;
        this.pieceAlliance = pieceAlliance;
        this.piecePosition = piecePosition;
        //TODO more work here!!
        this.isFirstMove = false;
    }
    //getter method for piece position
    public int getPiecePosition(){
        return this.piecePosition;
    }
    //getter method for Alliance
    public Alliance getPieceAlliance(){
        return this.pieceAlliance;
    }
    //method to determine if first move of game
    public boolean isFirstMove(){
        return this.isFirstMove;
    }
    //getter method for pieceType
    public PieceType getPieceType(){
        return this.pieceType;
    }


    //abstract method to calculate legal moves: legal moves DEPEND on the piece
    public abstract Collection<Move> calculateLegalMoves(final Board board);

    public enum PieceType{

        PAWN("P"){
            @Override
            public boolean isKing() {
                return false;
            }
        },
        KNIGHT("N") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        BISHOP("B") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        ROOK("R") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        QUEEN("Q") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        KING("K") {
            @Override
            public boolean isKing() {
                return true;
            }
        };

        private String pieceName;

        PieceType(final String pieceName){
            this.pieceName = pieceName;
        }

        @Override
        public String toString(){
            return this.pieceName;
        }

        public abstract boolean isKing();

    }

}
