package com.github.yueki1993.router.ring;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapRing implements Ring {
    public static final String CONFLICT_HASH = "hash confliceted";
    public static final String NOT_FOUND_NODE = "not found node";

    private TreeMap<Long, String> ring = new TreeMap<>();

    private final Object lock = new Object();

    public TreeMapRing() {
    }

    // Takes O(log n) time.
    @Nonnull
    public String getSuccessor(long hashedValue) {
        Map.Entry<Long, String> e = ring.higherEntry(hashedValue);
        if (e == null) {
            return ring.firstEntry().getValue();
        } else {
            return e.getValue();
        }
    }

    // Takes O(log n) time.
    public void putNode(String node, long hashedValue) {
        synchronized (lock) {
            if (ring.containsKey(hashedValue)) {
                throw new IllegalStateException(CONFLICT_HASH);
            }
            ring.put(hashedValue, node);
        }
    }

    // Takes O(log n) time.
    public void removeNode(String node, long hashedValue) {
        synchronized (lock) {
            boolean result = ring.remove(hashedValue, node);
            if (!result) {
                throw new IllegalStateException(NOT_FOUND_NODE);
            }
        }
    }
}
