package productions;
import enums.*;
import users.*;
import util.*;

public class ProductionClass implements Production {
	/**
	 * Default size of the database of collaborators.
	 */
	public static final int DEFAULT_SIZE = 100;
	
	/**
	 * Collection of collaborators.
	 */
	private Array<User> users;
	
	
	public ProductionClass() {
		users = new ArrayClass<User>(DEFAULT_SIZE);
	}
	
	
	@Override
	public void addUser(UserTypeEnum usertype, NotorietyTypeEnum notoriety, int hourlyCost, String username) {
		User user = null;	//class Main ensures that usertype has a match, therefore user won't be null
		switch(usertype) {	
		case ACTOR:
			if(notoriety == NotorietyTypeEnum.NORMAL)
				user = new ActorClass(hourlyCost, username);
			else {
				if(notoriety == NotorietyTypeEnum.STAR)						//defensive programming against null
					user = new ActorStarClass(hourlyCost, username);
			}
			break;
		case DIRECTOR:
			if(notoriety == NotorietyTypeEnum.NORMAL)
				user = new DirectorClass(hourlyCost, username);
			else {
				if(notoriety == NotorietyTypeEnum.STAR)
					user = new DirectorStarClass(hourlyCost, username);
			}
			break;
		case TECHNICIAN:
			user = new TechnicianClass(hourlyCost, username);
			break;
		case SENIOR:
			user = new SeniorProducerClass(hourlyCost, username);
			break;
		case JUNIOR:
			user = new JuniorProducerClass(hourlyCost, username);
			break;
		default:	//do nothing
			break;
		}
		users.insertLast(user);
	}

	@Override
	public boolean hasUser(String username) {
		Iterator<User> it = users.iterator();
		while(it.hasNext()) {
			User u = it.next();
			if(u.getName().equals(username))
				return true;
		}
		return false;
	}


	@Override
	public Iterator<User> listUsers() {
		return users.iterator();
	}
	
}