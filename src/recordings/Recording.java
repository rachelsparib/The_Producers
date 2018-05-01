package recordings;

import java.time.LocalDateTime;

import enums.RecordingStatusEnum;
import users.Director;
import users.Producer;
import users.User;
import util.Array;


/**
 * A recording of an audiovisual Production.
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 *
 */
public interface Recording {
	
	/**
	 * Returns the local of the recording session.
	 * @return local of the recording session.
	 */
	Local getLocal();
	
	/**
	 * Returns instant of time of the beginning of the recording.
	 * @return instant of time of the beginning of the recording.
	 */
	LocalDateTime getStartDate();
	
	/**
	 * Returns the duration of the recording.
	 * @return duration of the recording.
	 */
	int getDuration();
	
	/**
	 * Returns instant of time of the ending of the recording.
	 * @return instant of time of the ending of the recording.
	 */
	LocalDateTime getEndDate();
	
	/**
	 * Returns the status of the recording.
	 * @return status of the recording.
	 */
	RecordingStatusEnum getStatus();
	
	/**
	 * Checks if the recording is suspended following a disagreement between a star collaborator.
	 * @return <code>true</code> if the recording is suspended or <code>false</code> otherwise.
	 */
	boolean isSuspended();
	
	/**
	 * Changes recording suspension status.
	 */
	void toggleSuspension();
	
	/**
	 * Changes a recording status to <code>status</code>.
	 * @param status status of the recording.
	 */
	void changeRecordingStatus(RecordingStatusEnum status);	
	
	/**
	 * Returns collaborators that are participating in the recording session.
	 * @return collaborators participating in the recording session.
	 */
	Array<User> getCollabs();
	
	/**
	 * Checks if a recording session is in conflict with another one.
	 * @param other another recording session.
	 * @return <code>true</code> if the recording session is in conflict with <code>other</code> recording or false otherwise.
	 */
	boolean equals(Recording other);
	
//	/**
//	 * Checks if a collaborator <code>u</code> is available between <code>start</code> and <code>end</code>.
//	 * @param u a collaborator of the recording session.
//	 * @param start beginning of a period of time.
//	 * @param end ending of a period of time.
//	 * @return if the collaborator <code>u</code> of the recording session is available between <code>start</code> and <code>end</code>.
//	 */
//	boolean isCollabAvailable(User u, LocalDateTime start, LocalDateTime end);
	
		
	/**
	 * Returns the first producer (main producer) in the collection of collaborators in the recordings session.
	 * @return main producer of the recording session.
	 */
	Producer getProducer();	
	
	/**
	 * Returns the first director (main director) in the collection of collaborators in the recordings session.
	 * @return main director of the recording session.
	 */
	Director getDirector();
	
	/**
	 * Returns the next available time of the collaborators involved in this recording or null if all collaborators haven't appointments.
	 * @return next available time of the collaborators involved in this recording or null if all collaborators haven't appointments.
	 */
	LocalDateTime getCollabsMaxTime();
	
	/**
	 * Reschedules recording to another day, month and year.
	 * @param date new date of the recording.
	 */
	void rescheduleRecording(LocalDateTime date);
	
	/**
	 * Checks if a recording has a collaborator with name <code>username</code> in its collection of participating collaborators.
	 * @param username collaborator name.
	 * @return <code>true</code> if the recording has a collaborator in its collection of participating collaborators or <code>false</code> otherwise.
	 */
	boolean hasCollab(String username);
}
