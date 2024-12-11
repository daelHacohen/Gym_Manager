import java.util.ArrayList;
import java.util.Arrays;

public class Instructor {

private Person instructorPerson;
private int salaryPerHour;
private ArrayList<SessionType> sessionQualified;

    public Instructor(Person instructorPerson, int salaryPerHour, ArrayList<SessionType>  sessionQualified) {
        this.instructorPerson = instructorPerson;
        this.salaryPerHour = salaryPerHour;
        this.sessionQualified = sessionQualified;
    }
}


