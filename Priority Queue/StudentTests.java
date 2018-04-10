import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

/**
 * Tests to test your Heap and Priority Queue classes
 *
 * @author CS 1332 TAs
 * @version 1.1
 */
public class StudentTests {
    private Heap<Integer> heap;
    private PriorityQueue<Integer> pQueue;

    @Before
    public void setUp() {
        heap = new Heap<Integer>();
        pQueue = new PriorityQueue<Integer>();
    }

    @Test(timeout = 200)
    public void testBasicAddInOrder1() {
        basicAdd(1, 2, 3);

        checkBackingArray(1, 2, 3);
    }

    @Test(timeout = 200)
    public void testPeek1() {
        basicAdd(4, 6, 1, 3, 2, 5);
        assertEquals(new Integer(1), heap.peek());
        assertEquals(6, heap.size());
    }

    @Test(timeout = 200)
    public void testIsEmpty() {
        assertTrue(heap.isEmpty());

        basicAdd(1, 2, 3, 4, 5, 6);

        assertFalse(heap.isEmpty());
    }

    /**
     * Adds all of the numbers from an array to the heap.
     *
     * @param arr The array of numbers to add.
     */
    private void basicAdd(Integer... arr) {
        int i = 0;
        while (i < arr.length && arr[i] != null) {
            heap.add(arr[i]);
            i++;
        }
    }

    @Test(timeout = 200)
    public void testBasicResize() {
        @SuppressWarnings("rawtypes")
        Comparable[] array = heap.toArray();

        // 10 should be the default size of the heap backing array
        assertEquals(10, array.length);

        int num = 11;
        addMany(num);

        array = heap.toArray();
        assertEquals(20, array.length);
    }


    @Test(timeout = 200)
    public void testBasicInsert() {
        pQueueBasicAdd();

        assertArrayEquals(new Comparable[] {null, 21, 34, 38, 82, 73, 56, null,
            null, null}, pQueue.toArray());
    }

    @Test(timeout = 200)
    public void testBasicRemove() {
        basicAdd(5, 2, 8, 12, 31, 4, 6, 11, 15);
        assertEquals((Integer) 2, heap.remove());

        checkBackingArray(4, 5, 6, 11, 31, 8, 15, 12);
    }

    @Test(timeout = 200)
    public void testPQueueRemove() {
        pQueueBasicAdd();

        assertEquals((Integer) 21, pQueue.findMin());
        assertEquals((Integer) 21, pQueue.deleteMin());

        assertArrayEquals(new Comparable[] {null, 34, 56, 38, 82, 73, null,
            null, null, null}, pQueue.toArray());
    }

    /**
     * Adds num elements to the heap.
     *
     * @param num The number of elements to add to the heap.
     */
    private void addMany(int num) {
        for (int i = 1; i <= num; i++) {
            heap.add(i);
        }
    }

    /**
     * Checks that the backing array maintains the right properties.
     *
     * @param numbers The numbers to compare to.
     */
    private void checkBackingArray(Integer... numbers) {
        @SuppressWarnings("rawtypes")
        Comparable[] backingArray = heap.toArray();
        assertNull(backingArray[0]);

        for (int i = 1; i < backingArray.length; i++) {
            if (i > numbers.length) {
                assertNull(backingArray[i]);
            } else {
                assertEquals(numbers[i - 1], backingArray[i]);
            }
        }
    }

    private void pQueueBasicAdd() {
        pQueue.insert(34);
        pQueue.insert(82);
        pQueue.insert(38);
        pQueue.insert(21);
        pQueue.insert(73);
        pQueue.insert(56);
    }
}
