package array;

/**
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 * 
 * An implementation of a generic iterator, which will be used to traverse elements stored in array structures.
 * @param <E> - a generic type.
 * 
 */
public class IteratorClass<E> implements Iterator<E> {
	/**
	 * The array to be iterated.
	 */
	private E[] vector;
	
	/**
	 * The actual number of elements.
	 */
	private int counter;
	
	/**
	 * Element being visited.
	 */
	private int current;
	
	
	public IteratorClass(E[] vector, int counter) {
		this.vector = vector;
		this.counter = counter;
		init();
	}

	@Override
	public void init() {
		current = 0;
	}

	@Override
	public boolean hasNext() {
		return current < counter;
	}

	@Override
	public E next() {
		return vector[current++];
	}

}
