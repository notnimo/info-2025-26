package chess.core;

public class Piece {
  protected PieceType type;
  protected Color color;

  public Piece(PieceType type, Color color) {
    this.type = type;
    this.color = color;
  }

  public PieceType getType() {
    return this.type;
  }

  public Color getColor() {
    return this.color;
  }

  @Override
  public String toString() {
    return this.color.toString() + " " + this.type.toString();
  }

  @Override
  public boolean equals(Object obj) {
    if(obj.getClass() != this.getClass()) return false;
    if(((Piece)obj).getType() != this.getType()) return false;
    if(((Piece)obj).getColor() != this.getColor()) return false;
    return true;
  }

  public void validateMove(Move move) throws InvalidMoveException {
    System.out.println("piece.validate move invoked");
    if(move.getSourceRow() == move.getTargetRow() && 
    move.getSourceCol() == move.getTargetCol()) throw new InvalidMoveException("Source and target squares are the same.");

    if(move.isTargetOccupiedByAlly()) throw new InvalidMoveException("Target square occupied by ally piece.");
  }

  public void executeMove(Move move){}

  public boolean validateCapture(Move move){
    try{
      validateMove(move);
      return true;
    } catch(InvalidMoveException e){
      return false;
    }
  }
}
