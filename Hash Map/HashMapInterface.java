import java.util.Set;
import java.util.List;

/**
 * Interface for a hash map.
 *
 * @version 1.0
 */
public interface HashMapInterface<K, V> {
    int STARTING_SIZE = 10;
    double MAX_LOAD_FACTOR = 0.67;

    /**
     * Adds the given key-value pair to the HashMap.
     * If the key is already in the HashMap, then replace the value with this
     * value.
     *
     * Check to see if the backing array needs to be regrown BEFORE
     * adding. For example, If my HashMap has a backing array of size 5, and 3
     * elements in it, I should regrow at the start of the next add operation.
     *
     * Return null if the key was not already in the map. If it was in the map,
     * return the old value associated with it.
     *
     * Should run in O(1) with a good hash function, O(n) otherwise.
     * @param key
     * @param value
     * @throws IllegalArgumentException if key or value is null
     * @return null if the key was not already in the map.  If it was in the
     * map, return the old value associated with it.
     */
    V add(K key, V value);

    /**
     * Removes the value associated with the key from the map. Think through
     * what needs to happen at the appropriate index in the array.
     *
     * Should run in O(1) with a good hash function, O(n) otherwise.
     * @param key the key to remove
     * @throws IllegalArgumentException if key is null
     * @return the value previously associated with the key (null if it did not
     * exist)
     */
    V remove(K key);

    /**
     * Gets the value associated with the given key.
     * Should run in O(1) with a good hash function, O(n) otherwise
     * @param key
     * @throws IllegalArgumentException if key is null
     * @return the value associated with the give
     */
    V get(K key);

    /**
     * Returns whether or not the key is in the map.
     * Should run in O(1) with a good hash function, O(n) otherwise
     * @param key
     * @throws IllegalArgumentException if key is null
     * @return whether or not the key is in the map
     */
    boolean contains(K key);

    /**
     * Clears the table and resets it to the default size.
     * Should be O(1)
     */
    void clear();

    /**
     * Returns the number of elements in the map.
     * Should be O(1)
     * @return number of elements in the HashMap
     */
    int size();

    /**
     * Returns a Set view of the keys contained in this map.
     * Use @code{java.util.HashSet}.
     * Should be O(n)
     */
    Set<K> keySet();

    /**
     * Returns a List view of the values contained in this map.
     * Use any class that implements the List interface
     * This includes java.util.ArrayList and java.util.LinkedList.
     * Should be O(n)
     */
    List<V> values();
}
