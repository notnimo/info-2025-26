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

      System.out.println("updating white ep flags, white turn should be over");
    } else {
      Game.epLastBlackMove = true;
      Game.epBlackPawnRow = move.getTargetRow();
      Game.epBlackPawnCol = move.getTargetCol();

      System.out.println("updating black ep flags, black turn should be over");
    }
  }

  private boolean tryEpCapture(Move move) {
    System.out.println("try ep capture reached");
    boolean enPassantCapture = false;
    Piece capturedPiece = null;
    if (this.color == Color.BLACK && Game.epLastWhiteMove) {
      if (move.getSourceRow() == Game.epWhitePawnRow && Math.abs(move.getSourceCol() - Game.epWhitePawnCol) == 1) {
        Game.epBlackOnGoing = true;
        capturedPiece = move.getPiece(Game.epWhitePawnRow, Game.epWhitePawnCol);
        move.board.getTile(Game.epWhitePawnRow, Game.epWhitePawnCol).setPiece(null);
        enPassantCapture = true;
      }
    } else if (this.color == Color.WHITE && Game.epLastBlackMove) {
      if (move.getSourceRow() == Game.epBlackPawnRow && Math.abs(move.getSourceCol() - Game.epBlackPawnCol) == 1) {
        Game.epWhiteOnGoing = true;
        capturedPiece = move.getPiece(Game.epBlackPawnRow, Game.epBlackPawnCol);
        move.board.getTile(Game.epBlackPawnRow, Game.epBlackPawnCol).setPiece(null);
        enPassantCapture = true;
      }
    } if (enPassantCapture) {
      //game.removePieceFromArmy(capturedPiece, (this.color == Color.BLACK ? Color.BLACK : Color.WHITE));
    }
    return enPassantCapture;
  }

  private boolean tryDiagonalCapture(Move move) {
    System.out.println("try diagonal capture reached");
    int colOffset = Math.abs(move.getSourceCol() - move.getTargetCol());
    int rowOffset = Math.abs(move.getTargetRow() - move.getSourceRow());

    if((this.getColor() == Color.WHITE ? move.getTargetCol() == Game.epBlackPawnCol : move.getTargetCol() == Game.epWhitePawnCol))
      if(tryEpCapture(move)) return true;

    if((colOffset != 1 || rowOffset != 1) || move.board.getTile(move.getTargetRow(), move.getTargetCol()).getPiece() == null) return false;

    return true;
  }

  /*@Override
  public boolean validateCapture(Move move){
    if(!super.validateCapture(move)) return false;
    if(!tryDiagonalCapture(move)) return false;
    return true;
  }*/

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
  }
}
