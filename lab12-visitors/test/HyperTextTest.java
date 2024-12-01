import static org.junit.Assert.assertEquals;

import document.elements.HyperText;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for HyperText.
 */
public class HyperTextTest {

  private HyperText hyperText;

  /**
   * Set up the test.
   */
  @Before
  public void setUp() {
    hyperText = new HyperText("Google!", "https://www.google.com");
  }

  @Test
  public void testGetText() {
    assertEquals("Google!", hyperText.getText());
  }

  @Test
  public void getUrl() {
    assertEquals("https://www.google.com", hyperText.getUrl());
  }
}