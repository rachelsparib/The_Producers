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
		return indexOfUser(username) >= 0;
	}
	
	
	@Override
	public User getUser(String username) {	// Pre: hasUser(username)
		return users.get(indexOfUser(username));
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
		return indexOfLocal(localname) >=0;
	}
	
	
	@Override
	public Local getLocal(String localname) {	//Pre: hasLocal(localname)
		return locals.get(indexOfLocal(localname));
	}
	

	@Override
	public Iterator<Local> listLocals() {
		return locals.iterator();
	}


	@Override
	public void addRecording(String localname, Array<String> collabsName, LocalDateTime start, int duration) {
		Recording rec = new RecordingClass(getLocal(localname), getUserCollection(collabsName), start, duration);
		int i;
		for (i = 0; i < recordings.size(); i++){
			Recording currentRecording = recordings.get(i);
			if(rec.getStartDate().isAfter(currentRecording.getStartDate())){
				break;
			} else if(rec.getStartDate().isEqual(currentRecording.getStartDate())){
				if(rec.getDuration() < currentRecording.getDuration()){
					break;
				}
			}
		}
		
		if(i ==recordings.size()){
			recordings.insertLast(rec);
		}else{
			recordings.insertAt(rec, i);
		}
	}
	
	@Override
	public boolean isBeforeLastRecorded(LocalDateTime startDate) {
		Iterator<Recording> it = this.listRecordingsByStatus(RecordingStatusEnum.DONE);
		while(it.hasNext()) {
			Recording rec = it.next();
			if(startDate.isBefore(rec.getEndDate()))
				return true;
		}
		return false;
	}
	
	@Override
	public Iterator<Recording> listRecordings() {
		return recordings.iterator();
	}
	
	@Override
	public boolean hasRecording(String localname, LocalDateTime start) {
		return indexOfRecording(localname, start) >= 0;
	}


	@Override
	public Recording getRecording(String localname, LocalDateTime start) {	 //Pre: hasRecording(localname, start)
		return recordings.get(indexOfRecording(localname, start));	//a recording is univocally identified by its start instant of time and place
	}
	
	@Override
	public boolean hasUserCollection(Array<String> collabsName) {
		Iterator<String> it = collabsName.iterator();
		while(it.hasNext()) {
			 String name = it.next();
			 if(!hasUser(name))			
				 return false;
		 }
		 return false;
	}
	
	@Override
	public Array<User> getUserCollection(Array<String> collabsName) {
		Iterator<User> it = listUsers();
		Array<User> collabsInRec = new ArrayClass<User>();
		while(it.hasNext()) {
			User u = it.next();
			 for(int i = 0; i < collabsName.size(); i++) {
			 		if( u.getName().equals(collabsName.get(i)))
			 			collabsInRec.insertLast(u);
			 }
		}
		return collabsInRec;
	}
	
	@Override
	public boolean hasBlacklistConflict(Array<String> collabsName) {	
		 Iterator<User> it = getUserCollection(collabsName).iterator();
		 while(it.hasNext()) {
			 User u = it.next();
			 if(u instanceof Star) {
				 for(int i = 0; i < collabsName.size(); i++)
			 		if( ((Star)u).isUserInBlacklist(getUser(collabsName.get(i))))
			 			return true;
			 }	
		}
		return false;
	}


	@Override
	public void toggleRecordingSuspension(String localname, LocalDateTime start) {
		getRecording(localname, start).toggleSuspension();		
	}


	@Override
	public boolean isRecordingSuspended(String localname, LocalDateTime start) {
		return getRecording(localname, start).isSuspended();
	}


	@Override
	public void changeRecordingStatus(String localname, LocalDateTime start, RecordingStatusEnum status) {
		getRecording(localname, start).changeRecordingStatus(status);
	}
	
	@Override
	public Iterator<Recording> listRecordingsByStatus(RecordingStatusEnum status) {
		Iterator<Recording> it = listRecordings();	
		Array<Recording> recfiltered = new ArrayClass<Recording>(DEFAULT_SIZE);
		while(it.hasNext()) {
			Recording rec = it.next();
			if(rec.getStatus().equals(status))
				recfiltered.insertLast(rec);
		}
		return recfiltered.iterator();
	}


	@Override
	public boolean hasRecordingConflict(String localname, LocalDateTime start) {
		Iterator<Recording> it = listRecordings();
		Recording rec = null;
		if(hasRecording(localname, start)) {
			rec = getRecording(localname, start);
			while(it.hasNext()) {
				Recording other = it.next();
				if(rec.equals(other))
					return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean hasProducerPriority(String localname, LocalDateTime start) {	//pre: hasRecordingConflict(localname, start)
		Iterator<Recording> it = listRecordings();
		Recording rec = null;
		if(hasRecording(localname, start)) {
			rec = getRecording(localname, start);
			while(it.hasNext()) {
				Recording other = it.next();
				if(((rec.getProducer() instanceof SeniorProducerClass) && ((other.getProducer() instanceof SeniorProducerClass))))
					return true;
			}
		}
		return false;
	}
	
	@Override
	public Recording getConflictingRecording(String localname, LocalDateTime start) {	//Pre:  hasRecordingConflict(localname, start)
		Iterator<Recording> it = listRecordings();
		if(hasRecording(localname, start)) {
			Recording rec = getRecording(localname, start);
			while(it.hasNext()) {
				Recording other = it.next();
				if(rec.equals(other))
					return other;
			}
		}
		return null;
	}
	
	@Override
	public void rescheduleRecording(Recording rec) {
		LocalDateTime olddate = rec.getStartDate();
		int hour = olddate.getHour();
		int minutes = olddate.getMinute();
		LocalDateTime newdate = null;

		LocalDateTime date = rec.getCollabsMaxTime();	//return could be null
		if(date == null) // maintains
			newdate = olddate;
		else
			newdate = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), hour, minutes);
		
		while(hasRecordingConflict(rec.getLocal().getName(), newdate))
				newdate = newdate.plusDays(1);	//tries the next day
		rec.rescheduleRecording(newdate);
	}

	
	// Private methods
	/**
	 * Searches for the position of the collaborator with name <code>username</code> in the array.
	 * @param username name of the collaborator to be found in the array.
	 * @return the position of the collaborator in the array, or <code>-1</code> in case the collaborator don't exists.
	 */
	private int indexOfUser(String username) {
		for(int i = 0; i < users.size(); i++)
			if (users.get(i).getName().equals(username))
				return i;
		return -1;
	}
	
	/**
	 * Searches for the position of the recording with name of its local <code>localname</code> and beggining of recording time <code>start</code> in the array.
	 * @param localname local's name of the recording to be found in the array.
	 * @param instant of time of the beginning of the recording.
	 * @return the position of the recording in the array, or <code>-1</code> in case the recording don't exists.
	 */
	private int indexOfRecording(String localname, LocalDateTime start) {
		for(int i = 0; i < recordings.size(); i++)
			if (recordings.get(i).getLocal().equals(getLocal(localname)) && recordings.get(i).getStartDate().isEqual(start))
				return i;
		return -1;
	}
	
	/**
	 * Searches for the position of the local with name <code>localname</code> in the array.
	 * @param localname name of the local to be found in the array.
	 * @return the position of the local in the array, or <code>-1</code> in case the local don't exists.
	 */
	private int indexOfLocal(String localname) {
		for(int i = 0; i < locals.size(); i++)
			if (locals.get(i).getName().equals(localname))
				return i;
		return -1;
	}


	@Override
	public boolean hasStar(String starname) {
		int index = indexOfUser(starname);
		if(users.get(index) instanceof Star)
			return true;
		return false;
	}


	@Override
	public boolean hasCollabInBlacklist(String starname, String collabname) {
		Star s = (Star)users.get(indexOfUser(starname));
		User c = users.get(indexOfUser(collabname));
		return s.isUserInBlacklist(c);
	}


	@Override
	public int addCollabToBlacklist(String starname, String collabname) {
		Star s = (Star)users.get(indexOfUser(starname));
		User c = users.get(indexOfUser(collabname));
		s.addUserBlacklist(c);
		int recSuspended = 0;
		Iterator<Recording> it = listRecordings();
		while(it.hasNext()) {
			Recording rec = it.next();
			if(rec.hasCollab(starname) && rec.hasCollab(collabname))
				if(!rec.isSuspended()) {
					rec.toggleSuspension();
					recSuspended++;
				}
		}	
		return recSuspended;
	}


	@Override
	public int removeCollabOfBlacklist(String starname, String collabname) {
		Star s = (Star)users.get(indexOfUser(starname));
		User c = users.get(indexOfUser(collabname));
		s.removeUserBlacklist(c);
		int recActivated = 0;
		Iterator<Recording> it = listRecordings();
		while(it.hasNext()) {
			Recording rec = it.next();
			if(rec.hasCollab(starname) && rec.hasCollab(collabname))
				if(rec.isSuspended()) {
					rec.toggleSuspension();
					recActivated++;
				}
		}	
		return recActivated;
	}
	
	@Override
	public Iterator<Recording> getRecCollectionByUser(User user) {
		Iterator<Recording> it = listRecordingsByStatus(RecordingStatusEnum.SCHEDULED);
		Array<Recording> userRec = new ArrayClass<Recording>(DEFAULT_SIZE);
		while(it.hasNext()) {
			Recording rec = it.next();
			if(rec.hasCollab(user.getName()))
				userRec.insertLast(rec);
		}
		return userRec.iterator();
	}
	
//	@Override
//	public Producer getMainProducerOfRec(Recording rec) {		//TODO not used. Remove in the end.
//		// TODO Auto-generated method stub
//		return null;
//	}
	

}
