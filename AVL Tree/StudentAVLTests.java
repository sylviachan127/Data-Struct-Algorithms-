import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Before;
import org.junit.Test;

public class StudentAVLTests {
    private AVL<MagicNumber> avlMagicNumber;
    private AVL<MagicString> avlMagicString;

    @Before
    public void setup() {
        avlMagicNumber = new AVL<MagicNumber>();
        avlMagicString = new AVL<MagicString>();
    }

    @Test(timeout = 250)
    public void testAddNoRotation() {
        addShortBalancedTree();

        Node<MagicNumber> root = avlMagicNumber.getRoot();
        assertEquals(new MagicNumber("hello", 81), root.getData());
        assertEquals(new MagicNumber("goodbye", 13), root.getLeft().getData());
        assertEquals(new MagicNumber("igloo", -4), root.getRight().getData());
        assertNull(root.getLeft().getLeft());
        assertNull(root.getLeft().getRight());
        assertNull(root.getRight().getLeft());
        assertNull(root.getRight().getRight());
    }

    @Test(timeout = 250)
    public void testAddSingleRotation() {
        avlMagicNumber.add(new MagicNumber("goodbye", 13));
        avlMagicNumber.add(new MagicNumber("hello", 81));
        avlMagicNumber.add(new MagicNumber("igloo", -4));

        Node<MagicNumber> root = avlMagicNumber.getRoot();
        assertEquals("Incorrect rotation", new MagicNumber("hello", 81),
                root.getData());
        assertEquals("Incorrect rotation", new MagicNumber("goodbye", 13),
                root.getLeft().getData());
        assertEquals("Incorrect rotation", new MagicNumber("igloo", -4),
                root.getRight().getData());
        assertNull(root.getLeft().getLeft());
        assertNull(root.getLeft().getRight());
        assertNull(root.getRight().getLeft());
        assertNull(root.getRight().getRight());
    }

    @Test(timeout = 250)
    public void testAddDoubleRotation() {
        avlMagicNumber.add(new MagicNumber("igloo", -4));
        avlMagicNumber.add(new MagicNumber("goodbye", 13));
        avlMagicNumber.add(new MagicNumber("hello", 81));

        Node<MagicNumber> root = avlMagicNumber.getRoot();
        assertEquals("Incorrect rotation", new MagicNumber("hello", 81),
                root.getData());
        assertEquals("Incorrect rotation", new MagicNumber("goodbye", 13),
                root.getLeft().getData());
        assertEquals("Incorrect rotation", new MagicNumber("igloo", -4),
                root.getRight().getData());
        assertNull(root.getLeft().getLeft());
        assertNull(root.getLeft().getRight());
        assertNull(root.getRight().getLeft());
        assertNull(root.getRight().getRight());
    }

    @Test(timeout = 250)
    public void testGetContains() {
        addShortBalancedTree();

        assertTrue(avlMagicNumber.contains(new MagicNumber("hello")));

        MagicNumber firstGet = avlMagicNumber.get(
                new MagicNumber("hello"));
        assertEquals("Data in the tree is not being returned", 81,
                firstGet.magicNumber);

        assertTrue(avlMagicNumber.contains(new MagicNumber("igloo")));

        MagicNumber secondGet = avlMagicNumber.get(
                new MagicNumber("igloo"));
        assertEquals("Data in the tree is not being returned", -4,
                secondGet.magicNumber);
    }

    @Test(timeout = 250)
    public void testSizeIsEmptyRemoveNoRotation() {
        assertTrue(avlMagicNumber.isEmpty());
        assertEquals(0, avlMagicNumber.size());

        addShortBalancedTree();

        assertFalse(avlMagicNumber.isEmpty());
        assertEquals(3, avlMagicNumber.size());

        assertNull(avlMagicNumber.remove(new MagicNumber("maybe")));
        assertFalse(avlMagicNumber.isEmpty());
        assertEquals("Size is always being decremented", 3,
                avlMagicNumber.size());

        MagicNumber removeResult1 = avlMagicNumber
            .remove(new MagicNumber("hello"));
        assertEquals("hello", removeResult1.string);
        assertEquals(81, removeResult1.magicNumber);
        assertFalse(avlMagicNumber.isEmpty());
        assertEquals(2, avlMagicNumber.size());

        MagicNumber removeResult2 = avlMagicNumber
            .remove(new MagicNumber("igloo"));
        assertEquals("igloo", removeResult2.string);
        assertEquals(-4, removeResult2.magicNumber);
        assertFalse(avlMagicNumber.isEmpty());
        assertEquals(1, avlMagicNumber.size());

        MagicNumber removeResult3 = avlMagicNumber
            .remove(new MagicNumber("goodbye"));
        assertEquals("goodbye", removeResult3.string);
        assertEquals(13, removeResult3.magicNumber);
        assertTrue(avlMagicNumber.isEmpty());
        assertEquals(0, avlMagicNumber.size());

        assertNull(avlMagicNumber.remove(new MagicNumber("maybe not")));
        assertTrue(avlMagicNumber.isEmpty());
        assertEquals("Size is always being decremented",
                0, avlMagicNumber.size());
    }

