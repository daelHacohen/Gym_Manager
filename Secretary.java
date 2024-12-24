import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

    public class Secretary {//
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
            if (!checkIfTheSameSecretary())return null;
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

        public void findIfDuplicateClientToLesson(Client clientToComper, ArrayList<Client>clients) throws DuplicateClientException {
            for (int i = 0; i < clients.size(); i++) {
                if (clients.get(i).equals(clientToComper)){
                    throw new DuplicateClientException("Error: The client is already registered for this lesson");
                }
            }
        }

        public Instructor hireInstructor(Person p, int salary, ArrayList<SessionType>sessionQualified ) {
            if (!checkIfTheSameSecretary())return null;
            Instructor instructor = new Instructor(p, salary, sessionQualified);
            gymInstructorList.add(instructor);
            addGymActions("Hired new instructor: "+instructor.getInstructorPerson().getName()+" with salary per hour: "+instructor.getSalaryPerHour());
            return instructor;
        }

        public boolean checkIfTheSameSecretary(){

            if (this==Gym.getInstance().getSecretary())return true;
            else
                checkIfTheSameSecretaryExeption();
           return false;
        }
        public void checkIfTheSameSecretaryExeption()throws NullPointerException{
            if (this!=Gym.getInstance().getSecretary())throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        }
        public void registerClientToLesson(Client c, Session s) throws DuplicateClientException, ClientNotRegisteredException {// צריך להוסיף גם בדיקה של האם מועד השיעור חלף או לא.
            if (!checkIfTheSameSecretary())return;
            if(!findIfForumTypeIsValid(s.getForumType(), c.getPerson().getGender(), c.getPerson().getAge())||LocalDateTime.parse(s.getFormattedDateTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")).isBefore(LocalDateTime.now())||c.getPerson().getBalance() < s.getPrice()||s.getListOfClientsInCurrentClass().size() >= s.getNumber_of_people_in_the_class()) {

                findIfDuplicateClientToLesson(c,s.getListOfClientsInCurrentClass());//זורק שגיאה אם הלקוח כבר רשום לשיעור הנוכחי
                findIfClientNotRegisteredToLesson(c,gymClientList);// זורק שגיאה אם הלקוח כבר לא רשום כלקוח

                if (LocalDateTime.parse(s.getFormattedDateTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")).isBefore(LocalDateTime.now())) {
                    addGymActions("Failed registration: Session is not in the future");

                }

                addActionsIfFuromTypeIsNotValid(s.getForumType(), c.getPerson().getGender(), c.getPerson().getAge());

                if (c.getPerson().getBalance() < s.getPrice()) {//בודק האם הללקוח יש מספיק כסף בשביל לשלם לשיעור
                    addGymActions("Failed registration: Client doesn't have enough balance");

                }

                if (s.getListOfClientsInCurrentClass().size() >= s.getNumber_of_people_in_the_class()) {//בודק האם יש מקום פנוי בשיעור הנוכחי
                    addGymActions("Failed registration: No available spots for session");

                }

                return;
            }



            findIfDuplicateClientToLesson(c,s.getListOfClientsInCurrentClass());//זורק שגיאה אם הלקוח כבר רשום לשיעור הנוכחי
            findIfClientNotRegisteredToLesson(c,gymClientList);// זורק שגיאה אם הלקוח כבר לא רשום כלקוח
            c.getPerson().setBalance(c.getPerson().getBalance()-s.getPrice());//לוקח כסף מהלקוח עבור השיעור
            gymBalanc = gymBalanc+s.getPrice();//מכניס לחשבון של החדר כושר את הכסף עבור השיעור
            s.getListOfClientsInCurrentClass().add(c);//מוסיף את הלקוח לשיעור
            addGymActions("Registered client: "+c.getPerson().getName()+" to session: "+s.sessionType+" on "+s.getFormattedDateTime()+" for price: "+s.getPrice());


        }
        public  void addActionsIfFuromTypeIsNotValid(ForumType forumType,Gender gender, int age){
            switch (forumType) {
                case Male:
                    if (!gender.equals(Gender.Male)) {
                        addGymActions("Failed registration: Client's gender doesn't match the session's gender requirements");
                    }
                case Seniors:
                    if (!(age>=65)) {
                        addGymActions("Failed registration: Client doesn't meet the age requirements for this session (Seniors)");
                    }
                case Female:
                    if (!gender.equals(Gender.Female)){
                        addGymActions("Failed registration: Client's gender doesn't match the session's gender requirements");
                    }
                case All:

           }
        }
        public  boolean findIfForumTypeIsValid(ForumType forumType,Gender gender, int age){
            switch (forumType) {
                case Male:
                    if (gender.equals(Gender.Male)) {
                        return true;
                    }else {

                        return false;
                    }
                case Seniors:
                    if (age>=65) {return true;
                    }else {
                        return false;
                    }
                case Female:
                    if (gender.equals(Gender.Female)){return true;
                    }else {
                        return false;
                    }
                case All:
                    return true;
            }
            return false;
        }

    public void unregisterClient(Client c) throws ClientNotRegisteredException {
            if (!checkIfTheSameSecretary())return;
            findIfClientNotRegistered(c,gymClientList);
        for (int i = 0; i < gymClientList.size(); i++) {
            if (c==gymClientList.get(i))gymClientList.remove(c);
        }
        addGymActions("Unregistered client: "+c.getPerson().getName());

    }
    public void findIfClientNotRegistered(Client client , ArrayList<Client>clientArrayList) throws ClientNotRegisteredException {
            boolean registed =false;
        for (Client value : clientArrayList) {
            if (value.equals(client)) {
                registed = true;
                break;
            }
        }
            if (!registed)throw new ClientNotRegisteredException("Error: Registration is required before attempting to unregister");//צריך לראות מה בדיוק צריך בהדפסות בכל פונקציה



    }

        public void findIfClientNotRegisteredToLesson(Client client , ArrayList<Client>clientArrayList) throws ClientNotRegisteredException {
            boolean registed =false;
            for (Client value : clientArrayList) {
                if (value.equals(client)) {
                    registed = true;
                    break;
                }
            }
             if (!registed)throw new ClientNotRegisteredException("Error: The client is not registered with the gym and cannot enroll in lessons");//צריך לראות מה בדיוק צריך בהדפסות בכל פונקציה



        }
    public Session addSession(SessionType sessionType, String dateAndHour, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
            if (!checkIfTheSameSecretary())return null;
        findIfQualified(instructor.getSessionQualified(),sessionType);
        Session newsession = SessionFactory.createSession(sessionType,dateAndHour,forumType,instructor);
        sessionList.add(newsession);
        instructor.setCounterSesseions(instructor.getCounterSesseions()+1);
        addGymActions("Created new session: "+newsession.sessionType+" on "+newsession.getFormattedDateTime()+" with instructor: "+newsession.thisSessionInstructor.getInstructorPerson().getName());
        return newsession;
    }

    public void paySalaries() {
if (!checkIfTheSameSecretary())return;
        secretaryPerson.setBalance(secretaryPerson.getBalance()+getSalary());
        gymBalanc-=this.getSalary();
        for (Instructor instructor: gymInstructorList){
            instructor.getInstructorPerson().setBalance((instructor.getSalaryPerHour()*instructor.getCounterSesseions())+instructor.getInstructorPerson().getBalance());
            gymBalanc=gymBalanc-(instructor.getSalaryPerHour()*instructor.getCounterSesseions());
            instructor.setCounterSesseions(0);
        }
        addGymActions("Salaries have been paid to all employees");
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
            throw new InstructorNotQualifiedException("Error: Instructor is not qualified to conduct this session type.");
        }
    }
    public void notify (Session s,String messege){//הודעות לרשומים לשיעור ספציפי
            if (!checkIfTheSameSecretary())return;
       for (Client client:s.getListOfClientsInCurrentClass()){
           client.update(messege);
       }
           addGymActions("A message was sent to everyone registered for session " + s.sessionType + " on " +s.getFormattedDateTime() + " : " + messege );
    }
        public void notify(String specificDay, String message) {
            if (!checkIfTheSameSecretary())return;
            // הגדרת פורמטים
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // המרת specificDay לפורמט תואם (yyyy-MM-dd)
            LocalDate formattedSpecificDay = LocalDate.parse(specificDay, inputFormatter);

            for (Session s : sessionList) {
                // המרת התאריך של השיעור לפורמט LocalDate
                LocalDate sessionDate = LocalDate.parse(s.getFormattedDate(), outputFormatter);

                if (sessionDate.equals(formattedSpecificDay)) { // השוואת התאריכים
                    for (Client client : s.getListOfClientsInCurrentClass()) {
                        client.update(message); // שליחת הודעה ללקוחות
                    }

                    addGymActions("A message was sent to everyone registered for a session on " + sessionDate + " : " + message);
                }
            }
        }
        public void notify (String messege){//הודעות לכלל לקוחות המכון
            if (!checkIfTheSameSecretary())return;
        for (Client client : gymClientList){
            client.update(messege);
        }
            addGymActions("A message was sent to all gym clients: " + messege);
        }

        public void printActions() {
            if (!checkIfTheSameSecretary())return;
            for (int i = 0; i < gymActions.size(); i++) {
                System.out.println(gymActions.get(i));
            }

        }
        public void addGymActions(String action){
            gymActions.add(action);

        }

        public Person getPerson() {
            return secretaryPerson;
        }

        public int getSalary() {
            return salary;
        }

        public int getGymBalanc() {
            return gymBalanc;
        }

        public ArrayList<Client> getGymClientList() {
            return gymClientList;
        }

        public ArrayList<Instructor> getGymInstructorList() {
            return gymInstructorList;
        }

        public ArrayList<Session> getSessionList() {
            return sessionList;
        }

        public Person getSecretaryPerson() {
            return secretaryPerson;
        }

        public void setGymClientList(ArrayList<Client> gymClientList) {
            this.gymClientList = gymClientList;
        }

        public void setGymInstructorList(ArrayList<Instructor> gymInstructorList) {
            this.gymInstructorList = gymInstructorList;
        }

        public void setSessionList(ArrayList<Session> sessionList) {
            this.sessionList = sessionList;
        }

        public void setGymBalanc(int gymBalanc) {
            this.gymBalanc = gymBalanc;
        }

        public ArrayList<String> getGymActions() {
            return gymActions;
        }

        public void setGymActions(ArrayList<String> gymActions) {
            this.gymActions = gymActions;
        }
    }


