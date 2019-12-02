package com.github.yueki1993.router.ring;


import com.github.yueki1993.router.VirtualNode;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapRing implements Ring {
    public static final String EMPTY_RING = "ring is empty";
    public static final String CONFLICT_HASH = "hash confliceted";
    public static final String NOT_FOUND_NODE = "not found node";

    private TreeMap<Long, VirtualNode> ring = new TreeMap<>();

    private final Object lock = new Object();

    public TreeMapRing() {
    }

    // Takes O(log n) time.
    // XXX: maybe not yet thread safe
    @Nonnull
    public VirtualNode getSuccessor(long hashedValue) {
        if (ring.isEmpty()) {
            throw new IllegalStateException(EMPTY_RING);
        }

        Map.Entry<Long, VirtualNode> e = ring.higherEntry(hashedValue);
        if (e == null) {
            return ring.firstEntry().getValue();
        } else {
            return e.getValue();
        }
    }

    // Takes O(log n) time.
    public void putNode(VirtualNode node, long hashedValue) {
        synchronized (lock) {
            if (ring.containsKey(hashedValue)) {
                throw new IllegalStateException(CONFLICT_HASH);
            }
            ring.put(hashedValue, node);
        }
    }

    // Takes O(log n) time.
    public void removeNode(VirtualNode node, long hashedValue) {
        synchronized (lock) {
            boolean result = ring.remove(hashedValue, node);
            if (!result) {
                throw new IllegalStateException(NOT_FOUND_NODE);
            }
        }
    }
}
