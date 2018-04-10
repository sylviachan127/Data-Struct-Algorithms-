/**
 * The interface for making Homework assignments gradable.
 *
 * The data structure typically doesn't require the functions defined
 *     in this interface, but they allow for easier grading,
 *    therefore, you are responsible for making your deliverables
 *     implement gradable
 *
 * @author Suvrat Bhooshan
 * @version 1.0
 *
 */
public interface Gradable<T> {
    /**
     * Returns the backing array of the data structure INCLUDING ANY EMPTY
     * SPACES THAT MAY BE AT THE END.
     * This should be a 1-line method; just return the backing array exactly as
     * it is.
     *
     * @return the backing array of the data structure
     */
    public T[] toArray();
}