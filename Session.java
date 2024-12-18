public class Session {//
    private SessionType currenySesionType;
    private String dateAndHour;
    private ForumType currentForumType;
    private Instructor thisSessionInstructor;

    public Session(SessionType currenySesionType, String dateAndHour, ForumType currentForumType, Instructor thisSessionInstructor) {
        this.currenySesionType = currenySesionType;
        this.dateAndHour = dateAndHour;
        this.currentForumType = currentForumType;
        this.thisSessionInstructor = thisSessionInstructor;
    }
}