    @Test(timeout = 250)
    public void testSizeIsEmptyRemoveRotations() {
        addLongBalancedTree();

        MagicString removeResult1 = avlMagicString
            .remove(new MagicString(526));
        assertEquals("Data in the tree is not being returned", "sixth",
                removeResult1.magicString);
        assertEquals("Data in the tree is not being returned",
                new Integer(526), removeResult1.number);
        assertFalse(avlMagicString.isEmpty());
        assertEquals(9, avlMagicString.size());

        Node<MagicString> root = avlMagicString.getRoot();

        assertEquals(new MagicString("first", 477), root.getData());
        assertEquals(new MagicString("second", 251), root.getLeft().getData());
        assertEquals(new MagicString("third", 646), root.getRight().getData());
        assertEquals(new MagicString("fourth", 856), root.getRight()
                .getRight().getData());
        assertEquals(new MagicString("fifth", 186), root.getLeft().getLeft()
                .getData());
        assertNull(root.getRight().getLeft());
        assertEquals(new MagicString("seventh", 287), root.getLeft().getRight()
                .getData());

        MagicString removeResult2 = avlMagicString
            .remove(new MagicString(856));
        assertEquals("fourth", removeResult2.magicString);
        assertEquals(new Integer(856), removeResult2.number);
        assertFalse(avlMagicString.isEmpty());
        assertEquals(8, avlMagicString.size());

        root = avlMagicString.getRoot();

        assertEquals(new MagicString("second", 251), root.getData());
        assertEquals(new MagicString("fifth", 186), root.getLeft().getData());
        assertEquals(new MagicString("first", 477), root.getRight().getData());
        assertEquals(new MagicString("third", 646), root.getRight()
                .getRight().getData());
        assertEquals(new MagicString("ninth", 124), root.getLeft().getLeft()
                .getData());
        assertEquals(new MagicString("eigth", 224), root.getLeft().getRight()
                .getData());
    }

    @Test(timeout = 250)
    public void testHeight1() {
        addShortBalancedTree();

        assertEquals(1, avlMagicNumber.height());

        assertEquals(1, avlMagicNumber.getRoot().getHeight());
        assertEquals(0, avlMagicNumber.getRoot().getBalanceFactor());
        assertEquals(0, avlMagicNumber.getRoot().getLeft().getHeight());
        assertEquals(0, avlMagicNumber.getRoot().getLeft().getBalanceFactor());
        assertEquals(0, avlMagicNumber.getRoot().getRight().getHeight());
        assertEquals(0, avlMagicNumber.getRoot().getRight().getBalanceFactor());
    }

