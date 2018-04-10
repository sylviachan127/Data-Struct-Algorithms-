import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Graph {

    private Collection<Edge> edges = new HashSet<Edge>();
    private Set<Vertex> vertices = new HashSet<Vertex>();
    private Map<Vertex, Map<Vertex, Integer>> adjacencies = new HashMap<>();

    /**
     * A class representing a graph, can be built from an edge list.
     *
     * The first int is the number of edges. Following this are triples of
     * integers for each edge representing vertex u, vertex v, and the weight.
     *
     * For example, a triangle graph with all weight being 1 is as follows:
     * 3 1 2 1 1 3 1 2 3 1
     *
     * @param input the graph to make as per the format above
     */
    public Graph(String input) {
        Scanner scan = new Scanner(input);

        int count = scan.nextInt();
        for (int i = 0; i < count; i++) {
            Vertex u = new Vertex(scan.nextInt());
            Vertex v = new Vertex(scan.nextInt());
            int weight = scan.nextInt();
            vertices.add(u);
            vertices.add(v);
            edges.add(new Edge(u, v, weight));

            if (!adjacencies.containsKey(u)) {
                adjacencies.put(u, new HashMap<Vertex, Integer>());
            }
            adjacencies.get(u).put(v, weight);

            if (!adjacencies.containsKey(v)) {
                adjacencies.put(v, new HashMap<Vertex, Integer>());
            }
            adjacencies.get(v).put(u, weight);
        }

        scan.close();
    }

    public Collection<Edge> getEdgeList() {
        return edges;
    }

    public Set<Vertex> getVertices() {
        return vertices;
    }

    public Map<Vertex, Integer> getAdjacencies(Vertex u) {
        return adjacencies.get(u);
    }
}
