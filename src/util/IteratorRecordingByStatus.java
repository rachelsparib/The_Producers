package util;

import enums.RecordingStatusEnum;
import recordings.Recording;

/**
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 * 
 * An implementation of a generic iterator with filter, which will be used to traverse recordings with a certain recording status.
 * @param <E> a generic type.
 * 
 */
public class IteratorRecordingByStatus<E> implements Iterator<E> {
	
	/**
	 * The array to be iterated.
	 */
	private Array<E> vector;
		
	/**
	 * Element being visited.
	 */
	private int current;
	
	private T filter;

	/**
	 * Creates an iterator with filter by recording status.
	 * @param <T>
	 * @param status status of the recording.
	 * @param recordings array of recordings.
	 * @param counter number of elements in the array.
	 */
	@SuppressWarnings("unchecked")
	public <T> IteratorRecordingByStatus(T filter, Array<E> vector) {
		this.vector = vector;
		this.filter = (E) new Object();	//cast to type of filter received
		current = 0;
		init();
	}
	
	@Override
	public void init() {
		vector.iterator().init();
		searchNext();
	}

	@Override
	public boolean hasNext() {
		return vector.iterator().hasNext();
	}

	@Override
	public E next() {
		E elem = vector.get(current++);
		searchNext();
		return elem;	
	}
	
	/**
	 * Sets counter in the next element with the desired status.
	 */
	private void searchNext() {
		while ( (current < recordings.size()) && !recordings.get(current).getStatus().equals(status))
			current++;
	}

}
