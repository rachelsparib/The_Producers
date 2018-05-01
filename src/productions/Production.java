package productions;

import java.time.LocalDateTime;

import enums.*;
import users.User;
import util.*;
import recordings.Local;
import recordings.Recording;


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
	 * Returns a collaborator with name <code>username</code> or null if it doesn't exists.
	 * @param username collaborator's name.
	 * @return collaborator with name <code>username</code> or null if it doesn't exists.
	 */
	User getUser(String username);
	
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
	 * Returns a local with name <code>localname</code> or null if doesn't exists.
	 * @param localname name of the local.
	 * @return local with name <code>localname</code> or null if doesn't exists.
	 */
	Local getLocal(String localname);
	
	/**
	 * Returns iterator for the collection of locals.
	 * @return iterator of locals.
	 */
	Iterator<Local> listLocals();
	
	/**
	 * Adds a new recording session.
	 * @param localname local's name of the recording session.
	 * @param collabsName names of the collaborators participating in the recording.
	 * @param start instant of time of the beginning of the recording.
	 * @param duration recording's duration.
	 * @pre !hasRecording(localname, start) && !isBeforeLast(start) && hasLocal(localname) && hasCollabs(collabsName)
	 */
	void addRecording(String localname, Array<String> collabsName, LocalDateTime start, int duration);
	
	/**
	 * Checks if a recording initial date if before the last recorded.
	 * @param start instant of time of the beginning of the recording.
	 * @return <code>true</code> if a recording with initial date <code>date</code> is before the last recorded or <code>false</code> otherwise.
	 */
	boolean isBeforeLastRecorded(LocalDateTime start);
	
	/**
	 * Returns an iterator for the collection of recordings of an audiovisual Production.
	 * @return an iterator of recordings.
	 */
	public Iterator<Recording> listRecordings();
	
	/**
	 * Checks if exists a recording session that begins in the instant of time <code>start</code>.
	 * @param start instant of time of the beginning of the recording.
	 * @return <code>true</code> if exists a recording session that begins in the instant of time <code>start</code> or <code>false</code> otherwise.
	 */
	boolean hasRecording(String localname, LocalDateTime start);
	
	/**
	 * Returns a recording session with initial instant of time of <code>start</code> or null if it doesn't exists.
	 * @param localname local's name of the recording session.
	 * @param start instant of time of the beginning of the recording.
	 * @return a recording session with initial instant of time of <code>start</code> or null if it doesn't exists.
	 * @pre hasRecording(localname, start)
	 */
	Recording getRecording(String localname, LocalDateTime start);
	
	/**
	 * Checks if a collection of collaborators exist in the audiovisual Production.
	 * @param collabsName collection of collaborators' names.
	 * @return <code>true</code> if all collaborators in <code>collabsName</code> collection exist in the Production or <code>false</code> otherwise.
	 */
	boolean hasUserCollection(Array<String> collabsName);
	
	/**
	 * Returns a collection of collaborators who's name is in the collection of <code>collabsName</code>.
	 * @param collabsName collaborators's names.
	 * @return collection of collaborators.
	 * @pre hasUserCollection(collabsName)
	 */
	Array<User> getUserCollection(Array<String> collabsName);
	
	/**
	 * Checks if exists a star's conflict in a collection of names of collaborators.
	 * @param collabsName collection of colaborators's names.
	 * @return <code>true</code> if there's a conflict between at least one star and other collaborator or <code>false</code> otherwise.
	 */
	boolean hasBlacklistConflict(Array<String> collabsName);
	
	/**
	 * Changes a recording suspension status.
	 * @param name of the local of recording.
	 * @param start instant of time of the beginning of the recording.
	 * @pre hasRecording(localname, start)
	 */
	void toggleRecordingSuspension(String localname, LocalDateTime start);		// use getRecording 
	
	/**
	 * Checks if a recording is suspended.
	 * @param name of the local of recording.
	 * @param start instant of time of the beginning of the recording.
	 * @return <code>true</code> if the recording <code>rec</code> is suspended or <code>false</code> otherwise.
	 * @pre hasRecording(localname, start)
	 */
	boolean isRecordingSuspended(String localname, LocalDateTime start);	// use getRecording 
	
	/**
	 * Changes the recording status to <code>status</code>.
	 * @param name of the local of recording.
	 * @param start instant of time of the beginning of the recording.
	 * @param status status of the recording.
	 * @pre hasRecording(localname, start)
	 */
	void changeRecordingStatus(String localname, LocalDateTime start, RecordingStatusEnum status); // getRecording in Main
	
	/**
	 * Returns a collection of recordings with status <code>status</code>.
	 * @param status status of the recording.
	 * @return collection of recordings with status <code>status</code>.
	 */
	Array<Recording> getRecCollectionByStatus(RecordingStatusEnum status);
	
	
	/**
	 * Checks if the recording has a conflict with any other recording.
	 * @param name of the local of recording.
	 * @param start instant of time of the beginning of the recording.
	 * @return <code>true</code> if exists a conflict with another recording or <code>false</code> otherwise.
	 */
	boolean hasRecordingConflict(String localname, LocalDateTime start);	//TODO and include situation of hasProducerPriority(RecordingRec)
	
	/**
	 * Checks if a recording has priority over other conflicting recording (producer of superior rank).
	 * @param name of the local of recording.
	 * @param start instant of time of the beginning of the recording.
	 * @return <code>true</code> if the recording has priority over other conflicting <code>recording</code> or false otherwise.
	 * @pre hasRecordingConflict(localname, start)
	 */
	boolean hasProducerPriority(String localname, LocalDateTime start);
	
//	/**
//	 * Returns main producer of the recording session <code>rec</code>.
//	 * @param name of the local of recording.
//	 * @param start instant of time of the beginning of the recording.
//	 * @return main producer of the recording session.
//	 */
//	Producer getMainProducerOfRec(String localname, LocalDateTime start);	//TODO not used. Remove in the end

	/**
	 * Returns the conflict recording of the recording with local's name <code>localname</code> and starting date <code>start</code> or null if it doesn't exists any.
	 * @param name of the local of recording.
	 * @param start instant of time of the beginning of the recording.
	 * @return recording that is conflicting with the one given or null if it doesn't exists any.
	 */
	Recording getConflictingRecording(String localname, LocalDateTime start);
	
	/**
	 * Reschedules the recording session <code>rec</code> to the same place and at a time and when all the collaborators are available
	 * @param rec recording session to be rescheduled.
	 * @pre hasRecording(localname, start) && (hasBlacklistConflict(collabsName) || (hasRecordingConflict(localname, start) && hasProducerPriority(localname, start))) 
	 */
	void rescheduleRecording(Recording rec);
	
	
	
//	boolean hasRescheduledAnotherRecording(String localname, LocalDateTime start);	//TODO
	
	
	
	//addUserToBlacklist(username)		//use getUser(username); @Pre: hasUser
	//removeUserFromBlacklist(username)	//use getUser(username); @Pre: hasUser
	//listUserBlacklist(username)
	//hasUserInRecording(username)
	//hasLocalInRecording(localname)

	
	
	
	
//	public LocalDateTime getDate();

//	public boolean isDone();
//	
//	public boolean isPending();
//	
//	public int getDuration();

}
