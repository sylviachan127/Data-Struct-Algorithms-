import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Yuen Han Chan
 * @param <K>
 * @param <V>
 */
public class HashMap<K, V> implements HashMapInterface<K, V>, Gradable<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size = 0;

    public HashMap() {
        table = new MapEntry[STARTING_SIZE];
    }

    private void expend() {
        int newLength = table.length * 2;
        MapEntry<K, V>[] newTable = new MapEntry[newLength];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && !(table[i].isRemoved())) {
                int hashCode = Math.abs(table[i].getKey().hashCode());
                int insertValue = hashCode % newLength;
                while ((newTable[insertValue] != null)) {
                    hashCode += 1;
                    insertValue = hashCode % table.length;
                }
                newTable[insertValue] = table[i];
            }
        }
        table = newTable;
    }

    @Override
    public V add(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
        if (((double) (size() + 1) / table.length) > MAX_LOAD_FACTOR) {
            expend();
        }
        int hashCode = Math.abs(key.hashCode());
        int insertValue = hashCode % table.length;
        V storeValue = null;
        while ((table[insertValue] != null)
                && !(table[insertValue].isRemoved())) {
            if ((table[insertValue].getKey()).equals(key)) {
                storeValue = table[insertValue].getValue();
                size--;
                break;
            }
            hashCode += 1;
            insertValue = hashCode % table.length;
        }
        table[insertValue] = new MapEntry<K, V>(key, value);
        size++;
        return storeValue;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int hashCode = Math.abs(key.hashCode());
        int insertValue = hashCode % table.length;
        V storeValue = null;
        while ((table[insertValue] != null)
                && (table[insertValue].isRemoved())) {
            if ((table[insertValue].getKey()).equals(key)) {
                storeValue = table[insertValue].getValue();
                break;
            }
            hashCode += 1;
            insertValue = hashCode % table.length;
        }
        if (table[insertValue] != null) {
            storeValue = table[insertValue].getValue();
            table[insertValue].setRemoved(true);
            size--;
        }
        return storeValue;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int hashCode = Math.abs(key.hashCode());
        int insertValue = hashCode % table.length;
        V storeValue = null;
        while (table[insertValue] != null) {
            if ((table[insertValue].getKey()).equals(key)) {
                storeValue = table[insertValue].getValue();
                break;
            }
            hashCode += 1;
            insertValue = hashCode % table.length;
        }
        if (table[insertValue] != null) {
            storeValue = table[insertValue].getValue();
        }
        return storeValue;
    }

    @Override
    public boolean contains(K key) {
        boolean found = false;
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int hashCode = Math.abs(key.hashCode());
        int insertValue = hashCode % table.length;
        while ((table[insertValue] != null)) {
            if (!(table[insertValue].isRemoved())) {
                if ((table[insertValue].getKey()).equals(key)) {
                    found = true;
                    break;
                }
            }
            hashCode += 1;
            insertValue = hashCode % table.length;
        }
        return found;
    }

    @Override
    public void clear() {
        table = new MapEntry[STARTING_SIZE];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public MapEntry<K, V>[] toArray() {
        return table;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<K>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && !(table[i].isRemoved())) {
                keySet.add(table[i].getKey());
            }
        }
        return keySet;
    }

    @Override
    public List<V> values() {
        List<V> values = new ArrayList<V>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && !(table[i].isRemoved())) {
                values.add(table[i].getValue());
            }
        }
        return values;
    }

}
