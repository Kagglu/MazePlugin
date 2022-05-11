package me.kagglu.mazeplugin;

import java.util.ArrayList;
import java.util.Stack;

public class Maze {
    private final Node[][] maze;

    public Maze(int size) {
        maze = new Node[size][size];
        generateMaze();
    }

    public Node[][] getMaze() {
        return maze;
    }

    private void generateMaze() {
        for (int i = 0; i < maze.length; i++) { //initialize all nodes
            for (int j = 0; j < maze.length; j++) {
                maze[i][j] = new Node();
            }
        }
        for (int i = 0; i < maze.length; i++) { //connect all nodes (node connections represent "walls" in the maze)
            for (int j = 0; j < maze.length; j++) {
                if (i - 1 >= 0) {
                    maze[i][j].addToAdjacencyList(maze[i - 1][j]);
                }
                if (j - 1 >= 0) {
                    maze[i][j].addToAdjacencyList(maze[i][j - 1]);
                }
                if (i + 1 < maze.length) {
                    maze[i][j].addToAdjacencyList(maze[i + 1][j]);
                }
                if (j + 1 < maze.length) {
                    maze[i][j].addToAdjacencyList(maze[i][j + 1]);
                }
            }
        }

        Stack<Node> stack = new Stack<>();
        int randomStart = (int) (Math.random() * maze.length);
        Node start = maze[randomStart][randomStart];
        start.visit();
        stack.push(start);
        while (!stack.empty()) {
            Node node = stack.pop();
            ArrayList<Node> unvisitedList = new ArrayList<>();
            for (int i = 0; i < node.getAdjacencyList().size(); i++) {
                if (!node.getAdjacencyList().get(i).isVisited()) {
                    unvisitedList.add(node.getAdjacencyList().get(i));
                }
            }
            if (unvisitedList.size() > 0) {
                stack.push(node);
                int random = (int) (Math.random() * unvisitedList.size());
                Node unvisited = unvisitedList.get(random);
                unvisited.removeFromAdjacencyList(node);
                node.removeFromAdjacencyList(unvisited);
                unvisited.visit();
                stack.push(unvisited);
            }
        }
    }

}
