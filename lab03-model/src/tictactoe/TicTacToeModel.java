package tictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * A single game of Tic Tac Toe, played on a three-by-three grid with two players,
 * with the object of the game to achieve three markers in a row either vertically,
 * horizontally, or diagonally. {@link Player} X goes first.
 */

public class TicTacToeModel implements TicTacToe {
  private static final int SIZE_OF_BOARD = 3;
  private final Player[][] board = new Player[SIZE_OF_BOARD][SIZE_OF_BOARD];
  private Player currentPlayer;
  private boolean gameOver;
  private Player winner;
  private boolean boardFull = true;

  /**
   * Constructs a new TicTacToeModel object. The board is initialized to be empty, and the
   * current player is set to Player.X.
   */

  public TicTacToeModel() {
    this.currentPlayer = Player.X;
    this.gameOver = false;
    this.winner = null;
  }

  @Override
  public void move(int r, int c) {

    if (r < 0 || c < 0 || r >= SIZE_OF_BOARD || c >= SIZE_OF_BOARD
            || board[r][c] != null && !boardFull) {
      throw new IllegalArgumentException("Invalid position");
    }

    if (gameOver) {
      throw new IllegalStateException("Game is over");
    }

    board[r][c] = currentPlayer;
    checkWinner();

    if (!gameOver) {
      currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;
    }
  }

  /**
   * Check if there is a winner in the game.
   */

  private void checkWinner() {
    for (int i = 0; i < SIZE_OF_BOARD; i++) {
      // Rows
      if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != null) {
        winner = board[i][0];
        gameOver = true;
        return;
      }
      // Columns
      if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != null) {
        winner = board[0][i];
        gameOver = true;
        return;
      }
    }
    // Diagonals
    if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != null) {
      winner = board[0][0];
      gameOver = true;
      return;
    }
    if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != null) {
      winner = board[0][2];
      gameOver = true;
      return;
    }

    boardFull = true;
    for (int i = 0; i < SIZE_OF_BOARD; i++) {
      for (int j = 0; j < SIZE_OF_BOARD; j++) {
        if (board[i][j] == null) {
          boardFull = false;
          break;
        }
      }
    }
    if (boardFull) {
      gameOver = true;
    }
  }

  @Override
  public Player getTurn() {
    return currentPlayer;
  }

  /**
   * Set the board to be full or not.
   *
   * @param boardFull true if the board is full, false otherwise
   */
  public void setBoardFull(boolean boardFull) {
    this.boardFull = boardFull;
  }

  @Override
  public boolean isGameOver() {
    return gameOver;
  }

  @Override
  public Player getWinner() {
    return winner;
  }

  @Override
  public Player[][] getBoard() {
    return board;
  }

  @Override
  public Player getMarkAt(int r, int c) {
    if (r >= SIZE_OF_BOARD || c >= SIZE_OF_BOARD) {
      throw new IllegalArgumentException("Invalid position");
    }
    if (r < 0 || c < 0) {
      throw new IllegalArgumentException("Invalid position");
    }
    return board[r][c];
  }

  @Override
  public String toString() {
    // Using Java stream API to save code:
    return Arrays.stream(getBoard()).map(
                    row -> " " + Arrays.stream(row).map(
                            p -> p == null ? " " : p.toString()).collect(Collectors.joining(" | ")))
            .collect(Collectors.joining("\n-----------\n"));
    // This is the equivalent code as above, but using iteration, and still using the helpful
    // built-in String.join method.
    // List<String> rows = new ArrayList<>();
    // for(Player[] row : getBoard()) {
    //   List<String> rowStrings = new ArrayList<>();
    //   for(Player p : row) {
    //     if(p == null) {
    //       rowStrings.add(" ");
    //     } else {
    //       rowStrings.add(p.toString());
    //     }
    //   }
    //   rows.add(" " + String.join(" | ", rowStrings));
    // }
    // return String.join("\n-----------\n", rows);
  }
}
