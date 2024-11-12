import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import tictactoe.TicTacToeControllerImpl;
import tictactoe.TicTacToeModelImpl;
import tictactoe.TicTacToeViewImpl;

/**
 * Tests for the TicTacToeControllerImpl class.
 */
public class TicTacToeControllerImplTest {
  private TicTacToeModelImpl mockModel;
  private TicTacToeControllerImpl controller;

  /**
   * Sets up the test fixture.
   */
  @Before
  public void setUp() {
    mockModel = new TicTacToeModelImpl();
    TicTacToeViewImpl mockView = new TicTacToeViewImpl(mockModel);
    controller = new TicTacToeControllerImpl(mockModel, mockView);

    mockView.addClickListener(controller);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullModel() {
    new TicTacToeControllerImpl(null, new TicTacToeViewImpl(new TicTacToeModelImpl()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullView() {
    new TicTacToeControllerImpl(new TicTacToeModelImpl(), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullModelAndView() {
    new TicTacToeControllerImpl(null, null);
  }

  // Tests an invalid move, as of now the game doesn't end but keeps on asking for a valid move,
  // I'm not sure if this is the expected behavior.
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove() {
    controller.handleCellClick(0, 0);
    controller.handleCellClick(0, 0);
  }

  @Test
  public void handleCellClick() {
    controller.handleCellClick(0, 0);
    controller.handleCellClick(0, 1);
    controller.handleCellClick(1, 0);
    controller.handleCellClick(1, 1);
    controller.handleCellClick(2, 0);
    controller.handleCellClick(2, 1);
    controller.handleCellClick(0, 2);
    controller.handleCellClick(1, 2);
    controller.handleCellClick(2, 2);
    assertTrue(mockModel.isGameOver());
  }

  @Test
  public void testGameOverX() {
    controller.handleCellClick(0, 0);
    controller.handleCellClick(0, 1);
    controller.handleCellClick(1, 0);
    controller.handleCellClick(1, 1);
    controller.handleCellClick(2, 0);
    controller.handleCellClick(2, 1);
    controller.handleCellClick(0, 2);
    controller.handleCellClick(1, 2);
    controller.handleCellClick(2, 2);
    assertTrue(mockModel.isGameOver());
    assertNotNull(mockModel.getWinner());
    assertEquals("X", mockModel.getWinner().toString());
  }

  @Test
  public void testGameOverO() {
    controller.handleCellClick(0, 0);
    controller.handleCellClick(1, 0);
    controller.handleCellClick(2, 0);
    controller.handleCellClick(1, 1);
    controller.handleCellClick(2, 1);
    controller.handleCellClick(1, 2);
    assertTrue(mockModel.isGameOver());
    assertNotNull(mockModel.getWinner());
    assertEquals("O", mockModel.getWinner().toString());
  }

  @Test
  public void testGameIsTied() {
    controller.handleCellClick(0, 1);
    controller.handleCellClick(1, 1);
    controller.handleCellClick(2, 1);
    controller.handleCellClick(2, 0);
    controller.handleCellClick(0, 2);
    controller.handleCellClick(0, 0);
    controller.handleCellClick(2, 2);
    controller.handleCellClick(1, 2);
    controller.handleCellClick(1, 0);
    assertTrue(mockModel.isGameOver());
    assertNull(mockModel.getWinner());
  }
}