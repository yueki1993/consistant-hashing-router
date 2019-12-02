package com.github.yueki1993.router;

import javax.annotation.Nonnull;
import java.util.Set;

public class ConsistentHashingRouter implements Router.DynamicRouter {

    public ConsistentHashingRouter(Set<String> initialNodes) {
        for (String initialNode : initialNodes) {
            addNode(initialNode);
        }
    }

    @Nonnull
    public String getRoute(@Nonnull String target) {
        return null;
    }


    public void addNode(@Nonnull String node) {
        throw new UnsupportedOperationException("not implemented");
    }

    public void removeNode(@Nonnull String node) {
        throw new UnsupportedOperationException("not implemented");
    }
}
