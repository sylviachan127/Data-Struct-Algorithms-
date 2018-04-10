/**
 * A class representing a map entry for a hashmap
 *
 * @version 1.0
 */
public class MapEntry<K, V> {
    private boolean removed;
    private K key;
    private V value;

    public MapEntry(K k, V v) {
        key = k;
        value = v;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public boolean equals(Object o) {
        if (!(o instanceof MapEntry)) {
            return false;
        } else {
            MapEntry<K, V> that = (MapEntry<K, V>) o;
            return that.getKey().equals(key) && that.getValue().equals(value);
        }
    }

    public String toString() {
        return key.toString() + ": " + value.toString();
    }
}
