package me.kagglu.mazeplugin;

import java.util.ArrayList;

public class Node {
    private ArrayList<Node> adjacencyList = new ArrayList<>();
    private boolean visited;

    public Node() {
        visited = false;
    }

    public ArrayList<Node> getAdjacencyList() {
        return adjacencyList;
    }

    public boolean isVisited() {
        return visited;
    }

    public void visit() {
        visited = true;
    }

    public void addToAdjacencyList(Node node) {
        adjacencyList.add(node);
    }

    public void removeFromAdjacencyList(Node node) {
        for (int i = 0; i < adjacencyList.size(); i++) {
            if (adjacencyList.get(i).equals(node)) {
                adjacencyList.remove(i);
                return;
            }
        }
    }

    public void removeFromAdjacencyList(int index) {
        adjacencyList.remove(index);
    }

    public boolean isInAdjacencyList(Node node) {
        for (int i = 0; i < adjacencyList.size(); i++) {
            if (adjacencyList.get(i).equals(node)) {
                return true;
            }
        }
        return false;
    }
}
