package com.github.yueki1993.router;

import com.github.yueki1993.router.hash.Hash;
import com.github.yueki1993.router.hash.MD5Hash;
import com.github.yueki1993.router.ring.Ring;
import com.github.yueki1993.router.ring.TreeMapRing;

import javax.annotation.Nonnull;
import java.util.*;

public class ConsistentHashingRouter implements Router.DynamicRouter {
    private static final String ALREADY_REGISTERED_NODE = "already registered node";
    private static final String NOT_FOUND_NODE = "not found node";


    private Hash hashFunction = new MD5Hash();
    private Ring ring = new TreeMapRing();

    private int virtualNodeFactor;

    private Map<String, List<VirtualNode>> nodeToVirtualNodes = new HashMap<>();

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
        if (nodeToVirtualNodes.containsKey(node)) {
            throw new IllegalArgumentException(ALREADY_REGISTERED_NODE);
        }
        ArrayList<VirtualNode> vNodeList = new ArrayList<>();
        nodeToVirtualNodes.put(node, vNodeList);

        for (int i = 0; i < virtualNodeFactor; i++) {
            String virtualNodeName = getVirtualNodeName(node, i);
            VirtualNode vNode = new VirtualNode(virtualNodeName, node, hashFunction.getHash(virtualNodeName));

            ring.putNode(vNode);
            vNodeList.add(vNode);
        }

    }

    public void removeNode(@Nonnull String node) {
        List<VirtualNode> vNodes = nodeToVirtualNodes.get(node);
        if (vNodes == null) {
            throw new IllegalArgumentException(NOT_FOUND_NODE);
        }

        for (VirtualNode vNode : vNodes) {
            ring.removeNode(vNode);
        }
        nodeToVirtualNodes.remove(node);
    }

    private static String getVirtualNodeName(String realNodeName, int i) {
        return realNodeName + "_" + i;
    }

}
