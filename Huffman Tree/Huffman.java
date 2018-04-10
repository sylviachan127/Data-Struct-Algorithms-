import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author Yuen Han Chan
 */
public class Huffman {

    // Do NOT add any variables (instance or static)

    /**
     * Builds a frequency map of characters for the given string. This should
     * just be the count of each character. No character not in the input string
     * should be in the frequency map.
     *
     * @param s
     *            the string to map
     * @return the character -> Integer frequency map
     */
    public static Map<Character, Integer> buildFrequencyMap(String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }
        int stringLength = s.length();
        Map<Character, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < stringLength; i++) {
            char currentChar = s.charAt(i);
            if (freqMap.containsKey(currentChar)) {
                int count = freqMap.get(currentChar);
                freqMap.put(currentChar, ++count);
            } else {
                freqMap.put(currentChar, 1);
            }
        }
        return freqMap;
    }

    /**
     * Build the Huffman tree using the frequencies given. Add the nodes to the
     * tree based on their natural ordering (the order given by the compareTo
     * method). The frequency map will not necessarily come from the
     * {@code buildFrequencyMap()} method. Every entry in the map should be in
     * the tree.
     *
     * @param freq
     *            the frequency map to represent
     * @return the root of the Huffman Tree
     */
    public static HuffmanNode buildHuffmanTree(Map<Character, Integer> freq) {
        if (freq == null) {
            throw new IllegalArgumentException();
        }
        PriorityQueue<HuffmanNode> pqNode = new PriorityQueue<>();
        for (char x : freq.keySet()) {
            HuffmanNode newNode = new HuffmanNode(x, freq.get(x));
            pqNode.add(newNode);
        }
        while (pqNode.size() > 1) {
            HuffmanNode firstNode = pqNode.remove();
            HuffmanNode secondNode = pqNode.remove();
            HuffmanNode newRoot = new HuffmanNode(firstNode, secondNode);
            pqNode.add(newRoot);
        }
        return pqNode.remove();
    }

    /**
     * Traverse the tree and extract the encoding for each character in the
     * tree. The tree provided will be a valid huffman tree but may not come
     * from the {@code buildHuffmanTree()} method.
     *
     * @param tree
     *            the root of the Huffman Tree
     * @return the map of each character to the encoding string it represents
     */
    public static Map<Character, EncodedString> buildEncodingMap(
            HuffmanNode tree) {
        if (tree == null) {
            throw new IllegalArgumentException();
        }
        Map<Character, EncodedString> encodeMap = new HashMap<>();
        EncodedString es = new EncodedString();
        es.zero();
        // if(tree.getLeft()==null && tree.getRight()==null){
        // encodeMap.put(tree.getCharacter(),es);
        // //es.one();
        // return encodeMap;
        // }
        if (tree.getLeft() != null && tree.getRight() != null) {
            traverseHelper(encodeMap, tree);
        } else {
            encodeMap.put(tree.getCharacter(), es);
        }
        return encodeMap;
    }

    private static void traverseHelper(Map<Character, EncodedString> encodeMap,
            HuffmanNode root) {
        EncodedString es = new EncodedString();
        if (root.getLeft() == null && root.getRight() == null) {
            encodeMap.put(root.getCharacter(), es);
        }
        if (root.getLeft() != null) {
            es.zero();
            traverseHelper(encodeMap, root.getLeft());
        }
        if (root.getRight() != null) {
            es.one();
            traverseHelper(encodeMap, root.getRight());
        }
    }

    /**
     * Encode each character in the string using the map provided. If a
     * character in the string doesn't exist in the map ignore it. The encoding
     * map may not necessarily come from the {@code buildEncodingMap()} method,
     * but will be correct for the tree given to {@code decode()} when decoding
     * this method's output.
     *
     * @param encodingMap
     *            the map of each character to the encoding string it represents
     * @param s
     *            the string to encode
     * @return the encoded string
     */
    public static EncodedString encode(
            Map<Character, EncodedString> encodingMap, String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }
        EncodedString es = new EncodedString();
        int stringSize = s.length();
        for (int i = 0; i < stringSize; i++) {
            char currentChar = s.charAt(i);
            EncodedString charEs = encodingMap.get(currentChar);
            es.concat(charEs);
        }
        return es;
    }

    /**
     * Decode the encoded string using the tree provided. The encoded string may
     * not necessarily come from {@code encode()}, but will be a valid string
     * for the given tree. (tip: use StringBuilder to make this method faster --
     * concatenating strings is SLOW.)
     *
     * @param tree
     *            the tree to use to decode the string
     * @param es
     *            the encoded string
     * @return the decoded string
     */
    public static String decode(HuffmanNode tree, EncodedString es) {
        if (tree == null || es == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder word = new StringBuilder();
        HuffmanNode root = tree;
        if (root.getLeft() != null && root.getRight() != null) {
            for (byte x : es) {
                if (x == 0) {
                    root = root.getLeft();
                } else {
                    root = root.getRight();
                }
                if (root.getLeft() == null && root.getRight() == null) {
                    word.append(root.getCharacter());
                    root = tree;
                }
            }
        } else {
            for (byte x : es) {
                word.append(root.getCharacter());
            }
        }
        String newWord = word.toString();
        return newWord;
    }
}