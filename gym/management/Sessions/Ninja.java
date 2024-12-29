package gym.management.Sessions;

import gym.customers.Instructor;


import java.util.ArrayList;
/**
 * The Ninja class represents a specific type of gym session focused on Ninja-style training.
 * It extends the abstract Session class and defines properties specific to Ninja sessions.
 */
public class Ninja extends Session {

    /**
     * Constructs a Ninja session with the given details.
     * @param sessionType The type of session, which should be SessionType.Ninja.
     * @param dateAndHour The date and hour of the session, formatted as "dd-MM-yyyy HH:mm".
     * @param currentForumType The forum type of the session.
     * @param thisSessionInstructor The instructor assigned to this Ninja session.
     */
        public Ninja(SessionType sessionType, String dateAndHour, ForumType currentForumType, Instructor thisSessionInstructor) {
            super(sessionType,dateAndHour, currentForumType, thisSessionInstructor);
            this.price= 150;
            this.Number_of_people_in_the_class = 5;
            clientsInCurrentClass= new ArrayList<>();
        }

}

