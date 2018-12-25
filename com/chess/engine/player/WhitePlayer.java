package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.pieces.Piece;

import java.util.Collection;

public class WhitePlayer extends Player {
    //Constructor calls super so that second argument is PLAYER (this case white) moves
    public WhitePlayer(Board board,
                       Collection<Move> whiteStandardLegalMoves,
                       Collection<Move> blackStandardLegalMoves) {

        super(board, whiteStandardLegalMoves, blackStandardLegalMoves);

    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }
}
