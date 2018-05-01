package users;

import enums.NotorietyTypeEnum;
import enums.UserTypeEnum;

/**
 * An implementation of an actor with star notoriety.
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 *
 */
public class ActorStarClass extends ActorClass implements Actor {
	
	/**
	 * Creates an actor with the notoriety of a star.
	 * @param hourlyCost actor's cost charged per hour.
	 * @param username name of the actor.
	 */
	public ActorStarClass(int hourlyCost, String username) {
		super(hourlyCost, username);
	}
	
	@Override
	public String toString() {
		return UserTypeEnum.ACTOR.getName() + " " +  NotorietyTypeEnum.STAR.getName() + getName() + " " +  getHourlyCost();
	}
	
}
