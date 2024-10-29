package bst;

/**
 * This class implements the BinarySearchTree interface. It represents a binary
 * search tree that can store any type of data that implements the Comparable
 * interface
 *
 * @param <T> the type of element in the tree.
 */
public class BinarySearchTreeImpl<T extends Comparable<T>> implements BinarySearchTree<T> {

  private Node<T> root;
  private int size;

  /**
   * Add data to the binary search tree. This is ignored if the data item is already present.
   * Constructor to initialize the instance variables
   */
  public BinarySearchTreeImpl() {
    this.root = null;
    this.size = 0;
  }

  @Override
  public void add(T data) {
    if (data == null) {
      throw new IllegalArgumentException("Data cannot be null");
    }
    root = addHelper(root, data);
  }

  private Node<T> addHelper(Node<T> node, T data) {
    if (node == null) {
      size++;
      return new Node<>(data);
    }
    if (data.compareTo(node.getData()) < 0) {
      node.setLeft(addHelper(node.getLeft(), data));
    } else if (data.compareTo(node.getData()) > 0) {
      node.setRight(addHelper(node.getRight(), data));
    }
    return node;
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public int height() {
    return 0;
  }

  @Override
  public boolean present(T data) {
    return false;
  }

  @Override
  public T minimum() {
    return null;
  }

  @Override
  public T maximum() {
    return null;
  }

  @Override
  public String preOrder() {
    return "";
  }

  @Override
  public String inOrder() {
    return "";
  }

  @Override
  public String postOrder() {
    return "";
  }
}
