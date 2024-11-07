package permutations;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * An iterator that generates permutations of a given set of characters.
 */
public class Permutations implements BackAndForthIterator<String> {
  private final String sequence;
  private int permLength;
  private int[] indices;
  private boolean hasNext;
  private final Stack<String> history; // Stack

  /**
   * Constructs a new permutation iterator for the given characters.
   *
   * @param chars the characters to permute
   */
  public Permutations(String chars) {
    this(chars, 1);
  }

  /**
   * Constructs a new permutation iterator for the given characters and length.
   *
   * @param sequence the characters to permute
   * @param permLength the length of the permutations
   */
  public Permutations(String sequence, int permLength) {
    if (sequence == null || permLength < 1 || permLength > sequence.length()) {
      throw new IllegalArgumentException("Invalid sequence or permutation length");
    }

    this.sequence = sequence;
    this.permLength = permLength;
    this.indices = new int[permLength];
    for (int i = 0; i < permLength; i++) {
      indices[i] = i;
    }
    this.hasNext = true;
    this.history = new Stack<>();
  }

  @Override
  public String previous() {
    if (!hasPrevious()) {
      throw new NoSuchElementException("No previous permutation.");
    }

    String previousPermutation = history.pop();

    for (int i = 0; i < permLength; i++) {
      indices[i] = sequence.indexOf(previousPermutation.charAt(i));
    }

    return previousPermutation;
  }

  @Override
  public boolean hasPrevious() {
    return !history.isEmpty();
  }

  @Override
  public boolean hasNext() {
    return hasNext;
  }

  @Override
  public String next() {
    if (!hasNext) {
      throw new NoSuchElementException("No more permutations.");
    }

    StringBuilder permutation = new StringBuilder();
    for (int index : indices) {
      permutation.append(sequence.charAt(index));
    }

    history.push(permutation.toString());

    hasNext = incrementIndices();

    if (!hasNext && permLength < sequence.length()) {
      permLength++;
      indices = new int[permLength];
      for (int i = 0; i < permLength; i++) {
        indices[i] = i;
      }
      hasNext = true;
    }

    return permutation.toString();
  }


  private boolean incrementIndices() {
    int i = permLength - 1;
    while (i >= 0) {
      indices[i]++;
      if (indices[i] < sequence.length() - (permLength - (i + 1))) {
        return true;
      } else {
        indices[i] = i;
        i--;
      }
    }
    return false;
  }
}
