package gym.management.Sessions;

import gym.customers.Client;
import gym.customers.Instructor;
import gym.customers.Observer;



import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * The Session class represents an abstract gym session with various details such as date, time,
 * instructor, clients, and observers.
 */
 public abstract class Session{
    protected SessionType sessionType;
    protected String dateAndHour;
    protected ForumType currentForumType;
    protected Instructor thisSessionInstructor;
    protected int Number_of_people_in_the_class;
    protected int price;
    protected ArrayList<Client> clientsInCurrentClass;
     protected ArrayList<Observer> observerClientsInCurrentClass;

    /**
     * Constructs a Session object with the specified details.
     *
     * @param sessionType          The type of session.
     * @param dateAndHour          The date and hour of the session in the format "dd-MM-yyyy HH:mm".
     * @param currentForumType     The forum type of the session.
     * @param thisSessionInstructor The instructor assigned to the session.
     */
    public Session( SessionType sessionType,String dateAndHour, ForumType currentForumType, Instructor thisSessionInstructor) {
        this.sessionType= sessionType;
        this.dateAndHour = dateAndHour;
        this.currentForumType = currentForumType;
        this.thisSessionInstructor = thisSessionInstructor;
        clientsInCurrentClass =new ArrayList<>();
        observerClientsInCurrentClass =new ArrayList<>();

    }
    /**
     * Retrieves the number of people in the class.
     *
     * @return The number of people in the class.
     */
    public int getNumber_of_people_in_the_class() {
        return Number_of_people_in_the_class;
    }

    /**
     * Returns the date of the session in the format "yyyy-MM-dd".
     * @return A string representing the session date.
     */
     // מתודה להחזרת תאריך בלבד בפורמט yyyy-MM-dd
    public String getFormattedDate() {
         DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
         DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
         LocalDateTime dateTime = LocalDateTime.parse(dateAndHour, inputFormatter);
         return dateTime.format(outputFormatter);
     }

    /**
     * Returns the date and time of the session in the format "yyyy-MM-dd'T'HH:mm".
     * @return A string representing the session date and time.
     */
     // מתודה להחזרת תאריך ושעה בפורמט yyyy-MM-dd'T'HH:mm
     public String getFormattedDateTime() {
         DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
         DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
         LocalDateTime dateTime = LocalDateTime.parse(dateAndHour, inputFormatter);
         return dateTime.format(outputFormatter);
     }
    /**
     * Retrieves the price of the session.
     * @return The price of the session.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Retrieves the list of clients attending the session.
     * @return An ArrayList of clients in the session.
     */
    public ArrayList<Client> getListOfClientsInCurrentClass() {
        return clientsInCurrentClass;
    }

    /**
     * Retrieves the list of observer clients attending the session.
     * @return An ArrayList of observer clients in the session.
     */
     public ArrayList<Observer> getObserverClientsInCurrentClass() {
         return observerClientsInCurrentClass;
     }

    /**
     * Retrieves the type of the session.
     * @return The session type.
     */
     public SessionType getSessionType() {
         return sessionType;
     }

    /**
     * Retrieves the forum type of the session.
     * @return The forum type.
     */
     public ForumType getForumType() {
         return currentForumType;
     }

    /**
     * Retrieves the date and hour of the session.
     * @return A string representing the date and hour of the session.
     */
     public String getDateAndHour() {
         return dateAndHour;
     }

    /**
     * Retrieves the instructor assigned to the session.
     * @return The instructor of the session.
     */
     public Instructor getThisSessionInstructor() {
         return thisSessionInstructor;
     }
 }
