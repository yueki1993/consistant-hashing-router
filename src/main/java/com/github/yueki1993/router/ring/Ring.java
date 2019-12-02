package com.github.yueki1993.router.ring;

import javax.annotation.Nonnull;

/**
 * Maintains consistent hash ring.
 */
public interface Ring {
    /**
     * Get a successor of the given hashed value in the ring.
     * @return node
     */
    @Nonnull
    String getSuccessor(long hashedValue);

    /**
     * Put the node (and its hashed value) to the ring.
     */
    void putNode(String node, long hashedValue);

    /**
     * Remove the node from the ring.
     */
    void removeNode(String node, long hashedValue);
}
