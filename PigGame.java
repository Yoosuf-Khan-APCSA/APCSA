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
	public void playGame (){
		Dice dice = new Dice();
		int playerAmt=0, compAmt=0, turnScore=0;
		char choice='b';
		dice.roll();
		printIntroduction();
		while (compAmt<=100&&playerAmt<=100){
			System.out.println("**** USER Turn ***");
				System.out.println("\nYour turn score: " + turnScore);
				System.out.println("Your total score: " + playerAmt);
			
			while(turnScore==0 ||(dice.getValue()!=1&&choice!='h')){
				System.out.println();
				choice=Prompt.getChar("(r)oll or (h)old");
				if(choice=='r'){
					dice.roll();
					System.out.println("\nYour roll: ");
					dice.printDice();
					turnScore+=dice.getValue();
					System.out.println("\nYour turn score: " + turnScore);
					System.out.println("Your total score: " + playerAmt);
					System.out.println();
				}
				else{
					playerAmt+= turnScore;
					System.out.println("\nYour total score: " + playerAmt);
				}
			}
			turnScore=0;
			if(playerAmt<100){
				System.out.println("\n**** COMPUTER'S Turn ***");
				System.out.println("\nComputer's turn score: " + turnScore);
				System.out.println("Computer's total score: " + compAmt);
			while((turnScore<20&&dice.getValue()!=1)||turnScore==0){
				Prompt.getString("\nPress enter for computer's turn\n");
				
				if(turnScore<20&&compAmt<100){
					dice.roll();
					System.out.println("Computer will ROLL");
					dice.printDice();
					turnScore+=dice.getValue();
					System.out.println("\nComputer's turn score: " + turnScore);
					System.out.println("Computer's total score: " + compAmt);
					System.out.println();
				}
				if(turnScore>=20&&compAmt<=100){
					compAmt+= turnScore;
					System.out.println("Computer will HOLD");
					System.out.println("Computer's total score: " + compAmt);
				}
				else if ((compAmt+turnScore)>=100){
					compAmt+= turnScore;
					System.out.println("Computer will HOLD");
					System.out.println("Computer's total score: " + compAmt);
				}
				// if(turnScore<20){
				// 	compAmt+= turnScore;
				// 	System.out.println("Computer's total score: " + CompAmt);
				// }
			}
			}
			turnScore=0;
			
		}
		if(playerAmt>compAmt){
		System.out.println("Congratulations!!! YOU WON!!!!");
		}
		else{
			System.out.println("Too bad. COMPUTER WON.");
		}
		System.out.println("\nThanks for playing the Pig Game!!!");
	}
	public void runStatistics(){
		Dice dice = new Dice();
		int turns;
		int [] scorePercent =new  int [26];
		int turnScore =0;
		dice.roll();
		for(int i =0; i<scorePercent.length;i++){
			scorePercent[i]=0;
		}
		System.out.println("\nRun statistical analysis - \"Hold at 20\"\n");
		turns = Prompt.getInt("Number of turns (1000 - 10000000)",1000,10000000);
		for(int i =0; i<turns; i++){
			while((turnScore<20&&dice.getValue()!=1)||turnScore==0){
				if(turnScore<20){
					dice.roll();
					turnScore+=dice.getValue();
				}
				
				
			}
			if(turnScore<20) 
				turnScore=0;
			//System.out.println(turnScore);
			scorePercent[turnScore]++;
			turnScore=0;
		}
		System.out.printf("%-10s%s%n","Score","Estimated Probability");
		System.out.printf("%-10d%.6f%n",0,((double)scorePercent[0]/turns));
		System.out.printf("%-10d%.6f%n",20,((double)scorePercent[20]/turns));
		System.out.printf("%-10d%.6f%n",21,((double)scorePercent[21]/turns));
		System.out.printf("%-10d%.6f%n",22,((double)scorePercent[22]/turns));
		System.out.printf("%-10d%.6f%n",23,((double)scorePercent[23]/turns));
		System.out.printf("%-10d%.6f%n",24,((double)scorePercent[24]/turns));
		System.out.printf("%-10d%.6f%n",25,((double)scorePercent[25]/turns));
	}
	public static void main (String args[]){
		PigGame pg=new PigGame();
		pg.printIntroduction();
		char choice = Prompt.getChar("Play game or Statistics (p or s)");
		if(choice=='p')
			pg.playGame();
		else
			pg.runStatistics();
	}
	
}
