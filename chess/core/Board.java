package chess.core;

public class Board {
  public Tile[][] tiles;

  public static final int Rows = 8;
  public static final int Cols = 8;
  public static final int LastRow = Rows - 1;
  public static final int LastCol = Cols - 1;

  public Board() {
    this.tiles = new Tile[Rows][Cols];
    for (int row = 0; row < Rows; row++) {
      for (int col = 0; col < Cols; col++) {
        tiles[row][col] = new Tile(row, col, ((row + col) % 2 == 0 ? Color.WHITE : Color.BLACK));
      }
    }
  }

  public Tile getTile(int row, int col) throws IllegalArgumentException {
    if(row < 0 || row > LastRow || col < 0 || col > LastCol) throw new IllegalArgumentException();
    return this.tiles[row][col];
  }
}
