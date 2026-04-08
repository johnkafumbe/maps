package edu.ttap.maps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * An association list is an implementation of a map via a list of key-value pairs.
 */
public class AssociationList<K, V> implements Map<K, V> {
    public class Pair<T, U> {
        private T fst;

        private U snd;

        public Pair(T fst, U snd) {
            this.fst = fst;
            this.snd = snd;
        }
    }

    private ArrayList<Pair<K, V>> map;

    public AssociationList() {
        map = null;
    }

    /**
     * Clears the association list, removing all key-value pairs.
     */
    @Override
    public void clear() {
        for (Pair<K, V> pair : map) {
            map.remove(pair);
        }
    }

    /**
     * @param key the key to check
     * @return true iff this map contains a mapping for the specified key
     */
    @Override
    public boolean containsKey(Object key) {
        boolean result = false;

        for (Pair<K,V> pair : map) {
            if (pair.fst == key) {
                result = true;
            }
        }

        return result;
    }

    /**
     * @param value the value to check
     * @return true iff this map maps one or more keys to the specified value
     */
    @Override
    public boolean containsValue(Object value) {
        boolean result = false;

        for (Pair<K,V> pair : map) {
            if (pair.snd == value) {
                result = true;
            }
        }

        return result;
    }

    /**
     * @return a set view of the mappings contained in this map
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        // NOTE: you do not need to implement this method!
        throw new UnsupportedOperationException("Unimplemented method 'entrySet'");
    }

    /**
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key
     */
    @Override
    public V get(Object key) {
        V result = null;

        for (Pair<K, V> pair : map) {
            if (pair.fst == key) {
                result = pair.snd;
            }
        }

        return result;
    }

    /**
     * @return true iff this map contains no key-value mappings
     */
    @Override
    public boolean isEmpty() {
        return map.size() > 0;
    }

    /**
     * @return a set view of the keys contained in this map
     */
    @Override
    public Set<K> keySet() {
        HashSet<K> result = new HashSet<>();

        for (Pair<K, V> pair : map) {
            result.add(pair.fst);
        }

        return result;
    }

    /**
     * If there is no entry for key in the map, updates the entry to associate key
     * with value. Otherwise, it updates the entry for key accordingly.
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     * @return the previous value associated with key, or null if there was no
     *         mapping for key
     */
    @Override
    public V put(K key, V value) {
        for (Pair<K,V> pair : map) {
            if (pair.fst == key) {
                V snd = pair.snd;
                pair.snd = value;

                return snd;
            }
        }

        map.add(new Pair<K, V>(key, value));
        return null;
    }

    /**
     * Copies all of the mappings from the specified map to this map. The effect of this
     * operation is equivalent to applying the put(K, V) operation to each entry in the
     * specified map.
     * @param m the map whose mappings are to be copied to this map
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (K key : m.keySet()) {
            map.add(new Pair<K,V>(key, m.get(key)));
        }
    }

    /**
     * Removes the mapping for a key from this map if it is present.
     * @param key the key whose mapping is to be removed from the map
     * @return the previous value associated with key, or null if there was no mapping for
     *         key.
     */
    @Override
    public V remove(Object key) {
        V result = null;
        for (Pair<K,V> pair : map) {
            if (pair.fst == key) {
                result = pair.snd;
                map.remove(pair);
            }
        }

        return result;
    }

    /**
     * @return the number of key-value mappings in this map
     */
    @Override
    public int size() {
        return map.size();    
    }

    /**
     * @return a collection vof the values contained in this map, e.g., a list
     */
    @Override
    public Collection<V> values() {
        ArrayList<V> result = new ArrayList<>();

        for (Pair<K,V> pair : map) {
            result.add(pair.snd);
        }

        return result;
    }
}