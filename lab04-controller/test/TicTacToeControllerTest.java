import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import org.junit.Test;
import tictactoe.TicTacToe;
import tictactoe.TicTacToeConsoleController;
import tictactoe.TicTacToeController;
import tictactoe.TicTacToeModel;


/**
 * Test cases for the tic tac toe controller, using mocks for readable and
 * appendable.
 */
public class TicTacToeControllerTest {

  // Providing this shell for you to write your
  // own test cases for the TicTacToe controller
  // You DO NOT NEED to implement tests for the provided model

  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    // Testing when something goes wrong with the Appendable
    // Here we are passing it a mock of an Appendable that always fails
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    Appendable gameLog = new FailingAppendable();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
  }

  // TODO: This test might not be right exception to throw
  @Test(expected = IllegalArgumentException.class)
  public void testPlayGameNull() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    Appendable gameLog = new FailingAppendable();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFailingReadableNull() {
    StringBuilder gameLog = new StringBuilder();
    new TicTacToeConsoleController(null, gameLog);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFailingReadableTwoNull() {
    StringBuilder gameLog = new StringBuilder();
    new TicTacToeConsoleController(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFailingReadableOneNull() {
    StringReader input = new StringReader("a 1 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    new TicTacToeConsoleController(input, null);
  }

  @Test
  public void testFailedInput() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("a 1 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    assertTrue(gameLog.toString().contains("Not a valid number: a\n"));
  }

  @Test
  public void testFailedInputNegative() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("-1 2 3 1 3");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    System.out.println(gameLog.toString());
    assertTrue(gameLog.toString().contains("No input provided.\n"));
  }

  @Test
  public void testFailedInput4() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("e 2 2 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    System.out.println(gameLog.toString());
    assertTrue(gameLog.toString().contains("Game quit! Ending game state:\n"));
  }


  @Test
  public void testFailedInput2() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("8.79 1 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    assertTrue(gameLog.toString().contains("Not a valid number: 8.79\n"));
  }

  @Test
  public void testInvalidRowInput() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 4 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    System.out.println(gameLog.toString());
    assertTrue(gameLog.toString().contains("Not a valid move: 1, 4\n"));
  }

  @Test
  public void testMultipleInvalidMoves() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 2 u b 3 3 u g 1 3 h i 2 1 3 1 3 2 u b 3 3 u g 1 3 h i 2 1 3 1 3 2 3");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    System.out.println(gameLog.toString());
    assertTrue(gameLog.toString().contains("Not a valid move: 1, 4\n"));

  }

  @Test
  public void testNoInput() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    assertTrue(gameLog.toString().contains("No input provided."));
  }

  @Test
  public void testQuitGameShortRow() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("q 2 u b 3 3 u g 1 3 h i 2 1 3 1 3 2 3");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    assertTrue(gameLog.toString().contains("Game quit! Ending game state:"));
    assertFalse(m.isGameOver());
  }

  @Test
  public void testQuitGameShortCol() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 1 1 q 3 3 1 2 1 3 2 3 ");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    assertTrue(gameLog.toString().contains("Game quit! Ending game state:"));
  }

  @Test
  public void testQuitGameLongRow() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("quit 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2 3");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    System.out.println(gameLog.toString());
    assertTrue(gameLog.toString().contains("Game quit! Ending game state:"));
  }

  @Test
  public void testQuitGameOccupiedCell() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 1 1 2 2 q 2 2 2");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    assertTrue(gameLog.toString().contains("Game quit! Ending game state:"));
    System.out.println(gameLog.toString());
    assertFalse(m.isGameOver());
  }

  @Test
  public void testValidInputVerticalWin() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 1 2 2 1 2 2 3 1 3");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    assertTrue(gameLog.toString().contains("Game is over! X wins.\n")
            || gameLog.toString().contains("Game is over! O wins.\n"));
  }

  @Test
  public void testValidInputHorizontalWin() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 2 1 1 2 2 2 1 3 3");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    assertTrue(gameLog.toString().contains("Game is over! X wins.\n")
            || gameLog.toString().contains("Game is over! O wins.\n"));
  }

  @Test
  public void testValidInputDiagonalWin() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 1 2 2 2 2 1 3 3");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    assertTrue(gameLog.toString().contains("Game is over! X wins.\n")
            || gameLog.toString().contains("Game is over! O wins.\n"));
  }

  @Test
  public void testTieGame() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 1 2 1 3 2 2 3 2 2 3 2 1 3 1 3 3");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    System.out.println(gameLog.toString());
    assertTrue(gameLog.toString().contains("\nGame is over! Tie game.\n"));
  }
}
