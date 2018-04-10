import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.Before;

/**
 * Student JUnits for HW02
 * @version 1.1
 * @author Albert Shaw
 */
public class HW2JUnitsStudent {

    private LinkedListInterface<Integer> list;
    private StackInterface<Integer> stack;
    private QueueInterface<Integer> queue;

    @Before
    public void setUp() {
        list = new DoublyLinkedList<Integer>();
        stack = new Stack<Integer>();
        queue = new Queue<Integer>();

    }
    @Test (timeout = 200)
    public void getHeadTailNull() {
        assertEquals(list.getHead(), null);
        assertEquals(list.getTail(), null);
    }

    /*
     * LinkedList Tests
     */

    @Test (timeout = 200)
    public void testLinkedListAddToFront1() {
        list.addToFront(new Integer(1));
        Object[] result = list.toArray();
        assertEquals(result[0], new Integer(1));
        assertEquals(1, result.length);
        checkLinkedList(list, new Integer[]{1});
    }

    @Test (timeout = 200)
    public void testLinkedListAddToFront2() {
        list.addToFront(new Integer(0));
        list.addToFront(new Integer(1));
        list.addToFront(new Integer(2));
        list.addToFront(new Integer(3));
        list.addToFront(new Integer(4));
        list.addToFront(new Integer(5));
        Object[] result = list.toArray();
        for (int i = 0; i < result.length; i++) {
            assertEquals(result[i], new Integer(result.length - i - 1));
        }
        checkLinkedList(list, new Integer[]{5, 4, 3, 2, 1, 0});
    }

    @Test (timeout = 200)
    public void testLinkedListAddToBack1() {
        list.addToBack(new Integer(1));
        Object[] result = list.toArray();
        assertEquals(result[0], new Integer(1));
        assertEquals(1, result.length);
        checkLinkedList(list, new Integer[]{1});
    }

    @Test (timeout = 200)
    public void testLinkedListAddToBack2() {
        list.addToBack(new Integer(0));
        list.addToBack(new Integer(1));
        list.addToBack(new Integer(2));
        list.addToBack(new Integer(3));
        list.addToBack(new Integer(4));
        list.addToBack(new Integer(5));
        Object[] result = list.toArray();
        for (int i = 0; i < result.length; i++) {
            assertEquals(result[i], new Integer(i));
        }
        checkLinkedList(list, new Integer[]{0, 1, 2, 3, 4, 5});
    }

    @Test(timeout = 200)
    public void testGet1() {
        list.addToBack(new Integer(0));
        assertEquals(new Integer(0), list.get(0));
    }

    @Test(timeout = 200)
    public void testGet2() {
        list.addToBack(new Integer(0));
        list.addToBack(new Integer(1));
        list.addToBack(new Integer(2));

        assertEquals(new Integer(0), list.get(0));
        assertEquals(new Integer(1), list.get(1));
        assertEquals(new Integer(2), list.get(2));
    }

    @Test(timeout = 200)
    public void testAddAtIndex1() {
        list.addAtIndex(0, new Integer(0));
        list.addAtIndex(1, new Integer(2));
        list.addAtIndex(1, new Integer(1));

        checkLinkedList(list, new Integer[]{0, 1, 2});
    }

    @Test(timeout = 200)
    public void testRemoveAtIndex1() {
        list.addAtIndex(0, new Integer(0));
        list.addAtIndex(1, new Integer(2));
        list.addAtIndex(1, new Integer(1));

        assertEquals(new Integer(1), list.removeAtIndex(1));
        checkLinkedList(list, new Integer[]{0, 2});
        assertEquals(new Integer(2), list.removeAtIndex(1));
        checkLinkedList(list, new Integer[]{0});
        assertEquals(new Integer(0), list.removeAtIndex(0));
        checkLinkedList(list, new Integer[0]);
    }

    @Test (timeout = 200)
    public void testLinkedListRemoveEmpty() {
        assertEquals(list.removeFromFront(), null);
    }

    @Test (timeout = 200)
    public void testLinkedListRemoveFromFront1() {
        list.addToFront(new Integer(1));
        assertEquals(list.removeFromFront(), new Integer(1));
        checkLinkedList(list, new Integer[0]);
    }

    @Test (timeout = 200)
    public void testLinkedListRemoveFromFront2() {
        list.addToFront(new Integer(0));
        list.addToFront(new Integer(1));
        list.addToFront(new Integer(2));
        list.addToFront(new Integer(3));
        list.addToFront(new Integer(4));
        list.addToFront(new Integer(5));
        list.removeFromFront();
        Object[] result = list.toArray();
        for (int i = 0; i < result.length; i++) {
            assertEquals(result[i], new Integer(result.length - i - 1));
        }
        checkLinkedList(list, new Integer[]{4, 3, 2, 1, 0});
    }

