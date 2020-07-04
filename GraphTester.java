import junit.framework.*;

public class GraphTester extends TestCase {

    public static void main(String[] args) 
    {
    	//create a graph object to test
    	Graph<String> graph = new AdjacencyListGraph<String>();
    	
    	//add nodes to the graph
    	assertTrue(graph.add("A"));
    	assertTrue(graph.add("B"));
    	assertTrue(graph.add("C"));
    	
    	//add edges to the graph
    	assertTrue(graph.addEdge("A", "B"));
    	assertTrue(graph.addEdge("B", "A"));
    	assertTrue(graph.addEdge("A", "C"));
    	assertTrue(graph.addEdge("B", "C"));
    	
    	//print the graph
    	System.out.println(graph);
    }
}