package models;

import java.util.List;

interface Search<V> {
    List<Vertex<V>> getPath(V start, V end);
}

