package users;

import enums.NotorietyTypeEnum;
import enums.UserTypeEnum;

/**
 * An implementation of a director.
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 *
 */
public class DirectorClass extends AbstractUserClass implements Director {
	
	/**
	 * Creates a director.
	 * @param hourlyCost director's cost charged per hour.
	 * @param username name of the director.
	 */
	public DirectorClass(int hourlyCost, String username) {
		super(hourlyCost, username);
	}

	@Override
	public String toString() {
		return UserTypeEnum.DIRECTOR.getName() + " " +  NotorietyTypeEnum.NORMAL.getName() + getName() + " " + getHourlyCost();
	}
}
