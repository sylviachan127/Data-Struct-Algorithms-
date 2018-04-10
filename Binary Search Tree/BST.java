import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuen Han Chan
 * @param <T>
 */
public class BST<T extends Comparable<T>> implements BSTInterface<T> {
    private int size = 0;
    private Node<T> root = null;
    private final ArrayList<T> preOrder = new ArrayList<T>();
    private final ArrayList<T> inOrder = new ArrayList<T>();
    private final ArrayList<T> postOrder = new ArrayList<T>();
    private final ArrayList<T> levelOrder = new ArrayList<T>();
    private final ArrayList<Node<T>> levelOrderNode = new ArrayList<Node<T>>();

    @Override
    public void add(T data) {
        root = add(root, data);
        size++;
    }

    public Node<T> add(Node<T> node, T data) {
        if (node == null) {
            return new Node<T>(data);
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(add(node.getLeft(), data));
        } else {
            node.setRight(add(node.getRight(), data));
        }
        return node;
    }

    @Override
    public T remove(T data) {
        if (!contains(data)) {
            return null;
        } else {
            root = remove(root, data);
            size--;
            return data;
        }
    }

    public Node<T> remove(Node<T> tempNode, T data) {
        if (tempNode == null) {
            return null;
        } else if (data.compareTo(tempNode.getData()) < 0) {
            tempNode.setLeft(remove(tempNode.getLeft(), data));
        } else if (data.compareTo(tempNode.getData()) > 0) {
            tempNode.setRight(remove(tempNode.getRight(), data));
        } else if (tempNode.getData().compareTo(data) == 0) {
            if (tempNode.getLeft() == null) {
                tempNode.setData(tempNode.getRight().getData());
                tempNode.setLeft(tempNode.getRight().getLeft());
            }
            if (tempNode.getRight() == null) {
                tempNode.setData(tempNode.getLeft().getData());
                tempNode.setLeft(tempNode.getLeft().getLeft());
            } else {
                Node<T> oneLeft = tempNode.getLeft();
                Node<T> predecessor = oneLeft;
                while (oneLeft.getRight() != null) {
                    predecessor = oneLeft.getRight();
                }
                tempNode.setData(predecessor.getData());
                tempNode = remove(predecessor, predecessor.getData());
            }
        }
        return tempNode;
    }

    @Override
    public T get(T data) {
        Node<T> tempNode = root;
        while (tempNode != null) {
            if (data.compareTo(tempNode.getData()) < 0) {
                tempNode = tempNode.getLeft();
            } else if (data.compareTo(tempNode.getData()) > 0) {
                tempNode = tempNode.getRight();
            } else {
                return tempNode.getData();
            }
        }
        return null;
    }

    @Override
    public boolean contains(T data) {
        Node<T> tempNode = root;
        while (tempNode != null) {
            if (data.compareTo(tempNode.getData()) < 0) {
                tempNode = tempNode.getLeft();
            } else if (data.compareTo(tempNode.getData()) > 0) {
                tempNode = tempNode.getRight();
            } else if (tempNode.getData().equals(data)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> preorder() {
        preorder(root);
        return preOrder;
    }

    private void preorder(Node<T> node) {
        if (node != null) {
            preOrder.add(node.getData());
            preorder(node.getLeft());
            preorder(node.getRight());
        }
    }

    @Override
    public List<T> postorder() {
        postorder(root);
        return postOrder;
    }

    private void postorder(Node<T> node) {
        if (node != null) {
            postorder(node.getLeft());
            postorder(node.getRight());
            postOrder.add(node.getData());
        }
    }

    @Override
    public List<T> inorder() {
        inorder(root);
        return inOrder;
    }

    private void inorder(Node<T> node) {
        if (node != null) {
            inorder(node.getLeft());
            inOrder.add(node.getData());
            inorder(node.getRight());
        }
    }

    @Override
    public List<T> levelorder() {
        Node<T> tempNode = root;
        while (tempNode != null) {
            levelOrder.add(tempNode.getData());
            levelOrderNode.add(tempNode);
            if ((tempNode.getLeft()) != null) {
                levelOrder.add(tempNode.getLeft().getData());
                levelOrderNode.add(tempNode.getLeft());
            }
            if ((tempNode.getRight()) != null) {
                levelOrder.add(tempNode.getRight().getData());
                levelOrderNode.add(tempNode.getRight());
            }
            tempNode = levelOrderNode.remove(0);
        }
        return levelOrder;
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    private int height(Node node) {
        if (node == null) {
            return -1;
        }
        return Math.max(height(node.getLeft()), height(node.getRight())) + 1;
    }

    @Override
    public int height() {
        return height(root);
    }

    @Override
    public Node<T> getRoot() {
        return root;
    }
}