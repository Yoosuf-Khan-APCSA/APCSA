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
			board[0][j]='|';
			board[height-1][j]='|';
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
				if(snake.contains(new Coordinate(i,j)))
					System.out.print("*");
				else if(target.equals(new Coordinate(i,j)))
					System.out.print("+");
				else
					System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	
	/* Helper methods go here	*/
	
	/*	Accessor methods	*/

	
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
