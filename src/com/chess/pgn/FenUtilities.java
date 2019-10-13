package com.chess.pgn;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.pieces.Pawn;

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

    private static String calculateBoardText(Board board) {
        final StringBuilder builder = new StringBuilder();

        for(int i = 0; i < BoardUtils.NUM_TILES; i++) {
            final String tileText = board.getTile(i).toString();
            builder.append(tileText);
        }
        builder.insert(8, "/");
        builder.insert(17, "/");
        builder.insert(26, "/");
        builder.insert(35, "/");
        builder.insert(44, "/");
        builder.insert(53, "/");
        builder.insert(62, "/");

        return builder.toString().replaceAll("--------", "8")
                                 .replaceAll("-------", "7")
                                 .replaceAll("------", "6")
                                 .replaceAll("-----", "5")
                                 .replaceAll("----", "4")
                                 .replaceAll("---", "3")
                                 .replaceAll("--", "2")
                                 .replaceAll("-", "1");
    }

    private static String calculateEnPassantSquare(Board board) {
        final Pawn enPassantPawn = board.getEnPassantPawn();

        //get position "behind" enpassantPawn
        if(enPassantPawn != null) {
            return BoardUtils.getPositionAtCoordinate(enPassantPawn.getPiecePosition() +
                    (8) * enPassantPawn.getPieceAlliance().getOppositeDirection());
        }
        return "-";
    }

    // returns K/Q/k/q depending on current available castles on board, returns "-" if no castles
    private static String calculateCastleText(final Board board) {
        final StringBuilder builder = new StringBuilder();

        if(board.whitePlayer().isKingSideCastleCapable()) {
            builder.append("K");
        }
        if(board.whitePlayer().isQueenSideCastleCapable()) {
            builder.append("Q");
        }
        if(board.blackPlayer().isKingSideCastleCapable()) {
            builder.append("k");
        }
        if(board.blackPlayer().isQueenSideCastleCapable()) {
            builder.append("q");
        }

        final String result = builder.toString();
        return result.isEmpty() ? "-" : result;
    }

    // returns "w" if current player is white, and "b" if current player is black
    private static String calculateCurrentPlayerText(final Board board) {
        return board.currentPlayer().toString().substring(0, 1).toLowerCase();
    }
}
