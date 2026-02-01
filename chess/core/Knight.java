package chess.core;

public class Knight extends Piece {

  public Knight(Color color) {
    super(PieceType.KNIGHT, color);
  }
  
  @Override
  public void validateMove(Move move) throws InvalidMoveException {
    super.validateMove(move);

    int rowDiff = Math.abs(move.getTargetRow() - move.getSourceRow());
    int colDiff = Math.abs(move.getTargetCol() - move.getSourceCol());

    if(!((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2))) {
      throw new InvalidMoveException("invalid move.");
    }
  }
}
