package sentence;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test cases for Sentence interface.
 */
public class SentenceTest {

  @Test
  public void getNumberOfWords() {
    Sentence s = new WordNode("Hello", new WordNode("world", new EmptyNode()));
    assertEquals(2, s.getNumberOfWords());
  }

  @Test
  public void longestWord() {
    Sentence s = new WordNode("Hello", new WordNode("world", new WordNode("test",
            new EmptyNode())));
    assertEquals("Hello", s.longestWord());
  }

  @Test
  public void merge() {
    Sentence s = new WordNode("Hello", new WordNode("world", new EmptyNode()));
    Sentence s2 = new WordNode("test", new EmptyNode());
    Sentence s3 = s.merge(s2);
    assertEquals("Hello world test", s3.toString());
  }

  @Test
  public void merge2() {
    Sentence s = new WordNode("Hello", new PunctuationNode(",",
            new WordNode("world", new EmptyNode())));
    Sentence s2 = new WordNode("test", new EmptyNode());
    Sentence s3 = s.merge(s2);
    assertEquals("Hello, world test", s3.toString());
  }

  @Test
  public void duplicate() {
    Sentence s = new WordNode("Hello", new PunctuationNode(",",
            new WordNode("world", new EmptyNode())));
    Sentence s2 = s.duplicate();
    assertEquals("Hello, world", s2.toString());
  }

  @Test
  public void testToString() {
    Sentence s = new WordNode("Hello", new WordNode("world", new EmptyNode()));
    assertEquals("Hello world", s.toString());
  }
}