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

public class Queen extends Piece{

    //array of possible coordinates (offset values) for queen is bishop and rook combined
    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = { -9, -8, -7, -1, 1, 7, 8, 9 };

    public Queen(final Alliance pieceAlliance, final int piecePosition) {
        super(piecePosition, pieceAlliance);
    }

    //method to calculate possible moves
    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        //make an arraylist of legal moves
        final List<Move> legalMoves = new ArrayList<>();
        //loop through each coordinate, while each coordinate is a valid coordinate
        for(final int candidateCoordinatesOffset: CANDIDATE_MOVE_VECTOR_COORDINATES){
            int candidateDestinationCoordinate = this.piecePosition;
            while(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){

                if (isFirstExclusion(candidateDestinationCoordinate, candidateCoordinatesOffset) ||
                        isEightColumnExclusion(candidateDestinationCoordinate, candidateCoordinatesOffset)) {

                    break;
                }
                candidateDestinationCoordinate += candidateCoordinatesOffset;
                if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                    final Tile possibleDestinationTile = board.getTile(candidateDestinationCoordinate);
                    //if tile at destination is empty, add move into list of legal moves
                    if(!possibleDestinationTile.isTileOccupied()){
                        legalMoves.add(new Move.nonAttackMove(board, this, candidateDestinationCoordinate));
                    }
                    //if tile at destination occupied, get piece/alliance from that tile
                    else {
                        final Piece pieceAtDestination = possibleDestinationTile.getPiece();
                        final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                        //if alliances aren't same, add attacking move to list of legal moves
                        if (this.pieceAlliance != pieceAlliance) {
                            legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    //toString invokes toString from PieceType enum in Piece class
    @Override
    public String toString(){
        return PieceType.QUEEN.toString();
    }

    //methods to determine exclusions when bishop is on the side columns
    private static boolean isFirstExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -1 || candidateOffset == -9 || candidateOffset == 7);
    }


    private static boolean isEightColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == -7 || candidateOffset == 1 || candidateOffset == 9);
    }



}
