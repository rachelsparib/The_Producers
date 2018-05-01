package users;

import enums.NotorietyTypeEnum;
import enums.UserTypeEnum;

/**
 * An implementation of an actor.
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 *
 */
public class ActorClass extends UserClass implements Actor {
	
	/**
	 * Creates an actor.
	 * @param hourlyCost actor's cost charged per hour.
	 * @param username name of the actor.
	 */
	public ActorClass(int hourlyCost, String username) {
		super(hourlyCost, username);
	}

	@Override
	public String toString() {
		return UserTypeEnum.ACTOR.getName() + " " + NotorietyTypeEnum.NORMAL.getName() + getName() + " " + getHourlyCost();
	}
}
