package users;

import enums.NotorietyTypeEnum;
import enums.UserTypeEnum;
import util.*;

/**
 * An implementation of an actor with star notoriety.
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 *
 */
public class ActorStarClass extends ActorClass implements Actor, Star {
	
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
	public String toString() { //method redefinition from ActorClass
		return UserTypeEnum.ACTOR.getName() + " " +  NotorietyTypeEnum.STAR.getName()+ " " + getName() + " " +  getHourlyCost();
	}
	
	@Override
	public void addUserBlacklist(User u) {		//Pre: hasUser(username)
		blacklist.insertLast(u);
	}
	
	@Override
	public boolean removeUserBlacklist(User u) {
		return blacklist.remove(u);
	}
	
	@Override
	public boolean isUserInBlacklist(User u) {
		return blacklist.searchIndexOf(u) >= 0;
	}
}
