import static org.junit.Assert.assertEquals;

import document.elements.BoldText;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for BasicText.
 */
public class BoldTextTest {


  private BoldText boldText;

  /**
   * Set up the test.
   */
  @Before
  public void setUp() {
    boldText = new BoldText("Hello, World!");
  }

  @Test
  public void testGetText() {
    assertEquals("Hello, World!", boldText.getText());
  }

}