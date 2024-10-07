package tictactoe;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This starter files is for students to implement a console controller for the
 * TicTacToe MVC assignment.
 */
public class TicTacToeConsoleController implements TicTacToeController {

  private final Appendable out;
  private final Scanner scan;
  private boolean gameInProgress;

  /**
   * Constructor for the controller.
   *
   * @param in  the source to read from
   * @param out the target to print to
   */
  public TicTacToeConsoleController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    scan = new Scanner(in);
    this.gameInProgress = true;
  }

  /**
   * Move the player.
   *
   * @param m the game to move in, takes in a TicTacToe object
   */
  private void movePlayer(TicTacToe m) {
    try {
      out.append("Enter a move for " + m.getTurn() + ":\n");

      int row = getValidatedInput("row", m);
      if (row == -1) return;

      int col = getValidatedInput("col", m);
      if (col == -1) return;

      try {
        m.move(row - 1, col - 1);
      } catch (IllegalArgumentException iae) {
        out.append("Not a valid move: " + row + ", " + col + "\n");
      }

    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
  }


  private int getValidatedInput(String type, TicTacToe m) throws IOException {
    String input = "";
    try {
      out.append("Enter a " + type + ":\n");

      if (!scan.hasNext()) {
        out.append("No input provided.\n");
        gameInProgress = false;
        return -1;
      }

      input = scan.next();

      if (isQuitCommand(input, m)) {
        return -1;
      }

      while (Integer.parseInt(input) < 0) {
        out.append("Invalid input, please enter a positive number for " + type + ":\n");
        input = scan.next();
        if (isQuitCommand(input, m)) {
          return -1;
        }
      }

      return Integer.parseInt(input);

    } catch (NumberFormatException nfe) {
      out.append("Not a valid number: " + input + "\n");
      return getValidatedInput(type, m);
    }
  }

  private boolean isQuitCommand(String input, TicTacToe m) throws IOException {
    if ("q".equalsIgnoreCase(input) || "quit".equalsIgnoreCase(input)) {
      quitGame(m);
      return true;
    }
    return false;
  }

  /**
   * Quit the game.
   *
   * @param m the game to quit, takes in a TicTacToe object
   */
  private void quitGame(TicTacToe m) {
    try {
      out.append("Game quit! Ending game state:\n");
      if (gameInProgress && m != null && !m.isGameOver() && m.getWinner() == null
              && m.getTurn() != null) {
        out.append(m.toString());
        out.append("\n");
      }
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
    gameInProgress = false;
  }

  @Override
  public void playGame(TicTacToe m) {
    if (m == null) {
      throw new IllegalArgumentException("Model can't be null");
    }
    try {
      while (!m.isGameOver() && gameInProgress) {
        out.append(m.toString());
        out.append("\n");
        movePlayer(m);
      }
      if (gameInProgress) {
        out.append(m.toString());
        if (m.getWinner() == null && m.isGameOver()) {
          out.append("\nGame is over! Tie game.\n");
          gameInProgress = false;
        } else {
          out.append("\nGame is over! " + m.getWinner() + " wins.\n");
          gameInProgress = false;
        }
      }
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
  }

}
