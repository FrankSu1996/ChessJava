package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece {

    //list of possible offset for first round (vertical movement)
    private final static int[] CANDIDATE_MOVE_COORDINATE = {8};


    Pawn(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    //method to calculate all the legal moves
    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        final List<Move> legalMoves= new ArrayList<>();

        //list through all the possible moves
        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE){
            //get direction for Pawn depending on Alliance (black/white), multiply by current offset to get destination
            int candidateDestinationCoordinate = this.piecePosition + (this.getPieceAlliance().getDirection() * currentCandidateOffset);

            //check if destination tile is valid
            if(!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                continue;
            }

            if(currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                //todo more work to do here!!!
                legalMoves.add(new Move.nonAttackMove(board, this, candidateDestinationCoordinate));

            }

        }


        return null;
    }
}
