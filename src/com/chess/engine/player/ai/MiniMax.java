package com.chess.engine.player.ai;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.player.MoveTransition;

public class MiniMax implements MoveStrategy {

    private final BoardEvaluator boardEvaluator;

    public MiniMax() {
        this.boardEvaluator = null;
    }

    @Override
    public String toString() {
        return "MiniMax";
    }

    //calculates best move given a depth using minimax algorithm
    @Override
    public Move execute(Board board, int depth) {
        return null;
    }

    //helper minimizing function
    public int min(final Board board, final int depth) {
        //base case when depth is 0
        if(depth == 0 /* game over */) {
            return this.boardEvaluator.evaluate(board, depth);
        }

        //initialize lowest to max int value
        int lowest = Integer.MAX_VALUE;

        //loop through all possible moves. Update lowest with lowest evaluated board
        //after a given move
        for(final Move move : board.currentPlayer().getLegalMoves()) {
            final MoveTransition moveTransition = board.currentPlayer().makeMove(move);
            if(moveTransition.getMoveStatus().isDone()) {
                final int currentValue = max(moveTransition.getTransitionBoard(), depth - 1);
                if (currentValue <= lowest) {
                    lowest = currentValue;
                }
            }
        }

        return lowest;
    }

    //helper maximizing function
    public int max(final Board board, final int depth) {
        //base case when depth is 0
        if(depth == 0 /* game over */) {
            return this.boardEvaluator.evaluate(board, depth);
        }

        //initialize lowest to max int value
        int highest = Integer.MIN_VALUE;

        //loop through all possible moves. Update lowest with lowest evaluated board
        //after a given move
        for(final Move move : board.currentPlayer().getLegalMoves()) {
            final MoveTransition moveTransition = board.currentPlayer().makeMove(move);
            if(moveTransition.getMoveStatus().isDone()) {
                final int currentValue = min(moveTransition.getTransitionBoard(), depth - 1);
                if (currentValue >= highest) {
                    highest = currentValue;
                }
            }
        }

        return highest;
    }

}
