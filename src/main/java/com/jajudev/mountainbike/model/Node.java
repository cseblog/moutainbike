package com.jajudev.mountainbike.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter

public class Node {
    int v;
    List<Node> friends;

    public Node(int value) {
        v = value;
        friends = new ArrayList<>();
    }

    public void addFriends(int s, Node[][] matrix, int i, int j) {
        int[] up = new int[]{i - 1, j};
        int[] down = new int[]{i + 1, j};
        int[] left = new int[]{i, j - 1};
        int[] right = new int[]{i, j + 1};

        if (up[0] >= 0 && v > matrix[up[0]][up[1]].getV()) {
            friends.add(matrix[up[0]][up[1]]);
        }

        if (down[0] < s && v > matrix[down[0]][down[1]].getV()) {
            friends.add(matrix[down[0]][down[1]]);
        }

        if (left[1] >= 0 && v > matrix[left[0]][left[1]].getV()) {
            friends.add(matrix[left[0]][left[1]]);
        }

        if (right[1] < s && v > matrix[right[0]][right[1]].getV()) {
            friends.add(matrix[right[0]][right[1]]);
        }
    }

    @Override
    public String toString() {
        return "" + v;
    }
}
