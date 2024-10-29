package bst;

public class BinarySearchTreeImpl<T extends Comparable<T>> implements BinarySearchTree<T> {

  private class Node {
    T data;
    Node left;
    Node right;

    Node(T data) {
      this.data = data;
      this.left = null;
      this.right = null;
    }
  }

  private Node root;
  private int size;

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

  private Node addHelper(Node node, T data) {
    if (node == null) {
      size++;
      return new Node(data);
    }
    if (data.compareTo(node.data) < 0) {
      node.left = addHelper(node.left, data);
    } else if (data.compareTo(node.data) > 0) {
      node.right = addHelper(node.right, data);
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
