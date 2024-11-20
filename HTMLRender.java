
import java.util.Scanner;

/**
 * HTMLRender This program renders HTML code into a JFrame window. It requires
 * your HTMLUtilities class and the SimpleHtmlRenderer and HtmlPrinter classes.
 *
 * The tags supported:
 * <html>, </html> - start/end of the HTML file
 * <body>, </body> - start/end of the HTML code
 * <p>
 * , </p> - Start/end of a paragraph. Causes a newline before and a blank line
 * after. Lines are restricted to 80 characters maximum.
 * <hr>	- Creates a horizontal rule on the following line.
 * <br>	- newline (break)
 * <b>, </b> - Start/end of bold font print
 * <i>, </i> - Start/end of italic font print
 * <q>, </q> - Start/end of quotations
 * <hX>, </hX> - Start/end of heading with size X = 1, 2, 3, 4, 5, 6
 * <pre>, </pre> - Preformatted text
 *
 * @author
 * @version
 */
public class HTMLRender {

    // the array holding all the tokens of the HTML file
    private String[] tokens;
    private final int TOKENS_SIZE = 100000;	// size of array
    private final int ASCII_OF_TAB = 9; //ascii value of tab (\t)
    // SimpleHtmlRenderer fields
    private SimpleHtmlRenderer render;
    private HtmlPrinter browser;

    // list of punctuation chars
    private final char[] punctuation = new char[]{'.', ',', ';', ':', '(', ')', '?', '!', '=', '&', '~', '+', '-'};

    // NONE = not nested in a block, COMMENT = inside a comment block
    // PREFORMAT = inside a pre-format block
    private enum TokenState {
        NONE, COMMENT, PREFORMAT
    };

    // NONE = normal format, ITALIC = italicized text
    // Bold = bold text PREFORMAT =  pre-format block
    private enum TextState {
        NONE, ITALIC, BOLD, PREFORMAT, H1, H2, H3, H4, H5, H6
    };
    // the current tokenizer state
    private TokenState state = TokenState.NONE;

    public HTMLRender() {
        // Initialize token array
        tokens = new String[TOKENS_SIZE];

        // Initialize Simple Browser
        render = new SimpleHtmlRenderer();
        browser = render.getHtmlPrinter();
    }

    public static void main(String[] args) {
        HTMLRender hf = new HTMLRender();
        if (args.length > 0) {
            hf.run(args[0]);
        }

    }

