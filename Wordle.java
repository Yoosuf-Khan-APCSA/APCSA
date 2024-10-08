
import java.awt.Font;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Wordle.java
 *
 * Guess a five letter word chosen from a list (letters ending in s or ed are excluded).
 * After each guess letters will be yellow (partial match) green (exact match) or dark gray (no match) 
 * user gets 6 guesses to find the word. if word is found or user runs out of turns user can reset 
 * the game.
 * @author	Scott DeRuiter and David Greenstein and Yoosuf Khan
 * @version	1.0
 * @since	October 7, 2024
 */
public class Wordle {

    /**
     * This is a complete list of fields for the game
     */

    /**
     * A String to store the word that the player is trying to find.
     */
    private String word;

    /**
     * An array of String to store the guesses that have been made.
     */
    private String[] wordGuess;

    private int[][] wordGuessColors;

    private String[] wordList;

    private String[] wordAllowedList;

    /**
     * A String to store the letters in the current guess. Can have from 0 to 5 chars
     */
    private String letters;

    /**
     * File that contains 5-letter words to find.
     */
    private final String WORDS5 = "words5.txt";

    /**
     * File that contains 5-letter words allowed for user guesses. (bigger file)
     */
    private final String WORDS5_ALLOWED = "words5allowed.txt";

    /**
     * A variety of boolean variables to turn things on and off. These include:
     * show	-	when true, will print the current word to the terminal
     * readyForKeyInput -	when true, will accept keyboard input, when false,
     * will not accept keyboard input. readyForMouseInput -	when true, will
     * accept mouse input, when false, will not accept mouse input. activeGame -
     * when false, will only accept action on the RESET button.
     */
    private boolean show, readyForKeyInput, readyForMouseInput, activeGame;

    /**
     * An array to determine how to color the keyboard at the bottom of the
     * gameboard. 0 for not checked yet, 1 for no match, 2 for partial, 3 for
     * exact
     */
    private int[] keyBoardColors;

    /**
     * Creates a Wordle object. A constructor. Initializes all of the variables
     * by calling the method initAll.
     *
     * @param testWord	if this String is found in words5allowed.txt, it will be
     * used to set word. This method is complete.
     */
    public Wordle(String showIt, String testWord) {
        show = false;
        if (showIt.equalsIgnoreCase("show")) {
            show = true;
        }

        initAll(testWord);
    }

    /**
     * Initializes all fields. Calls openFileAndChooseWord to choose the word.
     * Sets all of the keyboard colors to light gray to start.
     *
     * @param testWord	if this String is found in words5allowed.txt, it will be
     * used to set word. This method is complete.
     */
    public void initAll(String testWord) {
        wordGuess = new String[6];
        wordGuessColors = new int[6][5];
        for (int i = 0; i < wordGuess.length; i++) {
            wordGuess[i] = new String("");
            for (int j = 0; j < wordGuessColors[0].length; j++) {
                wordGuessColors[i][j] = 1;
            }
        }

        letters = "";
        readyForKeyInput = activeGame = true;
        readyForMouseInput = false;
        keyBoardColors = new int[29];
        word = openFileAndChooseWord(WORDS5, testWord);
    }

    /**
     * The main method, to run the program. The constructor is called, so that
     * all of the fields are initialized. The canvas is set up, and the GUI (the
     * game of Wordle) runs. THIS METHOD IS INCOMPLETE. compelte
     */
    public static void main(String[] args) {
        String testWord = new String("");
        String showIt = new String("");
        if (args.length > 0) {
            if (args[0].equals("show")) {
                showIt = "show";
            }
        }
        if (args.length > 1) {
            if (args[1].length() > 0) {
                testWord = args[1];
            }
        }
        // Determines if args[0] and args[1] are set
        // args[0] is "show" which means to show the word chosen
        // args[1] is a word which is used as the chosen word

        Wordle run = new Wordle(showIt, testWord);
        run.setUpCanvas();
        run.playGame();
    }

