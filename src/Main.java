import java.time.LocalDateTime;
import java.util.Scanner;

import javax.jws.soap.SOAPBinding.Use;

import enums.*;
import productions.*;
import util.*;
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
		;

		for (int i = 0; i < numCollabs; i++) {
			collabsName.insertLast(in.nextLine());
		}

		LocalDateTime start = LocalDateTime.of(year, month, dayOfMonth, hour, minutes);

		Array<User> collabs = new ArrayClass<>();

		// level 1
		if (!p.hasLocal(localname)) {
			System.out.println(MessagesEnum.UNKNOWN_LOCAL);
			return;
		}

		// level 2
		if (p.isBeforeLastRecorded(start)) {
			System.out.println(MessagesEnum.INVALID_DATE);
			return;
		}

		// level 3
		if (duration < 0) {
			System.out.println(MessagesEnum.INVALID_DURATION);
			return;
		}

		// level 4
		if (!p.hasUser(producerName)) {
			System.out.println(MessagesEnum.UNKNOWN_PRODUCER);
			return;
		}
		collabs.insertLast(p.getUser(producerName));

		// level 5
		if (!p.hasUser(directorName)) {
			System.out.println(MessagesEnum.UNKNOWN_DIRECTOR);
			return;
		}
		collabs.insertLast(p.getUser(directorName));

		// level 6
		if (!p.hasUser(technicianName)) {
			System.out.println(MessagesEnum.UNKNOWN_TECHNICIAN);
			return;
		}
		collabs.insertLast(p.getUser(technicianName));

		// level 7
		Iterator<String> it = collabsName.iterator();
		while (it.hasNext()) {
			String collabName = it.next();

			if (!p.hasUser(collabName)) {
				System.out.println(MessagesEnum.UNKNOWN_COLLAB);
				return;
			}

			collabs.insertLast(p.getUser(collabName));
		}

		// level 8
		if (p.hasBlacklistConflict(collabsName) && !p.hasRecording(localname, start)) {
			System.out.println(MessagesEnum.RECORD_STAR_PENDENT);
			p.addRecording(localname, collabs, start, duration);
			if (!p.isRecordingSuspended(localname, start)) // suspends recording
				p.toggleRecordingSuspension(localname, start);
		} else {

			// level 9
			if (p.hasRecordingConflict(localname, start, duration, collabs)
					&& !p.hasProducerPriority(localname, start)) {
				System.out.println(MessagesEnum.RECORD_CONFLICT);
			} else {

				// level 10
				if (p.hasRecordingConflict(localname, start, duration, collabs)
						&& p.hasProducerPriority(localname, start) && !p.hasRecording(localname, start)) {
					p.rescheduleRecording(p.getConflictingRecording(localname, start));
					p.addRecording(localname, collabs, start, duration);
					System.out.println(MessagesEnum.RECORD_CAUSED_RESCHED);
				} else {
					// success
					p.addRecording(localname, collabs, start, duration);
					System.out.println(MessagesEnum.RECORD_ADDED_SUCCESS);
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
		if (!p.hasStar(starname))
			System.out.println(starname + MessagesEnum.INVALID_STAR);
		else {
			if (!p.hasUser(collabname))
				System.out.println(collabname + MessagesEnum.INVALID_COLLAB);
			else {
				if (p.hasCollabInBlacklist(starname, collabname))
					System.out.println(MessagesEnum.INVALID_ADD_BLACKLIST);
				else {
					int num = p.addCollabToBlacklist(starname, collabname);
					System.out.println(starname + " colocou " + collabname + " na sua lista negra, suspendendo " + num
							+ " gravacoes.");
				}

			}
		}
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
			if (p.hasCollabInBlacklist(starname, collabname) || !p.hasUser(collabname))
				System.out.println(MessagesEnum.NOT_IN_BLACKLIST + collabname);
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
				LocalDateTime date = record.getStartDate();

				System.out.format("%d %d %d; %s; %s; %s.\n", date.getYear(), date.getMonthValue(), date.getDayOfMonth(),
						record.getLocal().getName(), record.getProducer().getName(), record.getDirector().getName());

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
				LocalDateTime date = rec.getStartDate();
				Local local = rec.getLocal();
				User producer = rec.getProducer();
				User director = rec.getDirector();

				System.out.format("%d %d %d; %s; %s; %s.\n", date.getYear(), date.getMonthValue(), date.getDayOfMonth(),
						local.getName(), producer.getName(), director.getName());

				totalCost += rec.getTotalCost();
			}

			int totalCostInt = Math.round(totalCost);
			System.out.format("%d euros orcamentados.\n", totalCostInt);
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

			if (!it.hasNext()) {
				System.out.format("Nenhuma gravacao prevista em %s.\n", l.getName());
			} else {
				while (it.hasNext()) {
					Recording record = it.next();
					if (record.getLocal().equals(l)) {
						LocalDateTime date = record.getStartDate();

						System.out.format("%d %d %d; %s; %s.\n", date.getYear(), date.getMonthValue(),
								date.getDayOfMonth(), record.getProducer().getName(), record.getDirector().getName());

						totalCost += record.getTotalCost();
					}
				}

				int totalCostInt = Math.round(totalCost);
				System.out.format("%d euros orcamentados.\n", totalCostInt);
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
				while (it.hasNext()) {
					Recording record = it.next();
					if (record.hasCollab(collab.getName())) {
						LocalDateTime date = record.getStartDate();

						System.out.format("%d %d %d; %s; %s.\n", date.getYear(), date.getMonthValue(),
								date.getDayOfMonth(), record.getProducer().getName(), record.getDirector().getName());

					}
				}
			}

		}
	}

	private static void record(Production p) {
		Iterator<Recording> it = p.listRecordingsByStatus(RecordingStatusEnum.SCHEDULED);
		if (!it.hasNext()) {
			System.out.println(MessagesEnum.NO_SCHEDULE_RECORDING);
		} else {
			Recording rec = it.next();
			LocalDateTime date = rec.getStartDate();

			p.changeRecordingStatus(rec.getLocal().getName(), rec.getStartDate(), RecordingStatusEnum.DONE);

			if (rec.isSuspended()) {
				System.out.format("%d %d %d; %s; %s; %s. Cancelada!\n", date.getYear(), date.getMonthValue(),
						date.getDayOfMonth(), rec.getLocal().getName(), rec.getProducer().getName(),
						rec.getDirector().getName());

			} else {
				System.out.format("%d %d %d; %s; %s; %s. Gravada!\n", date.getYear(), date.getMonthValue(),
						date.getDayOfMonth(), rec.getLocal().getName(), rec.getProducer().getName(),
						rec.getDirector().getName());
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
