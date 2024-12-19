public class SessionFactory {
    public static Session createSession(SessionType sessionType, String str, ForumType forumType, Instructor instructor) {
        switch (sessionType) {
            case Pilates:
                return new Pilates( sessionType, str,forumType,instructor);
            case MachinePilates:
                return new MachinePilates(sessionType, str,forumType,instructor);
            case Ninja:
                return new Ninja(sessionType, str,forumType,instructor);
            case ThaiBoxing:
                return new ThaiBoxing(sessionType, str,forumType,instructor);
            default:
                throw new IllegalArgumentException("Unknown session type: " + sessionType);
        }
    }


}
