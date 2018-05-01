package users;

/**
 * An implementation of producer.
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 *
 */
public abstract class ProducerClass extends UserClass implements Producer {
	
	/**
	 * Creates a junior producer.
	 * @param hourlyCost junior producer's cost charged per hour.
	 * @param username name of the junior producer.
	 */
	public ProducerClass(float hourlyCost, String username) {
		super(hourlyCost, username);
	}
	
	@Override
	public abstract String toString();
	
}
