package com.jajudev.mountainbike.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Path {
    List<Node> nodeList;

    public Path(){
        nodeList = new ArrayList<>();
    }

    public void add(Node node){
        nodeList.add(node);
    }

    public void addAll(Path path){
        this.nodeList.addAll(path.getNodeList());
    }

    public int getSize(){
        if(nodeList.isEmpty() || nodeList == null){
            return 0;
        }
        return nodeList.size();
    }

    public boolean compare(Path p) {
        if(p == null){
            return true;
        }

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
