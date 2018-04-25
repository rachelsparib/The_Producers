package array;

/**
 * 
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 *	
 * An implementation of a generic array, which will be used to create array structures to store information of certain entities.
 *
 * @param <E> a generic type.
 */
public class ArrayClass<E> implements Array<E> {
	
	/**
	 * Default size of the array.
	 */
	private static final int DEFAULT_SIZE = 50;

	/**
	 * The generic array.
	 */
	private E [] array;
	
	/**
	 * The actual number of elements.
	 */
	private int counter;

	/**
	 * Constructor that defines an array with the default size.
	 */
	@SuppressWarnings("unchecked")
	public ArrayClass() {
		array = (E []) new Object[DEFAULT_SIZE];
		counter = 0;
	}
	
	
	/**
	 * Constructor that defines an array with the inicial size of <code>capacity</code>
	 * @param capacity inicial size of the array.
	 * @pre capacity > 0
	 */
	@SuppressWarnings("unchecked")
	public ArrayClass(int capacity) {
		array = (E []) new Object[capacity];
		counter = 0;
	}

	@Override
	public void insertLast(E elem) {
		if (counter == array.length) 
			resize();
		array[counter++] = elem;
	}

	@Override
	public void insertAt(E elem, int pos) {
		if (counter == array.length) 
			resize();
		for(int i = counter-1; i >= pos; i--)
			array[i+1] = array[i];
		array[pos] = elem;
		counter++;
	}
	
	@Override
	public E removeLast() {
		return array[--counter];
	}

	@Override
	public E removeAt(int pos) {
		E elem = array[pos];
		for(int i = pos; i < counter-1; i++)
			array[i] = array[i+1];
		counter--;
		return elem;
	}
	
	@Override
	public boolean remove(E e) {
		int pos = searchIndexOf(e);
		if (pos == -1) return false;
		for(int i = pos; i < counter-1; i++)
			array[i] = array[i+1];
		counter--;
		return true;
	}

	@Override
	public int size() {
		return counter;
	}

	@Override
	public int searchIndexOf(E elem) {
		boolean found = false;
		int i=0;
		while(i < counter && !found)
			if(array[i].equals(elem)) //uses references
				found = true;
			else i++;
		if (found) return i;
		else return -1;
	}
	
	public E get(int pos) {
		return array[pos];
	}
	

	@Override
	public Iterator<E> iterator() {
		return new IteratorClass<E>(array,counter);
	}

	/**
	 * Auxiliary method to double the size of the array. 
	 */
	@SuppressWarnings("unchecked")
	private void resize() {
		E[] tmp = (E []) new Object[counter * 2];
		for (int i = 0; i < counter; i++)
			tmp[i] = array[i];
		array = tmp;
	}
	
}
