package com.github.yueki1993.router;


import javax.annotation.Nonnull;
import java.util.Objects;

public class VirtualNode {
    @Nonnull
    public String virtualNodeName;

    @Nonnull
    public String realNode;

    public VirtualNode(@Nonnull String virtualNodeName, @Nonnull String realNode) {
        this.virtualNodeName = virtualNodeName;
        this.realNode = realNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VirtualNode that = (VirtualNode) o;
        return virtualNodeName.equals(that.virtualNodeName) &&
                realNode.equals(that.realNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(virtualNodeName, realNode);
    }
}
