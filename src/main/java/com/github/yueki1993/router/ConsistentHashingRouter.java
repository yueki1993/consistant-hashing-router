package com.github.yueki1993.router;

import com.github.yueki1993.router.hash.Hash;
import com.github.yueki1993.router.hash.MD5Hash;
import com.github.yueki1993.router.ring.Ring;
import com.github.yueki1993.router.ring.TreeMapRing;

import javax.annotation.Nonnull;
import java.util.Set;

public class ConsistentHashingRouter implements Router.DynamicRouter {

    private Hash hashFunction = new MD5Hash();
    private Ring ring = new TreeMapRing();

    public ConsistentHashingRouter(Set<String> initialNodes) {
        for (String initialNode : initialNodes) {
            addNode(initialNode);
        }
    }

    @Nonnull
    public String getRoute(@Nonnull String target) {
        long hashedValue = hashFunction.getHash(target);
        return ring.getSuccessor(hashedValue);
    }

    public void addNode(@Nonnull String node) {
        ring.putNode(node, hashFunction.getHash(node));
    }

    public void removeNode(@Nonnull String node) {
        ring.removeNode(node, hashFunction.getHash(node));
    }
}
