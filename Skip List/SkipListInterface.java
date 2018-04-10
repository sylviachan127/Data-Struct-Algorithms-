import java.util.Set;

/**
 * Interface for a skip list.
 *
 * @version 1.0
 */
public interface SkipListInterface<T extends Comparable<? super T>> {

    /**
     * Finds and returns the first item in the skip list. If the list is empty,
     * return {@code null}.
     * @return the first item in the skip list, or null
     */
    public T first();

    /**
     * Finds and returns the last item in the skip list. If the skip list is
     * empty, return {@code null}.
     * @return the last element in the skip list, or null
     */
    public T last();

    /**
     * Searches the skip list for a given data. If the list is empty, return
     * {@code false}.
     * @param data an item that is equal to some other item in the skip list
     * @throws IllegalArgumentException if the parameter is null
     * @return true if the data is in the skip list, false otherwise
     */
    public boolean contains(T data);

    /**
     * Adds a new item into the skip list.
     * @param data the item to put into the skip list
     * @throws IllegalArgumentException if any parameter is null
     */
    public void put(T data);


    /**
     * Removes a item from the skip list.
     * @param data an item that is equal to some other item in the skip list
     * @throws IllegalArgumentException if the parameter is null
     * @return the item removed if it was in the skip list, otherwise null. Do
     * NOT just return what was passed in.
     */
    public T remove(T data);

    /**
     * Finds and returns the data found in the skip list that is equal to the
     * data passed in.
     * @param data a item that is equal to some other item in the skip list
     * @throws IllegalArgumentException if the parameter is null
     * @return the data in the skip list if it exists, null otherwise. Do NOT
     * just return what was passed in.
     */
    public T get(T data);

    /**
     * Gives the size of the skip list.
     * @return the number of items in the skip list
     */
    public int size();

    /**
     * Clears the list. The size should be zero after this method is called.
     */
    public void clear();

    /**
     * The data of each item of the skip list. If the list is empty, return an
     * empty set.
     * @return a set of all the data in the skip list
     */
    public Set<T> dataSet();
}
