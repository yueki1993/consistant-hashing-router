package com.github.yueki1993.router;


import javax.annotation.Nonnull;

public interface Router {
    @Nonnull
    String getRoute(@Nonnull String target);

     interface DynamicRouter extends Router {
        void addNode(@Nonnull String node);

        void removeNode(@Nonnull String node);
    }
}
