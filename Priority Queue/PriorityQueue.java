/**
 * @author Yuen HanChan
 * @param <T>
 */
public class PriorityQueue<T extends Comparable<? super T>> implements
    PriorityQueueInterface<T>, Gradable<T> {
    private Heap<T> priorityQ = new Heap<T>();

    @Override
    public void insert(T item) {
        priorityQ.add(item);
    }

    @Override
    public T findMin() {
        if (priorityQ == null) {
            return null;
        }
        return priorityQ.peek();
    }

    @Override
    public T deleteMin() {
        return priorityQ.remove();
    }

    @Override
    public boolean isEmpty() {
        return priorityQ.isEmpty();
    }

    @Override
    public void makeEmpty() {
        priorityQ = new Heap<T>();
    }

    @Override
    public T[] toArray() {
        return priorityQ.toArray();
    }

}
