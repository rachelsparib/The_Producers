package users;

import java.time.LocalDateTime;

import util.Iterator;

/**
 * A collaborator of a audiovisual Production.
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 *
 */
public interface User {
	
	/**
	 * Returns collaborator's name.
	 * @return name of the collaborator.
	 */
	String getName();
	
	/**
	 * Collaborator's cost charged per hour.
	 * @return cost charged per hour by the collaborator.
	 */
	int getHourlyCost();
	
	/**
	 * Returns collaborator's information.
	 * @return information about the collaborator.
	 */
	String toString();
	
	/**
	 * Returns collaborator's calendar with scheduled recordings.
	 * @return calendar of the collaborator.
	 */
	//Array<LocalDateTime> getCalendar();		TODO NOT USED
	
	/**
	 * Adds a recording session schedule to a participating collaborator.
	 * @param start beginning of the recording session.
	 * @param end ending of the recording session.
	 * @pre start != null && duration > 0
	 */
	void addAppointment(LocalDateTime start, int duration);
	
	/**
	 * List all of collaborator's appointments.
	 * @return collection of collaborator's appointments.
	 */
	Iterator<LocalDateTime> listAppointments();
}
