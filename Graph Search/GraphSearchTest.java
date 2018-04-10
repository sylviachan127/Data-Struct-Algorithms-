import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
 
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class GraphSearchTest {

	Map<String, List<String>> adjList;
	 
	    @Before
	    public void setUp() {
	        adjList = new HashMap<>();
	 
	        LinkedList<String> aList = new LinkedList<>();
	        aList.add("B");
	        aList.add("D");
	        aList.add("F");
	        adjList.put("A", aList);
	 
	        LinkedList<String> bList = new LinkedList<>();
	        bList.add("C");
	        bList.add("D");
	        adjList.put("B", bList);
	 
	        LinkedList<String> cList = new LinkedList<>();
	        adjList.put("C", cList);
	 
	        LinkedList<String> dList = new LinkedList<>();
	        dList.add("A");
	        adjList.put("D", dList);
	 
	        LinkedList<String> fList = new LinkedList<>();
	        fList.add("G");
	        adjList.put("F", fList);
	 
	        LinkedList<String> gList = new LinkedList<>();
	        gList.add("H");
	        adjList.put("G", gList);
	 
	        LinkedList<String> hList = new LinkedList<>();
	        adjList.put("H", hList);

	        LinkedList<String> zList = new LinkedList<>();
	        adjList.put("Z", zList);
	 
	 
	        /**  graph
	        * 	   F<-A->B->C
	        *      |  |  |
	        *      v  |  v
	        *   H<-G  <> D
	        *   
	        *
	        *  Z
	        */
	    }
	 
	    @Test(timeout = 200)
	    public void testDepthFirstSearch1() {
	        assertTrue(GraphSearch.depthFirstSearch("A", adjList, "H"));
	    }

	    @Test(timeout = 200)
	    public void testDepthFirstSearch2() {
	    	assertFalse(GraphSearch.depthFirstSearch("A", adjList, "Z"));
	    }

	    @Test(timeout = 200)
	    public void testDepthFirstSearch3() {
	    	assertFalse(GraphSearch.depthFirstSearch("C", adjList, "H"));
	    }

	    @Test(timeout = 200)
	    public void testDepthFirstSearch4() {
	    	assertTrue(GraphSearch.depthFirstSearch("D", adjList, "H"));
	    }

	    @Test(timeout = 200)
	    public void testBreadthFirstSearch1() {
	        assertTrue(GraphSearch.breadthFirstSearch("A", adjList, "H"));
	    }

	     @Test(timeout = 200)
	    public void testBreadthFirstSearch2() {
	    	assertFalse(GraphSearch.breadthFirstSearch("A", adjList, "Z"));
	    }

	    @Test(timeout = 200)
	    public void testBreadthFirstSearch3() {
	    	assertFalse(GraphSearch.breadthFirstSearch("C", adjList, "H"));
	    }

	    @Test(timeout = 200)
	    public void testBreadthFirstSearch4() {
	    	assertTrue(GraphSearch.breadthFirstSearch("D", adjList, "H"));
	    }

	    @Test(timeout = 200, expected = IllegalArgumentException.class)
	    public void testDFSNullMap() {
	    	GraphSearch.depthFirstSearch("A", null, "H");
	    }

	    @Test(timeout = 200, expected = IllegalArgumentException.class)
	    public void testBFSNullMap() {
	    	GraphSearch.breadthFirstSearch("A", null, "H");
	    }

	    @After
	    public void tearDown() {
	        adjList = null;
	    }

}
