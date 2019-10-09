package com.chess.engine.player.ai;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

/**
 * Class to define what move engine will conform to
 */
public interface MoveStrategy {

    Move execute(Board board);
}
