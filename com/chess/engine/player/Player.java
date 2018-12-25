package com.chess.engine.player;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.pieces.King;
import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Player {
    //keep track of board that player is playing on, king
    protected final Board board;
    protected final King playerKing;
    protected final Collection<Move> legalMoves;
    private final boolean isInCheck;

    //Constructor has board, then PLAYERS legal moves, then OPPONENTS legal moves, subclasses
    //constructor will be based on their color
    Player(final Board board, final Collection<Move> legalMoves, final Collection<Move> opponentMoves){
        this.board = board;
        this.playerKing = establishKing();
        this.legalMoves = legalMoves;
        this.isInCheck = !Player.calculateAttacksOnTile(this.playerKing.getPiecePosition(), opponentMoves).isEmpty();
    }

    //calculate valid attack moves based on current position and moves
    private static Collection<Move> calculateAttacksOnTile(int piecePosition, Collection<Move> moves) {
        final List<Move> attackMoves = new ArrayList<>();
        //loop through moves, if destination is same as piece position, is valid attack move
        for (final Move move : moves) {
            if(piecePosition == move.getDestinationCoordinate()){
                attackMoves.add(move);
            }
        }
        return ImmutableList.copyOf(attackMoves);
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

    //helper methods to determine certain conditions of game, i.e.
    //if checked, checkmated, castled, etc
    public boolean isMoveLegal(final Move move){
        return this.legalMoves.contains(move);
    }

    public boolean isInCheck(){
        return this.isInCheck;
    }
    //player is in checkmate if he/she is in check and has no escape moves
    public boolean isInCheckMate(){
        return this.isInCheck && !hasEscapeMoves();
    }
    //to determine if player has escape move during check
    protected boolean hasEscapeMoves() {
        //loop through all legal moves, perform them on "imaginary" board
        //if can make the new board successfully after applying a move (king still in play)
        //then player HAS escape move
        for (final Move move : this.legalMoves){
            final MoveTransition transition = makeMove(move);
            if(transition.getMoveStatus().isDone()){
                return true;
            }
        }
        return false;
    }
    //stalemate occurs if currently not in check, and any move you make leads you into check
    public boolean isInStaleMate(){
        return !this.isInCheck && !hasEscapeMoves();
    }
    public boolean isCastled(){
        return false;
    }

    public MoveTransition makeMove(final Move move){
        return null;
    }


    //abstract getter methods to be implemented in subclasses
    public abstract Collection<Piece> getActivePieces();
    public abstract Alliance getAlliance();
    public abstract Player getOpponent();

}
