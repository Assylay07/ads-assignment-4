package models;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");

        graph.addEdge("A", "B", 2);
        graph.addEdge("A", "C", 5);
        graph.addEdge("B", "C", 1);
        graph.addEdge("B", "D", 3);
        graph.addEdge("C", "E", 7);
        graph.addEdge("D", "E", 4);

        Search<String> bfs = new BreadthFirstSearch<>(graph);
        List<Vertex<String>> bfsPath = bfs.getPath("A", "E");
        System.out.println("BFS path from A to E: " + bfsPath);

        Search<String> dijkstra = new DijkstraSearch<>(graph);
        List<Vertex<String>> djPath = dijkstra.getPath("A", "E");
        System.out.println("Dijkstra path from A to E: " + djPath);
    }
}

