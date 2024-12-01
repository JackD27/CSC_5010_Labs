import static org.junit.Assert.assertEquals;

import document.elements.BasicText;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for BasicText.
 */
public class BasicTextTest {

  private BasicText basicText;

  /**
   * Set up the test.
   */
  @Before
  public void setUp() {
    basicText = new BasicText("Hello, World!");
  }

  @Test
  public void testGetText() {
    assertEquals("Hello, World!", basicText.getText());
  }
}