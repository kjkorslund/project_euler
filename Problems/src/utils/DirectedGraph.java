package utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class DirectedGraph<T> {
    // *** WIP class; untested! ***

    private long nextID = 1;

    public final HashSet<Node> nodes = new HashSet<>();
    public final HashSet<Edge> edges = new HashSet<>();

    public Node newNode(T val) {
        Node node = new Node(val);
        nodes.add(node);
        return node;
    }

    public Edge newEdge(Node from, Node to) {
        Edge edge = new Edge(from, to);
        edges.add(edge);
        return edge;
    }

    public final class Node {
        public final long id;
        public T val;
        public final Set<String> tags = new HashSet<>();

        private Node(T val) {
            this.id = nextID++;
            this.val = val;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return id == node.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public final class Edge {
        public final long id;

        public final Node from;
        public final Node to;
        public final Set<String> tags = new HashSet<>();

        private Edge(Node from, Node to) {
            this.id = nextID++;
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return id == edge.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }
}
