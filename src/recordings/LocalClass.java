package recordings;

public class LocalClass implements Local {
	//constants
	
	
	//instance variables
	/**
	 * Stores local's name.
	 */
	private String name;
	
	/**
	 * Stores local's hourly cost.
	 */
	private int cost;
	
	
	//constructor
	/**
	 * Registers a new local for recordings.
	 * @param name - name of the local.
	 * @param cost - hourly cost of the local.
	 */
	public LocalClass(String name, int cost) {
		this.name = name;
		this.cost = cost;
	}
	
	
	//public methods
	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getCost() {
		return cost;
	}

}
