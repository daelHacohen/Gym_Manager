public class ClientFactory {
    public class ClientFactory {

        public static Client createClient(Person person) throws InvalidAgeException {
            if (person.getAge() < 18) {
                throw new InvalidAgeException("Client must be at least 16 years old!");
            }
            return new Client(person);
        }
    }
}
