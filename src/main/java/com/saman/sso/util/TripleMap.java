package com.saman.sso.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TripleMap<K, V1, V2> {

    private int size;

    private List<Entry<K, V1, V2>> entries = new ArrayList<>(size);

    public TripleMap(int size) {
        this.size = size;
    }

    public TripleMap() {
        this(10);
    }

    public void put(K key, V1 v1, V2 v2) {
        entries.add(new Entry<>(key, v1, v2));
    }

    public Entry<K, V1, V2> get(K key) {
        return entries.stream()
                .filter(e -> e.k.equals(key))
                .findAny()
                .get();
    }

    public Boolean contains(K key) {
        return entries.stream()
                .filter(e -> e.k.equals(key))
                .findAny()
                .isPresent();
    }

    public class Entry<K, V1, V2> {
        private K k;
        private V1 v1;
        private V2 v2;

        public Entry(K k, V1 v1, V2 v2) {
            this.k = k;
            this.v1 = v1;
            this.v2 = v2;
        }

        public K getK() {
            return k;
        }

        public V1 getV1() {
            return v1;
        }

        public V2 getV2() {
            return v2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry<?, ?, ?> entry = (Entry<?, ?, ?>) o;
            return Objects.equals(k, entry.k);
        }

        @Override
        public int hashCode() {
            return Objects.hash(k);
        }
    }
}
