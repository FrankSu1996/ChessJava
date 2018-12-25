package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.pieces.King;
import com.chess.engine.pieces.Piece;

import java.util.Collection;

public abstract class Player {
    //keep track of board that player is playing on, king
    protected final Board board;
    protected final King playerKing;
    protected final Collection<Move> legalMoves;

    //Constructor has board, then PLAYERS legal moves, then OPPONENTS legal moves, subclasses
    //constructor will be based on their color
    Player(final Board board, final Collection<Move> legalMoves, final Collection<Move> opponentMoves){
        this.board = board;
        this.playerKing = establishKing();
        this.legalMoves = legalMoves;
    }

    //method that determines if there is still a king in play
    //loop through active pieces, if there is king, return king piece, otherwise throw
    //runtime exception
    protected King establishKing(){
        for (final Piece piece : getActivePieces()) {
            if(piece.getPieceType().isKing()){
                return (King) piece;
            }
        }
        throw new RuntimeException("There is no King, not a valid board!");
    }
    public abstract Collection<Piece> getActivePieces();
}
