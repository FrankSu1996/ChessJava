package com.chess.engine.pieces;

public enum PieceType {

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
