import static org.junit.Assert.assertEquals;

import document.BasicStringVisitor;
import document.Document;
import document.HtmlStringVisitor;
import document.MarkdownStringVisitor;
import document.elements.BasicText;
import document.elements.BoldText;
import document.elements.Heading;
import document.elements.HyperText;
import document.elements.ItalicText;
import document.elements.Paragraph;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Document.
 */
public class DocumentTest {

  private final Document document = new Document();
  private final Paragraph paragraph = new Paragraph();

  /**
   * Set up the test.
   */
  @Before
  public void setUp() {
    document.add(new BasicText("This is basic!"));
    document.add(new BoldText("This is BOLD!"));
    document.add(new Heading("This is a nice heading.", 2));
    document.add(new HyperText("google.com", "http://www.google.com"));
    document.add(new ItalicText("This is italic text!"));
    paragraph.add(new BoldText("Bold text for paragraph."));
    paragraph.add(new BasicText("Basic text for paragraph."));
    document.add(paragraph);
  }

  @Test
  public void countWords() {
    assertEquals(24, document.countWords());
  }

  @Test
  public void testAdd() {
    BasicText basicText2 = new BasicText("Hello, World!");
    document.add(basicText2);
    assertEquals(26, document.countWords());
  }

  @Test
  public void toBasicStringText() {
    BasicStringVisitor basicVisitor = new BasicStringVisitor();
    String expected = "This is basic! This is BOLD! This is a nice heading. "
            + "google.com This is italic text! Bold text for paragraph. Basic text for paragraph.";
    assertEquals(expected, document.toText(basicVisitor));
  }

  @Test
  public void toHtmlStringText() {
    HtmlStringVisitor htmlVisitor = new HtmlStringVisitor();
    String expected = """
            This is basic!
            <b>This is BOLD!</b>
            <h2>This is a nice heading.</h2>
            <a href="http://www.google.com">google.com</a>
            <i>This is italic text!</i>
            <p><b>Bold text for paragraph.</b>
            Basic text for paragraph.
            </p>""";
    assertEquals(expected, document.toText(htmlVisitor));
  }

  @Test
  public void toMarkdownStringText() {
    MarkdownStringVisitor markdownVisitor = new MarkdownStringVisitor();
    String expected = """
            This is basic!
            **This is BOLD!**
            ## This is a nice heading.
            [google.com](http://www.google.com)
            *This is italic text!*
            
            **Bold text for paragraph.**
            Basic text for paragraph.""";
    assertEquals(expected, document.toText(markdownVisitor));
  }
}