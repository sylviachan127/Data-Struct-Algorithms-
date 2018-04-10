
import static org.junit.Assert.assertArrayEquals;

import java.util.Random;

import org.junit.runners.MethodSorters;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

public class SortingStudentTests {
    private static final int NLOGNLARGEMAX = 100000;
    private static final int N2LARGEMAX = 2000000;
    private SortingInterface sorter;
    private Integer[] smallIntegerArrayTest, smallIntegerArrayExpected;


    @Before
    public void setup() {
        sorter = new Sorting();

        //setup small test variables
        smallIntegerArrayTest = new Integer[6];
        smallIntegerArrayExpected = new Integer[6];

        smallIntegerArrayExpected[0] = 0;
        smallIntegerArrayExpected[1] = 2;
        smallIntegerArrayExpected[2] = 3;
        smallIntegerArrayExpected[3] = 5;
        smallIntegerArrayExpected[4] = 7;
        smallIntegerArrayExpected[5] = 9;

        smallIntegerArrayTest[0] = 7;
        smallIntegerArrayTest[1] = 2;
        smallIntegerArrayTest[2] = 9;
        smallIntegerArrayTest[3] = 5;
        smallIntegerArrayTest[4] = 0;
        smallIntegerArrayTest[5] = 3;
    }

    //Test helpers
    private <T extends Comparable<T>> void testBubble(T[] expected,
            T[] actual) {
        sorter.bubblesort(actual);
        assertArrayEquals(expected, actual);
    }

    private <T extends Comparable<T>> void testInsertion(T[] expected,
            T[] actual) {
        sorter.insertionsort(actual);
        assertArrayEquals(expected, actual);
    }
    private <T extends Comparable<T>> void testSelection(T[] expected,
            T[] actual) {
        sorter.selectionsort(actual);
        assertArrayEquals(expected, actual);
    }
    private <T extends Comparable<T>> void testQuick(T[] expected, T[] actual) {
        sorter.quicksort(actual, new Random());
        assertArrayEquals(expected, actual);
    }

    private <T extends Comparable<T>> void testMerge(T[] expected, T[] actual) {
        sorter.mergesort(actual);
        assertArrayEquals(expected, actual);
    }
    private void testRadix(int[] expected, int[] actual) {
        actual = sorter.radixsort(actual);
        assertArrayEquals(expected, actual);
    }

    //Test Small Sort
    //Bubble
    @Test
    public void testSmallBubble0() {
        testBubble(smallIntegerArrayExpected, smallIntegerArrayTest);
    }

    //Insertion
    @Test
    public void testSmallInsertion0() {
        testInsertion(smallIntegerArrayExpected, smallIntegerArrayTest);
    }

    //Selection
    @Test
    public void testSmallSelection0() {
        testSelection(smallIntegerArrayExpected, smallIntegerArrayTest);
    }

    //Quick
    @Test
    public void testSmallQuick0() {
        testQuick(smallIntegerArrayExpected, smallIntegerArrayTest);
    }

    //Merge
    @Test
    public void testSmallMerge0() {
        testMerge(smallIntegerArrayExpected, smallIntegerArrayTest);
    }

    //Radix
    @Test
    public void testSmallRadix0() {
        int [] expected = {0, 3, 10, 25, 33, 99};
        int [] test = {99, 0, 10, 3, 25, 33};
        testRadix(expected, test);
    }

    //Radix Specific small negative integer test
    @Test
    public void testSmallNegativeRadix() {
        int[] test = {5, 7, -1, 6, 10};
        int[] answer = {-1, 5, 6, 7, 10};
        testRadix(answer, test);
    }

}
