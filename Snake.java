/**
 *	A SinglyLinkedList of Coordinate objects representing
 *	a snake on a two-dimensional grid.
 *
 *	@author	Yoosuf Khan
 *	@since	May 12, 2025
 */
public class Snake extends SinglyLinkedList<Coordinate> {
		
	/**	Constructor for making a Snake that is 5 grids high facing north.
	 *	Places the snake head at Coordinate location and the tail below.
	 *	Precondition: To place the Snake, the board must have at
	 *				least location.getRow() + 4 more rows.
	 */
	public Snake(Coordinate location) { 
		for(int i=0;i<5;i++){
			add(new Coordinate(location.getRow()+i,location.getCol()));
		}
	}
	/** Creates a linked list to represent the snake given a head coordinate
	 * 
	 * @param row row of head
	 * @param col column of head
	 */
	public Snake(int row, int col) { 
		Coordinate location=new Coordinate(row,col);
		for(int i=0;i<5;i++){
			add(new Coordinate(location.getRow()+i,location.getCol()));
		}
	}
	
	
}
