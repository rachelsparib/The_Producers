package recordings;

import java.time.LocalDateTime;

import users.Producer;

public interface Record {

	public Local getLocal();
	
	public LocalDateTime getDate();
	
	public Producer getProducer();
	
	public boolean isDone();
	
	public boolean isPending();
	
	public int getDuration();
}
