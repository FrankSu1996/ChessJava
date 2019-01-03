package com.chess.engine.player;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WhitePlayer extends Player {
    //Constructor calls super so that second argument is PLAYER (this case white) moves
    public WhitePlayer(Board board,
                       final Collection<Move> whiteStandardLegalMoves,
                       final Collection<Move> blackStandardLegalMoves) {

        super(board, whiteStandardLegalMoves, blackStandardLegalMoves);

    }

    //methods to get active black pieces on board, and to get Alliance of player, opponent
    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.WHITE;
    }

    @Override
    public Player getOpponent() {
        return this.board.blackPlayer();
    }


    @Override
    protected Collection<Move> calculateKingCastles(Collection<Move> playerLegals, Collection<Move> opponentsLegals) {
        final List<Move> kingCastles = new ArrayList<>();
        //for white player's kingside castle: if players king is in first move, and is NOT in check
        if(this.playerKing.isFirstMove() && !this.isInCheck()){
            //for kingside check: if 2 tiles to the right are NOT occupied
            if(!this.board.getTile(61).isTileOccupied() &&
                    !this.board.getTile(62).isTileOccupied()){
                final Tile rookTile = this.board.getTile(63);
                //must also check if rook to the right is occupied and is first move to be
                //legal castling move
                if(rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()){
                    if(Player.calculateAttacksOnTile(61, opponentsLegals).isEmpty() &&
                            Player.calculateAttacksOnTile(62, opponentsLegals).isEmpty() &&
                            rookTile.getPiece().getPieceType().isRook()) {
                            kingCastles.add(null);
                    }
                }
            }

            //white players queenside castle move: same logic as above, but need to check 1 extra tile
            if(!this.board.getTile(59).isTileOccupied() &&
                    !this.board.getTile(58).isTileOccupied() &&
                    !this.board.getTile(57).isTileOccupied()){

                final Tile rookTile = this.board.getTile(56);
                if(rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()){
                    //todo add castle move
                    kingCastles.add(null);
                    }
                }
            }
        return ImmutableList.copyOf(kingCastles);
    }
}
