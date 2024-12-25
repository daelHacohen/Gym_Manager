package gym.management.Sessions;

import gym.customers.Client;
import gym.customers.Instructor;
import gym.customers.Observer;



import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

 public abstract class Session{
    protected SessionType sessionType;
    protected String dateAndHour;
    protected ForumType currentForumType;
    protected Instructor thisSessionInstructor;
    protected int Number_of_people_in_the_class;
    protected int price;
    protected ArrayList<Client> clientsInCurrentClass;
     protected ArrayList<Observer> observerClientsInCurrentClass;


    public Session( SessionType sessionType,String dateAndHour, ForumType currentForumType, Instructor thisSessionInstructor) {
        this.sessionType= sessionType;
        this.dateAndHour = dateAndHour;
        this.currentForumType = currentForumType;
        this.thisSessionInstructor = thisSessionInstructor;
        clientsInCurrentClass =new ArrayList<>();
        observerClientsInCurrentClass =new ArrayList<>();

    }

    public int getNumber_of_people_in_the_class() {
        return Number_of_people_in_the_class;
    }

     // מתודה להחזרת תאריך בלבד בפורמט yyyy-MM-dd
    public String getFormattedDate() {
         DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
         DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
         LocalDateTime dateTime = LocalDateTime.parse(dateAndHour, inputFormatter);
         return dateTime.format(outputFormatter);
     }

     // מתודה להחזרת תאריך ושעה בפורמט yyyy-MM-dd'T'HH:mm
     public String getFormattedDateTime() {
         DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
         DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
         LocalDateTime dateTime = LocalDateTime.parse(dateAndHour, inputFormatter);
         return dateTime.format(outputFormatter);
     }

    public int getPrice() {
        return price;
    }

    public ArrayList<Client> getListOfClientsInCurrentClass() {
        return clientsInCurrentClass;
    }

     public ArrayList<Observer> getObserverClientsInCurrentClass() {
         return observerClientsInCurrentClass;
     }

     public SessionType getSessionType() {
         return sessionType;
     }

     public ForumType getForumType() {
         return currentForumType;
     }

     public String getDateAndHour() {
         return dateAndHour;
     }

     public Instructor getThisSessionInstructor() {
         return thisSessionInstructor;
     }
 }
