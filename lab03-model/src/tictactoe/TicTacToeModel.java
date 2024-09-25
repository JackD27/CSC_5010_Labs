package tictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TicTacToeModel implements TicTacToe {
  Player[][] board;
  private Player currentPlayer;
  private boolean gameOver;
  private Player winner;

  public TicTacToeModel() {
    this.board = new Player[3][3];
    this.currentPlayer = Player.X;
    this.gameOver = false;
    this.winner = null;
  }

  @Override
  public void move(int r, int c) {

    if (gameOver || board[r][c] != null) {
      return;
    }

    board[r][c] = currentPlayer;
    checkWinner();
    if(!isGameOver()) {
      currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;
    }

  }

  private void checkWinner() {

    for (int i = 0; i < 3; i++) {
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
    }
  }

  @Override
  public Player getTurn() {
    return currentPlayer;
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
