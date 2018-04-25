package array;
/**
 * 
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 *	
 * A generic array, which will be used to create array structures to store information of certain entities.
 * @param <E> a generic type.
 */
public interface Array<E>{
	/**
	 * Inserts an element <code>e</code> in the last position of the array.
	 * @param e element to be inserted.
	 */
	void insertLast(E e);
	
	/**
	 * Inserts an element <code>e</code> in the position <code>pos</code> of the array.
	 * @param e element to be inserted in the array.
	 * @param pos position of the array where the element will be inserted.
	 * @pre pos < size()
	 */
	void insertAt(E e, int pos);
	
	/**
	 * Removes the last element of the array.
	 * @pre size() > 0
	 * @return the element removed.
	 */
	E removeLast();
  
	/**
	 * Remove the element in the position <code>pos</code> of the array.
	 * @param pos position of the element to be removed of the array.
	 * @pre pos < size()
	 * @return the element removed.
	 */
	E removeAt(int pos);
	
	/**
	 * Removes the first occurence of the element <code>e</code> of the array.
	 * @param <code>e</code> element to be searched, beggining in the initial position of the array.
	 * @return <code>true</code> if the element exists and its first occurence was removed from the array,
	 * 	     <code>false</code>  if the element don't exists.
	 */
	boolean remove(E e);

	/**
	 * Searches for the position of the element <code>e</code> in the array.
	 * @param e element to be found in the array.
	 * @return the position of the element in the array, or <code>-1</code> in case the element don't exists.
	 */
	int searchIndexOf(E e);
	
	/**
	 * Returns the element in the position <code>pos</code> of the array.
	 * @param pos position ín the array of the element to be returned.
	 * @return the element in the position <code>pos</code>.
	 * @pre pos < size()
	 */
	E get(int pos);
	
	/**
	 * Returns an iterator for the elements in the array.
	 * @return iterator for the elements in the array.
	 */
	Iterator<E> iterator();
	
	/**
	 * Returns the number of elements in the array.
	 * @return the number of elements in the array.
	 */
	int size();
}

