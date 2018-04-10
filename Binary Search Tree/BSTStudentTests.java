import java.util.List;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertArrayEquals;


public class BSTStudentTests {
    private BSTInterface<Integer> bst;
    private static final int TEST_SIZE = 1000;

    @Before
    public void setup() {
        bst = new BST<Integer>();
    }

    @Test (timeout = 200)
    public void testBasicBalanced() {
        bst.add(2);
        bst.add(1);
        bst.add(3);
        assertEquals(3, bst.size());

        Node<Integer> root = bst.getRoot();
        assertEquals(new Integer(2), root.getData());
        assertEquals(new Integer(1), root.getLeft().getData());
        assertEquals(new Integer(3), root.getRight().getData());
    }


    @Test (timeout = 200)
    public void testRemoveTwoChildren() {
        plantTree();
        assertEquals(new Integer(67), bst.getRoot().getData());
        assertEquals(new Integer(67), bst.remove(67));
        assertEquals(new Integer(59), bst.getRoot().getData());

        assertEquals(new Integer(79), bst.remove(79));
        assertEquals(new Integer(73), bst.getRoot().getRight().getData());
    }

    @Test (timeout = 200)
    public void testContains() {
        plantTree();
        assertTrue(bst.contains(43));
        assertTrue(bst.contains(97));
        assertFalse(bst.contains(98));
        assertFalse(bst.contains(18));
        assertFalse(bst.contains(31));
    }

    @Test (timeout = 200)
    public void testRemoveEmptyTree() throws Exception {
        assertNull(bst.remove(5));
    }

    @Test (timeout = 200)
    public void testGet() throws Exception {
        createBalanced7();
        for (int i = 1; i < 8; i++) {
            assertEquals(new Integer(i), bst.get(i));
        }
        assertNull(bst.get(11));
        assertNull(bst.get(-11));
    }


    @Test (timeout = 200)
    public void testSize() throws Exception {
        create12345678();
        assertEquals(8, bst.size());
    }

    @Test (timeout = 200)
    public void testPreorder() throws Exception {
        createBalanced7();
        Integer[] expected = {4, 2, 1, 3, 6, 5, 7};
        List<Integer> arrList = bst.preorder();
        Integer[] arr = arrList.toArray(new Integer[0]);
        assertArrayEquals(expected, arr);
    }

    @Test (timeout = 200)
    public void testPostorder() throws Exception {
        createBalanced7();
        Integer[] expected = {1, 3, 2, 5, 7, 6, 4};
        List<Integer> arrList = bst.postorder();
        Object[] arr = arrList.toArray();
        assertArrayEquals(expected, arr);
    }

    @Test (timeout = 200)
    public void testInorder() throws Exception {
        createBalanced7();
        Integer[] expected = {1, 2, 3, 4, 5, 6, 7};
        List<Integer> arrList = bst.inorder();
        Object[] arr = arrList.toArray();
        assertArrayEquals(expected, arr);
    }

    @Test (timeout = 200)
    public void testLevelorder() throws Exception {
        createBalanced7();
        Integer[] expected = {4, 2, 6, 1, 3, 5, 7};
        List<Integer> arrList = bst.levelorder();
        Object[] arr = arrList.toArray();
        assertArrayEquals(expected, arr);
    }

    @Test (timeout = 200)
    public void testClear() throws Exception {
        createBalanced7();
        bst.clear();
        assertNull(bst.getRoot());
        assertEquals(0, bst.size());
    }

    @Test (timeout = 200)
    public void testHeight() throws Exception {
        createBalanced7();
        assertEquals(2, bst.height());
    }

    public void plantTree() {
        // root
        bst.add(67);

        // left side
        bst.add(47);
        bst.add(37);
        bst.add(53);
        bst.add(41);
        bst.add(43);
        bst.add(59);

        // right side
        bst.add(79);
        bst.add(73);
        bst.add(71);
        bst.add(89);
        bst.add(83);
        bst.add(97);

    }

    // START Emily's Tests
    public void create12345678() {
        bst.add(1);
        bst.add(2);
        bst.add(3);
        bst.add(4);
        bst.add(5);
        bst.add(6);
        bst.add(7);
        bst.add(8);
    }

    public void createBalanced7() {
        bst.add(4);
        bst.add(2);
        bst.add(6);
        bst.add(1);
        bst.add(3);
        bst.add(5);
        bst.add(7);
    }

}
