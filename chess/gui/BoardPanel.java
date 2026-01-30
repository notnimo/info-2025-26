package chess.gui;

import java.net.URL;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import chess.core.Board;
import chess.core.Game;
import chess.core.PieceType;

public class BoardPanel extends JPanel {
  static int TILE_SIZE = 100;
  private JPanel main;
  private JPanel[][] tiles;
  private Game game;
  private GameDynamicsListener listener;
  private int sourceRow;
  private int sourceCol;
  private boolean moveInProgress;

  public void onMove(int row, int col) {
    // Handle the move logic here
  }

  public BoardPanel(Game game) {
    super(new BorderLayout());
    this.game = game;
    this.moveInProgress = false;
    initializeLayout();
    initializeGame();
  }

  private void initializeLayout() {
    game.getBoard();
    this.main = new JPanel(new GridLayout(Board.Rows, Board.Cols));
    main.setBounds(0, 0, TILE_SIZE * Board.Rows, TILE_SIZE * Board.Cols);

    setPreferredSize(new Dimension(800, 800));
    add(main, BorderLayout.CENTER);

    this.listener = new GameDynamicsListener(this);
    addMouseListener(this.listener);
    addMouseMotionListener(this.listener);
    tiles = new JPanel[Board.Rows][Board.Cols];
    for (int i = 0; i < Board.Rows; i++) {
      for (int j = 0; j < Board.Cols; j++) {
        this.tiles[i][j] = new JPanel(new GridLayout(1, 1));
        this.tiles[i][j].setPreferredSize(new Dimension(TILE_SIZE,TILE_SIZE));
        this.tiles[i][j].setSize(new Dimension(TILE_SIZE, TILE_SIZE));
        this.tiles[i][j].setBackground(determineTileColor(i, j));
        this.tiles[i][j].setVisible(true);
        this.main.add(tiles[i][j]);
      }
    }
  }

  public void initializeGame(){
    Board b = game.getBoard();
    for(int i = 0; i < Board.Rows; i++){
      for(int j = 0; j < Board.Cols; j++){
        if(b.getTile(i, j).getPiece() != null){
          this.drawPiece(i, j, b.getTile(i, j).getPiece().getType(), b.getTile(i, j).getPiece().getColor());
        }
      }
    }
  }

  public java.awt.Color determineTileColor(int row, int col){
    return ((row + col) % 2 == 0) ? java.awt.Color.WHITE : java.awt.Color.DARK_GRAY;
  }

  public void highlightSourceTile(int row, int col, java.awt.Color highlightColor){
    this.tiles[row][col].setBackground(highlightColor);
  }

  public void clearPiece(int row, int col){
    this.tiles[row][col].removeAll();
    this.tiles[row][col].updateUI();
  }

  public void drawPiece(int row, int col, PieceType pieceType, chess.core.Color color){
    String iconName = "icons/" + color.toString().toUpperCase().charAt(0) + pieceType.toString().toUpperCase().charAt(0) + ".png";
    URL url = getClass().getClassLoader().getResource(iconName);
    Image image = new ImageIcon(url).getImage();
    image = image.getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH);
    JLabel label = new JLabel(new ImageIcon(image));
    this.tiles[row][col].removeAll();
    this.tiles[row][col].add(label);
    this.tiles[row][col].updateUI();
  }
}
