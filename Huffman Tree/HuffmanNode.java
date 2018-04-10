/**
 * A class representing the nodes in the HuffmanTree
 * DO NOT MODIFY THIS FILE
 */
public class HuffmanNode implements Comparable<HuffmanNode> {

    private static int timer = 0;

    private final int time;
    private final int frequency;
    private final char character;

    private final HuffmanNode left;
    private final HuffmanNode right;

    public HuffmanNode(char character, int frequency) {
        this.time = timer++;
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    public HuffmanNode(HuffmanNode less, HuffmanNode more) {
        this.time = timer++;
        this.character = 0;
        this.frequency = less.frequency + more.frequency;
        this.left = less;
        this.right = more;
    }

    public char getCharacter() {
        return character;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    @Override
    public int compareTo(HuffmanNode that) {
        if (this.frequency == that.frequency) {
            if (this.character == that.character) {
                return this.time - that.time;
            } else {
                return this.character - that.character;
            }
        } else {
            return this.frequency - that.frequency;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof HuffmanNode) {
            HuffmanNode that = (HuffmanNode) obj;
            return (that.frequency == this.frequency && that.character
                    == this.character);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return time;
    }
}
