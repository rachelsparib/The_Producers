package util;

import enums.RecordingStatusEnum;

/**
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 * 
 * An implementation of a generic iterator with filter, which will be used to traverse recordings with a certain recording status.
 * @param <E> a generic type.
 * 
 */
public class IteratorWithFilterClass<E> implements Iterator<E> {
	
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
	
	private RecordingStatusEnum status;

	/**
	 * Creates an iterator with filter by recording status.
	 * @param status status of the recording.
	 * @param vector array of recordings.
	 * @param counter number of elements in the array.
	 */
	public IteratorWithFilterClass(RecordingStatusEnum status, E[] vector, int counter) {
		this.vector = vector;
		this.counter = counter;
		this.status = status;  
		init();
	}
	
	@Override
	public void init() {
		current = 0;
		searchNext();
	}

	@Override
	public boolean hasNext() {
		return current < counter;
	}

	@Override
	public E next() {
		E elem = vector[current++];
		searchNext();
		return elem;
	}
	
	/**
	 * Sets counter in the next element with the desired status.
	 */
	private void searchNext() {
		while ( (current < counter) && !vector[current].equals(status))
			current++;
	}

}