    public void run(String file) {
        TextState state = TextState.NONE;
        Scanner input = FileUtils.openToRead(file);
        String tokensArray[][];
        int tokenLineNum = 0;
        int tokensArrayLength = 0;
        int length = 0;
        int wrapLength = 80;
        while (input.hasNextLine()) {
            tokensArrayLength++;
            input.nextLine();
        }
        input.close();
        tokensArray = new String[tokensArrayLength][100];
        input = FileUtils.openToRead(file);
        while (input.hasNext()) {
            tokensArray[tokenLineNum] = tokenizeHTMLString(input.nextLine());
            tokenLineNum++;
        }
        input.close();
        tokenLineNum = 0;
        for (int i = 0; i < tokensArray.length; i++) {
            for (int j = 0; j < tokensArray[i].length; j++) {
                tokens[tokenLineNum] = tokensArray[i][j];
                tokenLineNum++;
            }
        }
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == null) {
            } else if (tokens[i].equalsIgnoreCase("<html>") || tokens[i].equalsIgnoreCase("</html>")) {
            } else if (tokens[i].equalsIgnoreCase("<body>") || tokens[i].equalsIgnoreCase("</body>")) {
            } else if (tokens[i].equalsIgnoreCase("<p>")) {
                browser.println();
				browser.println();
                length = 0;
            } else if (tokens[i].equalsIgnoreCase("</p>")) {
                browser.println();
                browser.println();
                length = 0;
            } else if (tokens[i].equalsIgnoreCase("<br>")) {
                browser.printBreak();
                length = 0;
            } else if (tokens[i].equalsIgnoreCase("<hr>")) {
                browser.printHorizontalRule();
                length = 0;
            } else if (tokens[i].equalsIgnoreCase("<b>")) {
                state = TextState.BOLD;
            } else if (tokens[i].equalsIgnoreCase("<i>")) {
                state = TextState.ITALIC;
            } else if (tokens[i].equalsIgnoreCase("<pre>")) {
                state = TextState.PREFORMAT;
            } else if (tokens[i].equalsIgnoreCase("</i>") || tokens[i].equalsIgnoreCase("</b>") || tokens[i].equalsIgnoreCase("</pre>")) {
                state = TextState.NONE;
				if (tokens[i].equalsIgnoreCase("</pre>")) length=0;
            } 
			else if (tokens[i].equalsIgnoreCase("<q>")) {
                browser.print(" " + '"');
                length++;
            } else if (tokens[i].equalsIgnoreCase("</q>")) {
                browser.print("" + '"');
                length++;
            } else if (tokens[i].toLowerCase().contains("<h")) {
                browser.println();
				browser.println();
                length = 0;
                if (tokens[i].equalsIgnoreCase("<h1>")) {
                    state = TextState.H1;
                    wrapLength = 40;
                } else if (tokens[i].equalsIgnoreCase("<h2>")) {
                    state = TextState.H2;
                    wrapLength = 50;
                } else if (tokens[i].equalsIgnoreCase("<h3>")) {
                    state = TextState.H3;
                	wrapLength = 60;
                } else if (tokens[i].equalsIgnoreCase("<h4>")) {
                    state = TextState.H4;
                    wrapLength = 80;
                } else if (tokens[i].equalsIgnoreCase("<h5>")) {
                    state = TextState.H5;
                    wrapLength = 100;
                } else if (tokens[i].equalsIgnoreCase("<h6>")) {
                    state = TextState.H6;
                    wrapLength = 120;
                }
            } else if (tokens[i].toLowerCase().contains("</h")) {
                length = 0;
                state = TextState.NONE;
                wrapLength = 80;
            } else {

                length += tokens[i].length();
                if (!isEndPunctuation(tokens[i])) {
                    length++;
                }
                if (length > wrapLength) {
                    browser.println();
                    length = tokens[i].length() + 1;
                }
                if (!isEndPunctuation(tokens[i]) && length > tokens[i].length() + 1 && i > 0 && !tokens[i - 1].equalsIgnoreCase("<q>")) {
                    if (state == TextState.H1 || state == TextState.H2 || state == TextState.H3 || state == TextState.H4 || state == TextState.H5 || state == TextState.H6) {
                        if (state == TextState.H1) {
                            browser.printHeading1(" ");
                        }
                        if (state == TextState.H2) {
                            browser.printHeading2(" ");
                        }
                        if (state == TextState.H3) {
                            browser.printHeading3(" ");
                        }
                        if (state == TextState.H4) {
                            browser.printHeading4(" ");
                        }
                        if (state == TextState.H5) {
                            browser.printHeading5(" ");
                        }
                        if (state == TextState.H6) {
                            browser.printHeading6(" ");
                        }
                    }else {
						browser.print(" "); 
                    }
                }
                if (state == TextState.BOLD) {
                    browser.printBold(tokens[i]);
                }
                if (state == TextState.ITALIC) {
                    browser.printItalic(tokens[i]);
                }
                if (state == TextState.PREFORMAT) {
                    browser.printPreformattedText(tokens[i]);
                    browser.println();
                }
                if (state == TextState.H1) {
                    browser.printHeading1(tokens[i]);
                }
                if (state == TextState.H2) {
                    browser.printHeading2(tokens[i]);
                }
                if (state == TextState.H3) {
                    browser.printHeading3(tokens[i]);
                }
                if (state == TextState.H4) {
                    browser.printHeading4(tokens[i]);
                }
                if (state == TextState.H5) {
                    browser.printHeading5(tokens[i]);
                }
                if (state == TextState.H6) {
                    browser.printHeading6(tokens[i]);
                }
                if (state == TextState.NONE) {
                    browser.print(tokens[i]);
                }

            }
        }
    }

    public String[] tokenizeHTMLString(String str) {
        String[] result = new String[10000];
        String[] sizedResult;
        int currentTokenNum = 0;
        String tokenTemp = "";
        for (int i = 0; i < str.length(); i++) {
            if (state == TokenState.COMMENT) {
                if (str.contains("-->")) {
                    i = str.indexOf('>', i);
                    state = TokenState.NONE;
                } else {
                    i = str.length();
                }
            } else if (state == TokenState.PREFORMAT) {
                if (str.equals("</pre>")) {
                    state = TokenState.NONE;
                }
                result[currentTokenNum] = str;
                currentTokenNum++;
                i = str.length();
            } else if (str.charAt(i) == '<') {
                if (tokenTemp.trim().length() > 0) {
                    result[currentTokenNum] = tokenTemp;
                    currentTokenNum++;
                    tokenTemp = "";
                }
                if (str.indexOf('>') == -1) {
                    tokenTemp = str.substring(i, i + 4);
                } else {
                    tokenTemp = str.substring(i, 1 + str.indexOf('>', i));
                }
                if (tokenTemp.equals("<pre>")) {
                    state = TokenState.PREFORMAT;
                } else if (tokenTemp.equals("<!--")) {
                    state = TokenState.COMMENT;

                } else if (tokenTemp.length() > 4 && tokenTemp.substring(0, 4).equals("<!--")) {
                    i = str.indexOf('>', i);
                } else {
                    result[currentTokenNum] = str.substring(i, 1 + str.indexOf('>', i));
                    currentTokenNum++;
                    i = str.indexOf('>', i);
                }
                tokenTemp = "";

            } else if (str.charAt(i) == ' ' || str.charAt(i) == ASCII_OF_TAB || isPunctuation(str.charAt(i))) {
                if (str.charAt(i) == '.' && i < str.length() - 1 && Character.isDigit(str.charAt(i + 1))) {
                    tokenTemp += str.charAt(i);
                } else if (str.charAt(i) == '-' && (i == 0 || Character.isLetterOrDigit(str.charAt(i - 1)) || (i < str.length() - 1 && Character.isLetterOrDigit(str.charAt(i + 1))))) {
                    tokenTemp += str.charAt(i);
                } else if (isPunctuation(str.charAt(i))) {
                    if (tokenTemp.trim().length() > 0) {
                        result[currentTokenNum] = tokenTemp;
                        currentTokenNum++;
                    }
                    tokenTemp = "";
                    result[currentTokenNum] = str.charAt(i) + "";
                    currentTokenNum++;
                    if (i < str.length() - 1 && str.charAt(i + 1) == ' ') {
                        i++;
                    }
                } else {
                    if (tokenTemp.trim().length() > 0) {
                        result[currentTokenNum] = tokenTemp;
                        currentTokenNum++;
                        tokenTemp = "";
                    }
                }

            } else {
                if (Character.isLetterOrDigit(str.charAt(i))) {
                    tokenTemp += str.charAt(i);
                }
            }
        }
        if (tokenTemp.length() > 0) {
            result[currentTokenNum] = tokenTemp;
            currentTokenNum++;
        }
        // return the correctly sized array
        sizedResult = new String[currentTokenNum];
        for (int i = 0; i < sizedResult.length; i++) {
            sizedResult[i] = result[i];
        }
        return sizedResult;
    }

    /**
     * Checks char to see if it matches the list of punctuation
     *
     * @param c char to check
     * @return If c is a punctuation mark
     */
    public boolean isPunctuation(char c) {
        for (int i = 0; i < punctuation.length; i++) {
            if (c == punctuation[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks char to see if it matches the list of punctuation
     *
     * @param c char to check
     * @return If c is a punctuation mark
     */
    public boolean isEndPunctuation(String str) {
        if (str.charAt(0) == '.' || str.charAt(0) == '?' || str.charAt(0) == '!' || str.charAt(0) == ','|| str.charAt(0) == ':') {
            return true;
        }
        else if (str.contains("<") && str.contains(">")) {
            return true;
        }
        return false;
    }

    public void run1() {
        // Sample renderings from HtmlPrinter class

        // Print plain text without line feed at end
        browser.print("First line");

        // Print line feed
        browser.println();

        // Print bold words and plain space without line feed at end
        browser.printBold("bold words");
        browser.print(" ");

        // Print italic words without line feed at end
        browser.printItalic("italic words");

        // Print horizontal rule across window (includes line feed before and after)
        browser.printHorizontalRule();

        // Print words, then line feed (printBreak)
        browser.print("A couple of words");
        browser.printBreak();
        browser.printBreak();

        // Print a double quote
        browser.print("\"");

        // Print Headings 1 through 6 (Largest to smallest)
        browser.printHeading1("Heading1");
        browser.printHeading2("Heading2");
        browser.printHeading3("Heading3");
        browser.printHeading4("Heading4");
        browser.printHeading5("Heading5");
        browser.printHeading6("Heading6");

        // Print pre-formatted text (optional)
        browser.printPreformattedText("Preformat Monospace\tfont");
        browser.printBreak();
        browser.print("The end");

    }

}
