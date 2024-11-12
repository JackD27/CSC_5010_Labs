package tictactoe;

/**
 * Represents a Controller for TicTacToe: handle user moves by executing them
 * using the model; convey move outcomes to the user in some form.
 */
public class TicTacToeControllerImpl implements TicTacToeController {
  private final TicTacToeModel model;
  private final TicTacToeView view;

  /**
   * Constructs a TicTacToeControllerImpl object.
   *
   * @param model the model of the game
   * @param view  the view of the game
   * @throws IllegalArgumentException if model or view is null
   */
  public TicTacToeControllerImpl(TicTacToeModel model, TicTacToeView view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Model and view must be non-null.");
    }
    this.model = model;
    this.view = view;
    view.addClickListener(this);
  }

  @Override
  public void playGame() {
    view.makeVisible();
    view.refresh();
  }

  @Override
  public void handleCellClick(int row, int col) {
    try {
      model.move(row, col);
      view.refresh();
      if (model.isGameOver()) {
        Player winner = model.getWinner();
        if (winner == null) {
          System.out.println("It's a tie!");
        } else {
          System.out.println(winner + " wins!");
        }
      }
    } catch (IllegalArgumentException | IllegalStateException e) {
      System.out.println("Invalid move: " + e.getMessage());
    }

  }
}
