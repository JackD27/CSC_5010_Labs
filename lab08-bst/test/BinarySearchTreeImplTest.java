import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import bst.BinarySearchTreeImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the BinarySearchTreeImpl class.
 */
public class BinarySearchTreeImplTest {

  private BinarySearchTreeImpl<Integer> bst;
  private BinarySearchTreeImpl<Integer> bst2;

  /**
   * Sets up the binary search tree.
   */
  @Before
  public void setUp() {
    bst = new BinarySearchTreeImpl<>();
    bst2 = new BinarySearchTreeImpl<>();
    bst.add(7);
    bst.add(3);
    bst.add(10);
    bst.add(12);
    bst.add(2);
    bst.add(8);
    bst.add(9);
    bst.add(0);
    bst.add(-10);
    bst.add(-3);
  }

  @Test
  public void add() {
    bst.add(1);
    assertEquals(11, bst.size());
    bst.add(1);
    assertEquals(11, bst.size());
    assertEquals(0, bst2.size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void addNull() {
    bst.add(null);
  }

  @Test
  public void size() {
    assertEquals(10, bst.size());
    assertEquals(0, bst2.size());
  }

  @Test
  public void height() {
    assertEquals(6, bst.height());
    assertEquals(0, bst2.height());
  }

  @Test
  public void present() {
    assertTrue(bst.present(7));
    assertFalse(bst.present(1));
  }

  @Test
  public void minimum() {
    assertEquals(Integer.valueOf(-10), bst.minimum());
    assertNull(bst2.minimum());
  }

  @Test
  public void maximum() {
    assertEquals(Integer.valueOf(12), bst.maximum());
    assertNull(bst2.maximum());
  }

  @Test
  public void preOrder() {
    assertEquals("[7 3 2 0 -10 -3 10 8 9 12]", bst.preOrder());
    assertEquals("[]", bst2.preOrder());
  }

  @Test
  public void inOrder() {
    assertEquals("[-10 -3 0 2 3 7 8 9 10 12]", bst.inOrder());
    assertEquals("[]", bst2.inOrder());
  }

  @Test
  public void postOrder() {
    assertEquals("[-3 -10 0 2 3 9 8 12 10 7]", bst.postOrder());
    assertEquals("[]", bst2.postOrder());
  }

  // Should be the same as inOrder
  @Test
  public void testToString() {
    assertEquals("[-10 -3 0 2 3 7 8 9 10 12]", bst.toString());
    assertEquals("[]", bst2.toString());
  }
}