    @Test(timeout = 250)
    public void testHeight2() {
        addLongBalancedTree();

        Node<MagicString> root = avlMagicString.getRoot();

        assertEquals(3, root.getHeight());
        assertEquals(1, root.getBalanceFactor());
        assertEquals(2, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals(1, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(1, root.getLeft().getLeft().getHeight());
        assertEquals(0, root.getLeft().getLeft().getBalanceFactor());
        assertEquals(1, root.getLeft().getRight().getHeight());
        assertEquals(-1, root.getLeft().getRight().getBalanceFactor());
    }

    @Test(timeout = 250)
    public void testClear() {
        addShortBalancedTree();
        assertEquals(3, avlMagicNumber.size());
        avlMagicNumber.clear();
        assertEquals("Size isn't being reset to 0", 0, avlMagicNumber.size());
    }

    @Test(timeout = 250)
    public void testPreorder() {
        addLongBalancedTree();
        MagicString[] preorder = new MagicString[10];
        preorder[0] = new MagicString(477);
        preorder[1] = new MagicString(251);
        preorder[2] = new MagicString(186);
        preorder[3] = new MagicString(124);
        preorder[4] = new MagicString(224);
        preorder[5] = new MagicString(287);
        preorder[6] = new MagicString(386);
        preorder[7] = new MagicString(646);
        preorder[8] = new MagicString(526);
        preorder[9] = new MagicString(856);
        assertArrayEquals(preorder,
                avlMagicString.preorder().toArray(new MagicString[0]));
    }

    @Test(timeout = 250)
    public void testPostorder() {
        addLongBalancedTree();
        MagicString[] postorder = new MagicString[10];
        postorder[0] = new MagicString(124);
        postorder[1] = new MagicString(224);
        postorder[2] = new MagicString(186);
        postorder[3] = new MagicString(386);
        postorder[4] = new MagicString(287);
        postorder[5] = new MagicString(251);
        postorder[6] = new MagicString(526);
        postorder[7] = new MagicString(856);
        postorder[8] = new MagicString(646);
        postorder[9] = new MagicString(477);
        assertArrayEquals(postorder,
                avlMagicString.postorder().toArray(new MagicString[0]));
    }

    @Test(timeout = 250)
    public void testInorder() {
        addLongBalancedTree();
        MagicString[] inorder = new MagicString[10];
        inorder[0] = new MagicString(124);
        inorder[1] = new MagicString(186);
        inorder[2] = new MagicString(224);
        inorder[3] = new MagicString(251);
        inorder[4] = new MagicString(287);
        inorder[5] = new MagicString(386);
        inorder[6] = new MagicString(477);
        inorder[7] = new MagicString(526);
        inorder[8] = new MagicString(646);
        inorder[9] = new MagicString(856);
        assertArrayEquals(inorder,
                avlMagicString.inorder().toArray(new MagicString[0]));
    }

    @Test(timeout = 250)
    public void testLevelorder() {
        addLongBalancedTree();
        MagicString[] levelorder = new MagicString[10];
        levelorder[0] = new MagicString(477);
        levelorder[1] = new MagicString(251);
        levelorder[2] = new MagicString(646);
        levelorder[3] = new MagicString(186);
        levelorder[4] = new MagicString(287);
        levelorder[5] = new MagicString(526);
        levelorder[6] = new MagicString(856);
        levelorder[7] = new MagicString(124);
        levelorder[8] = new MagicString(224);
        levelorder[9] = new MagicString(386);
        assertArrayEquals(levelorder,
                avlMagicString.levelorder().toArray(new MagicString[0]));
    }

    private void addShortBalancedTree() {
        avlMagicNumber.add(new MagicNumber("hello", 81));
        avlMagicNumber.add(new MagicNumber("goodbye", 13));
        avlMagicNumber.add(new MagicNumber("igloo", -4));
    }

    private void addLongBalancedTree() {
        avlMagicString.add(new MagicString("first", 477));
        avlMagicString.add(new MagicString("second", 251));
        avlMagicString.add(new MagicString("third", 646));
        avlMagicString.add(new MagicString("fourth", 856));
        avlMagicString.add(new MagicString("fifth", 186));
        avlMagicString.add(new MagicString("sixth", 526));
        avlMagicString.add(new MagicString("seventh", 287));
        avlMagicString.add(new MagicString("eigth", 224));
        avlMagicString.add(new MagicString("ninth", 124));
        avlMagicString.add(new MagicString("tenth", 386));
    }

    private class MagicNumber implements Comparable<MagicNumber> {
        private final String string;
        private final int magicNumber;

        public MagicNumber(String string) {
            this(string, 0);
        }

        public MagicNumber(String string, int magicNumber) {
            this.string = string;
            this.magicNumber = magicNumber;
        }

        @Override
        public boolean equals(Object other) {
            if (other == null) {
                return false;
            }
            if (!(other instanceof MagicNumber)) {
                return false;
            }
            MagicNumber that = (MagicNumber) other;
            return that.string.equals(string);
        }

        @Override
        public int compareTo(MagicNumber other) {
            return string.compareTo(other.string);
        }

        @Override
        public String toString() {
            return "MagicNumber: " + string + ", " + magicNumber;
        }
    }

    private class MagicString implements Comparable<MagicString> {
        private final String magicString;
        private final Integer number;

        public MagicString(Integer number) {
            this("", number);
        }

        public MagicString(String magicString, Integer number) {
            this.magicString = magicString;
            this.number = number;
        }

        @Override
        public boolean equals(Object other) {
            if (other == null) {
                return false;
            }
            if (!(other instanceof MagicString)) {
                return false;
            }
            MagicString that = (MagicString) other;
            return that.number.equals(number);
        }

        @Override
        public int compareTo(MagicString other) {
            return number.compareTo(other.number);
        }

        @Override
        public String toString() {
            return "MagicString: " + magicString + ", " + number;
        }
    }
}
