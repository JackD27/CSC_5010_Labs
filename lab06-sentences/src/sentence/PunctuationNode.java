package sentence;

/**
 * Represents a single punctuation in a sentence.
 */
public class PunctuationNode implements Sentence {
  private final String punctuation;
  private final Sentence rest;

  public PunctuationNode(String punctuation, Sentence rest) {
    this.punctuation = punctuation;
    this.rest = rest;
  }

  @Override
  public int getNumberOfWords() {
    return rest.getNumberOfWords();
  }

  @Override
  public String longestWord() {
    return rest.longestWord();
  }

  @Override
  public Sentence merge(Sentence other) {
    return new PunctuationNode(punctuation, rest.merge(other));
  }

  @Override
  public Sentence duplicate() {
    return new PunctuationNode(punctuation, rest.duplicate());
  }

  @Override
  public String toString() {
    String restString = rest.toString();

    if (!restString.isEmpty() && Character.isLetterOrDigit(restString.charAt(0))) {
      return punctuation + " " + restString;
    }

    return punctuation + restString;
  }

}
