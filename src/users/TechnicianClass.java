package users;

import enums.UserTypeEnum;

/**
 * An implementation of a technician.
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 *
 */
public class TechnicianClass extends UserClass implements Technician {
	
	/**
	 * Creates a technician.
	 * @param hourlyCost technician's cost charged per hour.
	 * @param username name of the technician.
	 */
	public TechnicianClass(int hourlyCost, String username) {
		super(hourlyCost, username);
	}

	@Override
	public String toString() {
		return UserTypeEnum.TECHNICIAN.getName() + getName() + " " + getHourlyCost();
	}
}