    /**
     * Sets up the canvas. Enables double buffering so that the gameboard is
     * drawn offscreen first, then drawn to the gameboard when everything is
     * ready (with the show method). This method is complete.
     */
    public void setUpCanvas() {
        StdDraw.setCanvasSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        StdDraw.setXscale(0, Constants.SCREEN_WIDTH);
        StdDraw.setYscale(0, Constants.SCREEN_HEIGHT);

        StdDraw.enableDoubleBuffering();
    }

    /**
     * Runs the game. An endless loop is created, constantly cycling and looking
     * for user input. This method is complete.
     */
    public void playGame() {
        boolean keepGoing = true;
        while (keepGoing) {
            if (activeGame) {
                drawPanel();
            }
            update();
        }
    }

    /**
     * If the testWord is valid, it is used as the "goal word". If it is not,
     * then the text file is opened, and a word is chosen at random from the
     * list to be the "goal word". If the field variable show is true, it will
     * print the chosen word to the terminal window. Be sure to close file when
     * you are done.
     *
     * @param inFileName	this file is to be opened, and a random word is to be
     * chosen from it.
     * @param testWord	if this String is found in words5allowed.txt, it will be
     * used to set word.
     * @return	the word chosen as the "goal word". THIS METHOD IS INCOMPLETE.
     * COMPELTe
     */
    public String openFileAndChooseWord(String inFileName, String testWord) {
        int listLength = 0;
        String result = "SMART";
        boolean isValid = false;
        java.util.Scanner input;
        input = FileUtils.openToRead(inFileName);
        while (input.hasNext()) {
            listLength++;
            input.next();
        }
        wordList = new String[listLength];
        input = FileUtils.openToRead(inFileName);
        for (int i = 0; i < wordList.length; i++) {
            wordList[i] = input.next().toUpperCase();
        }
        listLength = 0;
        input = FileUtils.openToRead(WORDS5_ALLOWED);
        while (input.hasNext()) {
            listLength++;
            input.next();
        }
        wordAllowedList = new String[listLength];
        input = FileUtils.openToRead(WORDS5_ALLOWED);
        for (int i = 0; i < wordAllowedList.length; i++) {
            wordAllowedList[i] = input.next().toUpperCase();
        }
        input.close();

        for (int i = 0; i < wordAllowedList.length; i++) {
            if (wordAllowedList[i].equals(testWord.toUpperCase())) {
                isValid = true;
            }
        }
        if (isValid) {
            result = testWord.toUpperCase(); 
        }else {
            result = wordList[(int) (Math.random() * wordList.length)];
        }
        if (show) {
            System.out.println(result);
        }
        return result;
    }

