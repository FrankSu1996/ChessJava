package com.tests.chess.engine.board;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.pieces.Piece;
import com.chess.engine.player.MoveTransition;
import com.chess.engine.player.ai.MiniMax;
import com.chess.engine.player.ai.MoveStrategy;
import com.google.common.collect.Iterables;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    public void initialBoard() {
        final Board board = Board.createStandardBoard();
        assertEquals(board.currentPlayer().getLegalMoves().size(), 20);
        assertEquals(board.currentPlayer().getOpponent().getLegalMoves().size(), 20);
        assertFalse(board.currentPlayer().isInCheck());
        assertFalse(board.currentPlayer().isInCheckMate());
        assertFalse(board.currentPlayer().isCastled());
        assertEquals(board.currentPlayer(), board.whitePlayer());
        assertEquals(board.currentPlayer().getOpponent(), board.blackPlayer());
        assertFalse(board.currentPlayer().getOpponent().isInCheck());
        assertFalse(board.currentPlayer().getOpponent().isInCheckMate());
        assertFalse(board.currentPlayer().getOpponent().isCastled());
    }

    // test AI can follow foolsmate moves if setup correctly
    @Test
    public void testFoolsMate() {
        final Board board = Board.createStandardBoard();
        final MoveTransition t1 = board.currentPlayer()
                .makeMove(Move.MoveFactory.createMove(board, BoardUtils.getCoordinateAtPosition("f2"),
                        BoardUtils.getCoordinateAtPosition("f3")));
        assertTrue(t1.getMoveStatus().isDone());

        final MoveTransition t2 = t1.getTransitionBoard().currentPlayer()
                .makeMove(Move.MoveFactory.createMove(t1.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("e7"),
                        BoardUtils.getCoordinateAtPosition("e5")));
        assertTrue(t2.getMoveStatus().isDone());

        final MoveTransition t3 = t2.getTransitionBoard().currentPlayer()
                .makeMove(Move.MoveFactory.createMove(t2.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("g2"),
                        BoardUtils.getCoordinateAtPosition("g4")));
        assertTrue(t3.getMoveStatus().isDone());

        // create Minimax ai and make "best move"
        final MoveStrategy strategy = new MiniMax(10);
        final Move aiMove = strategy.execute(t3.getTransitionBoard());

        // "manually" create the best move: should be a checkmate
        final Move bestMove = Move.MoveFactory.createMove(t3.getTransitionBoard(), BoardUtils.getCoordinateAtPosition("d8"),
                BoardUtils.getCoordinateAtPosition("h4"));

        assertEquals(aiMove, bestMove);
    }
}