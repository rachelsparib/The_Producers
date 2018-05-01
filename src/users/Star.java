package users;

/**
 * A behavior extension to the regular collaborator, confining the star's features.
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 *
 */
public interface Star extends User {
	
	/**
	 * Adds a collaborator to a star's blacklist.
	 * @param u collaborator turned rival.
	 */
	public void addUserBlacklist(User u);
	
	/**
	 * Removes a collaborator from the star's blacklist.
	 * @param u collaborator turned friend.
	 * @return <code>true</code> if collaborator was removed or <code>false</code> if it didn't exist in the collection.
	 */
	public boolean removedUserBlacklist(User u);
	
	/**
	 * Checks if a collaborator is in the star's blacklist.
	 * @param u a collaborator.
	 * @return <code>true</code> if collaborator was removed or <code>false</code> if it didn't exist in the collection.
	 */
	public boolean isUserInBlackList(User u);
}
