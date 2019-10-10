package com.chess.engine.player.ai;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.player.MoveTransition;

public class MiniMax implements MoveStrategy {

    private final BoardEvaluator boardEvaluator;
    private final int searchDepth;

    public MiniMax(final int searchDepth) {
        this.boardEvaluator = new StandardBoardEvaluator();
        this.searchDepth = searchDepth;
    }

    @Override
    public String toString() {
        return "MiniMax";
    }

    //calculates best move given a depth using minimax algorithm
    @Override
    public Move execute(Board board) {

        //record how long it takes to make decision
        final long startTime = System.currentTimeMillis();
        Move bestMove = null;

        int highestValue = Integer.MIN_VALUE;
        int lowestValue = Integer.MAX_VALUE;
        final int alpha = Integer.MIN_VALUE;
        final int beta = Integer.MAX_VALUE;
        int currentValue;

        System.out.println(board.currentPlayer() + " is calculating best move with depth = " + this.searchDepth);
        int numMoves = board.currentPlayer().getLegalMoves().size();

        for (final Move move : board.currentPlayer().getLegalMoves()) {

            //first execute a move
            final MoveTransition moveTransition = board.currentPlayer().makeMove(move);
            if(moveTransition.getMoveStatus().isDone()) {
                /*check alliance of next moves player. Use max/min functions accordingly
                NOTE: White by convention is maximizing player, Black is minimizing.*/
                currentValue = board.currentPlayer().getAlliance().isWhite() ?
                        min(moveTransition.getToBoard(), this.searchDepth - 1, alpha, beta) :
                        max(moveTransition.getToBoard(), this.searchDepth - 1, alpha, beta);

                // if current player is white, return highest value
                if(board.currentPlayer().getAlliance().isWhite() && currentValue >= highestValue) {
                    highestValue = currentValue;
                    bestMove = move;
                }
                // if current player is black, return lowest value
                else if (board.currentPlayer().getAlliance().isBlack() && currentValue <= lowestValue) {
                    lowestValue = currentValue;
                    bestMove = move;
                }
            }
        }
        final long executionTime = System.currentTimeMillis() - startTime;
        System.out.println("Best move calculated in " + (double)executionTime/1000 + " seconds");
        return bestMove;
    }

    //helper minimizing function
    public int min(final Board board, final int depth, int alpha, int beta) {
        //base case when depth is 0
        if(depth == 0 || isEndGameScenario(board)) {
            return this.boardEvaluator.evaluate(board, depth);
        }

        //initialize lowest to max int value
        int lowest = Integer.MAX_VALUE;

        //loop through all possible moves. Update lowest with lowest evaluated board
        //after a given move
        for(final Move move : board.currentPlayer().getLegalMoves()) {
            final MoveTransition moveTransition = board.currentPlayer().makeMove(move);
            if(moveTransition.getMoveStatus().isDone()) {
                final int currentValue = max(moveTransition.getToBoard(), depth - 1, alpha, beta);
                lowest = Math.min(lowest, currentValue);
                beta = Math.min(beta, currentValue);

                // Alpha Beta Pruning
                if (beta <= alpha) break;
            }
        }

        return lowest;
    }

    //helper maximizing function
    public int max(final Board board, final int depth, int alpha, int beta) {
        //base case when depth is 0
        if(depth == 0 || isEndGameScenario(board)) {
            return this.boardEvaluator.evaluate(board, depth);
        }

        //initialize lowest to max int value
        int highest = Integer.MIN_VALUE;

        //loop through all possible moves. Update lowest with lowest evaluated board
        //after a given move
        for(final Move move : board.currentPlayer().getLegalMoves()) {
            final MoveTransition moveTransition = board.currentPlayer().makeMove(move);
            if(moveTransition.getMoveStatus().isDone()) {
                final int currentValue = min(moveTransition.getToBoard(),depth - 1, alpha, beta);
                highest = Math.max(highest, currentValue);
                alpha = Math.max(alpha, currentValue);

                // Alpha beta pruning
                if (beta <= alpha) break;
            }
        }

        return highest;
    }

    private static boolean isEndGameScenario(final Board board) {
        return board.currentPlayer().isInCheckMate() || board.currentPlayer().isInStaleMate();
    }

}
