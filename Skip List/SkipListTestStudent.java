import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;


public class SkipListTestStudent {
    private SkipListInterface<Integer> list;
    CoinFlipper randomness;
    @Before
    public void setup() {
        randomness = new CoinFlipper(new Random(10));
        list = new SkipList<Integer>(randomness);
    }


    // This is an example of how your SkipList is tested
    // for efficiency.
    @Test (timeout = 200)
    public void testPutSingle() {
        list.put(1);
        assertEquals(1, list.size());
        assertEquals(new Integer(1), list.first());
        assertEquals(new Integer(1), list.last());
        assertEquals(2, randomness.getNumFlips());
    }

    @Test (timeout = 200)
    public void testPut() {
        addBasic();
        assertEquals("First element is incorrect", new Integer(0),
                list.first());
        assertEquals("Last element is incorrect", new Integer(10), list.last());
        assertEquals("Size of the list is incorrect", 11, list.size());
    }

    @Test (timeout = 200)
    public void testContains() {
        addBasic();
        for (int i = 0; i < 11; i++) {
            assertTrue("List should contain " + i, list.contains(i));
        }

        for (int i = 11; i < 20; i++) {
            assertFalse("List should not contain " + i, list.contains(i));
        }
    }

    @Test (timeout = 200)
    public void testRemove() {
        addBasic();
        for (int i = 0; i < 5; i++) {
            list.remove(i);
        }
        assertEquals("Size isn't being decremented correctly when removing from"
                + " the list", 6, list.size());

        for (int i = 5; i < 10; i++) {
            list.remove(i);
        }
        assertEquals("Size isn't being decremented correctly when removing from"
                + " the list", 1, list.size());

        list.remove(10);
        assertEquals("Size should be 0 on an empty list", 0, list.size());

    }

    @Test (timeout = 200)
    public void testClear() {
        addBasic();
        assertEquals("Size is incorrect after adding", 11, list.size());

        list.clear();
        assertEquals("Size isn't 0 after clearing the list", 0, list.size());

        addJagged();
        assertEquals("Size is incorrect after adding unordered numbers", 11,
                list.size());

        list.clear();
        assertEquals("Size isn't 0 after clearing the list", 0, list.size());
    }

    @Test (timeout = 200)
    public void testGet() {
        addJagged();
        for (int i = 0; i < 11; i++) {
            assertEquals("Incorrect order of elements in the list",
                    new Integer(i), list.get(i));
        }
    }

    @Test (timeout = 200)
    public void testDataSet() {
        Set<Integer> expected = new TreeSet<Integer>();
        for (int i = 0; i < 11; i++) {
            expected.add(i);
        }
        addJagged();
        Set<Integer> dataSet = list.dataSet();
        assertEquals("Incorrect elements in the data set", expected, dataSet);
    }

    @Test (timeout = 200)
    public void testEmptyBasic() {
        assertNull(list.first());
        assertNull(list.last());
        assertNull(list.get(1));
        assertNull(list.remove(1));
        assertEquals("Empty set not being returned for an empty list",
                list.dataSet(), new HashSet<Integer>());
    }

    private void addBasic() {
        for (int i = 10; i > -1; i--) {
            list.put(i);
        }
    }

    private void addJagged() {
        list.put(3);
        list.put(7);
        list.put(1);
        list.put(4);
        list.put(10);
        list.put(5);
        list.put(2);
        list.put(6);
        list.put(0);
        list.put(8);
        list.put(9);
    }

}
