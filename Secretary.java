import java.util.ArrayList;

public class Secretary {
    private Person secretaryPerson;
    private int salary;
    private ArrayList<Client> gymClientList;
    private ArrayList<Instructor> gymInstructorList;

    public Secretary(Person secretaryPerson, int salary) {
        this.secretaryPerson = secretaryPerson;
        this.salary = salary;
        gymClientList =new ArrayList<>();
        gymInstructorList =new ArrayList<>();
    }

    public Client registerClient(Person person) throws InvalidAgeException {
        validateAge(person.getAge());
        Client newClient =new Client(person);
        gymClientList.add(newClient);
//        ClientFactory clientFactory = new ClientFactory();
//        Client newClient = clientFactory.createClient(person);
        return newClient;
    }


    public Instructor hireInstructor(Person p, int salary, ArrayList<SessionType>sessionQualified ) {
        Instructor instructor = new Instructor(p, salary, sessionQualified);
        gymInstructorList.add(instructor);
        return instructor;
    }

    public void registerClientToLesson(Client c, Session s) {

    }

    public void unregisterClient(Client c) {
        for (int i = 0; i < gymClientList.size(); i++) {
            if (c==gymClientList.get(i))gymClientList.remove(i);

        }

    }

    public Session addSession(SessionType sessionType, String str, ForumType forumType, Instructor instructor) {
        return null;
    }

    public void paySalaries() {
        secretaryPerson.setBalance(secretaryPerson.getBalance()+salary);
    }
public static void validateAge(int age) throws InvalidAgeException {
    if (age < 18) {
        throw new InvalidAgeException("Age must be 18 or older.");

        }
    }
    }
