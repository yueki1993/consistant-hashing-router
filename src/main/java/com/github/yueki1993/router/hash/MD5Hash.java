package com.github.yueki1993.router.hash;

import com.google.common.hash.Hashing;

import javax.annotation.Nonnull;


public class MD5Hash implements Hash {
    public long getHash(@Nonnull String str) {
        return Hashing.md5().hashBytes(str.getBytes()).asLong();
    }
}
