/**
 *	A coordinate on a grid. Integer Row Col values.
 *
 *	@author Mr Greenstein
 */
public class Coordinate implements Comparable<Coordinate>
{
	private int col, row;
		
	public Coordinate(int myRow, int myCol)
	{
		col = myCol;
		row = myRow;
	}
	
	/* Accessor methods */
	public int getCol() { return col; }
	public int getRow() { return row; }
	@Override
	public boolean equals(Object other)
	{
		return compareTo((Coordinate)other) == 0;
	}
	
	/**
	 *	Coordinate is greater when:
	 *	1. col is greater or
	 *	2. col is equal and row is greater
	 *	3. otherwise Coordinates are equal
	 *	@return		negative if less than, 0 if equal, positive if greater than
	 */
	@Override
	public int compareTo(Coordinate other) {
		if (! (other instanceof Coordinate))
			throw new IllegalArgumentException("compareTo not Coordinate object");
		if (col > ((Coordinate)other).col || col < ((Coordinate)other).col)
			return col - ((Coordinate)other).col;
		if (row > ((Coordinate)other).row || row < ((Coordinate)other).row)
			return row - ((Coordinate)other).row;
		return 0;
	}
	
	public String toString()
	{	return "[ " + row + ", " + col + "]";  }
	
}
