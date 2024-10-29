package bst;

/**
 * This class represents a node in a binary search tree. It can store any type of
 * data that implements the Comparable interface
 *
 * @param <T> the type of element in the tree.
 */
public class Node<T extends Comparable<T>> {
  private T data;
  private Node<T> left;
  private Node<T> right;

  /**
   * Constructor to initialize the instance variables.
   *
   * @param data the data to be stored in the node
   */
  public Node(T data) {
    this.data = data;
    this.left = null;
    this.right = null;
  }

  public T getData() {
    return data;
  }

  public Node<T> getLeft() {
    return left;
  }

  public Node<T> getRight() {
    return right;
  }

  public void setData(T data) {
    this.data = data;
  }

  public void setLeft(Node<T> left) {
    this.left = left;
  }

  public void setRight(Node<T> right) {
    this.right = right;
  }
}
