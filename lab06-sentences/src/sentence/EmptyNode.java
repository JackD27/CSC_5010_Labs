package sentence;

/**
 * Represents an empty node in a sentence.
 */
public class EmptyNode implements Sentence {

  @Override
  public int getNumberOfWords() {
    return 0;
  }

  @Override
  public String longestWord() {
    return "";
  }

  @Override
  public Sentence merge(Sentence other) {
    return other.duplicate();
  }

  @Override
  public Sentence duplicate() {
    return new EmptyNode();
  }

  @Override
  public String toString() {
    return "";
  }
}
