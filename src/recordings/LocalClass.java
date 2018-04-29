package recordings;

/**
 * An implementation of the interface Local.
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 *
 */
public class LocalClass implements Local {
	
	/**
	 * Stores local's name.
	 */
	private String name;
	
	/**
	 * Stores local's hourly cost.
	 */
	private int cost;
	
	/**
	 * Creates a new local for recordings.
	 * @param name name of the local.
	 * @param cost hourly cost of the local.
	 */
	public LocalClass(String name, int cost) {
		this.name = name;
		this.cost = cost;
	}
	
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getCost() {
		return cost;
	}
	
	@Override
	public String toString() {
		return getName() + " " + getCost() + ".";
	}
}
