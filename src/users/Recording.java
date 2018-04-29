package recordings;

import java.time.LocalDateTime;

import enums.RecordingStatusEnum;


/**
 * A recording of an audiovisual Production.
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 *
 */
public interface Recording {
	
	/**
	 * Returns date of the beginning of the recording.
	 * @return date of the beginning of the recording.
	 */
	LocalDateTime getStartDate();
	
	/**
	 * Returns date of the ending of the recording.
	 * @return date of the ending of the recording.
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
	
}
