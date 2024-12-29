package gym.management;
import gym.customers.Observer;
import gym.management.Sessions.Session;

import java.util.ArrayList;

public interface Subject {
     public void registerToReceiveMessages(ArrayList<Observer>observerArrayList, Observer observer);
    public void unRegisterToReceiveMessages(ArrayList<Observer>observerArrayList, Observer observer);
    /**
     * Sends a notification to all clients.
     * @param messege The message to send.
     */
    public void notify (String messege);
    /**
     * Sends a message to all clients registered for sessions on a specific day.
     * @param specificDay The day of the sessions (formatted as dd-MM-yyyy).
     * @param message The message to send.
     */
   public void notify(String specificDay, String message);
    /**
     * Sends a message to all clients registered for a specific session.
     * @param s The session to notify clients about.
     * @param messege The message to send.
     */
    public void notify (Session s, String messege);
    
}
