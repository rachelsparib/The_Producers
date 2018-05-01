package users;
import util.*;

import java.time.LocalDateTime;

/**
 * Common behaviors of the different collaborators.
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 *
 */
public abstract class UserClass implements User {
	/**
	 * Collaborator's name.
	 */
	private String username;
	
	/**
	 * Collaborator's cost charged per hour.
	 */
	private int hourlyCost;
	
	/**
	 * Collaborator's calendar with scheduled recordings dates.
	 */
	private Array<LocalDateTime> calendar;
	
	/**
	 * Creates a collaborator in the management system of an audiovisual production. 
	 * @param hourlyCost cost per hour charged by the collaborator.
	 * @param username collaborator's name.
	 */
	protected UserClass(int hourlyCost, String username) {
		this.username = username;
		this.hourlyCost = hourlyCost;
		calendar = new ArrayClass<LocalDateTime>();	//with default size of 50
	}
	

	public String getName() {
		return username;
	}
	
	public int getHourlyCost() {
		return hourlyCost;
	}
	
	public abstract String toString();
	
//	public Array<LocalDateTime> getCalendar(){			TODO not used
//		return calendar;
//	}
	
	public void addAppointment(LocalDateTime start, int duration) {
		LocalDateTime end = start.plusMinutes(duration);
		calendar.insertLast(start);
		calendar.insertLast(end);
	}
	
	public Iterator<LocalDateTime> listAppointments(){
		return calendar.iterator();
	}
	
}
