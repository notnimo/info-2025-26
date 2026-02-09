package chess.core;

public class Bishop extends Piece {

  private boolean onWhite;

  public Bishop(Color color) {
    super(PieceType.BISHOP, color);
  }

  public static boolean diagonalMove(Move move) {
    int startRow = move.getSourceRow();
    int startCol = move.getSourceCol();
    int endRow = move.getTargetRow();
    int endCol = move.getTargetCol();
    

    int rowDiff = Math.abs(endRow - startRow);
    int colDiff = Math.abs(endCol - startCol);
    return (rowDiff == colDiff);
  }

  @Override
  public void validateMove(Move move) throws InvalidMoveException {
    super.validateMove(move);

    boolean validMove = diagonalMove(move) && !move.checkObstacles();
    if (!validMove) throw new InvalidMoveException("invalid move");
  }
  
}
