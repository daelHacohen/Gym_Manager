import java.util.ArrayList;
import java.util.Objects;

public class Client implements Observer  {

    private Person person;
    private ArrayList<String> notifications;

    public Client(Person person) {
        notifications =new ArrayList<>();
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
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Client client = (Client) obj;
        return Objects.equals(person, client.person);
    }

    @Override
    public void update(String messege) {
        notifications.add(messege);
    }

    public String getNotifications() {
        String str = notifications.toString();
        return str;
    }

}



