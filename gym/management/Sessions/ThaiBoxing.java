package gym.management.Sessions;
import gym.customers.Instructor;


import java.util.ArrayList;

/**
 * The ThaiBoxing class represents a specific type of gym session focused on Thai Boxing.
 * It extends the abstract Session class and sets predefined values for price and class capacity.
 */
public class ThaiBoxing extends Session {

    /**
     * Constructs a ThaiBoxing session with the specified session details.
     * @param sessionType         The type of the session (should be SessionType.ThaiBoxing).
     * @param dateAndHour          The date and time of the session in the format dd-MM-yyyy HH:mm.
     * @param currentForumType     The forum type for the session (e.g., group, individual).
     * @param thisSessionInstructor The instructor assigned to this session.
     */
    public ThaiBoxing(SessionType sessionType, String dateAndHour, ForumType currentForumType, Instructor thisSessionInstructor) {
        super(sessionType,dateAndHour, currentForumType, thisSessionInstructor);
        this.price= 100;
        this.Number_of_people_in_the_class = 20;
        clientsInCurrentClass= new ArrayList<>();
    }

}
