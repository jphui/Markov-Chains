public class MarkovChainGen3 extends MarkovChainGen2
{
	//INSTANCE VARIABLES
    /**
     *	This holds relationships between pairs-of-words -> next-word
     */
    private MWordGraph wgTrios;
    protected String lastlastlastWord;

    //CONSTRUCTOR
    public MarkovChainGen3(String START, String END)
    {
        //Initialize Instance Variables
        super(START, END);
        wgTrios = new MWordGraph(START, END);

        lastlastlastWord = null;
    }

    public MarkovChainGen3()
    {
        this("[START]", "[END]");
    }
    
    //METHODS

    //TODO: override appropriate methods to generate 3rd Degree Markov Chains

    @Override
    public void train(String filename)
    {
        super.train(filename);
        wgTrios.processFileGen(filename, 3);
    }


    @Override
    public void updateMemory()
    {
        lastlastlastWord = getLastlastWord();
        super.updateMemory();
    }

    @Override
    public String generateSentence()
    {
        // Fresh start
        lastlastlastWord = null;

        return super.generateSentence();
    }
}