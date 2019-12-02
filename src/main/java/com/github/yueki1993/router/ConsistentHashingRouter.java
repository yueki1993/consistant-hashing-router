package com.github.yueki1993.router;

import com.github.yueki1993.router.hash.Hash;
import com.github.yueki1993.router.hash.MD5Hash;
import com.github.yueki1993.router.ring.Ring;
import com.github.yueki1993.router.ring.TreeMapRing;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ConsistentHashingRouter implements Router.DynamicRouter {

    private Hash hashFunction = new MD5Hash();
    private Ring ring = new TreeMapRing();


    private Map<String, String> virtualToRealNode = new HashMap<>();
    private Map<String, Set<String>> RealToVirtualNodes = new HashMap<>();
    private int virtualNodeFactor;


    public ConsistentHashingRouter(@Nonnull Set<String> initialNodes) {
        this(initialNodes, 100);
    }

    public ConsistentHashingRouter(Set<String> initialNodes, int virtualNodeFactor) {
        assert virtualNodeFactor >= 1;
        this.virtualNodeFactor = virtualNodeFactor;

        for (String initialNode : initialNodes) {
            addNode(initialNode);
        }
    }

    @Nonnull
    public String getRoute(@Nonnull String target) {
        long hashedValue = hashFunction.getHash(target);
        return ring.getSuccessor(hashedValue).realNode;
    }

    public void addNode(@Nonnull String node) {
        for (int i = 0; i < virtualNodeFactor; i++) {
            String virtualNodeName = getVirtualNodeName(node, i);
            ring.putNode(new VirtualNode(virtualNodeName, node), hashFunction.getHash(virtualNodeName));
        }

    }

    public void removeNode(@Nonnull String node) {
        for (int i = 0; i < virtualNodeFactor; i++) {
            String virtualNodeName = getVirtualNodeName(node, i);
            ring.removeNode(new VirtualNode(virtualNodeName, node), // not cool...
                    hashFunction.getHash(node));
        }
    }

    private static String getVirtualNodeName(String realNodeName, int i) {
        return realNodeName + "_" + i;
    }

}
