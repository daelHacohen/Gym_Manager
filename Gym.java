public class Gym {//
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
       if (gymSecretary==null){gymSecretary = new Secretary(p, salary);
       }else {
           gymSecretary.setSecretaryPerson(p);
            gymSecretary.setSalary(salary);
       }
        gymSecretary.addGymActions("A new secretary has started working at the gym: "+p.getName());

}
public Secretary getSecretary(){
        return gymSecretary;
}



}
