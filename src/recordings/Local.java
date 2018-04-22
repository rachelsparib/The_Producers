package recordings;

/**
 * A local of a recording.
 * 
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 *	
 *
 */
public interface Local {
	/**
	 * Returns the name of a local.
	 * @return name of the local.
	 */
	String getName();
	
	/**
	 * Returns the hourly cost of the local.
	 * @return hourly cost.
	 */
	int getCost();
}
