public class Gym {
    private static Gym instance;
    private Secretary gymSecretary;
    private String gymName;

    private Gym() {

    }

    public static Gym getInstance() {
        if (instance == null) {
            synchronized (Gym.class){
            if (instance == null){
                instance=new Gym();
            }
         }
        }
        return instance;
    }
public void setName(String gym_Name){
        gymName = gym_Name;
}
public void setSecretary(Person p, int salary){
        gymSecretary = new Secretary(p, salary);

}
public Secretary getSecretary(){
        return gymSecretary;
}



}
