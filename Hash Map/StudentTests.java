import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;


@SuppressWarnings("unchecked")
public class StudentTests {

    private HashMap<Integer, String> ht;

    @Before
    public void setup() {
        ht = new HashMap<Integer, String>();
    }

    @Test (timeout = 300)
    public void testAdd() {
        ht.add(0, "a");
        ht.add(1, "b");
        ht.add(2, "c");
        ht.add(10, "d");
        assertEquals("a", ht.add(0, "aa"));

        MapEntry<Integer, String>[] arr = new MapEntry[10];
        arr[0] = new MapEntry<Integer, String>(0, "aa");
        arr[1] = new MapEntry<Integer, String>(1, "b");
        arr[2] = new MapEntry<Integer, String>(2, "c");
        arr[3] = new MapEntry<Integer, String>(10, "d");

        assertArrayEquals(arr, ht.toArray());
    }

    @Test (timeout = 300)
    public void testAddResize() {
        ht.add(0, "a");
        ht.add(1, "b");
        ht.add(2, "c");
        ht.add(3, "d");
        ht.add(10, "e");
        ht.add(11, "f");
        ht.add(12, "g");
        ht.add(13, "h");

        MapEntry<Integer, String>[] arr = new MapEntry[20];
        arr[0] = new MapEntry<Integer, String>(0, "a");
        arr[1] = new MapEntry<Integer, String>(1, "b");
        arr[2] = new MapEntry<Integer, String>(2, "c");
        arr[3] = new MapEntry<Integer, String>(3, "d");
        arr[10] = new MapEntry<Integer, String>(10, "e");
        arr[11] = new MapEntry<Integer, String>(11, "f");
        arr[12] = new MapEntry<Integer, String>(12, "g");
        arr[13] = new MapEntry<Integer, String>(13, "h");

        assertArrayEquals(arr, ht.toArray());
    }

    @Test (timeout = 300)
    public void testRemove() {
        ht.add(0, "a");
        ht.add(1, "b");
        ht.add(2, "c");
        ht.add(10, "d");
        assertEquals("b", ht.remove(1));
        assertEquals("c", ht.remove(2));

        assertFalse(ht.contains(1));
        assertTrue(ht.contains(10));

        MapEntry<Integer, String>[] arr = new MapEntry[10];
        arr[0] = new MapEntry<Integer, String>(0, "a");
        arr[1] = new MapEntry<Integer, String>(1, "b");
        arr[1].setRemoved(true);
        arr[2] = new MapEntry<Integer, String>(2, "c");
        arr[2].setRemoved(true);
        arr[3] = new MapEntry<Integer, String>(10, "d");
        assertArrayEquals(arr, ht.toArray());
    }

    @Test (timeout = 300)
    public void testRemoveOverwrite() {
        ht.add(0, "a");
        ht.add(1, "b");
        ht.add(2, "c");
        ht.add(10, "d");
        assertEquals("b", ht.remove(1));
        assertEquals("c", ht.remove(2));
        ht.add(2, "f");

        assertTrue(ht.contains(2));
        assertTrue(ht.contains(10));

        MapEntry<Integer, String>[] arr = new MapEntry[10];
        arr[0] = new MapEntry<Integer, String>(0, "a");
        arr[1] = new MapEntry<Integer, String>(1, "b");
        arr[1].setRemoved(true);
        arr[2] = new MapEntry<Integer, String>(2, "f");
        arr[3] = new MapEntry<Integer, String>(10, "d");
        assertArrayEquals(arr, ht.toArray());
    }


    @Test (timeout = 300)
    public void testGet() {
        ht.add(0, "a");
        ht.add(1, "b");
        ht.add(2, "c");
        ht.add(10, "d");

        assertEquals("a", ht.get(0));
        assertEquals("b", ht.get(1));
        assertEquals("c", ht.get(2));
        assertEquals("d", ht.get(10));
    }


    @Test (timeout = 300)
    public void testClear() {
        ht.add(0, "a");
        ht.add(1, "b");
        ht.add(2, "c");
        ht.add(10, "d");
        ht.clear();
        MapEntry<Integer, String>[] arr = new MapEntry[10];
        assertArrayEquals(arr, ht.toArray());
        assertEquals(0, ht.size());
    }


    @Test (timeout = 300)
    public void testSize() {
        assertEquals(0, ht.size());
        ht.add(0, "a");
        ht.add(1, "b");
        ht.add(2, "c");
        assertEquals(3, ht.size());
        ht.remove(0);
        ht.remove(1);
        ht.remove(2);
        assertEquals(0, ht.size());
    }

    @Test (timeout = 300)
    public void testValues() {
        ht.add(0, "a");
        ht.add(1, "b");
        ht.add(2, "c");
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        assertTrue(list.equals(ht.values()));
    }


    @Test (timeout = 300)
    public void testKeySet() {
        ht.add(0, "a");
        ht.add(1, "b");
        ht.add(2, "c");

        HashSet<Integer> set = new HashSet<>();
        set.add(0);
        set.add(1);
        set.add(2);
        assertTrue(set.equals(ht.keySet()));
    }
}
