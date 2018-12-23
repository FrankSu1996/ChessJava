package com.chess.engine;

public enum Alliance {
    //direction associated with color is for pawn movement
    WHITE{
        @Override
        public int getDirection(){
            return -1;
        }
    },
    BLACK {
        @Override
        public int getDirection(){
            return 1;
        }
    };

    public abstract int getDirection();
}
