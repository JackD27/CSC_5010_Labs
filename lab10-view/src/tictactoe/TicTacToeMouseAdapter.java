package tictactoe;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 * A MouseAdapter that listens for mouse clicks on a TicTacToe game board.
 */
public class TicTacToeMouseAdapter extends MouseAdapter {
  private final TicTacToeController controller;
  private final JPanel panel;

  /**
   * Constructs a new TicTacToeMouseAdapter.
   *
   * @param controller the controller to notify of clicks.
   * @param panel the panel to listen for clicks on.
   */
  public TicTacToeMouseAdapter(TicTacToeController controller, JPanel panel) {
    this.controller = controller;
    this.panel = panel;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    int cellWidth = panel.getWidth() / 3;
    int cellHeight = panel.getHeight() / 3;
    int row = e.getY() / cellHeight;
    int col = e.getX() / cellWidth;
    controller.handleCellClick(row, col);
  }
}