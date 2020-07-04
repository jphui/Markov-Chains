import junit.framework.*;

public class WeightedGraphTester extends TestCase {

    public static void main(String[] args) 
    {
    	//create a graph object to test
    	WeightedGraph<String> graph = new WeightedAdjacencyListGraph<String>();
    	
    	//add nodes to the graph
    	assertTrue(graph.add("A"));
    	assertTrue(graph.add("B"));
    	assertTrue(graph.add("C"));
    	
    	//add edges to the graph
    	assertTrue(graph.addEdge("A", "B"));
    	assertTrue(graph.addEdge("B", "A"));
    	assertTrue(graph.addEdge("A", "C"));
    	assertTrue(graph.addEdge("B", "C"));
    	
    	//add weights to the graph
    	graph.setWeight("A", "B", 7);
    	
    	//print the grapha
    	System.out.println(graph);
    }
}