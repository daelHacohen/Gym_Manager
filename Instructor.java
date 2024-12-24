import java.util.ArrayList;
import java.util.Arrays;

public class Instructor {

private Person instructorPerson;
private int salaryPerHour;
private ArrayList<SessionType> sessionQualified;
private  int counterSesseions;

    public Instructor(Person instructorPerson, int salaryPerHour, ArrayList<SessionType>  sessionQualified) {
        this.instructorPerson = instructorPerson;
        this.salaryPerHour = salaryPerHour;
        this.sessionQualified = sessionQualified;
        this.counterSesseions =0;
    }

    public Person getInstructorPerson() {
        return instructorPerson;
    }

    public int getSalaryPerHour() {
        return salaryPerHour;
    }

    public ArrayList<SessionType> getSessionQualified() {
        return sessionQualified;
    }
    public int getCounterSesseions(){return this.counterSesseions;}

    public void setCounterSesseions(int counterSesseions) {
        this.counterSesseions = counterSesseions;
    }
}


