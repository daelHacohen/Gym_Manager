package gym.customers;
/**
 * The Observer interface defines a contract for objects that need to receive updates or notifications.
 */
public interface Observer {

    /**
     * Receives an update or notification message.
     * @param messege The message containing the update or notification.
     */
     void update(String messege);
}
