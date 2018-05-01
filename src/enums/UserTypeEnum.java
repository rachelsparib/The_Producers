package enums;

/**
 * An enumeration type for types of collaborators.
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 *
 */
public enum UserTypeEnum {
	/**
	 * Enumeration constants and its values.
	 */
	ACTOR			("actor"),
	DIRECTOR		("realizador"),
	TECHNICIAN		("tecnico"),
	PRODUCER		("produtor"),
	JUNIOR			("junior"),
	SENIOR			("senior"),
	UNKNOWN			("");
	
	/**
	 * Stores the name of the type of collaborator.
	 */
	private String name;
	
	/**
	 * Creates an object of the enumeration type.
	 * @param type type of collaborator.
	 */
	private UserTypeEnum(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the name of the type of collaborator.
	 * @return name of the type of collaborator.
	 */
	public String getName() {
		return name;
	}
	
	
	/**
	 * Returns UserTypeEnum constant with name <code>name</code> if exists in the enum type, or <code>null</code> otherwise.
	 * @param name name of the type of collaborator.
	 * @return UserTypeEnum constant if exists in the enum type, or <code>null</code> otherwise.
	 */
	public static UserTypeEnum getValue(String name) {
		for(UserTypeEnum userType : UserTypeEnum.values())
			if(userType.getName().equalsIgnoreCase(name))
				return userType;
		return null;
	}
	
}
