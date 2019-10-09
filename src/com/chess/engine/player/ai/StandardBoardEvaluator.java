package com.chess.engine.player.ai;

import com.chess.engine.board.Board;
import com.chess.engine.pieces.Piece;
import com.chess.engine.player.Player;

/**
 * Class to evaluate a given chessboard for use in Minimax Algorithm.
 */
public final class StandardBoardEvaluator implements BoardEvaluator {

    private static final int CHECK_BONUS = 50;
    private static final int CHECK_MATE_BONUS = 10000;
    private static final int DEPTH_BONUS = 100;
    private static final int CASTLED_BONUS = 60;

    // if white has advantage, returns positive value, if black has advantage, returns negative value
    @Override
    public int evaluate(Board board, int depth) {
        return scorePlayer(board, board.whitePlayer(), depth) - scorePlayer(board, board.blackPlayer(), depth);
    }

    // method to return score of a player
    private int scorePlayer(final Board board, final Player player, final int depth) {
        return pieceValue(player) + mobility(player) +
                    check(player) + checkMate(player, depth) + castled(player);
    }

    // is player castled? apply bonus if so
    private int castled(Player player) {
        return player.isCastled() ? CASTLED_BONUS : 0;
    }

    // is player's opponent in checkmate? apply bonus to evaluate if so
    // dependent on depth. If higher up tree, bonus should be higher
    private static int checkMate(Player player, int depth) {
        return player.getOpponent().isInCheckMate() ? CHECK_MATE_BONUS * depthBonus(depth): 0;
    }

    private static int depthBonus(int depth) {
        return depth == 0 ? 1 : DEPTH_BONUS * depth;
    }

    // is player's opponent in check? apply bonus to evaluate if so
    private static int check(final Player player) {
        return player.getOpponent().isInCheck() ? CHECK_BONUS : 0;
    }

    // for given board, how many legal moves does player have?
    private static int mobility(Player player) {
        return player.getLegalMoves().size();
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
