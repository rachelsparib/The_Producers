import java.time.LocalDateTime;
import java.util.Scanner;
import enums.*;
import productions.*;
import util.*;
import users.Producer;
import users.User;
import recordings.Local;
import recordings.Recording;

/**
 * Main program to demonstrate the management of audiovisual productions.
 * 
 * @author Antonio Santos 49055 / Raquel Pena 45081.
 *
 */
public class Main {

	// auxiliary methods
	/**
	 * Auxialiary method to get command input.
	 * 
	 * @param in
	 *            - data input stream.
	 * @return an enum type constant for the command input.
	 */
	private static CommandEnum getCommand(Scanner in) {
		CommandEnum comm = CommandEnum.getValue(in.nextLine().toUpperCase());
		if (comm == null)
			return CommandEnum.UNKNOWN;
		else
			return comm;
	}

	/**
	 * Auxiliary method to exhibit command descriptions.
	 */
	private static void help() {
		for (CommandEnum comm : CommandEnum.values())
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

		while (!comm.equals(CommandEnum.QUIT)) {
			switch (comm) {
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
				grumpy(in, p);
				break;
			case RECONCILE:
				reconcile(in, p);
				break;
			case PERFORMED:
				performed(p);
				break;
			case PLANNED:
				planned(p);
				break;
			case PLACES:
				places(in, p);
				break;
			case COLLABORATOR:
				collaborator(in, p);
				break;
			case RECORD:
				record(p);
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
			default: // does nothing
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
	 * 
	 * @param in
	 *            data input stream.
	 * @param p
	 *            an audiovisual Production.
	 */
	private static void addUser(Scanner in, Production p) {
		int hourlyCost;
		String username;

		UserTypeEnum userEnum = UserTypeEnum.getValue(in.next());
		if (userEnum == null)
			userEnum = UserTypeEnum.UNKNOWN;

		NotorietyTypeEnum notorietyEnum;

		if (userEnum.equals(UserTypeEnum.ACTOR) || userEnum.equals(UserTypeEnum.DIRECTOR)) {
			notorietyEnum = NotorietyTypeEnum.getValue(in.next()); // null if it
																	// isn't an
																	// actor or
																	// director
			if (notorietyEnum == null)
				notorietyEnum = NotorietyTypeEnum.UNKNOWN;
		} else
			notorietyEnum = NotorietyTypeEnum.NORMAL;

		hourlyCost = in.nextInt();
		in.skip(" ");
		username = in.nextLine();

		if (p.hasUser(username))
			System.out.println(MessagesEnum.INVALID_USERNAME);
		else {
			if (userEnum.equals(UserTypeEnum.UNKNOWN))
				System.out.println(MessagesEnum.INVALID_USERTYPE);
			else {
				if (notorietyEnum.equals(NotorietyTypeEnum.UNKNOWN))
					System.out.println(MessagesEnum.INVALID_STARTYPE);
				else {
					if (hourlyCost < 0)
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
	 * 
	 * @param p
	 *            an audiovisual Production.
	 */
	private static void listUsers(Production p) {
		Iterator<User> it = p.listUsers();
		if (!it.hasNext())
			System.out.println(MessagesEnum.USERLIST_EMPTY);
		else
			while (it.hasNext())
				System.out.println(it.next());
	}

	/**
	 * Auxiliary method to register a new set for recordings.
	 * 
	 * @param in
	 *            data input stream.
	 * @param p
	 *            an audiovisual Production.
	 */
	private static void addSet(Scanner in, Production p) {
		String localname = in.nextLine();
		int localcost = in.nextInt();
		in.nextLine();

		if (p.hasLocal(localname))
			System.out.println(MessagesEnum.INVALID_LOCALNAME);
		else {
			if (localcost < 0)
				System.out.println(MessagesEnum.INVALID_LOCALCOST);
			else {
				p.addLocal(localname, localcost);
				System.out.println(MessagesEnum.LOCAL_SUCCESS);
			}
		}
	}

	/**
	 * Auxiliary method to list all sets for recordings.
	 * 
	 * @param p
	 *            an audiovisual Production.
	 */
	private static void listSets(Production p) {
		Iterator<Local> it = p.listLocals();
		if (!it.hasNext())
			System.out.println(MessagesEnum.LOCALIST_EMPTY);
		else
			while (it.hasNext())
				System.out.println(it.next());
	}

	/**
	 * Auxiliary method to schedule a new recording.
	 * 
	 * @param in
	 *            data input stream.
	 * @param p
	 *            an audiovisual Production.
	 */
	private static void schedule(Scanner in, Production p) {
		String localname = in.nextLine();
		int year = in.nextInt();
		int month = in.nextInt();
		int dayOfMonth = in.nextInt();
		int hour = in.nextInt();
		int minutes = in.nextInt();
		int duration = in.nextInt();
		in.nextLine();
		String producerName = in.nextLine();
		String directorName = in.nextLine();
		String technicianName = in.nextLine();
		int numCollabs = in.nextInt();
		in.nextLine();

		Array<String> collabsName = new ArrayClass<String>();

		for (int i = 0; i < numCollabs; i++) {
			collabsName.insertLast(in.nextLine());
		}

		LocalDateTime start = LocalDateTime.of(year, month, dayOfMonth, hour, minutes);

		aux_schedule(localname, start, duration, producerName, directorName, technicianName, collabsName, p, false);
	}
	
	// auxiliar method for recursive calling 
	private static void aux_schedule(String localname, LocalDateTime start, int duration, String producerName,
			String directorName, String technicianName, Array<String> collabsName, Production p, boolean silent) {
		Array<User> collabs = new ArrayClass<>();

		if (!p.hasLocal(localname)) { // level 1
			if (!silent)
				System.out.println(MessagesEnum.UNKNOWN_LOCAL);
		} else if (p.isBeforeLastRecorded(start)) { // level 2
			if (!silent)
				System.out.println(MessagesEnum.INVALID_DATE);
		} else if (duration < 0) { // level 3
			if (!silent)
				System.out.println(MessagesEnum.INVALID_DURATION);
		} else if (!p.isProducer(producerName)) { // level 4
			if (!silent)
				System.out.println(MessagesEnum.UNKNOWN_PRODUCER);
		} else if (!p.isDirector(directorName)) { // level 5
			if (!silent)
				System.out.println(MessagesEnum.UNKNOWN_DIRECTOR);
		} else if (!p.isTechnician(technicianName)) {
			if (!silent)
				System.out.println(MessagesEnum.UNKNOWN_TECHNICIAN); // level 6
		} else {

			// level 7
			Iterator<String> it = collabsName.iterator();
			while (it.hasNext()) {
				String collabName = it.next();

				if (!p.hasUser(collabName)) {
					if (!silent)
						System.out.println(MessagesEnum.UNKNOWN_COLLAB);

					return;
				}

				collabs.insertLast(p.getUser(collabName));
			}

			//add to collabs (as user type)
			Producer producer = (Producer) p.getUser(producerName);
			collabs.insertLast(producer);
			collabs.insertLast(p.getUser(directorName));
			collabs.insertLast(p.getUser(technicianName));

			//add to collabs os users, director, producer and tech for chekings on the next levels because teh principals werent there
			collabsName.insertLast(directorName);
			collabsName.insertLast(producerName);
			collabsName.insertLast(technicianName);

			// level 8
			if (p.hasBlacklistConflict(collabsName)) {
				if (!silent)
					System.out.println(MessagesEnum.RECORD_STAR_PENDENT);

				p.addRecording(localname, collabs, start, duration);
				if (!p.isRecordingSuspended(localname, start)) // suspends
																// recording
					p.toggleRecordingSuspension(localname, start);
			} else {

				// level 9
				if (p.hasRecordingConflict(localname, start, duration, collabs)
						&& !p.hasProducerPriority(producer, localname, start, duration)) {
					if (!silent)
						System.out.println(MessagesEnum.RECORD_CONFLICT);
				} else {

					// level 10
					if (p.hasRecordingConflict(localname, start, duration, collabs)
							&& p.hasProducerPriority(producer, localname, start, duration)) {

						Iterator<Recording> it2 = p.getRecordings(start, duration);

						// remove all conflicting recordings from productions 
						while (it2.hasNext()) {
							Recording rec = it2.next();

							p.removeRecording(rec);
						}

						// add our recording, that one that has priority
						p.addRecording(localname, collabs, start, duration);

						// add all previously removed recordings that were removed before (in the first place)
						it2.init();
						while (it2.hasNext()) {
							Recording rec = it2.next();
							
							//convert array of users to user name strings 
							Array<String> cNames = new ArrayClass<>();
							Iterator<User> it3 = rec.getCollabs().iterator();
							while (it3.hasNext()) {
								User user = it3.next();

								if (user != rec.getProducer() && user != rec.getDirector()
										&& user != rec.getTechician())
									cNames.insertLast(user.getName());
							}

							//recursive call to the method 
							aux_schedule(rec.getLocal().getName(), rec.getStartDate().plusDays(1), rec.getDuration(),
									rec.getProducer().getName(), rec.getDirector().getName(),
									rec.getTechician().getName(), cNames, p, true);
						}

						if (!silent)
							System.out.println(MessagesEnum.RECORD_CAUSED_RESCHED);
					} else {
						// success
						p.addRecording(localname, collabs, start, duration);

						if (!silent)
							System.out.println(MessagesEnum.RECORD_ADDED_SUCCESS);
					}
				}
			}
		}
	}

	/**
	 * Auxiliary method for dealing with the situation in which a star gets
	 * upset with a collaborator.
	 * 
	 * @param in
	 *            data input stream.
	 * @param p
	 *            an audiovisual Production.
	 */
	private static void grumpy(Scanner in, Production p) {
		String starname = in.nextLine();
		String collabname = in.nextLine();

		// level 1
		if (!p.hasStar(starname)) {
			System.out.println(starname + MessagesEnum.INVALID_STAR);
			return;
		}

		// level 2
		if (!p.hasUser(collabname)) {
			System.out.println(collabname + MessagesEnum.INVALID_COLLAB);
			return;
		}

		// level 3
		if (p.hasCollabInBlacklist(starname, collabname)) {
			System.out.println(MessagesEnum.INVALID_ADD_BLACKLIST);
			return;
		}

		int num = p.addCollabToBlacklist(starname, collabname);
		System.out.println(
				starname + " colocou " + collabname + " na sua lista negra, suspendendo " + num + " gravacoes.");

	}

	/**
	 * Auxiliary method for dealing with the situation in which a star
	 * reconciles with a collaborator.
	 * 
	 * @param in
	 *            data input stream.
	 * @param p
	 *            an audiovisual Production.
	 */
	private static void reconcile(Scanner in, Production p) {
		String starname = in.nextLine();
		String collabname = in.nextLine();

		if (!p.hasStar(starname))
			System.out.println(starname + MessagesEnum.INVALID_STAR);
		else {
			if (p.hasUser(collabname) && !p.hasCollabInBlacklist(starname, collabname) || !p.hasUser(collabname))
				System.out.println(MessagesEnum.NOT_IN_BLACKLIST + collabname + ".");
			else {
				int num = p.removeCollabOfBlacklist(starname, collabname);
				System.out.println(starname + " <3 " + collabname + ". " + num + " gravacoes salvas!");
			}
		}
	}

	private static void performed(Production p) {
		Iterator<Recording> recording = p.listRecordingsByStatus(RecordingStatusEnum.DONE);
		if (!recording.hasNext()) {
			System.out.println(MessagesEnum.RECORD_EMPTY_DONE);
		} else {
			float totalCost = 0.0f;

			while (recording.hasNext()) {
				Recording record = recording.next();
				String state = "";
				if (record.isCanceled())
					state = " Cancelada!";
				else if (record.isSuspended())
					state = " Suspensa!";

				System.out.println(record + state);

				if (!record.isCanceled() && !record.isSuspended())
					totalCost += record.getTotalCost();
			}

			int totalCostInt = Math.round(totalCost);
			System.out.format("%d euros gastos.\n", totalCostInt);
		}
	}

	private static void planned(Production p) {
		Iterator<Recording> it = p.listRecordingsByStatus(RecordingStatusEnum.SCHEDULED);
		if (!it.hasNext()) {
			System.out.println(MessagesEnum.RECORD_EMPTY_SCHEDULE);
		} else {
			float totalCost = 0.0f;

			while (it.hasNext()) {
				Recording rec = it.next();

				String state = "";
				if (rec.isCanceled())
					state = " Cancelada!";
				else if (rec.isSuspended())
					state = " Suspensa!";

				System.out.println(rec + state);

				totalCost += rec.getTotalCost();
			}

			int totalCostInt = Math.round(totalCost);
			System.out.printf("%d euros orcamentados.\n", totalCostInt);
		}
	}

	private static void places(Scanner in, Production p) {
		String local = in.nextLine();
		Local l = p.getLocal(local);
		if (l == null) {
			System.out.println(MessagesEnum.UNKNOWN_LOCAL);
		} else {
			float totalCost = 0;

			Iterator<Recording> it = p.listRecordingsByStatus(RecordingStatusEnum.SCHEDULED);

			boolean hasRecordings = false;
			while (it.hasNext()) {
				Recording record = it.next();
				if (record.getLocal().equals(l)) {
					LocalDateTime date = record.getStartDate();

					System.out.printf("%d %d %d; %s; %s.\n", date.getYear(), date.getMonthValue(), date.getDayOfMonth(),
							record.getProducer().getName(), record.getDirector().getName());

					totalCost += record.getTotalCost();
					hasRecordings = true;
				}
			}

			if (hasRecordings) {
				int totalCostInt = Math.round(totalCost);
				System.out.printf("%d euros orcamentados.\n", totalCostInt);
			} else {
				System.out.printf("Nenhuma gravacao prevista em %s.\n", l.getName());
			}
		}
	}

	private static void collaborator(Scanner in, Production p) {
		String name = in.nextLine();
		User collab = p.getUser(name);
		if (collab == null) {
			System.out.println(MessagesEnum.UNKNOWN_COLLAB);
		} else {

			Iterator<Recording> it = p.getRecCollectionByUser(collab);
			if (!it.hasNext()) {
				System.out.format("Nenhuma gravacao prevista com %s.\n", collab.getName());
			} else {
				float totalCost = 0.0f;

				while (it.hasNext()) {
					Recording record = it.next();
					if (record.hasCollab(collab.getName())) {
						LocalDateTime date = record.getStartDate();
						System.out.printf("%d %d %d; %s; %s.\n", date.getYear(), date.getMonthValue(),
								date.getDayOfMonth(), record.getProducer().getName(), record.getDirector().getName());
						totalCost += record.getTotalCost();
					}
				}

				int totalCostInt = Math.round(totalCost);
				System.out.printf("%d euros orcamentados.\n", totalCostInt);
			}
		}
	}

	private static void record(Production p) {
		Iterator<Recording> it = p.listRecordingsByStatus(RecordingStatusEnum.SCHEDULED);
		if (!it.hasNext()) {
			System.out.println(MessagesEnum.NO_SCHEDULE_RECORDING);
		} else {
			Recording rec = it.next();
			p.changeRecordingStatus(rec.getLocal().getName(), rec.getStartDate(), RecordingStatusEnum.DONE);

			if (rec.isSuspended()) {
				System.out.printf("%s Cancelada!\n", rec);
				rec.toggleCanceled();
			} else {
				System.out.printf("%s Gravada!\n", rec);
			}
		}
	}

	/**
	 * Main program.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Main.commandInterpreter();
	}

}
