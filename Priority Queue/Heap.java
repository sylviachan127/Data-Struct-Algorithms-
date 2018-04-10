/**
 * @author Yuen HanChan
 * @param <T>
 */

public class Heap<T extends Comparable<? super T>> implements HeapInterface<T>,
    Gradable<T> {
    private int arraySize = 10;
    private T[] heapArray = (T[]) new Comparable[arraySize];
    private int size = 1;

    private void expend() {
        int newSize = arraySize * 2;
        arraySize = newSize;
        T[] newHeapArray = (T[]) new Comparable[arraySize];
        int i = 1;
        for (T data : heapArray) {
            newHeapArray[i] = data;
            i++;
        }
        heapArray = newHeapArray;
    }

    @Override
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if ((size) == heapArray.length) {
            expend();
        }
        heapArray[size] = item;
        int currentSize = size;
        T parent = heapArray[currentSize / 2];
        while (((currentSize != 1) && (item.compareTo(parent)) < 0)) {
            swapUp(item, parent, currentSize);
            currentSize /= 2;
            parent = heapArray[currentSize / 2];
        }
        size++;
    }

    private void swapUp(T child, T parent, int childSize) {
        T childSwap = child;
        T parentSwap = parent;
        heapArray[childSize] = parentSwap;
        heapArray[childSize / 2] = childSwap;
    }

    @Override
    public boolean isEmpty() {
        return size == 1;
    }

    @Override
    public T peek() {
        return heapArray[1];
    }

    @Override
    public T remove() {
        T returnData = heapArray[1];
        heapArray[1] = heapArray[size - 1];
        heapArray[size - 1] = null;
        shiftDown(1, heapArray[1]);
        size--;
        return returnData;
    }

    private void swapDown(T parent, T child, int parentSize, int childSize) {
        T childSwap = child;
        T parentSwap = parent;
        heapArray[parentSize] = childSwap;
        heapArray[childSize] = parentSwap;
    }

    /**
     * @param position
     *            of the data
     * @param the
     *            big data that got swap up
     */
    private void shiftDown(int position, T data) {
        if ((position * 2 < heapArray.length)
                && heapArray[position * 2] != null
                && (data.compareTo(heapArray[position * 2]) > 0)) {
            if ((position * 2 + 1 < heapArray.length)
                    && (heapArray[position * 2]
                            .compareTo(heapArray[position * 2 + 1]) < 0)) {
                swapDown(data, heapArray[position * 2], position, position * 2);
                position *= 2;
                shiftDown(position, data);
            }
        }
        if ((((position * 2) + 1) < heapArray.length)
                && heapArray[position * 2 + 1] != null
                && (data.compareTo(heapArray[position * 2 + 1]) > 0)) {
            if ((heapArray[position * 2 + 1].
                    compareTo(heapArray[position * 2]) < 0)) {
                swapDown(data, heapArray[position * 2 + 1], position,
                        position * 2 + 1);
                position = position * 2 + 1;
                shiftDown(position, data);
            }
        }
    }

    @Override
    public int size() {
        return size - 1;
    }

    @Override
    public T[] toArray() {
        return heapArray;
    }

}
