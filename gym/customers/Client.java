package gym.customers;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The Client class represents a customer in the gym management system.
 * It implements the Observer interface, allowing clients to receive notifications.
 */
public class Client implements Observer  {

    private Person person;
    private ArrayList<String> notifications;

    /**
     * Constructs a Client with the given Person details.
     * @param person The Person object containing client details.
     */
    public Client(Person person) {
        notifications =new ArrayList<>();
        this.person = person;
    }

    /**
     * Retrieves the name of the client.
     * @return The name of the client.
     */
    public String getName() {
        return person.getName();
    }

//    public void setClient(Person person) {
//        this.person = person;
//    }

    /**
     * Retrieves the Person object associated with this client.
     * @return The associated Person object.
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Checks if this client is equal to another object.
     * @param obj The object to compare.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Client client = (Client) obj;
        return Objects.equals(person, client.person);
    }

    /**
     * Updates the client with a new notification message.
     * @param messege The message to add to the client's notifications.
     */
    @Override
    public void update(String messege) {
        notifications.add(messege);
    }

    /**
     * Retrieves all notifications received by the client as a formatted string.
     * @return A string representation of all notifications.
     */
    public String getNotifications() {
        String str = notifications.toString();
        return str;
    }



}



