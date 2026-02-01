package chess.core;

public class Pawn extends Piece {

  private boolean firstMove;

  public Pawn(Color color, boolean firstMove) {
    super(PieceType.PAWN, color);
    this.firstMove = firstMove;
  }

  private boolean tryAdvance(Move move, int distance) {
    int direction = (getColor() == Color.WHITE) ? 1 : -1;
    int expectedRow = move.getSourceRow() + (direction * distance);

    move.checkObstacles();

    return move.getTargetRow() == expectedRow && move.getSourceCol() == move.getTargetCol();
  }

  @Override
  public void validateMove(Move move) throws InvalidMoveException {
    super.validateMove(move);

    if(Math.abs(move.getSourceCol() - move.getTargetCol()) == 2) {
      if(!(firstMove && tryAdvance(move, 2))) throw new InvalidMoveException("invalid move");
      firstMove = false;
    } else if(Math.abs(move.getSourceRow() - move.getTargetRow()) == 1) {
      if(move.board.getTile(move.getTargetRow(), move.getTargetCol()).getPiece() != null) throw new InvalidMoveException("invalid move");
      tryAdvance(move, 1);
      if(firstMove) firstMove = false;
    } else {
      throw new InvalidMoveException("invalid pawn move");
    }
  }
  
}
