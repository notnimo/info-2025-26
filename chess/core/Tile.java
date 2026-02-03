package chess.core;

public class Tile {
  private int row;
  private int col;
  private Piece piece;
  private Color color;

  public Tile(int row, int col, Color color) {
    this.row = row;
    this.col = col;
    this.color = color;
    this.piece = null;
  }

  public Piece getPiece() {
    return this.piece;
  }

  public Color getColor() {
    return this.color;
  }

  public void setPiece(Piece piece) {
    this.piece = piece;
  }
}
