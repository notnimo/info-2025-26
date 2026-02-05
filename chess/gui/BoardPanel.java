package chess.gui;

import java.net.URL;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

import chess.core.Board;
import chess.core.Color;
import chess.core.Game;
import chess.core.PieceType;
import chess.core.Piece;

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
    if(!(this.moveInProgress)){
      if(this.game.isMoveSourceValid(row, col)){
        this.moveInProgress = true;
        highlightSourceTile(row, col, java.awt.Color.GREEN);
        this.sourceRow = row;
        this.sourceCol = col;
      }
    }else {
      this.moveInProgress = false;
      this.processMove(sourceRow, sourceCol, row, col);
      highlightSourceTile(sourceRow, sourceCol, determineTileColor(sourceRow, sourceCol));
    }
  }

  private void checkPawnPromotion() {
    if(chess.core.Game.whitePawnPromotionOnGoing || chess.core.Game.blackPawnPromotionOnGoing) {
      String promotedPieceType = (String)JOptionPane.showInputDialog(null, "Select promoted piece", "Pawn Promotion", JOptionPane.QUESTION_MESSAGE, null, new String[] {"Bishop", "Rook", "Knight", "Queen"}, "Bishop");

      Color pieceColor = (chess.core.Game.whitePawnPromotionOnGoing ? Color.WHITE : Color.WHITE);
      PieceType pieceType = (promotedPieceType == "Bishop" ? PieceType.BISHOP : (promotedPieceType == "Knight" ? PieceType.KNIGHT : (promotedPieceType == "Rook" ? PieceType.ROOK : PieceType.QUEEN)));
      Piece p = new Piece(pieceType, pieceColor);
      int row = (chess.core.Game.whitePawnPromotionOnGoing ? 0 : Board.LastRow);
      int col = chess.core.Game.pawnPromotionColumn;

      Piece pawnPromoted = this.game.getBoard().getTile(row, col).getPiece();
      this.game.removePieceFromArmy(pawnPromoted, pieceColor);
      this.clearPiece(row, col);
      this.game.getBoard().getTile(row, col).setPiece(p);
      this.game.addPieceToArmy(p, pieceColor);
      this.drawPiece(row, col, pieceType, pieceColor);
    }
  }

  private void checkCastling(){
    if(Game.blackLongCastlingOnGoing) { //optimize
      this.clearPiece(0, 0);
      this.drawPiece(0, 3, PieceType.ROOK, Color.BLACK);
    } else if(Game.blackShortCastlingOnGoing) {
      this.clearPiece(0, Board.LastCol);
      this.drawPiece(0, 5, PieceType.ROOK, Color.BLACK );
    } else if(Game.whiteLongCastlingOnGoing) {
      this.clearPiece(Board.LastRow, 0);
      this.drawPiece(Board.LastRow, 3, PieceType.ROOK, Color.WHITE);
    } else if(Game.whiteShortCastlingOnGoing) {
      this.clearPiece(Board.LastRow, Board.LastCol);
      this.drawPiece(Board.LastRow, 5, PieceType.ROOK, Color.WHITE);
    }
  }

  private void checkEp() {
    if(!(Game.epBlackOnGoing || Game.epWhiteOnGoing)) return;

    this.clearPiece((Game.epWhiteOnGoing ? Game.epWhitePawnRow : Game.epBlackPawnRow), (Game.epWhiteOnGoing ? Game.epWhitePawnCol : Game.epBlackPawnCol));
  }

  public void processMove(int sourceRow, int sourceCol, int targetRow, int targetCol){
    boolean moveSuccessful = game.processMove(sourceRow, sourceCol, targetRow, targetCol);
    if(moveSuccessful){
      Piece movedPiece = game.getBoard().getTile(targetRow, targetCol).getPiece();
      this.clearPiece(sourceRow, sourceCol);
      this.drawPiece(targetRow, targetCol, movedPiece.getType(), movedPiece.getColor());
      this.checkCastling();
      this.checkPawnPromotion();
      this.checkEp();
    }
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
    String iconFile = "" + color.toString().toUpperCase().charAt(0) + (pieceType.toString() == "KNIGHT" ? "N" : pieceType.toString().toUpperCase().charAt(0)) + ".png";
    String iconPath = "/chess/icons/" + iconFile;
    URL url = getClass().getResource(iconPath);
    if (url == null) {
      System.err.println("Resource not found: " + iconPath);
      JOptionPane.showMessageDialog(this, "Missing icon: " + iconPath, "Resource error", JOptionPane.ERROR_MESSAGE);
      return;
    }
    Image image = new ImageIcon(url).getImage();
    image = image.getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH);
    JLabel label = new JLabel(new ImageIcon(image));
    this.tiles[row][col].removeAll();
    this.tiles[row][col].add(label);
    this.tiles[row][col].updateUI();
  }
}
