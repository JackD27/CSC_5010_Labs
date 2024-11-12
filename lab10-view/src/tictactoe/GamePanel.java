package tictactoe;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * A JPanel that displays the TicTacToe game.
 */
public class GamePanel extends JPanel {
  private final ReadonlyTttModel model;

  /**
   * Constructs a new GamePanel.
   *
   * @param model the model to display.
   */
  public GamePanel(ReadonlyTttModel model) {
    this.model = model;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    int width = getWidth();
    int height = getHeight();
    int cellWidth = width / 3;
    int cellHeight = height / 3;

    g.setColor(Color.BLACK);
    for (int i = 1; i < 3; i++) {
      g.drawLine(0, i * cellHeight, width, i * cellHeight);
      g.drawLine(i * cellWidth, 0, i * cellWidth, height);
    }

    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        Player player = model.getMarkAt(r, c);
        if (player != null) {
          g.drawString(player.toString(), c * cellWidth + cellWidth / 2,
                  r * cellHeight + cellHeight / 2);
        }
      }
    }

    g.setColor(Color.RED);
    if (model.isGameOver()) {
      Player winner = model.getWinner();
      String status = (winner != null) ? "Winner: " + winner : "It's a tie!";
      g.drawString(status, 10, height - 10);
    } else {
      g.drawString("Turn: " + model.getTurn(), 10, height - 10);
    }
  }
}