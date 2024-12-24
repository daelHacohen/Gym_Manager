import java.util.ArrayList;

public class Gym {//
    private static Gym instance;
    private Secretary gymSecretary;
    private String gymName;

    private Gym() {//

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
       if (gymSecretary==null) {
           gymSecretary = new Secretary(p, salary);
       }else {

           ArrayList<Client> copyGymClientList = gymSecretary.getGymClientList();
           ArrayList<Instructor> copyGymInstructorList = gymSecretary.getGymInstructorList();
           ArrayList<Session> copySessionList = gymSecretary.getSessionList();
           int copyGymBalanc = gymSecretary.getGymBalanc();
           ArrayList<String> copyGymActions = gymSecretary.getGymActions();

           gymSecretary = new Secretary(p, salary);
           gymSecretary.setGymClientList(copyGymClientList);
           gymSecretary.setGymInstructorList(copyGymInstructorList);
           gymSecretary.setSessionList(copySessionList);
           gymSecretary.setGymBalanc(copyGymBalanc);
           gymSecretary.setGymActions(copyGymActions);
       }
    for (int i = 0; i < gymSecretary.getGymClientList().size(); i++) {
        if (gymSecretary.getGymClientList().get(i).getPerson()==p){
            gymSecretary.getGymClientList().remove(i);
        }
    }
       gymSecretary.addGymActions("A new secretary has started working at the gym: "+p.getName());

}
public Secretary getSecretary(){
        return gymSecretary;
}


    @Override
    public String toString() {
        String outPut ="Gym Name: "+this.gymName+"\nGym Secretary: ID: "+gymSecretary.getPerson().getId()+" | Name: "+gymSecretary.getPerson().getName()+" | Gender: "+gymSecretary.getPerson().getGender()+" | Birthday: "+ getSecretary().getPerson().getDate_of_birth()+" | Age: "+gymSecretary.getPerson().getAge()+" | Balance: "+ gymSecretary.getPerson().getBalance()+" | Role: Secretary | Salary per Month: "+gymSecretary.getSalary()+"\nGym Balance: "+gymSecretary.getGymBalanc()+"\n\n";
        outPut+="Clients Data:\n";
        for (Client client: gymSecretary.getGymClientList()) {
            outPut+="ID: "+client.getPerson().getId()+" | Name: "+client.getName()+" | Gender: "+client.getPerson().getGender()+" | Birthday: "+client.getPerson().getDate_of_birth()+" | Age: "+client.getPerson().getAge()+" | Balance: "+client.getPerson().getBalance()+"\n";
        }
        outPut+="\nEmployees Data:\n";
        for (Instructor instructor: gymSecretary.getGymInstructorList()){
            outPut+="ID: "+instructor.getInstructorPerson().getId()+" | Name: "+instructor.getInstructorPerson().getName()+" | Gender: "+instructor.getInstructorPerson().getGender()+" | Birthday: "+instructor.getInstructorPerson().getDate_of_birth()+" | Age: "+instructor.getInstructorPerson().getAge()+" | Balance: "+instructor.getInstructorPerson().getBalance()+" | Role: Instructor | Salary per Hour: "+instructor.getSalaryPerHour()+" | Certified Classes:"+arrayToString(instructor.getSessionQualified())+"\n";
        }
       outPut+="ID: "+gymSecretary.getPerson().getId()+" | Name: "+gymSecretary.getPerson().getName()+" | Gender: "+gymSecretary.getPerson().getGender()+" | Birthday: "+ getSecretary().getPerson().getDate_of_birth()+" | Age: "+gymSecretary.getPerson().getAge()+" | Balance: "+ gymSecretary.getPerson().getBalance()+" | Role: Secretary | Salary per Month: "+gymSecretary.getSalary()+"\n\n";
        outPut+="Sessions Data:\n";
        for (Session session: gymSecretary.getSessionList()){
            outPut+="Session Type: "+session.getSessionType()+" | Date: "+session.dateAndHour+" | Forum: "+session.getForumType()+" | Instructor: "+session.thisSessionInstructor.getInstructorPerson().getName()+" | Participants: "+session.getListOfClientsInCurrentClass().size()+"/"+session.getNumber_of_people_in_the_class()+"\n";
        }

        return outPut;
    }
    private String arrayToString(ArrayList<SessionType>sessionQualifiedList){
        String str = " "+sessionQualifiedList.get(0);
        for (int i = 1; i < sessionQualifiedList.size(); i++) {
            str+= ", "+sessionQualifiedList.get(i);
        }
        return str;
    }



}
