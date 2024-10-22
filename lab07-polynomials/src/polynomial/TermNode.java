package polynomial;

/**
 * This class represents a term in a polynomial. A term is defined as a
 * coefficient and a power.
 */
public class TermNode {
  private int coefficient;
  private int power;
  private TermNode next;

  /**
   * Constructs a new TermNode with the given coefficient and power.
   *
   * @param coefficient the coefficient of the term
   * @param power       the power of the term
   */
  public TermNode(int coefficient, int power, TermNode next) {
    this.coefficient = coefficient;
    this.power = power;
    this.next = next;
  }

  public int getCoefficient() {
    return coefficient;
  }

  public void setCoefficient(int coefficient) {
    this.coefficient = coefficient;
  }

  public int getPower() {
    return power;
  }

  public void setPower(int power) {
    this.power = power;
  }

  public TermNode getNext() {
    return next;
  }

  public void setNext(TermNode next) {
    this.next = next;
  }
}
