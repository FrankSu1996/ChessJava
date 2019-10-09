package com.chess.engine.player.ai;

import com.chess.engine.board.Board;

/**
 * Interface for evaluation functions for the ChessBoard.
 */
public interface BoardEvaluator {

    //returns integer value representing evaluation of the board
    int evaluate(Board board, int depth);
}
