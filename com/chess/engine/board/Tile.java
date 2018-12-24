package com.chess.engine.board;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public abstract class Tile {


    protected final int tileCoordinate;

    private static final Map<Integer, EmptyTile> EMPTY_TILES = createAllPossibleEmptyTiles();

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

        for (int i = 0; i < 64; i++){
            emptyTileMap.put(i, new EmptyTile(i));
        }

        return ImmutableMap.copyOf(emptyTileMap); //so cannot change once emptytilemap is created
    }

    //public factory method for user to create tile
    public static Tile createTile(final int tileCoordinate, final Piece piece){
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES.get(tileCoordinate);
    }



    private Tile(final int tileCoordinate){
        this.tileCoordinate = tileCoordinate;
    }

    //determines if tile is occupied or empty
    public abstract boolean isTileOccupied();

    //returns piece from occupied tile, or NULL from empty tile
    public abstract Piece getPiece();

    //subclass for Empty tiles
    public static final class EmptyTile extends Tile{

        private EmptyTile(final int coordinate){
            super(coordinate);
        }

        //toString for empty tile returns '-'
        @Override
        public String toString(){
            return "-";
        }

        //overwritten method: empty tiles are not occupied
        @Override
        public boolean isTileOccupied(){
            return false;
        }

        //overwritten method: empty tiles don't return a piece
        @Override
        public Piece getPiece(){
            return null;
        }

    }

    //subclass for occupied tile
    public static final class OccupiedTile extends Tile{
        //occupied tiles have piece on them
        private final Piece pieceOnTile;

        //constructor takes coordinate AND piece
        private OccupiedTile(int coordinate, Piece pieceOnTile){
            super(coordinate);
            this.pieceOnTile = pieceOnTile;
        }

        //toString for non-empty tile prints out piece on tile. Black prints in lower case
        //white prints in upper case
        @Override
        public String toString(){
            return getPiece().getPieceAlliance().isBlack() ? getPiece().toString().toLowerCase() :
                    getPiece().toString();
        }

        //overwritten method: occupied tiles are occupied
        @Override
        public boolean isTileOccupied(){
            return true;
        }

        //overwritten method: occupied tiles return a piece
        @Override
        public Piece getPiece(){
            return this.pieceOnTile;
        }

    }
}
