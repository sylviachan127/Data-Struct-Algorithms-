/**
 *Interface for the Priority Queue class
 *
 *@author Suvrat Bhooshan
 *@version 1.0
 */

public interface PriorityQueueInterface<T extends Comparable<? super T>> {

    /**
     * Inserts an element in order in the Priority Queue
     * Should throw IllegalArgumentException if item == null
     * Big O - O(log(n))
     * @param item the item to be inserted, it should be a data that does have
     * an order property
     */
    public void insert(T item);

    /**
     * returns the minimum value without changing the priority queue
     * Should return null if queue is empty
     * Big O - O(1)
     * @return the minimum value without changing the priority queue
     */
    public T findMin();

    /**
     * deletes and return the smallest item in the structure
     * Big O - O(log(n))
     * @return the minimum value
     */
    public T deleteMin();

    /**
     * Checks if the queue is empty or not
     * Big O - O(1)
     * @return true if queue is empty, else false
     */
    public boolean isEmpty();

    /**
     * Makes the queue empty
     * Big O - O(1)
     */
    public void makeEmpty();
}
