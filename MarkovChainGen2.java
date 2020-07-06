import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class MarkovChainGen2 extends MarkovChain
{
	//INSTANCE VARIABLES
	/**
	 *	This holds relationships between pairs-of-words -> next-word
	 */
	private MWordGraph wgPairs;

    /**
     * Holds the most recently-generated words
     */
    private String lastlastWord;

	//CONSTRUCTOR
    public MarkovChainGen2(String START, String END)
    {
    	//Initialize Instance Variables
        super(START, END);
        wgPairs = new MWordGraph(START, END);

        lastlastWord = null;
    }

    public MarkovChainGen2()
    {
        this("[START]", "[END]");
    }
    
    //METHODS
    public String getLastlastWord() { return lastlastWord; }

    @Override
    public void train(String filename)
    {
    	super.train(filename); //tell the MarkovChain super class to train() like it normally does
    	
    	//TODO: do some magic to store 2nd order markov chain data
    	wgPairs.processFileGen(filename, 2);
    }
    
    
    //TODO: override any other other methods needed to generate 2nd Degree Markov Chains

    @Override
    /**
     * Edge cases: "starting out" and "not seeing the current pair->single mapping
     * APPROACH: getNextWord() will operate the same at all levels!
     *      - It will be given a list from getNextWords and choose 1 randomly...
     *      - THUS, getNextWords() will change at the different orders!
     *      super.updatelastwords()
     */
    public List<String> getNextWords()
    {
        // First-order results
        List<String> firstOrder = super.getNextWords();

        // This is the "starting" case, in which the choices must be first-order!
        if (lastlastWord == null)
        {
            lastlastWord = START;
            return firstOrder;
        }

        // At this point, we know that both lastlast and last are filled
        // This is in the format of Queue.toString()
        String findMe = "[" + lastlastWord + ", " + getLastWord() + "]";

        Map<String, Integer> data = wgPairs.getGraph().getNeighborWeights(findMe);

        List<String> ret = new LinkedList<>();
        if (!data.isEmpty())
        {
            for (String word : data.keySet())
            {
                for (int i = 0; i < data.get(word); i++)
                {
                    ret.add(word);
                }
            }
        }
        else // We need a backup plan in case there is no data for the pair!
            ret = firstOrder;
        return ret;
    }

    @Override
    public void updateMemory()
    {
        lastlastWord = getLastWord();
        super.updateMemory();
    }

    @Override
    public String generateSentence()
    {
        // Fresh start
        lastlastWord = null;

        return super.generateSentence();
    }
}