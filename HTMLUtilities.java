

/**
 * Utilities for handling HTML
 *
 * @author
 * @since
 */
public class HTMLUtilities {

    char[] punctuation = new char[]{'.', ',', ';', ':', '(', ')', '?', '!', '=', '&', '~', '+', '-'};

    /**
     * Break the HTML string into tokens. The array returned is exactly the size
     * of the number of tokens in the HTML string. Example:	HTML string =
     * "Goodnight moon goodnight stars" returns { "Goodnight", "moon",
     * "goodnight", "stars" }
     *
     * @param str	the HTML string
     * @return	the String array of tokens
     */
    public String[] tokenizeHTMLString(String str) {
        // make the size of the array large to start
        String[] result = new String[10000];
        String[] sizedResult;
        int currentTokenNum = 0;
        String tokenTemp = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '<') {
				if(tokenTemp.trim().length()>0){
						result[currentTokenNum] = tokenTemp;
						currentTokenNum++;
						tokenTemp = "";
					}
                result[currentTokenNum] = str.substring(i, 1 + str.indexOf('>', i));
                currentTokenNum++;
                i = str.indexOf('>', i) ;
            }
			
            else if (str.charAt(i) == ' ' || isPunctuation(str.charAt(i))) {
				if(str.charAt(i)=='.'&&i<str.length()-1&&Character.isDigit(str.charAt(i+1))){
					 tokenTemp += str.charAt(i);
				}
				else if(str.charAt(i)=='-'&&(Character.isLetterOrDigit(str.charAt(i-1)) ||( i<str.length()-1 && Character.isLetterOrDigit(str.charAt(i+1))))){
					 tokenTemp += str.charAt(i);
				}
				else if (isPunctuation(str.charAt(i))) {
					if(tokenTemp.trim().length()>0){
					result[currentTokenNum] = tokenTemp;
                	currentTokenNum++;
					}
               		tokenTemp = "";
                    result[currentTokenNum] = str.charAt(i) + "";
                    currentTokenNum++;
					if( i<str.length()-1&&str.charAt(i+1)==' ')i++;
                }
				else{
					if(tokenTemp.trim().length()>0){
						result[currentTokenNum] = tokenTemp;
						currentTokenNum++;
						tokenTemp = "";
					}
				}
                
            } 
			else {
				if(Character.isLetterOrDigit(str.charAt(i))){
                tokenTemp += str.charAt(i);
				}
            }
        }
		if(tokenTemp.length()>0){
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
     * Print the tokens in the array to the screen Precondition: All elements in
     * the array are valid String objects. (no nulls)
     *
     * @param tokens	an array of String tokens
     */
    public void printTokens(String[] tokens) {
        if (tokens == null) {
            return;
        }
        for (int a = 0; a < tokens.length; a++) {
            if (a % 5 == 0) {
                System.out.print("\n  ");
            }
            System.out.print("[token " + a + "]: " + tokens[a] + " ");
        }
        System.out.println();
    }

    public boolean isPunctuation(char c) {
        for (int i = 0; i < punctuation.length; i++) {
            if (c == punctuation[i]) {
				return true;
			}
        }
		return false;
    }
}
