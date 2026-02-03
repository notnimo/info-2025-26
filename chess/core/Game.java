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
    for(int row = 0; row < Board.Rows; row++){
      if(row == 0 || row == Board.LastRow){
        for(int col = 0; col < Board.Cols; col++){
          if(col == 0 || col == Board.LastCol)
            this.board.getTile(row, col).setPiece(new Piece(PieceType.ROOK, (row == 0 ? Color.WHITE : Color.BLACK)));

          if(col == 1 || col == (Board.LastCol - 1))
            this.board.getTile(row, col).setPiece(new Piece(PieceType.KNIGHT, (row == 0 ? Color.WHITE : Color.BLACK)));

          if(col == 2 || col == (Board.LastCol - 2))
            this.board.getTile(row, col).setPiece(new Piece(PieceType.BISHOP, (row == 0 ? Color.WHITE : Color.BLACK)));

          if(col == 3) if(row == 0) this.board.getTile(row, col).setPiece(new Piece(PieceType.QUEEN, Color.BLACK));
          else this.board.getTile(row, col).setPiece(new Piece(PieceType.KING, Color.WHITE));

          if(col == 4) if(row == 0) this.board.getTile(row, col).setPiece(new Piece(PieceType.KING, Color.BLACK));
          else this.board.getTile(row, col).setPiece(new Piece(PieceType.QUEEN, Color.WHITE));
        }
      }else if(row == 1 || row == (Board.LastRow - 1)){
        for(int col = 0; col < Board.Cols; col++){
          this.board.getTile(row, col).setPiece(new Piece(PieceType.PAWN, (row == 1 ? Color.BLACK : Color.WHITE)));
        }
      }
    }
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