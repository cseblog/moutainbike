package com.jajudev.mountainbike.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GraphTest {

    Graph graph = new Graph();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test1() {
        graph.build(4,"4 8 7 3 2 5 9 3 6 3 2 5 4 4 1 6");
//        graph.print();
        Path maxChain = graph.getLongestPath();
        log.info("Final longest path: {}", maxChain.getNodeList());
    }

    @Test
    public void test2() throws IOException {
        String data = new String(getClass()
                .getClassLoader().getResourceAsStream("test2.txt").readAllBytes());
        data = data.replaceAll("\n", " ");
        graph.build(1000,data);
        Path maxChain = graph.getLongestPath();
        log.info("Final longest path: {}", maxChain.getNodeList());
    }
}