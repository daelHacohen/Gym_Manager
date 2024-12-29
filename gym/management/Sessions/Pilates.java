package gym.management.Sessions;

import gym.customers.Instructor;


import java.util.ArrayList;

/**
 * The Pilates class represents a specific type of gym session focused on Pilates exercises.
 * It extends the abstract Session class and sets predefined values for price and class capacity.
 */
public class Pilates extends Session {//

    /**
     * Constructs a Pilates session with the specified session details.
     * @param sessionType         The type of the session (should be SessionType.Pilates).
     * @param dateAndHour          The date and time of the session in the format dd-MM-yyyy HH:mm.
     * @param currentForumType     The forum type for the session (e.g., group, individual).
     * @param thisSessionInstructor The instructor assigned to this session.
     */
    public Pilates(SessionType sessionType, String dateAndHour, ForumType currentForumType, Instructor thisSessionInstructor) {
        super(sessionType, dateAndHour, currentForumType, thisSessionInstructor);
        this.price= 60;
        this.Number_of_people_in_the_class = 30;
        clientsInCurrentClass= new ArrayList<>();
    }

}
