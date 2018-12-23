package com.chess.engine.board;

import com.chess.engine.Alliance;
import com.chess.engine.pieces.Piece;

import java.util.Map;

public class Board {

    private Board(Builder builder){

    }


    public Tile getTile(final int tileCoordinate){
        return null;
    }

    //builder class for board
    public static class Builder{
        //Map that links tile coordinate to Piece on board
        Map<Integer, Piece> boardConfig;
        //Which color player is making the move
        Alliance nextMoveMaker;

        public Builder(){

        }

        //setter method for placing piece on board
        public Builder setPiece(final Piece piece){
            this.boardConfig.put(piece.getPiecePosition(), piece);
            return this;
        }

        //setter method for determining who has next move
        public Builder setMoveMaker(final Alliance nextMoveMaker){
            this.nextMoveMaker = nextMoveMaker;
            return this;
        }
        public Board build(){
            return new Board(this);
        }

    }


}
