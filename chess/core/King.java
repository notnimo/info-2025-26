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
  
  private boolean tryCastling(Move move){
    //if (!firstMove) {
    //  return false;
    //}
    //int rowDiff = move.getTargetRow() - move.getSourceRow();
    //int colDiff = move.getTargetCol() - move.getSourceCol();
    //if (rowDiff != 0) {
    //  return false;
    //}
    //if (Math.abs(colDiff) != 2) {
    //  return false;
    //}
    //// Additional castling validation logic would go here (e.g., checking rook position, path clearance, check conditions)
    //return true;

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
}
