import static org.junit.Assert.assertEquals;

import document.elements.ItalicText;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ItalicText.
 */
public class ItalicTextTest {

  private ItalicText italicText;

  /**
   * Set up the test.
   */
  @Before
  public void setUp() {
    italicText = new ItalicText("This is italic text!");
  }

  @Test
  public void testGetText() {
    assertEquals("This is italic text!", italicText.getText());
  }
}