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
  Permutations permutations7;

  /**
   * Sets up the test fixture.
   */
  @Before
  public void setUp() {
    permutations = new Permutations("abc");
    permutations2 = new Permutations("abcd");
    permutations3 = new Permutations("abcdef", 3);
    permutations7 = new Permutations("abc", 2);
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
    assertEquals("b", previousPerm);
    String previousPerm2 = permutations.previous();
    assertEquals("a", previousPerm2);
  }

  @Test
  public void hasPrevious() {
    String firstPerm = permutations.next();
    assertEquals("a", firstPerm);
    String secondPerm = permutations.next();
    assertEquals("b", secondPerm);
    assertTrue(permutations.hasPrevious());
    String previousPerm = permutations.previous();
    assertEquals("b", previousPerm);
    assertTrue(permutations.hasPrevious());
    String previousPerm2 = permutations.previous();
    assertEquals("a", previousPerm2);
    assertFalse(permutations.hasPrevious());
  }

  @Test // This should keep on going previous until it reaches the first element
  public void hasPrevious2() {
    assertTrue(permutations3.hasNext());
    String firstPerm = permutations3.next();
    assertEquals("abc", firstPerm);
    assertTrue(permutations3.hasPrevious());
    String firstPrevPerm = permutations3.previous();
    assertEquals("abc", firstPrevPerm);
    assertTrue(permutations3.hasPrevious());
  }

  @Test
  public void hasNext() {
    assertTrue(permutations3.hasNext());
    String firstPerm = permutations3.next();
    assertEquals("abc", firstPerm);
    String secondPerm = permutations3.next();
    assertEquals("abd", secondPerm);
    String thirdPerm = permutations3.next();
    assertEquals("abe", thirdPerm);
    String fourthPerm = permutations3.next();
    assertEquals("abf", fourthPerm);
    String fifthPerm = permutations3.next();
    assertEquals("acd", fifthPerm);
    String sixthPerm = permutations3.next();
    assertEquals("ace", sixthPerm);
    assertTrue(permutations3.hasNext());
  }

  @Test
  public void next() {
    String firstPerm = permutations7.next();
    assertEquals("ab", firstPerm);
    String secondPerm = permutations7.next();
    assertEquals("ac", secondPerm);
    String thirdPerm = permutations7.next();
    assertEquals("bc", thirdPerm);
    String fourthPerm = permutations7.next();
    assertEquals("abc", fourthPerm);
  }
}