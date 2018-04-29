package enums;

/**
 * An enumeration for types of collaborators's notoriety.
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 *
 */
public enum NotorietyTypeEnum {
	/**
	 * Enumeration constants and its values.
	 */
	STAR			("vedeta"),
	NORMAL			("normal");
	
	/**
	 * Stores the name of the type of notoriety.
	 */
	private String name;
	
	/**
	 * Creates an object of the enumeration type.
	 * @param type type of notoriety.
	 */
	private NotorietyTypeEnum(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the name of the type of notoriety.
	 * @return name of the type of notoriety.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns NotorietyTypeEnum constant with name <code>name</code> if exists in the enum type, or <code>null</code> otherwise.
	 * @param name of the type of notoriety.
	 * @return NotorietyTypeEnum constant if exists in the enum type, or <code>null</code> otherwise.
	 */
	public static NotorietyTypeEnum getValue(String name) {
		for(NotorietyTypeEnum notorietyType : NotorietyTypeEnum.values())
			if(notorietyType.getName().equalsIgnoreCase(name))
				return notorietyType;
		return null;
	}
	

}
