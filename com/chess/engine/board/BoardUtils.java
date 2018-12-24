package com.chess.engine.board;

public class BoardUtils {

    //members determine if given position is in certain column
    public static final boolean[] FIRST_COLUMN = initializeColumn(0);
    public static final boolean[] SECOND_COLUMN = initializeColumn(1);
    public static final boolean[] SEVENTH_COLUMN = initializeColumn(6);
    public static final boolean[] EIGHTH_COLUMN = initializeColumn(7);

    //members determine if given position is in certain row
    public static final boolean[] SECOND_ROW = initRow(8);
    public static final boolean[] SEVENTH_ROW = initRow(48);

    public static final int NUM_TILES = 64;
    public static final int TILES_PER_ROW = 8;

    private BoardUtils(){
        throw new RuntimeException("You cannot instantiate BoardUtils!");
    }

    //method to initialize columns of the board
    private static boolean[] initializeColumn(int columnNumber) {
        final boolean[] column = new boolean[NUM_TILES];
        //initialize column and column * multiples of 8 to true
        do{
            column[columnNumber] = true;
            columnNumber+= TILES_PER_ROW;
        } while(columnNumber < NUM_TILES);
        return column;
    }

    //method to initialize rows of the board
    private static boolean[] initRow(int rowNumber){
        final boolean[] row = new boolean[NUM_TILES];
        do {
            row[rowNumber] = true;
            rowNumber++;
        } while(rowNumber % 8 != 0);
        return row;
    }


    //method to determine if tile is valid (coordinate between 0 and 63)
    public static boolean isValidTileCoordinate(int coordinate) {
        return (coordinate >=0 && coordinate < NUM_TILES);
    }
}
