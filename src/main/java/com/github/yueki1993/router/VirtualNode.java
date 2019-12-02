package com.github.yueki1993.router;


import javax.annotation.Nonnull;
import java.util.Objects;

public class VirtualNode {
    @Nonnull
    public String virtualNodeName;

    @Nonnull
    public String realNode;

    @Nonnull
    public long hashedValue;

    public VirtualNode(@Nonnull String virtualNodeName, @Nonnull String realNode,
                       long hashedValue) {
        this.virtualNodeName = virtualNodeName;
        this.realNode = realNode;
        this.hashedValue = hashedValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VirtualNode that = (VirtualNode) o;
        return hashedValue == ((VirtualNode) o).hashedValue &&
                virtualNodeName.equals(that.virtualNodeName) &&
                realNode.equals(that.realNode);
    }

    @Override
    public int hashCode() {
        return (int)hashedValue;
    }
}
