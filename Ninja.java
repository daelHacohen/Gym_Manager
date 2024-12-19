import java.util.ArrayList;

public class Ninja extends Session{

        public Ninja(SessionType sessionType,String dateAndHour, ForumType currentForumType, Instructor thisSessionInstructor) {
            super(sessionType,dateAndHour, currentForumType, thisSessionInstructor);
            this.price= 150;
            this.Number_of_people_in_the_class = 5;
            clientsInCurrentClass= new ArrayList<>();
        }

}

