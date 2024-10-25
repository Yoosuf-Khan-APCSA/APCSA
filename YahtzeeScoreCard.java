
public class YahtzeeScoreCard {

    int scores[]; // scores for score cards 1-13
    private final int NUM_DICE = 5;	// number of dice
    private final int NUM_SCORE_HOLES = 13;	// number of score pegs
    int diceValues[]; //array with dice values that were rolled

	/**
     * initialize scores and dice values
     */
    public YahtzeeScoreCard() {
        scores = new int[NUM_SCORE_HOLES];
        diceValues = new int[6];
        for (int i = 0; i < scores.length; i++) {
            scores[i] = -1;
        }
    }

    /**
     * Print the scorecard header
     */
    public void printCardHeader() {
        System.out.println("\n");
        System.out.printf("\t\t\t\t\t       3of  4of  Fll Smll Lrg\n");
        System.out.printf("  NAME\t\t  1    2    3    4    5    6   Knd  Knd  Hse "
                + "Strt Strt Chnc Ytz!\n");
        System.out.printf("+----------------------------------------------------"
                + "---------------------------+\n");
    }

    /**
     * Prints the player's score
     */
    public void printPlayerScore(YahtzeePlayer player) {
        System.out.printf("| %-12s |", player.getName());
        for (int i = 1; i < 14; i++) {
            if (getScore(i) > -1) {
                System.out.printf(" %2d |", getScore(i)); 
            }else {
                System.out.printf("    |");
            }
        }
        System.out.println();
        System.out.printf("+----------------------------------------------------"
                + "---------------------------+\n");
    }

    /**
     * Change the scorecard based on the category choice 1-13.
     *
     * @param choice The choice of the player 1 to 13
     * @param dg The DiceGroup to score
     * @return true if change succeeded. Returns false if choice already taken.
     */
    public boolean changeScore(int choice, DiceGroup dg) {
        if (getScore(choice) > -1) {
            return false; 
        }else {
            switch (choice) {
                case 7:
                    threeOfAKind(dg);
                    break;
                case 8:
                    fourOfAKind(dg);
                    break;
                case 9:
                    fullHouse(dg);
                    break;
                case 10:
                    smallStraight(dg);
                    break;
                case 11:
                    largeStraight(dg);
                    break;
                case 12:
                    chance(dg);
                    break;
                case 13:
                    yahtzeeScore(dg);
                    break;
                default:
                    numberScore(choice, dg);
                    break;
            }
        }
        return true;
    }

    /**
     * Change the scorecard for a number score 1 to 6
     *
     * @param choice The choice of the player 1 to 6
     * @param dg The DiceGroup to score
     */
    public void numberScore(int choice, DiceGroup dg) {
        initializeDiceValues(dg);
        scores[choice - 1] = diceValues[choice - 1] * choice;
    }

    /**
     * Updates the scorecard for Three Of A Kind choice.
     *
     * @param dg	The DiceGroup to score
     */
    public void threeOfAKind(DiceGroup dg) {
        initializeDiceValues(dg);
        boolean has3Kind = false;
        int winningDiceFace = 0;
        for (int i = 0; i < diceValues.length; i++) {
            if (diceValues[i] >= 3) {
                has3Kind = true;
                winningDiceFace = i + 1;
            }
        }
        if (has3Kind) {
            scores[6] = 3 * winningDiceFace; 
        }else {
            scores[6] = 0;
        }
    }
	/**
     * Updates the scorecard for Four Of A Kind choice.
     *
     * @param dg	The DiceGroup to score
     */
    public void fourOfAKind(DiceGroup dg) {
        initializeDiceValues(dg);
        boolean has4Kind = false;
        int winningDiceFace = 0;
        for (int i = 0; i < diceValues.length; i++) {
            if (diceValues[i] >= 4) {
                has4Kind = true;
                winningDiceFace = i + 1;
            }
        }
        if (has4Kind) {
            scores[7] = 4 * winningDiceFace; 
        }else {
            scores[7] = 0;
        }
    }

	/**
     * Updates the scorecard for Full House choice.
     *
     * @param dg	The DiceGroup to score
     */
    public void fullHouse(DiceGroup dg) {
        initializeDiceValues(dg);
        boolean hasPair = false, hasTriple = false;
        int winningDiceFace = 0;
        for (int i = 0; i < diceValues.length; i++) {
            if (diceValues[i] == 2) {
                hasPair = true;
            }
            if (diceValues[i] == 3) {
                hasTriple = true;
            }
        }
        if (hasPair && hasTriple) {
            scores[8] = 25; 
        }else {
            scores[8] = 0;
        }

    }

	/**
     * Updates the scorecard for Small Straight choice.
     *
     * @param dg	The DiceGroup to score
     */
    public void smallStraight(DiceGroup dg) {
        initializeDiceValues(dg);
        if (diceValues[0] > 0 && diceValues[1] > 0 && diceValues[2] > 0 && diceValues[3] > 0) {
            scores[9] = 30; 
        }else if (diceValues[1] > 0 && diceValues[2] > 0 && diceValues[3] > 0 && diceValues[4] > 0) {
            scores[9] = 30; 
        }else if (diceValues[2] > 0 && diceValues[3] > 0 && diceValues[4] > 0 && diceValues[5] > 0) {
            scores[9] = 30; 
        }else {
            scores[9] = 0;
        }
    }
	/**
     * Updates the scorecard for Large Straight choice.
     *
     * @param dg	The DiceGroup to score
     */
    public void largeStraight(DiceGroup dg) {
        initializeDiceValues(dg);
        if (diceValues[0] > 0 && diceValues[1] > 0 && diceValues[2] > 0 && diceValues[3] > 0 && diceValues[4] > 0) {
            scores[10] = 40; 
        }else if (diceValues[1] > 0 && diceValues[2] > 0 && diceValues[3] > 0 && diceValues[4] > 0 && diceValues[5] > 0) {
            scores[10] = 40; 
        }else {
            scores[10] = 0;
        }
    }
	/**
     * Updates the scorecard for Chance choice.
     *
     * @param dg	The DiceGroup to score
     */
    public void chance(DiceGroup dg) {
        scores[11] = dg.getTotal();
    }
	/**
     * Updates the scorecard for Yahtzee choice.
     *
     * @param dg	The DiceGroup to score
     */
    public void yahtzeeScore(DiceGroup dg) {
        initializeDiceValues(dg);
        boolean hasYahtzee = false;
        for (int i = 0; i < diceValues.length; i++) {
            if (diceValues[i] == 5) {
                hasYahtzee = true;
            }
        }
        if (hasYahtzee) {
            scores[12] = 50; 
        }else {
            scores[12] = 0;
        }
    }
	/**
     * returns the score of the category selected
	 * 
     * @param pointCategory	The catagory of the score requested
	 * @return score of said category
     */
    public int getScore(int pointCategory) {
        return scores[pointCategory - 1];
    }
	/**
     * Initializes the values of diceValue to match the number of occurences of each face in dg
     *
     * @param dg	The DiceGroup to score
     */
    public void initializeDiceValues(DiceGroup dg) {
        for (int i = 0; i < diceValues.length; i++) {
            diceValues[i] = 0;
        }
        System.out.println();
        for (int i = 0; i < NUM_DICE; i++) {
            diceValues[dg.getDice(i) - 1]++;;
        }
    }
}
