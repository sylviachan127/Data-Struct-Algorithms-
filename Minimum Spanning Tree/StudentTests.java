import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashSet;

import org.junit.Test;

public class StudentTests {


    @Test(timeout = 500)
    public void basicDisjointTestSameSet() {
        HashSet<String> set = new HashSet<String>();
        set.add("a");
        set.add("b");
        set.add("c");
        set.add("d");
        DisjointSets<String> disjointSet = new DisjointSets<String>(set);
        assertTrue(disjointSet.sameSet("a", "a"));
    }

    @Test(timeout = 500)
    public void disjointMergeWithSimilarElements() {
        HashSet<String> set = new HashSet<String>();
        set.add("a");
        set.add("b");
        set.add("c");
        set.add("d");
        DisjointSets<String> disjointSet = new DisjointSets<String>(set);
        disjointSet.merge("a", "b");
        disjointSet.merge("a", "c");
        disjointSet.merge("c", "d");
        assertTrue(disjointSet.sameSet("a", "b"));
        assertTrue(disjointSet.sameSet("a", "c"));
        assertTrue(disjointSet.sameSet("a", "d"));
    }

    @Test(timeout = 500)
    public void linearKruskals() {
        Graph linearGraph = new Graph("4 1 2 1 2 3 1 3 4 1 4 5 1");
        int mstSize = 4;
        int mstTotalWeight = 4;
        int resultWeight = 0;
        Collection<Edge> resultMST = MST.kruskals(linearGraph);
        assertTrue(linearGraph.getEdgeList().containsAll(resultMST));
        assertEquals(mstSize, resultMST.size());
        for (Edge e : resultMST) {
            resultWeight = resultWeight + e.getWeight();
        }
        assertEquals(mstTotalWeight, resultWeight);
    }

    @Test(timeout = 500)
    public void circularKruskals() {
        Graph circularGraph = new Graph("5 1 2 1 2 3 1 3 4 1 4 5 1 5 1 2");
        Graph linearGraph = new Graph("4 1 2 1 2 3 1 3 4 1 4 5 1");
        int mstSize = 4;
        int mstTotalWeight = 4;
        int resultWeight = 0;
        Collection<Edge> resultMST = MST.kruskals(circularGraph);
        assertTrue(linearGraph.getEdgeList().containsAll(resultMST));
        assertEquals(mstSize, resultMST.size());
        for (Edge e : resultMST) {
            resultWeight = resultWeight + e.getWeight();
        }
        assertEquals(mstTotalWeight, resultWeight);
    }

    @Test(timeout = 500)
    public void linearPrims() {
        Graph linearGraph = new Graph("4 1 2 1 2 3 1 3 4 1 4 5 1");
        int mstSize = 4;
        int mstTotalWeight = 4;
        int resultWeight = 0;
        Collection<Edge> resultMST = MST.prims(linearGraph, 1);
        assertTrue(linearGraph.getEdgeList().containsAll(resultMST));
        resultMST = MST.prims(linearGraph, 2);
        assertTrue(linearGraph.getEdgeList().containsAll(resultMST));
        resultMST = MST.prims(linearGraph, 3);
        assertTrue(linearGraph.getEdgeList().containsAll(resultMST));
        resultMST = MST.prims(linearGraph, 4);
        assertTrue(linearGraph.getEdgeList().containsAll(resultMST));
        resultMST = MST.prims(linearGraph, 5);
        assertTrue(linearGraph.getEdgeList().containsAll(resultMST));
        assertEquals(mstSize, resultMST.size());
        for (Edge e : resultMST) {
            resultWeight = resultWeight + e.getWeight();
        }
        assertEquals(mstTotalWeight, resultWeight);
    }

    @Test(timeout = 500)
    public void circularPrims() {
        Graph circularGraph = new Graph("5 1 2 1 2 3 1 3 4 1 4 5 1 5 1 2");
        Graph linearGraph = new Graph("4 1 2 1 2 3 1 3 4 1 4 5 1");
        int mstSize = 4;
        int mstTotalWeight = 4;
        int resultWeight = 0;
        Collection<Edge> resultMST = MST.prims(circularGraph, 1);
        assertTrue(linearGraph.getEdgeList().containsAll(resultMST));
        resultMST = MST.prims(circularGraph, 1);
        assertTrue(linearGraph.getEdgeList().containsAll(resultMST));
        resultMST = MST.prims(circularGraph, 2);
        assertTrue(linearGraph.getEdgeList().containsAll(resultMST));
        resultMST = MST.prims(circularGraph, 3);
        assertTrue(linearGraph.getEdgeList().containsAll(resultMST));
        resultMST = MST.prims(circularGraph, 4);
        assertTrue(linearGraph.getEdgeList().containsAll(resultMST));
        resultMST = MST.prims(circularGraph, 5);
        assertTrue(linearGraph.getEdgeList().containsAll(resultMST));
        assertEquals(mstSize, resultMST.size());
        for (Edge e : resultMST) {
            resultWeight = resultWeight + e.getWeight();
        }
        assertEquals(mstTotalWeight, resultWeight);
    }

}
