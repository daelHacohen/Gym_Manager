import java.util.ArrayList;

public class Secretary {
    private Person secretaryPerson;
    private int salary;

    public Secretary(Person secretaryPerson, int salary) {
        this.secretaryPerson = secretaryPerson;
        this.salary = salary;
    }

    public Client registerClient(Person person){
        Client client = new Client(person);
    }


    public Instructor hireInstructor(Person p, int salary, ArrayList<SessionType>sessionQualified ) {
        Instructor instructor = new Instructor(p, salary, sessionQualified);
        return instructor;
    }
}
