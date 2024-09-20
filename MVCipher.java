// imports go here

/**
 *	MVCipher - Add your description here
 *	Requires Prompt and FileUtils classes.
 *	
 *	@author	
 *	@since	
 */
public class MVCipher {
	
	// fields go here
		
	/** Constructor */
	public MVCipher() { }
	
	public static void main(String[] args) {
		MVCipher mvc = new MVCipher();
		mvc.run();
	}
	
	/**
	 *	Method header goes here
	 */
	public void run() {
		String key="bob",inputFile,outputFile;
		String file[];
		boolean isAlpha=true;
		int userChoice = 0, inputLength;
		System.out.println("\n Welcome to the MV Cipher machine!\n");
		
		/* Prompt for a key and change to uppercase
		   Do not let the key contain anything but alpha
		   Use the Prompt class to get user input */
		do{
			if(!isAlpha||key.length()<3)
				System.out.println("ERROR: Key must be all letters and at least 3 characters long");
			isAlpha=true;
			key=Prompt.getString("Please input a word to use as key (letters only)");
			key=key.toUpperCase();
			for(int i =0; i<key.length();i++){
				if(key.charAt(i)<'A'||key.charAt(i)>'Z')
					isAlpha=false;
			}
		}while(!isAlpha||key.length()<3);
		
		/* Prompt for encrypt or decrypt */
		userChoice=Prompt.getInt("Encrypt or decrypt?",1,2);
			
		/* Prompt for an input file name */
		inputFile=Prompt.getString("Name of file to encrypt");
		
		/* Prompt for an output file name */
		outputFile=Prompt.getString("Name of output file");
		
		/* Read input file, encrypt or decrypt, and print to output file */
		if(userChoice==1){
			fileUtils.openToRead(inputFile);
			if(inputhasNextLine){
				inputLength++;
			}
		}
		else{
			
		}
		
		/* Don't forget to close your output file */
	}
	
	// other methods go here
	
}
