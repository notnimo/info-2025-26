package chess.core;

public class Rook extends Piece{

  public Rook(Color color) {
      super(PieceType.ROOK, color);
  }

  public static boolean orthogonalMove(Move move) {
    int startRow = move.getSourceRow();
    int startCol = move.getSourceCol();
    int endRow = move.getTargetRow();
    int endCol = move.getTargetCol();

    System.out.println("startRow: " + startRow + " startCol: " +startCol+ " endRow: " +endRow+ " endCol: "+ endCol);
    System.out.println((startRow == endRow || startCol == endCol) && !move.checkObstacles());
    return (startRow == endRow || startCol == endCol) && !move.checkObstacles();
  }

  @Override
  public void validateMove(Move move) throws InvalidMoveException {
    super.validateMove(move);

    boolean validMove = orthogonalMove(move);
    System.out.println("rook validated move: " + validMove);
    if (!validMove) throw new InvalidMoveException("invalid move");
  }
}
