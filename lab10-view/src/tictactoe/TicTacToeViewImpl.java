package tictactoe;

import javax.swing.JFrame;

/**
 * A JFrame that displays the TicTacToe game.
 */
public class TicTacToeViewImpl extends JFrame implements TicTacToeView {
  private final GamePanel gamePanel;

  /**
   * Constructs a new TicTacToeViewImpl.
   *
   * @param model the model to display.
   */
  public TicTacToeViewImpl(ReadonlyTttModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model must be non-null.");
    }
    this.gamePanel = new GamePanel(model);

    setTitle("Tic Tac Toe");
    setSize(400, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    add(gamePanel);

  }

  @Override
  public void addClickListener(TicTacToeController listener) {
    gamePanel.addMouseListener(new TicTacToeMouseAdapter(listener, gamePanel));
  }

  @Override
  public void refresh() {
    gamePanel.repaint();
  }

  @Override
  public void makeVisible() {
    setVisible(true);
  }
}
