public class Client {

    private Person person;

    public Client(Person person) {
        this.person = person;
    }

    public String getName() {
        return person.getName();
    }

    public void setClient(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }
}



