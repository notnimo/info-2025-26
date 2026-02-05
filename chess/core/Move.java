package chess.core;

public class Move {
  private int sourceRow;
  private int sourceCol;
  private int targetRow;
  private int targetCol;

  public Board board;

  public Move(Board board, int sourceRow, int sourceCol, int targetRow, int targetCol) {
    this.board = board;
    this.sourceRow = sourceRow;
    this.sourceCol = sourceCol;
    this.targetRow = targetRow;
    this.targetCol = targetCol;
  }

  public int getSourceRow() {
    return this.sourceRow;
  }
  
  public int getSourceCol() {
    return this.sourceCol;
  }
  
  public int getTargetRow() {
    return this.targetRow;
  }
  
  public int getTargetCol() {
    return this.targetCol;
  }

  public Piece getPiece(int row, int col) {
    return this.board.getTile(row, col).getPiece();
  }

  public void setPiece(int row, int col, Piece piece) {
    this.board.getTile(row, col).setPiece(piece);
  }

  public boolean onTarget(int row, int col) {
    return this.targetRow == row && this.targetCol == col;
  }

  public boolean checkPiecePresence(int row, int col) {
    return this.getPiece(row, col) != null;
  }

  public void wouldEndInKingCheck() throws InvalidMoveException {
    Piece sourcePiece = this.getPiece(this.sourceRow, this.sourceCol);
    Piece targetPiece = this.getPiece(this.targetRow, this.targetCol);

    this.setPiece(this.sourceRow, this.sourceCol, null);
    this.setPiece(this.targetRow, this.targetCol, sourcePiece);

    int kingRow = -1;
    int kingCol = -1;

    for(int row = 0; row < Board.Rows; row++){
      for(int col = 0; col < Board.Cols; col++){
        Piece piece = this.getPiece(row, col);
        if(piece != null && piece.getType() == PieceType.KING && piece.getColor() == sourcePiece.getColor()){
          kingRow = row;
          kingCol = col;
        }
      }
    }

    this.setPiece(this.sourceRow, this.sourceCol, sourcePiece);
    this.setPiece(this.targetRow, this.targetCol, targetPiece);

    boolean kingInCheck = this.isKingInCheck(kingRow, kingCol, sourcePiece.getColor());
    if (kingInCheck) throw new InvalidMoveException("Move would put or leave king in check");
  }

  public boolean isTargetOccupiedByAlly(){
    Piece sourcePiece = this.getPiece(this.sourceRow, this.sourceCol);
    Piece targetPiece = this.getPiece(this.targetRow, this.targetCol);

    if (targetPiece == null) return false;
    return sourcePiece.getColor() == targetPiece.getColor();
  }

  public boolean isKingInCheck(int kingRow, int kingCol, Color kingColor){
    Color opponentColor = (kingColor == Color.WHITE) ? Color.BLACK : Color.WHITE;

    for (int row = 0; row < Board.Rows; row++) {
      for (int col = 0; col < Board.Cols; col++) {
        Piece piece = this.getPiece(row, col);
        if (piece != null && opponentColor == kingColor) {
          Move potentialMove = new Move(this.board, row, col, kingRow, kingCol);
          try{
            piece.validateMove(potentialMove);
          }catch(InvalidMoveException e){
            return true;
          }
        }
      }
    }
    return false;
  }

  public boolean checkObstacles(){
    int row = this.sourceRow;
    int column = this.sourceCol;
    boolean rowGrows = row < this.targetRow;
    boolean columnGrows = column < this.targetCol;
    while (!this.onTarget(row, column)) {
      // increase/decrease row and column toward target
      if (row != this.targetRow){
        if (rowGrows) row++;
        else row--;
      }
      if (column != this.targetCol) {
        if (columnGrows) column++;
        else column--;
      }

      if (this.onTarget(row, column)) return false; // reached target
      Piece targetPiece = this.board.getTile(row, column).getPiece();
      if (targetPiece != null) return true; // obstacle found
    }
    return false;
  }
} 