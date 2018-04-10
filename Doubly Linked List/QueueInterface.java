/**
 * Interface for a Queue ADT
 *
 *
 * DO NOT MODIFY THIS FILE
 * @author CS 1332 TAs
 * @version 1.0
 */
public interface QueueInterface<T> {

    /**
     * Add a node with the given data
     * to the back of your queue.
     *
     * Must be O(1)
     *
     * @param t The data to add.
     */
    public void enqueue(T t);

    /**
     * Dequeue from the front of your queue.  Must be O(1)
     *
     * @return The data from the front node or null if empty.
     */
    public T dequeue();

    /**
     * Return the size of the stack.  Must be O(1)
     *
     * @return int size
     */
    public int size();

    /**
     * Return true if empty. False otherwise.  Must be O(1)
     *
     * @return boolean result
     */
    public boolean isEmpty();
}
