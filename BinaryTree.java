/**
 *	Binary Tree of Comparable values.
 *	The tree only has unique values. It does not add duplicate values.
 *	
 *	@author	
 *	@since	
 */
import java.util.ArrayList; 
import org.w3c.dom.Node;
public class BinaryTree<E extends Comparable<E>> {

	private TreeNode<E> root;		// the root of the tree
	
	private final int PRINT_SPACES = 3;	// print spaces between tree levels
										// used by printTree()
	
	/**	constructor for BinaryTree */
	public BinaryTree() { }
	
	/**	Field accessors and modifiers */
	
	/**	Add a node to the tree
	 *	@param value		the value to put into the tree
	 */
	public void add(E value) {
		addIterative(value);
		//addRecurse(value, root);
	}
	/**	Add a node to the tree
	 *	@param value		the value to put into the tree
	 */
	public void addIterative(E value) {
		if (root==null){
			root= new TreeNode(value);
			return;
		}
		TreeNode<E> traverse=root;
		boolean isDone=false;
		while (!isDone){
			if(value.compareTo(traverse.getValue())<0){
				if(traverse.getLeft()==null){
					traverse.setLeft(new TreeNode(value));
					isDone=true;
				}
				else
					traverse=traverse.getLeft();
			}
			else{
				if(traverse.getRight()==null){
					traverse.setRight(new TreeNode(value));
					isDone=true;
				}
				else
					traverse=traverse.getRight();
			}
		} 
	}
		/**	Add a node to the tree
	 *	@param value		the value to put into the tree
	 */
	public E search(E value) {
		if (root==null){
			return null;
		}
		TreeNode<E> traverse=root;
		
		boolean isDone=false;
		while (!isDone){
			if(value.compareTo(traverse.getValue())<0){
				if(traverse.getLeft()==null){
					isDone=true;
					return null;
				}
				else
					traverse=traverse.getLeft();
			}
			else if(value.compareTo(traverse.getValue())>0){
				if(traverse.getRight()==null){
					isDone=true;
					return null;
				}
				else
					traverse=traverse.getRight();
			}
			else{
				isDone=true;
				 return traverse.getValue();
			 }
			
		}
		return null; 
	}
	/**	Add a node to the tree
	 *	@param value		the value to put into the tree
	 */
	public void addRecurse(E value, TreeNode<E> traverse) {
		if (root==null){
			root= new TreeNode(value);
			return;
		}
		else if(value.compareTo(traverse.getValue())<0){
				if(traverse.getLeft()==null){
					traverse.setLeft(new TreeNode(value));
				}
				else
					addRecurse(value,traverse.getLeft());
			}
		else{
				if(traverse.getRight()==null){
					traverse.setRight(new TreeNode(value));
				}
				else
					addRecurse(value,traverse.getRight());
		}
	}
	/**
	 *	Print Binary Tree Inorder
	 */
	public void printInorder() { 
		inorderRecurse(root);
	}
	
	public void inorderRecurse(TreeNode node){
		if(node.getLeft()!=null)
			inorderRecurse(node.getLeft());
		System.out.print(node.getValue()+" ");
		if(node.getRight()!=null)
			inorderRecurse(node.getRight());
	}
	public void preorderRecurse(TreeNode node){
		System.out.print(node.getValue()+" ");
		if(node.getLeft()!=null)
			preorderRecurse(node.getLeft());
		if(node.getRight()!=null)
			preorderRecurse(node.getRight());
	}
	public void postorderRecurse(TreeNode node){
		if(node.getLeft()!=null)
			postorderRecurse(node.getLeft());
		if(node.getRight()!=null)
			postorderRecurse(node.getRight());
		System.out.print(node.getValue()+" ");
	}
	/**
	 *	Print Binary Tree Preorder
	 */
	public void printPreorder() {
		preorderRecurse(root);
	}
	
	/**
	 *	Print Binary Tree Postorder
	 */
	public void printPostorder() { 
		postorderRecurse(root);
	}
		
