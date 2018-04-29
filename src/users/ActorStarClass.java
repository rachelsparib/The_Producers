package users;

import enums.NotorietyTypeEnum;
import enums.UserTypeEnum;
import util.*;

/**
 * An implementation of an actor with star notoriety.
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 *
 */
public class ActorStarClass extends ActorClass implements Actor {
	
	/**
	 * A collection of the star's rivals.
	 */
	private Array<User> blacklist;
	
	/**
	 * Creates an actor with the notoriety of a star.
	 * @param hourlyCost actor's cost charged per hour.
	 * @param username name of the actor.
	 */
	public ActorStarClass(int hourlyCost, String username) {
		super(hourlyCost, username);
		blacklist = new ArrayClass<User>();
	}
	
	@Override
	public String toString() {
		return UserTypeEnum.ACTOR.getName() + " " +  NotorietyTypeEnum.STAR.getName() + getName() + " " +  getHourlyCost();
	}
	
	/**
	 * Adds a collaborator to actor's blacklist.
	 * @param u collaborator turned rival.
	 */
	public void addUserBlacklist(User u) {		//Pre: hasUser(username)
		blacklist.insertLast(u);
	}
	
	/**
	 * Removes a collaborator from the blacklist.
	 * @param u collaborator turned friend.
	 * @return <code>true</code> if collaborator was removed or <code>false</code> if it didn't exist in the collection.
	 */
	public boolean removedUserBlacklist(User u) {
		return blacklist.remove(u);
	}
	
	/**
	 * Checks if a collaborator is in the star's blacklist.
	 * @param u a collaborator.
	 * @return <code>true</code> if collaborator was removed or <code>false</code> if it didn't exist in the collection.
	 */
	public boolean isUserInBlackList(User u) {
		return blacklist.searchIndexOf(u) >= 0;
	}
}
