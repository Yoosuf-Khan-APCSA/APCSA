import java.util.NoSuchElementException;

/**
 *	SinglyLinkedList - (description)
 *
 *	@author	
 *	@since	
 */
public class SinglyLinkedList<E extends Comparable<E>>
{
	/* Fields */
	private ListNode<E> head, tail;		// head and tail pointers to list
	
	/* No-args Constructors */
	public SinglyLinkedList() {
		head=null;
		tail=null;
		}
	
	/** Copy constructor */
	public SinglyLinkedList(SinglyLinkedList<E> oldList) {
		head=null;
		tail=null;
		ListNode<E> tmp = oldList.head;
		while (tmp.getNext() != null){
			tmp = tmp.getNext();
			add(tmp.getValue());
		}
	}
	
	/**	Clears the list of elements */
	public void clear() {
		head=null;
		tail=null;
		}
	
	/**	Add the object to the end of the list
	 *	@param obj		the object to add
	 *	@return			true if successful; false otherwise
	 */
	public boolean add(E obj) {
		ListNode<E> tmp = new ListNode<E>(obj,null);
		if(head==null){
			head=tmp;
			tail=tmp;
		}
		else{
			tail.setNext(tmp);
			tail=tmp;
		}
		return true;
	}
	
	/**	Add the object at the specified index
	 *	@param index		the index to add the object
	 *	@param obj			the object to add
	 *	@return				true if successful; false otherwise
	 *	@throws NoSuchElementException if index does not exist
	 */
	public boolean add(int index, E obj) {
		if(index<0||index>size()) {
			System.err.print("NoSuchElementException");
			return false;
		}
		ListNode<E> tmp = new ListNode<E>(obj,null);
		if(index==0){
			tmp.setNext(head);
			head=tmp;
			if(tail==null) tail=tmp;
		}
		else if(index==size()){
			tail.setNext(tmp);
			tail=tmp;
		}
		else{
			ListNode<E> cur=head.getNext(),pre=head;
			for(int i=1; i<index;i++){
				pre=cur;
				cur = cur.getNext();
			}
			tmp.setNext(cur);
			pre.setNext(tmp);
		}
		return true;
	}
	
	/**	@return the number of elements in this list */
	public int size() {
		int count=1;
		ListNode<E> tmp = head;
		if(head==null) return 0;
		while (tmp.getNext() != null){
			tmp = tmp.getNext();
			count++;
		}
		return count;
	}
	
	/**	Return the ListNode at the specified index
	 *	@param index		the index of the ListNode
	 *	@return				the ListNode at the specified index
	 *	@throws NoSuchElementException if index does not exist
	 */
	public ListNode<E> get(int index) {
		ListNode<E> cur=head;
		for(int i=0; i<index;i++){
			if(cur==null){
				System.err.print("NoSuchElementException");
				return null;
			}
			cur = cur.getNext();
		}
		
		return cur;
	}
	
	/**	Replace the object at the specified index
	 *	@param index		the index of the object
	 *	@param obj			the object that will replace the original
	 *	@return				the object that was replaced
	 *	@throws NoSuchElementException if index does not exist
	 */
	public E set(int index, E obj) {
		ListNode<E> cur=head.getNext(),pre=head;
		ListNode<E> tmp = new ListNode<E>(obj,null);
		ListNode<E> removed ;

		if(index==0){
			removed=head;
			tmp.setNext(head.getNext());
			head=tmp;
		}
		else{
			for(int i=1; i<index;i++){
				if(cur==null){
					System.err.print("NoSuchElementException");
					return null;
				}
				pre=cur;
				cur = cur.getNext();
			}
		}
		pre.setNext(tmp);
		removed=cur;
		tmp.setNext(cur.getNext());
		return removed.getValue();
	}
	
	/**	Remove the element at the specified index
	 *	@param index		the index of the element
	 *	@return				the object in the element that was removed
	 *	@throws NoSuchElementException if index does not exist
	 */
	public E remove(int index) {
		ListNode<E> cur=head.getNext(),pre=head;
		ListNode<E> removed ;
		if(index<0||index>size()||isEmpty()) {
			System.err.print("NoSuchElementException");
			return null;
		}
		if(index==0){
			removed=head;
			if(tail==head){
				head=null;
				tail=null;
			}
			else {
				head=head.getNext();
			}
			return removed.getValue();
		}
		else{
			for(int i=1; i<index;i++){
				if(cur==null){
					System.err.print("NoSuchElementException");
					return null;
				}
				pre=cur;
				cur = cur.getNext();
			}
		}
		if(cur==tail){
			pre.setNext(null);
			tail=pre;
		}
		else
			pre.setNext(cur.getNext());
		removed=cur;
		return removed.getValue();
	}
	
	/**	@return	true if list is empty; false otherwise */
	public boolean isEmpty() {
		if(head==null && tail==null)
			return true;
		return false;
	}
	
	/**	Tests whether the list contains the given object
	 *	@param object		the object to test
	 *	@return				true if the object is in the list; false otherwise
	 */
	public boolean contains(E object) {
		if(isEmpty()) return false;
		ListNode<E> tmp = head;
		while (tmp.getNext() != null){
			if(tmp.getValue().equals(object)) return true;
			tmp = tmp.getNext();
		}
		if(tmp.getValue().equals(object)) return true;
		return false;
	}
	
	/**	Return the first index matching the element
	 *	@param element		the element to match
	 *	@return				if found, the index of the element; otherwise returns -1
	 */
	public int indexOf(E element) {
		if(contains(element)){
			ListNode<E> tmp = head;
			for(int i=1;i<size();i++){
				if(tmp.getValue().equals(element)) return i;
				tmp = tmp.getNext();
			}
		}
		return -1;
	}
	
	/**	Prints the list of elements */
	public void printList()
	{
		ListNode<E> ptr = head;
		while (ptr != null)
		{
			System.out.print(ptr.getValue() + "; ");
			ptr = ptr.getNext();
		}
	}
	

}
