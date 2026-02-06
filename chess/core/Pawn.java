package chess.core;

public class Pawn extends Piece {

  private boolean firstMove;

  public Pawn(Color color) {
    super(PieceType.PAWN, color);
    this.firstMove = true;
  }

  private boolean tryAdvance(Move move, int distance) {
    int direction = (getColor() == Color.WHITE) ? -1 : 1;
    int expectedRow = move.getSourceRow() + (direction * distance);

    move.checkObstacles();
    if(move.getPiece(move.getTargetRow(), move.getTargetCol()) != null) return false;
    if(distance == 2 && this.firstMove == false) return false;

    return move.getTargetRow() == expectedRow && move.getSourceCol() == move.getTargetCol();
  }

  private void updateEp(Move move){
    Color turn = this.getColor();

    if(turn == Color.WHITE) {
      Game.epLastWhiteMove = true;
      Game.epWhitePawnRow = move.getTargetRow();
      Game.epWhitePawnCol = move.getTargetCol();
    } else {
      Game.epLastBlackMove = true;
      Game.epBlackPawnRow = move.getTargetRow();
      Game.epBlackPawnCol = move.getTargetCol();
    }
  }

  @Override
  public void validateMove(Move move) throws InvalidMoveException {
    super.validateMove(move);
    
    boolean validMoveDetected = tryAdvance(move, 2);
    if (validMoveDetected) {
      this.updateEp(move);
      this.firstMove = false;
      return;
    }
    validMoveDetected = tryAdvance(move, 1);
    if (validMoveDetected) return;
    validMoveDetected = tryDiagonalCapture(move);
    if (validMoveDetected) return;

    System.out.println("invalid pawn move");
    throw new InvalidMoveException("Invalid move detected");

    //super.validateMove(move);
    //if(Math.abs(move.getSourceCol() - move.getTargetCol()) == 2) {
    //  if(!(firstMove && tryAdvance(move, 2))) throw new InvalidMoveException("invalid move");
    //  updateEp(move);
    //} else if(Math.abs(move.getSourceRow() - move.getTargetRow()) == 1) {
    //  if(move.board.getTile(move.getTargetRow(), move.getTargetCol()).getPiece() != null) throw new InvalidMoveException("invalid move");
    //  tryAdvance(move, 1);
    //} else {
    //  throw new InvalidMoveException("invalid pawn move");
    //}
  }

  private boolean tryEpCapture(Move move) {
    boolean enPassantCapture = false;
    Piece capturedPiece = null;
    if (this.color == Color.BLACK && Game.epLastWhiteMove) {
      if (move.getSourceRow() == Game.epWhitePawnRow && Math.abs(move.getSourceCol() - Game.epWhitePawnCol) == 1) {
        Game.epBlackOnGoing = true;
        capturedPiece = move.getPiece(Game.epWhitePawnRow, Game.epWhitePawnCol);
        move.setPiece(Game.epWhitePawnRow, Game.epWhitePawnCol, null);
        enPassantCapture = true;
      }
    } else if (this.color == Color.WHITE && Game.epLastBlackMove) {
      if (move.getSourceRow() == Game.epBlackPawnRow && Math.abs(move.getSourceCol() - Game.epBlackPawnCol) == 1) {
        Game.epWhiteOnGoing = true;
        capturedPiece = move.getPiece(Game.epBlackPawnRow, Game.epBlackPawnCol);
        move.setPiece(Game.epBlackPawnRow, Game.epBlackPawnCol, null);
        enPassantCapture = true;
      }
    } if (enPassantCapture) {
      //game.removePieceFromArmy(capturedPiece, (this.color == Color.BLACK ? Color.BLACK : Color.WHITE));
    }
    return enPassantCapture;
  }

  private boolean tryDiagonalCapture(Move move) {
    int colOffset = Math.abs(move.getSourceCol() - move.getTargetCol());
    int rowOffset = Math.abs(move.getTargetRow() - move.getSourceRow());

    if((colOffset != 1 || rowOffset != 1) || move.board.getTile(move.getTargetRow(), move.getTargetCol()).getPiece() == null) return false;

    if((this.getColor() == Color.WHITE ? move.getTargetCol() == Game.epBlackPawnRow : move.getTargetCol() == Game.epWhitePawnRow))
      if(tryEpCapture(move)) return true;

    return true;
  }

  @Override
  public boolean validateCapture(Move move){
    if(!super.validateCapture(move)) return false;
    if(!tryDiagonalCapture(move)) return false;
    return true;
  }

  @Override
  public void executeMove(Move move){
    this.firstMove = false;
    int lastRow = (this.getColor() == Color.WHITE ? 0 : Board.LastRow);
    if(move.getTargetRow() == lastRow){
      Game.pawnPromotionColumn = move.getTargetCol();
      if (this.getColor() == Color.WHITE){
        Game.pawnPromotionColumn = move.getTargetCol();
        Game.whitePawnPromotionOnGoing = true;
      } else {
        Game.pawnPromotionColumn = move.getTargetCol();
        Game.blackPawnPromotionOnGoing = true;
      }
    }
  }


}
