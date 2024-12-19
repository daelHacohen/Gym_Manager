import java.util.ArrayList;

public class ThaiBoxing extends Session{

    public ThaiBoxing(SessionType sessionType,String dateAndHour, ForumType currentForumType, Instructor thisSessionInstructor) {
        super(sessionType,dateAndHour, currentForumType, thisSessionInstructor);
        this.price= 100;
        this.Number_of_people_in_the_class = 20;
        clientsInCurrentClass= new ArrayList<>();
    }

}
