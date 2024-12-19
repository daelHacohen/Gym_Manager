import java.util.ArrayList;

    public class Secretary {
        private Person secretaryPerson;
        private int salary;
        private ArrayList<Client> gymClientList;
        private ArrayList<Instructor> gymInstructorList;
        private ArrayList<Session> sessionList;
        private int gymBalanc;

        public Secretary(Person secretaryPerson, int salary) {
            this.secretaryPerson = secretaryPerson;
            this.salary = salary;
            gymClientList =new ArrayList<>();
            gymInstructorList =new ArrayList<>();
            sessionList =new ArrayList<>();
        }

        public Client registerClient(Person person) throws InvalidAgeException {
            validateAge(person.getAge());
            Client newClient =new Client(person);
            gymClientList.add(newClient);
            return newClient;
        }


        public Instructor hireInstructor(Person p, int salary, ArrayList<SessionType>sessionQualified ) {
            Instructor instructor = new Instructor(p, salary, sessionQualified);
            gymInstructorList.add(instructor);
            return instructor;
        }

        public void registerClientToLesson(Client c, Session s) {// צריך להוסיף גם בדיקה של האם מועד השיעור חלף או לא.
            if (c.getPerson().getBalance()<s.getPrice()){//בודק האם הללקוח יש מספיק כסף בשביל לשלם לשיעור
                return;
            }
            if (!findIfForumTypeIsValid(s.getForumType(),c.getPerson().getGender(),c.getPerson().getAge())){//בודק האם הסוג פורום של השיעור מתאים ללקוח
                return;
            }
            if (s.getListOfClientsInCurrentClass().size()<=s.getNumber_of_people_in_the_class()){//בודק האם יש מקום פנוי בשיעור הנוכחי
                return;
            }
            c.getPerson().setBalance(c.getPerson().getBalance()-s.getPrice());//לוקח כסף מהלקוח עבור השיעור
            gymBalanc = gymBalanc+s.getPrice();//מכניס לחשבון של החדר כושר את הכסף עבור השיעור
            s.getListOfClientsInCurrentClass().add(c);//מוסיף את הלקוח לשיעור

        }
        public static boolean findIfForumTypeIsValid(ForumType forumType,Gender gender, int age){
            switch (forumType) {
                case Male:
                   if (gender==Gender.Male)return true;
                case Seniors:
                    if (age>=65)return true;
                case Female:
                    if (gender==Gender.Female)return true;
                case All:
                   return true;
            }
            return false;
        }


    public void unregisterClient(Client c) {
        for (int i = 0; i < gymClientList.size(); i++) {
            if (c==gymClientList.get(i))gymClientList.remove(i);
        }
        c.setClient(null);

    }

    public Session addSession(SessionType sessionType, String dateAndHour, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
        findIfQualified(instructor.getSessionQualified(),sessionType);
        Session newsession = SessionFactory.createSession(sessionType,dateAndHour,forumType,instructor);
        sessionList.add(newsession);
        return newsession;
    }

    public void paySalaries() {
        secretaryPerson.setBalance(secretaryPerson.getBalance()+salary);
    }
    public static void validateAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be 18 or older.");

        }
    }
    public static void findIfQualified(ArrayList<SessionType>sessionQualified, SessionType currentSessionType) throws InstructorNotQualifiedException {
        boolean isQualified = false;
        for (int i = 0; i < sessionQualified.size(); i++) {
            if (sessionQualified.get(i)==currentSessionType) {
                isQualified=true;
            }
        }
        if (!isQualified){
            throw new InstructorNotQualifiedException("Instructor Not Qualified.");
        }
    }
}


