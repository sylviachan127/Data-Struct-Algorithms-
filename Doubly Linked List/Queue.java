/**
 * Your queue implementation. Don't add any new public methods.
 *
 * @author Yuen Han Chan
 * @version 1.0
 */
public class Queue<T> implements QueueInterface<T> {

    private final LinkedListInterface<T> queue;

    public Queue() {
        queue = new DoublyLinkedList<T>();
    }

    @Override
    public void enqueue(T t) {
        queue.addToBack(t);
    }

    @Override
    public T dequeue() {
        return queue.removeFromFront();
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

}