    /**
     * Checks to see if the word in the parameter list is found in the text file
     * words5allowed.txt Returns true if the word is in the file, false
     * otherwise.
     *
     * @param possibleWord the word to looked for in words5allowed.txt
     * @return true if the word is in the text file, false otherwise THIS METHOD
     */
    public boolean inAllowedWordFile(String possibleWord) {
        for (int i = 0; i < wordAllowedList.length; i++) {
            if (wordAllowedList[i].equals(possibleWord)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Processes the guess made by the user. This method will only be called if
     * the field variable letters has length 5. The guess in letters will need
     * to be checked against the words in words5allowed.txt. The method
     * inAllowedWordFile will be called for this task. If the guess in letters
     * does not exist in the text file, a message is displayed to the user in
     * the form of a JOptionPane with JDialog. 
     */
    public void processGuess() {
        letters = letters.toUpperCase();
        // if guess is in words5allowed.txt then put into guess list

        if (inAllowedWordFile(letters)) {
            int guessNumber = 0;
            for (int i = 0; i < wordGuess.length; i++) {
                if (wordGuess[i].length() == 5) {
                    guessNumber = i + 1;
                }
            }
            wordGuess[guessNumber] = letters.toUpperCase();
            letters = "";
        } else {
            JOptionPane pane = new JOptionPane(letters + " is not in word list.");
            JDialog d = pane.createDialog(null, "INVALID INPUT");
            d.setLocation(365, 250);
            d.setVisible(true);
        }
        // else if guess is not in words5allowed.txt then print dialog box

    }

    /**
     * Draws the entire game panel. This includes the guessed words, the current
     * word being guessed, and all of the letters in the "keyboard" at the
     * bottom of the gameboard. The correct colors will need to be chosen for
     * every letter. 
     */
    public void drawPanel() {
        StdDraw.clear(StdDraw.WHITE);

        // Determine color of guessed letters and draw backgrounds
        // 0 for not checked yet, 1 for no match, 2 for partial, 3 for exact
        // draw guessed letter backgrounds
        int lastGuessNum = 0;
        int indexOfPartial = 0, LastIndexOfPartial = 0, MidIndexOfPartial;
        int wordLetCount = 0, keyboardPlace = 0;
		boolean hasExactAlready=false;
        for (int i = 0; i < 6; i++) {
            if (wordGuess[i].length() == 5) {
                lastGuessNum++;
            }
        }
        lastGuessNum--;

        if (lastGuessNum >= 0) {
            for (int c = 0; c < 5; c++) {
                indexOfPartial = word.indexOf(wordGuess[lastGuessNum].charAt(c));
                LastIndexOfPartial = word.lastIndexOf(wordGuess[lastGuessNum].charAt(c));
                MidIndexOfPartial = word.indexOf(wordGuess[lastGuessNum].charAt(c), indexOfPartial);
                wordLetCount = 0;
				hasExactAlready=false;
				
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == wordGuess[lastGuessNum].charAt(c)) {
                        wordLetCount++;
                    }
                }
				hasExactAlready=false;
				if(wordLetCount>0&&word.charAt(indexOfPartial)==wordGuess[lastGuessNum].charAt(indexOfPartial))
					hasExactAlready=true;
				if(wordLetCount>1&&word.charAt(LastIndexOfPartial)==wordGuess[lastGuessNum].charAt(LastIndexOfPartial))
					hasExactAlready=true;
				if(wordLetCount>2&&word.charAt(MidIndexOfPartial)==wordGuess[lastGuessNum].charAt(MidIndexOfPartial))
					hasExactAlready=true;
                keyboardPlace = -1;
                for (char b = 0; b <= 27; b++) {
                    if (Constants.KEYBOARD[b].charAt(0) == wordGuess[lastGuessNum].charAt(c) && keyboardPlace == -1) {
                        keyboardPlace = b;
                    }
                }
                if (wordLetCount == 1) {
                    if (indexOfPartial != -1 && wordGuessColors[lastGuessNum][wordGuess[lastGuessNum].indexOf(wordGuess[lastGuessNum].charAt(c))] != 2 &&
					 wordGuessColors[lastGuessNum][wordGuess[lastGuessNum].indexOf(wordGuess[lastGuessNum].charAt(c))] != 3&&!hasExactAlready) {
                        wordGuessColors[lastGuessNum][c] = 2;
                        keyBoardColors[keyboardPlace] = 2;
                    }
                } else if (wordLetCount == 2) {
                    if (indexOfPartial != -1 && (wordGuess[lastGuessNum].charAt(indexOfPartial) != word.charAt(indexOfPartial)
                            || wordGuess[lastGuessNum].charAt(LastIndexOfPartial) != word.charAt(LastIndexOfPartial))
                            && wordGuessColors[lastGuessNum][wordGuess[lastGuessNum].indexOf(wordGuess[lastGuessNum].charAt(c))] != 2) {
                        wordGuessColors[lastGuessNum][c] = 2;
                        keyBoardColors[keyboardPlace] = 2;
                    }
                } else if (wordLetCount == 3) {
                    if (indexOfPartial != -1 && (wordGuess[lastGuessNum].charAt(indexOfPartial) != word.charAt(indexOfPartial)
                            || wordGuess[lastGuessNum].charAt(LastIndexOfPartial) != word.charAt(LastIndexOfPartial)
                            || wordGuess[lastGuessNum].charAt(MidIndexOfPartial) != word.charAt(MidIndexOfPartial))
                            && wordGuessColors[lastGuessNum][wordGuess[lastGuessNum].indexOf(wordGuess[lastGuessNum].charAt(c))] != 2) {
                        wordGuessColors[lastGuessNum][c] = 2;
                        keyBoardColors[keyboardPlace] = 2;
                    }
                } else {
                    keyBoardColors[keyboardPlace] = 1;
                }

                if (wordGuess[lastGuessNum].charAt(c) == word.charAt(c)) {
                    wordGuessColors[lastGuessNum][c] = 3;
                    keyBoardColors[keyboardPlace] = 3;
                }
            }

        }
        /*
		for(int i=0;i<wordGuessColors.length;i++){
			for(int j=0;j<wordGuessColors[0].length;j++){
				System.out.print(" "+ wordGuessColors[i][j]);
			}
			System.out.println();
		}
         */
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 5; col++) {
                if (wordGuess[row].length() != 0) //  THIS METHOD IS INCOMPLETE.
                {
                    if (wordGuessColors[row][col] == 1) {
                        StdDraw.picture(209 + col * 68, 650 - row * 68, "letterFrameDarkGray.png"); 
                    }else if (wordGuessColors[row][col] == 2) {
                        StdDraw.picture(209 + col * 68, 650 - row * 68, "letterFrameYellow.png"); 
                    }else if (wordGuessColors[row][col] == 3) {
                        StdDraw.picture(209 + col * 68, 650 - row * 68, "letterFrameGreen.png");
                    }
                } else {
                    StdDraw.picture(209 + col * 68, 650 - row * 68, "letterFrame.png");
                }
            }
        }

