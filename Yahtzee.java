/**
 *	Runs a game of Yahtzee
 *	
 *	@author	Yoosuf Khan
 *	@since	October 23, 2024
 */


public class Yahtzee {

    YahtzeePlayer player1 = new YahtzeePlayer();
    YahtzeePlayer player2 = new YahtzeePlayer();

	/**
     * runs Yahtzee object
     */
    public static void main(String[] args) {
        Yahtzee yz = new Yahtzee();
        yz.runGame();
    }

	/**
     * runs Yahtzee game
     */
    public void runGame() {
        DiceGroup dice = new DiceGroup();

        int player1Sum = 0, player2Sum = 0, choice = 0;
        String hold = "";
        boolean isValidChoice = false;
        printHeader();
        player1.setName(Prompt.getString("Player 1, please enter your first name"));
        player2.setName(Prompt.getString("\nPlayer 2, please enter your first name"));
		while(player1Sum==player2Sum){
        Prompt.getString("\nLet's see who will go first. " + player1.getName()
                + ", please hit enter to roll the dice");
        dice.rollDice();
        dice.printDice();
        player1Sum = dice.getTotal();
        Prompt.getString(player2.getName() + ", it's your turn. Please hit enter to roll the dice");
        dice.rollDice();
        dice.printDice();
        player2Sum = dice.getTotal();
        System.out.println(player1.getName() + ", you rolled a sum of " + player1Sum
                + ", and " + player2.getName() + ", you rolled a sum of " + player2Sum + ". ");
		if(player1Sum==player2Sum)
			 System.out.println("Since you tied you will reroll.");
		}
        if (player1Sum > player2Sum) {
            System.out.println(player1.getName() + ", since your sum was higher, you'll roll first. ");
            for (int i = 0; i < 13; i++) {
                runTurn(player1, true, i + 1);
                runTurn(player2, false, i + 1);
            }
        } else {
            System.out.println(player2.getName() + ", since your sum was higher, you'll roll first. ");
            for (int i = 0; i < 13; i++) {
                runTurn(player2, true, i + 1);
                runTurn(player1, false, i + 1);
            }
        }
		player1Sum=player2Sum=0;
		for(int i=1;i<=13;i++){
			player1Sum+=player1.getScoreCard().getScore(i);
			player2Sum+=player2.getScoreCard().getScore(i);
		}
		player1.getScoreCard().printCardHeader();
        player1.getScoreCard().printPlayerScore(player1);
        player2.getScoreCard().printPlayerScore(player2);

		System.out.printf("%n%-10s score total = %d%n",player1.getName(),player1Sum);
		System.out.printf("%-10s score total = %d%n",player2.getName(),player2Sum);
		if(player1Sum>player2Sum)
			System.out.println("\nCongratulations "+player1.getName()+". YOU WON!!!\n");
		else if (player1Sum<player2Sum)
			System.out.println("\nCongratulations "+player2.getName()+". YOU WON!!!\n");
		else
			System.out.println(" YOU TIE!!!\n");
	}

	/**
     * runs one turn of a player
	 * @param  player the player the turn is for
	 * @param  isFirst if the player is playing first
	 * @param  round what round the turn is in
     */
    public void runTurn(YahtzeePlayer player, boolean isFirst, int round) {
        String hold = "";
        boolean isValidChoice = false;
        int choice;
        DiceGroup dice = new DiceGroup();
        player1.getScoreCard().printCardHeader();
        player1.getScoreCard().printPlayerScore(player1);
        player2.getScoreCard().printPlayerScore(player2);
        if (isFirst) {
            System.out.println("Round " + (round) + " of 13 rounds.");
        }
        Prompt.getString("\n"+player.getName() + ", it's your turn to play. Please hit enter to roll the dice");
        dice.rollDice();
        dice.printDice();
        for (int j = 0; j < 2 && !hold.equals("-1"); j++) {
            hold = Prompt.getString("\nWhich di(c)e would you like to keep? Enter the values you'd like to 'hold' without\n"
                    + "spaces. For examples, if you'd like to 'hold' die 1, 2, and 5, enter 125"+
					"\n(enter -1 if you'd like to end the turn)");
            if (!hold.equals("-1")) {
                dice.rollDice(hold);
                dice.printDice();
            }
        }
        player1.getScoreCard().printCardHeader();
        player1.getScoreCard().printPlayerScore(player1);
        player2.getScoreCard().printPlayerScore(player2);
        System.out.printf("  NAME\t\t  1    2    3    4    5    6   7    8    9   "
                + "10   11   12   13  \n\n");
        isValidChoice = false;
        while (!isValidChoice) {
            choice = Prompt.getInt(player.getName() + ", now you need to make a choice. "
                    + "Pick a valid integer from the list above ", 1, 13);
            isValidChoice = player.getScoreCard().changeScore(choice, dice);
        }
    }

	/**
     * prints Yahtzee header
     */
    public void printHeader() {
        System.out.println("\n");
        System.out.println("+------------------------------------------------------------------------------------+");
        System.out.println("| WELCOME TO MONTA VISTA YAHTZEE!                                                    |");
        System.out.println("|                                                                                    |");
        System.out.println("| There are 13 rounds in a game of Yahtzee. In each turn, a player can roll his/her  |");
        System.out.println("| dice up to 3 times in order to get the desired combination. On the first roll, the |");
        System.out.println("| player rolls all five of the dice at once. On the second and third rolls, the      |");
        System.out.println("| player can roll any number of dice he/she wants to, including none or all of them, |");
        System.out.println("| trying to get a good combination.                                                  |");
        System.out.println("| The player can choose whether he/she wants to roll once, twice or three times in   |");
        System.out.println("| each turn. After the three rolls in a turn, the player must put his/her score down |");
        System.out.println("| on the scorecard, under any one of the thirteen categories. The score that the     |");
        System.out.println("| player finally gets for that turn depends on the category/box that he/she chooses  |");
        System.out.println("| and the combination that he/she got by rolling the dice. But once a box is chosen  |");
        System.out.println("| on the score card, it can't be chosen again.                                       |");
        System.out.println("|                                                                                    |");
        System.out.println("| LET'S PLAY SOME YAHTZEE!                                                           |");
        System.out.println("+------------------------------------------------------------------------------------+");
        System.out.println("\n\n");
    }
}
