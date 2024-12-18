public class ClientFactory {//

    public static Client createClient(Person person) throws InvalidAgeException {
        // בדיקת גיל
        if (person.getAge() < 18) {
            throw new InvalidAgeException("Client must be at least 18 years old!");
        }

        // יצירת לקוח חדש
        return new Client(person);
    }
}