import java.util.Scanner;

/**
 *	The driver program for creating and manipulating
 *	a binary tree of state information.
 *
 *	@author	
 *	@since	
 */
public class StateTree
{
	// Fields
	private BinaryTree<State> bTree;
	private final String IN_FIlE = "states2.txt";	// input file
	
	public StateTree() { }
	
	public static void main ( String [] args )
	{
		StateTree treeOrder = new StateTree();
		treeOrder.mainMenu();
	}
 
	public void mainMenu ()
	{
		String choice;

		do
		{
			System.out.println("Binary Tree algorithm menu\n");
			System.out.println("(1) Read Data from a file");
			System.out.println("(2) Print the list");
			System.out.println("(3) Search the list");
			System.out.println("(4) Delete node");
			System.out.println("(5) Count nodes");
			System.out.println("(6) Clear the list");
			System.out.println("(7) Print the level");
			System.out.println("(8) Print depth of tree");
			System.out.println("(Q) Quit\n");
			choice = Prompt.getString("Choice");
			System.out.println();

			if ('1' <= choice.charAt(0) && choice.charAt(0) <= '8')
			{
				switch (choice.charAt(0))
				{
					case '1' :	
						loadData();		
						break;
					case '2' :
						System.out.println();
						System.out.println("The tree printed inorder\n");
						bTree.printInorder();
						System.out.println();
						break;
					case '3' :
						find();
						break;
					case '4' :
						delete();
						break;
					case '5' :
						System.out.println("Number of nodes = " + size(bTree.getRoot()));
						System.out.println();
						break;
					case '6' :
						clear();
						break;
					case '7' :
						printLevel();
						break;
					case '8' :
						if (depth(bTree.getRoot(), -1) > -1)
							System.out.println("Depth of tree = " + depth(bTree.getRoot(), -1));
						else
							System.out.println("Tree empty");
						System.out.println();
						break;
				}
			}
		}
		while (choice.charAt(0) != 'Q' && choice.charAt(0) != 'q');
	}
	
	/**	Load the data into the binary tree */
	public void loadData() { 
		Scanner scan = FileUtils.openToRead(IN_FILE);
		while(scan.hasNext()){
			String name1=scan.getNext();
			String abrv1=scan.getNext();
			int pop=scan.getNext();
			int area1=scan.getNextInt();
			int reps1=scan.getNextInt();
			String cap=scan.getNext();
			int mon=scan.getNextInt();
			int day1=scan.getNextInt();
			int yr=scan.getNextInt();
			bTree.add(new State( name1,  abrv1,  pop,  area1,  reps1,  cap,  mon,  day1,  yr));
		}
		System.out.println("Database "+IN_FIlE+" is loaded!!");
	}
	
	/**	Find the node in the tree */
	public void find() {
		String response= Prompt.getString("Enter state name to search (Q to quit)");
		if(response.equalsIgnoreCase("Q")) return;
		if(search(new State(response)!=null){
				System.out.println(search(new State(response));
		}
		else System.out.println(response+" not found");
			
	}
	
	/** Delete a node */
	public void delete() { 
		String response= Prompt.getString("Enter state name to delete(Q to quit)");
		if(response.equalsIgnoreCase("Q")) return;
		else {
			if(search(new State(response)!=null){
				bTree.remove(new State(response));
			}
			else System.out.println(response+" has been deleted!!");
		}
	}
	
	/**	Returns the number of nodes in the subtree - recursive
	 *	@param node		the root of the subtree
	 *	@return			the number of nodes in the subtree
	 */
	public int size(TreeNode<State> node) { 
		return bTree.createOrderedList.getSize(); 
		}
	
	/**	Clear out the binary tree */
	public void clear() { }
	
	/**	Print the level requested */
	public void printLevel() { 
		int level= Prompt.getInt("Enter state name to search (Q to quit)");
		bTree.printGivenLevel(bTree.getRoot,level);
		System.out.println();
		}
	
	/**	Returns the depth of the subtree - recursive
	 *	@param node		the root of the subtree
	 *	@return			the depth of the subtree
	 */
	public int depth(TreeNode<State> node, int depth) { return 0; }
	
}
