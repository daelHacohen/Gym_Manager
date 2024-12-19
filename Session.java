import java.util.ArrayList;

 abstract class Session{
    protected SessionType sessionType;
    protected String dateAndHour;
    protected ForumType currentForumType;
    protected Instructor thisSessionInstructor;
    protected int Number_of_people_in_the_class;
    protected int price;
    protected ArrayList<Client> clientsInCurrentClass;


    public Session( SessionType sessionType,String dateAndHour, ForumType currentForumType, Instructor thisSessionInstructor) {
        this.sessionType= sessionType;
        this.dateAndHour = dateAndHour;
        this.currentForumType = currentForumType;
        this.thisSessionInstructor = thisSessionInstructor;
        clientsInCurrentClass =new ArrayList<>();

    }

    public int getNumber_of_people_in_the_class() {
        return Number_of_people_in_the_class;
    }

    public int getPrice() {
        return price;
    }

    public ArrayList<Client> getListOfClientsInCurrentClass() {
        return clientsInCurrentClass;
    }

     public SessionType getSessionType() {
         return sessionType;
     }

     public ForumType getForumType() {
         return currentForumType;
     }
 }
