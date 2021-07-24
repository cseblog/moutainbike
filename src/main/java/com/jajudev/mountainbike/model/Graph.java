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

    /**
     * Build graph from a string of matrix
     * Ex: "4 8 7 3 2 5 9 3 6 3 2 5 4 4 1 6"
     * @param n
     * @param matrixStr
     */
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

    public void print() {
        nodes.forEach(n -> {
            List<Node> friends = n.getFriends();
            List<Integer> friendList = new ArrayList<>();
            friends.forEach(f -> friendList.add(f.getV()));
            log.info("{}-> {}", n.getV(), friendList);
        });
    }

    public Path getLongestPath() {
        List<Path> paths = new ArrayList<>();
        this.nodes.stream().forEach(n -> {
            Path friendLongestPath = this.getLongestPathByNode(n);
            paths.add(friendLongestPath);
        });

        Path longestPath = new Path();
        for (int i = 0; i < paths.size(); i++) {
            Path currPath = paths.get(i);
            if (currPath.compare(longestPath) ) {
                longestPath = currPath;
            }
        }
        return longestPath;
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

}