	/**	Return a balanced version of this binary tree
	 *	@return		the balanced tree
	 */
	public BinaryTree<E> makeBalancedTree() {
		BinaryTree<E> balancedTree = new BinaryTree<E>();
		ArrayList<E> orderedList = new ArrayList<>();
		createOrderedList(orderedList, root);
		balancedTree.root=createBalancedArray(orderedList, 0, orderedList.size()-1);
		return balancedTree;
	}
	
	public void createOrderedList(ArrayList<E> list, TreeNode<E> node){
		if(node.getLeft()!=null)
			createOrderedList(list,node.getLeft());
		list.add(node.getValue());
		if(node.getRight()!=null)
			createOrderedList(list,node.getRight());
	}

	public TreeNode<E> createBalancedArray(ArrayList<E> list, int start, int end){
		if(start<=end){
			int mid =(start+end)/2;
			TreeNode<E> node = new TreeNode<E>(list.get(mid));
			node.setLeft(createBalancedArray(list, start, mid-1));
			node.setRight(createBalancedArray(list, mid+1, end));
			return node;
		}
		return null;
	}
	/**
	 *	Remove value from Binary Tree
	 *	@param value		the value to remove from the tree
	 *	Precondition: value exists in the tree
	 */
	public void remove(E value) {
		root = remove(root, value);
	}
	/**
	 *	Remove value from Binary Tree
	 *	@param node			the root of the subtree
	 *	@param value		the value to remove from the subtree
	 *	@return				TreeNode that connects to parent
	 */
	public TreeNode<E> remove(TreeNode<E> node, E value) {
		if(node==null) return null;
		if(value.compareTo(node.getValue())<0){
			node.setLeft(remove(node.getLeft(),value));
		}
		else if(value.compareTo(node.getValue())>0){
			node.setRight(remove(node.getRight(),value));
		}
		else{
			if(node.getLeft()==null&&node.getRight()==null){
				node=null;
			}
			else if (node.getRight()==null){
				System.out.println(node+" "+node.getLeft());
				node=node.getLeft();
			}
			else if (node.getLeft()==null){
				node=node.getRight();
			}
			else{
				TreeNode<E> smallestNode=node.getRight();;
				
				while(smallestNode.getLeft()!=null){
					smallestNode=smallestNode.getLeft();
				}
				remove(smallestNode.getValue());
				TreeNode<E> nodeLeft=node.getLeft();
				TreeNode<E> nodeRight=node.getRight();
				node=new TreeNode<E>(smallestNode.getValue());
				node.setLeft(nodeLeft);
				node.setRight(nodeRight);
				
			}
		}
		return node;
	}
	

	/*******************************************************************************/	
	/********************************* Utilities ***********************************/	
	/*******************************************************************************/	
	/**
	 *	Print binary tree
	 *	@param root		root node of binary tree
	 *
	 *	Prints in vertical order, top of output is right-side of tree,
	 *			bottom is left side of tree,
	 *			left side of output is root, right side is deepest leaf
	 *	Example Integer tree:
	 *			  11
	 *			/	 \
	 *		  /		   \
	 *		5			20
	 *				  /	  \
	 *				14	   32
	 *
	 *	would be output as:
	 *
	 *				 32
	 *			20
	 *				 14
	 *		11
	 *			5
	 ***********************************************************************/
	public void printTree() {
		printLevel(root, 0);
	}
	public void clear(){
		root=null;
	}
	/**
	 *	Recursive node printing method
	 *	Prints reverse order: right subtree, node, left subtree
	 *	Prints the node spaced to the right by level number
	 *	@param node		root of subtree
	 *	@param level	level down from root (root level = 0)
	 */
	private void printLevel(TreeNode<E> node, int level) {
		if (node == null) return;
		// print right subtree
		printLevel(node.getRight(), level + 1);
		// print node: print spaces for level, then print value in node
		for (int a = 0; a < PRINT_SPACES * level; a++) System.out.print(" ");
		System.out.println(node.getValue());
		// print left subtree
		printLevel(node.getLeft(), level + 1);
	}
	private void printGivenLevel(TreeNode<E> node, int level) {
		if(level==0) System.out.print(node.getValue().getName());
		printlevel(node.getLeft(),level-1);
		printlevel(node.getRight(),level-1);
	}
	private TreeNode<E> getRoot(){
		return root;
	}
	
	
}
