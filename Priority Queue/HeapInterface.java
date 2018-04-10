/**
 * The interface for the Heap Class
 *
 * @author Suvrat Bhooshan
 * @version 1.0
 */
public interface HeapInterface<T extends Comparable<? super T>> {

    /**
     * Adds item to this heap
     * Should throw IllegalArgumentException if item is null
     * Big O - O(log(n))
     * @param item the item to be added
     */
    public void add(T item);

    /**
     * Checks if the heap is empty or not
     * Big O - O(1)
     * @return true if this heap is empty, false otherwise
     */
    public boolean isEmpty();

    /**
     * Returns the minimum element of the heap without modifying the heap
     * Big O - O(1)
     * @return the minimum element of this heap
     */
    public T peek();

    /**
     * Removes and returns the minimum element of this heap
     * Big O - O(log(n))
     * @return the minimum element of this heap
     */
    public T remove();

    /**
     * The size of the heap
     * Big O - O(1)
     * @return the number of elements in this heap
     */
    public int size();

}
