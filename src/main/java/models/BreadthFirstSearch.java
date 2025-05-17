package models;

import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    private WeightedGraph<V> graph;

    public BreadthFirstSearch(WeightedGraph<V> graph) {
        this.graph = graph;
    }

    @Override
    public List<Vertex<V>> getPath(V start, V end) {
        Vertex<V> src = graph.getVertex(start);
        Vertex<V> dst = graph.getVertex(end);
        if (src == null || dst == null) return Collections.emptyList();

        Queue<Vertex<V>> queue = new LinkedList<>();
        Map<Vertex<V>, Vertex<V>> prev = new HashMap<>();
        Set<Vertex<V>> visited = new HashSet<>();

        queue.add(src);
        visited.add(src);

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();
            if (current.equals(dst)) break;
            for (Vertex<V> neighbor : current.getAdjacentVertices().keySet()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    prev.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        return buildPath(src, dst, prev);
    }

    private List<Vertex<V>> buildPath(Vertex<V> src, Vertex<V> dst, Map<Vertex<V>, Vertex<V>> prev) {
        List<Vertex<V>> path = new ArrayList<>();
        for (Vertex<V> at = dst; at != null; at = prev.get(at)) {
            path.add(at);
            if (at.equals(src)) break;
        }
        Collections.reverse(path);
        if (!path.isEmpty() && path.get(0).equals(src)) return path;
        return Collections.emptyList();
    }
}