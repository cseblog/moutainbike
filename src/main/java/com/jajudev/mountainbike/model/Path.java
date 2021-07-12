package com.jajudev.mountainbike.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Path {
    List<Node> nodeList;

    public boolean compare(Path p) {
        List<Node> l = p.getNodeList();
        if (nodeList.size() > l.size()) {
            return true;
        }

        if (nodeList.size() < l.size()) {
            return false;
        }

        if( nodeList.size() == l.size()) {
            int delta1 = nodeList.get(0).getV() - nodeList.get(nodeList.size() -1).getV();
            int delta2 = l.get(0).getV() - l.get(l.size() -1).getV();
            if(delta1 >= delta2){
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
}
