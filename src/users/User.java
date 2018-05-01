package users;

/**
 * A collaborator of a audiovisual Production.
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 *
 */
public interface User {
	
	/**
	 * Returns collaborator's name.
	 * @return name of the collaborator.
	 */
	String getName();
	
	/**
	 * Collaborator's cost charged per hour.
	 * @return cost charged per hour by the collaborator.
	 */
	int getHourlyCost();
	
	/**
	 * Returns collaborator's information.
	 * @return information about the collaborator.
	 */
	String toString();
}
