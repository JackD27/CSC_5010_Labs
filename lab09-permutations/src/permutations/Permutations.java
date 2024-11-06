package permutations;

/**
 * An iterator that generates permutations of a given set of characters.
 */
public class Permutations implements BackAndForthIterator<String> {
  private final String chars;
  private final int length;
  private final int[] indices;
  private boolean hasNext;
  private boolean hasPrevious;


  /**
   * Constructs a new permutation iterator for the given characters.
   *
   * @param chars the characters to permute
   */
  public Permutations(String chars) {
    if (chars == null || chars.isEmpty()) {
      throw new IllegalArgumentException("Invalid input parameters.");
    }

    this.chars = chars;
    this.length = 1;
    this.indices = new int[length];

    for (int i = 0; i < length; i++) {
      indices[i] = i;
    }

    this.hasNext = true;
    this.hasPrevious = false;
  }

  /**
   * Constructs a new permutation iterator for the given characters and length.
   *
   * @param chars the characters to permute
   * @param length the length of the permutations
   */
  public Permutations(String chars, int length) {
    if (chars == null || chars.isEmpty() || length < 1 || length > chars.length()) {
      throw new IllegalArgumentException("Invalid input parameters.");
    }

    this.chars = chars;
    this.length = length;
    this.indices = new int[length];

    for (int i = 0; i < length; i++) {
      indices[i] = i;
    }

    this.hasNext = true;
    this.hasPrevious = false;
  }

  @Override
  public String previous() {
    if (!hasPrevious) {
      throw new IllegalArgumentException("No previous permutation.");
    }

    StringBuilder prevPermutation = new StringBuilder();
    for (int index : indices) {
      prevPermutation.append(chars.charAt(index));
    }

    hasPrevious = generatePreviousPermutation();
    hasNext = true;

    return prevPermutation.toString();
  }

  @Override
  public boolean hasPrevious() {
    return hasPrevious;
  }

  @Override
  public boolean hasNext() {
    return hasNext;
  }

  @Override
  public String next() {
    if (!hasNext) {
      throw new IllegalArgumentException("No more permutations.");
    }

    StringBuilder currentPermutation = new StringBuilder();
    for (int index : indices) {
      currentPermutation.append(chars.charAt(index));
    }

    hasNext = generateNextPermutation();
    hasPrevious = true;

    return currentPermutation.toString();
  }

  private boolean generateNextPermutation() {
    int i = length - 1;

    while (i > 0 && indices[i - 1] >= indices[i]) {
      i--;
    }

    if (i <= 0) {
      return false;  // No more permutations
    }

    int j = length - 1;
    while (indices[j] <= indices[i - 1]) {
      j--;
    }

    swap(indices, i - 1, j);

    j = length - 1;
    while (i < j) {
      swap(indices, i, j);
      i++;
      j--;
    }

    return true;
  }

  private boolean generatePreviousPermutation() {
    int i = length - 1;

    while (i > 0 && indices[i - 1] <= indices[i]) {
      i--;
    }

    if (i <= 0) {
      return false;
    }

    int j = length - 1;
    while (indices[j] >= indices[i - 1]) {
      j--;
    }

    swap(indices, i - 1, j);

    j = length - 1;
    while (i < j) {
      swap(indices, i, j);
      i++;
      j--;
    }

    return true;
  }

  private void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }
}
