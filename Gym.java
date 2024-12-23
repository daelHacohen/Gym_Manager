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


    @Override
    public String toString() {
        String outPut ="Gym Name: "+this.gymName+"\nGym Secretary: ID: 1113 | Name: "+gymSecretary.getPerson().getName()+" | Gender: "+gymSecretary.getPerson().getGender()+" | Birthday: "+ getSecretary().getPerson().getDate_of_birth()+" | Age: "+gymSecretary.getPerson().getAge()+" | Balance: "+ gymSecretary.getPerson().getBalance()+" | Role: Secretary | Salary per Month: "+gymSecretary.getSalary()+"\nGym Balance: "+gymSecretary.getGymBalanc()+"\n\n";
        outPut+="Clients Data:\n";
        for (Client client: gymSecretary.getGymClientList()) {
            outPut+="ID: 1112 | Name: "+client.getName()+" | Gender: "+client.getPerson().getGender()+" | Birthday: "+client.getPerson().getDate_of_birth()+" | Age: "+client.getPerson().getAge()+" | Balance: "+client.getPerson().getBalance()+"\n";
        }
        outPut+="\nEmployees Data:\n";
        for (Instructor instructor: gymSecretary.getGymInstructorList()){
            outPut+="ID: 1112 | Name: "+instructor.getInstructorPerson().getName()+" | Gender: "+instructor.getInstructorPerson().getGender()+" | Birthday: "+instructor.getInstructorPerson().getDate_of_birth()+" | Age: "+instructor.getInstructorPerson().getAge()+" | Balance: "+instructor.getInstructorPerson().getBalance()+" | Role: Instructor | Salary per Hour: "+instructor.getSalaryPerHour()+" | Certified Classes: "+instructor.getSessionQualified().toString()+"\n";
        }
       outPut+="ID: 1113 | Name: "+gymSecretary.getPerson().getName()+" | Gender: "+gymSecretary.getPerson().getGender()+" | Birthday: "+ getSecretary().getPerson().getDate_of_birth()+" | Age: "+gymSecretary.getPerson().getAge()+" | Balance: "+ gymSecretary.getPerson().getBalance()+" | Role: Secretary | Salary per Month: "+gymSecretary.getSalary()+"\n\n";
        outPut+="Sessions Data:\n";
        for (Session session: gymSecretary.getSessionList()){
            outPut+="Session Type: "+session.getSessionType()+" | Date: "+session.dateAndHour+" | Forum: "+session.getForumType()+" | Instructor: "+session.thisSessionInstructor.getInstructorPerson().getName()+" | Participants: "+session.getListOfClientsInCurrentClass().size()+"/"+session.getNumber_of_people_in_the_class()+"\n";
        }

        return outPut;
    }
}
