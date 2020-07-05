import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class MWordGraph extends WordGraph
{
    private String startWord;
    private String endWord;

    /**
     * Holds the most recently-added words for 2nd+ MC Orders
     */
    private Queue<String> lastWords;

    public MWordGraph(String startWord, String endWord)
    {
        this.startWord = startWord;
        this.endWord = endWord;
        graph.add(startWord);
        graph.add(endWord);

        lastWords = new LinkedList<>();
    }

    /**
     * This method should return true if the last character of word is one of the following characters
     *  Period (.)
     *  Question mark (?)
     *  Exclamation mark (!)
     *  Single quote (‘)
     *  Double quote (“)
     */
    private boolean isEndWord(String word)
    {
        return word.matches(".*[.?!'\"]$");
    }

    @Override
    public void addWord(String newWord)
    {
        if (lastWord == null)
            lastWord = startWord;

        super.addWord(newWord);

        if (isEndWord(newWord.trim()))
        {
            super.addWord(endWord);
            lastWord = null;
        }
    }

    /**
     *  I have made the decision to only process sentences that have at least 'order' words between START and END.
     *      NOTE how 'super.addWord()' is never called until the lastWord blob is big enough!!!
     *  However, in generation, it will use lower-order MC, and if that instantly produces an end word, then it can end
     * @param newWord is the word to be added
     * @param order is the Markov Chain order: * MUST be >0 *
     *              --> I believe that if {order = 1}, then it will operate identically to addWord() but will not make
     *              adjustments in MarkovChain.java
     */
    private void addWordGen(String newWord, int order)
    {
        // Ensure that it's always trimmed!
        newWord = newWord.trim();

        // We will ensure this is always a sign of starting a new sentence
        if (lastWords.isEmpty())
            lastWords.add(startWord);

        // If the queue size isn't of size 'order', then we need to "prep" more by enqueuing more words!
        if (lastWords.size() < order)
            lastWords.add(newWord);
        else
        {
            /*
             * A big realization is that lastWord can be formatted however we want because the only thing that
             * "matters" is what is CHOSEN, which is what word it has an edge to (that's what's printed).
             */
            lastWord = lastWords.toString();

            // Many->newWord mapping ==> note that after this call, lastWord is set to newWord as per the super's def
            super.addWord(newWord);

            // Remove the oldest word and move-in newWord
            lastWords.remove();
            lastWords.add(newWord);

            // EndWord procedure
            if (isEndWord(newWord))
            {
                lastWord = lastWords.toString();
                super.addWord(endWord);

                // RESET!!!
                lastWords.clear();  // We're done with this sentence: clear the queue!
                lastWord = null;    // This doesn't affect our implementation but conventionally we'll do this.
            }
        }
    }

    /**
     *  Scalable, higher-order version of the processString() method
     * @param order is the Markov Chain order: MUST be >0
     */
    public void processStringGen(String str, int order)
    {
        for (String word : str.split(" "))
            addWordGen(word, order);
    }

    /**
     *	Scalable, higher-order version of the processFile() method
     * @param order is the Markov Chain order: MUST be >0
     */
    public void processFileGen(String filename, int order)
    {
        try
        {
            //open the specified file
            File file = new File(filename);
            Scanner in = new Scanner(file);

            //loop through each line of the file and process it
            while(in.hasNextLine())
                processStringGen(in.nextLine(), order);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
