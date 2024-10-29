import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import bst.BinarySearchTreeImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the BinarySearchTreeImpl class.
 */
public class BinarySearchTreeImplTest {

  private BinarySearchTreeImpl<Integer> bst;

  /**
   * Sets up the binary search tree.
   */
  @Before
  public void setUp() {
    bst = new BinarySearchTreeImpl<>();
    bst.add(7);
    bst.add(3);
    bst.add(10);
    bst.add(12);
    bst.add(2);
    bst.add(8);
    bst.add(9);
  }

  @Test
  public void add() {
    bst.add(1);
    assertEquals(8, bst.size());
    bst.add(1);
    assertEquals(8, bst.size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void addNull() {
    bst.add(null);
  }

  @Test
  public void size() {
    assertEquals(7, bst.size());
  }

  @Test
  public void height() {
    assertEquals(4, bst.height());
  }

  @Test
  public void present() {
    assertTrue(bst.present(7));
    assertFalse(bst.present(1));
  }

  @Test
  public void minimum() {
    assertEquals(Integer.valueOf(2), bst.minimum());
  }

  @Test
  public void maximum() {
    assertEquals(Integer.valueOf(12), bst.maximum());
  }

  @Test
  public void preOrder() {
    assertEquals("[7 3 2 10 8 9 12]", bst.preOrder());
  }

  @Test
  public void inOrder() {
    assertEquals("[2 3 7 8 9 10 12]", bst.inOrder());
  }

  @Test
  public void postOrder() {
    assertEquals("[2 3 9 8 12 10 7]", bst.postOrder());
  }

  // Should be the same as inOrder
  @Test
  public void testToString() {
    assertEquals("[2 3 7 8 9 10 12]", bst.toString());
  }
}