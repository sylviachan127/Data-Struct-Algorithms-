/**
 * Gradable interface
 * for HW05 - HashMap
 *
 * This interface contains methods that a normal
 * data structure would not include, but we need
 * to grade your data structure.  Failure to
 * implement these methods may result in a zero.
 *
 * @version 1.0
 */
public interface Gradable<K, V> {

    /**
     *
     * @return the backing array of the data structure, not a copy.  INCLUDE
     * EMPTY SPACES.
     */
    MapEntry<K, V>[] toArray();
}
