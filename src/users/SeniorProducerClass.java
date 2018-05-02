package users;

import enums.UserTypeEnum;

/**
 * An implementation of producer of senior rank.
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 *
 */
public class SeniorProducerClass extends ProducerClass implements Producer {
	
	/**
	 * Creates a senior producer.
	 * @param hourlyCost senior producer's cost charged per hour.
	 * @param username name of the senior producer.
	 */
	public SeniorProducerClass(int hourlyCost, String username) {
		super(hourlyCost, username);
	}

	@Override
	public String toString() {
		return UserTypeEnum.PRODUCER.getName()+ " " + UserTypeEnum.SENIOR.getName() + " " + getName() + " " + getHourlyCost();
	}
}
