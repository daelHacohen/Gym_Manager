import java.util.ArrayList;

public class Secretary {//
    private Person secretaryPerson;
    private int salary;

    public Secretary(Person secretaryPerson, int salary) {
        this.secretaryPerson = secretaryPerson;
        this.salary = salary;
    }
/**נראה לי ככה אמור להיות השימוש בפקטורי בפונ' הזאת*/
    public Client registerClient(Person person) {
        ClientFactory clientFactory = new ClientFactory();
        Client newClient = clientFactory.createClient(person);

        return newClient;

          //  Client client = new Client(person);
    }


    public Instructor hireInstructor(Person p, int salary, ArrayList<SessionType>sessionQualified ) {
        Instructor instructor = new Instructor(p, salary, sessionQualified);
        return instructor;
    }

    public void registerClientToLesson(Client c, Session s) {

    }

    public void unregisterClient(Client c) {

    }

    public Session addSession(SessionType sessionType, String str, ForumType forumType, Instructor instructor) {
        return null;
    }

    public void paySalaries() {
        secretaryPerson.setBalance(secretaryPerson.getBalance()+salary);
    }
}
