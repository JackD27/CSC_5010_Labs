import static org.junit.Assert.assertEquals;

import document.elements.Heading;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Heading.
 */
public class HeadingTest {

  private Heading heading;

  /**
   * Set up the test.
   */
  @Before
  public void setUp() {
    heading = new Heading("Hello, World!", 1);
  }

  @Test
  public void testGetText() {
    assertEquals("Hello, World!", heading.getText());
  }

  @Test
  public void getLevel() {
    assertEquals(1, heading.getLevel());
  }
}