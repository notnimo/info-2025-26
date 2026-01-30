package chess.core;

import java.util.ArrayList;

public class Game {
  private Color turn;
  private ArrayList<Piece> whitePieces;
  private ArrayList<Piece> blackPieces;
  private Board board;
  
  public Game() {
    this.turn = Color.WHITE;
    this.whitePieces = new ArrayList<>();
    this.blackPieces = new ArrayList<>();
    this.board = new Board();
    setup();
  }

  private void setup(){
    // Set up pieces
  }

  public void clearBoard(){
    this.board = new Board();
  }

  public Color getTurn() {
    return this.turn;
  }

  public Board getBoard() {
    return this.board;
  }

  public void removePieceFromArmy(Piece piece, Color color) {
    if (color == Color.WHITE) this.whitePieces.remove(piece);
    else this.blackPieces.remove(piece);
  }

  public void addPieceToArmy(Piece piece, Color color) {
    if (color == Color.WHITE) this.whitePieces.add(piece);
    else this.blackPieces.add(piece);
  }

  public boolean isMoveSourceValid(int row, int col) {
    return (this.board.getTile(row, col).getPiece() != null && this.board.getTile(row, col).getPiece().getColor() == this.turn);
  }

  public void validateMove(Move move) throws InvalidMoveException {
      Piece piece = board.getTile(move.getSourceRow(), move.getSourceCol()).getPiece();
      piece.validateMove(move);
  }

  public void executeMove(Move move) throws InvalidMoveException {
    Tile sourceTile = board.getTile(move.getSourceRow(), move.getSourceCol());
    Tile targetTile = board.getTile(move.getTargetRow(), move.getTargetCol());
    Piece sourcePiece = sourceTile.getPiece();
    Piece targetPiece = targetTile.getPiece();

    sourcePiece.executeMove(move);
    if(targetPiece != null) removePieceFromArmy(targetPiece, targetPiece.getColor());

    targetTile.setPiece(sourcePiece);
    sourceTile.setPiece(null);
  }

  public boolean processMove(int sourceRow, int sourceCol, int targetRow, int targetCol) {
    Move move = new Move(this.board, sourceRow, sourceCol, targetRow, targetCol);
    try{
      validateMove(move);
      move.wouldEndInKingCheck();
      executeMove(move);
      this.turn = (this.turn == Color.WHITE) ? Color.BLACK : Color.WHITE;
      return true;
    } catch (InvalidMoveException e) {
      return false;
    }
  }
} 