package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Knight extends Piece {

    //possible legal moves offset (excluding out of bound tiles and occupied tiles)
    private final static int[] POSSIBLE_MOVE_COORDINATES = { -17, -15, -10, -6, 6, 10, 15, 17};
    //Constructor: Each knight has position and color(black/white)
    Knight(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        List<Move> legalMoves = new ArrayList<>();

        //loop through possible move coordinates from current position of knight
        for (final int currentCandidateOffset : POSSIBLE_MOVE_COORDINATES){
            //calculate all possible move coordinates using current position + offset
            final int possibleDestinationCoordinate = this.piecePosition + currentCandidateOffset;
            //if tile is valid
            if ( BoardUtils.isValidTileCoordinate(possibleDestinationCoordinate) ){
                //skip loop iteration if possible move is part of column exclusions
                if (isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                        isSecondColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                        isSeventhColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                        isEighthColumnExclusion(this.piecePosition, currentCandidateOffset)){
                    continue;
                }
                final Tile possibleDestinationTile = board.getTile(possibleDestinationCoordinate);
                //if tile at destination is empty, add move into list of legal moves
                if(!possibleDestinationTile.isTileOccupied()){
                    legalMoves.add(new Move());
                }
                //if tile at destination occupied, get piece/alliance from that tile
                else {
                    final Piece pieceAtDestination = possibleDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                    //if alliances aren't same, add attacking move to list of legal moves
                    if(this.pieceAlliance != pieceAlliance){
                        legalMoves.add(new Move());
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    //method to capture first column exceptions
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){

        //offset breaks down if current position is in first column and is the following offsets:
        return BoardUtils.FIRST_COLUMN[currentPosition] && ((candidateOffset == -17) || (candidateOffset == -10) || (candidateOffset == 6) || (candidateOffset == 15));

    }
    //method to capture second column exceptions
    private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.SECOND_COLUMN[currentPosition] && ((candidateOffset == -10) || (candidateOffset == 6));
    }
    //method to capture seventh column exceptions
    private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.SEVENTH_COLUMN[currentPosition] && ((candidateOffset == -6) || (candidateOffset == 10));
    }
    //method to capture eighth column exceptions
    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && ((candidateOffset == -15) || (candidateOffset == -6) || (candidateOffset == 10) || (candidateOffset == 17));
    }
}
