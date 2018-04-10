import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Yuen Han Chan
 * @param <T>
 */
public class DisjointSets<T> implements DisjointSetsInterface<T> {
    // Should be a map of data to its parent; root data maps to itself.
    private final Map<T, Node> set;

    /**
     * @param setItems
     *            the initial items (sameSet and merge will never be called with
     *            items not in this set, this set will never contain null
     *            elements).
     */
    public DisjointSets(Set<T> setItems) {
        if (setItems == null) {
            throw new IllegalArgumentException();
        }

        set = new HashMap<>();
        for (T item : setItems) {
            set.put(item, new Node(item));
        }
    }

    /**
     * @param Node
     *            Find the parent root of x
     * @return The root of the tree
     */
    private Node find(Node x) {
        while (!(x.getParent().equals(x))) {
            return find(x.getParent());
        }
        return x;
    }

    @Override
    public boolean sameSet(T u, T v) {

        if (u == null || v == null) {
            throw new IllegalArgumentException();
        }
        return ((find(set.get(u))).equals(find(set.get(v))));
    }

    @Override
    public void merge(T u, T v) {
        if (u == null || v == null) {
            throw new IllegalArgumentException();
        }
        if (sameSet(u, v)) {
            return;
        }
        Node uNode = find(set.get(u));
        Node vNode = find(set.get(v));
        if (uNode.getRank() > vNode.getRank()) {
            vNode.setParent(uNode);
        } else if (uNode.getRank() <= vNode.getRank()) {
            uNode.setParent(vNode);
        }
    }

    /**
     * Create Node for DisjointSets
     */
    private class Node {

        private int rank;
        private final T data;
        private Node parent;

        private Node(T data) {
            this.data = data;
            this.rank = 0;
            this.parent = this;
        }

        private T getData() {
            return data;
        }

        private int getRank() {
            return rank;
        }

        private void setRank(int rank) {
            this.rank = rank;
        }

        private Node getParent() {
            return parent;
        }

        private void setParent(Node x) {
            parent = x;
        }

        private boolean equals(Node p) {
            return data.equals(p.getData());
        }
    }
}
