package models;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class WeightedGraph<V> {
    private Map<V, Vertex<V>> vertices = new HashMap<>();

    public void addVertex(V data) {
        vertices.putIfAbsent(data, new Vertex<>(data));
    }

    public void addEdge(V source, V dest, double weight) {
        vertices.putIfAbsent(source, new Vertex<>(source));
        vertices.putIfAbsent(dest, new Vertex<>(dest));
        Vertex<V> srcVertex = vertices.get(source);
        Vertex<V> destVertex = vertices.get(dest);
        srcVertex.addAdjacentVertex(destVertex, weight);
    }

    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    public Collection<Vertex<V>> getAllVertices() {
        return vertices.values();
    }
}

