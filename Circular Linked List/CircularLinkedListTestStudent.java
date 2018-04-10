import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

/**
 * CircularLinkedListTests
 * Student Edition
 * @version 1.1
 * Changelog:
 * 1.1 Fix testAddFrontSingle() to actually add to the front.
 * 1.0 Initial release
 */
public class CircularLinkedListTestStudent {

    private LinkedListInterface<Integer> list;
    
    @Before
    public void setup() {
       list = new CircularLinkedList<Integer>();
    }

    @Test(timeout = 200)
    public void testAddFrontSingle() {
        list.addToFront(new Integer(1));
        assertEquals(1, list.size());
        assertSame(((CircularLinkedList<Integer>) list).getHead(), ((CircularLinkedList<Integer>) list).getTail());
        assertSame(((CircularLinkedList<Integer>) list).getHead().getNext(), ((CircularLinkedList<Integer>) list).getHead());
        assertEquals(new Integer(1), ((CircularLinkedList<Integer>) list).getHead().getData());
    }
    
    @Test(timeout = 200)
    public void testAddBackSingle() {
        list.addToBack(new Integer(1));
        assertEquals(1, list.size());
        assertSame(((CircularLinkedList<Integer>) list).getHead(), ((CircularLinkedList<Integer>) list).getTail());
        assertSame(((CircularLinkedList<Integer>) list).getHead().getNext(), ((CircularLinkedList<Integer>) list).getHead());
        assertEquals(new Integer(1), ((CircularLinkedList<Integer>) list).getHead().getData());
    }

    
    @Test(timeout = 200)
    public void testRemoveFront() {
        list.addToBack(new Integer(1));
        list.addToBack(new Integer(2));
        list.addToBack(new Integer(3));
        assertEquals(3, list.size());
        assertEquals(new Integer(1), ((CircularLinkedList<Integer>) list).getHead().getData());
        list.removeFromFront();
        assertEquals(2, list.size());
        assertSame(((CircularLinkedList<Integer>) list).getTail().getNext(), ((CircularLinkedList<Integer>) list).getHead());
        assertEquals(new Integer(2), ((CircularLinkedList<Integer>) list).getHead().getData());
    }

    @Test(timeout = 200)
    public void testRemoveBackNoData() {
        assertNull(list.removeFromBack());
    }

    @Test(timeout = 200)
    public void testToListMany() {
        Integer[] nums = addManyElementsToBack();
        assertArrayEquals(nums, list.toList());
    }

    @Test(timeout = 200)
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.addToFront(new Integer(0));
        assertFalse(list.isEmpty());
        list.addToBack(new Integer(1));
        assertFalse(list.isEmpty());
        list.removeFromFront();
        assertFalse(list.isEmpty());
        list.removeFromBack();
        assertTrue(list.isEmpty());
    }

    @Test(timeout = 200)
    public void testClearWithSize() {
        assertEquals(0, list.size());
        list.addToFront(new Integer(5));
        list.addToFront(new Integer(14));
        list.addToFront(new Integer(2));
        assertEquals(3, list.size());
        list.clear();
        assertEquals(0, list.size());
    }

    @Test(timeout = 200)
    public void testAddAtIndex() {
        list.addAtIndex(0, new Integer(0));
        list.addAtIndex(1, new Integer(2));
        list.addAtIndex(1, new Integer(1));

        Node<Integer> currentNode = ((CircularLinkedList<Integer>) list).getHead();
        assertEquals(new Integer(0), currentNode.getData());
        currentNode = currentNode.getNext();
        assertEquals(new Integer(1), currentNode.getData());
        currentNode = currentNode.getNext();
        assertEquals(new Integer(2), currentNode.getData());
        assertSame(currentNode.getNext(), ((CircularLinkedList<Integer>) list).getHead());
        assertSame(currentNode, ((CircularLinkedList<Integer>) list).getTail());
    }

    
    @Test(timeout = 200, expected = IndexOutOfBoundsException.class)
    public void testAddAtNegativeIndex() {
        list.addAtIndex(-1, new Integer(1));
    }

    
    @Test(timeout = 200)
    public void testRemoveAtIndex() {
        list.addAtIndex(0, new Integer(0));
        list.addAtIndex(1, new Integer(2));
        list.addAtIndex(1, new Integer(1));

        assertEquals(new Integer(1), list.removeAtIndex(1));
        assertEquals(new Integer(2), list.removeAtIndex(1));
        assertEquals(new Integer(0), list.removeAtIndex(0));
        assertTrue(list.isEmpty());
    }

    @Test(timeout = 200)
    public void testGet() {
        list.addToBack(new Integer(0));
        list.addToBack(new Integer(1));
        list.addToBack(new Integer(2));

        assertEquals(new Integer(0), list.get(0));
        assertEquals(new Integer(1), list.get(1));
        assertEquals(new Integer(2), list.get(2));
    }

    @Test(timeout = 200)
    public void testRemoveAtIndexFromFront() {
        list.addAtIndex(0, new Integer(0));
        list.addAtIndex(1, new Integer(2));
        list.addAtIndex(1, new Integer(1));

        assertEquals(new Integer(0), list.removeAtIndex(0));
        assertEquals(new Integer(1), list.removeAtIndex(0));
        assertEquals(new Integer(2), list.removeAtIndex(0));
        assertEquals(0, list.size());
    }
    
    @Test(timeout = 200, expected = IndexOutOfBoundsException.class)
    public void testRemoveAtLargeIndex() {
        list.removeAtIndex(100);
    }

    private Integer[] addManyElementsToBack() {
        Integer[] nums = new Integer[1000];
        for (int i = 0; i < 1000; i++) {
            list.addToBack(new Integer(i));
            nums[i] = new Integer(i);
        }
        return nums;
    }
}
