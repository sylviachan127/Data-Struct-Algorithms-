import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.runners.MethodSorters;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentStringSearchingTests {
    private StringSearchingInterface stringSearcher;

    @Before
    public void setup() {
        stringSearcher = new StringSearching();
    }

    // BoyerMoore
    @Test(timeout = 200)
    public void testBuildingTable1Comparisons() {
        SearchableString sampleString = new SearchableString("The quick brown");
        stringSearcher.buildLastTable(sampleString);
        assertEquals(sampleString.length(), sampleString.getCount());
    }

    @Test(timeout = 200)
    public void testBuildingTable1() {
        SearchableString sampleString = new SearchableString("The quick brown");
        int[] boyerTable = stringSearcher.buildLastTable(sampleString);
        assertEquals(Character.MAX_VALUE + 1, boyerTable.length);
        for (int i = 0; i < boyerTable.length; i++) {
            switch (i) {
            case 'T':
                assertEquals(0, boyerTable[i]);
                break;
            case 'h':
                assertEquals(1, boyerTable[i]);
                break;
            case 'e':
                assertEquals(2, boyerTable[i]);
                break;
            case ' ':
                assertEquals("Duplicates not handled correctly", 9,
                        boyerTable[i]);
                break;
            case 'q':
                assertEquals(4, boyerTable[i]);
                break;
            case 'u':
                assertEquals(5, boyerTable[i]);
                break;
            case 'i':
                assertEquals(6, boyerTable[i]);
                break;
            case 'c':
                assertEquals(7, boyerTable[i]);
                break;
            case 'k':
                assertEquals(8, boyerTable[i]);
                break;
            case 'b':
                assertEquals(10, boyerTable[i]);
                break;
            case 'r':
                assertEquals(11, boyerTable[i]);
                break;
            case 'o':
                assertEquals(12, boyerTable[i]);
                break;
            case 'w':
                assertEquals(13, boyerTable[i]);
                break;
            case 'n':
                assertEquals(14, boyerTable[i]);
                break;
            default:
                assertEquals("Default case is not correct", -1, boyerTable[i]);
                break;
            }
        }
    }

    @Test(timeout = 200)
    public void testBoyerMoore1() {
        SearchableString text = new SearchableString(
                "The quick brown fox jumps" + " over the lazy dog");
        SearchableString pattern = new SearchableString("own fox");
        List<Integer> matches = stringSearcher.boyerMoore(pattern, text);
        assertEquals("Incorrect number of matches found", 1, matches.size());
        assertEquals("Incorrect index for match", new Integer(12),
                matches.get(0));
        assertEquals(13, text.getCount());
        assertEquals(7 + 13, pattern.getCount());
    }

    @Test(timeout = 200)
    public void testBoyerMoore2() {
        SearchableString text = new SearchableString("The quick brown fox jumps"
                + " over the lazy dog");
        SearchableString pattern = new SearchableString("green");
        List<Integer> matches = stringSearcher.boyerMoore(pattern, text);
        assertEquals("False matches found", 0, matches.size());
        assertEquals(11, text.getCount());
        assertEquals(5 + 11, pattern.getCount());
    }

    // Rabin-Karp Tests
    @Test(timeout = 100)
    public void generateHashTestBasic() {
        SearchableString string = new SearchableString("bunnbunn");
        assertEquals(1523991782,
                stringSearcher.generateHash(string, string.length()));
    }

    @Test(timeout = 100)
    public void updateHashTestBasic() {
        String str = "bunn";
        int oldHash = -612042413;
        int length = str.length();
        char oldChar = str.charAt(0);
        char newChar = 'y';
        assertEquals(929117178,
                stringSearcher.updateHash(oldHash, length, oldChar, newChar));
    }

    // helper method
    private List<Integer> integersToList(Integer... values) {
        return Arrays.asList(values);
    }

    @Test(timeout = 200)
    public void rabinCarpBasicTest() {
        SearchableString pattern = new SearchableString("test");
        SearchableString text = new SearchableString(
                            "testatestfbteestasdntest");
        List<Integer> expected = integersToList(0, 5, 20);
        assertEquals(expected, stringSearcher.rabinKarp(pattern, text));
    }

    // Efficiency Tests
    @Test(timeout = 200)
    public void rabinCarpEfficiencyTest() {
        SearchableString pattern = new SearchableString("!!!!!!");
        SearchableString text = new SearchableString(
               "Lorem Ipsum is simply dummy text of the printing and "
               + "typesetting industry. Lorem Ipsum has been the industry's"
               + " standard dummy text ever since the 1500s, when an unknown"
               + " printer took a galley of type and scrambled it to make a"
               + " type specimen book. It has survived not only five centuries,"
               + " but also the leap into electronic typesetting, remaining"
               + " essentially unchanged. It was popularised in the 1960s "
               + "with the release of Letraset sheets containing Lorem Ipsum "
               + "passages, and more recently with desktop publishing software"
               + " like Aldus PageMaker including versions of Lorem Ipsum.");
        List<Integer> actual = stringSearcher.rabinKarp(pattern, text);

        System.out.println(text.getCount());
        assertTrue(text.getCount() <= text.length() * 2 + 10);
        assertTrue(pattern.getCount() <= pattern.length());
    }

    @Test(timeout = 200)
    public void generateHashEfficiencyTest() {
        SearchableString pattern = new SearchableString("!!!!!!");
        int actual = stringSearcher.generateHash(pattern, pattern.length());
        assertTrue(pattern.getCount() == pattern.length());
    }
}
