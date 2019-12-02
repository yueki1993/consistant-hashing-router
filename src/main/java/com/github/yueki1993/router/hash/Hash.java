package com.github.yueki1993.router.hash;

import javax.annotation.Nonnull;

@FunctionalInterface
public interface Hash {
    long getHash(@Nonnull String str);
}
