package polynomial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the PolynomialImpl class.
 */
public class PolynomialImplTest {
  PolynomialImpl p1;
  PolynomialImpl p2;
  PolynomialImpl p3;
  PolynomialImpl p4;

  /**
   * Sets up the test cases.
   */
  @Before
  public void setUp() {
    p1 = new PolynomialImpl("5x^2 +4x^1 -2");
    p2 = new PolynomialImpl("-50x^3 +1x^2 +3");
    p3 = new PolynomialImpl("2x^5 -3x^2 +4x^1 -10");
    p4 = new PolynomialImpl();

  }

  @Test
  public void add() {
    Polynomial addedPoly = p1.add(p2);
    assertEquals("-50x^3 + 6x^2 + 4x + 1", addedPoly.toString());
    Polynomial addedPoly2 = p3.add(p4);
    assertEquals("2x^5-3x^2 + 4x-10", addedPoly2.toString());
  }

  @Test
  public void addTerm() {
    p1.addTerm(5, 2);
    assertEquals("10x^2 + 4x-2", p1.toString());
    p4.addTerm(5, 2);
    assertEquals("5x^2", p4.toString());
  }

  @Test
  public void isSame() {
    assertFalse(p1.isSame(p2));
    assertTrue(p1.isSame(p1));
  }

  @Test
  public void evaluate() {
    assertEquals(26, p1.evaluate(2), 0.0);
    assertEquals(-46, p2.evaluate(1), 0.0);
    Polynomial addedPoly = p1.add(p2);
    assertEquals(-6079, addedPoly.evaluate(5), 0.0);
    assertEquals(1, addedPoly.evaluate(0), 0.0);
    assertEquals(0, p4.evaluate(0), 0.0);
  }

  @Test
  public void getCoefficient() {
    assertEquals(5, p1.getCoefficient(2));
    assertEquals(0, p1.getCoefficient(3));
    assertEquals(0, p4.getCoefficient(2));
  }

  @Test
  public void getDegree() {
    assertEquals(2, p1.getDegree());
    assertEquals(0, p4.getDegree());
  }

  @Test
  public void testToString() {
    assertEquals("5x^2 + 4x-2", p1.toString());
    assertEquals("-50x^3 + 1x^2 + 3", p2.toString());
    assertEquals("2x^5-3x^2 + 4x-10", p3.toString());
    assertEquals("0", p4.toString());
  }
}