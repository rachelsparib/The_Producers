package productions;

import enums.*;
import users.User;
import util.*;
import recordings.Local;


/**
 * An audiovisual production and its resources, namely participating collaborators, scheduling of recordings and storage of filming locations.
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 *
 */
public interface Production {
	
	/**
	 * Adds user to the audiovisual production.
	 * @param usertype type of collaborator in the production.
	 * @param notoriety type of notoriety of the collaborator.
	 * @param hourlyCost cost per hour charged by the collaborator.
	 * @param username name of the collaborator.
	 * @pre !hasUser(username)
	 */
	void addUser(UserTypeEnum usertype, NotorietyTypeEnum notoriety, int hourlyCost, String username);
	
	/**
	 * Checks if collaborator with name <code>username</code> already exists in the production.
	 * @param username name of the collaborator.
	 * @return <code>true</code> if a collaborator with name <code>username</code> already exists or <code>false</code> otherwise.
	 */
	boolean hasUser(String username);
	
	/**
	 * Returns iterator for the collection of users.
	 * @return iterator of users.
	 */
	Iterator<User> listUsers();
	
	/**
	 * Adds a new local for recordings.
	 * @param localname name of the local.
	 * @param localcost hourly cost of the local.
	 * @pre !hasLocal(localname)
	 */
	void addLocal(String localname, int localcost);
	
	/**
	 * Checks if a local with name <code>localname</code> already exists for recordings. 
	 * @param localname name of the local.
	 * @return <code>true</code> if a local with name <code>localname</code> already exists or <code>false</code> otherwise.
	 */
	boolean hasLocal(String localname);
	
	/**
	 * Returns iterator for the collection of locals.
	 * @return iterator of locals.
	 */
	Iterator<Local> listLocals();
	
//	public Local getLocal();
//	
//	public LocalDateTime getDate();
//	
//	public Producer getProducer();
//	
//	public boolean isDone();
//	
//	public boolean isPending();
//	
//	public int getDuration();

}
