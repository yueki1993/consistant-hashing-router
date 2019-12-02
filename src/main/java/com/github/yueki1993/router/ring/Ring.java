package com.github.yueki1993.router.ring;


import com.github.yueki1993.router.VirtualNode;

import javax.annotation.Nonnull;

/**
 * Maintains consistent hash ring.
 */
public interface Ring {
    /**
     * Get a successor of the given hashed value in the ring.
     *
     * @return node
     */
    @Nonnull
    VirtualNode getSuccessor(long hashedValue);

    /**
     * Put the node (and its hashed value) to the ring.
     */
    void putNode(VirtualNode node);

    /**
     * Remove the node from the ring.
     */
    void removeNode(VirtualNode node);
}
