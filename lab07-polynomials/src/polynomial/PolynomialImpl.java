package polynomial;

import java.util.Scanner;

/**
 * This class represents all the operations offered by a polynomial. A
 * polynomial is defined here as a function of one variable. The polynomial is a
 * weighted sum of terms (the weights are numeric). Each term may either be an
 * integer power of that variable, or some function in that variable, but never
 * both (i.e. (log x)^2 is not allowed).
 */
public class PolynomialImpl implements Polynomial {

  private TermNode head;

  PolynomialImpl() {
    this.head = null;
  }

  PolynomialImpl(String polyString) {
    this.head = null;
    parseString(polyString);
  }

  PolynomialImpl(TermNode head) {
    this.head = head;
  }

  private void parseString(String s) {
    Scanner scanner = new Scanner(s);
    while (scanner.hasNext()) {
      String termStr = scanner.next();
      if (termStr.matches("[-+]?\\d+(x\\^\\d+)?")) {
        int coefficient = parseCoefficient(termStr);
        int power = parsePower(termStr);
        if (power >= 0 && coefficient != 0) {
          addTerm(coefficient, power);
        }
      }
    }
  }

  private int parseCoefficient(String termStr) {
    if (termStr.contains("x")) {
      String[] parts = termStr.split("x");
      if (parts[0].isEmpty() || parts[0].equals("+")) {
        return 1;
      } else if (parts[0].equals("-")) {
        return -1;
      } else {
        return Integer.parseInt(parts[0]);
      }
    } else {
      return Integer.parseInt(termStr);
    }
  }

  private int parsePower(String termStr) {
    if (termStr.contains("^")) {
      return Integer.parseInt(termStr.split("\\^")[1]);
    } else if (termStr.contains("x")) {
      return 1;
    } else {
      return 0;
    }
  }

  @Override
  public Polynomial add(Polynomial other) throws IllegalArgumentException {
    if (!(other instanceof PolynomialImpl otherPoly)) {
      throw new IllegalArgumentException("Parameter must be of the same concrete type");
    }
    return new PolynomialImpl(addRecursive(this.head, otherPoly.head));
  }

  private TermNode addRecursive(TermNode p1, TermNode p2) {
    if (p1 == null) {
      return p2;
    }
    if (p2 == null) {
      return p1;
    }

    if (p1.getPower() == p2.getPower()) {
      int newCoefficient = p1.getCoefficient() + p2.getCoefficient();
      if (newCoefficient == 0) {
        return addRecursive(p1.getNext(), p2.getNext());
      }
      return new TermNode(newCoefficient, p1.getPower(), addRecursive(p1.getNext(), p2.getNext()));
    } else if (p1.getPower() > p2.getPower()) {
      return new TermNode(p1.getCoefficient(), p1.getPower(), addRecursive(p1.getNext(), p2));
    } else {
      return new TermNode(p2.getCoefficient(), p2.getPower(), addRecursive(p1, p2.getNext()));
    }
  }

  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Power must be non-negative");
    }
    this.head = addTermRecursive(this.head, coefficient, power);
  }

  private TermNode addTermRecursive(TermNode current, int coefficient, int power) {
    if (current == null) {
      return new TermNode(coefficient, power, null);
    }
    if (current.getPower() == power) {
      int newCoefficient = current.getCoefficient() + coefficient;
      if (newCoefficient == 0) {
        return current.getNext();
      }
      current.setCoefficient(newCoefficient);
      return current;
    } else if (current.getPower() > power) {
      return new TermNode(current.getCoefficient(), current.getPower(),
              addTermRecursive(current.getNext(), coefficient, power));
    } else {
      return new TermNode(coefficient, power, current);
    }
  }

  @Override
  public boolean isSame(Polynomial poly) {
    if (!(poly instanceof PolynomialImpl other)) {
      return false;
    }
    return isSameRecursive(this.head, other.head);
  }

  private boolean isSameRecursive(TermNode p1, TermNode p2) {
    if (p1 == null && p2 == null) {
      return true;
    }
    if (p1 == null || p2 == null) {
      return false;
    }
    return p1.getCoefficient() == p2.getCoefficient() && p1.getPower() == p2.getPower()
            && isSameRecursive(p1.getNext(), p2.getNext());
  }

  @Override
  public double evaluate(double x) {
    return evaluateRecursive(this.head, x);
  }

  private double evaluateRecursive(TermNode current, double x) {
    if (current == null) {
      return 0;
    }
    return current.getCoefficient() * Math.pow(x, current.getPower())
            + evaluateRecursive(current.getNext(), x);
  }

  @Override
  public int getCoefficient(int power) {
    return getCoefficientRecursive(this.head, power);
  }

  private int getCoefficientRecursive(TermNode current, int power) {
    if (current == null) {
      return 0;
    }
    if (current.getPower() == power) {
      return current.getCoefficient();
    }
    return getCoefficientRecursive(current.getNext(), power);
  }

  @Override
  public int getDegree() {
    return (head == null) ? 0 : head.getPower();
  }

  @Override
  public String toString() {
    if (head == null) {
      return "0";
    }
    return toStringRecursive(head);
  }

  private String toStringRecursive(TermNode current) {
    if (current == null) {
      return "";
    }
    String termStr = (current.getPower() == 0) ? String.valueOf(current.getCoefficient())
            : (current.getPower() == 1) ? current.getCoefficient() + "x"
            : current.getCoefficient() + "x^" + current.getPower();
    String rest = toStringRecursive(current.getNext());
    if (!rest.isEmpty()) {
      if (rest.charAt(0) != '-') {
        rest = " + " + rest;
      }
    }
    return termStr + rest;
  }
}
