import java.util.ArrayList;

public interface Subject {
     public void registerToReceiveMessages(ArrayList<Observer>observerArrayList, Observer observer);
    public void unRegisterToReceiveMessages(ArrayList<Observer>observerArrayList, Observer observer);
    public void notify (String messege);
   public void notify(String specificDay, String message);
    public void notify (Session s,String messege);
    
}
