import java.time.LocalDateTime;
import java.util.Scanner;
import enums.*;
import productions.*;
import util.*;
import users.User;
import recordings.Local;
import recordings.Recording;

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
					addSet(in, p);
					break;
				case SETS:
					listSets(p);
					break;
				case SCHEDULE:
					schedule(in, p);
					break;
				case GRUMPY:
					//grumpy(in, p);
					break;
				case RECONCILE:
					//reconcile(in, p);
					break;
				case PERFORMED:
					//performed(p);
					break;
				case PLANNED:
					planned(p);
					break;
				case PLACES:
					//
					break;
				case COLLABORATOR:
					//collaborator(in, p);
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
	
	/**
	 * Auxiliary method to register a new collaborator.
	 * @param in data input stream.
	 * @param p an audiovisual Production.
	 */
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
			
		hourlyCost = in.nextInt();in.skip(" ");
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
	
	/**
	 * Auxiliary method to list all collaborators of an audiovisual Production.
	 * @param p an audiovisual Production.
	 */
	private static void listUsers(Production p) {
		Iterator<User> it = p.listUsers();
		if(!it.hasNext())
			System.out.println(MessagesEnum.USERLIST_EMPTY);
		else
			while(it.hasNext())
				System.out.println(it.next());
	}
	
	/**
	 * Auxiliary method to register a new set for recordings.
	 * @param in data input stream.
	 * @param p an audiovisual Production.
	 */
	private static void addSet(Scanner in, Production p) {
		String localname = in.nextLine();
		int localcost = in.nextInt();
		in.nextLine();
		
		if(p.hasLocal(localname))
			System.out.println(MessagesEnum.INVALID_LOCALNAME);
		else {
			if(localcost < 0)
				System.out.println(MessagesEnum.INVALID_LOCALCOST);
			else {
				p.addLocal(localname, localcost);
				System.out.println(MessagesEnum.LOCAL_SUCCESS);
			}	
		}		
	}
	
	/**
	 * Auxiliary method to list all sets for recordings.
	 * @param p an audiovisual Production.
	 */
	 private static void listSets(Production p) {
		Iterator<Local> it = p.listLocals();
		if(!it.hasNext())
			System.out.println(MessagesEnum.LOCALIST_EMPTY);
		else
			while(it.hasNext())
				System.out.println(it.next());
	 }
	 
	 /**
	  * Auxiliary method to schedule a new recording.
	  * @param in data input stream.
	  * @param p an audiovisual Production.
	  */
	 private static void schedule(Scanner in, Production p) {
		 String localname = in.nextLine();
		 int year = in.nextInt(); 
		 int month = in.nextInt();
		 int dayOfMonth = in.nextInt();
		 int hour = in.nextInt();
		 int minutes = in.nextInt();
		 int duration = in.nextInt(); in.nextLine();
		 String producerName = in.nextLine();
		 String directorName = in.nextLine();
		 String technicianName = in.nextLine();
		 int collabs = in.nextInt(); in.nextLine();
		 Array<String> collabsName = null;			// for storing each extra collabName
		 
		 for(int i = 0; i < collabs; i++) {	
			 collabsName = new ArrayClass<String>();
			 collabsName.insertLast(in.nextLine());
		 }
		 
		 LocalDateTime start = LocalDateTime.of(year, month, dayOfMonth, hour, minutes);
		 
		 if(!p.hasLocal(localname))	// level 1
			 System.out.println(MessagesEnum.UNKNOWN_LOCAL);
		 else {
			 if(p.isBeforeLastRecorded(start)) 	// level 2
				 System.out.println(MessagesEnum.INVALID_DATE);
			 else {
				 if(duration < 0)		// level 3
					 System.out.println(MessagesEnum.INVALID_DURATION);
				 else {
					 if(!p.hasUser(producerName))	// level 4
						 System.out.println(MessagesEnum.UNKNOWN_PRODUCER);
					 else {
						 if(!p.hasUser(directorName))		// level 5
							 System.out.println(MessagesEnum.UNKNOWN_DIRECTOR);
						 else {
							 if(!p.hasUser(technicianName))		// level 6
								 System.out.println(MessagesEnum.UNKNOWN_TECHNICIAN);
							 else {	
								if(p.hasUserCollection(collabsName))		//level 7
									System.out.println(MessagesEnum.UNKNOWN_COLLAB);
								else {
									collabsName.insertLast(directorName); //to check blacklist conflicts with the collection
									collabsName.insertLast(producerName);
									collabsName.insertLast(technicianName);
									if(p.hasBlacklistConflict(collabsName) && !p.hasRecording(localname, start)) {	// level 8 
										System.out.println(MessagesEnum.RECORD_STAR_PENDENT);
										p.addRecording(localname, collabsName, start, duration);
										if(!p.isRecordingSuspended(localname, start)) // suspends recording
												p.toggleRecordingSuspension(localname, start);		
									}else {
										if(p.hasRecordingConflict(localname, start) && !p.hasProducerPriority(localname, start)) {		//level 9
											System.out.println(MessagesEnum.RECORD_CONFLICT);											
										}else {
											if(p.hasRecordingConflict(localname, start) && p.hasProducerPriority(localname, start) && !p.hasRecording(localname, start)) { //level 10
												p.rescheduleRecording(p.getConflictingRecording(localname, start));
												p.addRecording(localname, collabsName, start, duration);
												System.out.println(MessagesEnum.RECORD_CAUSED_RESCHED);
											}else {
												p.addRecording(localname, collabsName, start, duration);		// success
												System.out.println(MessagesEnum.RECORD_ADDED_SUCCESS);
											}
										}
									}
								 }
							 }
						 }
					 }
				 }
			 }
		 }
	 }
	 
	 /**
	  * Auxiliary method for dealing with the situation in which a star gets upset with a collaborator.
	  * @param in data input stream.
	  * @param p an audiovisual Production.
	  */
	 private static void grumpy(Scanner in, Production p) {
		 String starname = in.nextLine();
		 String collabname = in.nextLine();
		 if(!p.hasStar(starname))
			 System.out.println(starname + MessagesEnum.INVALID_STAR);
		 else {
			 if(!p.hasUser(collabname))
				 System.out.println(collabname + MessagesEnum.INVALID_COLLAB);
			 else {
				 if(p.hasCollabInBlacklist(starname, collabname))
					 System.out.println(MessagesEnum.INVALID_ADD_BLACKLIST);
				 else {
					 int num =  p.addCollabToBlacklist(starname, collabname);
					 System.out.println(starname + " colocou " + collabname + " na sua lista negra, suspendendo " + num + " gravacoes.");
				 }

			 }
		 }
	 }
	 
	 /**
	  * Auxiliary method for dealing with the situation in which a star reconciles with a collaborator.
	  * @param in data input stream.
	  * @param p an audiovisual Production.
	  */
	 private static void reconcile(Scanner in, Production p) {
		 String starname = in.nextLine();
		 String collabname = in.nextLine();
		 if(!p.hasStar(starname))
			 System.out.println(starname + MessagesEnum.INVALID_STAR);
		 else {
			 if(p.hasCollabInBlacklist(starname, collabname) || !p.hasUser(collabname))
					 System.out.println(MessagesEnum.NOT_IN_BLACKLIST + collabname);
			 else {
				 int num =  p.removeCollabOfBlacklist(starname, collabname);
				 System.out.println(starname + " <3 " + collabname + ". " + num + " gravacoes salvas!");
			 }

		 }
	 }
		

	 /*
	 private static void performed(Production p){
 		 Iterator<Recording> recording = p.listRecordingsByStatus(RecordingStatusEnum.DONE);
 		 if (!recording.hasNext()) {
 			 System.out.println(MessagesEnum.RECORD_EMPTY_DONE);
 		 } else {
 			 while(recording.hasNext()){
 				Recording record = recording.next();
 				LocalDateTime date = record.getStartDate();
 				
 				System.out.format("%d %d %d; %s; %s; %s.", date.getYear(), date.getMonth(), date.getDayOfMonth(), record.getLocal().getName(), record.getProducer().getName(), record.getDirector().getName());
 			 }
 		 }
 	}
 	*/
 		 
	private static void planned(Production p) {
 		 Iterator<Recording> it = p.listRecordingsByStatus(RecordingStatusEnum.SCHEDULED);
 		 if (!it.hasNext()) {
 			 System.out.println(MessagesEnum.RECORD_EMPTY_SCHEDULE);
 		 } else {
 			 while(it.hasNext()){
 				Recording rec = it.next();
 				LocalDateTime date = rec.getStartDate();
 				
 				System.out.format("%d %d %d; %s; %s; %s.", date.getYear(), date.getMonth(), date.getDayOfMonth(), rec.getLocal().getName(), rec.getProducer().getName(), rec.getDirector().getName());
 			 }
 		 }
	}
	/*
	private static void places(Scanner in, Production p) {
		String local = in.nextLine();
		Local l = p.getLocal(local);
		if (l == null) {
			System.out.println(MessagesEnum.UNKNOWN_LOCAL);
		} else {
			float totalCost = 0;

			Iterator<Recording> it = p.listRecordingsByStatus(RecordingStatusEnum.SCHEDULED);

			if (!it.hasNext()) {
				System.out.format("Nenhuma gravacao prevista em %s.\n", l.getName());
			} else {
				while (it.hasNext()) {
					Recording record = it.next();
					LocalDateTime date = record.getStartDate();

					System.out.format("%d %d %d; %s; %s.\n", date.getYear(), date.getMonth(), date.getDayOfMonth(),
							record.getProducer().getName(), record.getDirector().getName());

					totalCost += record.getTotalCost();
				}

				int totalCostInt = Math.round(totalCost);
				System.out.format("%d euros orcamentados.\n");

			}
		}
	}
	*/
	/*
	private static void collaborator(Scanner in, Production p) {
		String name = in.nextLine();
		User collab = p.getUser(name);
		if (collab == null) {
			System.out.println(MessagesEnum.UNKNOWN_COLLAB);
		} else {
				Iterator<Recording> it = p.getRecCollectionByUser(collab);
			if (!it.hasNext())
				System.out.format("Nenhuma gravacao prevista com %s.\n", collab.getName());
			else {
				while (it.hasNext()) {
					Recording record = it.next();
					if (record.hasCollab(collab.getName())) {
						LocalDateTime date = record.getStartDate();

						System.out.format("%d %d %d; %s; %s.\n", date.getYear(), date.getMonth(), date.getDayOfMonth(),
								record.getProducer().getName(), record.getDirector().getName());
					}
				}
			}

		}
 	}
	*/ 
	
	/**
	 * Main program.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
		Main.commandInterpreter();
	}

}
