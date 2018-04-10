import java.util.HashMap;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


import org.junit.Before;
import org.junit.Test;

public class BasicTests {
    private String studentString;
    private HashMap<Character, Integer> studentMap;
    private HuffmanNode studentTree;
    private HashMap<Character, EncodedString> studentEncodedMap;
    private EncodedString studentEncodedString;

    @Before
    public void stringsToTest() {
        studentString = "hello";
    }

    public void makeStudentMap() {
        studentMap = new HashMap<Character, Integer>();
        studentMap.put('h', 1);
        studentMap.put('e', 1);
        studentMap.put('l', 2);
        studentMap.put('o', 1);
    }

    public void makeStudentTree() {
        HuffmanNode h = new HuffmanNode('h', 1);
        HuffmanNode e = new HuffmanNode('e', 1);

        HuffmanNode eh = new HuffmanNode(e, h);

        HuffmanNode o = new HuffmanNode('o', 1);

        HuffmanNode oeh = new HuffmanNode(o, eh);

        HuffmanNode l = new HuffmanNode('l', 2);

        HuffmanNode loeh = new HuffmanNode(l, oeh);

        studentTree = loeh;
    }

    public void makeStudentEncodedString() {
        EncodedString h = new EncodedString();
        h.one();
        h.one();
        h.one();
        EncodedString e = new EncodedString();
        e.one();
        e.one();
        e.zero();
        EncodedString l = new EncodedString();
        l.zero();
        EncodedString o = new EncodedString();
        o.one();
        o.zero();

        studentEncodedMap = new HashMap<Character, EncodedString>();
        studentEncodedMap.put('h', h);
        studentEncodedMap.put('e', e);
        studentEncodedMap.put('l', l);
        studentEncodedMap.put('o', o);

        studentEncodedString = new EncodedString();
        studentEncodedString.concat(h);
        studentEncodedString.concat(e);
        studentEncodedString.concat(l);
        studentEncodedString.concat(l);
        studentEncodedString.concat(o);
    }

    @Test
    public void studentStringBuildFrequencyMap() {
        makeStudentMap();
        assertEquals(studentMap, Huffman.buildFrequencyMap(studentString));
    }

    @Test
    public void studentStringBuildHuffmanTree() {
        makeStudentTree();
        makeStudentMap();
        HuffmanNode theOtherTree = Huffman.buildHuffmanTree(studentMap);
        assertEquals(studentTree, theOtherTree);
        assertEquals(studentTree.getLeft(), theOtherTree.getLeft());
        assertEquals(studentTree.getRight(), theOtherTree.getRight());
        assertEquals(studentTree.getRight().getLeft(), theOtherTree.getRight()
                .getLeft());
        assertEquals(studentTree.getRight().getRight(), theOtherTree.getRight()
                .getRight());
        assertEquals(studentTree.getRight().getRight().getLeft(), theOtherTree
                .getRight().getRight().getLeft());
        assertEquals(studentTree.getRight().getRight().getRight(), theOtherTree
                .getRight().getRight().getRight());

    }

    @Test
    public void studentStringBuildEncode() {
        makeStudentTree();
        makeStudentEncodedString();
        Map<Character, EncodedString> theStudentEncodedString = Huffman
            .buildEncodingMap(studentTree);
        assertEquals(studentEncodedMap.keySet(),
                theStudentEncodedString.keySet());
        for (Entry<Character, EncodedString> me : studentEncodedMap
                .entrySet()) {
            me.getKey();
            Iterator i = me.getValue().iterator();
            EncodedString string = theStudentEncodedString.get(me.getKey());
            for (Byte b : string) {
                assertEquals(b, i.next());
            }
        }
    }

    @Test
    public void studentStringEncode() {
        makeStudentEncodedString();
        EncodedString string = Huffman.encode(studentEncodedMap, studentString);
        Iterator i = studentEncodedString.iterator();
        for (Byte b : string) {
            assertEquals(b, i.next());
        }
    }

    @Test
    public void studentStringDecode() {
        makeStudentTree();
        makeStudentEncodedString();
        assertEquals(studentString, Huffman.decode(studentTree,
                    studentEncodedString));
    }

}
