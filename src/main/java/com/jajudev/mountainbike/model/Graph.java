package com.jajudev.mountainbike.model;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@NoArgsConstructor
public class Graph {
    int size;
    List<Node> nodes = new ArrayList<>();

    public void build(int n, String matrixStr) {
        String[] strArr = matrixStr.split(" ");
        int[] intArr = Stream.of(strArr).mapToInt(Integer::parseInt).toArray();
        build(n, intArr);
    }

    public void build(int s, int[] arr) {
        Node[][] matrix = new Node[s][s];
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                matrix[i][j] = new Node(arr[i * s + j]);
            }
        }

        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                Node node = matrix[i][j];
                node.addFriends(s, matrix, i, j);
                nodes.add(node);
            }
        }
    }

    public Path getLongestPath() {
        List<Path> chains = new ArrayList<>();

        this.nodes.stream().forEach(n -> {
            Path friendLongestChain = this.getLongestPathByNode(n);
//            log.info("Longest path from {}, {}", n.getV(), friendLongestChain);
            chains.add(friendLongestChain);
        });

        Path maxChain = new Path();

        for (int i = 0; i < chains.size(); i++) {
            Path chain = chains.get(i);
            if (chain.compare(maxChain) ) {
                maxChain = chain;
            }
        }
        return maxChain;
    }


    public Path getLongestPathByNode(Node n) {
        Path path = new Path();
        path.add(n);
        List<Node> friends = n.getFriends();
        Path maxPath = null;
        for (int i = 0; i < friends.size(); i++) {
            Path currentPath = getLongestPathByNode(friends.get(i));
            if (currentPath.compare(maxPath)) {
                maxPath = currentPath;
            }
        }
        if (maxPath != null) {
            path.addAll(maxPath);
        }
        return path;
    }


    public void print() {
        nodes.forEach(n -> {
            List<Node> friends = n.getFriends();
            List<Integer> friendList = new ArrayList<>();
            friends.forEach(f -> friendList.add(f.getV()));
            log.info("{}-> {}", n.getV(), friendList);
        });
    }
}
