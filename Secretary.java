import java.util.ArrayList;

    public class Secretary {
        private Person secretaryPerson;
        private int salary;
        private ArrayList<Client> gymClientList;
        private ArrayList<Instructor> gymInstructorList;
        private ArrayList<Session> sessionList;
        private int gymBalanc;
        private ArrayList<String>gymActions;

        public Secretary(Person secretaryPerson, int salary) {
            this.secretaryPerson = secretaryPerson;
            this.salary = salary;
            gymClientList =new ArrayList<>();
            gymInstructorList =new ArrayList<>();
            sessionList =new ArrayList<>();
            gymActions= new ArrayList<>();
        }

        public void setSecretaryPerson(Person secretaryPerson) {
            this.secretaryPerson = secretaryPerson;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {
            validateAge(person.getAge());
            Client newClient =new Client(person);
            findIfDuplicateClient(newClient,gymClientList);
            gymClientList.add(newClient);
            addGymActions("Registered new client: "+newClient.getPerson().getName());
            return newClient;

        }

        public void findIfDuplicateClient(Client clientToComper, ArrayList<Client>clients) throws DuplicateClientException {
            for (int i = 0; i < clients.size(); i++) {
                if (clients.get(i).equals(clientToComper)){
                    throw new DuplicateClientException("Error: The client is already registered");
                }
            }
        }

        public Instructor hireInstructor(Person p, int salary, ArrayList<SessionType>sessionQualified ) {
            Instructor instructor = new Instructor(p, salary, sessionQualified);
            gymInstructorList.add(instructor);
            addGymActions("Hired new instructor: "+instructor.getInstructorPerson().getName()+" with salary per hour: "+instructor.getSalaryPerHour());
            return instructor;
        }

        public boolean checkIfTheSameSecretary(){
            boolean ans = false;
            if (this==Gym.getInstance().getSecretary())ans=true;
            return ans;
        }
        public void registerClientToLesson(Client c, Session s) throws DuplicateClientException, ClientNotRegisteredException {// צריך להוסיף גם בדיקה של האם מועד השיעור חלף או לא.
           if (!checkIfTheSameSecretary())return;
            if (c.getPerson().getBalance()<s.getPrice()){//בודק האם הללקוח יש מספיק כסף בשביל לשלם לשיעור
              addGymActions("Failed registration: Client doesn't have enough balance");
                return;
            }
            if (!findIfForumTypeIsValid(s.getForumType(),c.getPerson().getGender(),c.getPerson().getAge())){//בודק האם הסוג פורום של השיעור מתאים ללקוח
                return;
            }
            if (s.getListOfClientsInCurrentClass().size()>=s.getNumber_of_people_in_the_class()){//בודק האם יש מקום פנוי בשיעור הנוכחי
               addGymActions("Failed registration: No available spots for session");
                return;
            }
            findIfDuplicateClient(c,s.getListOfClientsInCurrentClass());//זורק שגיאה אם הלקוח כבר רשום לשיעור הנוכחי
            findIfClientNotRegistered(c,gymClientList);// זורק שגיאה אם הלקוח כבר לא רשום כלקוח
            c.getPerson().setBalance(c.getPerson().getBalance()-s.getPrice());//לוקח כסף מהלקוח עבור השיעור
            gymBalanc = gymBalanc+s.getPrice();//מכניס לחשבון של החדר כושר את הכסף עבור השיעור
            s.getListOfClientsInCurrentClass().add(c);//מוסיף את הלקוח לשיעור
            addGymActions("Registered client: "+c.getPerson().getName()+" to session: "+s.sessionType+" on "+s.dateAndHour+" for price: "+s.getPrice());
        }
        public  boolean findIfForumTypeIsValid(ForumType forumType,Gender gender, int age){
            switch (forumType) {
                case Male:
                   if (gender==Gender.Male)return true;
                   addGymActions("Failed registration: Client's gender doesn't match the session's gender requirements");
                case Seniors:
                    if (age>=65) return true;
                    addGymActions("Failed registration: Client doesn't meet the age requirements for this session (Seniors)");
                case Female:
                    if (gender==Gender.Female)return true;
                    addGymActions("Failed registration: Client's gender doesn't match the session's gender requirements");
                case All:
                   return true;
            }
            return false;
        }


    public void unregisterClient(Client c) throws ClientNotRegisteredException {
            findIfClientNotRegistered(c,gymClientList);
        for (int i = 0; i < gymClientList.size(); i++) {
            if (c==gymClientList.get(i))gymClientList.remove(i);
        }
        addGymActions("Unregistered client: "+c.getPerson().getName());
        /**לבדוק למה לא עושים את הלקוח null כאשר מוחקים הרשמה שלו( זה עשה לי בעיה בריצה עם זה שהוא null)(לכן שמתי את השורה מתחת בהערה)*/
//        c.setClient(null);
    }
    public void findIfClientNotRegistered(Client client , ArrayList<Client>clientArrayList) throws ClientNotRegisteredException {
            boolean registed =false;
        for (int i = 0; i < clientArrayList.size(); i++) {
            if (clientArrayList.get(i).equals(client))registed =true;
        }
            if (!registed)throw new ClientNotRegisteredException("Error: Registration is required before attempting to unregister");//צריך לראות מה בדיוק צריך בהדפסות בכל פונקציה



    }
    public Session addSession(SessionType sessionType, String dateAndHour, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
        findIfQualified(instructor.getSessionQualified(),sessionType);
        Session newsession = SessionFactory.createSession(sessionType,dateAndHour,forumType,instructor);
        sessionList.add(newsession);
        addGymActions("Created new session: "+newsession.sessionType+" on "+newsession.dateAndHour+" with instructor: "+newsession.thisSessionInstructor.getInstructorPerson().getName());
        return newsession;
    }

    public void paySalaries() {
        secretaryPerson.setBalance(secretaryPerson.getBalance()+salary);
    }
    public static void validateAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Error: Client must be at least 18 years old to register");

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
    public void notify (Session s,String messege){//הודעות לרשומים לשיעור ספציפי
       for (Client client:s.getListOfClientsInCurrentClass()){
           client.update(messege);
       }
    }
        public void notify (String specificDay, String messege){//הודעות לרשומים לשיעורים של יום ספציפי

        }
        public void notify (String messege){//הודעות לכלל לקוחות המכון
        for (Client client : gymClientList){
            client.update(messege);
        }
        }

        public void printActions() {
            for (int i = 0; i < gymActions.size(); i++) {
                System.out.println(gymActions.get(i));
            }

        }
        public void addGymActions(String action){
            gymActions.add(action);
        }
    }


