import static org.junit.Assert.assertEquals;

import document.elements.BasicText;
import document.elements.ItalicText;
import document.elements.Paragraph;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Paragraph.
 */
public class ParagraphTest {

  private Paragraph paragraph;

  /**
   * Set up the test.
   */
  @Before
  public void setUp() {
    paragraph = new Paragraph();
    paragraph.add(new BasicText("Hello, World!"));
    paragraph.add(new ItalicText("This is italic text!"));
  }


  @Test
  public void getContent() {
    List<BasicText> basicTexts = paragraph.getContent();
    assertEquals(2, basicTexts.size());
  }

  @Test
  public void getText() {
    assertEquals("Hello, World! This is italic text!", paragraph.getText());
  }

  @Test
  public void add() {
    BasicText basicText2 = new BasicText("Hello, World!");
    paragraph.add(basicText2);
    assertEquals("Hello, World! This is italic text! Hello, World!", paragraph.getText());
    assertEquals(3, paragraph.getContent().size());
  }
}