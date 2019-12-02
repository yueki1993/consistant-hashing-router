package com.github.yueki1993.router.ring;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TreeMapRingTest {
    private TreeMapRing sut = new TreeMapRing();

    @Before
    public void setUp() throws Exception {
        sut.putNode("node1", 1);
        sut.putNode("node10", 10);
        sut.putNode("node100", 100);
    }

    @Test
    public void testGetSuccessor() {
        MatcherAssert.assertThat(sut.getSuccessor(0), is("node1"));
        MatcherAssert.assertThat(sut.getSuccessor(1), is("node10"));
        MatcherAssert.assertThat(sut.getSuccessor(2), is("node10"));
        MatcherAssert.assertThat(sut.getSuccessor(9), is("node10"));
        MatcherAssert.assertThat(sut.getSuccessor(10), is("node100"));
        MatcherAssert.assertThat(sut.getSuccessor(99), is("node100"));
        MatcherAssert.assertThat(sut.getSuccessor(100), is("node1"));
        MatcherAssert.assertThat(sut.getSuccessor(101), is("node1"));
    }
}