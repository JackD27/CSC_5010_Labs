package tictactoe;

/**
 * Run a TicTacToe game interactively.
 */
public class Driver {
  /**
   * Run a TicTacToe game interactively.
   */
  public static void main(String[] args) {
    TicTacToeModel model = new TicTacToeModelImpl();
    TicTacToeView view = new TicTacToeViewImpl(model);
    TicTacToeController controller = new TicTacToeControllerImpl(model, view);
    controller.playGame();
  }
}
