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
	INVALID_OPTION				("Opcao inexistente."),
	REGISTER_SUCCESS 			("Colaborador registado com sucesso!"),
	INVALID_USERNAME			("Ja existe um colaborador com o mesmo nome."),
	INVALID_USERTYPE			("Tipo de colaborador desconhecido."),
	INVALID_STARTYPE			("Notoriedade invalida."),
	INVALID_HOURRATE			("Acha mesmo que este colaborador vai pagar para trabalhar?"),
	USERLIST_EMPTY				("Nao existem colaboradores registados."),
	INVALID_LOCALNAME			("Localizacao ja tinha sido registada."),
	INVALID_LOCALCOST			("Acha que eles nos pagam para gravar la?"),
	LOCAL_SUCCESS				("Cenario registado."),
	LOCALIST_EMPTY				("Nao existem localizacoes registadas."),
	UNKNOWN_LOCAL 				("Local desconhecido."),
	INVALID_DATE 				("Data de gravacao invalida."),
	INVALID_DURATION 			("Duracao invalida."),
	UNKNOWN_PRODUCER			("Produtor desconhecido."),
	UNKNOWN_DIRECTOR			("Realizador desconhecido."),
	UNKNOWN_TECHNICIAN			("Tecnico desconhecido."),
	UNKNOWN_COLLAB				("Colaborador desconhecido."),
	
	RECORD_STAR_PENDENT			("Gravacao pendente de uma birra."),
	DATE_CONFLICT				("Gravacao nao agendada por conflito de datas."),
	IMPENDING_RECORD_CHANGES	("Gravacao prioritaria agendada provocou mudancas noutra(s) gravacao(oes)."),
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
