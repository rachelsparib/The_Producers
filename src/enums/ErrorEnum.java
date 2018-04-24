package enums;

public enum ErrorEnum {
	/**
	 * Enumeration constants and its values.
	 */
	UNKNOWN_LOCAL 				("Local desconhecido.\n"),
	INVALID_DATA 				("Data de gravacao invalida.\n"),
	INVALID_DURATION 			("Duracao invalida.\n"),
	UNKNOWN_PRODUCER			("Produtor desconhecido.\n"),
	UNKNOWN_DIRECTOR			("Realizdor desconhecido.\n"),
	UNKNOWN_TECH				("Tecnico desconhecido.\n"),
	UNKNOWN_COLLAB				("Colaborador desconhecido.\n"),
	RECORD_STAR_PENDENT			("Gravacao pendente de uma birra.\n"),
	DATA_CONFLICT				("Gravacao nao agendada por conflito de datas.\n"),
	IMPENDING_RECORD_CHANGES	("Gravacao prioritaria agendada provocou mudancas noutra(s) gravacao(oes).\n"),
	OK							("");
	
	
	
	/**
	 * Stores the command description for each constant.
	 */
	private String description;
	
	/**
	 * Creates an object of the enumeration type.
	 * @param description - command description.
	 */
	private ErrorEnum(String description){
		this.description = description;
	}
	
	/**
	 * Returns the command description.
	 * @return command description.
	 */
	public String toString() {
		return description;
	}
}
