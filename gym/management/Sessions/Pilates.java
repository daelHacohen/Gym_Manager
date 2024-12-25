package gym.management.Sessions;

import gym.customers.Instructor;


import java.util.ArrayList;

public class Pilates extends Session {

    public Pilates(SessionType sessionType, String dateAndHour, ForumType currentForumType, Instructor thisSessionInstructor) {
        super(sessionType, dateAndHour, currentForumType, thisSessionInstructor);
        this.price= 60;
        this.Number_of_people_in_the_class = 30;
        clientsInCurrentClass= new ArrayList<>();
    }

}
