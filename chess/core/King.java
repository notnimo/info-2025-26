package chess.core;

public class King extends Piece {

  private boolean firstMove = true;

  public King(Color color) {
    super(PieceType.KING, color);
  }

  private boolean trySingleMove(Move move){
    int rowDiff = Math.abs(move.getSourceRow() - move.getTargetRow());
    int colDiff = Math.abs(move.getSourceCol() - move.getTargetCol());
    return (rowDiff <= 1 && colDiff <= 1);
  }
  
  private boolean tryCastling(Move move){ //improve with check for checks
    if(!this.firstMove) return false;

    int row = this.color == Color.WHITE ? Board.LastRow : 0;

    if(move.getTargetRow() != (row)) return false;

    if(move.getTargetCol() == 2){

      Piece rook = move.getPiece(row, 0);
      if(!(rook instanceof Rook && rook != null && rook.getColor() == this.getColor() )) return false;

      boolean obstacleDetected = move.checkPiecePresence(row, 1);
      obstacleDetected &= move.checkPiecePresence(row, 2);
      obstacleDetected &= move.checkPiecePresence(row, 3);
      if(obstacleDetected) return false;

      if(this.getColor() == Color.BLACK) Game.blackLongCastlingOnGoing = true;
      else Game.whiteLongCastlingOnGoing = true;

      move.setPiece(row, 3, rook);
      move.setPiece(row, 0, null);
      return true;

    } else if(move.getTargetCol() == 6){
      Piece rook = move.getPiece(row, Board.LastCol);
      if(!(rook instanceof Rook && rook != null && rook.getColor() == this.getColor() )) return false;

      boolean obstacleDetected = move.checkPiecePresence(row, 5);
      obstacleDetected &= move.checkPiecePresence(row, 6);
      if(obstacleDetected) return false;

      if(this.getColor() == Color.BLACK) Game.blackShortCastlingOnGoing = true;
      else Game.whiteShortCastlingOnGoing = true;

      move.setPiece(row, 5, rook);
      move.setPiece(row, Board.LastCol, null);
      return true;
      
    }
    return false;
  }

  @Override
  public void validateMove(Move move) throws InvalidMoveException {
    super.validateMove(move);
    boolean validMove = false;

    if(!(move.isKingInCheck(move.getSourceRow(), move.getTargetCol(), color))) validMove = tryCastling(move);

    validMove |= trySingleMove(move);

    if (!validMove) throw new InvalidMoveException("invalid move");
  }

  @Override
  public void executeMove(Move move){
    this.firstMove = false;
  }
}
