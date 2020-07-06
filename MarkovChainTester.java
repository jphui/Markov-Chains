public class MarkovChainTester 
{
	public static void main(String[] args)
	{
		//https://www.gutenberg.org/catalog/
		String testFile = "training_material/jane-austen.txt";

		//Create a MarkovChain object
		MarkovChain chain = new MarkovChain();
		
		//train the MarkovChain with some data
		chain.train(testFile);
		
		//Generate some random sentences
		int num = 10;

		for(int i = 0; i < num; i++)
		{
			System.out.println(chain.generateSentence());
		}

		System.out.println();

		MarkovChainGen2 chain2 = new MarkovChainGen2();
		chain2.train(testFile);
		for(int i = 0; i < num; i++)
		{
			System.out.println(chain2.generateSentence());
		}
	}
}
