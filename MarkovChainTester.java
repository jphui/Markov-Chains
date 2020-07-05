public class MarkovChainTester 
{
	public static void main(String[] args)
	{
		//https://www.gutenberg.org/catalog/
		
		//Create a MarkovChain object
		MarkovChain chain = new MarkovChain("<START>", "<END>");
		
		//train the MarkovChain with some data
		chain.train("training_material/garfield.txt");
		
		//Generate some random sentences
		int num = 10;
		for(int i = 0; i < num; i++)
		{
			System.out.println(chain.generateSentence());
			//System.out.println();
		}
	}
}
