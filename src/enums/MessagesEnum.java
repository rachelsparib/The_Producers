	package enums;

/**
 * An enumeration type for user messages.
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 *
 */
public enum MessagesEnum {
	/**
	 * Enumeration constants and its values.
	 */
	INVALID_OPTION				("Opcao inexistente"),
	REGISTER_SUCCESS 			("Colaborador registado com sucesso!"),
	INVALID_USERNAME			("Ja existe um colaborador com o mesmo nome."),
	INVALID_USERTYPE			("Tipo de colaborador desconhecido."),
	INVALID_STARTYPE			("Notoriedade invalida."),
	INVALID_HOURRATE			("Acha mesmo que este colaborador vai pagar para trabalhar?"),
	USERLIST_EMPTY				("Nao existem colaboradores registados."),
	
	UNKNOWN_LOCAL 				("Local desconhecido.\n"),
	INVALID_DATE 				("Data de gravacao invalida.\n"),
	INVALID_DURATION 			("Duracao invalida.\n"),
	UNKNOWN_PRODUCER			("Produtor desconhecido.\n"),
	UNKNOWN_DIRECTOR			("Realizador desconhecido.\n"),
	UNKNOWN_TECH				("Tecnico desconhecido.\n"),
	UNKNOWN_COLLAB				("Colaborador desconhecido.\n"),
	RECORD_STAR_PENDENT			("Gravacao pendente de uma birra.\n"),
	DATE_CONFLICT				("Gravacao nao agendada por conflito de datas.\n"),
	IMPENDING_RECORD_CHANGES	("Gravacao prioritaria agendada provocou mudancas noutra(s) gravacao(oes).\n"),
	EXIT 						("Ate a proxima"),
	OK							("");
		
	/**
	 * Stores the  description for each constant.
	 */
	private String description;
	
	/**
	 * Creates an object of the enumeration type.
	 * @param description message's description.
	 */
	private MessagesEnum(String description){
		this.description = description;
	}
	
	/**
	 * Returns the message's description.
	 * @return message's description.
	 */
	public String toString() {
		return description;
}
}
