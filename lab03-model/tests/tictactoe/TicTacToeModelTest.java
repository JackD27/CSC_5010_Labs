package tictactoe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * This tests the TicTacToe game and it's methods.
 */
public class TicTacToeModelTest {

  private TicTacToeModel tictactoe;

  @Before
  public void setUp() {
    tictactoe = new TicTacToeModel();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOutOfBoundsMove() {
    tictactoe.move(4, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOutOfBoundsMove2() {
    tictactoe.move(-1, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWrongGetMark() {
    tictactoe.move(0, 0);
    tictactoe.getMarkAt(0, -1);
  }

  @Test(expected = IllegalStateException.class)
  public void testGameOverMove() {
    tictactoe.move(0, 0); // X
    tictactoe.move(1, 1); // O
    tictactoe.move(0, 1); // X
    tictactoe.move(1, 0); // O
    tictactoe.move(0, 2); // X | X wins here
    tictactoe.move(2, 0); // Should be an error tha get thrown here.
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove() {
    tictactoe.move(0, 0);
    assertEquals(Player.X, tictactoe.getMarkAt(0, 0));
    tictactoe.move(1, 1);
    assertEquals(Player.O, tictactoe.getMarkAt(1, 1));
    tictactoe.move(0, 0);
    assertEquals(Player.O, tictactoe.getMarkAt(1, 1));
  }

  @Test
  public void testGameIsTied() {
    tictactoe.move(0, 0); // X
    tictactoe.move(0, 1); // O
    tictactoe.move(0, 2); // X
    tictactoe.move(1, 1); // O
    tictactoe.move(1, 0); // X
    tictactoe.move(1, 2); // O
    tictactoe.move(2, 1); // X
    tictactoe.move(2, 0); // O
    tictactoe.move(2, 2); // X
    assertTrue(tictactoe.isGameOver());

  }

  @Test
  public void testMove() {
    tictactoe.move(0, 0);
    assertEquals(Player.X, tictactoe.getMarkAt(0, 0));
    tictactoe.move(1, 1);
    assertEquals(Player.O, tictactoe.getMarkAt(1, 1));
    tictactoe.move(0, 1);
    assertEquals(Player.X, tictactoe.getMarkAt(0, 1));
  }

  @Test
  public void testGetTurn() {
    assertEquals(Player.X, tictactoe.getTurn());
    tictactoe.move(0, 0);
    assertEquals(Player.O, tictactoe.getTurn());
  }

  @Test
  public void testIsGameOver() {
    tictactoe.move(0, 0); // X
    tictactoe.move(1, 1); // O
    tictactoe.move(0, 1); // X
    tictactoe.move(1, 0); // O
    tictactoe.move(0, 2); // X
    assertTrue(tictactoe.isGameOver());
  }

  @Test
  public void testGetWinner() {
    tictactoe.move(0, 0); // X
    tictactoe.move(1, 1); // O
    tictactoe.move(0, 1); // X
    tictactoe.move(1, 0); // O
    tictactoe.move(0, 2); // X
    assertEquals(Player.X, tictactoe.getWinner());
  }

  @Test
  public void testGetBoard() {
    tictactoe.move(0, 0); // X
    tictactoe.move(1, 1); // O
    Player[][] board = tictactoe.getBoard();
    assertEquals(Player.X, board[0][0]);
    assertEquals(Player.O, board[1][1]);
    assertNull(board[2][2]);
  }

  @Test
  public void testGetBoardDeepCopy() {
    tictactoe.move(0, 0); // X
    Player[][] board = tictactoe.getBoard();
    board[0][0] = Player.O;
    assertEquals(Player.X, tictactoe.getMarkAt(0, 0));
  }

  @Test
  public void getMarkAt() {
    tictactoe.move(0, 0); // X
    assertEquals(Player.X, tictactoe.getMarkAt(0, 0));
    tictactoe.move(1, 1); // O
    assertEquals(Player.O, tictactoe.getMarkAt(1, 1));
    assertNull(tictactoe.getMarkAt(1, 0));
  }
}