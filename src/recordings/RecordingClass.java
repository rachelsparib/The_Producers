package recordings;

import java.time.LocalDateTime;

import enums.RecordingStatusEnum;
import users.Producer;
import users.User;
import util.Array;
import util.Iterator;

public class RecordingClass implements Recording {
	
	/**
	 * Collection of collaborators participating in this recording.
	 */
	private Array<User> collabs;
	
	/**
	 * Initial date of the recording.
	 */
	private LocalDateTime initialDate;
	
	/**
	 * Duration of the recording.
	 */
	private int duration;
	
	/**
	 * The local of the recording session.
	 */
	Local local;
	
	/**
	 * An enumeration type for the recording status.
	 */
	private RecordingStatusEnum status;
	
	/**
	 * Flag for recording suspension.
	 */
	private boolean suspended;
	
	/**
	 * Adds a new recording to the recordings' schedule.
	 * @param users collaborators participating in the recording.
	 * @param date initial date of the recording.
	 * @param duration duration of the recording.
	 */
	public RecordingClass(Local local, Array<User> users, LocalDateTime date, int duration){
		this.local = local;
		collabs = users;
		initialDate = date;
		this.duration = duration;
		suspended = false;
		status = RecordingStatusEnum.SCHEDULED;
		addToCollabsCalendar(users, date, duration);
	}
	
	@Override
	public Local getLocal() {
		return local;
	}
	
	@Override
	public LocalDateTime getStartDate() {
		return initialDate;
	}

	@Override
	public int getDuration() {
		return duration;
	}
	
	@Override
	public LocalDateTime getEndDate() {
		return getStartDate().plusMinutes(getDuration());
	}
	
	@Override
	public RecordingStatusEnum getStatus() {
		return status;
	}
		
	@Override
	public boolean isSuspended() {
		return suspended;
	}

	@Override
	public void toggleSuspension() {
		if(suspended)
			suspended = false;	//not suspended
		else
			suspended = true;
	}

	@Override
	public void changeRecordingStatus(RecordingStatusEnum status) {
		this.status = status;
	}

	@Override
	public Array<User> getCollabs() {
		return collabs;
	}
	
	@Override
	public boolean equals(Recording obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof RecordingClass))	
			return false;
		RecordingClass other = (RecordingClass) obj;
		
		if (collabs == null) {
			if (other.getCollabs() != null)
				return false;
		} else {
			if (local == null) {
				if (other.getLocal() != null)
					return false;
			} else {
				if (this.getStartDate() == null) {
					if (other.getStartDate() != null)
						return false;
				}else {
					if (this.getEndDate() == null) {
						if (other.getEndDate() != null)
							return false;
					}else {
						if(other.getStartDate().isAfter(this.getStartDate()) && other.getStartDate().isBefore(this.getEndDate())) {	//time collision
							Iterator<User> it = other.getCollabs().iterator();	//iterator of collabs of the other recording
							while(it.hasNext()) {
								User u = it.next();
								if(collabs.searchIndexOf(u) >= 0)	//share at least one collaborator
								 	return true;	
							}
							//collabs not shared
							if (!local.getName().equals(other.getLocal().getName()))	//time collision, local not shared and collabs not shared
								return false;
						}
						else {
							if(other.getStartDate().isEqual(this.getStartDate()) && local.getName().equals(other.getLocal().getName()))
								return true;
						}
					}
				}
			}	
		}
		return true;		
	}

	
	@Override
	public Producer getProducer() {
		Iterator<User> it = collabs.iterator();
		User u = null;
		while(it.hasNext())
			u = it.next();
			if(u instanceof Producer)
				return (Producer)u;
		return null;
	}

//	@Override
//	public boolean hasCollab(String username) {
//		for(int i = 0; i < collabs.size(); i++)
//			if(collabs.get(i).getName().equals(username))
//				return true;
//		return false;
//	}

	@Override
	public LocalDateTime getCollabsMaxTime() {
		Iterator<User> it = getCollabs().iterator();
		LocalDateTime maxStart = null;
		if(it.hasNext()) {
			maxStart = getMaxAvailableTime(it.next());
			while(it.hasNext()) {
				User collab = it.next();
				LocalDateTime date = getMaxAvailableTime(collab);
				if(date.isAfter(maxStart))
						maxStart = getMaxAvailableTime(collab);
			}
		}
		return maxStart;
	}
	
	/**
	 * Return the next available schedule for the collaborator <code>u</code> or null if there is no appointments.
	 * @param u a collaborator.
	 * @return the next available schedule for the collaborator <code>u</code> or null if there is no appointments.
	 */
	private LocalDateTime getMaxAvailableTime(User u) {
		Iterator <LocalDateTime> it = u.listAppointments();
		LocalDateTime maxStart = null;
		if(it.hasNext()) {
			maxStart = it.next();
			while(it.hasNext()) {
				LocalDateTime date = it.next();
				if(date.isAfter(maxStart))
					maxStart = date;
			}
		}
		return maxStart;
	}
	
