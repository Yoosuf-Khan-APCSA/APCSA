import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;		// for testing purposes

/**
 *	SortMethods - Sorts an ArrayList of Strings in ascending order.
 *
 *	Requires FileUtils class to compile.
 *	Requires file randomWords.txt to execute a test run.
 *
 * @author	Yoosuf Khan
 * @since	January 10 2025
 */
public class SortMethods {
	List<String > newArr = new ArrayList<String>(); //Temporary string for Merge sort
	/**
	 *	Merge Sort algorithm - in ascending order
	 *	@param arr		List of String objects to sort
	 */
	public void mergeSort(List<String> arr) {
		for(int i=0;i<arr.size();i++)
			newArr.add("");
		mergeSortRecurse(arr, 0, arr.size() - 1);
	}
	/**
	 *	Swap 2 elements in a String List
	 *	@param arr		List of String objects 
	 *	@param x 		index of element 1
	 *	@param y 		index of element 2
	 */
	private void swap(List<String> arr, int x, int y){
   		String temp = arr.get(x);
  		arr.set(x, arr.get(y));
  		arr.set(y, temp);
	}
	/**
	 *	Recursive mergeSort method.
	 *	@param arr		List of String objects to sort
	 *	@param first	first index of arr to sort
	 *	@param last		last index of arr to sort
	 */
	public void mergeSortRecurse(List<String> arr, int first, int last) {
		if(last-first<2){
			if(last>first&& arr.get(last).compareTo(arr.get(first))<0){
				swap(arr, last,first);
			}
		}
		else{
			int middle = (first+last)/2;
			mergeSortRecurse(arr,first,middle);
			mergeSortRecurse(arr,middle+1,last);
			merge(arr,first, middle,last);
		 }
	}
	
	/**
	 *	Merge two lists that are consecutive elements in array.
	 *	@param arr		List of String objects to merge
	 *	@param first	first index of first list
	 *	@param mid		the last index of the first list;
	 *					mid + 1 is first index of second list
	 *	@param last		last index of second list
	 */
	 private void merge(List<String> arr, int first, int mid, int last){
		int i=first, j=mid+1,k=first;
		while (i<=mid&&j<=last){
			if(arr.get(i).compareTo(arr.get(j))<0){
				newArr.set(k,arr.get(i));
				i++;
			}
			else{
				newArr.set(k,arr.get(j));
				j++;
			}
			k++;
		}
		while(i<=mid){
			newArr.set(k,arr.get(i));
			i++;
			k++;
		}
		while(j<=last){
			newArr.set(k,arr.get(j));
			j++;
			k++;
		}
		for(k=first;k<=last;k++)
			arr.set(k,newArr.get(k));
	}
	
	
	/**
	 *	Print an List of Strings to the screen
	 *	@param arr		the List of Strings
	 */
	public void printArray(List<String> arr) {
		if (arr.size() == 0) System.out.print("(");
		else System.out.printf("( %-15s", arr.get(0));
		for (int a = 1; a < arr.size(); a++) {
			if (a % 5 == 0) System.out.printf(",\n  %-15s", arr.get(a));
			else System.out.printf(", %-15s", arr.get(a));
		}
		System.out.println(" )");
	}
	
	/*************************************************************/
	/********************** Test program *************************/
	/*************************************************************/
	private final String FILE_NAME = "randomWords.txt"; //file of words
	
	 /**
     * Runs program run method of WordFinder
     **/
	public static void main(String[] args) {
		SortMethods se = new SortMethods();
		se.run();
	}
	/**
     * Runs program
     **/
	public void run() {
		List<String> arr = new ArrayList<String>();
		// Fill List with random words from file		
		fillArray(arr);
		
		System.out.println("\nMerge Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		mergeSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
	}
	
	// Fill String array with words
	public void fillArray(List<String> arr) {
		Scanner inFile = FileUtils.openToRead(FILE_NAME);
		while (inFile.hasNext())
			arr.add(inFile.next());
		inFile.close();
	}
}
