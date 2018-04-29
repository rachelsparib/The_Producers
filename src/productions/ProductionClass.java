package productions;
import java.time.LocalDateTime;

import enums.*;
import users.*;
import util.*;
import recordings.*;

/**
 * An implementation of an audiovisual production and its resources, namely participating collaborators, scheduling of recordings and storage of filming locations.
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 *
 */
public class ProductionClass implements Production {
	/**
	 * Default size of the database of collaborators.
	 */
	public static final int DEFAULT_SIZE = 100;
	
	/**
	 * Collection of collaborators of the audiovisual Production.
	 */
	private Array<User> users;
	
	/**
	 * Collection of possible locals for a recording.
	 */
	private Array<Local> locals;
	
	/**
	 * Collection of recordings of an audiovisual Production.
	 */
	private Array<Recording> recordings;
	
	/**
	 * Creates an audiovisualProduction.
	 */
	public ProductionClass() {
		users = new ArrayClass<User>(DEFAULT_SIZE);		// size 100
		locals = new ArrayClass<Local>();			// size 50
		recordings = new ArrayClass<Recording>(DEFAULT_SIZE);	// size 100
	}
	
	
	@Override
	public void addUser(UserTypeEnum usertype, NotorietyTypeEnum notoriety, int hourlyCost, String username) {
		User user = null;	//class Main ensures that usertype has a match, therefore user won't be null
		switch(usertype) {	
		case ACTOR:
			if(notoriety == NotorietyTypeEnum.NORMAL)
				user = new ActorClass(hourlyCost, username);
			else {
				if(notoriety == NotorietyTypeEnum.STAR)				//defensive programming against null
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


	@Override
	public void addLocal(String localname, int localcost) {
		Local l = new LocalClass(localname, localcost);
		locals.insertLast(l);
	}


	@Override
	public boolean hasLocal(String localname) {
		Iterator<Local> it = locals.iterator();
		while(it.hasNext()) {
			Local l = it.next();
			if(l.getName().equals(localname))
				return true;
		}
		return false;
	}


	@Override
	public Iterator<Local> listLocals() {
		return locals.iterator();
	}


	@Override
	public void addRecording(Array<User> users, LocalDateTime date, int duration) {
		Recording rec = new RecordingClass(users, date, duration);
		recordings.insertLast(rec);
	}
	
	@Override
	public boolean isBeforeLast(LocalDateTime startDate) {
		Iterator<Recording> it = recordings.iterator();
		while(it.hasNext()) {
			Recording rec = it.next();
			if(startDate.isBefore(rec.getEndDate()))
				return true;
		}
		return false;
	}

	@Override
	public Iterator<Recording> listRecordings(RecordingStatusEnum status) {		
		Iterator<Recording> iterator = new IteratorRecordingByStatus<Recording>(status, recordings);	//TODO implement iterator with filter
		//or implements here with a normal iterator
		return iterator;
	}


	
	
	
}
