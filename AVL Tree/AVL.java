import java.util.ArrayList;
import java.util.List;

/**
 * My AVL implementation.
 *
 * @author Yuen Han Chan
 */
public class AVL<T extends Comparable<T>> implements AVLInterface<T>,
    Gradable<T> {

    // Do not add additional instance variables
    private Node<T> root;
    private int size;
    private final ArrayList<T> preOrder = new ArrayList<T>();
    private final ArrayList<T> inOrder = new ArrayList<T>();
    private final ArrayList<T> postOrder = new ArrayList<T>();
    private final ArrayList<T> levelOrder = new ArrayList<T>();
    private final ArrayList<Node<T>> levelHelper = new ArrayList<>();

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        root = add(this.root, data);
        size++;
    }

    private Node<T> leftRotate(Node<T> node) {
        Node<T> leftChild = node.getLeft();
        node.setLeft(leftChild.getRight());
        leftChild.setRight(node);
        node.setHeight(height(node));
        leftChild.setHeight(height(leftChild));
        return leftChild;
    }

    private Node<T> rightRotate(Node<T> node) {
        Node<T> rightChild = node.getRight();
        node.setRight(rightChild.getLeft());
        rightChild.setLeft(node);
        node.setHeight(height(node));
        rightChild.setHeight(height(rightChild));
        return rightChild;
    }

    private Node<T> doubleLeftRotate(Node<T> node) {
        node.setLeft(rightRotate(node.getLeft()));
        Node<T> temp = leftRotate(node);
        return temp;
    }

    private Node<T> doubleRightRotate(Node<T> node) {
        node.setRight(leftRotate(node.getRight()));
        Node<T> temp = rightRotate(node);
        return temp;
    }

    private Node<T> checkBalance(Node<T> node, T data) {
        node.setHeight(height(node));
        node.setBalanceFactor(height(node.getLeft()) - height(node.getRight()));
        if ((node.getBalanceFactor() == 2)) {
            if (data.compareTo(node.getLeft().getData()) < 0) {
                node = leftRotate(node);
            } else {
                node = doubleLeftRotate(node);
            }
        }
        if ((node.getBalanceFactor() == -2)) {
            if (data.compareTo(node.getRight().getData()) > 0) {
                node = rightRotate(node);
            } else {
                node = doubleRightRotate(node);
            }
        }
        return node;
    }

    private Node<T> add(Node<T> node, T data) {
        if (node == null) {
            Node<T> newAdd = new Node<T>(data);
            newAdd.setHeight(0);
            newAdd.setBalanceFactor(0);
            return newAdd;
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(add(node.getLeft(), data));
        } else {
            node.setRight(add(node.getRight(), data));
        }
        node = checkBalance(node, data);
        return node;
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        Node<T> returnNode = new Node<T>(null);
        root = remove(root, data, returnNode);
        root = rebalance(root);
        return returnNode.getData();
    }

    private Node<T> rebalance(Node<T> node) {
        if (node == null) {
            return null;
        }
        node.setHeight(height(node));
        node.setBalanceFactor(height(node.getLeft()) - height(node.getRight()));
        if ((node.getBalanceFactor() == 2)) {
            if (height(node.getLeft().getLeft()) >= height(node.getLeft()
                    .getRight())) {
                node = leftRotate(node);
            } else {
                node = doubleLeftRotate(node);
            }
        }

        if ((node.getBalanceFactor() == -2)) {
            if (height(node.getRight().getRight()) >= height(node.getRight()
                    .getLeft())) {
                node = rightRotate(node);
            } else {
                node = doubleRightRotate(node);
            }
        }
        return node;
    }

    private Node<T> remove(Node<T> node, T data, Node<T> returnNode) {
        if (node == null) {
            return null;
        }
        if (data.compareTo(node.getData()) < 0) {
            node.setLeft(remove(node.getLeft(), data, returnNode));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(remove(node.getRight(), data, returnNode));
        } else {
            size--;
            returnNode.setData(node.getData());
            if ((node.getLeft() != null) && (node.getRight() != null)) {
                node.setData(twoChild(node));
            } else if (node.getLeft() == null) {
                node = node.getRight();
            } else {
                node = node.getLeft();
            }
        }
        return node;
    }

    private T twoChild(Node<T> node) {
        Node<T> child = node.getRight();
        Node<T> childParent = null;
        while (child.getLeft() != null) {
            childParent = child;
            child = child.getLeft();
        }
        T ret = child.getData();
        if (childParent == null) {
            node.setRight(child.getRight());
        } else {
            childParent.setLeft(child.getRight());
        }
        return ret;
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
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
        if (data == null) {
            throw new IllegalArgumentException();
        }
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
    public boolean isEmpty() {
        return size == 0;
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
    public void clear() {
        root = null;
        size = 0;
    }

    private int height(Node<T> node) {
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

    @Override
    public List<T> levelorder() {
        levelOrder.add(root.getData());
        levelHelper.add(root);
        while (levelHelper.size() != 0) {
            Node<T> node = levelHelper.remove(0);
            if (node.getLeft() != null) {
                levelOrder.add(node.getLeft().getData());
                levelHelper.add(node.getLeft());
            }
            if (node.getRight() != null) {
                levelOrder.add(node.getRight().getData());
                levelHelper.add(node.getRight());
            }
        }
        return levelOrder;
    }
}
