public class Client {

    private Person person;

    public Client(Person person) {
        this.person = person;
    }

    public String getName() {
        return person.getName();
    }
}

}
