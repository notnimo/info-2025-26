package chess.core;

public class Queen extends Piece {
    public Queen(Color color) {
      super(PieceType.QUEEN, color);
    }

  @Override
  public void validateMove(Move move) throws InvalidMoveException {
    super.validateMove(move);

    boolean validMove = (Bishop.diagonalMove(move) || Rook.orthogonalMove(move)) && !move.checkObstacles();
    if (!validMove) throw new InvalidMoveException("invalid move");
  }
  
}
