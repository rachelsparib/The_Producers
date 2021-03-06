package users;

import enums.NotorietyTypeEnum;
import enums.UserTypeEnum;
import util.Array;
import util.ArrayClass;

/**
 * An implementation of a director with star notoriety.
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 *
 */
public class DirectorStarClass extends DirectorClass implements Director, Star {
	/**
	 * A collection of the star's rivals.
	 */
	private Array<User> blacklist;
	
	/**
	 * Creates a director with the notoriety of a star.
	 * @param hourlyCost actor's cost charged per hour.
	 * @param username name of the actor.
	 */
	public DirectorStarClass(int hourlyCost, String username) {
		super(hourlyCost, username);
		blacklist = new ArrayClass<User>();
	}
	
	@Override
	public String toString() {	//method redefinition from DirectorClass
		return UserTypeEnum.DIRECTOR.getName() + " " + NotorietyTypeEnum.STAR.getName()+ " " + getName() + " " + getHourlyCost();
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
