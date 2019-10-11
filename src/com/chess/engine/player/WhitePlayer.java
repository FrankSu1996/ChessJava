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
    protected Collection<Move> calculateKingCastles(final Collection<Move> playerLegals,
                                                    final Collection<Move> opponentsLegals) {
        final List<Move> kingCastles = new ArrayList<>();

        //for white player's kingside castle: if players king is in first move, and is NOT in check
        if(this.playerKing.isFirstMove() && this.playerKing.getPiecePosition() == 60 && !this.isInCheck()){
            //for kingside check: if 2 tiles to the right are NOT occupied
            if(!this.board.getTile(61).isTileOccupied() &&
                    !this.board.getTile(62).isTileOccupied()){

                final Tile rookTile = this.board.getTile(63);
                final Piece kingSideRook = rookTile.getPiece();

                //must also check if rook to the right is occupied and is first move to be
                //legal castling move
                if(kingSideRook != null && kingSideRook.isFirstMove()){
                    if(Player.calculateAttacksOnTile(61, opponentsLegals).isEmpty() &&
                            Player.calculateAttacksOnTile(62, opponentsLegals).isEmpty() &&
                            kingSideRook.getPieceType().isRook()) {

                        if(!BoardUtils.isKingPawnTrap(this.board, this.playerKing, 52)) {
                            kingCastles.add(new KingSideCastleMove(this.board, this.playerKing, 62, (Rook) kingSideRook, kingSideRook.getPiecePosition(), 61));
                        }
                    }
                }
            }
            //white players queenside castle move: same logic as above, but need to check 1 extra tile
            if(!this.board.getTile(59).isTileOccupied() &&
                    !this.board.getTile(58).isTileOccupied() &&
                    !this.board.getTile(57).isTileOccupied()){

                //get piece from rooktile
                final Tile rookTile = this.board.getTile(56);
                final Piece queenSideRook = rookTile.getPiece();

                if(queenSideRook != null && queenSideRook.isFirstMove() &&
                    Player.calculateAttacksOnTile(58, opponentsLegals).isEmpty() &&
                    Player.calculateAttacksOnTile(59, opponentsLegals).isEmpty() &&
                    queenSideRook.getPieceType().isRook()){

                    if(!BoardUtils.isKingPawnTrap(this.board, this.playerKing, 52)) {
                        kingCastles.add(new QueenSideCastleMove(this.board, this.playerKing, 58, (Rook) queenSideRook, queenSideRook.getPiecePosition(), 59));
                    }
                    }
                }
            }
        return ImmutableList.copyOf(kingCastles);
    }
}
