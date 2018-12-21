public abstract class Tile {

    int tileCoordinate;

    public Tile(int tileCoordinate){
        this.tileCoordinate = tileCoordinate;
    }
    //determines if tile is occupied or empty
    public abstract boolean isTileOccupied();

    //returns piece from occupied tile, or NULL from empty tile
    public abstract Piece getPiece();

    //subclass for Empty tiles
    public static final class EmptyTile extends Tile{
        public EmptyTile(int coordinate){
            super(coordinate);
        }
        //overwritten method: empty tiles are not occupied
        public boolean isTileOccupied(){
            return false;
        }
        //overwritten method: empty tiles don't return a piece
        public Piece getPiece(){
            return null;
        }
    }

    //subclass for occupied tile
    public static final class OccupiedTile extends Tile{
        //occupied tiles have piece on them
        Piece pieceOnTile;

        //constructor takes coordinate AND piece
        public OccupiedTile(int coordinate, Piece pieceOnTile){
            super(coordinate);
            this.pieceOnTile = pieceOnTile;
        }


        //overwritten method: occupied tiles are occupied
        public boolean isTileOccupied(){
            return true;
        }

        //overwritten method: occupied tiles return a piece
        public Piece getPiece(){
            return this.pieceOnTile;
        }

    }
}
