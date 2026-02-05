package chess.gui;

import chess.core.Game;
import chess.core.Board;
public class GuiLauncher {
  public static void boardToString(Game game){
    Board b = game.getBoard();
    for(int i = 0; i < 8; i++){
      for(int j = 0; j < 8; j++){
        System.out.print( "[" + (b.getTile(i, j).getPiece() == null ? "null" : b.getTile(i, j).getPiece().getType().toString()) + "] ");
      }
      System.out.println();
    }
  }
  public static void main(String[] args) {
    GameFrame gameFrame = new GameFrame();
    System.out.println("null");
    boardToString(gameFrame.getGame());
    gameFrame.setVisible(true);
  }
}
