/**
 * This interface describes the public methods needed for a
 * Linked List, which should be doubly linked and should
 * have a head and tail pointer.
 *
 * We've given you the expected Big-O for each method this time around.
 *
 * DO NOT ALTER THIS FILE!!
 *
 * @author CS 1332 TAs
 * @version 2.0
 * Changelog
 * 2.0 - Initial HW02 interface
 * 1.x - HW01
 */
public interface LinkedListInterface<T> {

    /**
     * Adds the element to the index specified.
     * Adding to indices 0 and size should be O(1), all other adds are O(n)
     *
     * Throw java.lang.IndexOutOfBoundsException if index < 0 or index > size.
     *
     * @param index The index where you want the new element.
     * @param data Any object of type T.
     */
    public void addAtIndex(int index, T data);

    /**
     * Returns the element at the given index.
     * This method must be O(1) for index 0 and index (size-1).
     * O(n) is expected for all other indices.
     *
     * Throw java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     *
     *
     * @param index The index of the element
     * @return The object stored at that index.
     */
    public T get(int index);

    /**
     * Removes and returns the element at index.
     * This method should be O(1) for 0 and index (size-1).
     * O(n) is expected for all other cases.
     *
     * Throw java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     *
     * @param index The index of the element
     * @return The object that was formerly at that index.
     */
    public T removeAtIndex(int index);

    /**
     * Add a new node to the front of your linked list
     * that holds the given data. Make sure to update head.
     *
     * Must be O(1)
     *
     * @param t The data that the new node should hold.
     */
    public void addToFront(T t);

    /**
     * Add a new node to the back of your linked list
     * that holds the given data. Make sure to update tail.
     *
     * Must be O(1)
     *
     * @param t The data that the new node should hold.
     */
    public void addToBack(T t);

    /**
     * Remove the front node from the list and return the
     * data from it.
     *
     * Must be O(1)
     *
     * @return The data from the front node or null.
     */
    public T removeFromFront();

    /**
     * Remove the back node from the list and return the
     * data from it.
     *
     * Must be O(1)
     *
     * @return The data from the last node or null.
     */
    public T removeFromBack();

    /**
     * Return the linked list represented as an array of objects.
     *
     * Must be O(n)
     *
     * @return A copy of the linked list data as an array.
     */
    public Object[] toArray();

    /**
     * Return a boolean value representing whether or not
     * the list is empty.
     *
     * Must be O(1)
     *
     * @return True if empty. False otherwise.
     */
    public boolean isEmpty();

    /**
     * Return the size of the list as an integer.
     *
     * Must be O(1)
     *
     * @return The size of the list.
     */
    public int size();

    /**
     * Clear the list.
     *
     * Must be O(1)
     */
    public void clear();

    /**
     * Reference to the head node of the linked list.
     * Normally, you would not do this, but we need it
     * for grading your work.
     *
     * DO NOT USE THIS IN YOUR CODE - FOR JUnit USE ONLY
     *
     * @return Node representing the head of the linked list
     */
    public Node<T> getHead();

    /**
     * Reference to the tail node of the linked list.
     * Normally, you would not do this, but we need it
     * for grading your work.
     *
     * DO NOT USE THIS IN YOUR CODE - FOR JUnit USE ONLY
     *
     * @return Node representing the tail of the linked list
     */
    public Node<T> getTail();
}
