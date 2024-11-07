package permutations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * An iterator that generates permutations of a given set of characters.
 */
public class Permutations implements BackAndForthIterator<String> {
  private final String sequence;
  private final int permLength;
  private final List<String> combinations;
  private int currentIndex;
  private boolean generated;

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
    this.combinations = new ArrayList<>();
    this.currentIndex = 0;
    this.generated = false;
  }

  private void generatePermutations() {
    if (!generated) {
      for (int length = permLength; length <= sequence.length(); length++) {
        generateCombinationsOfLengthIteratively(length);
      }
      generated = true;
    }
  }

  private void generateCombinationsOfLengthIteratively(int length) {
    int n = sequence.length();
    int[] indices = new int[length];

    for (int i = 0; i < length; i++) {
      indices[i] = i;
    }

    while (indices[0] <= n - length) {
      StringBuilder combination = new StringBuilder();

      for (int i = 0; i < length; i++) {
        combination.append(sequence.charAt(indices[i]));
      }

      combinations.add(combination.toString());

      int i = length - 1;
      while (i >= 0 && indices[i] == n - length + i) {
        i--;
      }

      if (i < 0) {
        break;
      }

      indices[i]++;
      for (int j = i + 1; j < length; j++) {
        indices[j] = indices[j - 1] + 1;
      }
    }
  }

  @Override
  public String previous() {
    if (!hasPrevious()) {
      throw new NoSuchElementException("No previous combination.");
    }
    return combinations.get(--currentIndex);
  }

  @Override
  public boolean hasPrevious() {
    return currentIndex > 0;
  }

  @Override
  public boolean hasNext() {
    if (!generated) {
      generatePermutations();
    }
    return currentIndex < combinations.size();
  }

  @Override
  public String next() {
    if (!generated) {
      generatePermutations();
    }
    if (!hasNext()) {
      throw new NoSuchElementException("No more combinations.");
    }
    return combinations.get(currentIndex++);
  }
}
