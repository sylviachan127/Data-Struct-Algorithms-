/**
 * Interface for a Stack ADT
 *
 *
 * DO NOT MODIFY THIS FILE
 * @author CS 1332 TAs
 * @version 1.0
 */
public interface StackInterface<T> {
    /**
     * Push a new node onto the stack with the given data.  Must be O(1)
     *
     * @param t The data to push.
     */
    public void push(T t);

    /**
     * Pop from the stack.  Must be O(1)
     *
     * @return Data from node or null.
     */
    public T pop();

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
