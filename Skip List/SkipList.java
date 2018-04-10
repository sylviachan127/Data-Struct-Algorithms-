import java.util.HashSet;
import java.util.Set;

/**
 * @author Yuen Han Chan
 * @param <T>
 */

public class SkipList<T extends Comparable<? super T>> implements
    SkipListInterface<T> {
    private final CoinFlipper coinFlipper;
    private int size;
    private Node<T> head;

    /**
     * constructs a SkipList object that stores data in ascending order when an
     * item is inserted, the flipper is called until it returns a tails if for
     * an item the flipper returns n heads, the corresponding node has n + 1
     * levels
     *
     * @param coinFlipper
     *            the source of randomness
     */
    public SkipList(CoinFlipper coinFlipper) {
        this.coinFlipper = coinFlipper;
        size = 0;
        head = new Node<T>(null, 1);
    }

    @Override
    public T first() {
        if (head.getNext() == null) {
            return null;
        }
        Node<T> current = head;
        while (current.getLevel() != 1) {
            current = current.getDown();
        }
        return current.getNext().getData();
    }

    @Override
    public T last() {
        if (head.getNext() == null) {
            return null;
        }
        Node<T> current = head;
        while (current.getLevel() != 1) {
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current = current.getDown();
        }
        while (current.getNext() != null) {
            current = current.getNext();
        }
        return current.getData();
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        Node<T> current = head;
        while (current != null) {
            if ((current.getNext() != null)
                    && (data.compareTo(current.getNext().getData()) == 0)) {
                return true;
            } else if (((current.getNext() != null) && (data.compareTo(current
                    .getNext().getData()) > 0))) {
                current = current.getNext();
            } else {
                current = current.getDown();
            }
        }
        return false;
    }

    @Override
    public void put(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }

        int newHeight = 1;
        while (coinFlipper.flipCoin() == (CoinFlipper.Coin.HEADS)) {
            newHeight++;
        }

        while (head.getLevel() < newHeight) {
            Node<T> newHead = new Node<T>(null, head.getLevel() + 1);
            newHead.setDown(head);
            head.setUp(newHead);
            head = newHead;
        }

        Node<T> current = head;
        Node<T> lastAdd = null;
        while (current != null) {
            if (((current.getNext() == null) || (data.compareTo(current
                    .getNext().getData()) < 0))) {
                if (newHeight >= current.getLevel()) {
                    Node<T> newAdd = new Node<T>(data, current.getLevel());
                    newAdd.setNext(current.getNext());
                    current.setNext(newAdd);
                    if (lastAdd != null) {
                        lastAdd.setDown(newAdd);
                        newAdd.setUp(lastAdd);
                    }
                    lastAdd = newAdd;
                }
                current = current.getDown();
            } else {
                current = current.getNext();
            }
        }
        size++;
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        Node<T> current = head;
        while (current != null) {
            if ((current.getNext() != null)
                    && (data.compareTo(current.getNext().getData()) == 0)) {
                return current.getNext().getData();
            } else if (((current.getNext() != null) && (data.compareTo(current
                    .getNext().getData()) > 0))) {
                current = current.getNext();
            } else {
                current = current.getDown();
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        head = new Node<T>(null, 1);
    }

    @Override
    public Set<T> dataSet() {
        Set<T> dataSet = new HashSet<T>();
        if (size == 0) {
            return dataSet;
        }
        Node<T> first = head;
        while (first.getLevel() != 1) {
            first = first.getDown();
        }
        while (first.getNext() != null) {
            dataSet.add(first.getNext().getData());
            first = first.getNext();
        }
        return dataSet;
    }

    @Override
    public T remove(T data) {
        if (!(contains(data))) {
            return null;
        }
        if (data == null) {
            throw new IllegalArgumentException();
        }
        Node<T> current = head;
        while (current != null) {
            if ((current.getNext() != null)
                    && (data.compareTo(current.getNext().getData()) == 0)) {
                current.setNext(current.getNext().getNext());
            } else if (((current.getNext() != null) && (data.compareTo(current
                    .getNext().getData()) > 0))) {
                current = current.getNext();
            } else {
                current = current.getDown();
            }
        }
        size--;
        return null;
    }
}