    @Test (timeout = 200)
    public void testLinkedListRemoveFromBack1() {
        assertEquals(list.removeFromBack(), null);
    }

    @Test (timeout = 200)
    public void testLinkedListRemoveFromBack2() {
        list.addToFront(new Integer(1));
        assertEquals(list.removeFromBack(), new Integer(1));
        Object[] result = list.toArray();
        checkLinkedList(list, new Integer[0]);
    }

    @Test (timeout = 200)
    public void testLinkedListRemoveFromBack3() {
        list.addToFront(new Integer(0));
        list.addToFront(new Integer(1));
        list.addToFront(new Integer(2));
        list.addToFront(new Integer(3));
        list.addToFront(new Integer(4));
        list.addToFront(new Integer(5));
        list.removeFromBack();
        Object[] result = list.toArray();
        for (int i = 0; i < result.length; i++) {
            assertEquals(result[i], new Integer(result.length - i));
        }
        checkLinkedList(list, new Integer[]{5, 4, 3, 2, 1});
    }

    @Test (timeout = 200)
    public void testIsEmpty1() {
        list.addToBack(new Integer(1));
        assertFalse(list.isEmpty());
    }

    @Test (timeout = 200)
    public void testSizeAddToBack1() {
        list.addToBack(new Integer(1));
        assertEquals(list.size(), 1);
    }

    @Test (timeout = 200)
    public void testSizeAddToFront1() {
        list.addToFront(new Integer(1));
        assertEquals(list.size(), 1);
    }

    @Test (timeout = 200)
    public void testSizeRemoveFromFront1() {
        list.addToFront(new Integer(1));
        list.removeFromFront();
        assertEquals(list.size(), 0);
    }

    @Test (timeout = 200)
    public void testSizeRemoveFromBack1() {
        list.addToFront(new Integer(1));
        list.removeFromBack();
        assertEquals(list.size(), 0);
    }

    @Test (timeout = 200)
    public void testClear1() {
        list.addToBack(new Integer(0));
        list.addToBack(new Integer(1));
        list.addToBack(new Integer(2));
        list.addToBack(new Integer(3));
        list.addToBack(new Integer(4));
        list.addToBack(new Integer(5));
        list.clear();

        checkLinkedList(list, new Integer[0]);
    }

    /*
     * Stack Tests
     */
    @Test (timeout = 200)
    public void testStackPushPop1() {
        stack.push(new Integer(1));
        assertEquals(stack.pop(), new Integer(1));

    }

    @Test (timeout = 200)
    public void testStackPopNull() {
        assertEquals(stack.pop(), null);
    }

    @Test (timeout = 200)
    public void testStackSize1() {
        assertEquals(stack.size(), 0);
    }

    @Test (timeout = 200)
    public void testStackIsEmpty1() {
        assertTrue(stack.isEmpty());
    }

    /*
     * Queue Tests
     */
    @Test (timeout = 200)
    public void testQueueEnqueueDequeue1() {
        queue.enqueue(new Integer(1));
        assertEquals(queue.dequeue(), new Integer(1));
    }

    @Test (timeout = 200)
    public void testQueueDequeueNull() {
        assertEquals(queue.dequeue(), null);
    }

    @Test (timeout = 200)
    public void testQueueSize() {
        assertEquals(queue.size(), 0);
    }

    @Test (timeout = 200)
    public void testQueueIsEmpty1() {
        assertTrue(queue.isEmpty());
    }

    private void checkLinkedList(LinkedListInterface<Integer> list,
                                 Object[] array) {
        Node<Integer> head = list.getHead();
        Node<Integer> tail = list.getTail();
        if (array.length == 0) {
            assertTrue(head == null);
            assertTrue(tail == null);
        } else {
            assertEquals(head.getData(), array[0]);
            Node<Integer> current = head;
            Node<Integer> next = head;
            for (int x = 0; x < array.length; x++) {
                current = next;
                assertEquals(current.getData(), array[x]);
                next = current.getNext();
            }
            assertTrue(current == tail);

            current = tail;
            next = tail;
            for (int x = array.length - 1; x >= 0; x--) {
                current = next;
                assertEquals(current.getData(), array[x]);
                next = current.getPrevious();
            }
            assertTrue(current == head);
            assertEquals(tail.getData(), array[array.length - 1]);
            assertTrue(head.getPrevious() == null);
            assertTrue(tail.getNext() == null);
        }
    }

}
