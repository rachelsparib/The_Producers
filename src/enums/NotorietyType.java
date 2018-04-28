package enums;

/**
 * An enumeration for types of notoriety.
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 *
 */
public enum NotorietyType {
	/**
	 * Enumeration constants and its values.
	 */
	STAR			("vedeta"),
	NORMAL			("normal");
	
	/**
	 * Stores the name for each constant.
	 */
	private String type;
	
	/**
	 * Creates an object of the enumeration type.
	 * @param type type of notoriety.
	 */
	private NotorietyType(String type) {
		this.type = type;
	}
	
	/**
	 * Returns the name of the type of notoriety.
	 * @return name of the type of notoriety.
	 */
	public String getName() {
		return type;
	}
}
