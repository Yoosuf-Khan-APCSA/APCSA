/**
 *	AnagramMaker - <description goes here>
 *
 *	Requires the WordUtilities, SortMethods, Prompt, and FileUtils classes
 *
 *	@author	
 *	@since	
 */
public class AnagramMaker {
								
	private final String FILE_NAME = "randomWords.txt";	// file containing all words
	
	private WordUtilities wu;	// the word utilities for building the word
								// database, sorting the database,
								// and finding all words that match
								// a string of characters
	
	// variables for constraining the print output of AnagramMaker
	private int numWords;		// the number of words in a phrase to print
	private int maxPhrases;		// the maximum number of phrases to print
	private int numPhrases;		// the number of phrases that have been printed
		
	/*	Initialize the database inside WordUtilities
	 *	The database of words does NOT have to be sorted for AnagramMaker to work,
	 *	but the output will appear in order if you DO sort.
	 */
	public AnagramMaker() {
		wu = new WordUtilities();
		wu.readWordsFromFile(FILE_NAME);
		wu.sortWords();
	}
	
	public static void main(String[] args) {
		AnagramMaker am = new AnagramMaker();
		am.run();
	}
	
	/**	The top routine that prints the introduction and runs the anagram-maker.
	 */
	public void run() {
		printIntroduction();
		runAnagramMaker();
		System.out.println("\nThanks for using AnagramMaker!\n");
	}
	
	/**
	 *	Print the introduction to AnagramMaker
	 */
	public void printIntroduction() {
		System.out.println("\nWelcome to ANAGRAM MAKER");
		System.out.println("\nProvide a word, name, or phrase and out comes their anagrams.");
		System.out.println("You can choose the number of words in the anagram.");
		System.out.println("You can choose the number of anagrams shown.");
		System.out.println("\nLet's get started!");
	}
	
	/**
	 *	Prompt the user for a phrase of characters, then create anagrams from those
	 *	characters.
	 */
	public void runAnagramMaker() {
		int wordAmt,maxPrint;
		String phrase;
		
		while (!word.equals("q")){
			phrase=Prompt.getString("Word(s), name or phrase (q to quit) ");
			wordAmt=Prompt.getString("Number of words in anagram ");
			maxPrint=Prompt.getString("Maximum number of anagrams to print ");
			anagram=new String[wordAmt];
			for (int i=0; i<phrase.length();i++){
				if(!phrase.charAt(i).isLetter())phrase=phrase.substring(0,i)+phrase.substring(i+1,phrase.length())
			}
			phrase=phrase.toLower();
			anagramRecurse(phrase,wordAmt,maxPrint);
		}

	}
	public void anagramRecurse(String phrase,wordAmt){
		ArrayList<String> anagram;
		anagram=allWords(phrase)
		if (phrase.length()==0||wordAmt==0)System.out.println( "");
		while (anagram.size()>0){
			String word=phrase;
			for(int i=0;i<anagram.get(0).length();i++){
				int ind =phrase.indexOf(anagram.get(0).charAt(i));
				phrase=phrase.substring(0,ind)+phrase.substring(ind+1,phrase.length())
			}
			System.out.print(); 
			anagramRecurse(phrase,wordAmt-1)
			
		}
	}
	
}
