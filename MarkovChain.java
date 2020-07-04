import java.util.List;
import java.util.LinkedList;
import java.util.Map;

public class MarkovChain 
{
	//INSTANCE VARIABLES
	/**
	 *	This holds all of the word relationships
	 */
	private MWordGraph wg;
	
	/**
	 *	This remembers the lastWord that this Markov Chain generated
	 */
	private String lastWord;

	private String startWord;
	private String endWord;

	//CONSTRUCTOR
    public MarkovChain(String startWord, String endWord)
    {
    	//initialize instance variables
    	wg = new MWordGraph(startWord, endWord);

    	this.startWord = startWord;
    	this.endWord = endWord;

    	lastWord = null;
    }

    public MarkovChain()
    {
        this("[START]", "[END]");
    }
    
    //METHODS
    /**
     *	Add word relationships from the specified file
     */
    public void train(String filename)
    {
    	//TODO: add data from filename to the WordGraph
        wg.processFile(filename);
    }
    
    /**
     *	Get a list of words that follow lastWord.
	 *	words with more *weight* will appear more times in the list.
	 *  if lastWord is null, then return the words that are neighbors of [START]
     */
    public List<String> getNextWords()
    {
    	//TODO: return List<String> of words that are neighbors of lastWord, weighted appropriatly
    	String findMe = lastWord == null ? startWord : lastWord;

    	List<String> ret = new LinkedList<String>();
    	Map<String, Integer> data = wg.getGraph().getNeighborWeights(findMe);
    	for (String word : data.keySet()) {
            for (int i = 0; i < data.get(word); i++) {
                ret.add(word);
            }
        }
    	return ret;
    }
    
    /**
     *	Get a word that follows lastWord
     *	Use a weighted random number to pick the next word from the list of all of lastWord's neighbors in wordGraph
     *	If lastWord is null or [END], this should generate a word that *starts* a sentence
     *	Remember the word that is about to be returned in the appropriate instance variable
     */
    public String getNextWord()
    {
    	//TODO: return random word with an edge from lastWord
        List<String> pickMe = this.getNextWords();
        return pickMe.get((int) (Math.random()*pickMe.size()));
    }
    
    /**
     *	Generate a sentence using the data in the wordGraph. 
     */
    public String generateSentence()
    {
    	//TODO: generate a sentence from [START] to [END]
        String ret = "";

        lastWord = null;    // IMPORTANT to "freshstart"

        String next = getNextWord();
        while (!next.equals(endWord)) {
            ret += next + " ";
            lastWord = next;
            next = getNextWord();
        }

        return ret;
    }
    
    
}