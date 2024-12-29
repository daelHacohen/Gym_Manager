package gym.customers;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * The Person class represents an individual with personal details, balance, and identification.
 * It includes attributes such as name, balance, gender, date of birth, and a unique ID.
 */
public class Person {
    private String name;
    private int balance;
    private Gender gender;
    private String date_of_birth;
    private int id;
    private static int counter =1110;

    /**
     * Constructs a Person object with the specified name, balance, gender, and date of birth.
     * @param name         The name of the person.
     * @param balance      The initial balance of the person.
     * @param gender       The gender of the person.
     * @param date_of_birth The date of birth of the person in the format dd-MM-yyyy.
     */
    public Person(String name, int balance, Gender gender, String date_of_birth) {
        this.name = name;
        this.balance = balance;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        counter++;
        this.id = counter;
    }

    /**
     * Gets the name of the person.
     * @return The person's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the current balance of the person.
     * @return The person's balance.
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Gets the gender of the person.
     * @return The person's gender.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Gets the date of birth of the person.
     * @return The person's date of birth in the format dd-MM-yyyy.
     */
    public String getDate_of_birth() {
        return date_of_birth;
    }

    /**
     * Calculates and returns the age of the person based on their date of birth.
     * @return The person's age in years.
     */
    public int getAge() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birthDate = LocalDate.parse(this.date_of_birth, formatter);
        LocalDate currentDate = LocalDate.now();
        return (int) ChronoUnit.YEARS.between(birthDate, currentDate);
    }

    /**
     * Sets the balance of the person.
     * @param balance The new balance to set.
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * Gets the unique ID of the person.
     * @return The person's ID.
     */
    public int getId() {
        return id;
    }
}
