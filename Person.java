import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Person {//
    private String name;
    private int balance;
    private Gender gender;
    private String date_of_birth;

    public Person(String name, int balance, Gender gender, String date_of_birth) {
        this.name = name;
        this.balance = balance;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public Gender getGender() {
        return gender;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }
    public int getAge() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birthDate = LocalDate.parse(this.date_of_birth, formatter);
        LocalDate currentDate = LocalDate.now();
        return (int) ChronoUnit.YEARS.between(birthDate, currentDate);
    }





}
