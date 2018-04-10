/**
 * An interface for the DisjointSets ADT.  See Javadocs for instructions
 *
 * @author Albert Morlan
 * @version 1.0
 */
public interface DisjointSetsInterface<T> {

    /**
     * If either parameter is null, throw an {@code IllegalArgumentException}.
     *
     * @param u
     * @param v
     * @return true if the items given are in the same set, false otherwise
     */
    public boolean sameSet(T u, T v);

    /**
     * If either parameter is null, throw an {@code IllegalArgumentException}.
     *
     * Merges the sets u and v are in, do nothing if they are in the same set.
     * You are required to implement the following in this method:
     *  * Path compression: every node points to its root.
     *  * Merge by rank: Let the rank (estimate tree depth) of each set
     *  initially be 0. When merging two sets with different ranks, make the
     *  smaller-ranked root point to the larger-ranked root. If the two ranks
     *  are the same, choose one to point to the other, and increment the rank
     *  of the new set.
     *
     * @param u
     * @param v
     */
    public void merge(T u, T v);
}
