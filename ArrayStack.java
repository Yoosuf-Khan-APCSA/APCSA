import java.util.ArrayList;
import java.util.List;
import java.util.EmptyStackException;

/**
 * Simple stack using ArrayList
 * @author Yoosuf Khan
 * @since Febuary 26 2025
 */
 public class ArrayStack<E> implements Stack<E>
 {
	private List<E> theStack;
	
	public ArrayStack(){
		theStack = new ArrayList<E>();
	}
	public List<E> get(){
		return theStack;
	}
	/** @return		true if the stack is empty; false if otherwise */
	public boolean isEmpty(){ return theStack.isEmpty();	}
	
	/** @return 	the top element on the stack */
	public E peek()
	{
		if(theStack.isEmpty())
			throw new EmptyStackException();
		return theStack.get(theStack.size()-1);
	}
	
	/** @param 		obj	The object to push on the top of the stack */
	public void push(E obj) { theStack.add(obj);}
	
	/** @return		The object removed from the top of the stack */
	public E pop()
	{
		if(theStack.isEmpty())
			throw new EmptyStackException();
		return theStack.remove(theStack.size()-1);
	}
}
