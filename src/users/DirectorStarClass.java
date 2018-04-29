package users;

import enums.NotorietyTypeEnum;
import enums.UserTypeEnum;

/**
 * An implementation of a director with star notoriety.
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 *
 */
public class DirectorStarClass extends DirectorClass implements Director {
	/**
	 * Creates a director with the notoriety of a star.
	 * @param hourlyCost actor's cost charged per hour.
	 * @param username name of the actor.
	 */
	public DirectorStarClass(int hourlyCost, String username) {
		super(hourlyCost, username);
	}
	
	public String toString() {	//method redefinition from DirectorClass
		return UserTypeEnum.DIRECTOR.getName() + " " +  NotorietyTypeEnum.STAR.getName() + getName() + " " + getHourlyCost();
	}
}
