class MWordGraph extends WordGraph
{
    private String startWord;
    private String endWord;

    public MWordGraph(String startWord, String endWord)
    {
        this.startWord = startWord;
        this.endWord = endWord;
        graph.add(startWord);
        graph.add(endWord);
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
            this.addWord(endWord);
            lastWord = null;
        }
    }
}
