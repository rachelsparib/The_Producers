package enums;

/**
 * An enumeration type for main program commands.
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 *
 */
public enum CommandEnum {
	/**
	 * Enumeration constants and its values.
	 */
	REGISTER 		("REGISTA", 	"regista - regista um novo colaborador\n"),
	STAFF 			("STAFF", 		"staff - lista os colaboradores registados\n"),
	SET 			("CENARIO", 	"cenario - regista um novo local para gravacoes\n"),
	SETS 			("CENARIOS",	"cenarios - lista os locais para gravacoes registados\n"),
	SCHEDULE		("MARCA", 		"marca - marca uma nova gravacao\n"),
	GRUMPY 			("AMUA", 		"amua - vedeta deixa de trabalhar com colaborador\n"),
	RECONCILE 		("RECONCILIA",	"reconcilia - vedeta faz as pazes com colaborador\n"),
	PERFORMED 		("REALIZADAS", 	"realizadas - lista as gravacoes realizadas\n"), 
	PLANNED			("PREVISTAS", 	"previstas - lista as gravacoes previstas\n"), 
	PLACES			("LOCAL", 		"local - lista as gravacoes previstas para um local\n"),
	COLABORATOR 	("COLABORADOR", "colaborador - lista as gravacoes previstas para um colaborador\n"),
	RECORD			("GRAVA", 		"grava - executa a proxima gravacao agendada\n"),
	GRUMPIES 		("AMUANCOS", 	"amuancos - lista os colaboradores com quem uma vedeta esta amuada\n"), 
	HELP 			("AJUDA", 		"ajuda - Mostra a ajuda\n"), 
	QUIT 			("SAI", 		"sai - Termina a execucao do programa\n"),
	UNKNOWN 		("",			"");
	
	/**
	 * Stores the command description for each constant.
	 */
	private String description;
	
	/**
	 * Stores the command name for each constant.
	 */
	private String name;
	
	/**
	 * Creates an object of the enumeration type.
	 * @param name - command name.
	 * @param description - command description.
	 */
	private CommandEnum(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	/**
	 * Returns the command name.
	 * @return command name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the command description.
	 * @return command description.
	 */
	public String toString() {
		return description;
	}
	
	/**
	 * Returns CommandEnum constant with name <code>name</code> if exists in the enum type, or <code>null</code> otherwise.
	 * @param name - name of constant.
	 * @return CommandEnum constant if exists in the enum type, or <code>null</code> otherwise.
	 */
	public static CommandEnum getValue(String name) {
		for(CommandEnum comm : CommandEnum.values())
			if(comm.getName().equalsIgnoreCase(name))
				return comm;
		return null;
	}

}
