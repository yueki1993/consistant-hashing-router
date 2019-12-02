package com.github.yueki1993.router.ring;

import com.github.yueki1993.router.VirtualNode;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class TreeMapRingTest {
    private TreeMapRing sut = new TreeMapRing();
    private VirtualNode node1 = new VirtualNode("node1", "node1", 1);
    private VirtualNode node2 = new VirtualNode("node2", "node2", 10);
    private VirtualNode node3 = new VirtualNode("node3", "node3", 100);


    @Before
    public void setUp() throws Exception {
        sut.putNode(node1);
        sut.putNode(node2);
        sut.putNode(node3);
    }

    @Test
    public void testGetSuccessor() {
        MatcherAssert.assertThat(sut.getSuccessor(-1), is(node1));
        MatcherAssert.assertThat(sut.getSuccessor(0), is(node1));
        MatcherAssert.assertThat(sut.getSuccessor(1), is(node2));
        MatcherAssert.assertThat(sut.getSuccessor(2), is(node2));
        MatcherAssert.assertThat(sut.getSuccessor(9), is(node2));
        MatcherAssert.assertThat(sut.getSuccessor(10), is(node3));
        MatcherAssert.assertThat(sut.getSuccessor(99), is(node3));
        MatcherAssert.assertThat(sut.getSuccessor(100), is(node1));
        MatcherAssert.assertThat(sut.getSuccessor(101), is(node1));
    }
}
