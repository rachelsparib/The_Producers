package recordings;

import java.time.LocalDateTime;

import enums.RecordingStatusEnum;
import users.User;
import util.Array;

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
	public RecordingClass(Array<User> users, LocalDateTime date, int duration){
		collabs = users;
		initialDate = date;
		this.duration = duration;
		suspended = false;
		status = RecordingStatusEnum.SCHEDULED;
	}
	
	@Override
	public LocalDateTime getStartDate() {
		return initialDate;
	}

	@Override
	public LocalDateTime getEndDate() {
		return initialDate.plusMinutes(duration);
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

	private boolean userExists(User user) {
		Iterator<User> it = users.iterator();
		while (it.hasNext()) {
			User u = it.next();

			if (u.equals(user))
				return true;
		}

		return false;
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