        // draw Wordle board
        Font font = new Font("Arial", Font.BOLD, 12);
        StdDraw.setFont(font);
        StdDraw.picture(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT - 30, "wordle.png");

        // draw keyboard with appropriate colors
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        int place = 0;
        String tempWord = "";
        for (int[] pair : Constants.KEYPLACEMENT) {
            if (place == 19 || place == 27 || place == 28) {
                StdDraw.picture(pair[0], pair[1], "keyBackgroundBig.png");
            }
            //  This needs to be modified a great deal,
            //  so that the correct colors show up.
            if (keyBoardColors[place] == 2) {
                StdDraw.picture(pair[0], pair[1], "keyBackgroundYellow.png");
            } else if (keyBoardColors[place] == 3) {
                StdDraw.picture(pair[0], pair[1], "keyBackgroundGreen.png");
            } else if (keyBoardColors[place] == 1) {
                StdDraw.picture(pair[0], pair[1], "keyBackgroundDarkGray.png");
            } else {
                StdDraw.picture(pair[0], pair[1], "keyBackground.png");
            }
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(pair[0], pair[1], Constants.KEYBOARD[place]);
            place++;
        }

        // draw guesses
        drawAllLettersGuessed();

        StdDraw.show();
        StdDraw.pause(Constants.DRAW_DELAY);

