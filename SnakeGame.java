import java.io.PrintWriter;
import java.util.Scanner;
/**
 *	Snake Game - Runs a varient of the hit game "Snake" 
 *	Snake moves through board collecting targets to gain length
 * 	if snake hits walls or itself it dies and the game is over
 *	@author	Yoosuf Khan
 *	@since	May 12, 2025
 */
public class SnakeGame {
	
	private Snake snake;		// the snake in the game
	private SnakeBoard board;	// the game board
	private Coordinate target;	// the target for the snake
	private int score;			// the score of the game

	/*	Constructor	*/
	public SnakeGame() {
		snake=new Snake(5,5);
		board = new SnakeBoard(20,25);
		score=0;
		target=new Coordinate(10,10);
	 }
	
	/*	Main method	*/
	public static void main(String[] args) {
		SnakeGame sg = new SnakeGame();
		sg.run();
		
	}
	/* Runs the game of snake for the user to play
	 **/
	public void run(){
		printIntroduction();
		helpMenu();
		char input=' ';
		boolean isInvalidMove=false;
		Coordinate nextLoc=null;
		while(input!='q'&&!isGameOver()&&!isInvalidMove){
			board.printBoard(snake, target);
			System.out.println("Score: "+score+" (w - North, s - South, d - East, a - West, h - Help) ->");
			Coordinate head = new Coordinate(snake.get(0).getValue().getRow(),snake.get(0).getValue().getCol());
			input=Prompt.getChar("Where do you want to move?");
			if(input=='q'){
				if(Prompt.getChar("Are you sure? (y or n)")!='y') input=' ';
			}
			else if(input=='h') helpMenu();
			else if(input=='f') Save();
			else if(input=='r') Load();
			else if(input=='w'||input=='a'||input=='s'||input=='d'){
				if(input=='w') nextLoc=new Coordinate(head.getRow()-1,head.getCol());
				else if(input=='a') nextLoc=new Coordinate(head.getRow(),head.getCol()-1);
				else if(input=='s') nextLoc=new Coordinate(head.getRow()+1,head.getCol());
				else if(input=='d') nextLoc=new Coordinate(head.getRow(),head.getCol()+1);
				if(!board.isEmptySlot(nextLoc)||snake.contains(nextLoc)) isInvalidMove=true;
				else{
					snake.add(0, nextLoc);
					if(!nextLoc.equals(target)) snake.remove(snake.size()-1);
					else {
						makeTarget();
						score++;
					}
				}
			}
		}
		if(input!='q') System.out.println("You Lose.");
		System.out.println("Thanks for playing SnakeGame!!!");
	}
	/* creates a new target in an empty board location
	 **/
	public void makeTarget(){
		Coordinate c=new Coordinate((int)(board.getHeight()*Math.random()+1),
									(int)(board.getWidth()*Math.random()+1));
		while(board.getSlot(c.getRow(), c.getCol())!=' '||snake.contains(c)){
			c=new Coordinate((int)(board.getHeight()*Math.random()+1),
							(int)(board.getWidth()*Math.random()+1));
		}
		target=c;
	}
	/* Tells if a new move can not be made and the game is over
	 * 
	 * @return if game is over/ player can no longer move
	 **/
	public boolean isGameOver(){
		Coordinate head=snake.get(0).getValue();
		if(score>=board.getHeight()*board.getWidth()-10) {
			return true;
		}
		Coordinate north =new Coordinate(head.getRow()-1,head.getCol());
		Coordinate south =new Coordinate(head.getRow()+1,head.getCol());
		Coordinate east =new Coordinate(head.getRow(),head.getCol()+1);
		Coordinate west =new Coordinate(head.getRow(),head.getCol()-1);
		boolean blockedNorth=(!board.isEmptySlot(north)||snake.contains(north));
		boolean blockedSouth=(!board.isEmptySlot(south)||snake.contains(south));
		boolean blockedEast=(!board.isEmptySlot(east)||snake.contains(east));
		boolean blockedWest=(!board.isEmptySlot(west)||snake.contains(west));
		if(blockedNorth&&blockedSouth&&blockedEast&&blockedWest) return true;
		return false;
	}
	/* Saves the state of the game to a file called gameSave.txt
	 **/
	public void Save(){
		PrintWriter pw = FileUtils.openToWrite("gameSave.txt");
		pw.println("Score " + score);
		pw.println("Target");
		pw.println(target.getRow()+" "+target.getCol());
		pw.println("Snake "+snake.size());
		for(int i=0; i<snake.size();i++){
			pw.println(snake.get(i).getValue().getRow()+" "+snake.get(i).getValue().getCol());
		}
		pw.close();
		System.out.println("\nGame saved to gameSave.txt\n");
	}
	/* loads the state of the game from the file called gameSave.txt
	 **/
	public void Load(){
		Scanner scan = FileUtils.openToRead("gameSave.txt");
		int row,col,size;
		scan.next();
		score=scan.nextInt();
		scan.next();
		row=scan.nextInt();
		col=scan.nextInt();
		target=new Coordinate(row,col);
		scan.next();
		size=scan.nextInt();
		snake.clear();
		for(int i=0;i<size;i++){
			row=scan.nextInt();
			col=scan.nextInt();
			snake.add(new Coordinate(row,col));
		}
	}
	/**	Print the game introduction	*/
	public void printIntroduction() {
		System.out.println("  _________              __            ________");
		System.out.println(" /   _____/ ____ _____  |  | __ ____  /  _____/_____    _____   ____");
		System.out.println(" \\_____  \\ /    \\\\__  \\ |  |/ // __ \\/   \\  ___\\__  \\  /     \\_/ __ \\");
		System.out.println(" /        \\   |  \\/ __ \\|    <\\  ___/\\    \\_\\  \\/ __ \\|  Y Y  \\  ___/");
		System.out.println("/_______  /___|  (____  /__|_ \\\\___  >\\______  (____  /__|_|  /\\___  >");
		System.out.println("        \\/     \\/     \\/     \\/    \\/        \\/     \\/      \\/     \\/");
		System.out.println("\nWelcome to SnakeGame!");
		System.out.println("\nA snake @****** moves around a board " +
							"eating targets \"+\".");
		System.out.println("Each time the snake eats the target it grows " +
							"another * longer.");
		System.out.println("The objective is to grow the longest it can " +
							"without moving into");
		System.out.println("itself or the wall.");
		System.out.println("\n");
	}
	
	/**	Print help menu	*/
	public void helpMenu() {
		System.out.println("\nCommands:\n" +
							"  w - move north\n" +
							"  s - move south\n" +
							"  d - move east\n" +
							"  a - move west\n" +
							"  h - help\n" +
							"  f - save game to file\n" +
							"  r - restore game from file\n" +
							"  q - quit");
		Prompt.getString("Press enter to continue");
	}
	
}
