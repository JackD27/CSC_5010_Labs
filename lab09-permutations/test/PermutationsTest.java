import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import permutations.Permutations;


/**
 * Tests for the Permutations class.
 */
public class PermutationsTest {

  Permutations permutations;
  Permutations permutations2;
  Permutations permutations3;
  Permutations permutations4;
  Permutations permutations5;
  Permutations permutations6;
  Permutations permutationsEmpty;

  /**
   * Sets up the test fixture.
   */
  @Before
  public void setUp() {
    permutations = new Permutations("abc");
    permutations2 = new Permutations("abcd");
    permutations3 = new Permutations("abcdef", 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void wrongSetUp() {
    permutations4 = new Permutations("abcde", 6);
    permutations5 = new Permutations("abcde", 0);
    permutations6 = new Permutations("abcde", -1);
    permutationsEmpty = new Permutations("");
  }

  @Test
  public void previous() {
    String firstPerm = permutations.next();
    assertEquals("a", firstPerm);
    String secondPerm = permutations.next();
    assertEquals("b", secondPerm);
    String previousPerm = permutations.previous();
    assertEquals("abc", previousPerm);
    String previousPerm2 = permutations.previous();
    assertEquals("abc", previousPerm2);
  }

  @Test
  public void hasPrevious() {
    String firstPerm = permutations.next();
    assertEquals("abc", firstPerm);
    String secondPerm = permutations.next();
    assertEquals("acb", secondPerm);
    assertTrue(permutations.hasPrevious());
    String previousPerm = permutations.previous();
    assertEquals("abc", previousPerm);
    assertFalse(permutations.hasPrevious());
  }

  @Test
  public void hasNext() {
    assertTrue(permutations.hasNext());
    String firstPerm = permutations.next();
    assertEquals("abc", firstPerm);
    String secondPerm = permutations.next();
    assertEquals("acb", secondPerm);
    String thirdPerm = permutations.next();
    assertEquals("bac", thirdPerm);
    String fourthPerm = permutations.next();
    assertEquals("bca", fourthPerm);
    String fifthPerm = permutations.next();
    assertEquals("cab", fifthPerm);
    String sixthPerm = permutations.next();
    assertEquals("cba", sixthPerm);
    assertFalse(permutations.hasNext());
  }

  @Test
  public void next() {
    String firstPerm = permutations.next();
    assertEquals("abc", firstPerm);
    String secondPerm = permutations.next();
    assertEquals("acb", secondPerm);
    String thirdPerm = permutations.next();
    assertEquals("bac", thirdPerm);
    String fourthPerm = permutations.next();
    assertEquals("bca", fourthPerm);
    String fifthPerm = permutations.next();
    assertEquals("cab", fifthPerm);
    String sixthPerm = permutations.next();
    assertEquals("cba", sixthPerm);
  }
}