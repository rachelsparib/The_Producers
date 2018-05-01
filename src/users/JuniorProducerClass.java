package users;

import enums.UserTypeEnum;
/**
 * An implementation of producer of junior rank.
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 *
 */
public class JuniorProducerClass extends ProducerClass implements Producer {
	
	/**
	 * Creates a junior producer.
	 * @param hourlyCost junior producer's cost charged per hour.
	 * @param username name of the junior producer.
	 */
	public JuniorProducerClass(int hourlyCost, String username) {
		super(hourlyCost, username);
	}

	@Override
	public String toString() {
		return UserTypeEnum.PRODUCER.getName()+ " " + UserTypeEnum.JUNIOR.getName() + getName() + " " + getHourlyCost();
	}
}
