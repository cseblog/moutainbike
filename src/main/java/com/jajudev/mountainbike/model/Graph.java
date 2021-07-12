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

    public List<Node> getLongestPath() {
        List<List<Node>> chains = new ArrayList<>();

        this.nodes.stream().forEach(n -> {
            List<Node> friendLongestChain = this.getLongestPathByNode(n);
//            log.info("Longest path from {}, {}", n.getV(), friendLongestChain);
            chains.add(friendLongestChain);
        });

        List<Node> maxChain = new ArrayList<>();
        for (int i = 0; i < chains.size(); i++) {
            List<Node> chain = chains.get(i);
            if(chain.size() > maxChain.size()){
                maxChain = chain;
            }

            if(chain.size() == maxChain.size()) {
                int delta = chain.get(0).getV() - chain.get(chain.size() - 1).getV();
                int currDelta = maxChain.get(0).getV() - maxChain.get(chain.size() - 1).getV();
                if(delta > currDelta){
                    maxChain = chain;
                }
            }
        }
        return maxChain;
    }


    public List<Node> getLongestPathByNode(Node n){
        List<Node> list = new ArrayList<>();
        list.add(n);
        List<Node> friends = n.getFriends();
        int max = 0;
        List<Node> maxPath = null;
        for (int i = 0; i < friends.size(); i++) {
            List<Node> tmpList = getLongestPathByNode(friends.get(i));
            if(tmpList.size() > max){
                maxPath = tmpList;
                max = tmpList.size();
            }
        }
        if(maxPath != null){
            list.addAll(maxPath);
        }
        return list;
    }


    public void print() {
        nodes.forEach(n -> {
            List<Node> friends = n.getFriends();
            List<Integer> friendList = new ArrayList<>();
            friends.forEach(f -> friendList.add(f.getV()));
            log.info("{}-> {}",n.getV(), friendList);
        });
    }
}
