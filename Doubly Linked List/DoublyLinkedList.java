/**
 * Doubly linked list implementation
 *
 * @author Yuen Han Chan
 * @version 1.0
 */
public class DoublyLinkedList<T> implements LinkedListInterface<T> {

    private T[] doubleLinked;
    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;

    @Override
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else if ((head == null) || (index == 0)) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            Node<T> current = head;
            int headCount = 0;
            while (headCount != index) {
                current = current.getNext();
                headCount++;
            }
            Node<T> newAdd = new Node<T>(data);
            newAdd.setPrevious(current.getPrevious());
            newAdd.setNext(current);
            current.getPrevious().setNext(newAdd);
            current.setPrevious(newAdd);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else if (head == null) {
            return null;
        } else if (index == 0) {
            return head.getData();
        } else if (index == (size - 1)) {
            return tail.getData();
        } else {
            Node<T> current = head;
            int headCount = 0;
            while (headCount != index) {
                current = current.getNext();
                headCount++;
            }
            return current.getData();
        }
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else if (head == null) {
            return null;
        } else if (index == 0) {
            return removeFromFront();
        } else if (index == (size - 1)) {
            return removeFromBack();
        } else {
            Node<T> current = head;
            int headCount = 0;
            while (headCount != (index - 1)) {
                current = current.getNext();
                headCount++;
            }
            T data = current.getNext().getData();
            current.getNext().getNext().setPrevious(current);
            current.setNext(current.getNext().getNext());
            size--;
            return data;
        }
    }

    @Override
    public void addToFront(T t) {
        if (head == null) {
            head = new Node<T>(t);
            tail = head;
            size++;
        } else {
            Node<T> newAdd = new Node<T>(t);
            newAdd.setNext(head);
            head.setPrevious(newAdd);
            head = newAdd;
            size++;
        }
    }

    @Override
    public void addToBack(T t) {
        if (head == null) {
            addToFront(t);
        } else if (size == 1) {
            tail = new Node<T>(t);
            tail.setPrevious(head);
            head.setNext(tail);
            size++;
        } else {
            Node<T> newAdd = new Node<T>(t);
            newAdd.setPrevious(tail);
            tail.setNext(newAdd);
            tail = newAdd;
            size++;
        }
    }

    @Override
    public T removeFromFront() {
        if (head == null) {
            return null;
        } else if (size == 1) {
            T data = head.getData();
            clear();
            return data;
        } else if (size == 2) {
            T data = head.getData();
            head.getNext().setPrevious(null);
            head = head.getNext();
            size--;
            return data;
        } else {
            Node<T> front = head;
            T data = front.getData();
            front.getNext().setPrevious(null);
            head = front.getNext();
            size--;
            return data;
        }
    }

    @Override
    public T removeFromBack() {
        if (head == null) {
            return null;
        } else if (size == 1 || size == 2) {
            if (size == 1) {
                T data = head.getData();
                clear();
                return data;
            } else {
                T data = tail.getData();
                head.setNext(null);
                tail = head;
                size--;
                return data;
            }
        } else {
            T data = tail.getData();
            tail.getPrevious().setNext(null);
            tail = tail.getPrevious();
            size--;
            return data;
        }
    }

    @Override
    public T[] toArray() {
        doubleLinked = (T[]) new Object[size];
        Node<T> current = head;
        for (int start = 0; start < size; start++) {
            doubleLinked[start] = current.getData();
            current = current.getNext();
        }
        return doubleLinked;
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
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Reference to the head node of the linked list. Normally, you would not do
     * this, but we need it for grading your work. You will get a 0 if you do
     * not implement this method.
     *
     * @return Node representing the head of the linked list
     */
    @Override
    public Node<T> getHead() {
        return head;
    }

    /**
     * Reference to the tail node of the linked list. Normally, you would not do
     * this, but we need it for grading your work. You will get a 0 if you do
     * not implement this method.
     *
     * @return Node representing the tail of the linked list
     */
    @Override
    public Node<T> getTail() {
        return tail;
    }
}