        // check if won or lost
        checkIfWonOrLost();
    }

    /**
     * This method is called by drawPanel, and draws all of the letters in the
     * guesses made by the user. This method is complete.
     */
    public void drawAllLettersGuessed() {
        // Draw guessed letters
        Font font = new Font("Arial", Font.BOLD, 34);
        StdDraw.setFont(font);
        int guessNumber = 0;
        for (int i = 0; i < wordGuess.length; i++) {
            if (wordGuess[i].length() > 0) {
                for (int j = 0; j < wordGuess[i].length(); j++) {
                    StdDraw.text(209 + j * 68, 644 - i * 68, "" + wordGuess[i].charAt(j));
                }
            }
            if (wordGuess[i].length() == 5) {
                guessNumber = i + 1;
            }
        }
        for (int i = 0; i < letters.length(); i++) {
            StdDraw.text(209 + i * 68, 644 - guessNumber * 68, "" + letters.substring(i, i + 1));
        }
    }

    /**
     * Checks to see if the game has been won or lost. The game is won if the
     * user enters the correct word with a guess. The game is lost when the user
     * does not enter the correct word with the last (6th) guess. An appropriate
     * message is displayed to the user in the form of a JOptionPane with
     * JDialog for a win or a loss. 
     */
    public void checkIfWonOrLost() {
        String lastWord = "";
        for (int i = 0; i < wordGuess.length; i++) {
            if (wordGuess[i].length() == 5) {
                lastWord = wordGuess[i];
            }
        }

        // declare the winner by matching the word
        if (lastWord.equals(word)) {
            activeGame = false;
            JOptionPane pane = new JOptionPane(lastWord + " is the word!  Press RESET to begin again");
            JDialog d = pane.createDialog(null, "CONGRATULATIONS!");
            d.setLocation(365, 250);
            d.setVisible(true);
        } // else if all guesses are filled then declare loser
        else if (wordGuess[5].length() == 5) {
            activeGame = false;
            JOptionPane pane = new JOptionPane(word + " was the word.  Press RESET to begin again");
            JDialog d = pane.createDialog(null, "Sorry!");
            d.setLocation(365, 250);
            d.setVisible(true);
        }

    }

    /**
     * This method is constantly looking for keyboard or mouse input from the
     * user, and reacting to this input. This method is complete.
     */
    public void update() {
        if (activeGame) {
            respondToKeys();
        }
        respondToMouse();
    }

    /**
     * Responds to input from the keyboard. Will call the method processGuess
     * when the user has entered a word to guess. This method is complete.
     */
    public void respondToKeys() {
        if (readyForKeyInput && StdDraw.hasNextKeyTyped()
                && StdDraw.isKeyPressed(KeyEvent.VK_BACK_SPACE) && letters.length() > 0) {
            letters = letters.substring(0, letters.length() - 1);
            readyForKeyInput = false;
        } else if (readyForKeyInput && StdDraw.hasNextKeyTyped()
                && StdDraw.isKeyPressed(KeyEvent.VK_ENTER) && letters.length() == 5) {
            processGuess();
            readyForKeyInput = false;
        } else if (readyForKeyInput && StdDraw.hasNextKeyTyped() && letters.length() < 5) {
            String letter = "" + StdDraw.nextKeyTyped();
            letter = letter.toUpperCase();
            if (letter.charAt(0) >= 'A' && letter.charAt(0) <= 'Z') {
                letters += letter;
            }
            readyForKeyInput = false;
        } else {
            while (StdDraw.hasNextKeyTyped()) {
                StdDraw.nextKeyTyped();
            }
            if (!StdDraw.hasNextKeyTyped()) {
                readyForKeyInput = true;
            }
        }
    }

    /**
     * Responds to input from the mouse, simulating the typing of keys on the
     * "keyboard" at the bottom of the game panel. Will call the method
     * processGuess when the user has entered a word to guess. This method is
     * complete.
     */
    public void respondToMouse() {
        if (readyForMouseInput && StdDraw.isMousePressed()) {
            for (int i = 0; i < Constants.KEYPLACEMENT.length; i++) {
                if (StdDraw.mouseX() > Constants.KEYPLACEMENT[i][0] - 22
                        && StdDraw.mouseX() < Constants.KEYPLACEMENT[i][0] + 22
                        && StdDraw.mouseY() > Constants.KEYPLACEMENT[i][1] - 29
                        && StdDraw.mouseY() < Constants.KEYPLACEMENT[i][1] + 29) {
                    if (i == 28) {
                        initAll("");
                        activeGame = true;
                    } else if (activeGame && i == 27 && letters.length() > 0) {
                        letters = letters.substring(0, letters.length() - 1);
                    } else if (activeGame && i == 19 && letters.length() == 5) {
                        processGuess();
                    } else if (activeGame && i != 19 && i != 27 && i != 28 && letters.length() < 5) {
                        String letter = Constants.KEYBOARD[i].toUpperCase();
                        letters += letter;
                    }
                }
            }
            readyForMouseInput = false;
        } else if (!StdDraw.isMousePressed()) {
            readyForMouseInput = true;
        }
    }
}
