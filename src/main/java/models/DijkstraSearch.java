package models;

import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private WeightedGraph<V> graph;

    public DijkstraSearch(WeightedGraph<V> graph) {
        this.graph = graph;
    }

    @Override
    public List<Vertex<V>> getPath(V start, V end) {
        Vertex<V> src = graph.getVertex(start);
        Vertex<V> dst = graph.getVertex(end);
        if (src == null || dst == null) return Collections.emptyList();

        Map<Vertex<V>, Double> dist = new HashMap<>();
        Map<Vertex<V>, Vertex<V>> prev = new HashMap<>();
        PriorityQueue<Vertex<V>> pq = new PriorityQueue<>(Comparator.comparing(dist::get));

        for (Vertex<V> v : graph.getAllVertices()) {
            dist.put(v, Double.POSITIVE_INFINITY);
        }
        dist.put(src, 0.0);
        pq.add(src);

        while (!pq.isEmpty()) {
            Vertex<V> u = pq.poll();
            if (u.equals(dst)) break;
            for (Map.Entry<Vertex<V>, Double> entry : u.getAdjacentVertices().entrySet()) {
                Vertex<V> neighbor = entry.getKey();
                double weight = entry.getValue();
                double alt = dist.get(u) + weight;
                if (alt < dist.get(neighbor)) {
                    dist.put(neighbor, alt);
                    prev.put(neighbor, u);
                    pq.remove(neighbor);
                    pq.add(neighbor);
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

