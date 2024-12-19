import java.util.ArrayList;

public class MachinePilates extends Session{

    public MachinePilates(SessionType sessionType,String dateAndHour, ForumType currentForumType, Instructor thisSessionInstructor) {
        super(sessionType,dateAndHour, currentForumType, thisSessionInstructor);
        this.price= 80;
        this.Number_of_people_in_the_class = 10;
        clientsInCurrentClass= new ArrayList<>();
    }

}
