package com.github.yueki1993.router;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ConsistentHashingRouterTest {
    private ConsistentHashingRouter sut;

    private Set<String> nodes;

    @Before
    public void setUp() throws Exception {
        nodes = new HashSet<>();
        nodes.add("node1.test");
        nodes.add("node2.test");
        nodes.add("node3.test");
        sut = new ConsistentHashingRouter(nodes);
    }

    @Test
    public void anyResultOfTargetShouldBeInNodes() {
        for (int i = 0; i < 1000; i++) {
            String target = String.valueOf(i);
            MatcherAssert.assertThat(sut.getRoute(target), Matchers.isIn(nodes));
            String route = sut.getRoute(target);
        }
    }
}