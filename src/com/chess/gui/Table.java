package com.chess.gui;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static javax.imageio.ImageIO.read;

public class Table {

    private final JFrame gameFrame;
    private final BoardPanel boardPanel;
    private final static Dimension OUTER_FRAME_DIMENSION = new Dimension(1000, 650);
    private final static Dimension BOARD_PANEL_DIMENSION = new Dimension(400, 350);
    private final static Dimension TILE_PANEL_DIMENSION = new Dimension(10, 10);

    //colors for tiles
    private final Color lightTileColor = Color.decode("#FFFACD");
    private final Color darkTileColor = Color.decode("#593E1A");

    public Table(){
        this.gameFrame = new JFrame("JavaChess");
        this.gameFrame.setLayout(new BorderLayout());
        //creating menu bar
        final JMenuBar tableMenuBar = createTableMenuBar();
        this.gameFrame.setJMenuBar(tableMenuBar);
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        this.boardPanel = new BoardPanel();
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);
        this.gameFrame.setVisible(true);
    }

    //method to create a menu bar
    private JMenuBar createTableMenuBar(){
        final JMenuBar tableMenuBar = new JMenuBar();
        tableMenuBar.add(createFileMenu());
        return tableMenuBar;
    }

    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu("File");

        //menu option to load PGN files
        final JMenuItem openPGN = new JMenuItem("Load PGN File");
        openPGN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("open up that pgn file!");
            }
        });
        fileMenu.add(openPGN);

        //create exit option
        final JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);

        return fileMenu;
    }

    //inner class to represent chessboard
    private class BoardPanel extends JPanel {
        final List<TilePanel> boardTiles;

        BoardPanel(){
            super(new GridLayout(8,8));
            this.boardTiles = new ArrayList<>();
            for (int i = 0; i < BoardUtils.NUM_TILES; i++){
                final TilePanel tilePanel = new TilePanel(this, i);
                this.boardTiles.add(tilePanel);
                add(tilePanel);
            }
            setPreferredSize(BOARD_PANEL_DIMENSION);
            validate();
        }

    }
    //inner class to represent tiles on board
    private class TilePanel extends JPanel {

        private final int tileId;

        TilePanel(final BoardPanel boardPanel, final int tileId) {
            super (new GridBagLayout());
            this.tileId = tileId;
            setPreferredSize(TILE_PANEL_DIMENSION);
            try {
                assignTileColor();
            } catch (IOException e) {
                e.printStackTrace();
            }
            validate();
        }

        //places image on tiles
        private void assignTilePieceIcon(final Board board)
        {
            this.removeAll();

            //if tile has tile on it, must place image on it
            if (board.getTile(this.tileId).isTileOccupied()) {
                String pieceIconPath = "";
                try {
                    // example: WHITE, bishop = "WB.gif"
                    final BufferedImage image = read(new File(pieceIconPath + board.getTile(this.tileId).getPiece().getPieceAlliance().toString().substring(0, 1) +
                            board.getTile(this.tileId).getPiece().toString() + ".gif"));
                    add (new JLabel(new ImageIcon(image)));
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

        private void assignTileColor() throws IOException {
            //if tile is in these rows, if tile id even make it light color
            if (BoardUtils.EIGHTH_RANK[this.tileId] ||
                BoardUtils.SIXTH_RANK[this.tileId] ||
                BoardUtils.FOURTH_RANK[this.tileId] ||
                BoardUtils.SECOND_RANK[this.tileId])
            {
                setBackground(this.tileId % 2 == 0 ? lightTileColor : darkTileColor);
            }
            else if (BoardUtils.SEVENTH_RANK[this.tileId] ||
                    BoardUtils.FIFTH_RANK[this.tileId] ||
                    BoardUtils.THIRD_RANK[this.tileId] ||
                    BoardUtils.FIRST_RANK[this.tileId])
            {
                setBackground(this.tileId % 2 != 0 ? lightTileColor : darkTileColor);
            }
        }
    }


}