//	@Override
//	public boolean isCollabAvailable(User u, LocalDateTime start, LocalDateTime end) {
//		// TODO Auto-generated method stub
//		return false;
//	}
	
	/**
	 * Adds recording schedule to the participating collaboratiors's calendar,
	 * @param collabs collaborators participating in the recording.
	 */
	private void addToCollabsCalendar(Array<User> collabs, LocalDateTime start, int duration) {	
		for(int i = 0; i < collabs.size(); i++) {
			collabs.get(i).addAppointment(start, duration);
		}
	}

	@Override
	public void rescheduleRecording(LocalDateTime date) {
		this.initialDate = date;
	}
	
	/*private Locations locations;
	private Array<Record> recordings = new ArrayClass<>();
	private Array<User> users = new ArrayClass<>();

	public RecordingsClass(Locations locations) {
		this.locations = locations;
	}
	
	public ErrorEnum Record(Local local, LocalDateTime initialDate, int duration, Producer prod, Director dir,
			Technician tech, Array<User> collabs) {

		
		//se produtor existe		
		if (prod == null && !userExists(prod))
			return ErrorEnum.UNKNOWN_PRODUCER;
		//se realizador existe
		if (dir == null && !userExists(dir))
			return ErrorEnum.UNKNOWN_DIRECTOR;
		//se o tecnico existe
		if (tech == null && !userExists(tech))
			return ErrorEnum.UNKNOWN_TECH;
		
		//se colaboradores existem

		// caso 8 verificacao; agendada mas suspensa
		boolean starConflict = false;
		if (dir instanceof DirectorStarClass) {
			DirectorStarClass dirStar = (DirectorStarClass) dir;

			if (dirStar.isUserOnBlackList(prod) || dirStar.isUserOnBlackList(tech)
					|| dirStar.isUserOnBlackList(collabs)) {

				starConflict = true;
			}
		}

		Iterator<User> it = collabs.iterator();
		while (it.hasNext()) {
			User user = it.next();
			
			if (user instanceof StarClass) {
				StarClass star = (StarClass) user;

				if (star.isUserOnBlackList(prod) || star.isUserOnBlackList(tech)
						|| star.isUserOnBlackList(collabs)) {

					starConflict = true;
					break;
				}
			}
		}
		
		//caso 9 produtor nao tem prioridades
		
		Iterator<Record> it2 = recordings.iterator();
		while(it2.hasNext()){
			Record record = it2.next();
			
			if(local.equals(record.getLocal()) 
			&& isOverlapping(initialDate, duration, record.getDate(), record.getDuration())
			&& prod instanceof JuniorProducerClass 
			&& record.getProducer() instanceof SeniorProducerClass){
				
				
				
				return ErrorEnum.DATA_CONFLICT;
			}
		}
		//caso10 produtor tem prioridade
		Iterator<Record> it3 = recordings.iterator();
		while(it3.hasNext()){
			Record record = it3.next();
			
			if(record.getProducer() instanceof SeniorProducerClass)
				return ErrorEnum.IMPENDING_RECORD_CHANGES;
		
		
		// criar e gravar record.
			
			
		
		if(starConflict)
			return ErrorEnum.RECORD_STAR_PENDENT;
		else
			return ErrorEnum.OK;

	}

	private LocalDateTime getDateLastRecDone() {
		LocalDateTime max = null;

		Iterator<Record> it = recordings.iterator();
		while (it.hasNext()) {
			Record r = it.next();

			if (r.IsDone() && (max == null || r.getDate().compareTo(max) > 0)) {
				max = r.getDate();
			}
		}

		return max;
	}

	
	private boolean isOverlapping(LocalDateTime data1, int duration1, LocalDateTime data2, int duration2){
		LocalDateTime end1= data1.plusMinutes(duration1);
		LocalDateTime end2= data2.plusMinutes(duration2);
		
		if (end1.isBefore(data2) || end2.isBefore(data1))
			return false;
		else 
			return true;
		
	}
*/



}
