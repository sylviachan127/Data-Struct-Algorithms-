import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author Yuen Han Chan
 */
public class MST {
    /**
     * Using disjoint set(s), run Kruskal's algorithm on the given graph and
     * return the MST. Return null if no MST exists for the graph.
     *
     * @param g
     *            The graph to be processed. Will never be null.
     * @return The MST of the graph; null if no valid MST exists.
     */
    public static Collection<Edge> kruskals(Graph g) {

        // Set<Vertex> vertices = new HashSet<Vertex>();
        DisjointSets v = new DisjointSets(g.getVertices());
        PriorityQueue<Edge> edgePQ = new PriorityQueue<>();
        for (Edge e : g.getEdgeList()) {
            edgePQ.add(e);
        }
        Collection<Edge> mst = new HashSet<Edge>();
        if (edgePQ.size() == 0) {
            return null;
        }
        while (!edgePQ.isEmpty()) {
            // mst.size()<(g.getEdgeList().size()-1)){
            Edge se = edgePQ.remove();
            if (!(v.sameSet(se.getU(), se.getV()))) {
                mst.add(se);
                v.merge(se.getU(), se.getV());
            }
        }
        int vSize = g.getVertices().size();
        if (mst.isEmpty()) {
            return null;
        }
        if (vSize - 1 != (mst.size())) {
            return null;
        }
        // Edge[] testSet = mst.toArray(new Edge[mst.size()]);
        // for(int i = 0; i<vSize-1; i++){
        // Edge e1 = testSet[i];
        // Edge e2 = testSet[i];
        // if(!(v.sameSet(e1, e2))){
        // return null;
        // }
        // }
        return mst;
    }

    /**
     * Run Prim's algorithm on the given graph and return the minimum spanning
     * tree. If no MST exists, return null.
     *
     * @param g
     *            The graph to be processed. Will never be null.
     * @param start
     *            The ID of the start node. Will always exist in the graph.
     * @return the MST of the graph; null if no valid MST exists.
     */
    public static Collection<Edge> prims(Graph g, int start) {
        Collection<Edge> mst = new HashSet<>();
        Set<Vertex> vertex = new HashSet<>();
        Vertex startV = null;
        int edgeCount = 0;
        for (Vertex current : g.getVertices()) {
            if (current.getId() == start) {
                vertex.add(current);
                startV = current;
            }
            edgeCount++;
        }

        PriorityQueue<Edge> edgePQ = new PriorityQueue<>();
        while (vertex.size() < edgeCount) {
            Map<Vertex, Integer> adj = g.getAdjacencies(startV);
            for (Vertex v : adj.keySet()) {
                Edge adjWeight = new Edge(v, startV, adj.get(v));
                if (!vertex.contains(v)) {
                    edgePQ.add(adjWeight);
                } else {
                    if (adjWeight.getWeight() < adj.get(v)) {
                        edgePQ.remove(adjWeight);
                        edgePQ.add(adjWeight);
                    }
                }
            }
            if (edgePQ.size() == 0) {
                return null;
            }
            Edge ue = edgePQ.remove();
            if (!vertex.contains(ue.getU())) {
                mst.add(ue);
                vertex.add(ue.getU());
            }
            startV = ue.getU();
        }
        if (mst.isEmpty()) {
            return null;
        }
        return mst;
    }
}
