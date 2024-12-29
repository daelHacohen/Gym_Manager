package gym.customers;
import gym.management.Sessions.SessionType;
import java.util.ArrayList;

/**
 * The Instructor class represents an instructor in the gym.
 * It holds information about the instructor's personal details, salary, qualified session types, and session count.
 */
public class Instructor {

private Person instructorPerson;
private int salaryPerHour;
private ArrayList<SessionType> sessionQualified;
private  int counterSesseions;


    /**
     * Constructs an Instructor object with the specified personal details, salary, and qualified sessions.
     * @param instructorPerson The personal details of the instructor.
     * @param salaryPerHour    The salary per hour for the instructor.
     * @param sessionQualified A list of session types the instructor is qualified to lead.
     */
    public Instructor(Person instructorPerson, int salaryPerHour, ArrayList<SessionType>  sessionQualified) {
        this.instructorPerson = instructorPerson;
        this.salaryPerHour = salaryPerHour;
        this.sessionQualified = sessionQualified;
        this.counterSesseions =0;
    }

    /**
     * Gets the personal details of the instructor.
     * @return The Person object representing the instructor's details.
     */
    public Person getInstructorPerson() {
        return instructorPerson;
    }

    /**
     * Gets the salary per hour of the instructor.
     * @return The salary per hour.
     */
    public int getSalaryPerHour() {
        return salaryPerHour;
    }

    /**
     * Gets the list of session types the instructor is qualified to lead.
     * @return A list of SessionType objects.
     */
    public ArrayList<SessionType> getSessionQualified() {
        return sessionQualified;
    }

    /**
     * Gets the number of sessions the instructor has led.
     * @return The number of sessions.
     */
    public int getCounterSesseions(){return this.counterSesseions;}

    /**
     * Sets the number of sessions the instructor has led.
     * @param counterSesseions The number of sessions to set.
     */
    public void setCounterSesseions(int counterSesseions) {
        this.counterSesseions = counterSesseions;
    }
}


