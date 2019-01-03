package com.chess.engine;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

import com.chess.engine.board.Board;

public class JChess {

    public static void main(String []args ){
        Board board = Board.createStandardBoard();
        System.out.println(board);
    }
}
