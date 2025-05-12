/**
 *	<Describe the SnakeBoard here>
 *
 *	@author	
 *	@since	
 */
public class SnakeBoard {
	
	/*	fields	*/
	private char[][] board;			// The 2D array to hold the board
	
	/*	Constructor	*/
	public SnakeBoard(int height, int width) {
		height+=2;
		width+=2;
		board=new char [height][width];
		for(int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				board[i][j]=' ';
			}
		}
		for(int i=0;i<width;i++){
			board[0][i]='-';
			board[height-1][i]='-';
		}
		for(int j=0;j<height;j++){
			board[j][0]='|';
			board[j][width-1]='|';
		}
		board[0][0]='+';
		board[0][width-1]='+';
		board[height-1][0]='+';
		board[height-1][width-1]='+';
	}
	
	/**
	 *	Print the board to the screen.
	 */
	public void printBoard(Snake snake, Coordinate target) {
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board[0].length;j++){
				if(snake.get(0).getValue().equals(new Coordinate(i,j)))
					System.out.print("@");
				else if(snake.contains(new Coordinate(i,j)))
					System.out.print("*");
				else if(target.equals(new Coordinate(i,j)))
					System.out.print("+");
				else
					System.out.print(board[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	/* Helper methods go here	*/
	public boolean isEmptySlot(Coordinate c){
		if(c.getRow()<=0||c.getRow()>=board.length-1||c.getCol()<=0||c.getCol()>=board[0].length-1)
			return false;
		if(board[c.getRow()][c.getCol()]==' ')
			return true;
		else return false;
	} 
	/*	Accessor methods	*/
	public char[][] getBoard(){
		return board;
	}
	public int getHeight(){
		return board.length-2;
	}
	public int getWidth(){
		return board[0].length-2;
	}
	public char getSlot(int row, int col){
		return board[row][col];
	}
	public char getSlot(Coordinate c){
		return board[c.getRow()][c.getCol()];
	}
	/********************************************************/
	/********************* For Testing **********************/
	/********************************************************/
	
	public static void main(String[] args) {
		// Create the board
		int height = 10, width = 15;
		SnakeBoard sb = new SnakeBoard(height, width);
		// Place the snake
		Snake snake = new Snake(3, 3);
		// Place the target
		Coordinate target = new Coordinate(1, 7);
		// Print the board
		sb.printBoard(snake, target);
	}
}
