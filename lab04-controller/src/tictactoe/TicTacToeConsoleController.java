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
    String input = null;
    int row = 0;
    int col = 0;

    try {
      out.append("Enter a move for " + m.getTurn() + ":\n");

      try {
        if (!scan.hasNext()) {
          out.append("No input provided.\n");
          gameInProgress = false;
          return;
        }
        input = scan.next(); // Directly read the input

        if ("q".equalsIgnoreCase(input) || "quit".equalsIgnoreCase(input)) {
          quitGame(m);
          return; // Exit the method to quit the game
        }
        while (Integer.parseInt(input) < 0) { //TODO: This, possibly move under quitGame
          out.append("Invalid input, please enter a positive number for row:\n");
          input = scan.next(); // Ask for new row input
          if ("q".equalsIgnoreCase(input) || "quit".equalsIgnoreCase(input)) {
            quitGame(m);
            return; // Exit the method to quit the game
          }
        }

        // Try to parse the row input
        row = Integer.parseInt(input);

        // Try to parse the column input
        if (!scan.hasNext()) {
          out.append("No input provided.\n");
          gameInProgress = false;
          return;
        }
        input = scan.next();

        if ("q".equalsIgnoreCase(input) || "quit".equalsIgnoreCase(input)) {
          quitGame(m);
          return; // Exit the method to quit the game
        }
        while (Integer.parseInt(input) < 0) { //TODO: This, possibly move under quitGame
          out.append("Invalid input, please enter a positive number for col:\n");
          input = scan.next(); // Ask for new row input
          if ("q".equalsIgnoreCase(input) || "quit".equalsIgnoreCase(input)) {
            quitGame(m);
            return; // Exit the method to quit the game
          }
        }
        col = Integer.parseInt(input);

        m.move(row - 1, col - 1);

      } catch (NumberFormatException nfe) {
        out.append("Not a valid number: " + input + "\n");
      } catch (NoSuchElementException nsee) {
        out.append("No input provided.\n");
      }
    } catch (IllegalArgumentException iae) {
      try {
        out.append("Not a valid move: " + row + ", " + col + "\n");
      } catch (IOException ioe) {
        throw new IllegalStateException("Append failed", ioe);
      }
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
  }

  /**
   * Quit the game.
   *
   * @param m the game to quit, takes in a TicTacToe object
   */
  private void quitGame(TicTacToe m) {
    try {
      out.append("Game quit! Ending game state:\n");
      out.append(m.toString());
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
        if (m.getWinner() == null) {
          out.append("\nGame is over! Tie game.\n");
        } else {
          out.append("\nGame is over! " + m.getWinner() + " wins.\n");
        }
      }
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
  }

}
