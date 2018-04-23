import java.util.Scanner;

import users.CommandEnum;

/**
 * Main program for demonstrating the management of audiovisual productions.
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 *
 */
public class Main {
	//constants for user messages
	public static final String INVALID = "Opcao inexistente";
	public static final String EXIT = "Ate a proxima";
	
	//auxiliary methods
	/**
	 * Auxialiary method to get command input.
	 * @param in - data input stream.
	 * @return an enum type constant for the command input. 
	 */
	private static CommandEnum getCommand(Scanner in) {
		CommandEnum comm = CommandEnum.getValue(in.nextLine().toUpperCase());
		if(comm == null)
			return CommandEnum.UNKNOWN;
		else
			return comm;
	}
	
	private static void help() {
		for(CommandEnum comm : CommandEnum.values())
			System.out.print(comm);
	}
	
	/**
	 * Auxiliary method for treating command input.
	 */
	private static void commandInterpreter() {
		Scanner in = new Scanner(System.in);
		
		System.out.print("> ");
		CommandEnum comm = Main.getCommand(in);
		
		while(!comm.equals(CommandEnum.QUIT)) {
			switch(comm) {
				case REGISTER:
					//
					break;
				case STAFF:
					//
					break;
				case SET:
					//
					break;
				case SETS:
					//
					break;
				case SCHEDULE:
					//
					break;
				case GRUMPY:
					//
					break;
				case RECONCILE:
					//
					break;
				case PERFORMED:
					//
					break;
				case PLANNED:
					//
					break;
				case PLACES:
					//
					break;
				case COLABORATOR:
					//
					break;
				case RECORD:
					//
					break;
				case GRUMPIES:
					//
					break;
				case HELP: 
					help();
					break;
				case UNKNOWN:
					System.out.println(INVALID);
					break;
				default:	//does nothing
					break;
			}
			System.out.print("> ");
			comm = Main.getCommand(in);
		} 
		
		System.out.println(EXIT);
				
		in.close();
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
		Main.commandInterpreter();
	}

}
