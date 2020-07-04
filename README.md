# Markov Chain Spam Generator

**=== Project Description ===**

Implement a Graph using an Adjacency Map and extends its functionality to store weights between nodes.

Then, implement a Word Graph that uses the Weighted Adjacency Map implementation to create a mapping of input text material in the form of a string or a file.

Finally, implement first, second, and third-order Markov Chain programs that use the Word Graph implementation to generate increasingly human-cohesive sentences based on an input text training file.

***Personal Extension***: Create a program that uses the third-order Markov Chain program to generate a spam email and send it to a list of recipients from a file.

**=== Objective ===**

Understand how a graph data structure works through implementation of common, notable operations.

Understand how to populate a graph using real-world data and subsequently observe what kinds of "conclusions" can be drawn by using it.

*Employ a program in a real-world application to annoy people.*

**=== Testing ===**

All *...Tester.java* files contain modularized self-written and provided test cases to test each step of the development process.

*MarkovChainTester.java* and *FinalOutput.java* contain code to produce output which can then be qualitatively judged on the basis of randomness, syntactic correctness, and cohesiveness.

**=== Description of Relevant Files ===**

AdjacencyListGraph.java

- Basic implementation of a Graph using a Map.

WeightedAdjacencyListGraph.java

- Implementation of a weighted-edge Graph by layering an integer map on top of the previous structure.

WordGraph.java

- String-based weighted graph taking an input string or text file.

MWordGraph.java

- Markov-specific functionality abstracted in order to maintain integrity of WordGraph.java for grading purposes

MarkovChain.java

MarkovChainGen2.java

MarkovChainGen3.java

- Markov Chain programs for 1st, 2nd, and 3rd order chains, respectively.

***Spam.java***

- Spam email program.