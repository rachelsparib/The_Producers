import java.util.Scanner;
import enums.*;
import productions.*;
import util.Iterator;
import users.User;

/**
 * Main program to demonstrate the management of audiovisual productions.
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 *
 */
public class Main {

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
	
	/**
	 * Auxiliary method to exhibit command descriptions.
	 */
	private static void help() {
		for(CommandEnum comm : CommandEnum.values())
			System.out.print(comm);
	}
	
	/**
	 * Auxiliary method for treating command input.
	 */
	private static void commandInterpreter() {
		Scanner in = new Scanner(System.in);
		Production p = new ProductionClass();
		System.out.print("> ");
		CommandEnum comm = Main.getCommand(in);
		
		while(!comm.equals(CommandEnum.QUIT)) {
			switch(comm) {
				case REGISTER:
					addUser(in, p);
					break;
				case STAFF:
					listUsers(p);
					break;
				case SET:
					//setLocal(in, production);
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
				case COLLABORATOR:
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
					System.out.println(MessagesEnum.INVALID_OPTION);
					break;
				default:	//does nothing
					break;
			}
			System.out.print("> ");
			comm = Main.getCommand(in);
		} 
		
		System.out.println(MessagesEnum.EXIT);
				
		in.close();
	}
	
	private static void addUser(Scanner in, Production p) {
		int hourlyCost;
		String username;
		
		UserTypeEnum userEnum = UserTypeEnum.getValue(in.next());
		if(userEnum == null)
			userEnum = UserTypeEnum.UNKNOWN;
		
		NotorietyTypeEnum notorietyEnum;
		
		
		if(userEnum.equals(UserTypeEnum.ACTOR) || userEnum.equals(UserTypeEnum.DIRECTOR)) {
			notorietyEnum = NotorietyTypeEnum.getValue(in.next()); // null if it isn't an actor or director
			if(notorietyEnum == null)
				notorietyEnum = NotorietyTypeEnum.UNKNOWN;
		}
		else
			notorietyEnum = NotorietyTypeEnum.NORMAL;
			
		hourlyCost = in.nextInt();
		
		username = in.nextLine();
		if(p.hasUser(username))
			System.out.println(MessagesEnum.INVALID_USERNAME);
		else {
			if(userEnum.equals(UserTypeEnum.UNKNOWN))
				System.out.println(MessagesEnum.INVALID_USERTYPE);
			else {	
				if(notorietyEnum.equals(NotorietyTypeEnum.UNKNOWN))								
					System.out.println(MessagesEnum.INVALID_STARTYPE);
				else {
					if(hourlyCost < 0)
						System.out.println(MessagesEnum.INVALID_HOURRATE);
					else {
						p.addUser(userEnum, notorietyEnum, hourlyCost, username);
						System.out.println(MessagesEnum.REGISTER_SUCCESS);
					}
				}
			}
		}
		
	}
	
	private static void listUsers(Production p) {
		Iterator<User> it = p.listUsers();
		if(!it.hasNext())
			System.out.println(MessagesEnum.USERLIST_EMPTY);
		else
			while(it.hasNext())
				System.out.println(it.next().toString()); 	//TODO toString
	}
	
	/**
	 * Main program.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
		Main.commandInterpreter();
	}

}
