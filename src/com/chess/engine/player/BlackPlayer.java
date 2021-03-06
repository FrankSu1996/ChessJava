package com.chess.engine.player;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Rook;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.chess.engine.board.Move.*;

public class BlackPlayer extends Player {
    //Constructor calls super so that second argument is PLAYER (this case black) moves
    public BlackPlayer(final Board board,
                       final Collection<Move> whiteStandardLegalMoves,
                       final Collection<Move> blackStandardLegalMoves) {
        super(board, blackStandardLegalMoves, whiteStandardLegalMoves);
    }

    //methods to get active black pieces on board, and to get Alliance of player
    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.BLACK;
    }
    @Override
    public Player getOpponent() {
        return this.board.whitePlayer();
    }

    @Override
    protected Collection<Move> calculateKingCastles(final Collection<Move> playerLegals,
                                                    final Collection<Move> opponentsLegals) {

        final List<Move> kingCastles = new ArrayList<>();
        //for black player's kingside castle: if players king is in first move, and is NOT in check
        if(this.playerKing.isFirstMove() && this.playerKing.getPiecePosition() == 4 && !this.isInCheck()){
            //for kingside check: if 2 tiles to the right are NOT occupied
            if(!this.board.getTile(5).isTileOccupied() && !this.board.getTile(6).isTileOccupied()){

                final Tile rookTile = this.board.getTile(7);
                final Piece kingSideRook = rookTile.getPiece();
                //must also check if rook to the right is occupied and is first move to be
                //legal castling move
                if(kingSideRook != null && kingSideRook.isFirstMove() &&
                    Player.calculateAttacksOnTile(5, opponentsLegals).isEmpty() &&
                    Player.calculateAttacksOnTile(6, opponentsLegals).isEmpty() &&
                    kingSideRook.getPieceType().isRook()){

                    if(!BoardUtils.isKingPawnTrap(this.board, this.playerKing, 12)) {
                        kingCastles.add(new KingSideCastleMove(this.board, this.playerKing, 6,
                                (Rook)kingSideRook, kingSideRook.getPiecePosition(), 5));
                    }
                }
            }
            //black players queenside castle move: same logic as above, but need to check 1 extra tile
            if(!this.board.getTile(1).isTileOccupied() &&
                    !this.board.getTile(2).isTileOccupied() &&
                    !this.board.getTile(3).isTileOccupied()){

                final Tile rookTile = this.board.getTile(0);
                final Piece queenSideRook = rookTile.getPiece();

                if(queenSideRook != null && queenSideRook.isFirstMove() &&
                    Player.calculateAttacksOnTile(2, opponentsLegals).isEmpty() &&
                    Player.calculateAttacksOnTile(3, opponentsLegals).isEmpty() &&
                    queenSideRook.getPieceType().isRook()){

                    if(!BoardUtils.isKingPawnTrap(this.board, this.playerKing, 12)) {
                        kingCastles.add(new QueenSideCastleMove(this.board, this.playerKing, 2,
                                (Rook) queenSideRook, queenSideRook.getPiecePosition(), 3));
                    }
                }
            }
        }
        return ImmutableList.copyOf(kingCastles);
    }
}
