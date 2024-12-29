package gym.management;
import gym.Exception.*;
import gym.customers.*;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionFactory;
import gym.management.Sessions.SessionType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * The Secretary class manages gym operations, including client and instructor registrations,
 * session management, and salary payments. It also implements the Subject interface to
 * handle notifications.
 */
    public class Secretary implements Subject{
        private Person secretaryPerson;
        private int salary;
        private ArrayList<Client> gymClientList;
        private ArrayList<Observer> ObserverGymClientList;

        private ArrayList<Instructor> gymInstructorList;
        private ArrayList<Session> sessionList;
        private int gymBalanc;
        private ArrayList<String>gymActions;

        /**
         * Constructs a new Secretary object.
         * @param secretaryPerson The person acting as the secretary.
         * @param salary The salary of the secretary.
         */
        public Secretary(Person secretaryPerson, int salary) {
            this.secretaryPerson = secretaryPerson;
            this.salary = salary;
            gymClientList =new ArrayList<>();
            gymInstructorList =new ArrayList<>();
            sessionList =new ArrayList<>();
            gymActions= new ArrayList<>();
            ObserverGymClientList= new ArrayList<>();
        }

        /**
         * Registers a new client in the gym.
         * @param person The person to be registered as a client.
         * @return The registered Client object.
         * @throws InvalidAgeException if the client is under 18.
         * @throws DuplicateClientException if the client is already registered.
         */
        public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {
            if (!checkIfTheSameSecretary())return null;
            validateAge(person.getAge());
            Client newClient =new Client(person);
            findIfDuplicateClient(newClient,gymClientList);
            gymClientList.add(newClient);
            registerToReceiveMessages(ObserverGymClientList,newClient);
            addGymActions("Registered new client: "+newClient.getPerson().getName());
            return newClient;

        }
        /**
         * Checks if a client is already registered.
         * @param clientToComper The client to check.
         * @param clients The list of registered clients.
         * @throws DuplicateClientException if the client is already registered.
         */
        private void findIfDuplicateClient(Client clientToComper, ArrayList<Client>clients) throws DuplicateClientException {
            for (int i = 0; i < clients.size(); i++) {
                if (clients.get(i).equals(clientToComper)){
                    throw new DuplicateClientException("Error: The client is already registered");
                }
            }
        }
        /**
         * Checks if a client is already registered to the lesson.
         * @param clientToComper The client to check.
         * @param clients The list of registered clients.
         * @throws DuplicateClientException if the client is already registered to the lesson.
         */
        private void findIfDuplicateClientToLesson(Client clientToComper, ArrayList<Client>clients) throws DuplicateClientException {
            for (int i = 0; i < clients.size(); i++) {
                if (clients.get(i).equals(clientToComper)){
                    throw new DuplicateClientException("Error: The client is already registered for this lesson");
                }
            }
        }

        /**
         * Hires a new instructor.
         * @param p The person being hired.
         * @param salary The instructor's salary.
         * @param sessionQualified The sessions the instructor is qualified for.
         * @return The hired Instructor object.
         */
        public Instructor hireInstructor(Person p, int salary, ArrayList<SessionType>sessionQualified ) {
            if (!checkIfTheSameSecretary())return null;
            Instructor instructor = new Instructor(p, salary, sessionQualified);
            gymInstructorList.add(instructor);
            addGymActions("Hired new instructor: "+instructor.getInstructorPerson().getName()+" with salary per hour: "+instructor.getSalaryPerHour());
            return instructor;
        }
        /**
         * Checks if the current secretary instance matches the active secretary.
         * @return true if the current instance is the active secretary, otherwise false.
         */
        private boolean checkIfTheSameSecretary(){

            if (this==Gym.getInstance().getSecretary())return true;
            else
                checkIfTheSameSecretaryExeption();
           return false;
        }
        /**
         * Throws an exception if the current secretary is not the active secretary.
         * @throws NullPointerException if the current instance is not the active secretary.
         */
        private void checkIfTheSameSecretaryExeption()throws NullPointerException{
            if (this!=Gym.getInstance().getSecretary())throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
        }

        /**
         * Registers a client for a session.
         * @param c The client to register.
         * @param s The session to register for.
         * @throws DuplicateClientException if the client is already registered for the session.
         * @throws ClientNotRegisteredException if the client is not registered with the gym.
         */
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
           registerToReceiveMessages(s.getObserverClientsInCurrentClass(),c);
//            s.getObserverClientsInCurrentClass().add(c);
            addGymActions("Registered client: "+c.getPerson().getName()+" to session: "+ s.getSessionType() +" on "+s.getFormattedDateTime()+" for price: "+s.getPrice());
        }
        /**
         * Logs specific actions when a client's gender or age does not match the forum requirements.
         * @param forumType The type of forum (Male, Female, Seniors, All).
         * @param gender The gender of the client.
         * @param age The age of the client.
         */
        private  void addActionsIfFuromTypeIsNotValid(ForumType forumType, Gender gender, int age){
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
        /**
         * Validates whether a client's gender or age matches the forum requirements.
         * @param forumType The type of forum (Male, Female, Seniors, All).
         * @param gender The gender of the client.
         * @param age The age of the client.
         * @return true if the client meets the forum's requirements; false otherwise.
         */
        private boolean findIfForumTypeIsValid(ForumType forumType,Gender gender, int age){
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
        /**
         * Unregisters a client from the gym.
         * @param c The client to unregister.
         * @throws ClientNotRegisteredException if the client is not found in the gym's client list.
         */
    public void unregisterClient(Client c) throws ClientNotRegisteredException {
            if (!checkIfTheSameSecretary())return;
            findIfClientNotRegistered(c,gymClientList);
        for (int i = 0; i < gymClientList.size(); i++) {
            if (c==gymClientList.get(i)){gymClientList.remove(c);
                unRegisterToReceiveMessages(ObserverGymClientList,c);
            }
        }
        addGymActions("Unregistered client: "+c.getPerson().getName());

    }

        /**
         * Checks if a client is registered in the client list.
         * @param client The client to verify.
         * @param clientArrayList The list of registered clients.
         * @throws ClientNotRegisteredException if the client is not found.
         */
        private void findIfClientNotRegistered(Client client , ArrayList<Client>clientArrayList) throws ClientNotRegisteredException {
            boolean registed =false;
        for (Client value : clientArrayList) {
            if (value.equals(client)) {
                registed = true;
                break;
            }
        }
            if (!registed)throw new ClientNotRegisteredException("Error: Registration is required before attempting to unregister");//צריך לראות מה בדיוק צריך בהדפסות בכל פונקציה



    }
        /**
         * Checks if a client is registered in the lesson list.
         * @param client The client to verify.
         * @param clientArrayList The list of registered clients for lessons.
         * @throws ClientNotRegisteredException if the client is not found in the lesson list.
         */
        private void findIfClientNotRegisteredToLesson(Client client , ArrayList<Client>clientArrayList) throws ClientNotRegisteredException {
            boolean registed =false;
            for (Client value : clientArrayList) {
                if (value.equals(client)) {
                    registed = true;
                    break;
                }
            }
             if (!registed)throw new ClientNotRegisteredException("Error: The client is not registered with the gym and cannot enroll in lessons");//צריך לראות מה בדיוק צריך בהדפסות בכל פונקציה

        }

        /**
         * Adds a new session to the gym.
         * @param sessionType The type of session.
         * @param dateAndHour The date and time of the session.
         * @param forumType The forum type of the session.
         * @param instructor The instructor assigned to the session.
         * @return The newly created session.
         * @throws InstructorNotQualifiedException if the instructor is not qualified for the session type.
         */
    public Session addSession(SessionType sessionType, String dateAndHour, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
            if (!checkIfTheSameSecretary())return null;
        findIfQualified(instructor.getSessionQualified(),sessionType);
        Session newsession = SessionFactory.createSession(sessionType,dateAndHour,forumType,instructor);
        sessionList.add(newsession);
        instructor.setCounterSesseions(instructor.getCounterSesseions()+1);
        addGymActions("Created new session: "+ newsession.getSessionType() +" on "+newsession.getFormattedDateTime()+" with instructor: "+ newsession.getThisSessionInstructor().getInstructorPerson().getName());
        return newsession;
    }
    /**
     * Pays salaries to the secretary and instructors.
     */
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

    /**
     * Validates if the client's age meets gym requirements.
     * @param age The age to validate.
     * @throws InvalidAgeException if the age is below 18.
     */
   private void validateAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Error: Client must be at least 18 years old to register");

        }
    }
        /**
         * Checks if an instructor is qualified to conduct a session type.
         * @param sessionQualified The list of session types the instructor is qualified for.
         * @param currentSessionType The session type to validate.
         * @throws InstructorNotQualifiedException if the instructor is not qualified for the session type.
         */
    private void findIfQualified(ArrayList<SessionType>sessionQualified, SessionType currentSessionType) throws InstructorNotQualifiedException {
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
       for (Observer observer:s.getObserverClientsInCurrentClass()){
           observer.update(messege);
       }
           addGymActions("A message was sent to everyone registered for session " + s.getSessionType() + " on " +s.getFormattedDateTime() + " : " + messege );
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
                    for (Observer observer: s.getObserverClientsInCurrentClass()) {
                        observer.update(message); // שליחת הודעה ללקוחות
                    }

                    addGymActions("A message was sent to everyone registered for a session on " + sessionDate + " : " + message);
                }
            }
        }


        public void notify (String messege){//הודעות לכלל לקוחות המכון
            if (!checkIfTheSameSecretary())return;
        for (Observer observer : ObserverGymClientList){
            observer.update(messege);
        }
            addGymActions("A message was sent to all gym clients: " + messege);
        }
        @Override
        public void registerToReceiveMessages(ArrayList<Observer> observerArrayList,Observer observer) {
            observerArrayList.add(observer);

        }

        @Override
        public void unRegisterToReceiveMessages(ArrayList<Observer> observerArrayList,Observer observer) {
        observerArrayList.remove(observer);
        }

        /**
         * Prints all recorded gym actions.
         */
        public void printActions() {
            if (!checkIfTheSameSecretary())return;
            for (int i = 0; i < gymActions.size(); i++) {
                System.out.println(gymActions.get(i));
            }

        }

        /**
         * Adds an action to the gym's activity log.
         * @param action The action description.
         */
        public void addGymActions(String action){
            if (!checkIfTheSameSecretary())return;
            gymActions.add(action);

        }

        public Person getPerson() {
            if (!checkIfTheSameSecretary())return null;
            return secretaryPerson;
        }

        public int getSalary() {
            if (!checkIfTheSameSecretary())return 0;
            return salary;
        }

        public int getGymBalanc() {
            if (!checkIfTheSameSecretary())return 0;
            return gymBalanc;
        }

        public ArrayList<Client> getGymClientList() {
            if (!checkIfTheSameSecretary())return null;
            return gymClientList;
        }

        public ArrayList<Instructor> getGymInstructorList() {
            if (!checkIfTheSameSecretary())return null;
            return gymInstructorList;
        }

        public ArrayList<Session> getSessionList() {
            if (!checkIfTheSameSecretary())return null;
            return sessionList;
        }


        public void setGymClientList(ArrayList<Client> gymClientList) {
            if (!checkIfTheSameSecretary())return;
            this.gymClientList = gymClientList;
        }

        public void setGymInstructorList(ArrayList<Instructor> gymInstructorList) {
            if (!checkIfTheSameSecretary())return;
            this.gymInstructorList = gymInstructorList;
        }

        public void setSessionList(ArrayList<Session> sessionList) {
            if (!checkIfTheSameSecretary())return;
            this.sessionList = sessionList;
        }

        public void setGymBalanc(int gymBalanc) {
            if (!checkIfTheSameSecretary())return;
            this.gymBalanc = gymBalanc;
        }

        public ArrayList<String> getGymActions() {
            if (!checkIfTheSameSecretary())return null;
            return gymActions;
        }

        public void setGymActions(ArrayList<String> gymActions) {
            if (!checkIfTheSameSecretary())return;
            this.gymActions = gymActions;
        }

    }


