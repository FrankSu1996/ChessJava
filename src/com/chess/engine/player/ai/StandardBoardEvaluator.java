package com.chess.engine.player.ai;

import com.chess.engine.board.Board;
import com.chess.engine.pieces.Piece;
import com.chess.engine.player.Player;

/**
 * Class to evaluate a given chessboard for use in Minimax Algorithm. Based only off of cumulative
 * value of remaining pieces for a given player
 */
public final class StandardBoardEvaluator implements BoardEvaluator {

    // if white has advantage, returns positive value, if black has advantage, returns negative value
    @Override
    public int evaluate(Board board, int depth) {
        return scorePlayer(board, board.whitePlayer(), depth) - scorePlayer(board, board.blackPlayer(), depth);
    }

    // method to return score of a player
    private int scorePlayer(final Board board, final Player player, final int depth) {
        return pieceValue(player);
    }

    // method to sum up value of all remaining pieces on board
    private static int pieceValue(final Player player) {
        int pieceValueScore = 0;
        for(final Piece piece : player.getActivePieces()) {
            pieceValueScore += piece.getPieceValue();
        }
        return pieceValueScore;
    }
}
