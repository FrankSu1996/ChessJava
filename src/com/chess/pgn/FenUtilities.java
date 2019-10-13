package com.chess.pgn;

import com.chess.engine.board.Board;

public class FenUtilities {

    private FenUtilities() {
        throw new RuntimeException("Not Instantiable!");
    }

    /**
     * Method to create Board from an input fen string
     * @param fenString input FEN format string
     * @return a Board representing the state of FEN string
     */
    public static Board createGameFromFEN(final String fenString) {
        return null;
    }

    /**
     * Method to create FEN format string from current board. NOTE: always returns 0 1 for time because engine
     * does not support any clocking methods
     * @param board the current board in play
     * @return a String representing FEN format of board
     */
    public static String createFENfromGame(final Board board) {
        return calculateBoardText(board) + " " +
                calculateCurrentPlayerText(board) + " " +
                calculateCastleText(board) + " " +
                calculateEnPassantSquare(board) + " " +
                "0 1";
    }

    // returns "w" if current player is white, and "b" if current player is black
    private static Object calculateCurrentPlayerText(final Board board) {
        return board.currentPlayer().toString().substring(0, 1).toLowerCase();
    }
}
