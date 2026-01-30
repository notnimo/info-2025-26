package chess.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GameDynamicsListener implements MouseListener, MouseMotionListener {
  private BoardPanel boardPanel;

  public GameDynamicsListener(BoardPanel boardPanel) {
    this.boardPanel = boardPanel;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    int Xcord = e.getX();
    int Ycord = e.getY();

    int col = Xcord / BoardPanel.TILE_SIZE;
    int row = Ycord / BoardPanel.TILE_SIZE;

    this.boardPanel.onMove(row, col);
  }

  @Override
  public void mousePressed(MouseEvent e) {}

  @Override
  public void mouseReleased(MouseEvent e) {}

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}

  @Override
  public void mouseDragged(MouseEvent e) {}

  @Override
  public void mouseMoved(MouseEvent e) {}
}
