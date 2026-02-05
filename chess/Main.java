package chess;

public class Main {

  public static void printM(int[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        System.out.print("[" + matrix[i][j] + "] ");
      }
      System.out.println();
    }
  }

  //public static void main(String[] args) {
  //  int Rows = 8;
  //  int Cols = 8;
//
  //  int[][] tiles = new int[Rows][Cols];
  //  for (int row = 0; row < Rows; row++) {
  //    for (int col = 0; col < Cols; col++) {
  //      tiles[row][col] = (row + col) % 2 == 0 ? 0 : 1;
  //    }
  //  }
//
  //  printM(tiles);
  //}

  public static void main(String[] args) {
    System.out.println(chess.core.Color.BLACK.toString());
  }
}
