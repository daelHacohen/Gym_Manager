package gym.management.Sessions;

import gym.customers.Instructor;

/**
 * The SessionFactory class is responsible for creating instances of different types of gym sessions.
 * It follows the Factory Design Pattern to provide a unified interface for creating session objects.
 */
public class SessionFactory {

    /**
     * Creates a specific type of session based on the given SessionType.
     * @param sessionType The type of session to create (e.g., Pilates, MachinePilates, Ninja, ThaiBoxing).
     * @param str The date and hour of the session, formatted as "dd-MM-yyyy HH:mm".
     * @param forumType The forum type for the session.
     * @param instructor The instructor assigned to the session.
     * @return A Session object of the specified type.
     * @throws IllegalArgumentException if the provided SessionType is unknown.
     */
    public static Session createSession(SessionType sessionType, String str, ForumType forumType, Instructor instructor) {
        switch (sessionType) {
            case Pilates:
                return new Pilates( sessionType, str,forumType,instructor);
            case MachinePilates:
                return new MachinePilates(sessionType, str,forumType,instructor);
            case Ninja:
                return new Ninja(sessionType, str,forumType,instructor);
            case ThaiBoxing:
                return new ThaiBoxing(sessionType, str,forumType,instructor);
            default:
                throw new IllegalArgumentException("Unknown session type: " + sessionType);
        }
    }


}
