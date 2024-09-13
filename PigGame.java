/**
 *	The game of Pig.
 *	(Description here)
 *
 *	@author	Yoosuf Khan
 *	@since	9/13/24
 */
public class PigGame {
	
	/**	Print the introduction to the game */
	public void printIntroduction() {
		System.out.println("\n");
		System.out.println("______ _         _____");
		System.out.println("| ___ (_)       |  __ \\");
		System.out.println("| |_/ /_  __ _  | |  \\/ __ _ _ __ ___   ___");
		System.out.println("|  __/| |/ _` | | | __ / _` | '_ ` _ \\ / _ \\");
		System.out.println("| |   | | (_| | | |_\\ \\ (_| | | | | | |  __/");
		System.out.println("\\_|   |_|\\__, |  \\____/\\__,_|_| |_| |_|\\___|");
		System.out.println("          __/ |");
		System.out.println("         |___/");
		System.out.println("\nThe Pig Game is human vs computer. Each takes a"
							+ " turn rolling a die and the first to score");
		System.out.println("100 points wins. A player can either ROLL or "
							+ "HOLD. A turn works this way:");
		System.out.println("\n\tROLL:\t2 through 6: add points to turn total, "
							+ "player's turn continues");
		System.out.println("\t\t1: player loses turn");
		System.out.println("\tHOLD:\tturn total is added to player's score, "
							+ "turn goes to other player");
		System.out.println("\n");
	}
	public void run (){
		Dice dice = new Dice();
		int playerAmt=0, compAmt=0, turnScore=0;
		char choice='b';
		dice.roll();
		printIntroduction();
		while (compAmt<=100){
			
			
			while(turnScore==0 ||(dice.getValue()!=1&&choice!='h')){
				System.out.println("**** USER Turn ***");
				System.out.println("\nYour turn score: " + turnScore);
				System.out.println("Your total score: " + playerAmt);
				System.out.println();
				choice=Prompt.getChar("(r)oll or (h)old -> ");
				if(choice=='r'){
					dice.roll();
					System.out.println("Your roll: ");
					dice.printDice();
					turnScore+=dice.getValue();
				}
				else{
					playerAmt+= turnScore;
					System.out.println("Your total score: " + playerAmt);
				}
			}
			turnScore=0;
			while((turnScore<20||dice.getValue()!=1)||turnScore==0){
				Prompt.getString("Press enter for computer's turn ->");
				System.out.println("**** COMPUTER'S Turn ***");
				System.out.println("\nComputer's turn score: " + turnScore);
				System.out.println("Computer's total score: " + playerAmt);
				System.out.println();
				if(turnScore<20&&compAmt<100){
					dice.roll();
					System.out.println("Computer will roll: ");
					dice.printDice();
					turnScore+=dice.getValue();
				}
				else{
					compAmt+= turnScore;
					System.out.println("Your total score: " + playerAmt);
				}
				
			}
			turnScore=0;
			
		}
	}
	public static void main (String args[]){
		PigGame pg=new PigGame();
		pg.run();
	}
	
}
