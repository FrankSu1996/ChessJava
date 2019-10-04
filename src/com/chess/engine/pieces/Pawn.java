package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.chess.engine.board.Move.*;

public class Pawn extends Piece {

    //list of possible offsets
    private final static int[] CANDIDATE_MOVE_COORDINATE = {7, 8, 9, 16};

    //"convenience constructor", dummy true value for first move
    public Pawn(final Alliance pieceAlliance, final int piecePosition) {
        super(PieceType.PAWN, piecePosition, pieceAlliance, true);
    }

    //constructor that takes in actual isFirstMove argument
    public Pawn(final Alliance pieceAlliance, final int piecePosition, final boolean isFirstMove) {
        super(PieceType.PAWN, piecePosition, pieceAlliance, isFirstMove);
    }

    //method to calculate all the legal moves
    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

        final List<Move> legalMoves= new ArrayList<>();

        //list through all the possible moves
        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE){
            //get direction for Pawn depending on Alliance (black/white), multiply by current offset to get destination
            final int candidateDestinationCoordinate = this.piecePosition + (this.pieceAlliance.getDirection() * currentCandidateOffset);

            //check if destination tile is valid
            if(!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                continue;
            }

            //if tile is isn't occupied, add non-attacking move (1 square up/down)
            if(currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                //todo more work to do here (deal with promotions)!!!!!
                legalMoves.add(new nonAttackMove(board, this, candidateDestinationCoordinate));
            }
            //pawn "jump" move: 2 squares at first move of game
            else if (currentCandidateOffset == 16 && this.isFirstMove() &&
                    ((BoardUtils.SEVENTH_RANK[this.piecePosition] && this.getPieceAlliance().isBlack()) ||
                    (BoardUtils.SECOND_RANK[this.piecePosition] && this.getPieceAlliance().isWhite()))) {
                //possible coordinate where there is enemy behind
                final int behindCandidateDestinationCoordinate = this.piecePosition + (this.pieceAlliance.getDirection() * 8);
                //if the tile behind destination AND destination isn't occupied, add new non attacking "jump" move
                if(!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() &&
                        !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                        legalMoves.add(new PawnJump(board, this, candidateDestinationCoordinate));

                }
            }
            //attacking move: condition when offset is 7 and doesn't fall into exclusion columns
            else if (currentCandidateOffset == 7 &&
                    !((BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite() ||
                    (BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack())))){
                //if tile is occupied by enemy, get the piece on tile
                if(board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    //if alliances between pieces don't match, add attacking move
                    if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()){
                        //TODO more to do here
                        legalMoves.add(new PawnAttackMove(board, this, candidateDestinationCoordinate, pieceOnCandidate));
                    }
                }
            }
            //attacking move: condition when offset is 9 and doesn't fall into exclusion columns
            else if (currentCandidateOffset == 9 &&
                    !((BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite() ||
                     (BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack())))){
                //if tile is occupied by enemy, get the piece on tile
                if(board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    //if alliances between pieces don't match, add attacking move
                    if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()){
                        //TODO more to do here
                        legalMoves.add(new PawnAttackMove(board, this, candidateDestinationCoordinate, pieceOnCandidate));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    //create new Pawn with updated piece position
    @Override
    public Pawn movePiece(final Move move) {
        return new Pawn(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate());
    }

    //toString invokes toString from PieceType enum in Piece class
    @Override
    public String toString(){
        return PieceType.PAWN.toString();
    }
}
