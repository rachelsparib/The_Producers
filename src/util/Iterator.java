package util;

/**
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 * 
 * A generic iterator, which will be used to traverse elements stored in array structures.
 * @param <E> - a generic type.
 * 
 */
public interface Iterator<E> {
	/**
	 * Initializes the iterator.
	 */
	void init();

	/**
	 * Verifies if there are more elements to iterate.
	 * @return <code>true</code> if there are more elements to iterate or <code>false</code> otherwise.
	 */
	boolean hasNext();

	/**
	 * Returns the next element to iterate, advancing with the iterator.
	 * @pre hasNext()
	 * @return the next element.
	 */
	E next();
}
