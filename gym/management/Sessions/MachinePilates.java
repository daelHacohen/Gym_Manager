package gym.management.Sessions;

import gym.customers.Instructor;
import gym.customers.Client;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.SessionType;

import java.util.ArrayList;

public class MachinePilates extends Session {
    /**
     * Constructor for the MachinePilates class.
     * Initializes a Machine Pilates session with specific properties.
     *
     * @param sessionType         The type of session (e.g., group or private).
     * @param dateAndHour         The date and time of the session.
     * @param currentForumType    The type of forum (e.g., online or in-person).
     * @param thisSessionInstructor The instructor assigned to this session.
     */
    public MachinePilates(SessionType sessionType, String dateAndHour, ForumType currentForumType, Instructor thisSessionInstructor) {
        super(sessionType,dateAndHour, currentForumType, thisSessionInstructor);
        this.price= 80;
        this.Number_of_people_in_the_class = 10;
        // Initialize the list to keep track of clients in this session
        clientsInCurrentClass= new ArrayList<>();
    }

}
