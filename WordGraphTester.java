import junit.framework.TestCase;

public class WordGraphTester extends TestCase
{

    public static void main(String[] args) 
    {
    	//Create a word graph object
    	WordGraph wg = new WordGraph();
    	WordGraph wc = new WordGraph();
    	
    	//Process a string
    	String str = "One ring to rule them all, one ring to find them. One ring to bring them all and in the darkness bind them.";
    	wg.processString(str);

    	String woodchuck = "How much wood would a woodchuck chuck if a woodchuck could chuck wood";
    	wc.processString(woodchuck);
    	
    	//Output the graph so you can see the word associations and weights
    	System.out.println(wg.getGraph());
    	System.out.println(wc.getGraph());
    }
    
    
}