package chess.gui;

import javax.swing.JFrame;
import chess.core.Game;

public class GameFrame extends JFrame {
  private BoardPanel boardPanel;
  private Game game; 

  public GameFrame() {
    this.game = new Game();
    this.boardPanel = new BoardPanel(this.game);
    this.add(boardPanel);
    this.setTitle("Chess Game");
    this.pack();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }

  public Game getGame(){
    return this.game;
  }
}
