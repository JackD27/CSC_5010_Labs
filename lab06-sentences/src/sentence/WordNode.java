package sentence;

/**
 * Represents a single word in a sentence.
 */
public class WordNode implements Sentence {
  private final String word;
  private final Sentence rest;

  public WordNode(String word, Sentence rest) {
    this.word = word;
    this.rest = rest;
  }

  @Override
  public int getNumberOfWords() {
    return 1 + rest.getNumberOfWords();
  }

  @Override
  public String longestWord() {
    String longest = rest.longestWord();
    if (word.length() >= longest.length()) {
      return word;
    }
    return longest;
  }

  @Override
  public Sentence merge(Sentence other) {
    return new WordNode(word, rest.merge(other));
  }

  @Override
  public Sentence duplicate() {
    return new WordNode(word, rest.duplicate());
  }

  @Override
  public String toString() {
    String restString = rest.toString();
    if (!restString.isEmpty() && Character.isLetterOrDigit(restString.charAt(0))) {
      return word + " " + restString;
    }
    return word + restString;
  }
}
