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
    return size;
  }

  @Override
  public int height() {
    return heightHelper(root);
  }

  private int heightHelper(Node<T> node) {
    if (node == null) {
      return 0;
    }
    return 1 + Math.max(heightHelper(node.getLeft()), heightHelper(node.getRight()));
  }

  @Override
  public boolean present(T data) {
    return presentHelper(root, data);
  }

  private boolean presentHelper(Node<T> node, T data) {
    if (node == null) {
      return false;
    }
    int cmp = data.compareTo(node.getData());
    if (cmp < 0) {
      return presentHelper(node.getLeft(), data);
    } else if (cmp > 0) {
      return presentHelper(node.getRight(), data);
    } else {
      return true;
    }
  }

  @Override
  public T minimum() {
    if (root == null) {
      return null;
    }
    return minimumHelper(root).getData();
  }

  private Node<T> minimumHelper(Node<T> node) {
    if (node == null) {
      return null;
    }
    if (node.getLeft() == null) {
      return node;
    }
    return minimumHelper(node.getLeft());
  }

  @Override
  public T maximum() {
    if (root == null) {
      return null;
    }
    return maximumHelper(root).getData();
  }

  private Node<T> maximumHelper(Node<T> node) {
    if (node == null) {
      return null;
    }
    if (node.getRight() == null) {
      return node;
    }
    return maximumHelper(node.getRight());
  }

  @Override
  public String preOrder() {
    StringBuilder result = new StringBuilder();
    preOrderHelper(root, result);
    return formatResult(result);
  }

  @Override
  public String inOrder() {
    StringBuilder result = new StringBuilder();
    inOrderHelper(root, result);
    return formatResult(result);
  }

  @Override
  public String postOrder() {
    StringBuilder result = new StringBuilder();
    postOrderHelper(root, result);
    return formatResult(result);
  }

  private void preOrderHelper(Node<T> node, StringBuilder result) {
    if (node != null) {
      result.append(node.getData()).append(" ");
      preOrderHelper(node.getLeft(), result);
      preOrderHelper(node.getRight(), result);
    }
  }

  private void inOrderHelper(Node<T> node, StringBuilder result) {
    if (node != null) {
      inOrderHelper(node.getLeft(), result);
      result.append(node.getData()).append(" ");
      inOrderHelper(node.getRight(), result);
    }
  }

  private void postOrderHelper(Node<T> node, StringBuilder result) {
    if (node != null) {
      postOrderHelper(node.getLeft(), result);
      postOrderHelper(node.getRight(), result);
      result.append(node.getData()).append(" ");
    }
  }

  private String formatResult(StringBuilder result) {
    if (result.length() > 0) {
      return String.format("[%s]", result.toString().trim());
    }
    return "[]";
  }

  @Override
  public String toString() {
    return inOrder();
  }
}
